package vn.edu.hust.student.dynamicpool.bll.model;

import java.util.ArrayList;
import java.util.List;

public class Pool {
	private DeviceInfo deviceInfo = new DeviceInfo();
	private Boundary boundary = new Boundary();
	private List<Segment> segments = new ArrayList<Segment>();
	private List<IFish> fishes = new ArrayList<IFish>();

	public Pool() {
		
	}

	public Pool(DeviceInfo deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public DeviceInfo getDeviceInfo() {
		return deviceInfo;
	}
	
	public void setDeviceInfo(DeviceInfo deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
	
	public Boundary getBoundary() {
		return boundary;
	}

	public void setBoundary(Boundary rectangle) {
		Boundary oldRectangle = this.boundary;
		oldRectangle.setHeight(rectangle.getHeight());
		oldRectangle.setWidth(rectangle.getWidth());
		oldRectangle.setLocation(rectangle.getLocation());
	}
	
	public List<Segment> getSegments() {
		return segments;
	}
	
	public void setSegments(List<Segment> segments) {
		this.segments = segments;
//
//		if (segments != null) {
//
//			for (int i = 0; i < segments.size(); i++) {
//
//				Point beginPoint = segments.get(i).getBeginPoint();
//				Point endPoint = segments.get(i).getEndPoint();
//
//				if (beginPoint.getX() == endPoint.getX()) {
//					segmentsY.add(segments.get(i));
//				} else {
//					segmentsX.add(segments.get(i));
//				}
//			}
//		}
	}
	
	public List<IFish> getFishes() {
		return fishes;
	}
	
	public void setFishes(List<IFish> fishes) {
		this.fishes = fishes;
	}

	public List<IFish> updatePosition(float detatime) {
		for (int i = 0; i < fishes.size(); i++) {
			Fish fish = (Fish) fishes.get(i);
			fish.update(detatime);
			checkHit(detatime, boundary, fish);
		}
		return fishes;
	}

	private boolean checkHit(float detatime, Boundary poolPosition, Fish fish) {
//		float fishMinX = fish.getBoundary().getMinX();
//		float fishMinY = fish.getBoundary().getMinY();
//		float fishMaxX = fish.getBoundary().getMaxX();
//		float fishMaxY = fish.getBoundary().getMaxY();
//
//		float minY = poolPosition.getLocation().getY();
//		float maxY = AppConst.height + minY;
//
//		float minX = poolPosition.getLocation().getX();
//		float maxX = AppConst.width + minX;
//
////		System.out.println("Pool: fisX: " + fishMinX + ", fishY: " + fishMinY
////				+ ", minX: " + minX + ", minY: " + minY + ", maxX: " + maxX
////				+ ", maxY: " + maxY);
//
//		if (fishMinX <= minX || fishMaxX >= maxX) {
//			fish.getTrajectory().setDirection(Oxy.oy);
//			float newAngle = (float) Math.PI - fish.getAngle();
//			fish.setAngle(newAngle);
//			fish.setFishState(this, 1);
//			return true;
//		}
//		if (fishMinY <= minY || fishMaxY >= maxY) {
//
//			fish.getTrajectory().setDirection(Oxy.ox);
//			fish.setFishState(this, 0);
//			return true;
//		}
//		fish.setFishState(FishState.NONE);
		return false;
	}
}