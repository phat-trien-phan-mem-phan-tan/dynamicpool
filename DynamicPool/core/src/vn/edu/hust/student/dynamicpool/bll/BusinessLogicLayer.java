package vn.edu.hust.student.dynamicpool.bll;

import java.util.List;

import vn.edu.hust.student.dynamicpool.bll.model.DeviceInfo;
import vn.edu.hust.student.dynamicpool.bll.model.ETrajectoryType;
import vn.edu.hust.student.dynamicpool.bll.model.Fish;
import vn.edu.hust.student.dynamicpool.bll.model.FishType;
import vn.edu.hust.student.dynamicpool.bll.model.IFish;

public interface BusinessLogicLayer {
	
	void joinHost(String key);

	void createHost();
	
	void addDevide(DeviceInfo devideInfor);
	
	List<IFish> getFishes();

	void update(float deltaTime);

	void exit();

	void createFish(FishType fishType, ETrajectoryType trajectoryType, int width, int height);
	
	// ham dong cap nhat ket qua voi server
	void synchronization();

	String getKeyOfHost();

	void removeFish(Fish fish);
	
	
	
	
}
