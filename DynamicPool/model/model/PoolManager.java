package vn.edu.hust.student.dynamicpool.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import vn.edu.hust.student.dynamicpool.bll.poolmanager.IPool;
import vn.edu.hust.student.dynamicpool.bll.poolmanager.IPoolManager;

public class PoolManager extends ArrayList<IPool> implements IPoolManager {

	public PoolManager(){
		super();
		
	}
	
	@Override
	public List<IPool> getPools() {

		return this;
	}


	@Override
	public Stream<IPool> parallelStream() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<IPool> stream() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPool(IPool pool) {
		this.add(pool);
		
	}

}
