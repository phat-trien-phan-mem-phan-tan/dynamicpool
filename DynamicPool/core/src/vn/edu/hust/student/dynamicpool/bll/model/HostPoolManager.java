package vn.edu.hust.student.dynamicpool.bll.model;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vn.edu.hust.student.dynamicpool.events.EventDestination;
import vn.edu.hust.student.dynamicpool.events.EventType;

public class HostPoolManager implements PoolManager {
	private Logger logger = LoggerFactory.getLogger(HostPoolManager.class);
	private List<Pool> pools;

	public HostPoolManager() {
		pools = new ArrayList<Pool>();
	}

	public List<Pool> getPools() {
		return pools;
	}

	public void addPool(Pool pool) {
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

	@Override
	public void updateLocationOfFishes(float deltaTime) {
		detectCollision();
		for (Pool pool : pools) {
			pool.updateLocationOfFishes(deltaTime);
		}
	}

	class MovingFish {
		public MovingFish(IFish fish, Pool pool, String neighbour) {
			this.fish = fish;
			this.fromPool = pool;
			this.neighbourClientName = neighbour;
		}
		public IFish fish;
		public Pool fromPool;
		public String neighbourClientName;
		public void removeFish() {
			fromPool.removeFish(fish);
		}
		public void moveTo(Pool neighbourPool) {
			neighbourPool.addFish(fish);
		}
	}

	private List<MovingFish> movingFishes = new ArrayList<HostPoolManager.MovingFish>();

	private void detectCollision() {
		for (Pool pool : pools) {
			for (IFish fish : pool.getFishes()) {
				switch (fish.getFishState()) {
				case INSIDE:
					String neighbour = detectCollisionForInsideFish(pool, fish);
					if (neighbour != null) {
						movingFishes.add(new MovingFish(fish, pool, neighbour));
					}
					break;
				case OUTSIDE:
				case PASSING:
				case RETURN:
				default:
					Boundary poolBoundary = pool.getBoundary();
					Boundary fishBoundary = fish.getBoundary();
					if (fishBoundary.isInside(poolBoundary)) {
						logger.debug("new fish {} come inside pool {}", fish
								.getFishId(), pool.getDeviceInfo()
								.getClientName());
						fish.setFishState(FishState.INSIDE);
					}
					break;
				}
			}
		}
		movingFish();
	}

	private void movingFish() {
		for (MovingFish moving : movingFishes) {
			Pool neighbourPool = this.getPool(moving.neighbourClientName);
			moving.removeFish();
			moving.moveTo(neighbourPool);  
		}
		movingFishes.clear();
	}

	private String detectCollisionForInsideFish(Pool pool, IFish fish) {
		Boundary fishBoundary = fish.getBoundary();
		Boundary poolBoundary = pool.getBoundary();
		if (fishBoundary.getMinX() <= poolBoundary.getMinX()) {
			logger.debug("fish {} hit left: state {} ", fish.getFishId(),
					fish.getFishState());
			return hitLeft(pool, fish);
		} else if (fishBoundary.getMaxX() >= poolBoundary.getMaxX()) {
			logger.debug("fish {} hit right: state {} ", fish.getFishId(),
					fish.getFishState());
			return hitRight(pool, fish);
		} else if (fishBoundary.getMinY() <= poolBoundary.getMinY()) {
			logger.debug("fish {} hit bottom: state {} ", fish.getFishId(),
					fish.getFishState());
			return hitBottom(pool, fish);
		} else if (fishBoundary.getMaxY() >= poolBoundary.getMaxY()) {
			logger.debug("fish {} hit top: state {} ", fish.getFishId(),
					fish.getFishState());
			return hitTop(pool, fish);
		}
		return null;
	}

	private String hitLeft(Pool pool, IFish fish) {
		Segment segment = pool.detectCollisionLeftSegments(fish.getBoundary());
		fish.setFishState(FishState.RETURN);
		if (segment == null) {
			fish.getTrajectory().changeDirection(EDirection.LEFT);
			return null;
		} else {
			movingOverNeighbourPool(fish, segment);
			return segment.getNeighbourClientName();
		}
	}

	private String hitRight(Pool pool, IFish fish) {
		Segment segment = pool.detectCollisionRightSegments(fish.getBoundary());
		fish.setFishState(FishState.RETURN);
		if (segment == null) {
			fish.getTrajectory().changeDirection(EDirection.RIGHT);
			return null;
		} else {
			movingOverNeighbourPool(fish, segment);
			return segment.getNeighbourClientName();
		}
	}

	private String hitBottom(Pool pool, IFish fish) {
		Segment segment = pool
				.detectCollisionBottomSegments(fish.getBoundary());
		fish.setFishState(FishState.RETURN);
		if (segment == null) {
			fish.getTrajectory().changeDirection(EDirection.BOTTOM);
			return null;
		} else {
			movingOverNeighbourPool(fish, segment);
			return segment.getNeighbourClientName();
		}
	}

	private String hitTop(Pool pool, IFish fish) {
		Segment segment = pool.detectCollisionTopSegments(fish.getBoundary());
		fish.setFishState(FishState.RETURN);
		if (segment == null) {
			fish.getTrajectory().changeDirection(EDirection.TOP);
			return null;
		} else {
			movingOverNeighbourPool(fish, segment);
			return segment.getNeighbourClientName();
		}
	}

	private void movingOverNeighbourPool(IFish fish, Segment segment) {
		List<Object> params = new ArrayList<Object>();
		params.add(segment.getNeighbourClientName());
		params.add(this.getNewFishForNeighbourPool(fish.clone(),
				segment.getNeighbourClientName()));
		EventDestination.getInstance().dispatchSuccessEventWithObject(
				EventType.BLL_SEND_FISH, params);
	}

	private IFish getNewFishForNeighbourPool(IFish fish,
			String neighbourClientName) {
		IFish newFish = fish.clone();
		Boundary poolBoundary = this.getPool(neighbourClientName).getBoundary();
		newFish.getBoundary()
				.getLocation()
				.setX(fish.getBoundary().getLocation().getX()
						- poolBoundary.getLocation().getX());
		newFish.getBoundary()
				.getLocation()
				.setY(poolBoundary.getLocation().getY()
						- poolBoundary.getLocation().getY());
		return newFish;
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

	public void removeFish(String clientName, String fishId) {
		Pool pool = this.getPool(clientName);
		if (pool == null) {
			logger.error("removeFish: pool {} not found", clientName);
			return;
		}
		pool.removeFishById(fishId);
	}
}