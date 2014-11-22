package vn.edu.hust.student.dynamicpool.bll;

import vn.edu.hust.student.dynamicpool.equation.vector.Oxy;
import vn.edu.hust.student.dynamicpool.equation.vector.Vector;
import vn.edu.hust.student.dynamicpool.model.Point;
import vn.edu.hust.student.dynamicpool.model.Rectangle;

public class SinTrajectory extends Trajectory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myapp.equation.Equation#move(float)
	 * 
	 * x = x0+n*t; y = y0 + a*sin(t+angle)
	 */

	private float x0;
	private float y0;

	private float a;
	private float t;
	private static final int n = 10;

	private float angle;
	private int direction;

	public SinTrajectory(Rectangle fishPosition) {
		super(fishPosition);

		this.direction = 1;
		this.x0 = 0;
		this.y0 = 0;
		this.a = 50;
		this.angle = (float) (Math.PI / 2);
		this.t = 0;

	}

	@Override
	public ETrajectoryType getTrajectoryType() {
		return ETrajectoryType.SIN;
	}

	@Override
	public Rectangle updateCoordinate(float deltaTime) {
		t = t+direction*deltaTime;
		
		float x = (float) x0+n*t;
		float y = (float) (y0 + a*Math.sin(t + angle));
		fishPosition.setLocation(new Point(x, y));
		
		return getFishPosition();
	}

	@Override
	public void setDirection(Vector vector) {
		
		
		if (vector.equals(Oxy.ox)) {

			this.angle = (float) + (Math.PI - 2*(t+angle));
			

		} else if (vector.equals(Oxy.oy)) {
			
			this.t = -t;
			this.angle = -angle;
			x0 = (float) x0+n*t;
			direction = -1;
			
			float anpha = t+angle;
			
			float sinValue = (float) Math.sin(anpha);
			float cosValue = (float) Math.cos(anpha);
			
			
			if(sinValue  > 0){
			
				y0 = y0+2*a;
				
			}else if(sinValue  <0){
				y0 = y0- 2*a;
				
			}else{
				y0 = -y0;
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

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public static int getN() {
		return n;
	}

	
}
