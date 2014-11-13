package vn.edu.hust.student.dynamicpool;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BackgroundPool {
	private TextureRegion backgroundRegion;
	public BackgroundPool() {
		init();
	}
	
	private void init() {
		backgroundRegion = Assets.instance.background.mainBackgroundRegion;
	}
	public void render(SpriteBatch batch) {
		batch.draw(backgroundRegion, 0f, 0f);
	}
}
