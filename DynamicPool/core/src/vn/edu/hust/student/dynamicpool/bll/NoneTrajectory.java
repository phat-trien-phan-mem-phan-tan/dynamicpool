package vn.edu.hust.student.dynamicpool.bll;

public class NoneTrajectory extends Trajectory {

	public NoneTrajectory() {
		super(new FishPosition());
	}

	@Override
	public ETrajectoryType getTrajectoryType() {
		return ETrajectoryType.NONE;
	}

	@Override
	public IFishPosition updateCoordinate(float deltaTime) {
		return getFishPosition();
	}

}
