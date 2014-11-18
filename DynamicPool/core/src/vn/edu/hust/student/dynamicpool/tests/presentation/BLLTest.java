package vn.edu.hust.student.dynamicpool.tests.presentation;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import vn.edu.hust.student.dynamicpool.bll.BusinessLogicLayer;
import vn.edu.hust.student.dynamicpool.bll.IFish;
import vn.edu.hust.student.dynamicpool.model.DevideInfor;
import vn.edu.hust.student.dynamicpool.presentation.PresentationBooleanCallback;

public class BLLTest implements BusinessLogicLayer {
	private Timer timmer = new Timer();

	@Override
	public void joinHost(String key, PresentationBooleanCallback callback) {
		final PresentationBooleanCallback newcallback = callback;
		this.timmer.scheduleTask(new Task() {
			@Override
			public void run() {
				newcallback.callback(true, null);
			}
		}, 1);
	}

	@Override
	public void createHost(PresentationBooleanCallback callback) {
		final PresentationBooleanCallback newcallback = callback;
		this.timmer.scheduleTask(new Task() {
			@Override
			public void run() {
				newcallback.callback(true, null);
			}
		}, 1);
	}

	@Override
	public void intialDevide(DevideInfor devideInfor,
			PresentationBooleanCallback callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addDevide(DevideInfor devideInfor,
			PresentationBooleanCallback callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public java.util.List<IFish> getFishs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		
	}
}