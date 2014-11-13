package vn.edu.hust.student.dynamicpool;

import java.util.List;

import vn.edu.hust.student.dynamicpool.gameobject.FishUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class WorldRenderer implements Disposable {
	// private OrthographicCamera camera;
	private SpriteBatch batch;
	private WorldController worldController;
	private OrthographicCamera camera;

	public WorldRenderer(WorldController worldController) {
		this.worldController = worldController;
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

	public void render() {
		clearBackground();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		renderBackground();
		renderFish();
		batch.end();
	}

	private void renderFish() {
		List<FishUI> fishUIs = worldController.getFishs();
		for (FishUI fishUI : fishUIs) {
			fishUI.render(batch);
		}
	}

	private void renderBackground() {
		worldController.background.render(batch);
	}

	private void clearBackground() {
		// Thiet lap nen man hinh hien thi - Mau xanh da troi chuan RGBA
		Gdx.gl.glClearColor(0x64 / 255.0f, 0x95 / 255.0f, 0xed / 255.0f,
				0xff / 255.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	public void resize(int width, int height) {
		camera.setToOrtho(false, width, height);
		camera.update();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

}
