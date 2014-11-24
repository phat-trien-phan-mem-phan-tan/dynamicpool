package vn.edu.hust.student.dynamicpool.dal.processor;

import java.util.Map;

import vn.edu.hust.student.dynamicpool.bll.Fish;
import vn.edu.hust.student.dynamicpool.dal.controller.HostMainController;
import vn.edu.hust.student.dynamicpool.dal.statics.EventType;
import vn.edu.hust.student.dynamicpool.events.CreateFishEvent;
import flexjson.JSONDeserializer;

public class RemoveFishProcessor extends Processor {

	@Override
	public ProcessorExecutionResponse execute(ProcessorExecutionRequest request) {
		Map<String, Object> map = request.getParameters();
		if (map.containsKey("fish")) {
			JSONDeserializer<Fish> deserializer = new JSONDeserializer<Fish>();
			Fish fish = deserializer.deserialize(map.get("fish").toString());
			HostMainController.getInstance().dispatchAll(
					new CreateFishEvent(EventType.REMOVE_FISH, fish));
		}
		return null;
	}

}
