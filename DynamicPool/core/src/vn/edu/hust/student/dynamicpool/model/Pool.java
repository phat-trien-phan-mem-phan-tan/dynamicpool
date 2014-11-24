package vn.edu.hust.student.dynamicpool.model;

import java.util.ArrayList;

import vn.edu.hust.student.dynamicpool.bll.*;
import vn.edu.hust.student.dynamicpool.bll.poolmanager.IPool;
import vn.edu.hust.student.dynamicpool.dal.utils.AppConst;
import vn.edu.hust.student.dynamicpool.equation.vector.Oxy;

public class Pool implements IPool {

	private int id;
	private ArrayList<Segment> segmentsX = new ArrayList<>();

	private ArrayList<Segment> segmentsY = new ArrayList<>();
	private Rectangle position;

	private IFishManager fishManager;
	private float diagonal;

	public Pool(Rectangle position, IFishManager fishManager) {

		this.id = 0;
		this.position = position;
		this.fishManager = fishManager;
		this.diagonal = 0;

	}

	public Pool(Rectangle position, IFishManager fishManager, float diagonal) {

		this.id = 0;
		this.position = position;
		this.fishManager = fishManager;
		this.diagonal = diagonal;

	}

	public Pool(Rectangle position, IFishManager fishManager, float diagnoal,
			int id) {
		this.id = id;
		this.position = position;
		this.fishManager = fishManager;
		this.diagonal = diagnoal;
	}

	public Pool() {
		this(new Rectangle(), new FishManager());
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
		return position;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public IFishManager getFishManager() {
		return fishManager;
	}

	public void setFishManager(IFishManager fishManager) {
		this.fishManager = fishManager;
	}

	public float getDiagonal() {
		return diagonal;
	}

	public void setDiagonal(float diagonal) {
		this.diagonal = diagonal;
	}

	public Rectangle getPosition() {
		return position;
	}

	public void setSegmentsX(ArrayList<Segment> segmentsX) {
		this.segmentsX = segmentsX;
	}

	public void setSegmentsY(ArrayList<Segment> segmentsY) {
		this.segmentsY = segmentsY;
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
		for (int i = 0; i < fishes.size(); i++) {

			Fish fish = (Fish) fishes.get(i);

			fish.update(detatime);
			checkHit(detatime, position, fish);

		}
		return fishes;
	}

	private boolean checkHit(float detatime, Rectangle poolPosition, Fish fish) {
		float fishMinX = fish.getPosition().getMinX();
		float fishMinY = fish.getPosition().getMinY();
		float fishMaxX = fish.getPosition().getMaxX();
		float fishMaxY = fish.getPosition().getMaxY();

		float minY = poolPosition.getLocation().getY();
		float maxY = AppConst.height + minY;

		float minX = poolPosition.getLocation().getX();
		float maxX = AppConst.width + minX;

//		System.out.println("Pool: fisX: " + fishMinX + ", fishY: " + fishMinY
//				+ ", minX: " + minX + ", minY: " + minY + ", maxX: " + maxX
//				+ ", maxY: " + maxY);

		if (fishMinX <= minX || fishMaxX >= maxX) {
			fish.getTrajectory().setDirection(Oxy.oy);
			float newAngle = (float) Math.PI - fish.getAngle();
			fish.setAngle(newAngle);
			fish.setFishState(this, 1);
			return true;
		}
		if (fishMinY <= minY || fishMaxY >= maxY) {

			fish.getTrajectory().setDirection(Oxy.ox);
			fish.setFishState(this, 0);
			return true;
		}
		fish.setFishState(FishState.NONE);

		return false;

	}

	@Override
	public void setPosition(Rectangle rectangle) {
		Rectangle oldRectangle = this.position;
		oldRectangle.setHeight(rectangle.getHeight());
		oldRectangle.setWidth(rectangle.getWidth());
		oldRectangle.setLocation(rectangle.getLocation());

	}

}