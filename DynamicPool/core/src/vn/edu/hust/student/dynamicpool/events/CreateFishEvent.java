package vn.edu.hust.student.dynamicpool.events;

import vn.edu.hust.student.dynamicpool.bll.Fish;

import com.eposi.eventdriven.Event;

public class CreateFishEvent extends Event {
	private Fish fish;
	
	public CreateFishEvent(String type, Fish fish) {
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
