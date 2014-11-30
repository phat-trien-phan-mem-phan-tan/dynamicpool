package vn.edu.hust.student.dynamicpool.bll;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vn.edu.hust.student.dynamicpool.bll.model.DeviceInfo;
import vn.edu.hust.student.dynamicpool.bll.model.Fish;
import vn.edu.hust.student.dynamicpool.bll.model.IFish;
import vn.edu.hust.student.dynamicpool.bll.model.Pool;
import vn.edu.hust.student.dynamicpool.bll.model.PoolManager;
import vn.edu.hust.student.dynamicpool.dal.HostDataAccessLayerImpl;
import vn.edu.hust.student.dynamicpool.events.EventDestination;
import vn.edu.hust.student.dynamicpool.events.EventType;
import vn.edu.hust.student.dynamicpool.exception.DALException;
import com.eposi.eventdriven.Event;
import com.eposi.eventdriven.implementors.BaseEventListener;

public class HostBusinessLogicLayerImpl extends ClientBusinessLogicLayerImpl {
	private PoolManager hostPoolManager = new PoolManager();
	private Logger logger = LoggerFactory
			.getLogger(HostBusinessLogicLayerImpl.class);

	public HostBusinessLogicLayerImpl() {
		this.dataAccessLayer = new HostDataAccessLayerImpl();
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		hostPoolManager.updateLocationOfFishes(deltaTime);
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
		EventDestination.getInstance()
				.addEventListener(
						EventType.DAL_CREATE_FISH_REQUEST,
						new BaseEventListener(this,
								"onCreateFishRequestCallbackHander"));
		EventDestination.getInstance().addEventListener(
				EventType.BLL_SEND_FISH,
				new BaseEventListener(this, "onSendFishCallbackHander"));
	}

	@Override
	public void createHost() {
		try {
			dataAccessLayer.createHost();
		} catch (DALException e) {
			logger.error("cannot create host {}", e);
			e.printStackTrace();
		}
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
		logger.debug("on add device request callback hander");
		if (EventDestination.parseEventToBoolean(event)) {
			Object deviceObject = EventDestination
					.parseEventToTargetObject(event);
			if (DeviceInfo.class.isInstance(deviceObject)) {
				logger.info("add device success");
				DeviceInfo deviceInfo = (DeviceInfo) deviceObject;
				logger.info("add device request: client name {}",
						deviceInfo.getClientName());
				Pool pool = new Pool(deviceInfo);
				hostPoolManager.addHostPool(pool);
				if (hostPoolManager.size() == 1) {
					try {
						dataAccessLayer.updateSettingToClient(
								deviceInfo.getClientName(), pool);
					} catch (DALException e) {
						logger.error("update setting false");
					}
				} else {
					EventDestination.getInstance().dispatchSuccessEvent(
							EventType.BLL_ADD_DEVICE);
					logger.debug("sent add device envent to all client");
					sendUpdateSettingForAllClient();
				}
				return;
			}
			logger.error("cannot add device: target object is not an instance of DiviceInfo");
		} else {
			logger.error("Add Device Callback Hander: event false");
		}
	}

	private void sendUpdateSettingForAllClient() {
		for (Pool pool : hostPoolManager.getPools()) {
			String clientName = pool.getDeviceInfo().getClientName();
			Pool poolForClient = hostPoolManager.getPoolForClient(clientName);
			if (poolForClient == null) {
				logger.error("cannot get pool {}", clientName);
				return;
			}
			try {
				logger.debug("send update setting for client {}", clientName);
				dataAccessLayer
						.updateSettingToClient(clientName, poolForClient);
			} catch (DALException e) {
				logger.error("cannot send update setting to client {}", e);
			}
		}
	}

	@Deprecated
	public void onCreateFishRequestCallbackHander(Event event) {
		logger.debug("on request create fish callback hander");
		if (EventDestination.parseEventToBoolean(event)) {
			Object listObject = EventDestination
					.parseEventToTargetObject(event);
			if (List.class.isInstance(listObject)) {
				ArrayList<Object> list = (ArrayList<Object>) listObject;
				String clientName = null;
				IFish fish = null;
				for (Object object : list) {
					if (IFish.class.isInstance(object))
						fish = (IFish) object;
					if (String.class.isInstance(object))
						clientName = (String) object;
				}
				if (clientName == null || fish == null) {
					logger.debug("client name or fish null");
					try {
						if (clientName != null)
							dataAccessLayer.respondCreateFishRequest(
									clientName, false, null);
					} catch (DALException e) {
						logger.error(
								"cannot send add fish response (false result) {}",
								e);
					}
					return;
				}
				fish = hostPoolManager.addFish(clientName, fish);
				try {
					dataAccessLayer.respondCreateFishRequest(clientName, true,
							fish);
				} catch (DALException e) {
					logger.error("cannot respond create fish event {}", e);
				}
			}
		}
	}

	@Deprecated
	public void onSendFishCallbackHander(Event event) {
		logger.debug("on send fish to client callback hander");
		if (EventDestination.parseEventToBoolean(event)) {
			Object targetList = EventDestination
					.parseEventToTargetObject(event);
			if (!(targetList instanceof List)) {
				logger.error("error: event target is not instance of list");
				return;
			}
			String clientName = null;
			IFish fish = null;
			for (Object object : (List<Object>) targetList) {
				if (object instanceof String)
					clientName = (String) object;
				if (object instanceof IFish)
					fish = (IFish) object;
			}
			if (clientName == null || fish == null) {
				logger.error("error: invalid params in event target");
				return;
			}
			try {
				dataAccessLayer.respondCreateFishRequest(clientName, true,
						fish.cloneFish());
				logger.debug("sent a new fish {} to a client {}",
						fish.getFishId(), clientName);
			} catch (DALException e) {
				logger.error("cannot send new fish {} to client {}",
						fish.getFishId(), clientName);
			}

			logger.info("send fish {} to client {}", fish.getFishId(),
					clientName);
		} else {
			logger.error("send fish callback hander error");
		}
	}

	public PoolManager getHostPoolManager() {
		return this.hostPoolManager;
	}
}