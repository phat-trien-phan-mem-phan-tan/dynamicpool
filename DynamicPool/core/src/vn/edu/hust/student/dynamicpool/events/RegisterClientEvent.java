package vn.edu.hust.student.dynamicpool.events;

import java.security.InvalidParameterException;

import vn.edu.hust.student.dynamicpool.bll.model.DeviceInfo;

import com.eposi.eventdriven.Event;

public class RegisterClientEvent extends Event {
	private DeviceInfo deviceInfo;
	private String clientName;
	
	public RegisterClientEvent(String type, DeviceInfo deviceInfo,
			String clientName) {
		super(type);
		if (deviceInfo == null || clientName == null || clientName == "")
			throw new InvalidParameterException();
		this.deviceInfo = deviceInfo;
		this.clientName = clientName;
	}

	public String getClientName() {
		return clientName;
	}

	public DeviceInfo getDiviceInfo() {
		return deviceInfo;
	}
}
