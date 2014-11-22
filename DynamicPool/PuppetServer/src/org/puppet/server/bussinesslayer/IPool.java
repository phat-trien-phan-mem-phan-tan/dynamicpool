package org.puppet.server.bussinesslayer;

import java.util.List;


public interface IPool {
	DeviceInfo getDeviceInfo();
	Retangle getRetangle();
	List<Segment> getSegments();
	void setRetangle(Retangle rect);
	void convertDeviceInfoToRect();
}
