package vn.edu.hust.student.dynamicpool.bll;

import vn.edu.hust.student.dynamicpool.equation.vector.Oxy;
import vn.edu.hust.student.dynamicpool.equation.vector.Vector;
import vn.edu.hust.student.dynamicpool.model.Point;
import vn.edu.hust.student.dynamicpool.model.Rectangle;

public class CycleTrajectory extends Trajectory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myapp.equation.Equation#move(float)
	 * 
	 * x0,y0 toa goc a he so t goc
	 * 
	 * y = y0+aSin(t + angle) x = x0+aCos(t + angle)
	 */
	
	private float x0;
	private float y0;

	private float a;
	
	private float angle;
	

	private float t;
	
	private static final int n = 1;
	
	
	public CycleTrajectory(Rectangle fishPosition) {
		super(fishPosition);
		
		this.x0 = 0;
		this.y0 = 0;
		
		this.a = 80;
		
		this.angle = (float) (-Math.PI/2);
	}

	@Override
	public ETrajectoryType getTrajectoryType() {
		// TODO Auto-generated method stub
		return ETrajectoryType.CYCLE;
	}

	@Override
	public Rectangle updateCoordinate(float deltaTime) {
		
		t = t+deltaTime;
		float y = (float) (x0 + a*Math.sin(n*t+angle));
		float x = (float) (y0 + a*Math.cos(n*t+angle));
		
		System.out.println("Cycle: "+"Time: "+deltaTime +"x: "+x+"y: "+y);
		fishPosition.setLocation(new Point(x, y));
		
		return fishPosition;
	}


	@Override
	public void setDirection(Vector vector) {

		// alpha < 2PI
		float T = (float) ((n*t + angle) / (2 * Math.PI));
		T = (int) T + 1;

		// tinh goc bu
	/*	float beta = (float) (T * 2 * Math.PI - ( t + angle));*/

		//
		float anpha = (float) (n*t+angle);
		
		float temp = (float) (Math.PI - 2*anpha);
		
		float detaAngle = temp;
		
		// increase angle
		//setAngle(2 * beta + angle);
		float sinValue = (float) Math.sin(anpha);
		float cosValue = (float) Math.cos(anpha);

		if (vector.equals(Oxy.ox)) {
			// check cos  to set center point
			

			x0 = x0 + 2 * a * cosValue;

			if ( cosValue < 0) {
					
				angle = angle+Math.abs(detaAngle);
				
			} else {
				angle = angle - Math.abs(detaAngle);
				
			}

		

		} else if (vector.equals(Oxy.oy)) {

			
			// move center point of circle
			y0 = y0 + 2 * a * sinValue;

			if (cosValue < 0) {

				angle = angle+Math.abs(detaAngle);
				
			} else {

				angle = angle - Math.abs(detaAngle);
				
			}

		}

	}
	
	
	public double getX0() {
		return x0;
	}

	public void setX0(float x0) {
		this.x0 = x0;
	}

	public double getY0() {
		return y0;
	}

	public void setY0(float y0) {
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

	public double getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	
}

