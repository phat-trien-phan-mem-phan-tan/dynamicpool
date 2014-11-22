package vn.edu.hust.student.dynamicpool.dal.processor;

import java.util.Map;

import org.apache.mina.core.session.IoSession;

public interface ProcessorExecutionRequest {
	public Map<String, Object> getParameters();
	public void write(Object data);
	public IoSession getSession();
	public void callback(Object... data);
}
