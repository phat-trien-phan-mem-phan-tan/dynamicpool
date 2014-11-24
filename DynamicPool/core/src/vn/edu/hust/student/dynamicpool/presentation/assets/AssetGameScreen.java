package vn.edu.hust.student.dynamicpool.presentation.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import vn.edu.hust.student.dynamicpool.dal.utils.AppConst;

public class AssetGameScreen {
	private AssetBackground background = new AssetBackground();
	private AssetFishAnimated fish1;
	private AssetFishAnimated fish2;
	private AssetFishAnimated fish3;
	private AssetFishAnimated fish4;
	private AssetFishAnimated fish5;
	private AssetFishAnimated fish6;
	private AssetFishAnimated fish7;
	private AssetFishAnimated fish8;
	private AssetFishAnimated fish9;
	private AssetFishAnimated fish10;
	private Texture exitButtonTexture;
	private Texture selectFishButtonsTexture;
	private Texture addFishButtonTexture;
	private Texture selectTrajectoryButtonTexture;
	
	public AssetGameScreen() {
		fish1 = new AssetFishAnimated(AppConst.HORIZONTAL_FISH_1_TEXTURE, 8,
				AppConst.VERTICAL_FISH_1_TEXTURE, 8,
				AppConst.FISH_ANIMATED_DURATION);
		fish2 = new AssetFishAnimated(AppConst.HORIZONTAL_FISH_2_TEXTURE, 8,
				AppConst.VERTICAL_FISH_2_TEXTURE, 8,
				AppConst.FISH_ANIMATED_DURATION);
		fish3 = new AssetFishAnimated(AppConst.HORIZONTAL_FISH_3_TEXTURE, 8,
				AppConst.VERTICAL_FISH_3_TEXTURE, 8,
				AppConst.FISH_ANIMATED_DURATION);
		fish4 = new AssetFishAnimated(AppConst.HORIZONTAL_FISH_4_TEXTURE, 8,
				AppConst.VERTICAL_FISH_4_TEXTURE, 8,
				AppConst.FISH_ANIMATED_DURATION);
		fish5 = new AssetFishAnimated(AppConst.HORIZONTAL_FISH_5_TEXTURE, 8,
				AppConst.VERTICAL_FISH_5_TEXTURE, 8,
				AppConst.FISH_ANIMATED_DURATION);
		fish6 = new AssetFishAnimated(AppConst.HORIZONTAL_FISH_6_TEXTURE, 8,
				AppConst.VERTICAL_FISH_6_TEXTURE, 8,
				AppConst.FISH_ANIMATED_DURATION);
		fish7 = new AssetFishAnimated(AppConst.HORIZONTAL_FISH_7_TEXTURE, 8,
				AppConst.VERTICAL_FISH_7_TEXTURE, 8,
				AppConst.FISH_ANIMATED_DURATION);
		fish8 = new AssetFishAnimated(AppConst.HORIZONTAL_FISH_8_TEXTURE, 8,
				AppConst.VERTICAL_FISH_8_TEXTURE, 8,
				AppConst.FISH_ANIMATED_DURATION);
		fish9 = new AssetFishAnimated(AppConst.HORIZONTAL_FISH_9_TEXTURE, 8,
				AppConst.VERTICAL_FISH_9_TEXTURE, 8,
				AppConst.FISH_ANIMATED_DURATION);
		fish10 = new AssetFishAnimated(AppConst.HORIZONTAL_FISH_10_TEXTURE, 8,
				AppConst.VERTICAL_FISH_10_TEXTURE, 8,
				AppConst.FISH_ANIMATED_DURATION);
	}
	
	public void load(AssetManager assetManager) {
		background.load(assetManager);
		fish1.load(assetManager);
		fish2.load(assetManager);
		fish3.load(assetManager);
		fish4.load(assetManager);
		fish5.load(assetManager);
		fish6.load(assetManager);
		fish7.load(assetManager);
		fish8.load(assetManager);
		fish9.load(assetManager);
		fish10.load(assetManager);
		assetManager.load(AppConst.EXIT_BUTTON_TEXTURE, Texture.class);
		assetManager.load(AppConst.ADD_FISH_BUTTON_TEXTURE, Texture.class);
		assetManager.load(AppConst.SELECT_FISH_BUTTONS_TEXTURE, Texture.class);
		assetManager.load(AppConst.SELECT_TRAJECTORY_TEXTURE, Texture.class);
	}
	
	public void bind(AssetManager assetManager) {
		background.bind(assetManager, 0, 0, 800, 480);
		fish1.bind(assetManager);
		fish2.bind(assetManager);
		fish3.bind(assetManager);
		fish4.bind(assetManager);
		fish5.bind(assetManager);
		fish6.bind(assetManager);
		fish7.bind(assetManager);
		fish8.bind(assetManager);
		fish9.bind(assetManager);
		fish10.bind(assetManager);
		exitButtonTexture = assetManager.get(AppConst.EXIT_BUTTON_TEXTURE);
		addFishButtonTexture = assetManager.get(AppConst.ADD_FISH_BUTTON_TEXTURE);
		selectFishButtonsTexture = assetManager.get(AppConst.SELECT_FISH_BUTTONS_TEXTURE);
		selectTrajectoryButtonTexture = assetManager.get(AppConst.SELECT_TRAJECTORY_TEXTURE);
	}

	public TextureRegion getGameBackgroundRegion() {
		return background.gameBackgroundRegion;
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
	
	public AssetFishAnimated getFish1Asset() {
		return fish1;
	}

	public AssetFishAnimated getFish2Asset() {
		return fish2;
	}

	public AssetFishAnimated getFish3Asset() {
		return fish3;
	}

	public AssetFishAnimated getFish4Asset() {
		return fish4;
	}

	public AssetFishAnimated getFish5Asset() {
		return fish5;
	}

	public AssetFishAnimated getFish6Asset() {
		return fish6;
	}

	public AssetFishAnimated getFish7Asset() {
		return fish7;
	}

	public AssetFishAnimated getFish8Asset() {
		return fish8;
	}

	public AssetFishAnimated getFish9Asset() {
		return fish9;
	}

	public AssetFishAnimated getFish10Asset() {
		return fish10;
	}
}
