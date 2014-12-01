package vn.edu.hust.student.dynamicpool.bll.model;


public class LineTrajectory extends Trajectory {
	private float dx = 1, dy = 1;
	private static final int A = 20;

	public LineTrajectory(Boundary fishBoundary) {
		super(fishBoundary);
	}
	
	public void init(float dx, float dy) {
		this.dx = dx;
		this.dy = dy;
	}

	@Override
	public void changeDirection(EDirection hitTo) {
		switch (hitTo) {
		case TOP:
		case BOTTOM:
			dy = -dy;
			break;
		case LEFT:
		case RIGHT:
		default:
			dx = -dx;
			break;
		}
	}

	@Override
	public ETrajectoryType getTrajectoryType() {
		return ETrajectoryType.LINE;
	}

	@Override
	public Point updateLocation(float deltaTime) {
		increaseTimeState(deltaTime);
		float x = (float) (fishBoundary.getMinX() + A * dx * deltaTime);
		float y = (float) (fishBoundary.getMinY() + A * dy * deltaTime);
		this.getFishBoundary().getLocation().setLocation(x, y);
		return getFishBoundary().getLocation();
	}

	@Override
	public EDirection getHorizontalDirection() {
		return dx < 0 ? EDirection.LEFT : EDirection.RIGHT;
	}

	@Override
	public Trajectory clone() {
		LineTrajectory t = new LineTrajectory(fishBoundary.clone());
		t.init(dx, dy);
		t.setTimeState(getTimeState());
		return t;
	}
}
