package vn.edu.hust.student.dynamicpool.bll.model;

import java.util.Random;

public class Fish implements IFish {
	private static Random random = new Random();  
	private int fishId = random.nextInt();
	private FishType fishType = FishType.FISH1;
	private Trajectory trajectory = new NoneTrajectory();
	private Boundary boundary = new Boundary();
	private FishState fishState = FishState.INSIDE;
	private boolean isIgnoreUpdateLocation = false;

	public Fish(FishType fishType, Trajectory fishTrajectory,
			Boundary fishBoundary) {
		this.fishType = fishType;
		this.trajectory = fishTrajectory;
		this.boundary = fishBoundary;
	}

	@Override
	public int getFishId() {
		return fishId;
	}

	@Override
	public void setFishId(int fishId) {
		this.fishId = fishId;
	}

	@Override
	public Boundary getBoundary() {
		return boundary;
	}

	@Override
	public void setBoundary(Boundary boundary) {
		this.boundary = boundary;
	}

	@Override
	public Trajectory getTrajectory() {
		return trajectory;
	}

	@Override
	public void setTrajectory(Trajectory trajectory) {
		this.trajectory = trajectory;
	}

	@Override
	public FishType getFishType() {
		return fishType;
	}

	@Override
	public FishState getFishState() {
		return fishState;
	}

	@Override
	public void setFishState(FishState fishState) {
		this.fishState = fishState;
	}

	@Override
	public void updateLocation(float deltaTime) {
		if (!isIgnoreUpdateLocation)
			trajectory.updateLocation(deltaTime);
	}

	@Override
	public boolean isIgnoreUpdateLocation() {
		return isIgnoreUpdateLocation;
	}

	@Override
	public void ignoreUpdateLocation() {
		isIgnoreUpdateLocation = true;
	}

	@Override
	public IFish cloneIgnoreFishState() {
		IFish fish = new Fish(fishType, trajectory, boundary);
		fish.setFishId(fishId);
		fish.setFishState(fishState);
		fish.ignoreUpdateLocation();
		return fish;
	}

	@Override
	public IFish cloneFish() {
		IFish fish = new Fish(fishType, trajectory, boundary);
		fish.setFishId(fishId);
		fish.setFishState(fishState);
		fish.ignoreUpdateLocation();
		return fish;
	}
}
