package vn.edu.hust.student.dynamicpool.dal;

import vn.edu.hust.student.dynamicpool.bll.BusinessLogicDataCallback;
import vn.edu.hust.student.dynamicpool.bll.Fish;
import vn.edu.hust.student.dynamicpool.dal.adapter.ModelAdapter;
import vn.edu.hust.student.dynamicpool.dal.client.entity.Client;
import vn.edu.hust.student.dynamicpool.dal.controller.HostMainController;
import vn.edu.hust.student.dynamicpool.dal.server.logic.Pool;
import vn.edu.hust.student.dynamicpool.exception.NetworkException;
import vn.edu.hust.student.dynamicpool.model.DeviceInfo;

public class HostDataAccessLayerImpl implements DataAccessLayer {

	@Override
	public String getClientName() {
		return "host";
	}

	@Override
	public void joinHost(int key, BusinessLogicDataCallback callback) {
		callback.callback(false, new Exception(
				"host instance cannot join to another host"));
	}

	@Override
	public void createHost(BusinessLogicDataCallback callback) {
		try {
			String key = HostMainController.getInstance().connectServer();
			HostMainController.getInstance().start();
			callback.callback(key, null);
		} catch (NetworkException e) {
			callback.callback(null, e);
		}
	}

	@Override
	public void addDevide(DeviceInfo devideInfor,
			BusinessLogicDataCallback callback) {
		Client client = new Client();
		client.setClientName(this.getClientName());
		client.setPool(new Pool(ModelAdapter.convert(devideInfor)));
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

}
