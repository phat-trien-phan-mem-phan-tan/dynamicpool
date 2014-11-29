package vn.edu.hust.student.dynamicpool.bll.model;

import java.util.List;

public class FindCommonEdgeFunction {
	public static void findCommonEdge(List<Pool> pools) {
		if (pools.size() == 1) {
			pools.get(0).getSegments().clear();
		}
		if (isValid(pools)) {
			
		}
		clearAllSegments(pools);
		for (int i = 0; i < pools.size() - 1; i++) {
			for (int j = i + 1; j < pools.size(); j++) {
				if (i == j)
					return;
				Pool leftPool = pools.get(i);
				Pool righPool = pools.get(j);
				Boundary leftBoundary = leftPool.getBoundary();
				Boundary rightBoundary = righPool.getBoundary();
				if (leftBoundary.isPassing(rightBoundary)) {
					
				}
			}
		}
	}

	private static boolean isValid(List<Pool> pools) {
		for (int i = 0; i < pools.size() - 1; i++) {
			for (int j = i + 1; j < pools.size(); j++) {
				
			}
		}
		return true;
	}

	private static void clearAllSegments(List<Pool> pools) {
		for (Pool pool : pools) {
			pool.getSegments().clear();
		}
	}
}
