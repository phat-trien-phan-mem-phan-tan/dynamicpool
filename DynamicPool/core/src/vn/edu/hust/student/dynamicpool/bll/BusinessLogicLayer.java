package vn.edu.hust.student.dynamicpool.bll;

import java.util.List;

import vn.edu.hust.student.dynamicpool.model.DeviceInfo;
import vn.edu.hust.student.dynamicpool.presentation.PresentationBooleanCallback;

public interface BusinessLogicLayer {

	void joinHost(String key, PresentationBooleanCallback callback);

	void createHost(PresentationBooleanCallback callback);
	
	
	void intialDevide(DeviceInfo devideInfor,PresentationBooleanCallback callback);
	
	void addDevide(DeviceInfo devideInfor,PresentationBooleanCallback callback);
	
	List<IFish> getFishs();

	void update(float deltaTime);

	void exit();

	void createFish(FishType fishType, ETrajectoryType trajectoryType, int width, int height);
}
