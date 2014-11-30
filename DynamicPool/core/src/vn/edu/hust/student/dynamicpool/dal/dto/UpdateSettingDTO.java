package vn.edu.hust.student.dynamicpool.dal.dto;

import java.util.ArrayList;
import vn.edu.hust.student.dynamicpool.bll.model.Segment;

public class UpdateSettingDTO {
	public String clientName = null;
	public float x;
	public float y;
	public float width;
	public float height;
	public ArrayList<Segment> segments = new ArrayList<>();
}