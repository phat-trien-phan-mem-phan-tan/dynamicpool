package vn.edu.hust.student.dynamicpool.dal.server.logic;

import java.util.List;

import vn.edu.hust.student.dynamicpool.model.*;

public interface IPoolServer {
	String getClientName();
	void setClientName(String clientName);
	List<Segment> getSegments();
	void setDeviceInfo(DeviceInfo deviceInfo);
}