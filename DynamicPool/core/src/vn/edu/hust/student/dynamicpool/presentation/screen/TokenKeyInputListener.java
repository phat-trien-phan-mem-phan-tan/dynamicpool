package vn.edu.hust.student.dynamicpool.presentation.screen;

import com.badlogic.gdx.Input.TextInputListener;

public class TokenKeyInputListener implements TextInputListener {

	private MyStringCallback joinHostInputCallback;

	public TokenKeyInputListener(MyStringCallback joinHostInputCallback) {
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
