package vn.edu.hust.student.dynamicpool.bll;

import java.util.List;
import java.util.UUID;

import vn.edu.hust.student.dynamicpool.model.DeviceInfo;
import vn.edu.hust.student.dynamicpool.presentation.PresentationBooleanCallback;

public interface BusinessLogicLayer {
	
	void joinHost(String key, PresentationBooleanCallback callback);

	void createHost(PresentationBooleanCallback callback);
	
	void addDevide(DeviceInfo devideInfor,PresentationBooleanCallback callback);
	
	List<IFish> getFishs();

	void update(float deltaTime);

	void exit();

	void createFish(FishType fishType, ETrajectoryType trajectoryType, int width, int height);
	
	// ham dong cap nhat ket qua voi server
	void synchronization();
	
	
	
	
}
