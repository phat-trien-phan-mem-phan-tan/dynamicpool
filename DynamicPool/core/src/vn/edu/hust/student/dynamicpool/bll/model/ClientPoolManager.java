package vn.edu.hust.student.dynamicpool.bll.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientPoolManager implements PoolManager {
	private Logger logger = LoggerFactory.getLogger(ClientPoolManager.class);
	private Pool clientPool = null;

	public ClientPoolManager() {
		clientPool = new Pool();
	}

	@Override
	public void updateLocationOfFishes(float deltaTime) {
		detectCollision();
		clientPool.updateLocationOfFishes(deltaTime);
	}

	private void detectCollision() {
		IFish removeFish = null;
		for (IFish fish : clientPool.getFishes()) {
			Boundary poolBoundary = clientPool.getBoundary();
			Boundary fishBoundary = fish.getBoundary();
			switch (fish.getFishState()) {
			case INSIDE:
				detectCollisionForInsideFish(fish);
				break;
			case PASSING:
				if (fishBoundary.isOutside(poolBoundary)) {
					logger.debug("remove fish {} from client pool",
							fish.getFishId());
					removeFish = fish;
				}
				break;
			case OUTSIDE:
				if (!fish.getBoundary().isOutside(clientPool.getBoundary())) {
					logger.debug("fish {} begin to draw in client pool",
							fish.getFishId());
					fish.setFishState(FishState.RETURN);
				}
				break;
			case RETURN:
			default:
				if (fishBoundary.isInside(poolBoundary)) {
					logger.debug("new fish {} come inside client pool",
							fish.getFishId());
					fish.setFishState(FishState.INSIDE);
				}
				break;
			}
		}
		if (removeFish != null) clientPool.removeFish(removeFish);
	}

	private void detectCollisionForInsideFish(IFish fish) {
		Boundary fishBoundary = fish.getBoundary();
		Boundary poolBoundary = clientPool.getBoundary();
		if (fishBoundary.getMinX() <= poolBoundary.getMinX()) {
			logger.debug("fish {} hit left: state {} ", fish.getFishId(),
					fish.getFishState());
			hitLeft(fish, fishBoundary);
		} else if (fishBoundary.getMaxX() >= poolBoundary.getMaxX()) {
			logger.debug("fish {} hit right: state {} ", fish.getFishId(),
					fish.getFishState());
			hitRight(fish, fishBoundary);
		} else if (fishBoundary.getMinY() <= poolBoundary.getMinY()) {
			logger.debug("fish {} hit bottom: state {} ", fish.getFishId(),
					fish.getFishState());
			hitBottom(fish, fishBoundary);
		} else if (fishBoundary.getMaxY() >= poolBoundary.getMaxY()) {
			logger.debug("fish {} hit top: state {} ", fish.getFishId(),
					fish.getFishState());
			hitTop(fish, fishBoundary);
		}
	}

	private void hitLeft(IFish fish, Boundary fishBoundary) {
		Segment segment = clientPool.detectCollisionLeftSegments(fishBoundary);
		if (segment == null) {
			fish.getTrajectory().changeDirection(EDirection.LEFT);
			fish.setFishState(FishState.RETURN);
		} else {
			fish.setFishState(FishState.PASSING);
		}
	}

	private void hitRight(IFish fish, Boundary fishBoundary) {
		Segment segment = clientPool.detectCollisionRightSegments(fish
				.getBoundary());
		if (segment == null) {
			fish.getTrajectory().changeDirection(EDirection.RIGHT);
			fish.setFishState(FishState.RETURN);
		} else {
			fish.setFishState(FishState.PASSING);
		}
	}

	private void hitBottom(IFish fish, Boundary fishBoundary) {
		Segment segment = clientPool.detectCollisionBottomSegments(fish
				.getBoundary());
		if (segment == null) {
			fish.getTrajectory().changeDirection(EDirection.BOTTOM);
			fish.setFishState(FishState.RETURN);
		} else {
			fish.setFishState(FishState.PASSING);
		}
	}

	private void hitTop(IFish fish, Boundary fishBoundary) {
		Segment segment = clientPool.detectCollisionTopSegments(fishBoundary);
		if (segment == null) {
			fish.getTrajectory().changeDirection(EDirection.TOP);
			fish.setFishState(FishState.RETURN);
		} else {
			fish.setFishState(FishState.PASSING);
		}
	}

	public void updateSetting(Pool clientPoolSetting) {
		clientPool.getDeviceInfo().setClientName(clientPoolSetting.getDeviceInfo()
				.getClientName());
		clientPool.getBoundary().setWidth(clientPoolSetting.getBoundary().getWidth());
		clientPool.getBoundary().setHeight(
				clientPoolSetting.getBoundary().getHeight());
		updateSegments(clientPoolSetting.getSegments());
		clientPool.setScale(clientPoolSetting.getScale());
	}

	private void updateSegments(List<Segment> clientSegments) {
		List<Segment> segments = clientPool.getSegments();
		segments.clear();
		for (Segment clientSegment : clientSegments) {
			Segment segment = new Segment(clientSegment.getSegmentDirection(),
					clientSegment.getBeginPoint(), clientSegment
							.getEndPoint());
			segments.add(segment);
		}
	}

	public List<IFish> getFishes() {
		return clientPool.getFishes();
	}

	public void addFish(IFish fish) {
		clientPool.removeFishById(fish.getFishId());
		clientPool.addFish(fish);
	}

	public void removeFish(String fishId) {
		clientPool.removeFishById(fishId);
	}
}
