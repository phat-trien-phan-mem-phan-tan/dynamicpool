package vn.edu.hust.student.dynamicpool.bll.model;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.org.apache.regexp.internal.recompile;

import flexjson.JSON;


public class Pool {
	private DeviceInfo deviceInfo = new DeviceInfo();
	public Boundary boundary = new Boundary();
	private List<Segment> segments = new ArrayList<Segment>();
	private List<IFish> fishes = new ArrayList<IFish>();
	private float scale = 1;
	@JSON(include=false)
	private Logger logger = LoggerFactory.getLogger(Pool.class);

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
		if (segments == null)
			segments = new ArrayList<>();
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

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public void updateLocationOfFishes(float detatime) {
		for (IFish fish : fishes) {
			fish.updateLocation(detatime);
		}
	}

	public void addFish(IFish fish) {
		fishes.add(fish);
	}
	
	public void removeFish(IFish fish) {
		fishes.remove(fish);
	}
	
	public IFish getFish(String fishId) {
		for (IFish fish : fishes) {
			if (fish.getFishId().equals(fishId)) return fish;
		}
		return null;
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

	public void updateSetting(Pool clientPoolSetting) {
		this.deviceInfo.setClientName(clientPoolSetting.deviceInfo
				.getClientName());
		this.getBoundary().setWidth(clientPoolSetting.getBoundary().getWidth());
		this.getBoundary().setHeight(
				clientPoolSetting.getBoundary().getHeight());
		logger.debug("updating {} segments: client name {}", clientPoolSetting
				.getSegments().size(), deviceInfo.getClientName());
		this.updateSegments(clientPoolSetting.getSegments());
		this.scale = clientPoolSetting.scale;
	}

	private void updateSegments(List<Segment> clientSegments) {
		this.getSegments().clear();
		for (Segment clientSegment : clientSegments) {
			Segment segment = new Segment(clientSegment.getSegmentDirection(),
					clientSegment.getBeginPoint(), clientSegment
							.getEndPoint());
			this.getSegments().add(segment);
		}
	}
	
	@Override
	public Pool clone() {
		Pool clone = new Pool();
		clone.setBoundary(this.boundary);
		clone.setDeviceInfo(this.deviceInfo);
		clone.setFishes(this.fishes);
		clone.setScale(this.scale);
		return clone;
	}

	public void removeFishById(String fishId) {
		IFish fish = getFish(fishId);
		if (fish == null) {
			logger.error("removeFishById: fish {} not found", fishId);
			return;
		}
		fishes.remove(fish);
		logger.info("removeFishById: fish {} removed from pool {}", fishId, getDeviceInfo().getClientName());
	}
}