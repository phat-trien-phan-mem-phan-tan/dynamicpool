package vn.edu.hust.student.dynamicpool.equation;

import java.awt.Point;

import vn.edu.hust.student.dynamicpool.equation.vector.Oxy;
import vn.edu.hust.student.dynamicpool.equation.vector.Vector;

public class LineEquation extends Equation {

	/*
	 * x = x0+u1t; y = y0+u2t;
	 * 
	 * he so goc cua duong thang : k = u2/u1 v(u1,u2) la vec to chi phuong.
	 */

	private double x0;
	private double y0;

	// vector chi phuong
	private Vector u;

	public LineEquation() {
		this.t = 0;
		this.x0 = 0;
		this.y0 = 0;
		u = new Vector();
	}

	public LineEquation(float t, float x0, float y0, float u1, float u2) {
		this.t = t;
		this.x0 = x0;
		this.y0 = y0;
		u = new Vector(u1, u2);
	}

	public LineEquation(float t, Point point, float u1, float u2) {

		this.t = t;
		this.x0 = point.getX();
		this.y0 = point.getY();
		u = new Vector(u1, u2);
	}

	@Override
	public Point move(float t) {

		double x = x0 + u.getX() * t;
		double y = y0 + u.getX() * t;
		Point point = new Point();
		point.setLocation(x, y);
		return point;
	}


	@Override
	public void hit(Vector vector) {

		setDirection(vector);
		
	}

	public void setDirection(Vector vector) {
		if (vector.equals(Oxy.ox)) {
			// create new vector u
			double b = 0 - u.getX();
			u.setY(b);

		} else if (vector.equals(Oxy.oy)) {
			double a = 0 - u.getX();
			u.setX(a);

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

	public Vector getU() {
		return u;
	}

	public void setU(Vector u) {
		this.u = u;
	}

}
