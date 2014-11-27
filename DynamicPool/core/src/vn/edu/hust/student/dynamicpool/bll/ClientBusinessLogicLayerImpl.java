package vn.edu.hust.student.dynamicpool.bll;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.hust.student.dynamicpool.bll.model.*;
import vn.edu.hust.student.dynamicpool.dal.*;
import vn.edu.hust.student.dynamicpool.events.*;
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
		clientPoolManager.add(clientPool);
	}

	@Override
	public void joinHost(String key) {
		EventDestination.getInstance().addEventListener(
				EventType.DAL_JOIN_HOST,
				new BaseEventListener(this, "onJoinHostCallBackHander"));
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
		EventDestination.getInstance().addEventListener(
				EventType.DAL_ADD_DEVICE,
				new BaseEventListener(this, "onAddDeviceCallbackHander"));
		dataAccessLayer.addDevice(deviceInfo);
	}

	@Deprecated
	public void onAddDeviceCallbackHander(Event event) {
		logger.debug("onAddDeviceCallbackHander");
		// if (ex == null) {
		// try {
		//
		// if (data != null) {
		// ArrayList<Segment> segments = (ArrayList<Segment>) data;
		// this.pool.setSegments(segments);
		// }
		//
		// // set size for pool
		// Rectangle poolPosition = this.pool.getCorrdiate();
		//
		// poolPosition.setLocation(new Point(0, 0));
		// poolPosition.setHeight(AppConst.height);
		// poolPosition.setWidth(AppConst.width);
		//
		// } catch (final Exception castEx) {
		// callback.callback(false, new BLLException("Cannot add device ",
		// ex));
		// }
		// } else {
		// callback.callback(false, new BLLException("Cannot add device", ex));
		// }
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IFish> getFishs() {
		return clientPool.getFishes();
	}

	@Override
	public void update(float deltaTime) {
		clientPool.updatePosition(deltaTime);
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
		EventDestination.getInstance().addEventListener(
				EventType.DAL_CREATE_FISH,
				new BaseEventListener(this, "onCreateFishCallbackHander"));
		dataAccessLayer.createFish(fish);
	}

	@Deprecated
	public void onCreateFishCallbackHander(Event event) {
		logger.debug("on create fish callback hander");
		if (EventDestination.parseEventToBoolean(event)) {
			logger.info("create fish success");

			EventDestination.getInstance().dispatchSuccessEvent(
					EventType.BLL_CREATE_FISH);
		} else {
			logger.error("cannot create fish");
			EventDestination.getInstance().dispatchFailEvent(
					EventType.BLL_CREATE_FISH);
		}
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