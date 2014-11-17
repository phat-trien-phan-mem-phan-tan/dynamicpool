package vn.edu.hust.student.dynamicpool.presentation.screen;

import vn.edu.hust.student.dynamicpool.presentation.PresentationStringCallback;

import com.badlogic.gdx.Input.TextInputListener;

public class TokenKeyInputListener implements TextInputListener {

	private PresentationStringCallback joinHostInputCallback;

	public TokenKeyInputListener(PresentationStringCallback joinHostInputCallback) {
		this.joinHostInputCallback = joinHostInputCallback;
	}

	@Override
	public void input(String text) {
		joinHostInputCallback.callback(text);
	}

	@Override
	public void canceled() {
		// TODO Auto-generated method stub

	}

}
