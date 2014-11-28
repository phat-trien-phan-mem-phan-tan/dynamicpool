package vn.edu.hust.student.dynamicpool.bll.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vn.edu.hust.student.dynamicpool.presentation.gameobject.EDirection;

public class PoolManager {
	
	private Logger logger = LoggerFactory.getLogger(PoolManager.class);
	private List<Pool> pools;

	public PoolManager() {
		pools = new ArrayList<Pool>();
	}

	public List<Pool> getPools() {
		return pools;
	}
	
	public void addClientPool(Pool pool) {
		this.pools.add(pool);
	}
	
	public void addHostPool(Pool pool) {
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
		FindCommonEdgeFunction.findCommonEdge(this.pools);
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
					logger.error("Passing");
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
					allFishes.get(pool.getDeviceInfo().getClientName()).add(fish);
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
			logger.debug(String.format("{0} hit left: state {1} ", fish.getFishId(), fish.getFishState()));
			hitLeft(allFishes, pool, fish);
		} else if (fishBoundary.getMaxX() >= poolBoundary.getMaxX()) {
			logger.debug(String.format("{0} hit right: state {1} ", fish.getFishId(), fish.getFishState()));
			hitRight(allFishes, pool, fish);
		} else if (fishBoundary.getMinY() <= poolBoundary.getMinY()) {
			logger.debug(String.format("{0} hit bottom: state {1} ", fish.getFishId(), fish.getFishState()));
			hitBottom(allFishes, pool, fish);
		} else if (fishBoundary.getMaxY() >= poolBoundary.getMaxY()) {
			logger.debug(String.format("{0} hit top: state {1} ", fish.getFishId(), fish.getFishState()));
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
			fish.setFishState(FishState.RETURN);
		} else {
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
		}
	}
	
	private void putFishsFromContainerToPools(Map<String, List<IFish>> allFishes) {
		for (Pool pool : pools) {
			pool.setFishes(allFishes.get(pool.getDeviceInfo().getClientName()));
		}
	}
}
