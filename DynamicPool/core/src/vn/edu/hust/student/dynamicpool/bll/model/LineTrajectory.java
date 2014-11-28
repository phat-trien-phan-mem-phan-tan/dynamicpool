package vn.edu.hust.student.dynamicpool.bll.model;

import vn.edu.hust.student.dynamicpool.equation.vector.Oxy;
import vn.edu.hust.student.dynamicpool.equation.vector.Vector;

public class LineTrajectory extends Trajectory {

	/*
	 * x = x0+u1t; y = y0+u2t;
	 * 
	 * he so goc cua duong thang : k = u2/u1 v(u1,u2) la vec to chi phuong.
	 */

	// vector chi phuong
	private Vector u;
	private static final int A = 20;
	
	public LineTrajectory(Boundary fishPosition) {
		super(fishPosition);
		this.timeState = 0;
		u = new Vector(1, 1);
	}



	public LineTrajectory(Boundary fishPosition, Vector u, float timeState) {
		super(fishPosition);
		this.timeState = timeState;
		this.u = u;
	}
	
	@Override
	public void setDirection(Vector vector) {
		if (vector.equals(Oxy.ox)) {
			
			double b = - u.getY();
			u.setY(b);

		} else if (vector.equals(Oxy.oy)) {
			double a = 0 - u.getX();
			u.setX(a);

		}
	}

	public Vector getU() {
		return u;
	}

	public void setU(Vector u) {
		this.u = u;
	}

	@Override
	public ETrajectoryType getTrajectoryType() {
		return ETrajectoryType.LINE;
	}

	@Override
	public Boundary updateLocation(float deltaTime) {
		float x = (float) (fishPosition.getMinX() +A* u.getX() * deltaTime);
		float y = (float) (fishPosition.getMinY() +A* u.getY() * deltaTime);
		fishPosition.setLocation(new vn.edu.hust.student.dynamicpool.bll.model.Point(x, y));
		return fishPosition;
	}

}
