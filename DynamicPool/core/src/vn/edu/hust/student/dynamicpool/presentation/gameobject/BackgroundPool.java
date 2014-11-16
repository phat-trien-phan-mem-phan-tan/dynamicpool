package vn.edu.hust.student.dynamicpool.presentation.gameobject;

import vn.edu.hust.student.dynamicpool.presentation.assets.Assets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BackgroundPool {
	private TextureRegion backgroundRegion;
	public BackgroundPool() {
		init();
	}
	
	private void init() {
		backgroundRegion = Assets.instance.gameScreen.getGameBackgroundRegion();
	}
	public void render(SpriteBatch batch) {
		batch.draw(backgroundRegion, 0f, 0f);
	}
}
