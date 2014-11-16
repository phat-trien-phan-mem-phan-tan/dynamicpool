package vn.edu.hust.student.dynamicpool.dal;

import vn.edu.hust.student.dynamicpool.bll.BusinessLogicDataCallback;

public interface DataAccessLayer {
	
	void joinHost(int key, BusinessLogicDataCallback callback);

	void createHost(BusinessLogicDataCallback callback);
}
