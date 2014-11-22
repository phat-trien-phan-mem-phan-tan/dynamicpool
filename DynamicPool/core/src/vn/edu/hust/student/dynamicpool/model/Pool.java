package vn.edu.hust.student.dynamicpool.model;

import java.util.ArrayList;

import vn.edu.hust.student.dynamicpool.bll.Fish;
import vn.edu.hust.student.dynamicpool.bll.FishManager;
import vn.edu.hust.student.dynamicpool.bll.FishPosition;
import vn.edu.hust.student.dynamicpool.bll.IFish;
import vn.edu.hust.student.dynamicpool.bll.IFishManager;
import vn.edu.hust.student.dynamicpool.bll.poolmanager.IPool;
import vn.edu.hust.student.dynamicpool.bll.poolmanager.IPoolPosition;
import vn.edu.hust.student.dynamicpool.bll.poolmanager.PoolPosition;
import vn.edu.hust.student.dynamicpool.dal.utils.AppConst;
import vn.edu.hust.student.dynamicpool.equation.vector.Oxy;

public class Pool implements IPool {

	private int id;
	private ArrayList<Segment> segmentsX;

	private ArrayList<Segment> segmentsY;
	private IPoolPosition position;

	private IFishManager fishManager;
	private float diagonal;

	public Pool(IPoolPosition position, IFishManager fishManager) {

		this.id = 0;
		this.position = position;
		this.fishManager = fishManager;
		this.diagonal = diagonal;

	}

	public Pool(IPoolPosition position, IFishManager fishManager, float diagonal) {

		this.id = 0;
		this.position = position;
		this.fishManager = fishManager;
		this.diagonal = diagonal;

	}

	public Pool(IPoolPosition position, IFishManager fishManager,
			float diagnoal, int id) {
		this.id = id;
		this.position = position;
		this.fishManager = fishManager;
		this.diagonal = diagnoal;
	}

	public Pool() {
		this(new PoolPosition(), new FishManager());
	}

	@Override
	public int getPoolId() {

		return id;
	}

	public void setPoolId(int id) {
		this.id = id;
	}

	@Override
	public Rectangle getCorrdiate() {
		return position.getPosition();
	}

	@Override
	public IFishManager getFishCollection() {

		return fishManager;
	}

	@Override
	public void setSegments(ArrayList<Segment> segments) {

		if (segments != null) {

			for (int i = 0; i < segments.size(); i++) {

				Point beginPoint = segments.get(i).getBeginPoint();
				Point endPoint = segments.get(i).getEndPoint();

				if (beginPoint.getX() == endPoint.getX()) {
					segmentsY.add(segments.get(i));
				} else {
					segmentsX.add(segments.get(i));
				}
			}
		}
	}

	@Override
	public ArrayList<Segment> getSegmentsX() {

		return segmentsX;
	}

	@Override
	public ArrayList<Segment> getSegmentsY() {

		return segmentsY;
	}

	@Override
	public ArrayList<IFish> updatePosition(float detatime) {

		ArrayList<IFish> fishes = (ArrayList<IFish>) fishManager.getFishs();
		Rectangle poolPosition = position.getPosition();

		for (int i = 0; i < fishes.size(); i++) {

			Fish fish = (Fish) fishes.get(i);

			fish.update(detatime);
			checkHit(detatime, poolPosition, fish);

		}

		return fishes;
	}

	private void checkHit(float detatime, Rectangle poolPosition, Fish fish) {
		double fishMinX = fish.getPoint().getX();
		double fishMinY = fish.getPoint().getY();
		double fishMaxX = fish.getPoint().getX() + fish.getDx();
		double fishMaxY = fish.getPoint().getY() + fish.getDy();

		double minY = poolPosition.getLocation().getY();
		double maxY = AppConst.height + minY;
		
		double minX = poolPosition.getLocation().getX();
		double maxX = AppConst.width + minX;

		System.out.println("Pool: fisX: " + fishMinX + ", fishY: " + fishMinY
				+ ", minX: " + minX + ", minY: " + minY + ", maxX: " + maxX
				+ ", maxY: " + maxY);
		
		if (fishMinX <= minX || fishMaxX >= maxX){
			fish.getTrajectory().setDirection(Oxy.oy);
			float newAngle = (float) (Math.PI - fish.getPoint().getAngle()); 
			fish.getPoint().setAngle(newAngle);
		}
		if (fishMinY <= minY || fishMaxY >= maxY) fish.getTrajectory().setDirection(Oxy.ox);
		FishPosition nextPosition = (FishPosition) fish.checkPosition(detatime
				+ AppConst.timePass);
		

	}

	@Override
	public void setPosition(Rectangle rectangle) {
		
		Rectangle oldRectangle = this.position.getPosition();
		oldRectangle.setHeight(rectangle.getHeight());
		oldRectangle.setWidth(rectangle.getWidth());
		oldRectangle.setLocation(rectangle.getLocation());

	}

}