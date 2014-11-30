package vn.edu.hust.student.dynamicpool.presentation.gameobject;

import vn.edu.hust.student.dynamicpool.bll.model.Boundary;
import vn.edu.hust.student.dynamicpool.bll.model.Point;
import vn.edu.hust.student.dynamicpool.utils.AppConst;

public class CordinateConvert {

	private Boundary acturalBoundary = new Boundary(new Point(), AppConst.width, AppConst.height);
	private Boundary vituralBoundary = new Boundary(new Point(AppConst.width/4, 0), AppConst.width/2, AppConst.height/2);
	private float scale = 1;

	public void setActuralBoundary(Boundary acturalBoundary) {
		this.acturalBoundary = acturalBoundary;
		updateScale();
	}

	private void updateScale() {
		this.scale = vituralBoundary.getWidth() / acturalBoundary.getWidth();
	}

	public void setVituralBoundary(Boundary vituralBoundary) {
		this.vituralBoundary = vituralBoundary;
		updateScale();
	}

	public Boundary convertBoundary(Boundary boundary) {
		return new Boundary(convertLocation(boundary.getLocation()),
				convertFloat(boundary.getWidth()),
				convertFloat(boundary.getHeight()));
	}

	public Point convertLocation(Point point) {
		Point p = convertPoint(point);
		return new Point(p.getX()+vituralBoundary.getMinX(), p.getY()+vituralBoundary.getMinY());
	}
	
	private float convertFloat(float value) {
		return value * scale;
	}

	private Point convertPoint(Point point) {
		return new Point(point.getX() * scale, point.getY() * scale);
	}

	public Boundary convertBoundaryToOriginal(Boundary boundary) {
		Boundary b = convertBoundary(boundary);
		b.getLocation().setX(b.getWidth()/2+b.getLocation().getX());
		b.getLocation().setY(b.getHeight()/2+b.getLocation().getY());
		return b;
	}
}
