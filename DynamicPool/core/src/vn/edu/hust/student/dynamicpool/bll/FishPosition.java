package vn.edu.hust.student.dynamicpool.bll;

import java.awt.Point;

public class FishPosition implements IFishPosition {
	private float x;
	private float y;
	private float angle;

	public FishPosition(float x, float y) {
		this.angle = 0;
		this.increaseX(x);
		this.increaseY(y);
		
	}

	public FishPosition() {
		this.x = 0;
		this.y = 0;
		this.angle = 0;
	}

	@Override
	public float getX() {
		return x;
	}

	public void setX(float x){
		this.x = x;
	}
	
	@Override
	public float getY() {
		return y;
	}

	public void setY(float y){
		this.y = y;
	}
	


	@Override
	public void setAngle(float angle) {
		this.angle = angle;
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
	public float getAngle() {
		
		return angle;
	}

	@Override
	public void setPosition(Point point) {
		
		this.x = (float) point.getX();
		this.y = (float) point.getY();
		
	}



	
}
