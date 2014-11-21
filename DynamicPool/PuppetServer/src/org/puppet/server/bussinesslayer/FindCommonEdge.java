package org.puppet.server.bussinesslayer;

import java.util.ArrayList;
import java.util.List;

//yêu cầu mỗi hình chữ nhật phải ánh xạ được sang một thiết bị duy nhất
public class FindCommonEdge {
	public static List<ArrayList<Segment>> arraylist = new ArrayList<>();

	public FindCommonEdge(List<Pool> pool) {
		int n = ListPool.sumPool;
		int x = 0, y = 0;
		for (int i = 0; i < n; i++) {

			// vi tri khoi tao mac dinh xep thanh hang ngang\
			if (i == 0) {
				x = 0;
				y = 0;
			} else if (i == 1) {
				x = pool.get(0).width;
				y = 0;
			} else {
				x = pool.get(i - 1).width + pool.get(i - 2).width;
				y = 0;
			}
			pool.get(i).definePool(pool.get(i).width, pool.get(i).height,
					pool.get(i).diagonal);
			pool.get(i).setX(x);
			pool.get(i).setY(y);
		}

		System.out.println("so thiet bi " + n);
		int[][] xCoordinate = new int[n][4];
		int[][] yCoordinate = new int[n][4];
		for (int i = 0; i < n; i++) {

			// hoanh do cac dinh cua hinh chu nhat thu i
			xCoordinate[i][0] = (int) pool.get(i).x;
			xCoordinate[i][1] = (int) (pool.get(i).x + pool.get(i).width);
			xCoordinate[i][2] = xCoordinate[i][1];
			xCoordinate[i][3] = xCoordinate[i][0];
			// tung do
			yCoordinate[i][0] = (int) pool.get(i).y;
			yCoordinate[i][1] = yCoordinate[i][0];
			yCoordinate[i][2] = (int) (pool.get(i).y + pool.get(i).height);
			yCoordinate[i][3] = yCoordinate[i][2];
		}
		int startx;
		int starty = 0;
		int endx = 0;
		int endy;

		// kiem tra canh chung cua moi hinh chu nhat voi cac hinh chu nhat ben
		// canh
		// dua tap hop cac canh chung cua tung hinh vao mot danh sach
		for (int i = 0; i < n; i++) {
			ArrayList<Segment> seglist = new ArrayList<>();
			for (int j = 0; j < n; j++) {

				if (j == i) {

				} else {
					if (xCoordinate[i][0] == xCoordinate[j][1]) {
						// x1[3]=x2[2]
						starty = (yCoordinate[i][0] > yCoordinate[j][1] ? yCoordinate[i][0]
								: yCoordinate[j][1]);
						endy = (yCoordinate[i][3] < yCoordinate[j][2] ? yCoordinate[i][3]
								: yCoordinate[j][2]);
						if (starty <= endy) {

							System.out
									.println(xCoordinate[i][1] + ":" + starty);
							System.out.println(xCoordinate[i][1] + ":" + endy);
							Point pointS = new Point(xCoordinate[i][0], starty);
							Point pointE = new Point(xCoordinate[i][0], endy);
							Segment segment = new Segment(pointS, pointE);

							seglist.add(segment);

						}
					}

					if (xCoordinate[i][1] == xCoordinate[j][0]) {
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
							Point pointS = new Point(xCoordinate[i][1], starty);
							Point pointE = new Point(xCoordinate[i][1], endy);
							// Segment segment=new Segment(pointS,pointE);
							/*
							 * System.out.println(pointS.x);
							 * System.out.println(pointS.y);
							 */
							// arraylist[i].add(segment);
							Segment segment = new Segment(pointS, pointE);

							seglist.add(segment);

						}
					}

					if (yCoordinate[i][0] == yCoordinate[j][3]) {
						// y1[1]==y2[2]
						startx = (xCoordinate[i][0] > xCoordinate[j][3] ? xCoordinate[i][0]
								: xCoordinate[j][3]);
						endx = (xCoordinate[i][1] < xCoordinate[j][2] ? xCoordinate[i][1]
								: xCoordinate[j][2]);
						if (startx <= endx) {
							Point pointS = new Point(startx, yCoordinate[i][0]);
							Point pointE = new Point(endx, yCoordinate[i][0]);
							Segment segment = new Segment(pointS, pointE);
							seglist.add(segment);

						}
					}

					if (yCoordinate[i][3] == yCoordinate[j][0]) {
						// y1[2]==y2[1]
						startx = (xCoordinate[i][3] > xCoordinate[j][0] ? xCoordinate[i][3]
								: xCoordinate[j][0]);
						endx = (xCoordinate[i][2] < xCoordinate[j][1] ? xCoordinate[i][2]
								: xCoordinate[j][1]);
						if (startx <= endx) {
							Point pointS = new Point(startx, yCoordinate[i][3]);
							Point pointE = new Point(endx, yCoordinate[i][3]);
							Segment segment = new Segment(pointS, pointE);
							seglist.add(segment);

						}
					}
				}

			}
			arraylist.add(seglist);
		}
	}

	public static void main(String[] args) {

		/*
		 * System.out.println(FindCommonEdge.arraylist.size());
		 * System.out.println(FindCommonEdge.arraylist.size());
		 * System.out.println(FindCommonEdge.arraylist.size());
		 */
		/*
		 * for (int i = 0; i < ListPool.listPool.size(); i++) {
		 * System.out.println("Canh chung hinh thu " + (i + 1)); for (int j = 0;
		 * j < arraylist.get(i).size(); j++) {
		 * 
		 * Segment segment = (Segment) (arraylist.get(i).get(j));
		 * 
		 * System.out.println(segment.getPointS().x + ":" +
		 * segment.getPointS().y); System.out.println(segment.getPointE().x +
		 * ":" + segment.getPointE().y);
		 * 
		 * } }
		 */

	}

}
