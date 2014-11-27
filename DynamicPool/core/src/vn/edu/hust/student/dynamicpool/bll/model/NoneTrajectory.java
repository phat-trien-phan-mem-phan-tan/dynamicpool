package vn.edu.hust.student.dynamicpool.bll.model;

import vn.edu.hust.student.dynamicpool.equation.vector.Vector;

public class NoneTrajectory extends Trajectory {

	public NoneTrajectory() {
		super();
	}

	@Override
	public ETrajectoryType getTrajectoryType() {
		return ETrajectoryType.NONE;
	}

	@Override
	public Boundary updateCoordinate(float deltaTime) {
		return getFishPosition();
	}

	@Override
	public void setDirection(Vector vector) {
		// TODO Auto-generated method stub
		
	}

}
