package org.puppet.server.bussinesslayer;

import java.awt.Point;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FindCommonEdgeFunction implements IFindCommonEgde {
	private Logger log = LoggerFactory.getLogger(FindCommonEdgeFunction.class);

	@Override
	public void findCommonEdge(List<IPool> pools) {
		int n = pools.size();
		float x = 0, y = 0;
		for (int i = 0; i < n; i++) {
			// vi tri khoi tao mac dinh xep thanh hang ngang\
			if (i == 0) {
				x = 0;
				y = 0;
			} else if (i == 1) {
				x = pools.get(0).getRetangle().getWidth();
				y = 0;
			} else {
				x = pools.get(i - 1).getRetangle().getWidth()
						+ pools.get(i - 2).getRetangle().getWidth();
				y = 0;
			}
			log.debug("x: {}, y: {}", x, y);
			pools.get(i).convertDeviceInfoToRect();
			Point point = new Point();
			point.setLocation(x, y);
			pools.get(i).getRetangle().setCoordinate(point);

			log.debug("pool {} location {} {}", i, pools.get(i).getRetangle()
					.getCoordinate().getX(), pools.get(i).getRetangle()
					.getCoordinate().getY());
		}

		float[][] xCoordinate = new float[n][4];
		float[][] yCoordinate = new float[n][4];
		for (int i = 0; i < n; i++) {
			// hoanh do cac dinh cua hinh chu nhat thu i
			xCoordinate[i][0] = (float) pools.get(i).getRetangle()
					.getCoordinate().getX();
			xCoordinate[i][1] = (float) (pools.get(i).getRetangle()
					.getCoordinate().getX() + pools.get(i).getRetangle()
					.getWidth());
			xCoordinate[i][2] = xCoordinate[i][1];
			xCoordinate[i][3] = xCoordinate[i][0];
			// tung do
			yCoordinate[i][0] = (float) pools.get(i).getRetangle()
					.getCoordinate().getY();
			yCoordinate[i][1] = yCoordinate[i][0];
			yCoordinate[i][2] = (float) (pools.get(i).getRetangle()
					.getCoordinate().getY() + pools.get(i).getRetangle()
					.getHeight());
			yCoordinate[i][3] = yCoordinate[i][2];
		}

		float startx;
		float starty = 0;
		float endx = 0;
		float endy;

		// kiem tra canh chung cua moi hinh chu nhat voi cac hinh chu nhat ben
		// canh
		// dua tap hop cac canh chung cua tung hinh vao mot danh sach
		for (int i = 0; i < n; i++) {

			for (int j = 0; j < n; j++) {

				if (j == i) {

				} else {
					/*log.debug("x[i] = {}, x[j] = {}", xCoordinate[i][0],
							xCoordinate[j][1]);*/
					System.out.println(xCoordinate[i][0]+":"+xCoordinate[j][1]);
					if ((int)xCoordinate[i][0] == (int)xCoordinate[j][1]) {
						// x1[3]=x2[2]
						starty = (yCoordinate[i][0] > yCoordinate[j][1] ? yCoordinate[i][0]
								: yCoordinate[j][1]);
						endy = (yCoordinate[i][3] < yCoordinate[j][2] ? yCoordinate[i][3]
								: yCoordinate[j][2]);
						if (starty <= endy) {

							System.out
									.println(xCoordinate[i][1] + ":" + starty);
							System.out.println(xCoordinate[i][1] + ":" + endy);

							Point pointS = new Point();
							pointS.setLocation(xCoordinate[i][0], starty);
							Point pointSConvert = new Point();
							pointSConvert.setLocation(
									(xCoordinate[i][0]
											* pools.get(i).getDeviceInfo()
													.getHeight() / pools.get(i)
											.getRetangle().getHeight()), starty
											* pools.get(i).getDeviceInfo()
													.getHeight()
											/ pools.get(i).getRetangle()
													.getHeight());

							Point pointE = new Point();
							pointE.setLocation(xCoordinate[i][0], endy);

							Point pointEConvert = new Point();
							pointEConvert.setLocation(
									(xCoordinate[i][0]
											* pools.get(i).getDeviceInfo()
													.getHeight() / pools.get(i)
											.getRetangle().getHeight()),
									(int) (endy
											* pools.get(i).getDeviceInfo()
													.getHeight() / pools.get(i)
											.getRetangle().getHeight()));

							pools.get(i)
									.getSegments()
									.add(new Segment(pointSConvert,
											pointEConvert));
						}
					}

					/*log.debug("x[i][1] = {}, x[j][1] = {}", xCoordinate[i][1],
							xCoordinate[j][0]);*/
					System.out.println(xCoordinate[i][1]+":"+xCoordinate[j][0]);
					
					if ((int)xCoordinate[i][1] ==(int) xCoordinate[j][0]) {
						// x1[2]=x2[3]
						starty = (yCoordinate[i][1] > yCoordinate[j][0] ? yCoordinate[i][1]
								: yCoordinate[j][0]);
						endy = (yCoordinate[i][2] < yCoordinate[j][3] ? yCoordinate[i][2]
								: yCoordinate[j][3]);
						if (starty <= endy) {

							/*
							 * System.out.println(x[i][1]+":"+starty);
							 * System.out.println(x[i][1]+":"+endy);
							 */
							Point pointS = new Point();
							pointS.setLocation(xCoordinate[i][1], starty);

							Point pointSConvert = new Point();
							pointSConvert.setLocation(
									(xCoordinate[i][1]
											* pools.get(i).getDeviceInfo()
													.getHeight() / pools.get(i)
											.getRetangle().getHeight()),
									(starty
											* pools.get(i).getDeviceInfo()
													.getHeight() / pools.get(i)
											.getRetangle().getHeight()));
							Point pointE = new Point();
							pointE.setLocation(xCoordinate[i][1], endy);
							Point pointEConvert = new Point();
							pointEConvert.setLocation(
									(xCoordinate[i][1]
											* pools.get(i).getDeviceInfo()
													.getHeight() / pools.get(i)
											.getRetangle().getHeight()), (endy
											* pools.get(i).getDeviceInfo()
													.getHeight() / pools.get(i)
											.getRetangle().getHeight()));
							pools.get(i)
									.getSegments()
									.add(new Segment(pointSConvert,
											pointEConvert));
						}
					}

					/*log.debug("y[i][0] = {}, y[j][3] = {}", yCoordinate[i][0],
							yCoordinate[j][3]);*/
					System.out.println(yCoordinate[i][0]+":"+yCoordinate[j][3]);
					if ((int)yCoordinate[i][0] ==(int) yCoordinate[j][3]) {
						// y1[1]==y2[2]
						startx = (xCoordinate[i][0] > xCoordinate[j][3] ? xCoordinate[i][0]
								: xCoordinate[j][3]);
						endx = (xCoordinate[i][1] < xCoordinate[j][2] ? xCoordinate[i][1]
								: xCoordinate[j][2]);
						if (startx <= endx) {
							Point pointS = new Point();
							pointS.setLocation(startx, yCoordinate[i][0]);
							Point pointE = new Point();
							pointE.setLocation(endx, yCoordinate[i][0]);

							Point pointSConvert = new Point();
							pointSConvert.setLocation(
									(startx
											* pools.get(i).getDeviceInfo()
													.getWidth() / pools.get(i)
											.getRetangle().getWidth()),
									(yCoordinate[i][0]
											* pools.get(i).getDeviceInfo()
													.getWidth() / pools.get(i)
											.getRetangle().getWidth()));
							Point pointEConvert = new Point();
							pointEConvert.setLocation(
									(endx
											* pools.get(i).getDeviceInfo()
													.getWidth() / pools.get(i)
											.getRetangle().getWidth()),
									(yCoordinate[i][0]
											* pools.get(i).getDeviceInfo()
													.getWidth() / pools.get(i)
											.getRetangle().getWidth()));

							pools.get(i)
									.getSegments()
									.add(new Segment(pointSConvert,
											pointEConvert));

						}
					}

				/*	log.debug("y[i][3] = {}, y[j][0] = {}", yCoordinate[i][3],
							yCoordinate[j][0]);*/
					System.out.println(yCoordinate[i][3]+":"+yCoordinate[j][0]);
					if ((int)yCoordinate[i][3] == (int)yCoordinate[j][0]) {
						// y1[2]==y2[1]
						startx = (xCoordinate[i][3] > xCoordinate[j][0] ? xCoordinate[i][3]
								: xCoordinate[j][0]);
						endx = (xCoordinate[i][2] < xCoordinate[j][1] ? xCoordinate[i][2]
								: xCoordinate[j][1]);
						if (startx <= endx) {
							Point pointS = new Point();
							pointS.setLocation(startx, yCoordinate[i][3]);
							Point pointE = new Point();
							pointE.setLocation(endx, yCoordinate[i][3]);
							Point pointSConvert = new Point();
							pointSConvert.setLocation(
									(startx
											* pools.get(i).getDeviceInfo()
													.getWidth() / pools.get(i)
											.getRetangle().getWidth()),
									(yCoordinate[i][3]
											* pools.get(i).getDeviceInfo()
													.getWidth() / pools.get(i)
											.getRetangle().getWidth()));

							Point pointEConvert = new Point();
							pointEConvert.setLocation(
									(endx
											* pools.get(i).getDeviceInfo()
													.getWidth() / pools.get(i)
											.getRetangle().getWidth()),
									(yCoordinate[i][3]
											* pools.get(i).getDeviceInfo()
													.getWidth() / pools.get(i)
											.getRetangle().getWidth()));
							Segment segment = new Segment(pointSConvert,
									pointEConvert);
							pools.get(i).getSegments().add(segment);

						}
					}
				}
			}
		}
	}
}
