package vn.edu.hust.student.dynamicpool;

import vn.edu.hust.student.dynamicpool.presentation.WorldController;
import vn.edu.hust.student.dynamicpool.presentation.WorldRenderer;
import vn.edu.hust.student.dynamicpool.presentation.assets.Assets;
import vn.edu.hust.student.dynamicpool.presentation.screen.SplashScreen;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;

public class GameCenter extends Game {
	private WorldRenderer worldRenderer;
	private boolean paused;
	private WorldController worldController;

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Assets.instance.init(new AssetManager());
		worldRenderer = new WorldRenderer();
		worldController = new WorldController(this);
		worldController.init();
	}

	@Override
	public void render() {
		super.render();
	}
	
	@Override
	public void resize(int width, int height) {
		worldRenderer.resize(width, height);
	}

	@Override
	public void dispose() {
		worldRenderer.dispose();
	}

	public boolean isPaused() {
		return paused;
	}

	public WorldRenderer getWorldRenderer() {
		return worldRenderer;
	}
}
