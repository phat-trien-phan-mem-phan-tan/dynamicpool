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
	public void joinHost(String key, PresentationBooleanCallback callback) {

		BusinessLogicDataCallback logicDataCallBack = new BusinessLogicDataCallback() {

			@Override
			public void callback(Object data, Exception ex) {
				// TODO Auto-generated method stub

			}
		};

		try {
			int keyJoin = Integer.parseInt(key);

			dataAccessLayer.joinHost(keyJoin, logicDataCallBack);

			// check number format
		} catch (NumberFormatException e) {

			System.out.println(e.getMessage());
			e.printStackTrace();

			throw new NumberFormatException(key);
		}

	}

	@Override
	public void createHost(PresentationBooleanCallback callback) {

		BusinessLogicDataCallback logicDataCallBack = new BusinessLogicDataCallback() {

			@Override
			public void callback(Object data, Exception ex) {
				// TODO Auto-generated method stub

			}
		};

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
		
		
		
	}
}
