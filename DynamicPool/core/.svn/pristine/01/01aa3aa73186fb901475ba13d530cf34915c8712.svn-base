package vn.edu.hust.student.dynamicpool.bll;

public class LineTrajectory extends Trajectory {
	private float amplitude = 1f;
	private float dx = 1f;
	private float dy = 1f;
	
	public LineTrajectory(IFishPosition fishPosition) {
		super(fishPosition);
	}

	@Override
	public IFishPosition updateCoordinate(float deltaTime) {
		IFishPosition position = getFishPosition();
		position.increaseX(dx * amplitude);
		position.increaseY(dy * amplitude);
		return getFishPosition();
	}

	@Override
	public ETrajectoryType getTrajectoryType() {
		return ETrajectoryType.LINE;
	}
}
