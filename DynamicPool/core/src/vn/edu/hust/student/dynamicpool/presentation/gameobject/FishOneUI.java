package vn.edu.hust.student.dynamicpool.presentation.gameobject;

import vn.edu.hust.student.dynamicpool.bll.IFish;
import vn.edu.hust.student.dynamicpool.presentation.assets.Assets;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class FishOneUI extends FishUI {
	private Animation horizontalAnimation;
	private Animation verticalAnimation;

	public FishOneUI(IFish fish) {
		super(fish);
		init();
	}

	@Override
	public void init() {
		Assets assets = Assets.instance;
		Assets.AssetFishAnimated fishAsset = assets.getFish1Asset();
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
//		int angle = ((int) getAngle()+45) % 90;
//		switch (getDirection()) {
//		case TOP:
//			return angle-90;
//		case LEFT:
//			return angle+180;
//		case BOTTOM:
//			return angle+180;
//		default:
//			return angle;
//		}
	}
}