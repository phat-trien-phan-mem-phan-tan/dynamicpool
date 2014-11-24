package vn.edu.hust.student.dynamicpool.dal.processor;

import java.util.Map;

import vn.edu.hust.student.dynamicpool.dal.statics.Field;

public class ConnectProcessor extends Processor {

	@Override
	public ProcessorExecutionResponse execute(ProcessorExecutionRequest request) {
		Map<String, Object> params = request.getParameters();
		if (params.containsKey(Field.SUCCESSFUL)) {
			boolean success = (boolean) params.get(Field.SUCCESSFUL);
		}
		return null;
	}

}
