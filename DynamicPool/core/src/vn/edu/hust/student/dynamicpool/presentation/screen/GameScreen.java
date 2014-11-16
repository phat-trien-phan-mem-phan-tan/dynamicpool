package vn.edu.hust.student.dynamicpool.presentation.screen;

import vn.edu.hust.student.dynamicpool.presentation.WorldController;
import vn.edu.hust.student.dynamicpool.presentation.WorldRenderer;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {

	private WorldRenderer worldRenderer = null;
	private WorldController worldController = null;
	private SpriteBatch batch = null;

	public GameScreen(WorldRenderer worldRenderer,
			WorldController worldController) {
		this.worldRenderer = worldRenderer;
		this.worldController = worldController;
		this.batch = worldRenderer.getBatch();
	}

	@Override
	public void render(float delta) {
		worldRenderer.beginRender();
		
		worldRenderer.endRender();
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
