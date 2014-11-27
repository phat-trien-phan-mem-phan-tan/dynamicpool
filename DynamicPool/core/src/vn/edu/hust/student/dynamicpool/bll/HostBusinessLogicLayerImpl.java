package vn.edu.hust.student.dynamicpool.bll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vn.edu.hust.student.dynamicpool.bll.model.DeviceInfo;
import vn.edu.hust.student.dynamicpool.bll.model.Pool;
import vn.edu.hust.student.dynamicpool.bll.model.PoolManager;
import vn.edu.hust.student.dynamicpool.dal.HostDataAccessLayerImpl;
import vn.edu.hust.student.dynamicpool.events.EventDestination;
import vn.edu.hust.student.dynamicpool.events.EventType;
import vn.edu.hust.student.dynamicpool.events.results.AddDeviceResult;
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
		if (EventDestination.parseEventToBoolean(event)) {
			Object keyObject = EventDestination.parseEventToTargetObject(event);
			saveKey(keyObject.toString());
			logger.info("create host success");
			EventDestination.getInstance().dispatchSuccessEvent(
					EventType.BLL_CREATE_HOST);
		} else {
			logger.error("cannot create host");
			EventDestination.getInstance().dispatchFailEvent(
					EventType.BLL_CREATE_HOST);
		}
	}

	@Override
	public void addDevide(DeviceInfo deviceInfo) {
		deviceInfo.setClientName(AppConst.DEFAULT_HOST_NAME);
		Pool pool = new Pool(deviceInfo);
		this.poolManager.add(pool);
		dataAccessLayer.addDevice(deviceInfo);
		EventDestination.getInstance().dispatchSuccessEventWithObject(
				EventType.BLL_ADD_DEVICE, new AddDeviceResult());
	}
}
