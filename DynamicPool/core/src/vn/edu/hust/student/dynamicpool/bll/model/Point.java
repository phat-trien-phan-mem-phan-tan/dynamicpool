package vn.edu.hust.student.dynamicpool.bll.model;

public class Point {

	private float x;
	private float y;

	public Point() {
		this.x = 0;
		this.y = 0;
	}
	
	public Point(float x,float y){
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setLocation(float x, float y) {
		this.setX(x);
		this.setY(y);
	}
	
	

}
