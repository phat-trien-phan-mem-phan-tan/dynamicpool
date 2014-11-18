package vn.edu.hust.student.dynamicpool.presentation.screen;

import vn.edu.hust.student.dynamicpool.bll.FishType;
import vn.edu.hust.student.dynamicpool.presentation.WorldController;

public class GSSelectFishInputProcessor extends GSDefaultInputProcessor {
	
	public GSSelectFishInputProcessor(WorldController worldController) {
		super(worldController);
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		super.touchUp(screenX, screenY, pointer, button);
		if (isClickOnSelectFish(screenX, screenY)) {
			if (screenX < 55) worldController.selectFish(FishType.FISH1);
			if (screenX >= 55 && screenX < 105) worldController.selectFish(FishType.FISH2);
			if (screenX >= 105 && screenX < 145) worldController.selectFish(FishType.FISH3);
			if (screenX >= 145 && screenX < 205) worldController.selectFish(FishType.FISH4);
			if (screenX >= 205 && screenX < 295) worldController.selectFish(FishType.FISH6);
			if (screenX >= 295 && screenX < 350) worldController.selectFish(FishType.FISH7);
			if (screenX >= 350 && screenX < 395) worldController.selectFish(FishType.FISH8);
			if (screenX >= 395 && screenX < 440) worldController.selectFish(FishType.FISH9);
			if (screenX >= 440) worldController.selectFish(FishType.FISH10);
		} else {
			worldController.cancelAddFish();
		}
		return false;
	}

	private boolean isClickOnSelectFish(int screenX, int screenY) {
		return screenY >= 0 && screenY < 100 && screenX >= 0 && screenX < 480;
	}
}
