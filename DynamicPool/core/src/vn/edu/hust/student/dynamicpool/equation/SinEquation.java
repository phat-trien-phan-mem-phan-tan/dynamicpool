package vn.edu.hust.student.dynamicpool.equation;

import java.awt.Point;

import vn.edu.hust.student.dynamicpool.equation.vector.Vector;


public class SinEquation extends Equation {

	/*
	 * (non-Javadoc)
	 * @see com.myapp.equation.Equation#move(float)
	 * 
	 * x = x0 + t;
	 * y = y0 + a*sin(t)
	 */
	
	private double x0;
	private double y0;
	
	private float a;
	private float t;
	
	public SinEquation() {
		// TODO Auto-generated constructor stub
		
		this.x0 = 0;
		this.y0 = 0;
		this.a = 1;
		this.t = 0;
	
	}
	
	public SinEquation(float x0,float y0,float a,float t){
		this.x0 = x0;
		this.y0 = y0;
		this.a = a;
		this.t = t;
	}
	
	public SinEquation(Point point,float a,float t){
		this.x0 = point.getX();
		this.y0 = point.getY();
		this.a = a;
		this.t = t;
	}
	
	
	@Override
	public Point move(float t) {
		return null;
	}

	

	@Override
	public void hit(Vector vector) {
		
		setDirection(vector);
	}

	@Override
	public void setDirection(Vector vector) {
		
	}

	public double getX0() {
		return x0;
	}

	public void setX0(double x0) {
		this.x0 = x0;
	}

	public double getY0() {
		return y0;
	}

	public void setY0(double y0) {
		this.y0 = y0;
	}

	public float getA() {
		return a;
	}

	public void setA(float a) {
		this.a = a;
	}

	public float getT() {
		return t;
	}

	public void setT(float t) {
		this.t = t;
	}
	
	

}
