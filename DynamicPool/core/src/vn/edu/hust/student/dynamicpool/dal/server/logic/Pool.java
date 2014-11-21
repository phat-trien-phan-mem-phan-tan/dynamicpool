package vn.edu.hust.student.dynamicpool.dal.server.logic;

import java.util.ArrayList;
import java.util.List;

public class Pool implements IPool  {
	private String clientName;
	private DeviceInfo deviceInfo;
	private Retangle retangle;
	private List<Segment> segments;
	private IConverter converter;
	
	public Pool(){
		converter = new PoolConverter();
		segments = new ArrayList<>();
	}
	
	public Pool(DeviceInfo device){
		this();
		setDeviceInfo(device);
	}
	
	@Override
	public DeviceInfo getDeviceInfo() {
		return deviceInfo;
	}

	@Override
	public Retangle getRetangle() {
		return retangle;
	}

	public void setDeviceInfo(DeviceInfo deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	@Override
	public void setRetangle(Retangle retangle) {
		this.retangle = retangle;
	}

	@Override
	public List<Segment> getSegments() {
		return segments;
	}

	public void setSegments(List<Segment> segments) {
		this.segments = segments;
	}

	@Override
	public void convertDeviceInfoToRect() {
		setRetangle(converter.convertDeviceInfoToRect(deviceInfo));
	}

	@Override
	public String getClientName() {
		return this.clientName;
		
	}

	@Override
	public void setClientName(String clientName) {
		this.clientName=clientName;
		
	}
	
}
