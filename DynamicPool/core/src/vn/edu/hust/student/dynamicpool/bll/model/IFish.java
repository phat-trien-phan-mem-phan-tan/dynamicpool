package vn.edu.hust.student.dynamicpool.bll.model;

public interface IFish {
	int getFishId();

	void setFishId(int fishId);

	Boundary getBoundary();

	void setBoundary(Boundary boundary);

	Trajectory getTrajectory();

	void setTrajectory(Trajectory trajectory);

	FishType getFishType();

	FishState getFishState();

	void setFishState(FishState fishState);

	void updateLocation(float deltaTime);

	boolean isIgnoreUpdateLocation();

	IFish cloneIgnoreFishState();

	void ignoreUpdateLocation();

	IFish cloneFish();
}