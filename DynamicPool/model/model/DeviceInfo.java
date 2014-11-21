package vn.edu.hust.student.dynamicpool.model;

public class DeviceInfo {
	
	private float screenWidth;
	private float screenHeight;
	private float screenSize;
	
	public DeviceInfo(float screenWidth, float screenHeight, float screenSize) {
		
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.screenSize = screenSize;
	}

	public float getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(float screenWidth) {
		this.screenWidth = screenWidth;
	}

	public float getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(float screenHeight) {
		this.screenHeight = screenHeight;
	}

	public float getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(float screenSize) {
		this.screenSize = screenSize;
	}
	
	
	
	
}
