package vn.edu.hust.student.dynamicpool.bll;

import vn.edu.hust.student.dynamicpool.exception.DALException;

public interface BusinessLogicDataCallback {
	void callback(Object data, DALException ex);
}
