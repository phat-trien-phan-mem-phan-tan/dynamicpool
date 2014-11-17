package org.puppet.server.server;

import java.util.Map;

import org.puppet.server.processor.Processor;
import org.puppet.server.processor.ProcessorManager;

public abstract class AbstractServerController{
	protected ProcessorManager processorManager;

	
	public abstract void initProcessor(Map<String, Class<? extends Processor>> map);
	public abstract void start();
}
