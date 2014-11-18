package vn.edu.hust.student.dynamicpool.presentation.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import vn.edu.hust.student.dynamicpool.utils.AppConst;

public class AssetGameScreen {
	private AssetBackground background = new AssetBackground();
	private AssetFishAnimated fish1;
	private Texture exitButtonTexture;
	private Texture selectFishButtonsTexture;
	private Texture addFishButtonTexture;
	private Texture selectTrajectoryButtonTexture;
	
	public AssetGameScreen() {
		fish1 = new AssetFishAnimated(AppConst.HORIZONTAL_FISH_1_TEXTURE, 8,
				AppConst.VERTICAL_FISH_1_TEXTURE, 8,
				AppConst.FISH_ANIMATED_DURATION);
	}
	
	public void load(AssetManager assetManager) {
		background.load(assetManager);
		fish1.load(assetManager);
		assetManager.load(AppConst.EXIT_BUTTON_TEXTURE, Texture.class);
		assetManager.load(AppConst.ADD_FISH_BUTTON_TEXTURE, Texture.class);
		assetManager.load(AppConst.SELECT_FISH_BUTTONS_TEXTURE, Texture.class);
		assetManager.load(AppConst.SELECT_TRAJECTORY_TEXTURE, Texture.class);
	}
	
	public void bind(AssetManager assetManager) {
		background.bind(assetManager, 0, 0, 800, 480);
		fish1.bind(assetManager);
		exitButtonTexture = assetManager.get(AppConst.EXIT_BUTTON_TEXTURE);
		addFishButtonTexture = assetManager.get(AppConst.ADD_FISH_BUTTON_TEXTURE);
		selectFishButtonsTexture = assetManager.get(AppConst.SELECT_FISH_BUTTONS_TEXTURE);
		selectTrajectoryButtonTexture = assetManager.get(AppConst.SELECT_TRAJECTORY_TEXTURE);
	}

	public TextureRegion getGameBackgroundRegion() {
		return background.gameBackgroundRegion;
	}

	public AssetFishAnimated getFish1Asset() {
		return fish1;
	}

	public Texture getExitButtonTexture() {
		return exitButtonTexture;
	}
	
	public Texture getAddFishButtonTexture() {
		return addFishButtonTexture;
	}

	public Texture getSelectFishButtonsTexture() {
		return selectFishButtonsTexture;
	}

	public Texture getSelectTrajectoryButtonTexture() {
		return selectTrajectoryButtonTexture;
	}
}
