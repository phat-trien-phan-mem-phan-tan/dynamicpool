package org.puppet.server.bussinesslayer;

import java.util.ArrayList;
import java.util.List;

public class PoolManager{
	private List<IPool> pools;
	private IFindCommonEgde findCommonEdgeFunction;
	
	public PoolManager(){
		pools = new ArrayList<IPool>();
		findCommonEdgeFunction = new FindCommonEdgeFunction();
	}
	
	public List<IPool> getPools() {
		return pools;
	}
	public void setPools(List<IPool> pools) {
		this.pools = pools;
	}
	
	public void add(IPool pool){
		this.pools.add(pool);
	}
	
	public int size(){
		return this.pools.size();
	}
	
	public void calculate() {
		findCommonEdgeFunction.findCommonEdge(this.pools);
	}
}
