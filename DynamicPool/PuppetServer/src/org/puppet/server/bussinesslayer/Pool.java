package org.puppet.server.bussinesslayer;

import java.util.ArrayList;

public class Pool implements IPool{
	
	public int pWidth;//chiều rộng thiết bị
	public int pHeight;//chiều cao thiết bị
	public float diagonal;//đường chéo thiết bị
	public int width; //chiều rộng hình chữ nhật 
	int height;//chiều cao hcn
	public int x,y ;//vi tri khoi ta
	public int mul=10;
	@Override
	public void definePool(int width, int height, float diagonal) {
		// TODO Auto-generated method stub
		this.pWidth=width;
		this.pHeight=height;
		this.diagonal=diagonal;
		this.height=(int)(mul*Math.sqrt(Math.pow(diagonal,2)/(1+Math.pow(pWidth/pHeight,2))));
		this.width=(int)(height*pWidth/pHeight);		
		
	}
	public void getPool(){
		
	}
	/*public void setKey(int key){
		this.key=key;
	}
	public int getKey(){
		return this.key;
	}*/
	
	
	
	public int getpWidth() {
		return pWidth;
	}
	public void setpWidth(int pWidth) {
		this.pWidth = pWidth;
	}
	public int getpHeight() {
		return pHeight;
	}
	public void setpHeight(int pHeight) {
		this.pHeight = pHeight;
	}
	public float getDiagonal() {
		return diagonal;
	}
	public void setDiagonal(float diagonal) {
		this.diagonal = diagonal;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
}
