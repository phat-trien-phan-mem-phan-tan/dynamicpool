package vn.edu.hust.student.dynamicpool.bll.model;

import java.util.ArrayList;
import java.util.List;


public class PoolManager{
	private List<Pool> pools;
	
	public PoolManager(){
		pools = new ArrayList<Pool>();
	}
	
	public List<Pool> getPools() {
		return pools;
	}
	
	public void add(Pool pool){
		initDefaultPosition(pool);
		this.pools.add(pool);
	}
	
	private void initDefaultPosition(Pool poolServer) {
		float x = 0, y = 0;
		for (Pool pool : pools) {
			x = Math.max(pool.getBoundary().getMaxX(), x);
			y = Math.min(pool.getBoundary().getMinY(), y);
		}
		poolServer.getBoundary().setLocation(new Point(x, y));
	}

	public int size(){
		return this.pools.size();
	}
	
	public void calculate() {
		FindCommonEdgeFunction.findCommonEdge(this.pools);
	}
}
