package vn.edu.hust.student.dynamicpool.bll.model;

import vn.edu.hust.student.dynamicpool.presentation.gameobject.EDirection;

public class SinTrajectory extends Trajectory {
	// x = x+dx
	// y = y0 + a * sin(t)

	private int a = 100;
	private int dx = 15;
	private float y0 = 0;

	public SinTrajectory(Boundary fishPosition) {
		super(fishPosition);
		y0 = fishPosition.getLocation().getY();
	}

	@Override
	public ETrajectoryType getTrajectoryType() {
		return ETrajectoryType.SIN;
	}

	@Override
	public void updateLocation(float deltaTime) {
		increaseTimeState(deltaTime);
		float x = getFishBoundary().getLocation().getX() + dx * deltaTime;
		float y = (float) (y0 + a * Math.sin(getTimeState()));
		fishBoundary.setLocation(new Point(x, y));
	}

	@Override
	public void changeDirection(EDirection direction) {
		switch (direction) {
		case LEFT:
		case RIGHT:
			dx = -dx;
			y0 = 2 * getFishBoundary().getLocation().getY() - y0;
			setTimeState(-getTimeState());
			break;
		case TOP:
		case BOTTOM:
		default:
			increaseTimeState(Math.PI);
			break;
		}
	}

	@Override
	public EDirection getHorizontalDirection() {
		return dx < 0 ? EDirection.LEFT : EDirection.RIGHT;
	}
}
