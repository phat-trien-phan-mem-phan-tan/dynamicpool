package vn.edu.hust.student.dynamicpool.events;

import vn.edu.hust.student.dynamicpool.bll.FishManager;

import com.eposi.eventdriven.Event;

public class SynchoronousEvent extends Event {
	private FishManager fishManager;
	
	public SynchoronousEvent(String type, FishManager fishManager) {
		super(type);
		setFishManager(fishManager);
	}

	public FishManager getFishManager() {
		return fishManager;
	}

	public void setFishManager(FishManager fishManager) {
		this.fishManager = fishManager;
	}
}
