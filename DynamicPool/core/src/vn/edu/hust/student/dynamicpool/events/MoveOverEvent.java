package vn.edu.hust.student.dynamicpool.events;

import vn.edu.hust.student.dynamicpool.bll.Fish;

import com.eposi.eventdriven.Event;

public class MoveOverEvent extends Event {
	private Fish fish;
	
	public MoveOverEvent(String type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

}
