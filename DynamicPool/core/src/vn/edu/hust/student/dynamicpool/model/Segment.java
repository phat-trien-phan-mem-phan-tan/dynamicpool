package vn.edu.hust.student.dynamicpool.model;


public class Segment {

	private Point point1;
	private Point point2;
	
	
	public Segment(){
		this.point1 = new Point(0, 0);
		this.point2 = new Point(0,0);
	}
	
	public Segment(Point beginPoint,Point endPoint){
		this.point1 = beginPoint;
		this.point2 = endPoint;
		
	}
	
	public Point getBeginPoint() {
		
		if(point1.getX() < point2.getX() || point1.getY() < point2.getY()){
			return point1;
		}
		return point2;
	}
	public void setBeginPoint(Point beginPoint) {
		this.point1 = beginPoint;
	}
	public Point getEndPoint() {
		
		if(point1.getX() < point2.getX() || point1.getY() < point2.getY()){
			return point2;
		}
		return point1;
	}
	public void setEndPoint(Point endPoint) {
		this.point2 = endPoint;
	}
	
	
}
