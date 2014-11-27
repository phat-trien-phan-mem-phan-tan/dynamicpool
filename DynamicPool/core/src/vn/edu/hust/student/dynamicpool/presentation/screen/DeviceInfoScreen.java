package vn.edu.hust.student.dynamicpool.presentation.screen;

import vn.edu.hust.student.dynamicpool.events.EventDestination;
import vn.edu.hust.student.dynamicpool.events.EventType;
import vn.edu.hust.student.dynamicpool.presentation.WorldController;
import vn.edu.hust.student.dynamicpool.presentation.WorldRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.eposi.eventdriven.Event;
import com.eposi.eventdriven.implementors.BaseEventListener;

public class DeviceInfoScreen implements Screen {

	private WorldRenderer worldRenderer;
	private WorldController worldController;

	public DeviceInfoScreen(WorldRenderer worldRenderer,
			WorldController worldController) {
		this.worldRenderer = worldRenderer;
		this.worldController = worldController;
		registerEventListener();
	}

	private void registerEventListener() {
		EventDestination.getInstance().addEventListener(
				EventType.PRS_ENTER_SCREEN_SIZE,
				new BaseEventListener(this, "onEnterScreenSizeCallbackHander"));
	}
	
	public void onEnterScreenSizeCallbackHander(Event event) {
		if (EventDestination.parseEventToBoolean(event)) {
			String text = EventDestination.parseEventToTargetObject(event).toString();
			enterScreenSizeCallbakHander(text);
		} else {
			showTextInputWithRequireMessage();
		}
	}

	private void showTextInputWithRequireMessage() {
		Gdx.input.getTextInput(new EnterScreenSizeInputListener(), "You must enter your screen size (by inch)", "0");
	}
	
	private void enterScreenSizeCallbakHander(String text) {
		if (!worldController.saveScreenSizeByInch(text)) showTextInputInvalidMessage();
	}
	
	private void showTextInputInvalidMessage() {
		Gdx.input.getTextInput(new EnterScreenSizeInputListener(), "You must enter a valid number for screen size (by inch [0, 30])", "0");
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
		Gdx.input.getTextInput(new EnterScreenSizeInputListener(), "Please enter your screen size (by inch)", "0");
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
