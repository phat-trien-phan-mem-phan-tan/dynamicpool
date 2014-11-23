package vn.edu.hust.student.dynamicpool.dal.server.logic;

import java.util.ArrayList;
import java.util.List;

import vn.edu.hust.student.dynamicpool.model.Point;

public class PoolManager{
	private List<PoolServer> pools;
	
	public PoolManager(){
		pools = new ArrayList<PoolServer>();
	}
	
	public List<PoolServer> getPools() {
		return pools;
	}
	
	public void add(PoolServer pool){
		this.pools.add(pool);
		initDefaultPosition(pool);
	}
	
	private void initDefaultPosition(PoolServer poolServer) {
		float x = 0, y = 0;
		for (PoolServer pool : pools) {
			x = Math.max(pool.getCorrdiate().getMaxX(), x);
			y = Math.min(pool.getCorrdiate().getMinY(), y);
		}
		poolServer.getCorrdiate().setLocation(new Point(x, y));
	}

	public int size(){
		return this.pools.size();
	}
	
	public void calculate() {
		FindCommonEdgeFunction.findCommonEdge(this.pools);
	}
}
