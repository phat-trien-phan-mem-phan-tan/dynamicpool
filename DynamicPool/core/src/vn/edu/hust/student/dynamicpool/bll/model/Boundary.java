package vn.edu.hust.student.dynamicpool.bll.model;

public class Boundary {

	private float width;
	private float height;
	private Point location = new Point();
	
	public Boundary() {
		this(new Point(), 0, 0);
	}
	
	public Boundary(float width, float height) {
		this(new Point(), width, height);
	}
	
	public Boundary(Point location, float width, float height) {
		this.location = location;
		this.width = width;
		this.height = height;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point point) {
		this.location = point;
	}

	public float getMinX() {
		return location.getX();
	}

	public float getMinY() {
		return location.getY();
	}
	
	public float getMaxX() {
		return location.getX() + getWidth();
	}
	
	public float getMaxY() {
		return location.getY() + getHeight();
	}
}
