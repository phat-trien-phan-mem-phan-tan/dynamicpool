package vn.edu.hust.student.dynamicpool.dal.processor;

public abstract class Processor {
	public abstract ProcessorExecutionResponse execute(ProcessorExecutionRequest request);
}
