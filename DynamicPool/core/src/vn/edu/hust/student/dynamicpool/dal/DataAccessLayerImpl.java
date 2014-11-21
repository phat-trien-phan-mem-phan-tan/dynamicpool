package vn.edu.hust.student.dynamicpool.dal;

import vn.edu.hust.student.dynamicpool.bll.BusinessLogicDataCallback;
import vn.edu.hust.student.dynamicpool.bll.ETrajectoryType;
import vn.edu.hust.student.dynamicpool.bll.Fish;
import vn.edu.hust.student.dynamicpool.bll.FishType;
import vn.edu.hust.student.dynamicpool.controller.MainController;
import vn.edu.hust.student.dynamicpool.exception.NetworkException;
import vn.edu.hust.student.dynamicpool.model.DeviceInfo;

public class DataAccessLayerImpl implements DataAccessLayer {
	@Override
	public void joinHost(int key, BusinessLogicDataCallback callback) {
		try {
			MainController.getInstance().start(key);
			callback.callback(true, null);
		} catch (NetworkException e) {
			callback.callback(false, e);
		}
	}

	@Override
	public void createHost(BusinessLogicDataCallback callback) {
		try {
			int created = MainController.getInstance().createHost();
			callback.callback(created, null);
		} catch (NetworkException e) {
			callback.callback(0, e);
		}
	}

	@Override
	public void intialDevide(DeviceInfo devideInfor,
			BusinessLogicDataCallback callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addDevide(DeviceInfo devideInfor,
			BusinessLogicDataCallback callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exit(BusinessLogicDataCallback callback) {
		
	}

	@Override
	public void createFish(Fish fish, BusinessLogicDataCallback callback) {
		
	}

	// ong viet giup toi cai event lam sao ma cu 1s thi no tu dong gui ket qua nen 
	/*
	 * srever va nhan ket qua tu server duoi dang 1 list danh sach cac diem cua ca trong be
	 * 
	 */
	@Override
	public void synchronization(BusinessLogicDataCallback callback) {
		
	}

	@Override
	public void removeFish(Fish fish, BusinessLogicDataCallback callback) {
		// TODO Auto-generated method stub
		
	}




}
