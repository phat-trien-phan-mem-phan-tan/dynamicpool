package vn.edu.hust.student.dynamicpool.presentation.screen;

import java.util.List;

import vn.edu.hust.student.dynamicpool.bll.IFish;
import vn.edu.hust.student.dynamicpool.presentation.WorldController;
import vn.edu.hust.student.dynamicpool.presentation.WorldRenderer;
import vn.edu.hust.student.dynamicpool.presentation.assets.AssetGameScreen;
import vn.edu.hust.student.dynamicpool.presentation.assets.Assets;
import vn.edu.hust.student.dynamicpool.presentation.gameobject.FishUI;
import vn.edu.hust.student.dynamicpool.presentation.gameobject.FishUICollection;
import vn.edu.hust.student.dynamicpool.presentation.gameobject.GameBackgroundUI;
import vn.edu.hust.student.dynamicpool.utils.AppConst;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen, InputProcessor {

	private WorldRenderer worldRenderer = null;
	private WorldController worldController = null;
	private SpriteBatch batch = null;
	private GameBackgroundUI gameBackground = new GameBackgroundUI();
	private FishUICollection fishUICollection = null;
	private Texture exitButtonTexture;
	private Texture fish1ButtonTexture;

	public GameScreen(WorldRenderer worldRenderer,
			WorldController worldController) {
		this.worldRenderer = worldRenderer;
		this.worldController = worldController;
		this.batch = worldRenderer.getBatch();
		this.fishUICollection = worldController.getFishUICollection();
	}

	@Override
	public void render(float delta) {
		worldRenderer.beginRender();
		renderGameBackground();
		renderFishsUIAndUpdate(delta);
		renderHubControl();
		worldRenderer.endRender();
	}

	private void renderGameBackground() {
		gameBackground.render(batch);
	}

	private void renderFishsUIAndUpdate(float deltaTime) {
		List<IFish> fishs = worldController.getFishs();
		for (IFish fish : fishs) {
			renderAFishUI(fish);
		}
		worldController.updateFishsCordinate(deltaTime);
	}

	private void renderAFishUI(IFish fish) {
		FishUI fishUI = fishUICollection.getFishUI(fish);
		fishUI.render(batch);
	}

	private void renderHubControl() {
		batch.draw(exitButtonTexture, 10, AppConst.height - 10
				- AppConst.HUB_BUTTON_HEIGHT, AppConst.HUB_BUTTON_WIDTH,
				AppConst.HUB_BUTTON_HEIGHT);
		batch.draw(fish1ButtonTexture, 20 + AppConst.HUB_BUTTON_WIDTH,
				AppConst.height - 10 - AppConst.HUB_BUTTON_HEIGHT,
				AppConst.HUB_BUTTON_WIDTH, AppConst.HUB_BUTTON_HEIGHT);
	}

	@Override
	public void resize(int width, int height) {
		worldRenderer.resize(width, height);
	}

	@Override
	public void show() {
		AssetGameScreen gameAssets = Assets.instance.gameScreen;
		this.exitButtonTexture = gameAssets.getExitButtonTexture();
		this.fish1ButtonTexture = gameAssets.getFish1ButtonTexture();
		Gdx.input.setInputProcessor(this);
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

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (isClickOnExitBtn(screenX, screenY))
			worldController.exit();
		if (isTouchOnCreateFishOneBtn(screenX, screenY))
			worldController.createFish1();
		return true;
	}

	private boolean isTouchOnCreateFishOneBtn(int screenX, int screenY) {
		return screenX > 20 + AppConst.HUB_BUTTON_WIDTH
				&& screenX < 20 + 2 * AppConst.HUB_BUTTON_WIDTH
				&& screenY > AppConst.HUB_BUTTON_HEIGHT + 20
				&& screenY < 20 + 2 * AppConst.HUB_BUTTON_HEIGHT;
	}

	private boolean isClickOnExitBtn(int screenX, int screenY) {
		return screenX > 10 && screenX < 10 + AppConst.HUB_BUTTON_WIDTH
				&& screenY > 10 && screenY < 10 + AppConst.HUB_BUTTON_HEIGHT;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
