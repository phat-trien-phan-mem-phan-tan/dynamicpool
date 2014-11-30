package vn.edu.hust.student.dynamicpool.dal.processor;

import java.util.Map;

import vn.edu.hust.student.dynamicpool.bll.model.Pool;
import vn.edu.hust.student.dynamicpool.dal.statics.Field;
import vn.edu.hust.student.dynamicpool.events.EventDestination;
import vn.edu.hust.student.dynamicpool.events.EventType;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class SendSettingsProcessor extends Processor {

	@Override
	public ProcessorExecutionResponse execute(ProcessorExecutionRequest request) {
		Map<String, Object> params = request.getParameters();
		if (params.containsKey(Field.POOL)) {
			JSONDeserializer<Pool> jsonDeserializer = new JSONDeserializer<Pool>();
			JSONSerializer serilizer = new JSONSerializer();
			String poolString = serilizer.exclude("*.class").deepSerialize(
					params.get(Field.POOL));
			Pool pool = jsonDeserializer.deserialize(poolString, Pool.class);
			EventDestination.getInstance().dispatchSuccessEventWithObject(
					EventType.DAL_UPDATE_SETTINGS_RESPONSE, pool);
		}
		return null;
	}

}