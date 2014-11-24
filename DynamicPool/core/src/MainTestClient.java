import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import vn.edu.hust.student.dynamicpool.bll.Fish;
import vn.edu.hust.student.dynamicpool.dal.controller.ClientMainController;
import vn.edu.hust.student.dynamicpool.exception.DALException;
import vn.edu.hust.student.dynamicpool.model.DeviceInfo;


public class MainTestClient {
	public static void main(String[] args) {
//		try {
//			ClientMainController.getInstance().start(6742);
//		} catch (DALException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		DeviceInfo deviceInfo = new DeviceInfo(10, 10, 10);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("device", deviceInfo);
		JSONSerializer serializer = new JSONSerializer();
		String s = serializer.exclude("*.class").serialize(deviceInfo);
		String device = serializer.exclude("*.class").serialize(map.get("device"));
		
		JSONDeserializer<DeviceInfo> deserializer = new JSONDeserializer<DeviceInfo>();
		DeviceInfo deviceInfoDes = deserializer.deserialize(s, DeviceInfo.class);
		System.out.println(s); 
		
		//System.out.println(s);
	}
}
