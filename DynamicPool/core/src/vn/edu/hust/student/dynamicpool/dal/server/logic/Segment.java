package vn.edu.hust.student.dynamicpool.dal.server.logic;

import java.awt.Point;

public class Segment {
	private Point pointStart;
	private Point pointEnd;

	public Segment(Point start, Point end) {
		pointStart = start;
		pointEnd = end;
	}

	public Point getPointStart() {
		return pointStart;
	}

	public void setPointStart(Point pointStart) {
		this.pointStart = pointStart;
	}

	public Point getPointEnd() {
		return pointEnd;
	}

	public void setPointEnd(Point pointEnd) {
		this.pointEnd = pointEnd;
	}
	
	@Override
	public String toString() {
		String s = "Start: [%s,%s] End: [%s,%s]";
		return String.format(s, pointStart.getX(), pointStart.getLocation().getY(), pointEnd.getLocation().getX(), pointEnd.getLocation().getY());
	}
}
