package vn.edu.hust.student.dynamicpool.dal.server;

import java.util.Map;

import vn.edu.hust.student.dynamicpool.dal.processor.Processor;
import vn.edu.hust.student.dynamicpool.dal.processor.ProcessorManager;

public abstract class AbstractServerController{
	protected ProcessorManager processorManager;

	
	public abstract void initProcessor(Map<String, Class<? extends Processor>> map);
	public abstract void start();
}
