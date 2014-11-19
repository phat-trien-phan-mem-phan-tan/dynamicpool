package vn.edu.hust.student.dynamicpool.bll;

import vn.edu.hust.student.dynamicpool.equation.vector.Vector;

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

	@Override
	public void setDirection(Vector vector) {
		// TODO Auto-generated method stub
		
	}

}
