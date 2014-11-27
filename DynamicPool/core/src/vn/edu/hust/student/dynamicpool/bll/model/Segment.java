package vn.edu.hust.student.dynamicpool.bll.model;

public class Segment {

	private Point beginPoint;
	private Point endPoint;
	
	
	public Segment(){
		this.beginPoint = new Point(0, 0);
		this.endPoint = new Point(0,0);
	}
	
	public Segment(Point beginPoint,Point endPoint){
		this.beginPoint = beginPoint;
		this.endPoint = endPoint;
		
	}
	
	public Point getBeginPoint() {
		
		if(beginPoint.getX() < endPoint.getX() || beginPoint.getY() < endPoint.getY()){
			return beginPoint;
		}
		return endPoint;
	}
	public void setBeginPoint(Point beginPoint) {
		this.beginPoint = beginPoint;
	}
	public Point getEndPoint() {
		
		if(beginPoint.getX() < endPoint.getX() || beginPoint.getY() < endPoint.getY()){
			return endPoint;
		}
		return beginPoint;
	}
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}
	
	
}
