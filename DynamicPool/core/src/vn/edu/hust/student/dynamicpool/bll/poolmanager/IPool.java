package vn.edu.hust.student.dynamicpool.bll.poolmanager;

import java.util.ArrayList;

import com.eposi.eventdriven.implementors.BaseEventDispatcher;

import vn.edu.hust.student.dynamicpool.bll.IFish;
import vn.edu.hust.student.dynamicpool.bll.IFishManager;
import vn.edu.hust.student.dynamicpool.model.Rectangle;
import vn.edu.hust.student.dynamicpool.model.Segment;

public abstract class IPool extends BaseEventDispatcher{

	public abstract int getPoolId();
	public abstract void setPosition(Rectangle rectangle);
	
	public abstract Rectangle getCorrdiate();
	
	public abstract IFishManager getFishCollection();
	
	public abstract ArrayList<Segment> getSegmentsX();
	public abstract ArrayList<Segment> getSegmentsY();
	public abstract void setSegments(ArrayList<Segment> segments);
	
	public abstract ArrayList<IFish> updatePosition(float detatime);
	
}
