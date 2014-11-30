package vn.edu.hust.student.dynamicpool.bll.model;

public class Segment {

	private float beginPoint;
	private float endPoint;
	private EDirection segmentDirection;
	private String neighbourClientName = null;

	@Override
	public String toString() {
		return "Segment [beginPoint=" + beginPoint + ", endPoint=" + endPoint
				+ ", segmentDirection=" + segmentDirection
				+ ", neighbourClientName=" + neighbourClientName + "]";
	}

	public Segment() {

	}

	public float getBeginPoint() {
		return beginPoint;
	}

	public void setBeginPoint(float beginPoint) {
		this.beginPoint = beginPoint;
	}

	public float getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(float endPoint) {
		this.endPoint = endPoint;
	}

	public EDirection getSegmentDirection() {
		return segmentDirection;
	}

	public void setSegmentDirection(EDirection segmentDirection) {
		this.segmentDirection = segmentDirection;
	}

	public Segment(EDirection sengmentDirection, float beginPoint,
			float endPoint) {
		this.segmentDirection = sengmentDirection;
		this.beginPoint = beginPoint;
		this.endPoint = endPoint;
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