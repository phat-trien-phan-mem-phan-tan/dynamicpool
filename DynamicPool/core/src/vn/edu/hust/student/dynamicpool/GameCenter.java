package vn.edu.hust.student.dynamicpool;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;

public class GameCenter extends ApplicationAdapter {
	private WorldController worldController;
	private WorldRenderer worldRenderer;
	private boolean paused;

	@Override
	public void create() {
		// Thiet lap log debug cho game
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		// init asset
		Assets.instance.init(new AssetManager());
		// Tao the hien cho controller v� render
		worldController = new WorldController();
		worldRenderer = new WorldRenderer(worldController);
	}

	@Override
	public void render() {
		// Thiet lap delta time - thoi gian cap nhat game
		worldController.update(Gdx.graphics.getDeltaTime());
		// Hien thi game ra man hinh
		worldRenderer.render();
	}

	
	@Override
	public void resize(int width, int height) {
		worldRenderer.resize(width, height);
	}	

	@Override
	public void pause() {
		paused = true;
	}

	@Override
	public void resume() {
		paused = false;
	}

	@Override
	public void dispose() {
		worldRenderer.dispose();
	}

}
