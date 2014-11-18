package vn.edu.hust.student.dynamicpool.bll;

import java.awt.Point;

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
	
	public LineTrajectory(IFishPosition fishPosition) {
		
		
		super(fishPosition);
		this.timeState = 0;
		u = new Vector();
		
		
		// TODO Auto-generated constructor stub
	}

	/*
	 * private float amplitude = 1f; private float dx = 1f; private float dy =
	 * 1f;
	 * 
	 * public LineTrajectory(IFishPosition fishPosition) { super(fishPosition);
	 * }
	 * 
	 * @Override public IFishPosition updateCoordinate(float deltaTime) {
	 * IFishPosition position = getFishPosition(); position.increaseX(dx *
	 * amplitude); position.increaseY(dy * amplitude); return getFishPosition();
	 * }
	 */

	
	public LineTrajectory(IFishPosition fishPosition,Vector u,float timeState){
		super(fishPosition);
		this.timeState = timeState;
		this.u = u;
	}
	
	

	public Point move(float t) {

		
		float x = (float) (fishPosition.getX()+ u.getX()*t);
		float y = (float) (fishPosition.getY() + u.getY()*t);
		
		Point point = new Point();
		point.setLocation(x, y);
		return point;
	}


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
	public IFishPosition updateCoordinate(float deltaTime) {
		// TODO Auto-generated method stub
		
		float x = (float) (fishPosition.getX()+ u.getX()*deltaTime);
		float y = (float) (fishPosition.getY() + u.getY()*deltaTime);
		
		FishPosition fishPosition = new FishPosition();
		fishPosition.setX(x);
		fishPosition.setY(y);
		
		return fishPosition;
	}
}
