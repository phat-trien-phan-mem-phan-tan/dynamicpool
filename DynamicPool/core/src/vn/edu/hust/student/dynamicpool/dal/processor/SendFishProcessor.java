package vn.edu.hust.student.dynamicpool.dal.processor;

import java.util.Map;

import vn.edu.hust.student.dynamicpool.bll.model.IFish;
import vn.edu.hust.student.dynamicpool.dal.statics.Field;
import vn.edu.hust.student.dynamicpool.events.EventDestination;
import vn.edu.hust.student.dynamicpool.events.EventType;
import vn.edu.hust.student.dynamicpool.exception.DALException;

public class SendFishProcessor extends Processor {

	@Override
	public ProcessorExecutionResponse execute(ProcessorExecutionRequest request) {
		Map<String, Object> params = request.getParameters();
		if (params.containsKey(Field.FISH)
				&& params.containsKey(Field.SUCCESSFUL)) {
			IFish fish = FishParser.deserializerFish(params);
			boolean isSuccess = (boolean) params.get(Field.SUCCESSFUL);
			if (isSuccess) {
				EventDestination.getInstance().dispatchSuccessEventWithObject(
						EventType.DAL_SEND_FISH_RESPONSE, fish);
			} else {
				EventDestination.getInstance().dispatchFailEventWithObject(EventType.DAL_SEND_FISH_RESPONSE, 
						new DALException("receive false result", null), fish);
			}
		}
		return null;
	}

}
