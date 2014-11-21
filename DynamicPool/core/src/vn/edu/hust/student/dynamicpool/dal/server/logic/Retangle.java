package vn.edu.hust.student.dynamicpool.dal.server.logic;

import java.awt.Point;

public class Retangle {
	private float width;
	private float height;
	private Point coordinate;
	
	public Retangle(float width, float height){
		setWidth(width);
		setHeight(height);
	}

	public Point getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Point coordinate) {
		this.coordinate = coordinate;
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
	
}
