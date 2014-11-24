package vn.edu.hust.student.dynamicpool.bll;

import vn.edu.hust.student.dynamicpool.model.FishState;
import vn.edu.hust.student.dynamicpool.model.Rectangle;

public interface IFish {
	int getFishId();
	Rectangle getPosition();
	Rectangle update(float deltaTime);	
	FishState getFishState();
	void setFishState(FishState fishState);
	void setAngle(float angle);
	float getAngle();
	FishType getFishType();
}