package vn.edu.hust.student.dynamicpool.bll;

public class CycleTrajectory extends Trajectory {

	public CycleTrajectory(IFishPosition fishPosition) {
		super(fishPosition);
	}

	@Override
	public ETrajectoryType getTrajectoryType() {
		// TODO Auto-generated method stub
		return ETrajectoryType.CYCLE;
	}

	@Override
	public IFishPosition updateCoordinate(float deltaTime) {
		// TODO Auto-generated method stub
		return getFishPosition();
	}

}
