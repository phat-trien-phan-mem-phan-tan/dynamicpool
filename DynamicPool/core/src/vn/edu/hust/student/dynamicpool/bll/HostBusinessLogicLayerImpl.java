package vn.edu.hust.student.dynamicpool.bll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vn.edu.hust.student.dynamicpool.bll.model.DeviceInfo;
import vn.edu.hust.student.dynamicpool.bll.model.Pool;
import vn.edu.hust.student.dynamicpool.bll.model.PoolManager;
import vn.edu.hust.student.dynamicpool.dal.HostDataAccessLayerImpl;
import vn.edu.hust.student.dynamicpool.events.EventDestination;
import vn.edu.hust.student.dynamicpool.events.EventType;
import vn.edu.hust.student.dynamicpool.utils.AppConst;

import com.eposi.eventdriven.Event;
import com.eposi.eventdriven.implementors.BaseEventListener;

public class HostBusinessLogicLayerImpl extends ClientBusinessLogicLayerImpl {
	private PoolManager poolManager = new PoolManager();
	private Logger logger = LoggerFactory
			.getLogger(HostBusinessLogicLayerImpl.class);

	public HostBusinessLogicLayerImpl() {
		this.dataAccessLayer = new HostDataAccessLayerImpl();
	}

	@Override
	public void createHost() {
		EventDestination.getInstance().addEventListener(
				EventType.DAL_CREATE_HOST,
				new BaseEventListener(this, "onCreateHostCallbackHander"));
		dataAccessLayer.createHost();
	}

	@Deprecated
	public void onCreateHostCallbackHander(Event event) {
		logger.debug("on create host callback hander");
		//TODO 
	}

	@Override
	public void addDevide(DeviceInfo deviceInfo) {
		deviceInfo.setClientName(AppConst.DEFAULT_HOST_NAME);
		Pool pool = new Pool(deviceInfo);
		this.poolManager.add(pool);
		super.addDevide(deviceInfo);
	}
}
