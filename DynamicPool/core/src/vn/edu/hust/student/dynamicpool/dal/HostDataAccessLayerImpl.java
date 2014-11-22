package vn.edu.hust.student.dynamicpool.dal;

import java.util.HashMap;
import java.util.Map;

import vn.edu.hust.student.dynamicpool.bll.BusinessLogicDataCallback;
import vn.edu.hust.student.dynamicpool.bll.Fish;
import vn.edu.hust.student.dynamicpool.bll.FishManager;
import vn.edu.hust.student.dynamicpool.dal.client.entity.Client;
import vn.edu.hust.student.dynamicpool.dal.controller.HostMainController;
import vn.edu.hust.student.dynamicpool.dal.server.logic.PoolServer;
import vn.edu.hust.student.dynamicpool.dal.statics.Field;
import vn.edu.hust.student.dynamicpool.exception.DALException;
import vn.edu.hust.student.dynamicpool.model.DeviceInfo;

public class HostDataAccessLayerImpl extends DataAccessLayer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6710175512739230910L;

	@Override
	public String getClientName() {
		return "host";
	}

	@Override
	public void joinHost(int key, BusinessLogicDataCallback callback) {
		callback.callback(false, new DALException(
				"host instance cannot join to another host", null));
	}

	@Override
	public void createHost(BusinessLogicDataCallback callback) {
		try {
			String key = HostMainController.getInstance().connectServer();
			HostMainController.getInstance().start();
			callback.callback(key, null);
		} catch (DALException e) {
			callback.callback(null, e);
		}
	}

	@Override
	public void addDevice(DeviceInfo deviceInfo,
			BusinessLogicDataCallback callback) {
		Client client = new Client();
		client.setClientName(this.getClientName());
		PoolServer poolServer = new PoolServer(getClientName(), deviceInfo);
		client.setPool(poolServer);
		HostMainController.getInstance().getClientManager().addClient(client);
		callback.callback(null, null);
	}

	@Override
	public void exit(BusinessLogicDataCallback callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createFish(Fish fish, BusinessLogicDataCallback callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public void synchronization(BusinessLogicDataCallback callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeFish(Fish fish, BusinessLogicDataCallback callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public void synchronous(FishManager fishManager, String clientName) {
		Client client = HostMainController.getInstance().getClientManager()
				.getClient(clientName);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put(Field.COMMAND, "synchoronous");
		data.put("fishManager", fishManager);
		if (client != null) {
			client.send(data);
		}
	}

	@Override
	public void registerEvent(Object target) {
		// TODO Auto-generated method stub
		
	}
}
