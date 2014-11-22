package vn.edu.hust.student.dynamicpool.dal.server.logic;

import java.util.ArrayList;
import java.util.List;

public class PoolManager{
	private List<PoolServer> pools;
	
	public PoolManager(){
		pools = new ArrayList<PoolServer>();
	}
	
	public List<PoolServer> getPools() {
		return pools;
	}
	public void setPools(List<PoolServer> pools) {
		this.pools = pools;
	}
	
	public void add(PoolServer pool){
		this.pools.add(pool);
	}
	
	public int size(){
		return this.pools.size();
	}
	
	public void calculate() {
		FindCommonEdgeFunction.findCommonEdge(this.pools);
	}
}
