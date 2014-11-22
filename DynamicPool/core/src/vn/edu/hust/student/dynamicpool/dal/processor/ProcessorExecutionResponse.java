package vn.edu.hust.student.dynamicpool.dal.processor;


public class ProcessorExecutionResponse {
	private Object data;
	
	public ProcessorExecutionResponse() {
		// do nothing
	}
	
	public ProcessorExecutionResponse(Object data) {
		this.setData(data);
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
