package vn.edu.hust.student.dynamicpool.bll;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vn.edu.hust.student.dynamicpool.bll.model.DeviceInfo;
import vn.edu.hust.student.dynamicpool.bll.model.IFish;
import vn.edu.hust.student.dynamicpool.bll.model.Pool;
import vn.edu.hust.student.dynamicpool.bll.model.PoolManager;
import vn.edu.hust.student.dynamicpool.dal.HostDataAccessLayerImpl;
import vn.edu.hust.student.dynamicpool.events.EventDestination;
import vn.edu.hust.student.dynamicpool.events.EventType;
import vn.edu.hust.student.dynamicpool.exception.DALException;
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
	protected void registerEvents() {
		super.registerEvents();
		EventDestination.getInstance().addEventListener(
				EventType.DAL_CREATE_HOST,
				new BaseEventListener(this, "onCreateHostCallbackHander"));
		EventDestination.getInstance()
				.addEventListener(
						EventType.DAL_ADD_DEVICE_REQUEST,
						new BaseEventListener(this,
								"onAddDiviceRequestCallbackHander"));
		EventDestination.getInstance().addEventListener(
				EventType.DAL_CREATE_FISH_REQUEST,
				new BaseEventListener(this, "onCreateFishRequest"));
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

	@Deprecated
	public void onAddDiviceRequestCallbackHander(Event event) {
		if (EventDestination.parseEventToBoolean(event)) {
			Object deviceObject = EventDestination
					.parseEventToTargetObject(event);
			if (DeviceInfo.class.isInstance(deviceObject)) {
				logger.info("add device success");
				DeviceInfo deviceInfo = (DeviceInfo) deviceObject;
				Pool pool = new Pool(deviceInfo);
				poolManager.addHostPool(pool);
				if (poolManager.size() == 1) {
					try {
						dataAccessLayer.updateSettingToClient(
								deviceInfo.getClientName(), pool);
					} catch (DALException e) {
						logger.error("update setting false");
					}
				} else {
					EventDestination.getInstance().dispatchSuccessEvent(
							EventType.BLL_ADD_DEVICE);
					logger.debug("sent add device envent to server");
				}
				return;
			}
			logger.error("cannot add device: target object is not an instance of DiviceInfo");
		} else {
			logger.error("Add Device Callback Hander: event false");
		}
	}
	
	@Deprecated
	public void onCreateFishRequest(Event event) {
		if (EventDestination.parseEventToBoolean(event)) {
			Object listObject = EventDestination.parseEventToTargetObject(event);
			if (List.class.isInstance(listObject)) {
				ArrayList<Object> list = (ArrayList<Object>) listObject;
				String clientName = null;
				IFish fish = null;
				for (Object object : list) {
					if (IFish.class.isInstance(object)) fish = (IFish) object;
					if (String.class.isInstance(object)) clientName = (String) object; 
				}
				if (clientName == null || fish == null) {
					logger.debug("client name or fish null");
					return;
				}
				poolManager.addFish(clientName, fish);
			}
		}
	}
}
