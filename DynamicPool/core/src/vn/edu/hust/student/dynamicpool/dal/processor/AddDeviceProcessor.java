package vn.edu.hust.student.dynamicpool.dal.processor;

import java.util.Map;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

import vn.edu.hust.student.dynamicpool.bll.model.DeviceInfo;
import vn.edu.hust.student.dynamicpool.dal.client.entity.Client;
import vn.edu.hust.student.dynamicpool.dal.controller.HostMainController;
import vn.edu.hust.student.dynamicpool.events.EventDestination;
import vn.edu.hust.student.dynamicpool.events.EventType;
import vn.edu.hust.student.dynamicpool.events.RegisterClientEvent;
import vn.edu.hust.student.dynamicpool.utils.AppConst;

public class AddDeviceProcessor extends Processor {

	@Override
	public ProcessorExecutionResponse execute(ProcessorExecutionRequest request) {
		Map<String, Object> params = request.getParameters();
		if (params.containsKey("device")) {
			JSONDeserializer<DeviceInfo> json = new JSONDeserializer<DeviceInfo>();
			JSONSerializer serilizer = new JSONSerializer();
			String device = serilizer.exclude("*.class").serialize(
					params.get("device"));
			DeviceInfo deviceInfo = json.deserialize(device, DeviceInfo.class);
			Client client = new Client();
			client.setClientName(deviceInfo.getClientName());
			client.setSession(request.getSession());
			HostMainController.getInstance().getClientManager()
					.addClient(client);
			EventDestination.getInstance().dispatchSuccessEventWithObject(
					EventType.DAL_ADD_DEVICE_REQUEST, deviceInfo);
		}
		return null;
	}
}
