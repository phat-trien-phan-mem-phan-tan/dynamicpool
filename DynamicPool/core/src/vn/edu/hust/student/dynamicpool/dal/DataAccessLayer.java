package vn.edu.hust.student.dynamicpool.dal;

import vn.edu.hust.student.dynamicpool.presentation.PresentationBooleanCallback;

public interface DataAccessLayer {
	
	void joinHost(String key, PresentationBooleanCallback callback);

	void createHost(PresentationBooleanCallback callback);
}
