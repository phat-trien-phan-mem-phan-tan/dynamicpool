package vn.edu.hust.student.dynamicpool.presentation.screen;

import vn.edu.hust.student.dynamicpool.presentation.WorldRenderer;
import vn.edu.hust.student.dynamicpool.presentation.assets.AssetLoadingScreen;
import vn.edu.hust.student.dynamicpool.presentation.assets.Assets;
import vn.edu.hust.student.dynamicpool.utils.AppConst;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LoadingScreen implements Screen {

	private WorldRenderer wordRenderer = null;
	private SpriteBatch batch = null;
	private BitmapFont bitmapFont;
	private float loadingX;
	private float loadingY;

	public LoadingScreen(WorldRenderer worldRenderer) {
		this.wordRenderer = worldRenderer;
		this.batch = worldRenderer.getBatch();
	}

	@Override
	public void render(float delta) {
		wordRenderer.beginRender();
		renderLoadingText();
		wordRenderer.endRender();
	}

	private void renderLoadingText() {
		bitmapFont.draw(batch, AppConst.LOADING_TEXT, loadingX, loadingY);
	}

	@Override
	public void resize(int width, int height) {
		wordRenderer.resize(width, height);
		calculateLoadingTextPosition();
	}

	@Override
	public void show() {
		AssetLoadingScreen loadingAssets = Assets.instance.loadingScreen;
		this.bitmapFont = loadingAssets.getDefaultFont();
		calculateLoadingTextPosition();
	}

	private void calculateLoadingTextPosition() {
		loadingX = AppConst.width/2 - bitmapFont.getBounds(AppConst.LOADING_TEXT).width/2;
		loadingY = AppConst.height/2 + bitmapFont.getBounds(AppConst.LOADING_TEXT).height;
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
