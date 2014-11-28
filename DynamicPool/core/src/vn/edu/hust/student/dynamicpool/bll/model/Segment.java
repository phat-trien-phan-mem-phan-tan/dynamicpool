package vn.edu.hust.student.dynamicpool.bll.model;

import vn.edu.hust.student.dynamicpool.presentation.gameobject.EDirection;

public class Segment {

	private float beginPoint;
	private float endPoint;
	private EDirection segmentDirection;
	private String neighbourClientName = null;

	public Segment() {
		
	}

	public Segment(EDirection sengmentDirection, float beginPoint, float endPoint) {
		this.segmentDirection = sengmentDirection;
		this.beginPoint = Math.min(beginPoint, endPoint);
		this.endPoint = Math.max(beginPoint, endPoint);
	}

	public float getBeginPoint() {
		return beginPoint;
	}

	public float getEndPoint() {
		return endPoint;
	}

	public EDirection getSegmentDirection() {
		return this.segmentDirection;
	}
	
	public String getNeighbourClientName() {
		return this.neighbourClientName;		
	}
	
	public void setNeighbourClientName(String clientName) {
		this.neighbourClientName = clientName;
	}
}