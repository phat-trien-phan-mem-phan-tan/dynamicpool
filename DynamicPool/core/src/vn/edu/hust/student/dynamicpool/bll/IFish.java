<<<<<<< HEAD
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
=======
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
>>>>>>> 4aa3cd03695b2a746545fbff4eb38d3feeb685ee
}