package vn.edu.hust.student.dynamicpool.bll;

import vn.edu.hust.student.dynamicpool.equation.vector.Oxy;
import vn.edu.hust.student.dynamicpool.equation.vector.Vector;

public class CycleTrajectory extends Trajectory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myapp.equation.Equation#move(float)
	 * 
	 * x0,y0 toa goc a he so t goc
	 * 
	 * y = y0+aSin(nt + angle) x = x0+aCos(nt + angle)
	 */
	
	private float x0;
	private float y0;

	private float a;
	
	private float angle;

	private float t;
	
	
	public CycleTrajectory(IFishPosition fishPosition) {
		super(fishPosition);
		
		this.x0 = 30;
		this.y0 = 30;
		
		this.a = 80;
		
		this.angle = (float) (-Math.PI/2);
	}

	@Override
	public ETrajectoryType getTrajectoryType() {
		// TODO Auto-generated method stub
		return ETrajectoryType.CYCLE;
	}

	@Override
	public IFishPosition updateCoordinate(float deltaTime) {

		float y = (float) (x0 + a*Math.sin(t+angle));
		float x = (float) (y0 + a*Math.cos(t+angle));
		
		fishPosition.setX(x);
		fishPosition.setY(y);

		System.out.println("LineTrajectory: " + "X :" + x + " Y :" + y);

		
		return fishPosition;
	}


	@Override
	public void setDirection(Vector vector) {

		// alpha < 2PI
		float T = (float) ((t + angle) / (2 * Math.PI));
		T = (int) T + 1;

		// tinh goc bu
	/*	float beta = (float) (T * 2 * Math.PI - ( t + angle));*/

		//
		float anpha = (float) (t+angle);
		float detaAngle = (float)(2*anpha - Math.PI);
		
		// increase angle
		//setAngle(2 * beta + angle);

		if (vector.equals(Oxy.ox)) {
			// check cos  to set center point
			float cosValue = (float) Math.cos(t + angle);

			x0 = x0 + 2 * a * cosValue;

			if (cosValue < 0) {
					
				angle = angle+Math.abs(detaAngle);
				
			} else {
				angle = angle - Math.abs(detaAngle);
				
			}

			setX0(x0);

		} else if (vector.equals(Oxy.oy)) {

			// check sin (anphal) to set center point
			float sinValue = (float) Math.sin( t + angle);
	

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

