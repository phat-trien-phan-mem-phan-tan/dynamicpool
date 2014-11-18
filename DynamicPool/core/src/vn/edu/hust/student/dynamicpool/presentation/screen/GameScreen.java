package vn.edu.hust.student.dynamicpool.presentation.screen;

import vn.edu.hust.student.dynamicpool.presentation.WorldController;
import vn.edu.hust.student.dynamicpool.presentation.WorldRenderer;
import vn.edu.hust.student.dynamicpool.presentation.assets.AssetGameScreen;
import vn.edu.hust.student.dynamicpool.presentation.assets.Assets;
import vn.edu.hust.student.dynamicpool.presentation.gameobject.FishUICollection;
import vn.edu.hust.student.dynamicpool.presentation.gameobject.GameBackgroundUI;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameScreen implements Screen {

	private WorldRenderer worldRenderer = null;
	private WorldController worldController = null;
	private SpriteBatch batch = null;
	private GameBackgroundUI gameBackground = new GameBackgroundUI();
	private FishUICollection fishUICollection = null;

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
		renderFishsUI(delta);
		worldRenderer.endRender();
	}

	private void renderGameBackground() {
		gameBackground.render(batch);
	}
	
	private void renderFishsUI(float deltaTime) {
		
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
