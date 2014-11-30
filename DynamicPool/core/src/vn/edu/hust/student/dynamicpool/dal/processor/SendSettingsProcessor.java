package vn.edu.hust.student.dynamicpool.dal.processor;

import java.lang.reflect.Type;
import java.util.Map;

import org.eclipse.jetty.util.ajax.JSON;
import org.eclipse.jetty.util.ajax.JSON.Output;
import org.eclipse.jetty.util.ajax.JSONObjectConvertor;

import vn.edu.hust.student.dynamicpool.bll.model.Pool;
import vn.edu.hust.student.dynamicpool.bll.model.Segment;
import vn.edu.hust.student.dynamicpool.dal.statics.Field;
import vn.edu.hust.student.dynamicpool.events.EventDestination;
import vn.edu.hust.student.dynamicpool.events.EventType;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import flexjson.JsonNumber;
import flexjson.ObjectBinder;
import flexjson.ObjectFactory;

public class SendSettingsProcessor extends Processor {
	JSONDeserializer<Pool> poolDeserializer = new JSONDeserializer<Pool>();
	JSONSerializer serilizer = new JSONSerializer();
	JSONDeserializer<Segment> segmentDeserializer = new JSONDeserializer<>();

	@Override
	public ProcessorExecutionResponse execute(ProcessorExecutionRequest request) {
		Map<String, Object> params = request.getParameters();
		if (params.containsKey(Field.POOL)) {
			String poolString = serilizer.include("segments").serialize(params);
			Pool pool = poolDeserializer.deserialize(poolString, Pool.class);
			EventDestination.getInstance().dispatchSuccessEventWithObject(
					EventType.DAL_UPDATE_SETTINGS_RESPONSE, pool);
		}

		return null;
	}

}
