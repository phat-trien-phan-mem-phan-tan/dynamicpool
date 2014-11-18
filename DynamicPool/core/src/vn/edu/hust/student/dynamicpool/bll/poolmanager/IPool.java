package vn.edu.hust.student.dynamicpool.bll.poolmanager;

import java.awt.List;
import java.util.ArrayList;

import vn.edu.hust.student.dynamicpool.bll.Fish;
import vn.edu.hust.student.dynamicpool.bll.FishPosition;
import vn.edu.hust.student.dynamicpool.bll.IFish;
import vn.edu.hust.student.dynamicpool.bll.IFishManager;
import vn.edu.hust.student.dynamicpool.model.FishState;
import vn.edu.hust.student.dynamicpool.model.Segment;

public interface IPool {

	int getPoolId();
	
	IPoolPosition getCorrdiate();
	
	IFishManager getFishCollection();
	
	ArrayList<Segment> getSegmentsX();
	ArrayList<Segment> getSegmentsY();
	void setSegments(ArrayList<Segment> segments);
	
	ArrayList<IFish> updatePosition(float detatime);
	
}
