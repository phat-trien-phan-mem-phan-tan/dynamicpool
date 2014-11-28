package vn.edu.hust.student.dynamicpool.bll.model;

import vn.edu.hust.student.dynamicpool.presentation.gameobject.EDirection;

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

	public CycleTrajectory(Boundary fishBoundary) {
		super(fishBoundary);

		this.x0 = 0;
		this.y0 = 0;

		this.a = 80;

		this.angle = (float) (-Math.PI / 2);
	}

	@Override
	public ETrajectoryType getTrajectoryType() {
		// TODO Auto-generated method stub
		return ETrajectoryType.CYCLE;
	}

	@Override
	public void updateLocation(float deltaTime) {
		increaseTimeState(deltaTime);
		t = t + deltaTime;
		float y = (float) (x0 + a * Math.sin(n * t + angle));
		float x = (float) (y0 + a * Math.cos(n * t + angle));

		System.out.println("Cycle: " + "Time: " + deltaTime + "x: " + x + "y: "
				+ y);
		fishBoundary.setLocation(new Point(x, y));
	}

	@Override
	public void changeDirection(EDirection direction) {

		/*
		 * // alpha < 2PI float T = (float) ((n*t + angle) / (2 * Math.PI)); T =
		 * (int) T + 1;
		 */
		// tinh goc bu
		/* float beta = (float) (T * 2 * Math.PI - ( t + angle)); */

		//
		float anpha = (float) (n * t + angle);

		float detaAngle = (float) (Math.PI - 2 * anpha);

		// increase angle
		// setAngle(2 * beta + angle);
		float sinValue = (float) Math.sin(anpha);
		float cosValue = (float) Math.cos(anpha);
		switch (direction) {
		case LEFT:
		case RIGHT:
			x0 = x0 + 2 * a * cosValue;
			if (cosValue < 0) {
				angle = angle + Math.abs(detaAngle);
			} else {
				angle = angle - Math.abs(detaAngle);
			}
			break;
		case TOP:
		case BOTTOM:
		default:
			y0 = y0 + 2 * a * sinValue;
			if (sinValue < 0) {
				angle = angle + Math.abs(detaAngle);
			} else {
				angle = angle - Math.abs(detaAngle);
			}
			break;
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

	@Override
	public EDirection getHorizontalDirection() {
		return EDirection.RIGHT;
	}

}
