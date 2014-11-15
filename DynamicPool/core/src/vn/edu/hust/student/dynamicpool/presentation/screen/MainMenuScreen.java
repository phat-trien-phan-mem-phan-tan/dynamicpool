package vn.edu.hust.student.dynamicpool.presentation.screen;

import vn.edu.hust.student.dynamicpool.presentation.WorldRenderer;
import vn.edu.hust.student.dynamicpool.presentation.assets.AssetMainMenu;
import vn.edu.hust.student.dynamicpool.presentation.assets.Assets;
import vn.edu.hust.student.dynamicpool.utils.AppConst;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainMenuScreen implements Screen {
	private WorldRenderer worldRenderer = null;
	private SpriteBatch bath = null;
	AssetMainMenu mainMenuAssets = null;
	
	public MainMenuScreen(WorldRenderer worldRenderer) {
		this.worldRenderer = worldRenderer;
		this.bath = worldRenderer.getBatch();
	}

	@Override
	public void render(float delta) {
		mainMenuAssets = Assets.instance.mainMenu;
		worldRenderer.beginRender();
		renderBackgroundTexture();
		renderButtons();
		worldRenderer.endRender();
	}

	private void renderBackgroundTexture() {
		Texture mainMenuBackgroundTexture = mainMenuAssets.getMainMenuBackgroundTexture();
		bath.draw(mainMenuBackgroundTexture, 0, 0, AppConst.width, AppConst.height);
	}
	
	private void renderButtons() {
		Texture createHostTexture = renderCreateHostButton();
		renderJoinHostButton(createHostTexture);
	}

	public Texture renderCreateHostButton() {
		Texture createHostTexture = mainMenuAssets.getCreateHostTexture();
		int btn1X = AppConst.width /2 - createHostTexture.getWidth()/2;
		int btn1Y = AppConst.height/2 + 30;
		bath.draw(createHostTexture, btn1X, btn1Y);
		return createHostTexture;
	}
	
	public void renderJoinHostButton(Texture createHostTexture) {
		Texture joinHostTexture = mainMenuAssets.getJoinHostTexture();
		int btn2X = AppConst.width /2 - createHostTexture.getWidth()/2;
		int btn2Y = AppConst.height/2 - 30 - joinHostTexture.getHeight();
		bath.draw(joinHostTexture, btn2X, btn2Y);
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
