package vn.edu.hust.student.dynamicpool.presentation.gameobject;

import vn.edu.hust.student.dynamicpool.bll.model.Boundary;
import vn.edu.hust.student.dynamicpool.bll.model.Point;
import vn.edu.hust.student.dynamicpool.bll.model.Pool;
import vn.edu.hust.student.dynamicpool.bll.model.PoolManager;
import vn.edu.hust.student.dynamicpool.events.EventDestination;
import vn.edu.hust.student.dynamicpool.events.EventType;
import vn.edu.hust.student.dynamicpool.utils.AppConst;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class WidePoolUI {
	private static final Color POOL_COLOR = new Color(255, 255, 255, 0.1f);
	CordinateConvert convert = new CordinateConvert();
	private PoolManager hostPoolManager;
	private ShapeRenderer shapeRenderer;

	public WidePoolUI(PoolManager poolManager) {
		this.hostPoolManager = poolManager;
	}

	public void draw(ShapeRenderer sharpeRenderer) {
		this.shapeRenderer = sharpeRenderer;
		updateChange();
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(POOL_COLOR);
//		shapeRenderer.set(ShapeType.Line);
		for (Pool pool : hostPoolManager.getPools()) {
			this.drawPool(pool);
		}
		shapeRenderer.end();
	}

	private void drawPool(Pool pool) {
		Boundary poolBoundary = convert.convertBoundary(pool.getBoundary());
		shapeRenderer.rect(poolBoundary.getMinX(), poolBoundary.getMinY(),
				poolBoundary.getWidth(), poolBoundary.getHeight());
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
