package vn.edu.hust.student.dynamicpool.bll;

import java.util.concurrent.Future;

import vn.edu.hust.student.dynamicpool.dal.DataAccessLayer;
import vn.edu.hust.student.dynamicpool.dal.DataAccessLayerImpl;
import vn.edu.hust.student.dynamicpool.model.DevideInfor;
import vn.edu.hust.student.dynamicpool.presentation.PresentationBooleanCallback;

public class BusinessLogicLayerImpl implements BusinessLogicLayer {

	private DataAccessLayer dataAccessLayer;

	public BusinessLogicLayerImpl() {
		this.dataAccessLayer = new DataAccessLayerImpl();
	}

	@Override
	public void joinHost(String key, final PresentationBooleanCallback callback) {

		BusinessLogicDataCallback logicDataCallBack = new BusinessLogicDataCallback() {

			@Override
			public void callback(Object data, Exception ex) {
				callback.callback((Boolean)data, ex);

			}
		};

		try {
			int keyJoin = Integer.parseInt(key);

			dataAccessLayer.joinHost(keyJoin, logicDataCallBack);

			// check number format
		} catch (NumberFormatException e) {

			callback.callback(false, e);
			System.out.println(e.getMessage());
			e.printStackTrace();

			throw new NumberFormatException(key);
		}

	}

	@Override
	public void createHost(final PresentationBooleanCallback callback) {

		BusinessLogicDataCallback logicDataCallBack = new BusinessLogicDataCallback() {

			@Override
			public void callback(Object data, Exception ex) {
				// TODO Auto-generated method stub
				callback.callback((Boolean)data, ex);

			}
		};
		
		dataAccessLayer.createHost(logicDataCallBack);

	}

	@Override
	public void intialDevide(DevideInfor devideInfor,
			PresentationBooleanCallback callback) {
		
		BusinessLogicDataCallback logicDataCallBack = new BusinessLogicDataCallback() {

			@Override
			public void callback(Object data, Exception ex) {
				// TODO Auto-generated method stub

			}
		};
		
		dataAccessLayer.intialDevide(devideInfor, logicDataCallBack);
		
		
		
	}

	@Override
	public void addDevide(DevideInfor devideInfor,
			PresentationBooleanCallback callback) {
		
		BusinessLogicDataCallback logicDataCallBack = new BusinessLogicDataCallback() {

			@Override
			public void callback(Object data, Exception ex) {
				// TODO Auto-generated method stub

			}
		};

		dataAccessLayer.addDevide(devideInfor, logicDataCallBack);
		
	}
}
