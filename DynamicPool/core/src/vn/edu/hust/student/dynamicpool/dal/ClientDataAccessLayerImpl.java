package vn.edu.hust.student.dynamicpool.dal;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.eposi.eventdriven.implementors.BaseEventDispatcher;

import vn.edu.hust.student.dynamicpool.bll.BusinessLogicDataCallback;
import vn.edu.hust.student.dynamicpool.bll.Fish;
import vn.edu.hust.student.dynamicpool.bll.FishManager;
import vn.edu.hust.student.dynamicpool.dal.controller.ClientMainController;
import vn.edu.hust.student.dynamicpool.dal.statics.Field;
import vn.edu.hust.student.dynamicpool.exception.DALException;
import vn.edu.hust.student.dynamicpool.model.DeviceInfo;

public class ClientDataAccessLayerImpl extends DataAccessLayer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7685205083926173715L;
	private String clientName;

	@Override
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
		} catch (DALException e) {
			callback.callback(false, e);
		}
	}

	@Override
	public void createHost(BusinessLogicDataCallback callback) {
		callback.callback(false, new DALException("Default client cannot create host", null));
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

	@Override
	public void addDevice(DeviceInfo deviceInfor,
			BusinessLogicDataCallback callback) {
	
		Map<String, Object> data = new HashMap<String, Object>();
		data.put(Field.COMMAND, "dp_addDevice");
		data.put("width", deviceInfor.getScreenWidth());
		data.put("height", deviceInfor.getScreenHeight());
		data.put("size", deviceInfor.getScreenSize());
		data.put("clientName", getClientName());
		ClientMainController.getInstance().getClientSocketController().sendMessage(data);
		
	}

	@Override
	public void synchronous(FishManager fishManager, String clientName) {
		
	}

	@Override
	public void registerEvent(BaseEventDispatcher target) {
		// TODO Auto-generated method stub
		
	}
}
