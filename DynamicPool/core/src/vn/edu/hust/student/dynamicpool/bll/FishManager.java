package vn.edu.hust.student.dynamicpool.bll;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import vn.edu.hust.student.dynamicpool.bll.statics.EventType;

import com.eposi.eventdriven.Event;
import com.eposi.eventdriven.exceptions.InvalidHandlerMethod;
import com.eposi.eventdriven.exceptions.NoContextToExecute;
import com.eposi.eventdriven.implementors.BaseEventListener;

public class FishManager extends IFishManager {
	private List<IFish> fishes;

	public FishManager() {
		fishes = new ArrayList<IFish>();
	}

	@Override
	public List<IFish> getFishs() {
		return fishes;
	}

	@Override
	public void addFish(IFish fish) {
		this.fishes.add(fish);
		fish.addEventListener(EventType.COLISSION, new BaseEventListener(this,
				"onColission"));
	}
	
	@Deprecated
	public void onColission(Event e){
		try {
			this.dispatchEvent(e);
		} catch (InvocationTargetException | IllegalAccessException
				| NoSuchMethodException | InvalidHandlerMethod
				| NoContextToExecute e1) {
			// TODO Auto-generated catch block
			
			e1.printStackTrace();
		}
	}

	public List<IFish> getFishes() {
		return fishes;
	}

	public void setFishes(List<IFish> fishes) {
		this.fishes = fishes;
	}
	
	
}
