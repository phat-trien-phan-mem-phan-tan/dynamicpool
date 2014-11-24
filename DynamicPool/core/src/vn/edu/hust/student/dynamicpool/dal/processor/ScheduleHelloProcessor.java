package vn.edu.hust.student.dynamicpool.dal.processor;

import vn.edu.hust.student.dynamicpool.dal.executor.ExecutorServiceFactory;

public class ScheduleHelloProcessor extends Processor {
	@Override
	public ProcessorExecutionResponse execute(
			final ProcessorExecutionRequest request) {
		ExecutorServiceFactory.getInstance().scheduleExecution(2000, 10, new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				request.write("schedule");
			}
		});

		return null;
	}

}
