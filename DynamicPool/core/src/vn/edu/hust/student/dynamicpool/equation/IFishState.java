package vn.edu.hust.student.dynamicpool.equation;

import java.util.ArrayList;


import vn.edu.hust.student.dynamicpool.bll.poolmanager.IPool;
import vn.edu.hust.student.dynamicpool.model.FishState;
import vn.edu.hust.student.dynamicpool.model.Segment;

public interface IFishState {

	FishState getFishState(ArrayList<Segment>  segmentList,IPool pool);
}
