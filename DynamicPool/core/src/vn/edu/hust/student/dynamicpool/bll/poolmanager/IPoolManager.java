package vn.edu.hust.student.dynamicpool.bll.poolmanager;

import java.util.List;


public interface IPoolManager {

	List<IPool> getPools();

	void addPool(IPool pool);
}
