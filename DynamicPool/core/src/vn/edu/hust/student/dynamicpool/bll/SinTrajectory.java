package vn.edu.hust.student.dynamicpool.bll;

public class SinTrajectory extends Trajectory {
	
	public SinTrajectory(IFishPosition fishPosition) {
		super(fishPosition);
	}

	@Override
	public ETrajectoryType getTrajectoryType() {
		return ETrajectoryType.SIN;
	}

	@Override
	public IFishPosition updateCoordinate(float deltaTime) {
		return getFishPosition();
	}

}
