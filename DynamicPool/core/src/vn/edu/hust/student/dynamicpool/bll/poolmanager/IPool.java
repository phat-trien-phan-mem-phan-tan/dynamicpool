package vn.edu.hust.student.dynamicpool.bll.poolmanager;

import java.awt.Rectangle;
import java.util.ArrayList;

import vn.edu.hust.student.dynamicpool.bll.IFish;
import vn.edu.hust.student.dynamicpool.bll.IFishManager;
import vn.edu.hust.student.dynamicpool.model.Segment;

public interface IPool {

	int getPoolId();
	void setPosition(Rectangle rectangle);
	
	IPoolPosition getCorrdiate();
	
	IFishManager getFishCollection();
	
	ArrayList<Segment> getSegmentsX();
	ArrayList<Segment> getSegmentsY();
	void setSegments(ArrayList<Segment> segments);
	
	ArrayList<IFish> updatePosition(float detatime);
	
}
