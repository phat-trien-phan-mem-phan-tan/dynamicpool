package vn.edu.hust.student.dynamicpool.bll.model;


public class TrajectoryFactory {
	public static LineTrajectory createLineTrajectory(Boundary fishPosition) {
		return new LineTrajectory(fishPosition);
	}
	
	public static SinTrajectory createSinTrajectory(Boundary fishPosition) {
		return new SinTrajectory(fishPosition);
	}
	
	public static CycleTrajectory createCycleTrajectory(Boundary fishPosition) {
		return new CycleTrajectory(fishPosition);
	}
}
