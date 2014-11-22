package vn.edu.hust.student.dynamicpool.bll;

import vn.edu.hust.student.dynamicpool.model.Rectangle;

public class TrajectoryFactory {
	public static LineTrajectory createLineTrajectory(Rectangle fishPosition) {
		return new LineTrajectory(fishPosition);
	}
	
	public static SinTrajectory createSinTrajectory(Rectangle fishPosition) {
		return new SinTrajectory(fishPosition);
	}
	
	public static CycleTrajectory createCycleTrajectory(Rectangle fishPosition) {
		return new CycleTrajectory(fishPosition);
	}
}
