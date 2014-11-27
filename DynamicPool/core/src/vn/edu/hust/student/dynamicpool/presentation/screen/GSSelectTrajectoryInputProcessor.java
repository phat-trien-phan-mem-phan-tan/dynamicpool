package vn.edu.hust.student.dynamicpool.presentation.screen;

import vn.edu.hust.student.dynamicpool.bll.model.ETrajectoryType;
import vn.edu.hust.student.dynamicpool.presentation.WorldController;

public class GSSelectTrajectoryInputProcessor extends GSDefaultInputProcessor {

	public GSSelectTrajectoryInputProcessor(WorldController worldController) {
		super(worldController);
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		super.touchUp(screenX, screenY, pointer, button);
		if (isClickOnSelectTrajectory(screenX, screenY)) {
			if (screenX < 100) worldController.selectTrajectory(ETrajectoryType.LINE);
			if (screenX >= 100 && screenX < 200) worldController.selectTrajectory(ETrajectoryType.CYCLE);
			if (screenX >= 200) worldController.selectTrajectory(ETrajectoryType.SIN);
		}
		return false;
	}

	private boolean isClickOnSelectTrajectory(int screenX, int screenY) {
		return screenX >= 0 && screenX < 300 && screenY >= 0 && screenY <= 100;
	}
}
