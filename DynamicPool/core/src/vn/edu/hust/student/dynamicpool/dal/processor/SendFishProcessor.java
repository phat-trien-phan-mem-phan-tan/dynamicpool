package vn.edu.hust.student.dynamicpool.dal.processor;

import java.util.Map;

import com.eposi.eventdriven.models.AbstractModel.fields;

import vn.edu.hust.student.dynamicpool.bll.model.Fish;
import vn.edu.hust.student.dynamicpool.dal.statics.Field;
import vn.edu.hust.student.dynamicpool.events.EventDestination;
import vn.edu.hust.student.dynamicpool.events.EventType;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class SendFishProcessor extends Processor {

	@Override
	public ProcessorExecutionResponse execute(ProcessorExecutionRequest request) {
		// TODO Auto-generated method stub
		Map<String, Object> params = request.getParameters();
		if (params.containsKey(Field.FISH)
				&& params.containsKey(Field.SUCCESSFUL)) {
			JSONDeserializer<Fish> deserializer = new JSONDeserializer<Fish>();
			JSONSerializer serializer = new JSONSerializer();
			String fishString = serializer.exclude("*.class").serialize(
					params.get(Field.FISH));
			Fish fish = deserializer.deserialize(fishString);
			boolean isSuccess = (boolean) params.get(Field.SUCCESSFUL);
			if (isSuccess) {
				EventDestination.getInstance().dispatchSuccessEventWithObject(
						EventType.DAL_SEND_FISH_RESPONSE, fish);
			} else {
				EventDestination.getInstance().dispatchFailEvent(
						EventType.DAL_SEND_FISH_RESPONSE);
			}
		}
		return null;
	}

}
