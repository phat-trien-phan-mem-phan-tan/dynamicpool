package vn.edu.hust.student.dynamicpool.presentation.gameobject;

import vn.edu.hust.student.dynamicpool.bll.model.IFish;
import vn.edu.hust.student.dynamicpool.presentation.assets.AssetFishAnimated;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class FishUIWithTwoAnimation extends FishUI {
	protected Animation horizontalAnimation;
	protected Animation verticalAnimation;
	protected AssetFishAnimated fishAsset;

	public FishUIWithTwoAnimation(IFish fish) {
		super(fish);
		initFishAsset();
		horizontalAnimation = fishAsset.getHorizontalAnimation();
		verticalAnimation = fishAsset.getVerticalAnimation();
	}
	
	@Override
	public void render(SpriteBatch batch) {
		Animation animation = getAnimation();
		TextureRegion frame = animation.getKeyFrame(getTime(), true);
		float w = frame.getRegionWidth();
		float h = frame.getRegionHeight();
		if (getDirection() == EDirection.LEFT) frame.flip(true, false);
		if (getDirection() == EDirection.BOTTOM) frame.flip(false, true);
		batch.draw(frame, getX(), getY(), w / 2, h / 2, w, h, getScale(),
				getScale(), getRotatedAngle());
	}

	private Animation getAnimation() {
		EDirection direction = getDirection();
		if (direction == EDirection.LEFT || direction == EDirection.RIGHT)
			return horizontalAnimation;
		return verticalAnimation;
	}

	private float getRotatedAngle() {
		return 0;
	}
}
