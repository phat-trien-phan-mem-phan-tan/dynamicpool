package vn.edu.hust.student.dynamicpool.presentation.assets;

import vn.edu.hust.student.dynamicpool.utils.AppConst;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class AssetLoading {
	private BitmapFont defaultFont;
	public void load(AssetManager assetManager) {
		assetManager.load(AppConst.DEFAUFT_FONT, BitmapFont.class);
	}

	public void bind(AssetManager assetManager) {
		defaultFont = assetManager.get(AppConst.DEFAUFT_FONT);
	}
	
	public BitmapFont getDefaultFont() {
		return defaultFont;
	}
}
