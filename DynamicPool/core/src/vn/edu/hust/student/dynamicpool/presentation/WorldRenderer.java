package vn.edu.hust.student.dynamicpool.presentation;

import vn.edu.hust.student.dynamicpool.utils.AppConst;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class WorldRenderer implements Disposable {
	// private OrthographicCamera camera;
	private SpriteBatch batch;
	private OrthographicCamera camera;

	public WorldRenderer() {
		init();
	}

	private void init() {
		initBatch();
		initCamera();
	}

	private void initBatch() {
		batch = new SpriteBatch();
	}

	private void initCamera() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		camera.update();
	}
	
	public void endRender() {
		batch.end();
	}

	public void beginRender() {
		clearBackground();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
	}

	public void clearBackground() {
		// Thiet lap nen man hinh hien thi - Mau xanh da troi chuan RGBA
		Gdx.gl.glClearColor(0x64 / 255.0f, 0x95 / 255.0f, 0xed / 255.0f,
				0xff / 255.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	public void resize(int width, int height) {
		AppConst.width = width;
		AppConst.height = height;
		camera.setToOrtho(false, width, height);
		camera.update();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

}
