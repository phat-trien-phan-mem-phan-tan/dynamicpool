package vn.edu.hust.student.dynamicpool.dal.server.logic;

import vn.edu.hust.student.dynamicpool.bll.BusinessLogicDataCallback;
import vn.edu.hust.student.dynamicpool.bll.BusinessLogicLayerImpl;
import vn.edu.hust.student.dynamicpool.dal.HostDataAccessLayerImpl;
import vn.edu.hust.student.dynamicpool.exception.BLLException;
import vn.edu.hust.student.dynamicpool.exception.DALException;
import vn.edu.hust.student.dynamicpool.presentation.PresentationBooleanCallback;

public class BusinessLogicLayerServerImpl extends BusinessLogicLayerImpl {
	private PoolManager poolManager = new PoolManager();

	public BusinessLogicLayerServerImpl() {
		this.dataAccessLayer = new HostDataAccessLayerImpl();
	}

	@Override
	public void createHost(PresentationBooleanCallback callback) {
		final PresentationBooleanCallback booleanCallback = callback;
		this.dataAccessLayer.createHost(new BusinessLogicDataCallback() {
			@Override
			public void callback(Object data, DALException ex) {
				createHostCallbackHander(booleanCallback, data, ex);
			}
		});
	}

	private void createHostCallbackHander(
			final PresentationBooleanCallback booleanCallback, Object data,
			DALException ex) {
		if (ex == null) {
			try {
				String key = (String) data;
				if (key != null && key != "") {
					booleanCallback.callback(true, null);
				}
			} catch (Exception e) {
				booleanCallback.callback(false, new BLLException(
						"Cannot Create Host", e));
			}
		}
		booleanCallback.callback(false, new BLLException(
				"Cannot ceate host", ex));
	}
}
