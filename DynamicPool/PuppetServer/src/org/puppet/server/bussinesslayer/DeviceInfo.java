package org.puppet.server.bussinesslayer;

public class DeviceInfo {
	private float width;
	private float height;
	private float size;
	
	public DeviceInfo(float width, float height, float size){
		setHeight(height);
		setWidth(width);
		setSize(size);
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

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}
}
