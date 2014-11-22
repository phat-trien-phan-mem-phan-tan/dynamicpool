package vn.edu.hust.student.dynamicpool.tests.presentation;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import vn.edu.hust.student.dynamicpool.bll.BusinessLogicLayer;
import vn.edu.hust.student.dynamicpool.bll.ETrajectoryType;
import vn.edu.hust.student.dynamicpool.bll.FishFactory;
import vn.edu.hust.student.dynamicpool.bll.FishType;
import vn.edu.hust.student.dynamicpool.bll.IFish;
import vn.edu.hust.student.dynamicpool.dal.utils.AppConst;
import vn.edu.hust.student.dynamicpool.model.DeviceInfo;
import vn.edu.hust.student.dynamicpool.presentation.PresentationBooleanCallback;

public class BLLTest implements BusinessLogicLayer {
	private Timer timmer = new Timer();
	private List<IFish> fishs = new ArrayList<IFish>();
	
	public BLLTest() {
		fishs.add(FishFactory.createFishWithLineTrajectory(AppConst.width, AppConst.height));
	}
	@Override
	public void joinHost(String key, PresentationBooleanCallback callback) {
		final PresentationBooleanCallback newcallback = callback;
		this.timmer.scheduleTask(new Task() {
			@Override
			public void run() {
				newcallback.callback(true, null);
			}
		}, 3);
	}

	@Override
	public void createHost(PresentationBooleanCallback callback) {
		final PresentationBooleanCallback newcallback = callback;
		this.timmer.scheduleTask(new Task() {
			@Override
			public void run() {
				newcallback.callback(true, null);
			}
		}, 3);
	}

	@Override
	public void addDevide(DeviceInfo devideInfor,
			final PresentationBooleanCallback callback) {
		timmer.scheduleTask(new Task() {
			@Override
			public void run() {
				callback.callback(true, null);
			}
		}, 2);
		
	}

	@Override
	public java.util.List<IFish> getFishs() {
		return fishs;
	}

	@Override
	public void update(float deltaTime) {
		for (IFish fish : fishs) {
			fish.update(deltaTime);
		}
	}
	@Override
	public void exit() {
		
	}
	@Override
	public void createFish(FishType fishType, ETrajectoryType trajectoryType,
			int width, int height) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void synchronization() {
		// TODO Auto-generated method stub
		
	}
}