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
	public Point updateLocation(float deltaTime) {
		increaseTimeState(deltaTime);
		return new Point();
	}

	@Override
	public void changeDirection(EDirection direction) {
		
	}

	@Override
	public EDirection getHorizontalDirection() {
		return EDirection.RIGHT;
	}

	@Override
	public Trajectory clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
