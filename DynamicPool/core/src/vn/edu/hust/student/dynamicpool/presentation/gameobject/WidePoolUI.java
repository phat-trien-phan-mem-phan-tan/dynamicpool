package vn.edu.hust.student.dynamicpool.presentation.gameobject;

import vn.edu.hust.student.dynamicpool.bll.model.Boundary;
import vn.edu.hust.student.dynamicpool.bll.model.IFish;
import vn.edu.hust.student.dynamicpool.bll.model.Point;
import vn.edu.hust.student.dynamicpool.bll.model.Pool;
import vn.edu.hust.student.dynamicpool.bll.model.PoolManager;
import vn.edu.hust.student.dynamicpool.bll.model.Segment;
import vn.edu.hust.student.dynamicpool.events.EventDestination;
import vn.edu.hust.student.dynamicpool.events.EventType;
import vn.edu.hust.student.dynamicpool.utils.AppConst;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class WidePoolUI {
	private static final Color POOL_COLOR = new Color(255, 255, 255, 0.1f);
	private static final Color FISH_COLOR = new Color(255, 255, 0, 1);
	private static final Color SEGMENT_COLOR = new Color(0, 255, 255, 0.1f);
	CordinateConvert convert = new CordinateConvert();
	private PoolManager hostPoolManager;
	private ShapeRenderer shapeRenderer;

	public WidePoolUI(PoolManager poolManager) {
		this.hostPoolManager = poolManager;
	}

	public void draw(ShapeRenderer shapeRenderer) {
		this.shapeRenderer = shapeRenderer;
		updateChange();
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(POOL_COLOR);
		for (Pool pool : hostPoolManager.getPools()) {
			this.drawPool(pool);
		}
		shapeRenderer.end();
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(FISH_COLOR);
		for (Pool pool : hostPoolManager.getPools()) {
			for (IFish fish : pool.getFishes()) {
				this.drawFish(fish);
			}
		}
		shapeRenderer.end();
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(SEGMENT_COLOR);
		for (Pool pool : hostPoolManager.getPools()) {
			for (Segment segment : pool.getSegments()) {
				this.drawSegment(pool, segment);
			}
		}
		shapeRenderer.end();
	}

	private void drawSegment(Pool pool, Segment segment) {
		float x1 = 0, y1 = 0, x2 = 0, y2 = 0;
		Boundary poolBoundary = pool.getBoundary();
		switch (segment.getSegmentDirection()) {
		case LEFT:
			x1 = x2 = poolBoundary.getMinX();
			y1 = segment.getBeginPoint();
			y2 = segment.getEndPoint();
			break;
		case RIGHT:
			x1 = x2 = poolBoundary.getMaxX();
			y1 = segment.getBeginPoint();
			y2 = segment.getEndPoint();
			break;
		case TOP:
			y1 = y2 = poolBoundary.getMaxY();
			x1 = segment.getBeginPoint();
			x2 = segment.getEndPoint();
			break;
		case BOTTOM:
			y1 = y2 = poolBoundary.getMinY();
			x1 = segment.getBeginPoint();
			x2 = segment.getEndPoint();
			break;
		default:
			return;
		}
		shapeRenderer.line(x1, y1, x2, y2);
	}

	private void drawPool(Pool pool) {
		Boundary poolBoundary = convert.convertBoundary(pool.getBoundary());
		shapeRenderer.rect(poolBoundary.getMinX(), poolBoundary.getMinY(),
				poolBoundary.getWidth(), poolBoundary.getHeight());
	}

	private void drawFish(IFish fish) {
		Boundary boundary = convert.convertBoundary(fish.getBoundary());
		shapeRenderer.rect(boundary.getMinX(), boundary.getMinY(),
				boundary.getWidth(), boundary.getHeight());
	}

	public void updateChange() {
		float minX = 0, minY = 0, maxX = 0, maxY = 0;
		for (Pool pool : hostPoolManager.getPools()) {
			minX = Math.min(minX, pool.getBoundary().getMinX());
			minY = Math.min(minY, pool.getBoundary().getMinY());
			maxX = Math.max(maxX, pool.getBoundary().getMaxX());
			maxY = Math.max(maxY, pool.getBoundary().getMaxY());
		}
		Boundary acturalBoundary = new Boundary(new Point(minX, maxY), maxX
				- minX, maxY - minY);
		convert.setActuralBoundary(acturalBoundary);
		Boundary vituralBoundary = new Boundary(
				new Point(AppConst.width / 4, 0), AppConst.width / 2,
				AppConst.height / 2);
		convert.setVituralBoundary(vituralBoundary);
	}
}
