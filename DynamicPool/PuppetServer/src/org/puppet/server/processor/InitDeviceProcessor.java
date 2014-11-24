package org.puppet.server.processor;

import java.util.Map;

import org.puppet.server.bussinesslayer.IPool;
import org.puppet.server.bussinesslayer.Pool;
import org.puppet.server.controller.MainController;

public class InitDeviceProcessor extends Processor {

	@Override
	public ProcessorExecutionResponse execute(ProcessorExecutionRequest request) {
		// TODO Auto-generated method stub
		Map<String, Object> data = request.getParameters();
		float width = (float) data.get("width");
		float height = (float) data.get("height");
		float size = (float) data.get("size");
		String clientName = (String) data.get("clientName");

		return null;

	}
}
