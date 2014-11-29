package vn.edu.hust.student.dynamicpool.bll.model;

import java.util.Random;

import vn.edu.hust.student.dynamicpool.presentation.gameobject.EDirection;
import vn.edu.hust.student.dynamicpool.utils.AppConst;

public class CycleTrajectory extends Trajectory {

	private float centerX;
	private float centerY;

	private float a = 80;
	private float d = 0.2f;

	public CycleTrajectory(Boundary fishBoundary) {
		super(fishBoundary);
		this.centerX = fishBoundary.getLocation().getX();
		int randomInt = Math.abs(new Random().nextInt() % AppConst.height);
		this.centerY = fishBoundary.getLocation().getY() + randomInt;
		increaseTimeState(-Math.PI / 2);
		a = Math.abs(this.centerY - fishBoundary.getLocation().getY());
	}

	@Override
	public ETrajectoryType getTrajectoryType() {
		return ETrajectoryType.CYCLE;
	}

	@Override
	public void updateLocation(float deltaTime) {
		increaseTimeState(deltaTime * d);
		float x = (float) (centerX + a * Math.cos(getTimeState()));
		float y = (float) (centerY + a * Math.sin(getTimeState()));
		fishBoundary.setLocation(new Point(x, y));
	}

	@Override
	public void changeDirection(EDirection direction) {
		switch (direction) {
		case LEFT:
		case RIGHT:
			centerY = 2 * getFishBoundary().getLocation().getY() - centerY;
			setTimeState(-getTimeState());
			break;
		case TOP:
		case BOTTOM:
			centerX = 2 * getFishBoundary().getLocation().getX() - centerX;
			setTimeState(Math.PI - getTimeState());
			break;
		default:
			break;
		}
	}

	@Override
	public EDirection getHorizontalDirection() {
		return Math.sin(getTimeState()) > 0 ? EDirection.LEFT
				: EDirection.RIGHT;
	}

}
