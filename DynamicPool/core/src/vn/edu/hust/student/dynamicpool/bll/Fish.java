package vn.edu.hust.student.dynamicpool.bll;

import java.util.ArrayList;

import vn.edu.hust.student.dynamicpool.model.FishState;
import vn.edu.hust.student.dynamicpool.model.Pool;
import vn.edu.hust.student.dynamicpool.model.Rectangle;
import vn.edu.hust.student.dynamicpool.model.Segment;

public class Fish implements IFish {
	private Rectangle position;
	private Trajectory trajectory;
	private int id;
	private ETrajectoryType trajectoryType;
	private FishType fishType;

	private FishState fishState;
	private float angle;

	public Fish(Rectangle fishPosition, Trajectory trajectory) {
		this.position = fishPosition;
		this.trajectory = trajectory;
		this.fishState = FishState.NONE;
		trajectoryType = ETrajectoryType.LINE;
		this.fishType = FishType.FISH1;
	}

	public Fish(Rectangle fishPosition) {
		this(fishPosition, new NoneTrajectory());
	}

	public Fish() {
		this(new Rectangle(), new NoneTrajectory());
	}

	public Fish(int id) {
		this(new Rectangle(), new NoneTrajectory());
		this.id = id;
	}

	@Override
	public Rectangle getPosition() {
		return position;
	}

	@Override
	public Rectangle update(float deltaTime) {
		position = this.trajectory.updateCoordinate(deltaTime);
		return position;
	}

	public Rectangle checkPosition(float deltaTime) {

		return this.trajectory.updateCoordinate(deltaTime);
	}

	public Trajectory getTrajectory() {
		return trajectory;
	}

	public void setTrajectory(Trajectory trajectory) {
		this.trajectory = trajectory;
	}

	@Override
	public int getFishId() {
		return id;
	}

	public void setFishId(int id) {
		this.id = id;
	}

	@Override
	public FishState getFishState() {
		return fishState;
	}

	public FishState getFishState(Rectangle fishPosition) {
		return fishState;

	}

	@Override
	public void setFishState(FishState fishState) {
		this.fishState = fishState;
	}

	public ETrajectoryType getTrajectoryType() {
		return trajectoryType;
	}

	public void setTrajectoryType(ETrajectoryType trajectoryType) {
		this.trajectoryType = trajectoryType;
	}

	public FishType getFishType() {
		return fishType;
	}

	public void setFishType(FishType fishType) {
		this.fishType = fishType;
	}

	/***
	 * kiem tra vi tri tiep theo cua con ca sau moi lan update
	 * 
	 */

	public void setFishState(Pool pool, int direction) {

		float fishMinX = position.getMinX();
		float fishMinY = position.getMinY();
		float fishMaxX = position.getMaxX();
		float fishMaxY = position.getMaxY();

		// hit OX
		if (direction == 1) {

			ArrayList<Segment> segmentsX = pool.getSegmentsX();
			for (int i = 0; i < segmentsX.size(); i++) {
				if (fishMinX >= segmentsX.get(i).getBeginPoint().getX()
						&& fishMaxX <= segmentsX.get(i).getEndPoint().getX()) {

					this.setFishState(FishState.NOT_PASS);

				}
			}

			// hit Oy
		} else if (direction == 0) {
			ArrayList<Segment> segmentsY = pool.getSegmentsY();

			for (int i = 0; i < segmentsY.size(); i++) {
				if (fishMinY >= segmentsY.get(i).getBeginPoint().getY()
						&& fishMaxY <= segmentsY.get(i).getEndPoint().getY()) {

					this.setFishState(FishState.NOT_PASS);

				}
			}
		}

	}

	@Override
	public void setAngle(float angle) {
		this.angle = angle;
	}

	@Override
	public float getAngle() {
		return angle;
	}
}
