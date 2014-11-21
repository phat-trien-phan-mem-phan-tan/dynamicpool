package org.puppet.server.bussinesslayer;

import java.util.ArrayList;

public interface IPool {
	public static int key=0;
	public  ArrayList<Point> returnPointStart=null;
	public  ArrayList<Point> returnPointEnd=null;
	public void definePool(int width,int height, float diagonal);
	
}
