package vn.edu.hust.student.dynamicpool.bll;

import java.util.ArrayList;
import java.util.List;

public class FishManager extends ArrayList<IFish> implements IFishManager {

	
	public FishManager(){
		super();
	}

	@Override
	public List<IFish> getFishs() {

		return this;
	}

	@Override
	public void addFish(IFish fish) {

		super.add(fish);
	}

}
