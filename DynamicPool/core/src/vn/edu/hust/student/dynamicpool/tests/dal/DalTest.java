package vn.edu.hust.student.dynamicpool.tests.dal;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import vn.edu.hust.student.dynamicpool.bll.BusinessLogicDataCallback;
import vn.edu.hust.student.dynamicpool.bll.Fish;
import vn.edu.hust.student.dynamicpool.bll.FishManager;
import vn.edu.hust.student.dynamicpool.dal.DataAccessLayer;
import vn.edu.hust.student.dynamicpool.model.DeviceInfo;

public class DalTest implements DataAccessLayer {

	private Timer timer = new Timer();

	@Override
	public void joinHost(int key, BusinessLogicDataCallback callback) {

		final BusinessLogicDataCallback newCallback = callback;

		timer.scheduleTask(new Task() {

			@Override
			public void run() {
				newCallback.callback("0000", null);

			}
		}, 1);
	}

	@Override
	public void createHost(BusinessLogicDataCallback callback) {
		final BusinessLogicDataCallback newCallback = callback;

		timer.scheduleTask(new Task() {

			@Override
			public void run() {
				newCallback.callback("0000", null);

			}
		}, 1);

	}

	@Override
	public void addDevice(DeviceInfo deviceInfo,
			BusinessLogicDataCallback callback) {

		final BusinessLogicDataCallback newCallback = callback;

		timer.scheduleTask(new Task() {

			@Override
			public void run() {
				newCallback.callback(new Boolean(true), null);
			}
		}, 1);

	}

	@Override
	public void exit(BusinessLogicDataCallback callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createFish(Fish fish, BusinessLogicDataCallback callback) {

		final BusinessLogicDataCallback newCallback = callback;

		timer.scheduleTask(new Task() {

			@Override
			public void run() {
				newCallback.callback(new Boolean(true), null);
			}
		}, 1);

	}

	@Override
	public void synchronization(BusinessLogicDataCallback callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeFish(Fish fish, BusinessLogicDataCallback callback) {

		final BusinessLogicDataCallback newCallback = callback;

		timer.scheduleTask(new Task() {

			@Override
			public void run() {
				newCallback.callback(new Boolean(true), null);
			}
		}, 1);
	}

	@Override
	public String getClientName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void synchronous(FishManager fishManager, String clientName) {
		// TODO Auto-generated method stub
		
	}

}
