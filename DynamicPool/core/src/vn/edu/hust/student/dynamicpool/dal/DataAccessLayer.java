package vn.edu.hust.student.dynamicpool.dal;

import com.eposi.eventdriven.implementors.BaseEventDispatcher;

import vn.edu.hust.student.dynamicpool.bll.BusinessLogicDataCallback;
import vn.edu.hust.student.dynamicpool.bll.Fish;
import vn.edu.hust.student.dynamicpool.bll.FishManager;
import vn.edu.hust.student.dynamicpool.model.DeviceInfo;

public abstract class DataAccessLayer extends BaseEventDispatcher  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 776252984297076409L;

	abstract String getClientName();
	
	abstract void joinHost(int key, BusinessLogicDataCallback callback);

	abstract void createHost(BusinessLogicDataCallback callback);
	
	// them thiet bi va dua ket qua tra ve cua server la mot mang danh sach cac Segment cua be
	abstract void addDevice(DeviceInfo deviceInfo,BusinessLogicDataCallback callback);
	
	abstract void exit(BusinessLogicDataCallback callback);

	abstract void createFish(Fish fish,BusinessLogicDataCallback callback);
	
	abstract void synchronization(BusinessLogicDataCallback callback);
	
	// gui thong tin ca nen server  khi chuan bi ra khoi be
	abstract void removeFish(Fish fish,BusinessLogicDataCallback callback);
	
	abstract void synchronous(FishManager fishManager, String clientName);
}
