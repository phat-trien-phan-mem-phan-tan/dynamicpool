package vn.edu.hust.student.dynamicpool.bll.model;

public class Fish implements IFish {
	private int fishId;
	private Boundary boundary = new Boundary();
	private Trajectory trajectory = new NoneTrajectory();
	private FishType fishType = FishType.FISH1;
	private FishState fishState;
	private float angle;

	public Fish(FishType fishType, Trajectory fishTrajectory, Boundary fishBoundary) {
		this.fishType = fishType;
		this.trajectory = fishTrajectory;
		this.boundary = fishBoundary;
	}

	@Override
	public int getFishId() {
		return fishId;
	}

	@Override
	public void setFishId(int fishId) {
		this.fishId = fishId;
	}

	@Override
	public Boundary getBoundary() {
		return boundary;
	}

	@Override
	public void setBoundary(Boundary boundary) {
		this.boundary = boundary;
	}

	@Override
	public Trajectory getTrajectory() {
		return trajectory;
	}

	@Override
	public void setTrajectory(Trajectory trajectory) {
		this.trajectory = trajectory;
	}

	@Override
	public FishType getFishType() {
		return fishType;
	}

	@Override
	public FishState getFishState() {
		return fishState;
	}

	@Override
	public void setFishState(FishState fishState) {
		this.fishState = fishState;
	}

	@Override
	public float getAngle() {
		return angle;
	}

	@Override
	public void setAngle(float angle) {
		this.angle = angle;
	}

	@Override
	public void updateLocation(float deltaTime) {
		trajectory.updateLocation(deltaTime);
	}
}
