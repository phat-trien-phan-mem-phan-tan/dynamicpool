package vn.edu.hust.student.dynamicpool.model;

public class Pool {

	private float width_limit;
	private float high_limit;
	
	
	
	public Pool(){
		this.width_limit = 0;
		this.high_limit = 0;
		
	}
	
	public Pool(float width_limit,float high_limit){
		this.width_limit = width_limit;
		this.high_limit = high_limit;
	}
	
	public float getWidth_limit() {
		return width_limit;
	}
	public void setWidth_limit(float width_limit) {
		this.width_limit = width_limit;
	}
	public float getHigh_limit() {
		return high_limit;
	}
	public void setHigh_limit(float high_limit) {
		this.high_limit = high_limit;
	}
	
	
	
}
