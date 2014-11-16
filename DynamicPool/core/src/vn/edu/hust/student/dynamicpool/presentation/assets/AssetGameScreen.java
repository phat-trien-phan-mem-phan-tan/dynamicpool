package vn.edu.hust.student.dynamicpool.presentation.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import vn.edu.hust.student.dynamicpool.utils.AppConst;

public class AssetGameScreen {
	public AssetBackground background = new AssetBackground();
	private AssetFishAnimated fish1;
	
	public AssetGameScreen() {
		fish1 = new AssetFishAnimated(AppConst.HORIZONTAL_FISH_1_TEXTURE, 8,
				AppConst.VERTICAL_FISH_1_TEXTURE, 8,
				AppConst.FISH_ANIMATED_DURATION);
	}
	
	public void load(AssetManager assetManager) {
		background.load(assetManager);
		fish1.load(assetManager);
	}
	
	public void bind(AssetManager assetManager) {
		background.bind(assetManager, 0, 0, 800, 480);
		fish1.bind(assetManager);
	}

	public TextureRegion getGameBackgroundRegion() {
		return background.gameBackgroundRegion;
	}

	public AssetFishAnimated getFish1Asset() {
		return fish1;
	}
}
