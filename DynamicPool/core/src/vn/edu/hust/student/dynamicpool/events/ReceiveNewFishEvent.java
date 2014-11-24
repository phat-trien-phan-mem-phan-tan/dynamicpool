package vn.edu.hust.student.dynamicpool.events;

import com.eposi.eventdriven.Event;

import vn.edu.hust.student.dynamicpool.bll.Fish;

public class ReceiveNewFishEvent extends Event {
	private Fish fish;
	
	public ReceiveNewFishEvent(String type, Fish fish) {
		super(type);
		setFish(fish);
	}

	public Fish getFish() {
		return fish;
	}

	public void setFish(Fish fish) {
		this.fish = fish;
	}			
}
