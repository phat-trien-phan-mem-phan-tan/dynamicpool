package org.puppet.server.processor;

import java.util.HashMap;
import java.util.Map;

public class HelloProcessor extends Processor {

	@Override
	public ProcessorExecutionResponse execute(ProcessorExecutionRequest request) {
		// TODO Auto-generated method stub
		//1 processor co the tra ve bang http hoac socket
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hello", "thanh");
		return new ProcessorExecutionResponse(map);
	}

}
