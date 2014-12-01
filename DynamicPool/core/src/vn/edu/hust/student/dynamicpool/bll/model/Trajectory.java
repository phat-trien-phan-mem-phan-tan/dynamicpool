package vn.edu.hust.student.dynamicpool.bll.model;


public abstract class Trajectory {
	private double timeState = 0f;
	protected Boundary fishBoundary;

	public Trajectory(Boundary fishPosition) {
		this.setFishPosition(fishPosition);
	}

	public Trajectory() {
		this.fishBoundary = new Boundary();
	}

	public Boundary getFishBoundary() {
		return fishBoundary;
	}

	private void setFishPosition(Boundary fishBoundary) {
		this.fishBoundary = fishBoundary;
	}

	public double getTimeState() {
		return timeState;
	}

	public void increaseTimeState(double d) {
		this.timeState += d;
	}
	
	public void setTimeState(double timeState) {
		this.timeState = timeState;
	}

	public abstract void changeDirection(EDirection hitTo);

	public abstract ETrajectoryType getTrajectoryType();

	public abstract Point updateLocation(float deltaTime);

	public abstract EDirection getHorizontalDirection();
	
	public abstract Trajectory clone();
}
