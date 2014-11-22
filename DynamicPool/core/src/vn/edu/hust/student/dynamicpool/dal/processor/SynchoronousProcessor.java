package vn.edu.hust.student.dynamicpool.dal.processor;

import java.util.Map;

import vn.edu.hust.student.dynamicpool.bll.FishManager;
import vn.edu.hust.student.dynamicpool.dal.controller.HostMainController;
import vn.edu.hust.student.dynamicpool.dal.statics.EventType;
import vn.edu.hust.student.dynamicpool.events.SynchoronousEvent;
import flexjson.JSONDeserializer;

public class SynchoronousProcessor extends Processor {

	@Override
	public ProcessorExecutionResponse execute(ProcessorExecutionRequest request) {
		Map<String, Object> params = request.getParameters();
		if (params.containsKey("fishManager")) {
			JSONDeserializer<FishManager> deSerializer = new JSONDeserializer<FishManager>();
			String json = params.get("fishManager").toString();
			FishManager fishManager = deSerializer.deserialize(json,
					FishManager.class);
			HostMainController.getInstance().dispatchAll(
					new SynchoronousEvent(EventType.SYNCHORONOUS_EVENT,
							fishManager));
		}

		return null;
	}
}
