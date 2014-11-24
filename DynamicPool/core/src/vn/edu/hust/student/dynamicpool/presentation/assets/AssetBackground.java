package vn.edu.hust.student.dynamicpool.presentation.assets;

import vn.edu.hust.student.dynamicpool.dal.utils.AppConst;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetBackground {
	public Texture gameBackgroundTexture;
	public TextureRegion gameBackgroundRegion;

	public AssetBackground() {
		
	}

	public void load(AssetManager assetManager) {
		assetManager.load(AppConst.BACKGROUND_TEXTURE, Texture.class);
	}

	public void bind(AssetManager assetManager, int x, int y, int width,
			int height) {
		gameBackgroundTexture = assetManager
				.get(AppConst.BACKGROUND_TEXTURE);
		gameBackgroundTexture.setFilter(TextureFilter.Linear,
				TextureFilter.Linear);
		gameBackgroundRegion = new TextureRegion(gameBackgroundTexture, x,
				y, width, height);
	}
}
