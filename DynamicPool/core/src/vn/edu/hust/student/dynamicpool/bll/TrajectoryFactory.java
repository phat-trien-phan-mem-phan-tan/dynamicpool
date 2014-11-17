package vn.edu.hust.student.dynamicpool.bll;

public class TrajectoryFactory {
	public static LineTrajectory createLineTrajectory(IFishPosition fishPosition) {
		return new LineTrajectory(fishPosition);
	}
	
	public static SinTrajectory createSinTrajectory(IFishPosition fishPosition) {
		return new SinTrajectory(fishPosition);
	}
	
	public static CycleTrajectory createCycleTrajectory(IFishPosition fishPosition) {
		return new CycleTrajectory(fishPosition);
	}
}
