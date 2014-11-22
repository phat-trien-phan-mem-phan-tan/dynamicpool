package vn.edu.hust.student.dynamicpool.bll;

import vn.edu.hust.student.dynamicpool.model.Point;


public interface IFishPosition {
	float getX();

	void setX(float x);

	float getY();

	void setY(float y);

	float getAngle();
	void setAngle(float angle);

	float increaseX(float dx);

	float increaseY(float dy);

	
	
	void setPosition( Point point);
}