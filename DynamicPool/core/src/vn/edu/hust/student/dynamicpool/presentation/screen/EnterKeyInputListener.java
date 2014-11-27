package vn.edu.hust.student.dynamicpool.presentation.screen;

import vn.edu.hust.student.dynamicpool.events.EventDestination;
import vn.edu.hust.student.dynamicpool.events.EventType;

import com.badlogic.gdx.Input.TextInputListener;

public class EnterKeyInputListener implements TextInputListener {

	public EnterKeyInputListener() {
		
	}

	@Override
	public void input(String text) {
		EventDestination.getInstance().dispatchSuccessEventWithObject(
				EventType.PRS_ENTER_KEY, text);
	}

	@Override
	public void canceled() {
		EventDestination.getInstance().dispatchFailEvent(
				EventType.PRS_ENTER_KEY);
	}

}
