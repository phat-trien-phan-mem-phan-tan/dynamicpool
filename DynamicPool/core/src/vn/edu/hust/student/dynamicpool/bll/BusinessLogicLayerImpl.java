package vn.edu.hust.student.dynamicpool.bll;

import java.util.ArrayList;
import java.util.List;

import vn.edu.hust.student.dynamicpool.dal.DataAccessLayer;
import vn.edu.hust.student.dynamicpool.dal.DataAccessLayerImpl;
import vn.edu.hust.student.dynamicpool.exception.BLLException;
import vn.edu.hust.student.dynamicpool.model.DeviceInfo;
import vn.edu.hust.student.dynamicpool.model.Pool;
import vn.edu.hust.student.dynamicpool.presentation.PresentationBooleanCallback;

public class BusinessLogicLayerImpl implements BusinessLogicLayer {

	private DataAccessLayer dataAccessLayer;
	private Pool pool;
	private int keyOfHost;

	public BusinessLogicLayerImpl() {
		this.dataAccessLayer = new DataAccessLayerImpl();
		this.pool = new Pool();
	}

	@Override
	public void joinHost(String key, final PresentationBooleanCallback callback) {

		BusinessLogicDataCallback logicDataCallBack = new BusinessLogicDataCallback() {

			@Override
			public void callback(Object data, Exception ex) {
				callback.callback((Boolean) data, ex);

			}
		};

		try {
			int keyJoin = Integer.parseInt(key);

			dataAccessLayer.joinHost(keyJoin, logicDataCallBack);

			// check number format
		} catch (NumberFormatException e) {

			callback.callback(false, e);
			System.out.println(e.getMessage());
			e.printStackTrace();

			throw new NumberFormatException(key);
		}

	}

	@Override
	public void createHost(final PresentationBooleanCallback callback) {

		BusinessLogicDataCallback logicDataCallBack = new BusinessLogicDataCallback() {
			@Override
			public void callback(Object data, Exception ex) {
				creatHostDALCalback(callback, data, ex);
			}
		};
		dataAccessLayer.createHost(logicDataCallBack);
	}

	private void creatHostDALCalback(
			final PresentationBooleanCallback callback, Object data,
			Exception ex) {
		if (ex == null) {
			try {
				int key = Integer.parseInt(data.toString());
				this.keyOfHost = key;
				callback.callback(true, null);
			} catch (Exception castEx) {
				callback.callback(false, new BLLException(
						"Cannot cast key of host", ex));
			}

		} else {
			callback.callback(false, new BLLException("Cannot create host", ex));
		}
	}

	@Override
	public void addDevide(DeviceInfo devideInfor,
			PresentationBooleanCallback callback) {

		BusinessLogicDataCallback logicDataCallBack = new BusinessLogicDataCallback() {

			@Override
			public void callback(Object data, Exception ex) {

			}
		};

		dataAccessLayer.addDevice(devideInfor, logicDataCallBack);

	}

	@Override
	public List<IFish> getFishs() {

		List<IFish> fishes = new ArrayList<IFish>();

		if (pool.getFishCollection() != null) {
			fishes = (List<IFish>) pool.getFishCollection();
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
			public void callback(Object data, Exception ex) {

			}
		};

		this.dataAccessLayer.exit(logicDataCallBack);
	}

	@Override
	public void createFish(FishType fishType, ETrajectoryType trajectoryType,
			int width, int height) {

		final Fish newFish = new Fish();
		newFish.setDx(width / 2);
		newFish.setDy(height / 2);
		newFish.setTrajectoryType(trajectoryType);
		newFish.setFishType(fishType);

		BusinessLogicDataCallback logicDataCallBack = new BusinessLogicDataCallback() {

			@Override
			public void callback(Object data, Exception ex) {

				// check if data is true -> create fish in client
				Boolean check = (Boolean) data;
				if (check) {
					pool.getFishCollection().addFish(newFish);
				}

			}
		};

		// pool.getFishCollection().addFish(newFish);

		this.dataAccessLayer.createFish(newFish, logicDataCallBack);

	}

	@Override
	public void synchronization() {

		BusinessLogicDataCallback logicDataCallBack = new BusinessLogicDataCallback() {

			@Override
			public void callback(Object data, Exception ex) {

				// check if data is true -> create fish in client
				Boolean check = (Boolean) data;
				if (check) {

				}

			}
		};

		this.dataAccessLayer.synchronization(logicDataCallBack);

	}
}
