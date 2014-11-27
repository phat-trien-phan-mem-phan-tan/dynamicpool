package vn.edu.hust.student.dynamicpool.equation;

import java.util.ArrayList;

import vn.edu.hust.student.dynamicpool.bll.model.FishState;
import vn.edu.hust.student.dynamicpool.bll.model.Pool;
import vn.edu.hust.student.dynamicpool.bll.model.Segment;

public interface IFishState {

	FishState getFishState(ArrayList<Segment>  segmentList,Pool pool);
}
