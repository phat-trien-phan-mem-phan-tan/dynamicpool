package org.puppet.server.bussinesslayer;

public class clientDimention {
	public int pWidth;//chiều rộng thiết bị
	public int pHeight;//chiều cao thiết bị
	public float diagonal;//đường chéo thiết bị
	public int width; //chiều rộng hình chữ nhật 
	int height;//chiều cao hcn
	public int bs=10;
	public int x,y;
	
	public  clientDimention(int w,int h,float d,int x, int y){
		pWidth=w;
		pHeight=h;
		diagonal=d;
		height=(int)(bs*Math.sqrt(Math.pow(diagonal,2)/(1+Math.pow(pWidth/pHeight,2))));
		width=(int)(height*pWidth/pHeight);
		this.x=x;
		this.y=y;
		}
	
}

