package vn.edu.hust.student.dynamicpool.bll.poolmanager;

import java.util.ArrayList;

import vn.edu.hust.student.dynamicpool.bll.IFish;
import vn.edu.hust.student.dynamicpool.bll.IFishManager;
import vn.edu.hust.student.dynamicpool.model.Segment;

public interface IPool {

	int getPoolId();
	
	IPoolPosition getPosition();
	
	IFishManager getFishCollection();
	
	ArrayList<Segment> getSegments();
	void setSegments(ArrayList<Segment> segments);
	
	IFish updatePosition(float detatime);
}
