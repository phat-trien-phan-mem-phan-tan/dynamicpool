package vn.edu.hust.student.dynamicpool.dal.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import vn.edu.hust.student.dynamicpool.bll.model.IFish;
import vn.edu.hust.student.dynamicpool.dal.statics.Field;
import vn.edu.hust.student.dynamicpool.events.EventDestination;
import vn.edu.hust.student.dynamicpool.events.EventType;

public class CreateFishProcessor extends Processor {

	@Override
	public ProcessorExecutionResponse execute(ProcessorExecutionRequest request) {
		Map<String, Object> map = request.getParameters();
		if (map.containsKey(Field.FISH) && map.containsKey(Field.CLIENT_NAME)) {
			IFish fish = FishParser.deserializerFish(map);
			String clientName = (String) map.get(Field.CLIENT_NAME);
			List<Object> list = new ArrayList<Object>();
			list.add(fish);
			list.add(clientName);
			EventDestination.getInstance().dispatchSuccessEventWithObject(
					EventType.DAL_CREATE_FISH_REQUEST, list);
		}
		return null;
	}
	
	
}
