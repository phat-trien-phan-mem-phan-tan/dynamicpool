package vn.edu.hust.student.dynamicpool.dal.server.logic;

public class PoolConverter implements IConverter {
	private final int MUL = 10;

	@Override
	public Retangle convertDeviceInfoToRect(DeviceInfo deviceInfo) {
		
		float height = (float) (MUL * Math.sqrt(Math.pow(deviceInfo.getSize(),
				2)
				/ (1 + Math.pow(deviceInfo.getWidth() / deviceInfo.getHeight(),
						2))));

		float width = (float) (height * deviceInfo.getWidth() / deviceInfo
				.getHeight());
		
		Retangle rec = new Retangle(width, height);
		return rec;
	}
}
