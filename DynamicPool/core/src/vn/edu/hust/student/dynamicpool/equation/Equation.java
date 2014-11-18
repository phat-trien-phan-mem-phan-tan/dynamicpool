package vn.edu.hust.student.dynamicpool.equation;

import java.awt.Point;

import vn.edu.hust.student.dynamicpool.equation.vector.Vector;


public abstract class Equation {

	public float t;
	
	
	public abstract Point move(float t);
	
	public abstract void hit(Vector vector);
	
	public abstract void setDirection(Vector vector);
}
