package vn.edu.hust.student.dynamicpool.bll.model;

import java.util.List;

public class FindCommonEdgeFunction {
	public static void findCommonEdge(List<Pool> pools) {
		int numberOfPools = pools.size();
		initOrderOfPools(pools);

		float[][] xCoordinate = new float[numberOfPools][4];
		float[][] yCoordinate = new float[numberOfPools][4];
		initPositionForPools(pools, numberOfPools, xCoordinate, yCoordinate);

		findSegments(pools, numberOfPools, xCoordinate, yCoordinate);
	}

	private static void initOrderOfPools(List<Pool> pools) {
		float lastX = 0;
		for (Pool Pool : pools) {
			Boundary poolPosition = Pool.getBoundary();
			poolPosition.getLocation().setX(lastX);
			lastX += poolPosition.getWidth();
		}
	}
	
	private static void initPositionForPools(List<Pool> pools, int numberOfPools,
			float[][] xCoordinate, float[][] yCoordinate) {
		for (int i = 0; i<pools.size(); i++) {
			Boundary corrdiate = pools.get(i).getBoundary();
			float xMin = corrdiate.getMinX();
			float yMin = corrdiate.getMinY();
			float xMax = xMin + corrdiate.getWidth();
			float yMax = yMin + corrdiate.getHeight();
			xCoordinate[i][0] = xMin;
			yCoordinate[i][0] = yMax;
			xCoordinate[i][1] = xMax;
			yCoordinate[i][1] = yMax;
			xCoordinate[i][2] = xMax;
			yCoordinate[i][2] = yMin;
			xCoordinate[i][3] = xMin;
			yCoordinate[i][3] = yMin;
		}
	}
	
	private static void findSegments(List<Pool> pools, int numberOfPools,
			float[][] xCoordinate, float[][] yCoordinate) {
//		Line line = new Line();
//		// kiem tra canh chung cua moi hinh chu nhat voi cac hinh chu nhat ben
//		// canh
//		// dua tap hop cac canh chung cua tung hinh vao mot danh sach
//		for (int i = 0; i < numberOfPools; i++) {
//
//			for (int j = 0; j < numberOfPools; j++) {
//
//				if (j != i) {
//					System.out.println(xCoordinate[i][0]+":"+xCoordinate[j][1]);
//					if ((int)xCoordinate[i][0] == (int)xCoordinate[j][1]) {
//						// x1[3]=x2[2]
//						starty = (yCoordinate[i][0] > yCoordinate[j][1] ? yCoordinate[i][0]
//								: yCoordinate[j][1]);
//						endy = (yCoordinate[i][3] < yCoordinate[j][2] ? yCoordinate[i][3]
//								: yCoordinate[j][2]);
//						if (starty <= endy) {
//
//							System.out
//									.println(xCoordinate[i][1] + ":" + starty);
//							System.out.println(xCoordinate[i][1] + ":" + endy);
//
//							Point pointS = new Point();
//							pointS.setLocation(xCoordinate[i][0], starty);
//							Point pointSConvert = new Point();
//							pointSConvert.setLocation(
//									(xCoordinate[i][0]
//											* pools.get(i).getDeviceInfo()
//													.getHeight() / pools.get(i)
//											.getRetangle().getHeight()), starty
//											* pools.get(i).getDeviceInfo()
//													.getHeight()
//											/ pools.get(i).getRetangle()
//													.getHeight());
//
//							Point pointE = new Point();
//							pointE.setLocation(xCoordinate[i][0], endy);
//
//							Point pointEConvert = new Point();
//							pointEConvert.setLocation(
//									(xCoordinate[i][0]
//											* pools.get(i).getDeviceInfo()
//													.getHeight() / pools.get(i)
//											.getRetangle().getHeight()),
//									(int) (endy
//											* pools.get(i).getDeviceInfo()
//													.getHeight() / pools.get(i)
//											.getRetangle().getHeight()));
//
//							pools.get(i)
//									.getSegments()
//									.add(new Segment(pointSConvert,
//											pointEConvert));
//						}
//					}
//
//					/*log.debug("x[i][1] = {}, x[j][1] = {}", xCoordinate[i][1],
//							xCoordinate[j][0]);*/
//					System.out.println(xCoordinate[i][1]+":"+xCoordinate[j][0]);
//					
//					if ((int)xCoordinate[i][1] ==(int) xCoordinate[j][0]) {
//						// x1[2]=x2[3]
//						starty = (yCoordinate[i][1] > yCoordinate[j][0] ? yCoordinate[i][1]
//								: yCoordinate[j][0]);
//						endy = (yCoordinate[i][2] < yCoordinate[j][3] ? yCoordinate[i][2]
//								: yCoordinate[j][3]);
//						if (starty <= endy) {
//
//							/*
//							 * System.out.println(x[i][1]+":"+starty);
//							 * System.out.println(x[i][1]+":"+endy);
//							 */
//							Point pointS = new Point();
//							pointS.setLocation(xCoordinate[i][1], starty);
//
//							Point pointSConvert = new Point();
//							pointSConvert.setLocation(
//									(xCoordinate[i][1]
//											* pools.get(i).getDeviceInfo()
//													.getHeight() / pools.get(i)
//											.getRetangle().getHeight()),
//									(starty
//											* pools.get(i).getDeviceInfo()
//													.getHeight() / pools.get(i)
//											.getRetangle().getHeight()));
//							Point pointE = new Point();
//							pointE.setLocation(xCoordinate[i][1], endy);
//							Point pointEConvert = new Point();
//							pointEConvert.setLocation(
//									(xCoordinate[i][1]
//											* pools.get(i).getDeviceInfo()
//													.getHeight() / pools.get(i)
//											.getRetangle().getHeight()), (endy
//											* pools.get(i).getDeviceInfo()
//													.getHeight() / pools.get(i)
//											.getRetangle().getHeight()));
//							pools.get(i)
//									.getSegments()
//									.add(new Segment(pointSConvert,
//											pointEConvert));
//						}
//					}
//
//					/*log.debug("y[i][0] = {}, y[j][3] = {}", yCoordinate[i][0],
//							yCoordinate[j][3]);*/
//					System.out.println(yCoordinate[i][0]+":"+yCoordinate[j][3]);
//					if ((int)yCoordinate[i][0] ==(int) yCoordinate[j][3]) {
//						// y1[1]==y2[2]
//						startx = (xCoordinate[i][0] > xCoordinate[j][3] ? xCoordinate[i][0]
//								: xCoordinate[j][3]);
//						endx = (xCoordinate[i][1] < xCoordinate[j][2] ? xCoordinate[i][1]
//								: xCoordinate[j][2]);
//						if (startx <= endx) {
//							Point pointS = new Point();
//							pointS.setLocation(startx, yCoordinate[i][0]);
//							Point pointE = new Point();
//							pointE.setLocation(endx, yCoordinate[i][0]);
//
//							Point pointSConvert = new Point();
//							pointSConvert.setLocation(
//									(startx
//											* pools.get(i).getDeviceInfo()
//													.getWidth() / pools.get(i)
//											.getRetangle().getWidth()),
//									(yCoordinate[i][0]
//											* pools.get(i).getDeviceInfo()
//													.getWidth() / pools.get(i)
//											.getRetangle().getWidth()));
//							Point pointEConvert = new Point();
//							pointEConvert.setLocation(
//									(endx
//											* pools.get(i).getDeviceInfo()
//													.getWidth() / pools.get(i)
//											.getRetangle().getWidth()),
//									(yCoordinate[i][0]
//											* pools.get(i).getDeviceInfo()
//													.getWidth() / pools.get(i)
//											.getRetangle().getWidth()));
//
//							pools.get(i)
//									.getSegments()
//									.add(new Segment(pointSConvert,
//											pointEConvert));
//
//						}
//					}
//
//				/*	log.debug("y[i][3] = {}, y[j][0] = {}", yCoordinate[i][3],
//							yCoordinate[j][0]);*/
//					System.out.println(yCoordinate[i][3]+":"+yCoordinate[j][0]);
//					if ((int)yCoordinate[i][3] == (int)yCoordinate[j][0]) {
//						// y1[2]==y2[1]
//						startx = (xCoordinate[i][3] > xCoordinate[j][0] ? xCoordinate[i][3]
//								: xCoordinate[j][0]);
//						endx = (xCoordinate[i][2] < xCoordinate[j][1] ? xCoordinate[i][2]
//								: xCoordinate[j][1]);
//						if (startx <= endx) {
//							Point pointS = new Point();
//							pointS.setLocation(startx, yCoordinate[i][3]);
//							Point pointE = new Point();
//							pointE.setLocation(endx, yCoordinate[i][3]);
//							Point pointSConvert = new Point();
//							pointSConvert.setLocation(
//									(startx
//											* pools.get(i).getDeviceInfo()
//													.getWidth() / pools.get(i)
//											.getRetangle().getWidth()),
//									(yCoordinate[i][3]
//											* pools.get(i).getDeviceInfo()
//													.getWidth() / pools.get(i)
//											.getRetangle().getWidth()));
//
//							Point pointEConvert = new Point();
//							pointEConvert.setLocation(
//									(endx
//											* pools.get(i).getDeviceInfo()
//													.getWidth() / pools.get(i)
//											.getRetangle().getWidth()),
//									(yCoordinate[i][3]
//											* pools.get(i).getDeviceInfo()
//													.getWidth() / pools.get(i)
//											.getRetangle().getWidth()));
//							Segment segment = new Segment(pointSConvert,
//									pointEConvert);
//							pools.get(i).getSegments().add(segment);
//
//						}
//					}
//				}
//			}
//		}
	}
}
