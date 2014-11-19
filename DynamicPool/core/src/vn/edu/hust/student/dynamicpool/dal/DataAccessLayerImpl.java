package vn.edu.hust.student.dynamicpool.dal;

import vn.edu.hust.student.dynamicpool.bll.BusinessLogicDataCallback;
import vn.edu.hust.student.dynamicpool.bll.ETrajectoryType;
import vn.edu.hust.student.dynamicpool.bll.Fish;
import vn.edu.hust.student.dynamicpool.bll.FishType;
import vn.edu.hust.student.dynamicpool.controller.MainController;
import vn.edu.hust.student.dynamicpool.exception.NetworkException;
import vn.edu.hust.student.dynamicpool.model.DevideInfor;

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
	public void intialDevide(DevideInfor devideInfor,
			BusinessLogicDataCallback callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addDevide(DevideInfor devideInfor,
			BusinessLogicDataCallback callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exit(BusinessLogicDataCallback callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createFish(Fish fish,BusinessLogicDataCallback callback) {
		
		
	}
}
