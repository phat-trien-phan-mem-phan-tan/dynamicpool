package vn.edu.hust.student.dynamicpool.dal;

import java.util.List;
import vn.edu.hust.student.dynamicpool.bll.model.*;

public interface DataAccessLayer {
	String getClientName();

	void createHost();

	void joinHost(String key);

	void addDevice(DeviceInfo deviceInfo);

	void requestCreateFish(IFish fish);

	void synchronization();

	void removeFish(Fish fish);

	void synchronous(List<IFish> fishes, String clientName);

	void registerDone(Pool pool);

	void exit();

	void respondCreateFishRequest(String clientName, boolean isSuccess,
			IFish fish);
}
