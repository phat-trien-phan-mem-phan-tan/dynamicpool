package vn.edu.hust.student.dynamicpool.bll.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vn.edu.hust.student.dynamicpool.events.EventDestination;
import vn.edu.hust.student.dynamicpool.events.EventType;

public class PoolManager {
	private int lastFishId = 1;
	private Logger logger = LoggerFactory.getLogger(PoolManager.class);
	private List<Pool> pools;

	public PoolManager() {
		pools = new ArrayList<Pool>();
	}

	public List<Pool> getPools() {
		return pools;
	}

	public void addClientPool(Pool pool) {
		logger.debug("add client pool {}", pool.getDeviceInfo().getClientName());
		this.pools.add(pool);
	}

	public void addHostPool(Pool pool) {
		logger.debug("add host pool {}", pool.getDeviceInfo().getClientName());
		Point location = getDefaultLocation(pool);
		pool.getBoundary().setLocation(location);
		pools.add(pool);
		calculate();
	}

	private Point getDefaultLocation(Pool poolServer) {
		float x = 0, y = 0;
		for (Pool pool : pools) {
			x = Math.max(pool.getBoundary().getMaxX(), x);
			y = Math.min(pool.getBoundary().getMinY(), y);
		}
		return new Point(x, y);
	}

	public void clear() {
		pools.clear();
	}

	public int size() {
		return this.pools.size();
	}

	public void calculate() {
		FindCommonEdgeFunction.test(pools);
		// FindCommonEdgeFunction.findCommonEdge(this.pools);
	}

	public void updateLocationOfFishes(float deltaTime) {
		for (Pool pool : pools) {
			pool.updateLocationOfFishes(deltaTime);
		}
		detectCollision();
	}

	private void detectCollision() {
		Map<String, List<IFish>> allFishes = createFishContainer();
		for (Pool pool : pools) {
			for (IFish fish : pool.getFishes()) {
				switch (fish.getFishState()) {
				case INSIDE:
					detectCollisionForInsideFish(allFishes, pool, fish);
					break;
				case PASSING:
					if (fish.getBoundary().isOutside(pool.getBoundary()))
						return;
					break;
				case OUTSIDE:
					logger.error("Outsite");
					break;
				case RETURN:
				default:
					Boundary poolBoundary = pool.getBoundary();
					Boundary fishBoundary = fish.getBoundary();
					if (fishBoundary.isInside(poolBoundary)) {
						fish.setFishState(FishState.INSIDE);
					}
					allFishes.get(pool.getDeviceInfo().getClientName()).add(
							fish);
					break;
				}
			}
		}
		putFishsFromContainerToPools(allFishes);
	}

	private Map<String, List<IFish>> createFishContainer() {
		Map<String, List<IFish>> allFishes = new HashMap<String, List<IFish>>();
		for (Pool pool : pools) {
			allFishes.put(pool.getDeviceInfo().getClientName(),
					new ArrayList<IFish>());
		}
		return allFishes;
	}

	private void detectCollisionForInsideFish(
			Map<String, List<IFish>> allFishes, Pool pool, IFish fish) {
		Boundary fishBoundary = fish.getBoundary();
		Boundary poolBoundary = pool.getBoundary();
		if (fishBoundary.getMinX() <= poolBoundary.getMinX()) {
			logger.debug("fish {} hit left: state {} ", fish.getFishId(),
					fish.getFishState());
			hitLeft(allFishes, pool, fish);
		} else if (fishBoundary.getMaxX() >= poolBoundary.getMaxX()) {
			logger.debug("fish {} hit right: state {} ", fish.getFishId(),
					fish.getFishState());
			hitRight(allFishes, pool, fish);
		} else if (fishBoundary.getMinY() <= poolBoundary.getMinY()) {
			logger.debug("fish {} hit bottom: state {} ", fish.getFishId(),
					fish.getFishState());
			hitBottom(allFishes, pool, fish);
		} else if (fishBoundary.getMaxY() >= poolBoundary.getMaxY()) {
			logger.debug("fish {} hit top: state {} ", fish.getFishId(),
					fish.getFishState());
			hitTop(allFishes, pool, fish);
		}
		allFishes.get(pool.getDeviceInfo().getClientName()).add(fish);
	}

	private void hitLeft(Map<String, List<IFish>> allFishes, Pool pool,
			IFish fish) {
		Segment segment = pool.detectCollisionLeftSegments(fish.getBoundary());
		if (segment == null) {
			fish.getTrajectory().changeDirection(EDirection.LEFT);
			fish.setFishState(FishState.RETURN);
		} else {
			movingOverNeighbourPool(allFishes, fish, segment);
		}
	}

	private void hitRight(Map<String, List<IFish>> allFishes, Pool pool,
			IFish fish) {
		Segment segment = pool.detectCollisionRightSegments(fish.getBoundary());
		if (segment == null) {
			fish.getTrajectory().changeDirection(EDirection.RIGHT);
			fish.setFishState(FishState.RETURN);
		} else {
			movingOverNeighbourPool(allFishes, fish, segment);
		}
	}

	private void hitBottom(Map<String, List<IFish>> allFishes, Pool pool,
			IFish fish) {
		Segment segment = pool
				.detectCollisionBottomSegments(fish.getBoundary());
		if (segment == null) {
			fish.getTrajectory().changeDirection(EDirection.BOTTOM);
			fish.setFishState(FishState.RETURN);
		} else {
			movingOverNeighbourPool(allFishes, fish, segment);
		}
	}

	private void hitTop(Map<String, List<IFish>> allFishes, Pool pool,
			IFish fish) {
		Segment segment = pool.detectCollisionTopSegments(fish.getBoundary());
		if (segment == null) {
			fish.getTrajectory().changeDirection(EDirection.TOP);
			logger.debug("after hit: return");
			fish.setFishState(FishState.RETURN);
		} else {
			logger.debug("after hit: move out");
			movingOverNeighbourPool(allFishes, fish, segment);
		}
	}

	private void movingOverNeighbourPool(Map<String, List<IFish>> allFishes,
			IFish fish, Segment segment) {
		fish.setFishState(FishState.PASSING);
		if (segment.getNeighbourClientName() != null) {
			IFish referenceFish = fish.cloneIgnoreFishState();
			referenceFish.setFishState(FishState.OUTSIDE);
			allFishes.get(segment.getNeighbourClientName()).add(referenceFish);
			List<Object> params = new ArrayList<Object>();
			params.add(segment.getNeighbourClientName());
			params.add(referenceFish);
			logger.info("send fish {} to client {}", referenceFish.getFishId(),
					segment.getNeighbourClientName());
			EventDestination.getInstance().dispatchSuccessEventWithObject(
					EventType.BLL_SEND_FISH, params);
		} else {
			logger.debug("neighbour client name is null");
		}
	}

	private void dispatchSendFishEventToHostBLL(Pool pool, IFish refFish) {
		List<Object> params = new ArrayList<Object>();
		params.add(pool.getDeviceInfo().getClientName());
		if (refFish != null) {
			params.add(refFish);
			EventDestination.getInstance().dispatchSuccessEventWithObject(
					EventType.BLL_SEND_FISH, params);
			logger.info("send fish {} to client {}", refFish.getFishId(), pool
					.getDeviceInfo().getClientName());
		} else {
			EventDestination.getInstance().dispatchFailEvent(
					EventType.BLL_SEND_FISH);
			logger.error("error when send a fish to new pool");
		}
	}

	private void putFishsFromContainerToPools(Map<String, List<IFish>> allFishes) {
		for (Pool pool : pools) {
			pool.setFishes(allFishes.get(pool.getDeviceInfo().getClientName()));
		}
	}

	public IFish addFish(String clientName, IFish fish) {
		logger.debug("add new fish to host: id {}", fish.getFishId());
		Pool pool = getPool(clientName);
		if (pool == null) {
			logger.error("client name: {} is not exist", clientName);
			return null;
		}
		pool.addFish(fish);
		return fish;
	}

	private Pool getPool(String clientName) {
		for (Pool pool : pools) {
			String poolClientName = pool.getDeviceInfo().getClientName();
			if (poolClientName.equals(clientName))
				return pool;
		}
		return null;
	}

	public Pool getPoolForClient(String clientName) {
		Pool pool = getPool(clientName);
		if (pool == null) {
			logger.error("not found client name {}", clientName);
			return null;
		}
		Pool clientPool = new Pool(pool.getDeviceInfo());
		clientPool.setScale(pool.getScale());
		for (Segment segment : pool.getSegments()) {
			Segment clientSegment = segment.cloneWithoutNeighbour();
			clientPool.getSegments().add(clientSegment);
		}
		return clientPool;
	}
}
