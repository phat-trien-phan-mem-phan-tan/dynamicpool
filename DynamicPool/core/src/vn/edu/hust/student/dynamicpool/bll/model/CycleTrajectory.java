package vn.edu.hust.student.dynamicpool.bll.model;

import java.util.Random;

import org.eclipse.jetty.util.ajax.JSON;

import vn.edu.hust.student.dynamicpool.utils.AppConst;

public class CycleTrajectory extends Trajectory {

	private float centerX;
	private float centerY;

	private float a = 80;
	private float d = 0.2f;
	@flexjson.JSON(include=false)
	JSON json = new JSON(); 

	public CycleTrajectory(Boundary fishBoundary) {
		super(fishBoundary);
		this.centerX = fishBoundary.getLocation().getX();
		int randomInt = Math.abs(new Random().nextInt() % AppConst.height);
		this.centerY = fishBoundary.getLocation().getY() + randomInt;
		increaseTimeState(-Math.PI / 2);
		a = Math.abs(this.centerY - fishBoundary.getLocation().getY());
	}
	
	public void init(float centerX, float centerY, float a, float d) {
		this.centerX = centerX;
		this.centerY = centerY;
		this.a = a;
		this.d = d;
	}

	@Override
	public ETrajectoryType getTrajectoryType() {
		return ETrajectoryType.CYCLE;
	}

	@Override
	public Point updateLocation(float deltaTime) {
		increaseTimeState(deltaTime * d);
		float x = (float) (centerX + a * Math.cos(getTimeState()));
		float y = (float) (centerY + a * Math.sin(getTimeState()));
		this.getFishBoundary().getLocation().setLocation(x, y);
		return getFishBoundary().getLocation();
	}

	@Override
	public void changeDirection(EDirection direction) {
		switch (direction) {
		case LEFT:
		case RIGHT:
			centerY = 2 * getFishBoundary().getLocation().getY() - centerY;
			setTimeState(-getTimeState());
			break;
		case TOP:
		case BOTTOM:
			centerX = 2 * getFishBoundary().getLocation().getX() - centerX;
			setTimeState(Math.PI - getTimeState());
			break;
		default:
			break;
		}
	}

	@Override
	public EDirection getHorizontalDirection() {
		return Math.sin(getTimeState()) > 0 ? EDirection.LEFT
				: EDirection.RIGHT;
	}

	@Override
	public Trajectory clone() {
		CycleTrajectory t = new CycleTrajectory(getFishBoundary().clone());
		t.init(centerX, centerY, a, d);
		t.setTimeState(this.getTimeState());
		return t;
	}

	@Override
	public String getJsonEncode() {
		return json.toJSON(new float[] {centerX, centerY, a, d});
	}

	@Override
	public void setJsonEncode(String jsonEncode) {
		float[] params = (float[]) json.fromJSON(jsonEncode);
		init(params[0], params[1], params[2], params[3]);
	}

}
