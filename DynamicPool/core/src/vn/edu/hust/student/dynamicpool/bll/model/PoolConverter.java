package vn.edu.hust.student.dynamicpool.bll.model;

public class PoolConverter {
	private static final int MUL = 10;

	public static Boundary convertDeviceInfoToRect(DeviceInfo deviceInfo) {

		float height = (float) (MUL * Math.sqrt(Math.pow(
				deviceInfo.getScreenSize(), 2)
				/ (1 + Math.pow(
						deviceInfo.getScreenWidth()
								/ deviceInfo.getScreenHeight(), 2))));

		float width = (float) (height * deviceInfo.getScreenWidth() / deviceInfo
				.getScreenHeight());
		return new Boundary(width, height);
	}
}
