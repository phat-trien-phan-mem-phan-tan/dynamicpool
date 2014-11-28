package vn.edu.hust.student.dynamicpool.bll.model;

import java.util.ArrayList;
import java.util.List;

import vn.edu.hust.student.dynamicpool.presentation.gameobject.EDirection;

public class Pool {
	private DeviceInfo deviceInfo = new DeviceInfo();
	private Boundary boundary = new Boundary();
	private List<Segment> segments = new ArrayList<Segment>();
	private List<IFish> fishes = new ArrayList<IFish>();

	public Pool() {
		
	}

	public Pool(DeviceInfo deviceInfo) {
		this.deviceInfo = deviceInfo;
		this.boundary.setWidth(deviceInfo.getScreenWidth());
		this.boundary.setHeight(deviceInfo.getScreenHeight());
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
	}

	public List<IFish> getFishes() {
		return fishes;
	}

	public void setFishes(List<IFish> fishes) {
		this.fishes = fishes == null ? new ArrayList<IFish>() : fishes;
	}

	public void updateLocationOfFishes(float detatime) {
		for (IFish fish : fishes) {
			fish.updateLocation(detatime);
		}
	}

	public void addFish(IFish fish) {
		fishes.add(fish);
	}

	public Segment detectCollisionLeftSegments(Boundary fishBoundary) {
		for (Segment segment : segments) {
			if (segment.getSegmentDirection() == EDirection.LEFT
					&& segment.getBeginPoint() <= fishBoundary.getMinY()
					&& fishBoundary.getMaxY() <= segment.getEndPoint()) {
				return segment;
			}
		}
		return null;
	}

	public Segment detectCollisionRightSegments(Boundary fishBoundary) {
		for (Segment segment : segments) {
			if (segment.getSegmentDirection() == EDirection.RIGHT
					&& segment.getBeginPoint() <= fishBoundary.getMinY()
					&& fishBoundary.getMaxY() <= segment.getEndPoint()) {
				return segment;
			}
		}
		return null;
	}

	public Segment detectCollisionTopSegments(Boundary fishBoundary) {
		for (Segment segment : segments) {
			if (segment.getSegmentDirection() == EDirection.TOP
					&& segment.getBeginPoint() <= fishBoundary.getMinX()
					&& fishBoundary.getMaxX() <= segment.getEndPoint()) {
				return segment;
			}
		}
		return null;
	}
	public Segment detectCollisionBottomSegments(Boundary fishBoundary) {
		for (Segment segment : segments) {
			if (segment.getSegmentDirection() == EDirection.BOTTOM
					&& segment.getBeginPoint() <= fishBoundary.getMinX()
					&& fishBoundary.getMaxX() <= segment.getEndPoint()) {
				return segment;
			}
		}
		return null;
	}
}