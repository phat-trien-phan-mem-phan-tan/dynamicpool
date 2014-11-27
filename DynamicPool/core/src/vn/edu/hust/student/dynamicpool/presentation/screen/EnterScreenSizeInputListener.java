package vn.edu.hust.student.dynamicpool.presentation.screen;

import vn.edu.hust.student.dynamicpool.events.EventDestination;
import vn.edu.hust.student.dynamicpool.events.EventType;

import com.badlogic.gdx.Input.TextInputListener;

public class EnterScreenSizeInputListener implements TextInputListener {

	public EnterScreenSizeInputListener() {
		
	}

	@Override
	public void input(String text) {
		EventDestination.getInstance().dispatchSuccessEventWithObject(
				EventType.PRS_ENTER_SCREEN_SIZE, text);
	}

	@Override
	public void canceled() {
		EventDestination.getInstance().dispatchFailEvent(
				EventType.PRS_ENTER_SCREEN_SIZE);
	}

}
