package vn.edu.hust.student.dynamicpool.dal.processor;

import java.util.Map;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

import vn.edu.hust.student.dynamicpool.bll.model.CycleTrajectory;
import vn.edu.hust.student.dynamicpool.bll.model.ETrajectoryType;
import vn.edu.hust.student.dynamicpool.bll.model.Fish;
import vn.edu.hust.student.dynamicpool.bll.model.IFish;
import vn.edu.hust.student.dynamicpool.bll.model.LineTrajectory;
import vn.edu.hust.student.dynamicpool.bll.model.NoneTrajectory;
import vn.edu.hust.student.dynamicpool.bll.model.SinTrajectory;
import vn.edu.hust.student.dynamicpool.dal.statics.Field;

public class FishParser {
	private static JSONDeserializer<Fish> deserializer = new JSONDeserializer<Fish>();
	private static JSONSerializer serializer = new JSONSerializer();
	public static IFish deserializerFish(Map<String, Object> map) {
		ETrajectoryType trajectoryType = getTrajectoryType(map);
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

	private static ETrajectoryType getTrajectoryType(Map<String, Object> map) {
		if (map != null && map.containsKey("trajectory")) {
			@SuppressWarnings("unchecked")
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
