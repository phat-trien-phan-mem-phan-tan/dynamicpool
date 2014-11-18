package vn.edu.hust.student.dynamicpool.bll;

import java.util.List;

import vn.edu.hust.student.dynamicpool.model.DevideInfor;
import vn.edu.hust.student.dynamicpool.presentation.PresentationBooleanCallback;

public interface BusinessLogicLayer {

	void joinHost(String key, PresentationBooleanCallback callback);

	void createHost(PresentationBooleanCallback callback);
	
	
	void intialDevide(DevideInfor devideInfor,PresentationBooleanCallback callback);
	
	void addDevide(DevideInfor devideInfor,PresentationBooleanCallback callback);
	
	List<IFish> getFishs();

	void update(float deltaTime);

	void exit();

	void createFish(FishType fishType, ETrajectoryType trajectoryType, int width, int height);
}
