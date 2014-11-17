package vn.edu.hust.student.dynamicpool.bll;

public abstract class Trajectory {
	private float timeState = 0f;
	private IFishPosition fishPosition;

	public Trajectory(IFishPosition fishPosition) {
		this.setFishPosition(fishPosition);
	}
	
	public IFishPosition getFishPosition() {
		return fishPosition;
	}

	private void setFishPosition(IFishPosition fishPosition) {
		this.fishPosition = fishPosition;
	}

	public float getTimeState() {
		return timeState;
	}

	public void increaseTimeState(float deltaTime) {
		this.timeState += deltaTime;
	}
	
	public abstract ETrajectoryType getTrajectoryType();
	public abstract IFishPosition updateCoordinate(float deltaTime);
}
