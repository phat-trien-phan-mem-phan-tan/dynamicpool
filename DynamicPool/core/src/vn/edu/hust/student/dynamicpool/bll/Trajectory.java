package vn.edu.hust.student.dynamicpool.bll;

import vn.edu.hust.student.dynamicpool.equation.vector.Vector;
import vn.edu.hust.student.dynamicpool.model.Rectangle;

public abstract class Trajectory {
	
	protected float timeState = 0f;
	protected Rectangle fishPosition;

	public Trajectory(Rectangle fishPosition) {
		this.setFishPosition(fishPosition);
	}
	
	public Trajectory() {
		this.fishPosition = new Rectangle();
	}

	public Rectangle getFishPosition() {
		return fishPosition;
	}

	private void setFishPosition(Rectangle fishPosition) {
		this.fishPosition = fishPosition;
	}

	public float getTimeState() {
		return timeState;
	}

	public void increaseTimeState(float deltaTime) {
		this.timeState += deltaTime;
	}
	
	public abstract void  setDirection(Vector vector);
	
	public abstract ETrajectoryType getTrajectoryType();
	public abstract Rectangle updateCoordinate(float deltaTime);
}
