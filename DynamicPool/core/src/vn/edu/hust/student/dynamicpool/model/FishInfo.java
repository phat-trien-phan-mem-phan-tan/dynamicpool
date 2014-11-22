package vn.edu.hust.student.dynamicpool.model;

import java.awt.Point;
import java.util.ArrayList;

import vn.edu.hust.student.dynamicpool.bll.poolmanager.IPool;
import vn.edu.hust.student.dynamicpool.equation.IFishState;

public class FishInfo implements IFishState {

	// distance between center point border
	private float dx;
	private float dy;
	private Point point;

	public FishInfo() {
		this.dx = 0;
		this.dy = 0;
		this.point = new Point(0, 0);
	}

	public FishInfo(float dx, float dy, Point point) {
		this.dx = dx;
		this.dy = dy;
		this.point = point;
	}

	@Override
	public FishState getFishState(ArrayList<Segment> segmentList, IPool pool) {

		Point point = this.getPoint();

		// if hit
		double pointAboveBoundX = point.getX() + this.getDx();
		double pointUnderBoundX = point.getX() - this.getDx();

		double pointAboveBoundY = point.getY() + this.getDy();
		double pointUnderBoundY = point.getY() - this.getDy();
/*
		// check hit with oy
		if (pointAboveBoundX == pool.getWidth_limit() || pointUnderBoundX == 0) {

			// check can go through

			for (int i = 0; i < segmentList.size(); i++) {

				Segment segment = segmentList.get(i);

				Point beginPoint = segment.getBeginPoint();
				Point endPoint = segment.getEndPoint();
				if (endPoint.getX() == 0
						|| endPoint.getX() == pool.getWidth_limit()) {

					if (pointAboveBoundY < endPoint.getY()
							&& pointUnderBoundY > beginPoint.getY()) {
						return FishState.PASS;
					}

					return FishState.HIT_Y;
				}
			}

			// check hit with ox
		} else if (pointAboveBoundY == pool.getHigh_limit()
				|| pointUnderBoundY == 0) {

			// check can go through
			for (int i = 0; i < segmentList.size(); i++) {

				Segment segment = segmentList.get(i);

				Point beginPoint = segment.getBeginPoint();
				Point endPoint = segment.getEndPoint();
				if (endPoint.getY() == 0
						|| endPoint.getY() == pool.getHigh_limit()) {

					if (pointAboveBoundX < endPoint.getX()
							&& pointUnderBoundX > beginPoint.getX()) {

						return FishState.PASS;
					}

					return FishState.HIT_X;
				}
			}
		}*/
		return FishState.NONE;
	}

	
	public float getDx() {
		return dx;
	}

	public void setDx(float dx) {
		this.dx = dx;
	}

	public float getDy() {
		return dy;
	}

	public void setDy(float dy) {
		this.dy = dy;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

}
