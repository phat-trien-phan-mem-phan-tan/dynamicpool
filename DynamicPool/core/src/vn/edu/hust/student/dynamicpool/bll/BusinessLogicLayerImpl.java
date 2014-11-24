<<<<<<< HEAD
package vn.edu.hust.student.dynamicpool.bll;

import java.util.ArrayList;

import vn.edu.hust.student.dynamicpool.dal.ClientDataAccessLayerImpl;
import vn.edu.hust.student.dynamicpool.dal.DataAccessLayer;
import vn.edu.hust.student.dynamicpool.dal.utils.AppConst;
import vn.edu.hust.student.dynamicpool.equation.vector.Vector;
import vn.edu.hust.student.dynamicpool.exception.BLLException;
import vn.edu.hust.student.dynamicpool.exception.DALException;
import vn.edu.hust.student.dynamicpool.model.DeviceInfo;
import vn.edu.hust.student.dynamicpool.model.Point;
import vn.edu.hust.student.dynamicpool.model.Pool;
import vn.edu.hust.student.dynamicpool.model.Rectangle;
import vn.edu.hust.student.dynamicpool.model.Segment;
import vn.edu.hust.student.dynamicpool.presentation.PresentationBooleanCallback;
import vn.edu.hust.student.dynamicpool.tests.dal.DalTest;

public class BusinessLogicLayerImpl implements BusinessLogicLayer {

	protected DataAccessLayer dataAccessLayer;
	private Pool pool = new Pool();
	private String keyOfHost;

	public BusinessLogicLayerImpl() {
	/*	this.dataAccessLayer = new ClientDataAccessLayerImpl();*/
	 this.dataAccessLayer = new DalTest(); 

	}

	@Override
	public void joinHost(String key, final PresentationBooleanCallback callback) {

		BusinessLogicDataCallback logicDataCallBack = new BusinessLogicDataCallback() {


			@Override
			public void callback(Object data, DALException ex) {
				callback.callback((Boolean) data, ex);

				joinHostCallBack(callback, data, ex);
				
			}
		};
		// tro thanh sua data access layer
		/*
		 * this.dataAccessLayer.joinHost(key, logicDataCallBack);
		 */
	}

	private void joinHostCallBack(final PresentationBooleanCallback callback,
			Object data, Exception ex) {

		if (ex == null) {
			try {
				if (data != null) {
					Boolean joinResult = (Boolean) data;
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

	protected void addDeviceCallback(final PresentationBooleanCallback callback,
			final Object data, final Exception ex) {

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
				// TODO Auto-generated method stub
				
			}
		};

		this.dataAccessLayer.exit(logicDataCallBack);
	}

	@Override
	public void createFish(final FishType fishType,
			final ETrajectoryType trajectoryType, final int width,
			final int height) {

		final Fish newFish = new Fish();
		Rectangle fishPosition = newFish.getPosition();
		fishPosition.setLocation(new Point(AppConst.width / 2,
				AppConst.height / 2));
		fishPosition.setWidth(width);
		fishPosition.setHeight(height);
		newFish.setTrajectoryType(trajectoryType);
		newFish.setFishType(fishType);

		// check trajectory type

		if (trajectoryType == ETrajectoryType.LINE
				|| trajectoryType == ETrajectoryType.NONE) {

			newFish.setAngle((float) (Math.PI / 4));

			LineTrajectory lineTrajectory = new LineTrajectory(fishPosition);
			lineTrajectory.setDirection(new Vector(1, 1));

			newFish.setTrajectory(lineTrajectory);
		} else if (trajectoryType == ETrajectoryType.CYCLE) {

			newFish.setAngle(0);

			CycleTrajectory cycleTrajectory = new CycleTrajectory(fishPosition);

			cycleTrajectory.setX0(AppConst.width/2);
			cycleTrajectory.setY0(AppConst.height/2);
			
			newFish.setTrajectory(cycleTrajectory);
			// set location for fish
			Point newPoint = cycleTrajectory.updateCoordinate(0).getLocation();
			fishPosition.setLocation(newPoint);

		} else if (trajectoryType == ETrajectoryType.SIN) {

			SinTrajectory sinTrajectory = new SinTrajectory(fishPosition);

			sinTrajectory.setX0(AppConst.width / 2);
			sinTrajectory.setY0(AppConst.height / 2);

			newFish.setTrajectory(sinTrajectory);

		}

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
	public void synchronization() {

		BusinessLogicDataCallback logicDataCallBack = new BusinessLogicDataCallback() {


			@Override
			public void callback(Object data, DALException ex) {
				
			}
		};

		this.dataAccessLayer.synchronization(logicDataCallBack);

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

	public String getKeyOfHost() {
		return keyOfHost;
	}

	public void saveKey(String keyOfHost) {
		this.keyOfHost = keyOfHost;
	}

}
=======
package vn.edu.hust.student.dynamicpool.bll;

import java.util.ArrayList;

import vn.edu.hust.student.dynamicpool.dal.ClientDataAccessLayerImpl;
import vn.edu.hust.student.dynamicpool.dal.DataAccessLayer;
import vn.edu.hust.student.dynamicpool.dal.utils.AppConst;
import vn.edu.hust.student.dynamicpool.equation.vector.Vector;
import vn.edu.hust.student.dynamicpool.exception.BLLException;
import vn.edu.hust.student.dynamicpool.exception.DALException;
import vn.edu.hust.student.dynamicpool.model.DeviceInfo;
import vn.edu.hust.student.dynamicpool.model.Point;
import vn.edu.hust.student.dynamicpool.model.Pool;
import vn.edu.hust.student.dynamicpool.model.Rectangle;
import vn.edu.hust.student.dynamicpool.model.Segment;
import vn.edu.hust.student.dynamicpool.presentation.PresentationBooleanCallback;
import vn.edu.hust.student.dynamicpool.tests.dal.DalTest;

public class BusinessLogicLayerImpl implements BusinessLogicLayer {

	protected DataAccessLayer dataAccessLayer;
	private Pool pool = new Pool();
	private String keyOfHost;

	public BusinessLogicLayerImpl() {
	/*	this.dataAccessLayer = new ClientDataAccessLayerImpl();*/
	 this.dataAccessLayer = new DalTest(); 

	}

	@Override
	public void joinHost(String key, final PresentationBooleanCallback callback) {

		BusinessLogicDataCallback logicDataCallBack = new BusinessLogicDataCallback() {


			@Override
			public void callback(Object data, DALException ex) {
				callback.callback((Boolean) data, ex);

				joinHostCallBack(callback, data, ex);
				
			}
		};
		// tro thanh sua data access layer
		/*
		 * this.dataAccessLayer.joinHost(key, logicDataCallBack);
		 */
	}

	private void joinHostCallBack(final PresentationBooleanCallback callback,
			Object data, Exception ex) {

		if (ex == null) {
			try {
				if (data != null) {
					Boolean joinResult = (Boolean) data;
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

	protected void addDeviceCallback(final PresentationBooleanCallback callback,
			final Object data, final Exception ex) {

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
				// TODO Auto-generated method stub
				
			}
		};

		this.dataAccessLayer.exit(logicDataCallBack);
	}

	@Override
	public void createFish(final FishType fishType,
			final ETrajectoryType trajectoryType, final int width,
			final int height) {

		final Fish newFish = new Fish();
		Rectangle fishPosition = newFish.getPosition();
		fishPosition.setLocation(new Point(AppConst.width / 2,
				AppConst.height / 2));
		fishPosition.setWidth(width);
		fishPosition.setHeight(height);
		newFish.setTrajectoryType(trajectoryType);
		newFish.setFishType(fishType);

		// check trajectory type

		if (trajectoryType == ETrajectoryType.LINE
				|| trajectoryType == ETrajectoryType.NONE) {

			newFish.setAngle((float) (Math.PI / 4));

			LineTrajectory lineTrajectory = new LineTrajectory(fishPosition);
			lineTrajectory.setDirection(new Vector(1, 1));

			newFish.setTrajectory(lineTrajectory);
		} else if (trajectoryType == ETrajectoryType.CYCLE) {

			newFish.setAngle(0);

			CycleTrajectory cycleTrajectory = new CycleTrajectory(fishPosition);

			cycleTrajectory.setX0(AppConst.width/2);
			cycleTrajectory.setY0(AppConst.height/2);
			
			newFish.setTrajectory(cycleTrajectory);
			// set location for fish
			Point newPoint = cycleTrajectory.updateCoordinate(0).getLocation();
			fishPosition.setLocation(newPoint);

		} else if (trajectoryType == ETrajectoryType.SIN) {

			SinTrajectory sinTrajectory = new SinTrajectory(fishPosition);

			sinTrajectory.setX0(AppConst.width / 2);
			sinTrajectory.setY0(AppConst.height / 2);

			newFish.setTrajectory(sinTrajectory);

		}

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
	public void synchronization() {

		BusinessLogicDataCallback logicDataCallBack = new BusinessLogicDataCallback() {


			@Override
			public void callback(Object data, DALException ex) {
				
			}
		};

		this.dataAccessLayer.synchronization(logicDataCallBack);

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

	public String getKeyOfHost() {
		return keyOfHost;
	}

	public void saveKey(String keyOfHost) {
		this.keyOfHost = keyOfHost;
	}

}
>>>>>>> 4aa3cd03695b2a746545fbff4eb38d3feeb685ee
