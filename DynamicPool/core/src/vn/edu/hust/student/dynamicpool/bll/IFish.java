package vn.edu.hust.student.dynamicpool.bll;

public interface IFish {
	IFishPosition getPoint();
	IFishPosition update(float deltaTime);
}