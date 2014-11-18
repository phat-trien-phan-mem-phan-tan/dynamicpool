package vn.edu.hust.student.dynamicpool.dal;

import vn.edu.hust.student.dynamicpool.bll.BusinessLogicDataCallback;
import vn.edu.hust.student.dynamicpool.bll.ETrajectoryType;
import vn.edu.hust.student.dynamicpool.bll.FishType;
import vn.edu.hust.student.dynamicpool.model.DevideInfor;

public interface DataAccessLayer {
	
	void joinHost(int key, BusinessLogicDataCallback callback);

	void createHost(BusinessLogicDataCallback callback);
	
	void intialDevide(DevideInfor devideInfor,BusinessLogicDataCallback callback);
	
	void addDevide(DevideInfor devideInfor,BusinessLogicDataCallback callback);
	
	void exit();

	void createFish(FishType fishType, ETrajectoryType trajectoryType, int width, int height);
	
	
}
