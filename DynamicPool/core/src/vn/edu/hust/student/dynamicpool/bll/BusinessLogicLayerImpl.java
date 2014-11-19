package vn.edu.hust.student.dynamicpool.bll;

import java.util.ArrayList;
import java.util.List;

import vn.edu.hust.student.dynamicpool.dal.DataAccessLayer;
import vn.edu.hust.student.dynamicpool.dal.DataAccessLayerImpl;
import vn.edu.hust.student.dynamicpool.model.DeviceInfo;
import vn.edu.hust.student.dynamicpool.model.Pool;
import vn.edu.hust.student.dynamicpool.presentation.PresentationBooleanCallback;

public class BusinessLogicLayerImpl implements BusinessLogicLayer {

	private DataAccessLayer dataAccessLayer;
	private Pool pool;

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
				// TODO Auto-generated method stub
				callback.callback((Boolean) data, ex);

			}
		};

		dataAccessLayer.createHost(logicDataCallBack);

	}

	@Override
	public void intialDevide(DeviceInfo devideInfor,
			PresentationBooleanCallback callback) {

		BusinessLogicDataCallback logicDataCallBack = new BusinessLogicDataCallback() {

			@Override
			public void callback(Object data, Exception ex) {
				// TODO Auto-generated method stub

			}
		};

		dataAccessLayer.intialDevide(devideInfor, logicDataCallBack);

	}

	@Override
	public void addDevide(DeviceInfo devideInfor,
			PresentationBooleanCallback callback) {

		BusinessLogicDataCallback logicDataCallBack = new BusinessLogicDataCallback() {

			@Override
			public void callback(Object data, Exception ex) {

			}
		};

		dataAccessLayer.addDevide(devideInfor, logicDataCallBack);

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
	}

	@Override
	public void createFish(FishType fishType, ETrajectoryType trajectoryType,
			int width, int height) {

		
		Fish newFish = new Fish();
		newFish.setDx(width/2);
		newFish.setDy(height/2);
		newFish.setTrajectoryType(trajectoryType);
		newFish.setFishType(fishType);
		
		pool.getFishCollection().addFish(newFish);
		// call data access  layer
		
	}
}
