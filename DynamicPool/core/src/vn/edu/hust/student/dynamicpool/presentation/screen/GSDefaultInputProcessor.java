package vn.edu.hust.student.dynamicpool.presentation.screen;

import vn.edu.hust.student.dynamicpool.presentation.WorldController;
import vn.edu.hust.student.dynamicpool.utils.AppConst;

import com.badlogic.gdx.InputProcessor;

public class GSDefaultInputProcessor implements InputProcessor {

	protected WorldController worldController;

	public GSDefaultInputProcessor(WorldController worldController) {
		this.worldController = worldController;
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (isClickOnExitButton(screenX, screenY))
			worldController.exit();
		if (isClickOnAddFishButton(screenX, screenY))
			worldController.addFishButtonClick();
		return false;
	}

	private boolean isClickOnAddFishButton(int screenX, int screenY) {
		return screenX >= 64 && screenX < 128 && screenY > AppConst.height - 64 && screenY <= AppConst.height;
	}

	private boolean isClickOnExitButton(int screenX, int screenY) {
		return screenX >= 0 && screenX < 64 && screenY > AppConst.height-64 && screenY <= AppConst.height;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
