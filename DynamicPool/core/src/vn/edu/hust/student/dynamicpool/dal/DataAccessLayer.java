package vn.edu.hust.student.dynamicpool.dal;

import vn.edu.hust.student.dynamicpool.bll.BusinessLogicDataCallback;
import vn.edu.hust.student.dynamicpool.bll.Fish;
import vn.edu.hust.student.dynamicpool.bll.FishManager;
import vn.edu.hust.student.dynamicpool.model.DeviceInfo;

import com.eposi.eventdriven.implementors.BaseEventDispatcher;

public abstract class DataAccessLayer extends BaseEventDispatcher  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 776252984297076409L;

	public abstract String getClientName();
	
	public abstract void joinHost(String key, BusinessLogicDataCallback callback);

	public abstract void createHost(BusinessLogicDataCallback callback);
	
	// them thiet bi va dua ket qua tra ve cua server la mot mang danh sach cac Segment cua be
	public abstract void addDevice(DeviceInfo deviceInfo,BusinessLogicDataCallback callback);
	
	public abstract void exit(BusinessLogicDataCallback callback);

	public abstract void createFish(Fish fish,BusinessLogicDataCallback callback);
	
	public abstract void synchronization(BusinessLogicDataCallback callback);
	
	// gui thong tin ca nen server  khi chuan bi ra khoi be
	public abstract void removeFish(Fish fish,BusinessLogicDataCallback callback);
	
	public abstract void synchronous(FishManager fishManager, String clientName);
	
	public abstract void registerEvent(BaseEventDispatcher target);
	
	public abstract void registerDone(boolean state);
}
