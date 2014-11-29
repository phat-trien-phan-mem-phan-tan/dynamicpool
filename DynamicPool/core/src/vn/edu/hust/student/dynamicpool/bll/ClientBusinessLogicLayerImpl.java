package vn.edu.hust.student.dynamicpool.bll;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vn.edu.hust.student.dynamicpool.bll.model.DeviceInfo;
import vn.edu.hust.student.dynamicpool.bll.model.ETrajectoryType;
import vn.edu.hust.student.dynamicpool.bll.model.Fish;
import vn.edu.hust.student.dynamicpool.bll.model.FishFactory;
import vn.edu.hust.student.dynamicpool.bll.model.FishType;
import vn.edu.hust.student.dynamicpool.bll.model.IFish;
import vn.edu.hust.student.dynamicpool.bll.model.Pool;
import vn.edu.hust.student.dynamicpool.bll.model.PoolManager;
import vn.edu.hust.student.dynamicpool.dal.ClientDataAccessLayerImpl;
import vn.edu.hust.student.dynamicpool.dal.DataAccessLayer;
import vn.edu.hust.student.dynamicpool.events.EventDestination;
import vn.edu.hust.student.dynamicpool.events.EventType;

import com.eposi.eventdriven.Event;
import com.eposi.eventdriven.implementors.BaseEventListener;

public class ClientBusinessLogicLayerImpl implements BusinessLogicLayer {

	protected DataAccessLayer dataAccessLayer;
	private PoolManager clientPoolManager = new PoolManager();
	private Pool clientPool = new Pool();
	private String keyOfHost;
	private Logger logger = LoggerFactory
			.getLogger(ClientBusinessLogicLayerImpl.class);

	public ClientBusinessLogicLayerImpl() {
		this.dataAccessLayer = new ClientDataAccessLayerImpl();
		registerEvents();
		setClientPool(new Pool());
	}

	protected void registerEvents() {
		EventDestination.getInstance().addEventListener(
				EventType.DAL_JOIN_HOST,
				new BaseEventListener(this, "onJoinHostCallBackHander"));		
		EventDestination.getInstance().addEventListener(
				EventType.DAL_ADD_DEVICE,
				new BaseEventListener(this, "onAddDeviceCallbackHander"));
		EventDestination.getInstance().addEventListener(
				EventType.DAL_CREATE_FISH_RESPOND,
				new BaseEventListener(this, "onCreateFishCallbackHander"));
	}

	protected void setClientPool(Pool pool) {
		this.clientPool = pool;
		clientPoolManager.clear();
		clientPoolManager.addClientPool(this.clientPool);
	}

	@Override
	public void joinHost(String key) {
		this.dataAccessLayer.joinHost(key);
	}

	@Deprecated
	public void onJoinHostCallBackHander(Event event) {
		logger.debug("on join host callback hander");
		if (EventDestination.parseEventToBoolean(event)) {
			logger.info("join host success");
			EventDestination.getInstance().dispatchSuccessEvent(
					EventType.BLL_JOIN_HOST_WHITH_A_KEY);
		} else {
			logger.error("cannot join host");
			EventDestination.getInstance().dispatchFailEvent(
					EventType.BLL_JOIN_HOST_WHITH_A_KEY);
		}
	}

	@Override
	public void createHost() {
		logger.error("cannot create host because this is a client");
		EventDestination.getInstance().dispatchFailEvent(
				EventType.BLL_CREATE_HOST);
	}

	@Override
	public void addDevide(DeviceInfo deviceInfo) {
		logger.debug("add device");
		dataAccessLayer.addDevice(deviceInfo);
	}

	@Deprecated
	public void onAddDeviceCallbackHander(Event event) {
		logger.debug("on add device callback hander");
		if (EventDestination.parseEventToBoolean(event)) {
			Object addDeviceResultObject = EventDestination
					.parseEventToTargetObject(event);
			if (Pool.class.isInstance(addDeviceResultObject)) {
				logger.debug("add device success");
				setClientPool((Pool) addDeviceResultObject);
				EventDestination.getInstance().dispatchSuccessEvent(
						EventType.BLL_ADD_DEVICE);
				return;
			}
			logger.debug("cannot add device: target object is not an instance of Pool");
		}
		logger.debug("Cannot add device");
		EventDestination.getInstance().dispatchFailEvent(
				EventType.BLL_ADD_DEVICE);
	}

	@Override
	public List<IFish> getFishes() {
		return clientPool.getFishes();
	}

	@Override
	public void update(float deltaTime) {
		clientPoolManager.updateLocationOfFishes(deltaTime);
	}

	@Override
	public void exit() {
		this.dataAccessLayer.exit();
	}

	@Override
	public void createFish(final FishType fishType,
			final ETrajectoryType trajectoryType, final int width,
			final int height) {
		logger.debug("Create fish");
		IFish fish = FishFactory.createFishWithTrajectoryType(fishType,
				trajectoryType, width, height);
		dataAccessLayer.requestCreateFish(fish);
	}

	@Deprecated
	public void onCreateFishCallbackHander(Event event) {
		logger.debug("on create fish callback hander");
		if (EventDestination.parseEventToBoolean(event)) {
			Object targetObject = EventDestination
					.parseEventToTargetObject(event);
			if (IFish.class.isInstance(targetObject)) {
				logger.info("create fish success");
				IFish fish = (IFish) targetObject;
				clientPool.addFish(fish);
				EventDestination.getInstance().dispatchSuccessEventWithObject(
						EventType.BLL_CREATE_FISH, targetObject);
				return;
			}
			logger.error("cannot create fish: target object is not instance of IFish");
		} else {
			logger.error("cannot create fish");
		}
		EventDestination.getInstance().dispatchFailEvent(
				EventType.BLL_CREATE_FISH);
	}

	@Override
	public void removeFish(Fish fish) {

	}

	@Override
	public void synchronization() {

	}

	@Deprecated
	public void onCollision(Event e) {

	}

	public DataAccessLayer getDataAccessLayer() {
		return dataAccessLayer;
	}

	public Pool getPool() {
		return clientPool;
	}

	public void setPool(Pool pool) {
		this.clientPool = pool;
	}

	@Override
	public String getKeyOfHost() {
		return keyOfHost;
	}

	public void saveKey(String keyOfHost) {
		this.keyOfHost = keyOfHost;
	}

	public String getKey() {
		return keyOfHost;
	}
}