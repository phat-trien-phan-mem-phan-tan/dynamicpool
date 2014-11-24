package vn.edu.hust.student.dynamicpool.equation.vector;

public class Vector {

	private double x;
	private double y;
	
	public Vector(double x,double y){
		this.x = x;
		this.y = y;
	}
	
	public Vector(){
		this.x = 0;
		this.y = 0;
	}
	
	
	@Override
	public boolean equals(Object object) {
		// TODO Auto-generated method stub
		
		Vector vector = (Vector)object;
		
		if(x == vector.getX() && y == vector.getY()){
			return true;
		}else return false;
		
		
	}

	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
	
	
}
