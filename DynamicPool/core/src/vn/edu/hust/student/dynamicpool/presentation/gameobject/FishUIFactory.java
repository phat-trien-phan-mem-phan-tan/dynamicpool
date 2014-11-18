package vn.edu.hust.student.dynamicpool.presentation.gameobject;

import vn.edu.hust.student.dynamicpool.bll.IFish;

public class FishUIFactory {
	public static FishUI createFishUI(IFish fish) {
		return new FishOneUI(fish);
	}
}
