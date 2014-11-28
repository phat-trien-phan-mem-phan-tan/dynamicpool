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

	public boolean isInside(Boundary boundary) {
		return this.getMinX() > boundary.getMinX()
				&& this.getMaxX() < boundary.getMaxX()
				&& this.getMinY() > boundary.getMinY()
				&& this.getMaxY() < boundary.getMaxY();
	}

	public boolean isPassing(Boundary boundary) {
		return !isInside(boundary) && !isOutside(boundary);
	}

	public boolean isOutside(Boundary boundary) {
		double distanceX = Math.abs(boundary.getMinX() * 2
				+ boundary.getWidth() - this.getMinX() * 2 - this.getWidth());
		float minDistanceX = boundary.getWidth() + this.getWidth();
		float doubleDistanceY = Math.abs(boundary.getMinY() * 2
				+ boundary.getHeight() - this.getMinY() * 2 - this.getHeight());
		float minDoubleDistanceY = boundary.getHeight() + this.getHeight();
		return distanceX > minDistanceX && doubleDistanceY > minDoubleDistanceY;
	}
}
