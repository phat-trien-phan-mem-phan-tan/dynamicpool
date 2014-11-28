package vn.edu.hust.student.dynamicpool.bll.model;

import vn.edu.hust.student.dynamicpool.presentation.gameobject.EDirection;

public class SinTrajectory extends Trajectory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myapp.equation.Equation#move(float)
	 * 
	 * x = x0+n*t; y = y0 + a*sin(t+angle)
	 */

	private int a = 10;
	private int dx = 20;

	public SinTrajectory(Boundary fishPosition) {
		super(fishPosition);
	}

	@Override
	public ETrajectoryType getTrajectoryType() {
		return ETrajectoryType.SIN;
	}

	@Override
	public void updateLocation(float deltaTime) {
		increaseTimeState(deltaTime);
		Point location = this.getFishBoundary().getLocation();
		float x = location.getX() + dx * deltaTime;
		float y = location.getY() + (float) (a * Math.cos(Math.toRadians(getTimeState())));
		fishBoundary.setLocation(new Point(x, y));
	}

	@Override
	public void changeDirection(EDirection direction) {
		switch (direction) {
		case LEFT:
		case RIGHT:
			dx = -dx;
			break;
		case TOP:
		case BOTTOM:
		default:
			increaseTimeState(Math.toRadians(180));
			break;
		}
	}

	@Override
	public EDirection getHorizontalDirection() {
		if (dx < 0)
			return EDirection.LEFT;
		return EDirection.RIGHT;
	}

}
