package vn.edu.hust.student.dynamicpool.dal;

import vn.edu.hust.student.dynamicpool.bll.BusinessLogicDataCallback;
import vn.edu.hust.student.dynamicpool.bll.Fish;
import vn.edu.hust.student.dynamicpool.model.DeviceInfo;

public interface DataAccessLayer {
	
	void joinHost(int key, BusinessLogicDataCallback callback);

	void createHost(BusinessLogicDataCallback callback);
	
	void addDevice(DeviceInfo devideInfor,BusinessLogicDataCallback callback);
	
	void exit(BusinessLogicDataCallback callback);

	void createFish(Fish fish,BusinessLogicDataCallback callback);
	
	void synchronization(BusinessLogicDataCallback callback);
	
	// gui thong tin ca nen server  khi chuan bi ra khoi be
	void removeFish(Fish fish,BusinessLogicDataCallback callback);
}
