package vn.edu.hust.student.dynamicpool.dal.processor;

import java.util.Map;

import flexjson.JSONDeserializer;

import vn.edu.hust.student.dynamicpool.dal.controller.HostMainController;
import vn.edu.hust.student.dynamicpool.dal.utils.AppConst;
import vn.edu.hust.student.dynamicpool.events.RegisterClientEvent;
import vn.edu.hust.student.dynamicpool.model.DeviceInfo;

public class AddDeviceProcessor extends Processor {

	@Override
	public ProcessorExecutionResponse execute(ProcessorExecutionRequest request) {
		Map<String, Object> params = request.getParameters();
		if (params.containsKey("device")) {
			JSONDeserializer<DeviceInfo> json = new JSONDeserializer<DeviceInfo>();
			DeviceInfo deviceInfo = json.deserialize(params.get("device")
					.toString());
			String clientName = (String) params.get("clientName");
			HostMainController.getInstance().dispatchAll(
					new RegisterClientEvent(AppConst.REGISTER_EVENT_NAME,
							deviceInfo, clientName));
		}
		return null;
	}
}
