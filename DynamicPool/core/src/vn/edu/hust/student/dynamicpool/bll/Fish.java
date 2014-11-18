package vn.edu.hust.student.dynamicpool.bll;

public class Fish implements IFish {
	private IFishPosition position;
	private Trajectory trajectory;
	
	public Fish(IFishPosition fishPosition, Trajectory trajectory) {
		this.position = fishPosition;
		this.trajectory = trajectory;
	}

	public Fish(IFishPosition fishPosition) {
		this(fishPosition, new NoneTrajectory());
	}
	
	public Fish() {
		this(new FishPosition(), new NoneTrajectory());
	}

	@Override
	public IFishPosition getPoint() {
		return position;
	}

	@Override
	public IFishPosition update(float deltaTime) {
		return this.trajectory.updateCoordinate(deltaTime);
	}
	
	public void setTrajectory(Trajectory trajectory) {
		this.trajectory = trajectory;
	}

	@Override
	public int getFishId() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
