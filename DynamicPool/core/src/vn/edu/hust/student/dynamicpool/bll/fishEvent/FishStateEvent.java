package vn.edu.hust.student.dynamicpool.bll.fishEvent;

import vn.edu.hust.student.dynamicpool.bll.Fish;

import com.eposi.eventdriven.Event;

public class FishStateEvent extends Event{

	private Fish fish;
	
	public FishStateEvent(String type, Fish fish){
		super(type);
		this.fish = fish;
		
	}

	public Fish getFish() {
		return fish;
	}

	public void setFish(Fish fish) {
		this.fish = fish;
	}
	
	
	
}
