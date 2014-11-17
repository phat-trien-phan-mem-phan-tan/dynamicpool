package vn.edu.hust.student.dynamicpool.bll;

import vn.edu.hust.student.dynamicpool.model.DevideInfor;
import vn.edu.hust.student.dynamicpool.presentation.PresentationBooleanCallback;

public interface BusinessLogicLayer {

	void joinHost(String key, PresentationBooleanCallback callback);

	void createHost(PresentationBooleanCallback callback);
	
	
	void intialDevide(DevideInfor devideInfor,PresentationBooleanCallback callback);
}
