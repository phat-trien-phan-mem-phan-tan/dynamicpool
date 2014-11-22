import java.util.ArrayList;
import java.util.List;

import flexjson.JSONSerializer;
import vn.edu.hust.student.dynamicpool.bll.Fish;
import vn.edu.hust.student.dynamicpool.dal.controller.ClientMainController;
import vn.edu.hust.student.dynamicpool.exception.DALException;


public class MainTestClient {
	public static void main(String[] args) {
//		try {
//			ClientMainController.getInstance().start(6742);
//		} catch (DALException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		List<Fish> list = new ArrayList<Fish>();
		JSONSerializer serializer = new JSONSerializer();
		Fish fish = new Fish();
		fish.setFishId(1);
		list.add(fish);
		list.add(fish);
		String s = serializer.exclude("*.class").serialize(list);
		System.out.println(s);
	}
}
