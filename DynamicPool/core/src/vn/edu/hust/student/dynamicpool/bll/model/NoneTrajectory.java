package vn.edu.hust.student.dynamicpool.bll.model;


public class NoneTrajectory extends Trajectory {

	public NoneTrajectory() {
		super();
	}

	@Override
	public ETrajectoryType getTrajectoryType() {
		return ETrajectoryType.NONE;
	}

	@Override
	public void updateLocation(float deltaTime) {
		increaseTimeState(deltaTime);
	}

	@Override
	public void changeDirection(EDirection direction) {
		
	}

	@Override
	public EDirection getHorizontalDirection() {
		return EDirection.RIGHT;
	}

}
