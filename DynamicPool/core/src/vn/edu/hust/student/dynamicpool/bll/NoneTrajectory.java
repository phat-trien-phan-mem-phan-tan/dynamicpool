package vn.edu.hust.student.dynamicpool.bll;

import vn.edu.hust.student.dynamicpool.equation.vector.Vector;
import vn.edu.hust.student.dynamicpool.model.Rectangle;

public class NoneTrajectory extends Trajectory {

	public NoneTrajectory() {
		super();
	}

	@Override
	public ETrajectoryType getTrajectoryType() {
		return ETrajectoryType.NONE;
	}

	@Override
	public Rectangle updateCoordinate(float deltaTime) {
		return getFishPosition();
	}

	@Override
	public void setDirection(Vector vector) {
		// TODO Auto-generated method stub
		
	}

}
