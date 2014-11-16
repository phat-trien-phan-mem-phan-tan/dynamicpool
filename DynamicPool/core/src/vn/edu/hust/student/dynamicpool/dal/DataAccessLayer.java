package vn.edu.hust.student.dynamicpool.dal;

import vn.edu.hust.student.dynamicpool.presentation.PresentationBooleanCallback;

public interface DataAccessLayer {
	
	void joinHost(int key, PresentationBooleanCallback callback);

	void createHost(PresentationBooleanCallback callback);
}
