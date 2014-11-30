package vn.edu.hust.student.dynamicpool.bll.model;

import flexjson.JSON;

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

	@JSON(include=false)
	public float getMinX() {
		return location.getX();
	}

	@JSON(include=false)
	public float getMinY() {
		return location.getY();
	}

	@JSON(include=false)
	public float getMaxX() {
		return location.getX() + getWidth();
	}

	@JSON(include=false)
	public float getMaxY() {
		return location.getY() + getHeight();
	}

	public boolean isInside(Boundary containerBoundary) {
		return this.getMinX() > containerBoundary.getMinX()
				&& this.getMaxX() < containerBoundary.getMaxX()
				&& this.getMinY() > containerBoundary.getMinY()
				&& this.getMaxY() < containerBoundary.getMaxY();
	}

	public boolean isPassing(Boundary containerBoundary) {
		return !isInside(containerBoundary) && !isOutside(containerBoundary);
	}

	public boolean isOutside(Boundary containerBoundary) {
		double distanceX = Math.abs(containerBoundary.getMinX() * 2
				+ containerBoundary.getWidth() - this.getMinX() * 2 - containerBoundary.getWidth());
		float minDistanceX = containerBoundary.getWidth() + this.getWidth();
		float doubleDistanceY = Math.abs(containerBoundary.getMinY() * 2
				+ containerBoundary.getHeight() - this.getMinY() * 2 - containerBoundary.getHeight());
		float minDoubleDistanceY = containerBoundary.getHeight() + this.getHeight();
		return distanceX > minDistanceX && doubleDistanceY > minDoubleDistanceY;
	}
}
