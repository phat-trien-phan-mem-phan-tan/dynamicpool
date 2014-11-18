package vn.edu.hust.student.dynamicpool.bll;

import vn.edu.hust.student.dynamicpool.model.FishState;

public interface IFish {
	int getFishId();
	IFishPosition getPoint();
	IFishPosition update(float deltaTime);
	
	void setDx(float dx);
	float getDx();
	
	void setDy(float dy);
	float getDy();
	
	FishState getFishState();
	void setFishState(FishState fishState);
	
	
}