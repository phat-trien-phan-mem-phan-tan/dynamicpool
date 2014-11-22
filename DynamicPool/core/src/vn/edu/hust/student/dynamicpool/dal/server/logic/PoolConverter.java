package vn.edu.hust.student.dynamicpool.dal.server.logic;

import vn.edu.hust.student.dynamicpool.model.*;

public class PoolConverter {
	private static final int MUL = 10;

	public static Rectangle convertDeviceInfoToRect(DeviceInfo deviceInfo) {

		float height = (float) (MUL * Math.sqrt(Math.pow(
				deviceInfo.getScreenSize(), 2)
				/ (1 + Math.pow(
						deviceInfo.getScreenWidth()
								/ deviceInfo.getScreenHeight(), 2))));

		float width = (float) (height * deviceInfo.getScreenWidth() / deviceInfo
				.getScreenHeight());
		return new Rectangle(width, height);
	}
}
