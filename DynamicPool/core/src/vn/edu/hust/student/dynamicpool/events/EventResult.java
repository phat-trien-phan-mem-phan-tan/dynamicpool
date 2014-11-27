package vn.edu.hust.student.dynamicpool.events;

public class EventResult {

	private boolean isSuccess;
	private Object targetObject;
	private Exception error;

	public EventResult(boolean isSuccess, Object targetObject, Exception error) {
		this.isSuccess = isSuccess;
		this.targetObject = targetObject;
		this.error = error;
	}

	public boolean isSuccess() {
		return isSuccess;
	}
	
	public Object getTargetObject() {
		return targetObject;
	}
	
	public Exception getError() {
		return error;
	}
}
