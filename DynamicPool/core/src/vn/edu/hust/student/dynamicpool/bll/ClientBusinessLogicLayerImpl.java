package vn.edu.hust.student.dynamicpool.bll;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vn.edu.hust.student.dynamicpool.bll.model.DeviceInfo;
import vn.edu.hust.student.dynamicpool.bll.model.ETrajectoryType;
import vn.edu.hust.student.dynamicpool.bll.model.Fish;
import vn.edu.hust.student.dynamicpool.bll.model.FishFactory;
import vn.edu.hust.student.dynamicpool.bll.model.FishType;
import vn.edu.hust.student.dynamicpool.bll.model.IFish;
import vn.edu.hust.student.dynamicpool.bll.model.Pool;
import vn.edu.hust.student.dynamicpool.dal.ClientDataAccessLayerImpl;
import vn.edu.hust.student.dynamicpool.dal.DataAccessLayer;
import vn.edu.hust.student.dynamicpool.events.EventDestination;
import vn.edu.hust.student.dynamicpool.events.EventType;

import com.eposi.eventdriven.Event;
import com.eposi.eventdriven.implementors.BaseEventListener;

public class ClientBusinessLogicLayerImpl implements BusinessLogicLayer {

	protected DataAccessLayer dataAccessLayer;
	private Pool pool = new Pool();
	private String keyOfHost;
	private Logger logger = LoggerFactory
			.getLogger(ClientBusinessLogicLayerImpl.class);

	public ClientBusinessLogicLayerImpl() {
		this.dataAccessLayer = new ClientDataAccessLayerImpl();
		EventDestination.getInstance().addEventListener(EventType.COLISSION,
				new BaseEventListener(this, "onColission"));
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
			EventDestination.getInstance().dispatchSuccessEvent(EventType.BLL_JOIN_HOST_WHITH_A_KEY);
		} else {
			logger.error("cannot join host");
			EventDestination.getInstance().dispatchFailEvent(EventType.BLL_JOIN_HOST_WHITH_A_KEY);
		}
	}

	@Override
	public void createHost() {
		logger.error("cannot create host because this is a client");
		EventDestination.getInstance().dispatchFailEvent(EventType.BLL_CREATE_HOST);
	}

	@Override
	public void addDevide(DeviceInfo deviceInfo) {
		logger.debug("add device");
		EventDestination.getInstance().addEventListener(EventType.DAL_ADD_DEVICE, new BaseEventListener(this, "onAddDeviceCallbackHander"));
		dataAccessLayer.addDevice(deviceInfo);
	}

	@Deprecated
	public void onAddDeviceCallbackHander(Event event) {
		logger.debug("onAddDeviceCallbackHander");
//		if (ex == null) {
//			try {
//
//				if (data != null) {
//					ArrayList<Segment> segments = (ArrayList<Segment>) data;
//					this.pool.setSegments(segments);
//				}
//
//				// set size for pool
//				Rectangle poolPosition = this.pool.getCorrdiate();
//
//				poolPosition.setLocation(new Point(0, 0));
//				poolPosition.setHeight(AppConst.height);
//				poolPosition.setWidth(AppConst.width);
//
//			} catch (final Exception castEx) {
//				callback.callback(false, new BLLException("Cannot add device ",
//						ex));
//			}
//		} else {
//			callback.callback(false, new BLLException("Cannot add device", ex));
//		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<IFish> getFishs() {

		ArrayList<IFish> fishes = new ArrayList<IFish>();
		try {
			Object a = pool.getFishes();
			fishes = (ArrayList<IFish>) a;
		} catch (Exception e) {
			System.err.println("Error: BLL " + e.getMessage());
		}

		return fishes;
	}

	@Override
	public void update(float deltaTime) {

		pool.updatePosition(deltaTime);

	}

	@Override
	public void exit() {
		this.dataAccessLayer.exit();
	}

	@Override
	public void createFish(final FishType fishType,
			final ETrajectoryType trajectoryType, final int width,
			final int height) {

		Fish newFish = null;

		if (trajectoryType == ETrajectoryType.LINE
				|| trajectoryType == ETrajectoryType.NONE) {

			newFish = FishFactory.createFishWithLineTrajectory(width, height,
					fishType);
		} else if (trajectoryType == ETrajectoryType.CYCLE) {

			newFish = FishFactory.createFishWithCycleTrajectory(width, height,
					fishType);

		} else if (trajectoryType == ETrajectoryType.SIN) {

			newFish = FishFactory.createFishWithSinTrajectory(width, height,
					fishType);

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
		return pool;
	}

	public void setPool(Pool pool) {
		this.pool = pool;
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