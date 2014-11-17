package org.puppet.server.processor;

public abstract class Processor {
	public abstract ProcessorExecutionResponse execute(ProcessorExecutionRequest request);
}
