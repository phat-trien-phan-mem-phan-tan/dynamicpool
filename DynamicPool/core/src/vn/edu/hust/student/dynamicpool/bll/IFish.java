package vn.edu.hust.student.dynamicpool.bll;

public interface IFish {
	int getFishId();
	IFishPosition getPoint();
	IFishPosition update(float deltaTime);
}