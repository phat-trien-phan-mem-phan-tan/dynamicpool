package org.puppet.server.bussinesslayer;

public class Segment {
	
	public Point pointS;
	public Point pointE;
	public Point getPointS(){
		return pointS;
	}
	public Point getPointE(){
		return pointE;
	}
	public void setPointSE(Point pointS, Point pointE){
		this.pointS=pointS;
		this.pointE=pointE;
	}
	public Segment(Point pointS, Point pointE){
		this.pointS=pointS;
		this.pointE=pointE;	
	}
}
