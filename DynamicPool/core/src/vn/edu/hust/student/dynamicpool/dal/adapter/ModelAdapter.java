package vn.edu.hust.student.dynamicpool.dal.adapter;

import java.util.HashMap;
import java.util.Map;

import vn.edu.hust.student.dynamicpool.bll.Fish;

public class ModelAdapter {
	public static Map<String, Object> convertFish(Fish fish){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("angle", fish.getAngle());
		map.put("id", fish.getFishId());
		map.put("state", fish.getFishState().ordinal());
		return null;
	}
}
