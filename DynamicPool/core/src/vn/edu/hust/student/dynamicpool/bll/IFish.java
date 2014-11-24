
package vn.edu.hust.student.dynamicpool.bll;

import com.eposi.eventdriven.implementors.BaseEventDispatcher;

import vn.edu.hust.student.dynamicpool.model.FishState;
import vn.edu.hust.student.dynamicpool.model.Rectangle;

public abstract class IFish extends BaseEventDispatcher{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4169541919524570235L;
	public abstract int getFishId();
	public abstract Rectangle getPosition();
	public abstract Rectangle update(float deltaTime);	
	public abstract FishState getFishState();
	public abstract void setFishState(FishState fishState);
	public abstract void setAngle(float angle);
	public abstract float getAngle();
	public abstract FishType getFishType();

}