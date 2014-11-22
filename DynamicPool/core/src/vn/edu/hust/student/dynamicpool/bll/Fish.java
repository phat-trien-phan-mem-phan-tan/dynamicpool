package vn.edu.hust.student.dynamicpool.bll;

import vn.edu.hust.student.dynamicpool.model.FishState;
import vn.edu.hust.student.dynamicpool.model.Pool;
import vn.edu.hust.student.dynamicpool.model.Rectangle;

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

	public Fish(int id){
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
	
	public Rectangle checkPosition(float deltaTime){
		
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
	
	public void setFishId(int id){
		this.id = id;
	}

	@Override
	public FishState getFishState() {
		return fishState;
	}

	public FishState getFishState(Rectangle fishPosition){
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
	
	
	public void setFishState(Pool pool, Rectangle position)
	{
	/*	float x = (float) position.getX();
		float y = (float) position.getY();

		// if hit
		float pointAboveBoundX = x + dx;
		double pointUnderBoundX = x - dx;

		double pointAboveBoundY = y + dy;
		double pointUnderBoundY = y - dy;

		
		double minX = pool.getCorrdiate().getPosition().getHeight();
		double maxX = pool.getCorrdiate().getPosition().get;
		
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

		}*/
		fishState = FishState.NONE;
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
