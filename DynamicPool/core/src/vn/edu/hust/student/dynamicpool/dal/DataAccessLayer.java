package vn.edu.hust.student.dynamicpool.dal;

import java.util.List;
import vn.edu.hust.student.dynamicpool.bll.model.*;
import vn.edu.hust.student.dynamicpool.exception.DALException;

public interface DataAccessLayer {
	String getClientName();

	void createHost() throws DALException;

	void joinHost(String key) throws DALException;

	void addDevice(DeviceInfo deviceInfo);

	void requestCreateFish(IFish fish);
	
	void synchronization();

	void removeFish(String clientName, Fish fish);

	void synchronous(List<IFish> fishes, String clientName);

	void exit();

	void respondCreateFishRequest(String clientName, boolean isSuccess,
			IFish fish) throws DALException;

	void updateSettingToClient(String clientName, Pool pool) throws DALException;
}
