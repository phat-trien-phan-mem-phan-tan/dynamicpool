package vn.edu.hust.student.dynamicpool.bll;

import java.util.ArrayList;

import vn.edu.hust.student.dynamicpool.bll.fishEvent.FishStateEvent;
import vn.edu.hust.student.dynamicpool.bll.statics.EventType;
import vn.edu.hust.student.dynamicpool.dal.ClientDataAccessLayerImpl;
import vn.edu.hust.student.dynamicpool.dal.DataAccessLayer;
import vn.edu.hust.student.dynamicpool.dal.utils.AppConst;
import vn.edu.hust.student.dynamicpool.exception.BLLException;
import vn.edu.hust.student.dynamicpool.exception.DALException;
import vn.edu.hust.student.dynamicpool.model.DeviceInfo;
import vn.edu.hust.student.dynamicpool.model.Point;
import vn.edu.hust.student.dynamicpool.model.Pool;
import vn.edu.hust.student.dynamicpool.model.Rectangle;
import vn.edu.hust.student.dynamicpool.model.Segment;
import vn.edu.hust.student.dynamicpool.presentation.PresentationBooleanCallback;

import com.eposi.eventdriven.Event;
import com.eposi.eventdriven.implementors.BaseEventListener;

public class BusinessLogicLayerImpl implements BusinessLogicLayer {

	protected DataAccessLayer dataAccessLayer;
	private Pool pool = new Pool();
	private String keyOfHost;

	public BusinessLogicLayerImpl() {
		 this.dataAccessLayer = new ClientDataAccessLayerImpl();
		 pool.addEventListener(EventType.COLISSION, new BaseEventListener(this,
					"onColission"));
		/*this.dataAccessLayer = new DalTest();*/

	}

	@Override
	public void joinHost(String key, final PresentationBooleanCallback callback) {

		BusinessLogicDataCallback logicDataCallBack = new BusinessLogicDataCallback() {

			@Override
			public void callback(Object data, DALException ex) {
				
		
				joinHostCallBack(callback, data, ex);
				

			}
		};
		// tro thanh sua data access layer

		this.dataAccessLayer.joinHost(key, logicDataCallBack);

	}

	private void joinHostCallBack(final PresentationBooleanCallback callback,
			Object data, Exception ex) {

		if (ex == null) {
			try {
				if (data != null) {
					System.out.println(data.toString());
					boolean joinResult = (boolean) data;
					
					if (joinResult) {
						callback.callback(true, null);

					} else {
						callback.callback(false, null);
					}

				}

			} catch (Exception castEx) {
				callback.callback(false, new BLLException("Join host failed !",
						castEx));
			}
		} else {
			callback.callback(false, new BLLException("Join host failed !", ex));
		}
	}

	@Override
	public void createHost(final PresentationBooleanCallback callback) {
		callback.callback(false, new BLLException("Cannot create host"));
	}

	@Override
	public void addDevide(DeviceInfo deviceInfo,
			final PresentationBooleanCallback callback) {

		BusinessLogicDataCallback logicDataCallBack = new BusinessLogicDataCallback() {

			@Override
			public void callback(Object data, DALException ex) {
				addDeviceCallback(callback, data, ex);

			}
		};

		dataAccessLayer.addDevice(deviceInfo, logicDataCallBack);
	}

	protected void addDeviceCallback(
			final PresentationBooleanCallback callback, final Object data,
			final Exception ex) {

		if (ex == null) {
			try {

				if (data != null) {
					ArrayList<Segment> segments = (ArrayList<Segment>) data;
					this.pool.setSegments(segments);
				}

				// set size for pool
				Rectangle poolPosition = this.pool.getCorrdiate();

				poolPosition.setLocation(new Point(0, 0));
				poolPosition.setHeight(AppConst.height);
				poolPosition.setWidth(AppConst.width);

			} catch (final Exception castEx) {
				callback.callback(false, new BLLException("Cannot add device ",
						ex));
			}
		} else {

			callback.callback(false, new BLLException("Cannot add device", ex));
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<IFish> getFishs() {

		ArrayList<IFish> fishes = new ArrayList<IFish>();
		try {
			Object a = pool.getFishCollection();
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

		// call data access layer
		BusinessLogicDataCallback logicDataCallBack = new BusinessLogicDataCallback() {

			@Override
			public void callback(Object data, DALException ex) {
				

			}
		};

		this.dataAccessLayer.exit(logicDataCallBack);
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

		createFishCallBackAction(newFish);

	}

	protected void createFishCallBackAction(final Fish newFish) {
		BusinessLogicDataCallback logicDataCallBack = new BusinessLogicDataCallback() {

			@Override
			public void callback(Object data, DALException ex) {
				createFishCallBack(newFish, data, ex);

			}
		};

		this.dataAccessLayer.createFish(newFish, logicDataCallBack);
	}

	private void createFishCallBack(Fish fish, Object data, final Exception ex) {

		if (ex == null) {

			if (data != null) {
				Boolean resultCreateFish = (Boolean) data;

				if (resultCreateFish) {

					this.pool.getFishCollection().addFish(fish);
				}

			}

		}

	}
	
	@Override
	public void removeFish(Fish fish) {
		
		
		BusinessLogicDataCallback logicDataCallBack = new BusinessLogicDataCallback() {

			@Override
			public void callback(Object data, DALException ex) {
			

			}
		};
		// tro thanh sua data access layer
		this.dataAccessLayer.removeFish(fish,logicDataCallBack);
		pool.getFishCollection().getFishs().remove(fish);
		
	}
	
	
	


	@Override
	public void synchronization() {

		BusinessLogicDataCallback logicDataCallBack = new BusinessLogicDataCallback() {

			@Override
			public void callback(Object data, DALException ex) {

			}
		};

		this.dataAccessLayer.synchronization(logicDataCallBack);

	}
	
	
	
	@Deprecated
	public void onCollision(Event e){
		try{
			FishStateEvent fishStateEvent = (FishStateEvent) e;
			
			Fish fish = fishStateEvent.getFish();
			
		}catch(Exception ex){
			
		}
		
	}
	
	
	public DataAccessLayer getDataAccessLayer() {
		return dataAccessLayer;
	}

	public void setDataAccessLayer(DataAccessLayer dataAccessLayer) {
		this.dataAccessLayer = dataAccessLayer;
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