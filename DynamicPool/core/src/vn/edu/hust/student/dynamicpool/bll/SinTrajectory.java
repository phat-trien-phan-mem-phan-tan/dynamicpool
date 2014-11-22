package vn.edu.hust.student.dynamicpool.bll;

import vn.edu.hust.student.dynamicpool.equation.vector.Oxy;
import vn.edu.hust.student.dynamicpool.equation.vector.Vector;

public class SinTrajectory extends Trajectory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.myapp.equation.Equation#move(float)
	 * 
	 * x = x0 + t; y = y0 + a*sin(t+angle)
	 */

	private float x0;
	private float y0;

	private float a;
	private float t;

	private float angle;

	public SinTrajectory(IFishPosition fishPosition) {
		super(fishPosition);

		this.x0 = fishPosition.getX();
		this.y0 = fishPosition.getY();
		this.a = 50;
		this.angle = (float) (Math.PI / 2);
		this.t = 0;

	}

	@Override
	public ETrajectoryType getTrajectoryType() {
		return ETrajectoryType.SIN;
	}

	@Override
	public IFishPosition updateCoordinate(float deltaTime) {
		float x = (float) fishPosition.getX() + deltaTime;

		float y = (float) (fishPosition.getY() + a
				* Math.sin(deltaTime + angle));

		fishPosition.setX(x);
		fishPosition.setY(y);

		System.out.println("LineTrajectory: " + "X :" + x + " Y :" + y);

		return getFishPosition();
	}

	@Override
	public void setDirection(Vector vector) {

		if (vector.equals(Oxy.ox)) {

			this.angle = (float) + (Math.PI - 2*(t+angle));
			

		} else if (vector.equals(Oxy.oy)) {
			
			

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
