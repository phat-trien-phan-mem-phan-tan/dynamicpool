package vn.edu.hust.student.dynamicpool.bll.model;


public class Segment {

	private float beginPoint;
	private float endPoint;
	private EDirection segmentDirection;
	private String neighbourClientName = null;

	public Segment() {
		
	}

	public Segment(EDirection sengmentDirection, float beginPoint, float endPoint) {
		this.segmentDirection = sengmentDirection;
		this.beginPoint = beginPoint;
		this.endPoint = endPoint;
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

	public Segment cloneWithoutNeighbour() {
		return new Segment(segmentDirection, beginPoint, endPoint);
	}
}