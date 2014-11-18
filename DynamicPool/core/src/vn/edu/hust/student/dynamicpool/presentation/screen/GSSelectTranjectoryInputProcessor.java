package vn.edu.hust.student.dynamicpool.presentation.screen;

import vn.edu.hust.student.dynamicpool.bll.ETrajectoryType;
import vn.edu.hust.student.dynamicpool.presentation.WorldController;

public class GSSelectTranjectoryInputProcessor extends GSDefaultInputProcessor {

	public GSSelectTranjectoryInputProcessor(WorldController worldController) {
		super(worldController);
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		super.touchUp(screenX, screenY, pointer, button);
		if (isClickOnSelectTranjectory(screenX, screenY)) {
			if (screenX < 100) worldController.selectTranjectory(ETrajectoryType.LINE);
			if (screenX >= 100 && screenX < 200) worldController.selectTranjectory(ETrajectoryType.CYCLE);
			if (screenX >= 200) worldController.selectTranjectory(ETrajectoryType.SIN);
		}
		return false;
	}

	private boolean isClickOnSelectTranjectory(int screenX, int screenY) {
		return screenX >= 0 && screenX < 300 && screenY >= 0 && screenY <= 100;
	}
}
