package vn.edu.hust.student.dynamicpool.bll.model;

import vn.edu.hust.student.dynamicpool.equation.vector.Vector;

public abstract class Trajectory {

	protected float timeState = 0f;
	protected Boundary fishPosition;

	public Trajectory(Boundary fishPosition) {
		this.setFishPosition(fishPosition);
	}

	public Trajectory() {
		this.fishPosition = new Boundary();
	}

	public Boundary getFishPosition() {
		return fishPosition;
	}

	private void setFishPosition(Boundary fishPosition) {
		this.fishPosition = fishPosition;
	}

	public float getTimeState() {
		return timeState;
	}

	public void increaseTimeState(float deltaTime) {
		this.timeState += deltaTime;
	}

	public abstract void setDirection(Vector vector);

	public abstract ETrajectoryType getTrajectoryType();

	public abstract Boundary updateLocation(float deltaTime);
}
