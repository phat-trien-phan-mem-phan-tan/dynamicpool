package vn.edu.hust.student.dynamicpool.equation;

import java.awt.Point;

import vn.edu.hust.student.dynamicpool.equation.vector.Oxy;
import vn.edu.hust.student.dynamicpool.equation.vector.Vector;



public class CircleEquation extends Equation  {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myapp.equation.Equation#move(float)
	 * 
	 * x0,y0 toa goc a he so t goc
	 * 
	 * x = x0+aSin(nt + angle) y = y0+aCos(nt + angle)
	 */

	private double x0;
	private double y0;

	private float a;
	private float n;
	private double angle;

	private float t;

	public CircleEquation() {
		this.x0 = 0;
		this.y0 = 0;
		this.a = 0;
		this.t = 0;
		this.n = 1;
		this.angle = 0;
	}

	public CircleEquation(float x0, float y0, float a, float t) {
		this.x0 = x0;
		this.y0 = y0;
		this.a = a;
		this.t = t;
		this.n = 1;
		this.angle = 0;
	}

	public CircleEquation(float x0, float y0, float a, float t, float n,
			float angle) {
		this.x0 = x0;
		this.y0 = y0;
		this.a = a;
		this.t = t;
		this.n = n;
		this.angle = angle;
	}

	public CircleEquation(Point point, float a, float t) {
		this.x0 = point.getX();
		this.y0 = point.getY();

		this.a = a;
		this.t = t;
	}

	@Override
	public Point move(float t) {

		float x = (float) (x0 + a * Math.sin(n * t + angle));
		float y = (float) (y0 + a * Math.cos(n * t + angle));

		Point point = new Point();
		point.setLocation(x, y);
		// check hit at continue point
		return point;
	}

	@Override
	public void hit(Vector vector) {
		
		setDirection(vector);
		
		
	}

	@Override
	public void setDirection(Vector vector) {

		// alpha < 2PI
		float T = (float) ((n * t + angle) / (2 * Math.PI));
		T = (int) T + 1;

		// tinh goc bu
		float beta = (float) (T * 2 * Math.PI - (n * t + angle));

		//
		float anpha = (float) (n*t+angle);
		float detaAngle = (float)(2*anpha - Math.PI);
		
		// increase angle
		//setAngle(2 * beta + angle);

		if (vector.equals(Oxy.ox)) {
			// check cos  to set center point
			float cosValue = (float) Math.cos(n * t + angle);

			x0 = x0 + 2 * a * cosValue;

			if (cosValue < 0) {
					
				angle = angle+Math.abs(detaAngle);
				
			} else {
				angle = angle - Math.abs(detaAngle);
				
			}

			setX0(x0);

		} else if (vector.equals(Oxy.oy)) {

			// check sin (anphal) to set center point
			float sinValue = (float) Math.sin(n * t + angle);
	

			// move center point of circle
			y0 = y0 + 2 * a * sinValue;

			if (sinValue < 0) {

				angle = angle+Math.abs(detaAngle);
				
			} else {

				angle = angle - Math.abs(detaAngle);
				
			}

			setY0(y0);

		}

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

	public float getN() {
		return n;
	}

	public void setN(float n) {
		this.n = n;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	
}
