package vn.edu.hust.student.dynamicpool.bll.model;

import vn.edu.hust.student.dynamicpool.equation.vector.Vector;
import vn.edu.hust.student.dynamicpool.utils.AppConst;

public class FishFactory {
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
