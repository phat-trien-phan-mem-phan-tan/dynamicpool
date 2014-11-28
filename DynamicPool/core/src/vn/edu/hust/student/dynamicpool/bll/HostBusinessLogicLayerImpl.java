package vn.edu.hust.student.dynamicpool.bll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vn.edu.hust.student.dynamicpool.bll.model.Pool;
import vn.edu.hust.student.dynamicpool.bll.model.PoolManager;
import vn.edu.hust.student.dynamicpool.dal.HostDataAccessLayerImpl;
import vn.edu.hust.student.dynamicpool.events.EventDestination;
import vn.edu.hust.student.dynamicpool.events.EventType;

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
	protected void registerEvents() {
		super.registerEvents();
		EventDestination.getInstance().addEventListener(
				EventType.DAL_CREATE_HOST,
				new BaseEventListener(this, "onCreateHostCallbackHander"));
	}

	@Override
	public void createHost() {
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
	@Deprecated
	public void onAddDeviceCallbackHander(Event event) {
		logger.debug("on add device callback hander");
		if (EventDestination.parseEventToBoolean(event)) {
			Object addDeviceResultObject = EventDestination
					.parseEventToTargetObject(event);
			if (Pool.class.isInstance(addDeviceResultObject)) {
				logger.debug("add device success");
				Pool clientPool = (Pool) addDeviceResultObject;
				setClientPool(clientPool);
				poolManager.clear();
				poolManager.addHostPool(new Pool(clientPool.getDeviceInfo()));
				EventDestination.getInstance().dispatchSuccessEvent(
						EventType.BLL_ADD_DEVICE);
				return;
			}
			logger.debug("cannot add device: target object is not an instance of AddDeviceResult");
		}
		logger.debug("Cannot add device");
		EventDestination.getInstance().dispatchFailEvent(
				EventType.BLL_ADD_DEVICE);
	}
}
