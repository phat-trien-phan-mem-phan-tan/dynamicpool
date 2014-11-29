package vn.edu.hust.student.dynamicpool.dal.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import vn.edu.hust.student.dynamicpool.bll.model.CycleTrajectory;
import vn.edu.hust.student.dynamicpool.bll.model.ETrajectoryType;
import vn.edu.hust.student.dynamicpool.bll.model.Fish;
import vn.edu.hust.student.dynamicpool.bll.model.IFish;
import vn.edu.hust.student.dynamicpool.bll.model.LineTrajectory;
import vn.edu.hust.student.dynamicpool.bll.model.NoneTrajectory;
import vn.edu.hust.student.dynamicpool.bll.model.SinTrajectory;
import vn.edu.hust.student.dynamicpool.dal.statics.Field;
import vn.edu.hust.student.dynamicpool.events.EventDestination;
import vn.edu.hust.student.dynamicpool.events.EventType;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class CreateFishProcessor extends Processor {
	JSONDeserializer<Fish> deserializer = new JSONDeserializer<Fish>();
	JSONSerializer serializer = new JSONSerializer();

	@Override
	public ProcessorExecutionResponse execute(ProcessorExecutionRequest request) {
		Map<String, Object> map = request.getParameters();
		if (map.containsKey(Field.FISH) && map.containsKey(Field.CLIENT_NAME)) {
			ETrajectoryType trajectoryType = getTrajectoryType(map);
			IFish fish = getFish(map, trajectoryType);
			String clientName = (String) map.get(Field.CLIENT_NAME);
			List<Object> list = new ArrayList<Object>();
			list.add(fish);
			list.add(clientName);
			EventDestination.getInstance().dispatchSuccessEventWithObject(
					EventType.DAL_CREATE_FISH_REQUEST, list);
		}
		return null;
	}

	private IFish getFish(Map<String, Object> map,
			ETrajectoryType trajectoryType) {
		String fishString = serializer.exclude("*.class").serialize(
				map.get(Field.FISH));
		switch (trajectoryType) {
		case LINE:
			return deserializer.use("trajectory", LineTrajectory.class)
					.deserialize(fishString, Fish.class);
		case CYCLE:
			return deserializer.use("trajectory", CycleTrajectory.class)
					.deserialize(fishString, Fish.class);
		case SIN:
			return deserializer.use("trajectory", SinTrajectory.class)
					.deserialize(fishString, Fish.class);
		default:
			return deserializer.use("trajectory", NoneTrajectory.class)
					.deserialize(fishString, Fish.class);
		}
	}

	private ETrajectoryType getTrajectoryType(Map<String, Object> map) {
		if (map != null && map.containsKey("trajectory")) {
			Map<String, Object> trajectoryMap = ((Map<String, Object>) map
					.get("trajectory"));
			if (trajectoryMap != null
					&& trajectoryMap.containsKey("trajectoryType")) {
				String trajectoryTypeMap = (String) map.get("trajectoryType");
				if (map != null)
					return ETrajectoryType.valueOf(trajectoryTypeMap);
			}
		}
		return ETrajectoryType.NONE;
	}
}
