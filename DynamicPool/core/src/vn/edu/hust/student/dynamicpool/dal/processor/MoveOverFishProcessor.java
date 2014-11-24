package vn.edu.hust.student.dynamicpool.dal.processor;

import java.util.Map;

import com.eposi.eventdriven.Event;

import vn.edu.hust.student.dynamicpool.bll.Fish;
import vn.edu.hust.student.dynamicpool.dal.controller.HostMainController;
import vn.edu.hust.student.dynamicpool.dal.statics.EventType;
import vn.edu.hust.student.dynamicpool.events.MoveOverEvent;
import flexjson.JSONDeserializer;

public class MoveOverFishProcessor extends Processor {

	@Override
	public ProcessorExecutionResponse execute(ProcessorExecutionRequest request) {
		Map<String, Object> map = request.getParameters();
		if (map.containsKey("fish")) {
			JSONDeserializer<Fish> json = new JSONDeserializer<Fish>();
			Fish fish = json.deserialize(map.get("fish").toString());
			HostMainController.getInstance().dispatchAll(
					new MoveOverEvent(EventType.MOVE_OVER_EVENT, fish));
		}
		return null;
	}

}
