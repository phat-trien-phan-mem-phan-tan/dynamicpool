package vn.edu.hust.student.dynamicpool.presentation.screen;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.sun.prism.GraphicsPipeline.ShaderType;

import vn.edu.hust.student.dynamicpool.bll.model.PoolManager;
import vn.edu.hust.student.dynamicpool.presentation.WorldController;
import vn.edu.hust.student.dynamicpool.presentation.WorldRenderer;
import vn.edu.hust.student.dynamicpool.presentation.assets.Assets;
import vn.edu.hust.student.dynamicpool.presentation.gameobject.WidePoolUI;
import vn.edu.hust.student.dynamicpool.utils.AppConst;

public class HostGameScreen extends GameScreen {

	private BitmapFont defaultFont;
	private WidePoolUI widePoolUI;

	public HostGameScreen(WorldRenderer worldRenderer,
			WorldController worldController, PoolManager poolManager) {
		super(worldRenderer, worldController);
		widePoolUI = new WidePoolUI(poolManager);
	}
	
	@Override
	public void show() {
		super.show();
		this.defaultFont = Assets.instance.gameScreen.getDefaultFont();
	}
	
	@Override
	protected void createInputprocessors() {
		this.defaultInputProcessor = new HostGSDefaultInputProcessor(
				worldController);
		this.selectFishInputProcessor = new HostGSSelectFishInputProcessor(
				worldController);
		this.selectTrajectoryInputProcessor = new HostGSSelectTranjectoryInputPorcessor(
				worldController);
	}

	@Override
	public void render(float delta) {
		worldRenderer.beginRender();
		renderGameBackground();
		renderFishsUIAndUpdate(delta);
		renderKey();
		renderHubControl();
		worldRenderer.endRender();
		renderWidePool();
	}

	private void renderKey() {
		String key = worldController.getKey();
		float x = AppConst.width-20-defaultFont.getBounds(key).width;
		float y = 25+defaultFont.getBounds(key).height;
		defaultFont.draw(batch, key, x, y);
//		System.out.println(String.format("HostGameScreen: width %d, height %d, key %s", AppConst.width, AppConst.height, key));
	}
	
	ShapeRenderer shapeRenderer = new ShapeRenderer();
	private void renderWidePool() {
		shapeRenderer.setProjectionMatrix(worldRenderer.getCamera().combined);
		widePoolUI.draw(this.shapeRenderer);
	}
}
