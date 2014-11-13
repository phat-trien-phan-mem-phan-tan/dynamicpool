package vn.edu.hust.student.dynamicpool.bll;

public class FishPosition implements IFishPosition {
	private float x;
	private float y;
	private float angle;

	public FishPosition(float x, float y) {
		this.increaseX(x);
		this.increaseY(y);
	}

	public FishPosition() {
	}

	@Override
	public float getX() {
		return x;
	}

	@Override
	public float getY() {
		return y;
	}

	@Override
	public float getAngle() {
		return angle;
	}

	@Override
	public float increaseX(float dx) {
		x += dx;
		return x;
	}

	@Override
	public float increaseY(float dy) {
		y += dy;
		return dy;
	}

	@Override
	public void setAngle(float angle) {
		this.angle = angle;
	}

}
