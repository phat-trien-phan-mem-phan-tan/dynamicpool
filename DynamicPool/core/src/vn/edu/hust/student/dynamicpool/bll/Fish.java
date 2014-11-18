package vn.edu.hust.student.dynamicpool.bll;

import vn.edu.hust.student.dynamicpool.model.FishState;

public class Fish implements IFish {
	private IFishPosition position;
	private Trajectory trajectory;
	
	
	private int id;
	private float dx;
	private float dy;
	private FishState fishState;
	
	
	public Fish(IFishPosition fishPosition, Trajectory trajectory) {
		this.position = fishPosition;
		this.trajectory = trajectory;
		this.id = 0;
		this.dx = 0;
		this.dy = 0;
		this.fishState = FishState.NONE;
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
		
		/*return this.trajectory.updateCoordinate(deltaTime);*/
		return position;
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
}
