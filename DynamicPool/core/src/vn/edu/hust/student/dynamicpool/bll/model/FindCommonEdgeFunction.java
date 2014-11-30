package vn.edu.hust.student.dynamicpool.bll.model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.utils.Pools;

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

	private static List<Pool> cloneListPools(List<Pool> pools) {
		List<Pool> list = new ArrayList<Pool>();
		for (Pool pool : pools) {
			Pool clone = (Pool) pool.clone();
			list.add(clone);
		}

		return list;
	}

	public static List<Pool> calucalteCommonEdge(List<Pool> pools) {
		List<Pool> list = cloneListPools(pools);

		if (list.size() >= 2) {
			for (Pool pool : list) {
				Boundary boundary = pool.getBoundary();
				Point pointStart = new Point(0, 0);
				Point pointEnd = new Point(0, 0);

				// tìm bên phải
				System.out.println("------------Right---------------");
				pointStart = new Point(boundary.getMinX(), boundary.getMinY());
				pointEnd = new Point(boundary.getMinX(), boundary.getMaxY());
				System.out
						.println("start: " + pointStart + " end: " + pointEnd);

				// lặp tìm trong các pool còn lại
				for (Pool nearPool : list) {
					if (!nearPool.equals(pool)) {
						// lấy cạnh bên phải
						Boundary nearPoolBoundary = nearPool.getBoundary();
						System.out
								.println("near boundary: " + nearPoolBoundary);
						Point nearPoolPointStart = new Point(
								nearPoolBoundary.getMaxX(),
								nearPoolBoundary.getMinY());
						Point nearPoolPointEnd = new Point(
								nearPoolBoundary.getMaxX(),
								nearPoolBoundary.getMaxY());
						System.out.println("near start: " + nearPoolPointStart
								+ " end: " + nearPoolPointEnd);
						List<Point> pointCommon = getPointOnCommonEdge(
								pointStart, pointEnd, nearPoolPointStart,
								nearPoolPointEnd);
						if (pointCommon != null) {
							Segment segment = new Segment(EDirection.RIGHT,
									pointCommon.get(0).getY(), pointCommon.get(
											1).getY());
							segment.setNeighbourClientName(nearPool
									.getDeviceInfo().getClientName());
							pool.getSegments().add(segment);

							segment = new Segment(EDirection.LEFT, pointCommon
									.get(0).getY(), pointCommon.get(1).getY());
							segment.setNeighbourClientName(pool.getDeviceInfo()
									.getClientName());
							nearPool.getSegments().add(segment);
						}
					}
				}

				System.out.println("------------Top---------------");
				pointStart = new Point(boundary.getMinX(), boundary.getMaxY());
				pointEnd = new Point(boundary.getMaxX(), boundary.getMaxY());
				System.out
						.println("start: " + pointStart + " end: " + pointEnd);

				// lặp tìm trong các pool còn lại
				for (Pool nearPool : list) {
					if (!nearPool.equals(pool)) {
						// lấy cạnh bên dưới
						Boundary nearPoolBoundary = nearPool.getBoundary();
						System.out
								.println("near boundary: " + nearPoolBoundary);
						Point nearPoolPointStart = new Point(
								nearPoolBoundary.getMinX(),
								nearPoolBoundary.getMinY());
						Point nearPoolPointEnd = new Point(
								nearPoolBoundary.getMaxX(),
								nearPoolBoundary.getMinY());
						System.out.println("near start: " + nearPoolPointStart
								+ " end: " + nearPoolPointEnd);
						List<Point> pointCommon = getPointOnCommonEdge(
								pointStart, pointEnd, nearPoolPointStart,
								nearPoolPointEnd);
						if (pointCommon != null) {
							Segment segment = new Segment(EDirection.TOP,
									pointCommon.get(0).getX(), pointCommon.get(
											1).getX());
							segment.setNeighbourClientName(nearPool
									.getDeviceInfo().getClientName());
							pool.getSegments().add(segment);

							segment = new Segment(EDirection.BOTTOM,
									pointCommon.get(0).getX(), pointCommon.get(
											1).getX());
							segment.setNeighbourClientName(pool.getDeviceInfo()
									.getClientName());
							nearPool.getSegments().add(segment);
						} else {
							System.out
									.println("top -bottom: not found common point");
						}
					}
				}
			}
		}

		return list;
	}

	public static List<Point> getPointOnCommonEdge(Point srcPointStart,
			Point srcPointEnd, Point tarPointStart, Point tarPointEnd) {
		List<Point> list = new ArrayList<Point>();
		Point start = new Point();
		Point end = new Point();
		if (srcPointStart.getX() == tarPointEnd.getX()
				&& srcPointStart.getX() == tarPointStart.getX()) {
			start.setX(srcPointStart.getX());
			end.setX(srcPointStart.getX());
			if (tarPointStart.getY() < srcPointEnd.getY()
					&& tarPointStart.getY() >= srcPointStart.getY()) {
				start.setY(tarPointStart.getY());
				if (srcPointEnd.getY() > tarPointEnd.getY()) {
					end.setY(tarPointEnd.getY());
				} else {
					end.setY(srcPointEnd.getY());
				}
			} else if (tarPointStart.getY() < srcPointStart.getY()
					&& tarPointEnd.getY() > srcPointStart.getY()) {
				start.setY(srcPointStart.getY());
				if (srcPointEnd.getY() > tarPointEnd.getY()) {
					end.setY(tarPointEnd.getY());
				} else {
					end.setY(srcPointEnd.getY());
				}
			} else {
				return null;
			}

			list.add(start);
			list.add(end);
			return list;
		} else if (srcPointStart.getY() == tarPointStart.getY()
				&& srcPointStart.getY() == tarPointEnd.getY()) {
			start.setY(srcPointStart.getY());
			end.setY(srcPointStart.getY());
			if (tarPointStart.getX() < srcPointEnd.getX()
					&& tarPointStart.getX() >= srcPointStart.getX()) {
				start.setX(tarPointStart.getX());
				if (srcPointEnd.getX() > tarPointEnd.getX()) {
					end.setX(tarPointEnd.getX());
				} else {
					end.setX(srcPointEnd.getX());
				}
			} else if (tarPointStart.getX() < srcPointStart.getX()
					&& tarPointEnd.getX() > srcPointStart.getX()) {
				start.setX(srcPointStart.getX());
				if (srcPointEnd.getX() > tarPointEnd.getX()) {
					end.setX(tarPointEnd.getX());
				} else {
					end.setX(srcPointEnd.getX());
				}
			} else {
				return null;
			}

			list.add(start);
			list.add(end);
			return list;
		} else {
			return null;
		}
	}

	public static List<Pools> getListPoolsWithout(List<Pool> list, Pool pool) {
		return null;
	}

	public static List<Pools> findPoolsHave1CommonEdge(Pool pool, Point start,
			Point end) {
		return null;
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
			Segment rightSegment1 = new Segment(EDirection.RIGHT, 0, pool1
					.getBoundary().getHeight());
			rightSegment1.setNeighbourClientName(pool2.getDeviceInfo()
					.getClientName());
			pool1.getSegments().add(rightSegment1);
			Segment leftSegment2 = new Segment(EDirection.LEFT, 0, pool1
					.getBoundary().getHeight());
			leftSegment2.setNeighbourClientName(pool1.getDeviceInfo()
					.getClientName());
			pool2.getSegments().add(leftSegment2);
		}
		if (pools.size() > 3) {
			Pool firstPool = pools.get(0);
			Pool lastPool = pools.get(pools.size() - 1);
			Segment rightSegment1 = new Segment(EDirection.RIGHT, 0, firstPool
					.getBoundary().getHeight());
			rightSegment1.setNeighbourClientName(pools.get(1).getDeviceInfo()
					.getClientName());
			List<Segment> firstPoolSegments = firstPool.getSegments();
			firstPoolSegments.clear();
			firstPoolSegments.add(rightSegment1);
			Segment leftSegment2 = new Segment(EDirection.LEFT, 0, lastPool
					.getBoundary().getHeight());
			leftSegment2.setNeighbourClientName(pools.get(pools.size() - 2)
					.getDeviceInfo().getClientName());
			List<Segment> lastPoolSegments = lastPool.getSegments();
			lastPoolSegments.clear();
			lastPoolSegments.add(leftSegment2);
			for (int i = 1; i < pools.size() - 1; i++) {
				Pool pool = pools.get(i);
				Segment leftSegment = new Segment(EDirection.LEFT, 0, pool
						.getBoundary().getHeight());
				leftSegment.setNeighbourClientName(pools.get(i - 1)
						.getDeviceInfo().getClientName());
				Segment rightSegment = new Segment(EDirection.RIGHT, 0, pool
						.getBoundary().getHeight());
				rightSegment.setNeighbourClientName(pools.get(i + 1)
						.getDeviceInfo().getClientName());
				List<Segment> segments = pool.getSegments();
				segments.clear();
				segments.add(leftSegment);
				segments.add(rightSegment);
			}
		}
	}
}
