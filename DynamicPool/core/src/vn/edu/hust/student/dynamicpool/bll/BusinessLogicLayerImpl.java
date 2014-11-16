package vn.edu.hust.student.dynamicpool.bll;

import vn.edu.hust.student.dynamicpool.dal.DataAccessLayerImpl;
import vn.edu.hust.student.dynamicpool.presentation.PresentationBooleanCallback;

public class BusinessLogicLayerImpl implements BusinessLogicLayer {
	
	private DataAccessLayerImpl dataAccessLayerImpl = new DataAccessLayerImpl();
	
	public BusinessLogicLayerImpl() {
		
	}

	@Override
	public void joinHost(String key, PresentationBooleanCallback callback) {
		
		/*
		 *  check key is null
		 */
		
		
		
	}

	@Override
	public void createHost(PresentationBooleanCallback callback) {
		
		
	}
}
