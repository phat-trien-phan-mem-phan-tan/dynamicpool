package vn.edu.hust.student.dynamicpool.bll.poolmanager;

import java.awt.Rectangle;

public class PoolPosition implements IPoolPosition{

	private Rectangle position;
	
	
	public PoolPosition(){
		
		this.position = new Rectangle();
		
	}
	
	
	public PoolPosition(Rectangle position){
		this.position = position;
	}
	
	@Override
	public void setPosition(Rectangle position) {
		this.position = position;
	}



	@Override
	public Rectangle getPosition() {
		
		return position;
	}

	
}
