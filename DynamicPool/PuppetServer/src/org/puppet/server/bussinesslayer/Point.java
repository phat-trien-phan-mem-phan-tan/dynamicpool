package org.puppet.server.bussinesslayer;

public class Point {
	public int x;
	public int y;
	public int  getX(){
		return x;
	}
	public int  getY(){
		return y;
	}
	public void setXY(int x, int y){
		this.x=x;
		this.y=y;
	}
	public Point(int x, int y){
		this.x=x;
		this.y=y;
	}
}
