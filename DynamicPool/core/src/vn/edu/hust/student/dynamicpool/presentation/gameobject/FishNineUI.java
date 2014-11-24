package vn.edu.hust.student.dynamicpool.presentation.gameobject;

import vn.edu.hust.student.dynamicpool.bll.IFish;
import vn.edu.hust.student.dynamicpool.presentation.assets.AssetFishAnimated;
import vn.edu.hust.student.dynamicpool.presentation.assets.AssetGameScreen;
import vn.edu.hust.student.dynamicpool.presentation.assets.Assets;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class FishNineUI extends FishUIWithTwoAnimation {
	public FishNineUI(IFish fish) {
		super(fish);
	}

	@Override
	public void initFishAsset() {
		AssetGameScreen gameScreen = Assets.instance.gameScreen;
		fishAsset = gameScreen.getFish9Asset();
	}
}
