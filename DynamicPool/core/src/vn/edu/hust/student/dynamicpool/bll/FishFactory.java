package vn.edu.hust.student.dynamicpool.bll;

import com.sun.org.apache.regexp.internal.recompile;

public class FishFactory {
	public static Fish createFishWithLineTrajectory(float screenWidth, float screenHeight) {
		// center of screen
		IFishPosition position = new FishPosition(screenWidth/2, screenHeight/2);
		Trajectory trajectory = new LineTrajectory(position);
		Fish fish = new Fish(position, trajectory);
		return fish;
	}
	
	public static Fish createFishWithSinTrajectory(float screenWidth, float screenHeight) {
		// center of screen
		IFishPosition position = new FishPosition(screenWidth/2, screenHeight/2);
		Trajectory trajectory = new SinTrajectory(position);
		Fish fish = new Fish(position, trajectory);
		return fish;
	}
	
	public static Fish createFishWithCycleTrajectory(float screenWidth, float screenHeight) {
		// center of screen
		IFishPosition position = new FishPosition(screenWidth/2, screenHeight/2);
		Trajectory trajectory = new CycleTrajectory(position);
		Fish fish = new Fish(position, trajectory);
		return fish;
	}
}
