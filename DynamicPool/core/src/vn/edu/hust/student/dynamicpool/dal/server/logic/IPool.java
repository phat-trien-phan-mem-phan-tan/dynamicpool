package vn.edu.hust.student.dynamicpool.dal.server.logic;

import java.util.List;


public interface IPool {
	DeviceInfo getDeviceInfo();
	Retangle getRetangle();
	List<Segment> getSegments();
	void setRetangle(Retangle rect);
	void convertDeviceInfoToRect();
	String getClientName();
	void setClientName(String clientName);
}
