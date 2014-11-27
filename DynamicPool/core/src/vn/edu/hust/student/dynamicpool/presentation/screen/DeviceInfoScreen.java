package vn.edu.hust.student.dynamicpool.presentation.screen;

import vn.edu.hust.student.dynamicpool.presentation.WorldController;
import vn.edu.hust.student.dynamicpool.presentation.WorldRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class DeviceInfoScreen implements Screen {

	private WorldRenderer worldRenderer;
	private WorldController worldController;

	public DeviceInfoScreen(WorldRenderer worldRenderer,
			WorldController worldController) {
		this.worldRenderer = worldRenderer;
		this.worldController = worldController;
	}

	protected void showTextInputWithRequireMessage() {
		Gdx.input.getTextInput(new StringKeyInputListener(), "You must enter your screen size (by inch)", "0");
	}
	
	private void enterScreenSizeCallbakHander(String text) {
		if (!worldController.saveScreenSizeByInch(text)) showTextInputInvalidMessage();
	}
	
	private void showTextInputInvalidMessage() {
		Gdx.input.getTextInput(new StringKeyInputListener(), "You must enter a valid number for screen size (by inch [0, 30])", "0");
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
		Gdx.input.getTextInput(new StringKeyInputListener(), "Please enter your screen size (by inch)", "0");
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
