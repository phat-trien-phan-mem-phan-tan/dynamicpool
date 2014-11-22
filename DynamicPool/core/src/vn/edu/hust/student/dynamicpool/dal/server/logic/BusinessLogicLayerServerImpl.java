package vn.edu.hust.student.dynamicpool.dal.server.logic;

import vn.edu.hust.student.dynamicpool.bll.BusinessLogicDataCallback;
import vn.edu.hust.student.dynamicpool.bll.BusinessLogicLayerImpl;
import vn.edu.hust.student.dynamicpool.dal.HostDataAccessLayerImpl;
import vn.edu.hust.student.dynamicpool.presentation.PresentationBooleanCallback;

public class BusinessLogicLayerServerImpl extends BusinessLogicLayerImpl {
	private PoolManager poolManager = new PoolManager();
	
	public BusinessLogicLayerServerImpl() {
		this.dataAccessLayer = new HostDataAccessLayerImpl();
	}

	@Override
	public void createHost(PresentationBooleanCallback callback) {
		this.dataAccessLayer.createHost(new BusinessLogicDataCallback() {
			
			@Override
			public void callback(Object data, Exception ex) {
				
			}
		});
	}
}
