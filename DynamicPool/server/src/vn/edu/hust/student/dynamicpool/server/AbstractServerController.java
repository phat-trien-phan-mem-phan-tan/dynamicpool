package vn.edu.hust.student.dynamicpool.server;

import java.util.Map;

import vn.edu.hust.student.dynamicpool.processor.Processor;
import vn.edu.hust.student.dynamicpool.processor.ProcessorManager;

public abstract class AbstractServerController{
	protected ProcessorManager processorManager;

	
	public abstract void initProcessor(Map<String, Class<? extends Processor>> map);
	public abstract void start(String ip, int port);
}
