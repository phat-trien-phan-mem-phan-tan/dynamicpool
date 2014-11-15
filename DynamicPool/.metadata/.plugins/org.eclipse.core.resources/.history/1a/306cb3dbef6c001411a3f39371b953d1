package vn.edu.hust.student.dynamicpool.presentation.screen;

import vn.edu.hust.student.dynamicpool.presentation.WorldRenderer;
import vn.edu.hust.student.dynamicpool.presentation.assets.Assets;
import vn.edu.hust.student.dynamicpool.utils.AppConst;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SplashScreen implements Screen {
	private WorldRenderer worldRenderer = null;
	private SpriteBatch bath = null;
	public SplashScreen(WorldRenderer worldRenderer) {
		this.worldRenderer  = worldRenderer;
		this.bath = worldRenderer.getBatch();
	}

	@Override
	public void render(float delta) {
		worldRenderer.beginRender();
		renderSplashScreenTexture();
		worldRenderer.endRender();
	}

	private void renderSplashScreenTexture() {
		Texture splashScreenTexture = Assets.instance.splashScreen.getSplashScreenTexture();
		bath.draw(splashScreenTexture, 0, 0, AppConst.width, AppConst.height);
	}

	@Override
	public void resize(int width, int height) {
		worldRenderer.resize(width, height);
	}

	@Override
	public void show() {

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
