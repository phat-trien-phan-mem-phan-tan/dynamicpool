package vn.edu.hust.student.dynamicpool.bll;

import vn.edu.hust.student.dynamicpool.dal.utils.AppConst;
import vn.edu.hust.student.dynamicpool.equation.vector.Vector;
import vn.edu.hust.student.dynamicpool.model.Point;
import vn.edu.hust.student.dynamicpool.model.Rectangle;

public class FishFactory {
	public static Fish createFishWithLineTrajectory(float fishWidth,
			float fishHeight, FishType fishType) {
		// center of screen
		final Fish newFish = new Fish();
		Rectangle fishPosition = newFish.getPosition();
		fishPosition.setLocation(new Point(AppConst.width / 2,
				AppConst.height / 2));
		fishPosition.setWidth(fishWidth);
		fishPosition.setHeight(fishHeight);
		newFish.setTrajectoryType(ETrajectoryType.LINE);
		newFish.setFishType(fishType);

		// check trajectory type

		newFish.setAngle((float) (Math.PI / 4));

		LineTrajectory lineTrajectory = new LineTrajectory(fishPosition);
		lineTrajectory.setDirection(new Vector(1, 1));

		newFish.setTrajectory(lineTrajectory);

		return newFish;
	}

	public static Fish createFishWithSinTrajectory(float fishWidth,
			float fishHeight, FishType fishType){
		// center of screen
		final Fish newFish = new Fish();
		Rectangle fishPosition = newFish.getPosition();
		fishPosition.setLocation(new Point(AppConst.width / 2,
				AppConst.height / 2));
		fishPosition.setWidth(fishWidth);
		fishPosition.setHeight(fishHeight);
		newFish.setTrajectoryType(ETrajectoryType.SIN);
		
		newFish.setFishType(fishType);

		
		SinTrajectory sinTrajectory = new SinTrajectory(fishPosition);

		sinTrajectory.setX0(AppConst.width / 2);
		sinTrajectory.setY0(AppConst.height / 2);

		newFish.setTrajectory(sinTrajectory);

		return newFish;
	}

	
	public static Fish createFishWithCycleTrajectory(float fishWidth,
			float fishHeight, FishType fishType){
		// center of screen
		
		final Fish newFish = new Fish();
		Rectangle fishPosition = newFish.getPosition();
		fishPosition.setLocation(new Point(AppConst.width / 2,
				AppConst.height / 2));
		fishPosition.setWidth(fishWidth);
		fishPosition.setHeight(fishHeight);
		newFish.setTrajectoryType(ETrajectoryType.CYCLE);
		newFish.setFishType(fishType);

		newFish.setAngle(0);

		CycleTrajectory cycleTrajectory = new CycleTrajectory(fishPosition);

		cycleTrajectory.setX0(AppConst.width/2);
		cycleTrajectory.setY0(AppConst.height/2);
		
		newFish.setTrajectory(cycleTrajectory);
		// set location for fish
		Point newPoint = cycleTrajectory.updateCoordinate(0).getLocation();
		fishPosition.setLocation(newPoint);
		
		return newFish;
	}
}
