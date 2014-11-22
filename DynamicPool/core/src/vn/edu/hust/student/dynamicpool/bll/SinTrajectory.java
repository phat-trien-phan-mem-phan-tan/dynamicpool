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
	 * x = x0+t; y = y0 + a*sin(t+angle)
	 */

	private float x0;
	private float y0;

	private float a;
	private float t;

	private float angle;

	public SinTrajectory(Rectangle fishPosition) {
		super(fishPosition);

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
		t = t+deltaTime;
		
		float x = (float) x0+t;
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
			
			float checkSinValue = (float) Math.sin(t+angle);
			if(checkSinValue > 0){
			
				y0 = y0+2*a;
			}else if(checkSinValue <0){
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

}
