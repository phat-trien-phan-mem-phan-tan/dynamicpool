package vn.edu.hust.student.dynamicpool.bll;

import java.util.List;

import com.eposi.eventdriven.implementors.BaseEventDispatcher;

public abstract class IFishManager extends BaseEventDispatcher {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3058982944888783318L;

	public abstract List<IFish> getFishs();
	
	public abstract void addFish(IFish fish);
}
