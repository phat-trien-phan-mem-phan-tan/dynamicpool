package vn.edu.hust.student.dynamicpool.presentation.screen;

import vn.edu.hust.student.dynamicpool.presentation.PresentationStringCallback;
import vn.edu.hust.student.dynamicpool.presentation.WorldController;
import vn.edu.hust.student.dynamicpool.presentation.WorldRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class DeviceInfoScreen implements Screen {

	private WorldRenderer worldRenderer;
	private WorldController worldController;
	PresentationStringCallback successCallback = new PresentationStringCallback() {
		@Override
		public void callback(String text) {
			enterScreenSizeCallbakHander(text);
		}
	};
	PresentationStringCallback cancelCallback = new PresentationStringCallback() {
		@Override
		public void callback(String text) {
			showTextInputWithRequireMessage();
		}
	};

	public DeviceInfoScreen(WorldRenderer worldRenderer,
			WorldController worldController) {
		this.worldRenderer = worldRenderer;
		this.worldController = worldController;
	}

	protected void showTextInputWithRequireMessage() {
		Gdx.input.getTextInput(new StringKeyInputListener(successCallback, cancelCallback), "You must enter your screen size (by inch)", "0");
	}
	
	private void enterScreenSizeCallbakHander(String text) {
		if (!worldController.saveScreenSizeByInch(text)) showTextInputInvalidMessage();
	}
	
	private void showTextInputInvalidMessage() {
		Gdx.input.getTextInput(new StringKeyInputListener(successCallback, cancelCallback), "You must enter a valid number for screen size (by inch [0, 30])", "0");
	}

	@Override
	public void render(float delta) {
		worldRenderer.beginRender();
		
		worldRenderer.endRender();
	}

	@Override
	public void resize(int width, int height) {
		worldRenderer.resize(width, height);
	}

	
	@Override
	public void show() {
		Gdx.input.getTextInput(new StringKeyInputListener(successCallback, cancelCallback), "Please enter your screen size (by inch)", "0");
	}


	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

}
