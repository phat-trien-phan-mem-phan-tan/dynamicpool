package vn.edu.hust.student.dynamicpool.bll;

import java.awt.Point;
import java.util.ArrayList;

import vn.edu.hust.student.dynamicpool.model.FishState;
import vn.edu.hust.student.dynamicpool.model.Pool;
import vn.edu.hust.student.dynamicpool.model.Segment;

public class Fish implements IFish {
	private IFishPosition position;
	private Trajectory trajectory;
	
	
	private int id;
	private float dx;
	private float dy;
	private ETrajectoryType trajectoryType;
	private FishType fishType;
	
	private FishState fishState;	
	
	public Fish(IFishPosition fishPosition, Trajectory trajectory) {
		this.position = fishPosition;
		this.trajectory = trajectory;
		this.id = 0;
		this.dx = 0;
		this.dy = 0;
		this.fishState = FishState.NONE;
		trajectoryType = ETrajectoryType.LINE;
		this.fishType = FishType.FISH1;
	}

	
	public Fish(IFishPosition fishPosition) {
		this(fishPosition, new NoneTrajectory());
	}
	
	public Fish() {
		this(new FishPosition(), new NoneTrajectory());
	}

	public Fish(int id){
		this(new FishPosition(), new NoneTrajectory());
		this.id = id;
	}
	
	@Override
	public IFishPosition getPoint() {
		return position;
	}

	@Override
	public IFishPosition update(float deltaTime) {
	
		position = this.trajectory.updateCoordinate(deltaTime);
		return position;
		
	}
	
	public IFishPosition checkPosition(float deltaTime){
		
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
		// TODO Auto-generated method stub
		return id;
	}
	
	public void setFishId(int id){
		this.id = id;
	}


	@Override
	public void setDx(float dx) {
		// TODO Auto-generated method stub
		
		this.dx = dx;
	}


	@Override
	public float getDx() {
		// TODO Auto-generated method stub
		return dx;
	}


	@Override
	public void setDy(float dy) {
		// TODO Auto-generated method stub
		this.dy = dy;
	}


	@Override
	public float getDy() {
		// TODO Auto-generated method stub
		return dy;
	}


	@Override
	public FishState getFishState() {
		// TODO Auto-generated method stub
		return fishState;
	}

	public FishState getFishState(IFishPosition fishPosition){
		
		return fishState;
		
	}

	@Override
	public void setFishState(FishState fishState) {
		// TODO Auto-generated method stub
		
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
	
	
	public void setFishState(Pool pool,FishPosition position)
	{
		float x = (float) position.getX();
		float y = (float) position.getY();

		// if hit
		double pointAboveBoundX = x + dx;
		double pointUnderBoundX = x - dx;

		double pointAboveBoundY = y + dy;
		double pointUnderBoundY = y - dy;

		
		double maxX = pool.getCorrdiate().getPosition().getMaxX();
		double minX = pool.getCorrdiate().getPosition().getMinX();

		double maxY = pool.getCorrdiate().getPosition().getMaxY();
		double minY = pool.getCorrdiate().getPosition().getMinY();

		
		ArrayList<Segment> segmentsX = pool.getSegmentsX();
		ArrayList<Segment> segmentsY = pool.getSegmentsY();
		
		// check hit with oy
		if (pointAboveBoundX == maxX || pointUnderBoundX == minX) {

			
			// check can go through
			for (int i = 0; i < segmentsY.size(); i++) {

				Segment segment = segmentsY.get(i);

				Point beginPoint = segment.getBeginPoint();
				Point endPoint = segment.getEndPoint();

				if (pointAboveBoundY < endPoint.getY()
						&& pointUnderBoundY > beginPoint.getY()) {
					fishState = FishState.NOT_PASS;
				}
				fishState = FishState.HIT_Y;
			}

			// check hit with ox
		} else if (pointAboveBoundY == maxY || pointUnderBoundY == minY) {

			// check can go through
			for (int i = 0; i < segmentsX.size(); i++) {

				Segment segment = segmentsX.get(i);

				Point beginPoint = segment.getBeginPoint();
				Point endPoint = segment.getEndPoint();

				if (pointAboveBoundX < endPoint.getX()
						&& pointUnderBoundX > beginPoint.getX()) {

					fishState = FishState.NOT_PASS;
				}

				fishState = FishState.HIT_X;;
			}

		}
		fishState = FishState.NONE;
	}
}
