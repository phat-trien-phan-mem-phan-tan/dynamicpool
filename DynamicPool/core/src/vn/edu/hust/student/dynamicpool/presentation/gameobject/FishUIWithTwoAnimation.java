package vn.edu.hust.student.dynamicpool.presentation.gameobject;

import vn.edu.hust.student.dynamicpool.bll.model.IFish;
import vn.edu.hust.student.dynamicpool.presentation.assets.AssetFishAnimated;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class FishUIWithTwoAnimation extends FishUI {
	protected Animation rightAnimation;
	private Animation leftAnimation;
	protected AssetFishAnimated fishAsset;
	public FishUIWithTwoAnimation(IFish fish) {
		super(fish);
		initFishAsset();
		rightAnimation = fishAsset.getRightAnimation();
		leftAnimation = fishAsset.getLeftAnimation();
	}

	@Override
	public void render(SpriteBatch batch) {
		Animation animation = getAnimation();
		TextureRegion frame = animation.getKeyFrame(getTime(), true);
		float w = frame.getRegionWidth();
		float h = frame.getRegionHeight();
		batch.draw(frame, getX(), getY(), w / 2, h / 2, w, h, getScale(),
				getScale(), getRotatedAngle());
	}

	private Animation getAnimation() {
		if (getDirection() == EDirection.LEFT) return leftAnimation;
		return rightAnimation;
	}

	private float getRotatedAngle() {
		return 0;
	}
}
