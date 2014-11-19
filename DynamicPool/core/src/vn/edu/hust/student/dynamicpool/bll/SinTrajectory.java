package vn.edu.hust.student.dynamicpool.bll;

import vn.edu.hust.student.dynamicpool.equation.vector.Vector;

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

	@Override
	public void setDirection(Vector vector) {
		// TODO Auto-generated method stub
		
	}

}
