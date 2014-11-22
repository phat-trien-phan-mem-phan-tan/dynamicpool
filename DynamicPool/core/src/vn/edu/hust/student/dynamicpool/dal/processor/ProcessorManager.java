package vn.edu.hust.student.dynamicpool.dal.processor;

import java.util.HashMap;
import java.util.Map;

public class ProcessorManager {
	private Map<String, Processor> commandProcessorMapping;

	public ProcessorManager() {
		commandProcessorMapping = new HashMap<String, Processor>();
	}

	public ProcessorExecutionResponse processCommand(String command, ProcessorExecutionRequest data) throws Exception {
		if (!this.commandProcessorMapping.containsKey(command)) {
			throw new Exception("Processor not found for command `" + command + "`");
		}
		return this.commandProcessorMapping.get(command).execute(data);
	}

	public void register(String command, Processor processor) {
		commandProcessorMapping.put(command, processor);
	}

	public void register(Map<String, Processor> map) {
		for (String command : map.keySet()) {
			commandProcessorMapping.put(command, map.get(command));
		}
	}
}
