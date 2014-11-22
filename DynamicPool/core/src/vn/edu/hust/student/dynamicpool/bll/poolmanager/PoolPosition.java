package vn.edu.hust.student.dynamicpool.bll.poolmanager;

import vn.edu.hust.student.dynamicpool.model.Rectangle;


public class PoolPosition implements IPoolPosition{

	private Rectangle position;
	
	
	public PoolPosition(){
		
		this.position = new Rectangle(0,0);
		
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
