package vn.edu.hust.student.dynamicpool.bll.model;

import vn.edu.hust.student.dynamicpool.equation.vector.Vector;
import vn.edu.hust.student.dynamicpool.utils.AppConst;

public class FishFactory {
	private FishFactory() {
		
	}
	public static IFish createFishWithTrajectoryType(final FishType fishType,
			final ETrajectoryType trajectoryType, final int width,
			final int height) {
		switch (trajectoryType) {
		case SIN:
			return createFishWithSinTrajectory(width, height, fishType);
		case CYCLE:
			return createFishWithCycleTrajectory(width, height, fishType);
		case LINE:
		case NONE:
		default:
			return createFishWithLineTrajectory(width, height, fishType);
		}
	}
	public static Fish createFishWithLineTrajectory(float fishWidth,
			float fishHeight, FishType fishType) {
		Point location = new Point(AppConst.width / 2, AppConst.height / 2);
		Boundary fishBoundary = new Boundary(location, fishWidth, fishHeight);

		LineTrajectory lineTrajectory = new LineTrajectory(fishBoundary);
		lineTrajectory.setDirection(new Vector(1, 1));

		return new Fish(fishType, lineTrajectory);
	}

	public static Fish createFishWithSinTrajectory(float fishWidth,
			float fishHeight, FishType fishType) {
		Point location = new Point(AppConst.width / 2, AppConst.height / 2);
		Boundary fishBoundary = new Boundary(location, fishWidth, fishHeight);

		SinTrajectory sinTrajectory = new SinTrajectory(fishBoundary);
		sinTrajectory.setX0(AppConst.width / 2);
		sinTrajectory.setY0(AppConst.height / 2);

		return new Fish(fishType, sinTrajectory);
	}

	public static Fish createFishWithCycleTrajectory(float fishWidth,
			float fishHeight, FishType fishType) {
		// center of screen

		Point location = new Point(AppConst.width / 2, AppConst.height / 2);
		Boundary fishBoundary = new Boundary(location, fishWidth, fishHeight);

		CycleTrajectory cycleTrajectory = new CycleTrajectory(fishBoundary);
		cycleTrajectory.setX0(AppConst.width / 2);
		cycleTrajectory.setY0(AppConst.height / 2);

		return new Fish(fishType, cycleTrajectory);
	}
}
