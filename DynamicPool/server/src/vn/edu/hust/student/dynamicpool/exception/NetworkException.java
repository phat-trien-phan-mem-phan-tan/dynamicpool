package vn.edu.hust.student.dynamicpool.exception;

public class NetworkException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 264604355194982107L;

	private String message;
	
	public NetworkException(String message){
		setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
