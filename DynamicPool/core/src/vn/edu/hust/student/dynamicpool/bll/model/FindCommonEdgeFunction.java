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

	public static void test(List<Pool> pools) {
		if (pools.size() == 2) {
			Pool pool1 = pools.get(0);
			Pool pool2 = pools.get(1);
			Segment rightSegment1 = new Segment(EDirection.RIGHT, 0, pool1.getBoundary().getHeight());
			rightSegment1.setNeighbourClientName(pool2.getDeviceInfo().getClientName());
			pool1.getSegments().add(rightSegment1);
			Segment leftSegment2 = new Segment(EDirection.LEFT, 0, pool1.getBoundary().getHeight());
			leftSegment2.setNeighbourClientName(pool1.getDeviceInfo().getClientName());
			pool2.getSegments().add(leftSegment2);
		}
		if (pools.size()>3) {
			Pool firstPool = pools.get(0);
			Pool lastPool = pools.get(pools.size()-1);
			Segment rightSegment1 = new Segment(EDirection.RIGHT, 0, firstPool.getBoundary().getHeight());
			rightSegment1.setNeighbourClientName(pools.get(1).getDeviceInfo().getClientName());
			List<Segment> firstPoolSegments = firstPool.getSegments();
			firstPoolSegments.clear();
			firstPoolSegments.add(rightSegment1);
			Segment leftSegment2 = new Segment(EDirection.LEFT, 0, lastPool.getBoundary().getHeight());
			leftSegment2.setNeighbourClientName(pools.get(pools.size()-2).getDeviceInfo().getClientName());
			List<Segment> lastPoolSegments = lastPool.getSegments();
			lastPoolSegments.clear();
			lastPoolSegments.add(leftSegment2);
			for (int i = 1; i < pools.size()-1; i++) {
				Pool pool = pools.get(i);
				Segment leftSegment = new Segment(EDirection.LEFT, 0, pool.getBoundary().getHeight());
				leftSegment.setNeighbourClientName(pools.get(i-1).getDeviceInfo().getClientName());
				Segment rightSegment = new Segment(EDirection.RIGHT, 0, pool.getBoundary().getHeight());
				rightSegment.setNeighbourClientName(pools.get(i+1).getDeviceInfo().getClientName());
				List<Segment> segments = pool.getSegments();
				segments.clear();
				segments.add(leftSegment);
				segments.add(rightSegment);
			}
		}
	}
}
