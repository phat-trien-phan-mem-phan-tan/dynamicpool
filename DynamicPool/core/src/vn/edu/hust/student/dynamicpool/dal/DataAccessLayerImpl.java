package vn.edu.hust.student.dynamicpool.dal;

import vn.edu.hust.student.dynamicpool.controller.MainController;
import vn.edu.hust.student.dynamicpool.exception.NetworkException;
import vn.edu.hust.student.dynamicpool.presentation.PresentationBooleanCallback;

public class DataAccessLayerImpl implements DataAccessLayer{

	@Override
	public void joinHost(int key, PresentationBooleanCallback callback) {
		// TODO Auto-generated method stub
		try {
			MainController.getInstance().start(key);
			callback.callback(true, null);
		} catch (NetworkException e) {
			callback.callback(false, e);
		}
	}

	@Override
	public void createHost(PresentationBooleanCallback callback) {
		// TODO Auto-generated method stub
		
	}

}
