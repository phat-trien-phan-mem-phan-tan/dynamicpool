package vn.edu.hust.student.dynamicpool.bll;

public interface IFishPosition {
	float getX();
	float getY();
	float getAngle();
	float increaseX(float dx);
	float increaseY(float dy);
	void setAngle(float angle);
}