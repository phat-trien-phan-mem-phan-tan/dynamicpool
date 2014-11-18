package vn.edu.hust.student.dynamicpool.model;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import vn.edu.hust.student.dynamicpool.bll.Fish;
import vn.edu.hust.student.dynamicpool.bll.FishManager;
import vn.edu.hust.student.dynamicpool.bll.FishPosition;
import vn.edu.hust.student.dynamicpool.bll.IFish;
import vn.edu.hust.student.dynamicpool.bll.IFishManager;
import vn.edu.hust.student.dynamicpool.bll.poolmanager.IPool;
import vn.edu.hust.student.dynamicpool.bll.poolmanager.IPoolPosition;
import vn.edu.hust.student.dynamicpool.bll.poolmanager.PoolPosition;
import vn.edu.hust.student.dynamicpool.equation.vector.Oxy;
import vn.edu.hust.student.dynamicpool.utils.AppConst;

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
	public IPoolPosition getCorrdiate() {
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
			if (fish.getFishState() == FishState.HIT_X) {

				if (fish.getPoint().getY() == poolPosition.getMinY()
						|| fish.getPoint().getY() == poolPosition.getMaxY()) {

					fish.getTrajectory().setDirection(Oxy.ox);
				}

			} else if (fish.getFishState() == FishState.HIT_Y) {
				if (fish.getPoint().getX() == poolPosition.getMinX()
						|| fish.getPoint().getX() == poolPosition.getMaxX()) {

					fish.getTrajectory().setDirection(Oxy.oy);
				}
			} else if (fish.getFishState() == FishState.NOT_PASS) {

				// xu ly phan cho ca chuyen dong tren vung co the di qua
			}

			FishPosition nextPosition = (FishPosition) fish
					.checkPosition(detatime + AppConst.timePass);

			fish.setFishState(this, nextPosition);

		}

		return fishes;
	}

}