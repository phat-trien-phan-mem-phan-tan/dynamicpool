package vn.edu.hust.student.dynamicpool.dal;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import vn.edu.hust.student.dynamicpool.bll.BusinessLogicDataCallback;
import vn.edu.hust.student.dynamicpool.bll.Fish;
import vn.edu.hust.student.dynamicpool.dal.controller.ClientMainController;
import vn.edu.hust.student.dynamicpool.dal.statics.Field;
import vn.edu.hust.student.dynamicpool.exception.NetworkException;
import vn.edu.hust.student.dynamicpool.model.DeviceInfo;

public class ClientDataAccessLayerImpl implements DataAccessLayer {
	private String clientName;

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public ClientDataAccessLayerImpl() {
		setClientName(UUID.randomUUID().toString());
	}

	@Override
	public void joinHost(int key, BusinessLogicDataCallback callback) {
		try {
			ClientMainController.getInstance().start(key);
			callback.callback(true, null);
		} catch (NetworkException e) {
			callback.callback(false, e);
		}
	}

	@Override
	public void createHost(BusinessLogicDataCallback callback) {
		try {
			int created = ClientMainController.getInstance().createHost();
			callback.callback(created, null);
		} catch (NetworkException e) {
			callback.callback(0, e);
		}
	}

	@Override
	public void addDevide(DeviceInfo devideInfor,
			BusinessLogicDataCallback callback) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put(Field.COMMAND, "dp_addDevice");
		data.put("width", devideInfor.getScreenWidth());
		data.put("height", devideInfor.getScreenHeight());
		data.put("size", devideInfor.getScreenSize());
		data.put("clientName", getClientName());
		
	}

	@Override
	public void exit(BusinessLogicDataCallback callback) {

	}

	@Override
	public void createFish(Fish fish, BusinessLogicDataCallback callback) {

	}

	// ong viet giup toi cai event lam sao ma cu 1s thi no tu dong gui ket qua
	// nen
	/*
	 * srever va nhan ket qua tu server duoi dang 1 list danh sach cac diem cua
	 * ca trong be
	 */
	@Override
	public void synchronization(BusinessLogicDataCallback callback) {

	}

	@Override
	public void removeFish(Fish fish, BusinessLogicDataCallback callback) {
		// TODO Auto-generated method stub

	}
}
