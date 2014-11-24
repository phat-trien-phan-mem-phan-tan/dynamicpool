package vn.edu.hust.student.dynamicpool.presentation.screen;

import vn.edu.hust.student.dynamicpool.presentation.PresentationStringCallback;

import com.badlogic.gdx.Input.TextInputListener;

public class StringKeyInputListener implements TextInputListener {

	private PresentationStringCallback successCallback;
	private PresentationStringCallback cancelCallback;

	public StringKeyInputListener(PresentationStringCallback joinHostInputCallback) {
		this.successCallback = joinHostInputCallback;
	}

	public StringKeyInputListener(PresentationStringCallback successCallback,
			PresentationStringCallback cancelCallback) {
		this.successCallback = successCallback;
		this.cancelCallback = cancelCallback;
	}

	@Override
	public void input(String text) {
		successCallback.callback(text);
	}

	@Override
	public void canceled() {
		cancelCallback.callback(new String());
	}

}
