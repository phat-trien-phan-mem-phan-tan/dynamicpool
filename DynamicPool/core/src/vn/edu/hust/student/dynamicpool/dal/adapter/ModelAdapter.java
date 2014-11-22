package vn.edu.hust.student.dynamicpool.dal.adapter;

import vn.edu.hust.student.dynamicpool.dal.server.logic.DeviceInfo;

public class ModelAdapter {
	public static DeviceInfo convert(
			vn.edu.hust.student.dynamicpool.model.DeviceInfo device) {
		DeviceInfo deviceInfo = new DeviceInfo(device.getScreenWidth(),
				device.getScreenHeight(), device.getScreenSize());
		return deviceInfo;
	}

}
