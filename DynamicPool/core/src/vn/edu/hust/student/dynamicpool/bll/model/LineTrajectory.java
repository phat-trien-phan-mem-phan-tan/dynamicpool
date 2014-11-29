package vn.edu.hust.student.dynamicpool.bll.model;

import vn.edu.hust.student.dynamicpool.presentation.gameobject.EDirection;

public class LineTrajectory extends Trajectory {
	private float dx = 1, dy = 1;
	private static final int A = 20;

	public LineTrajectory(Boundary fishBoundary) {
		super(fishBoundary);
	}

	@Override
	public void changeDirection(EDirection hitTo) {
		switch (hitTo) {
		case TOP:
		case BOTTOM:
			dy = -dy;
			break;
		case LEFT:
		case RIGHT:
		default:
			dx = -dx;
			break;
		}
	}

	@Override
	public ETrajectoryType getTrajectoryType() {
		return ETrajectoryType.LINE;
	}

	@Override
	public void updateLocation(float deltaTime) {
		increaseTimeState(deltaTime);
		float x = (float) (fishBoundary.getMinX() + A * dx * deltaTime);
		float y = (float) (fishBoundary.getMinY() + A * dy * deltaTime);
		fishBoundary.setLocation(new Point(x, y));
	}

	@Override
	public EDirection getHorizontalDirection() {
		return dx < 0 ? EDirection.LEFT : EDirection.RIGHT;
	}
}
