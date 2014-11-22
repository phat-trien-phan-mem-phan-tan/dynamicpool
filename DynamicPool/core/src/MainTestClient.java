import vn.edu.hust.student.dynamicpool.dal.controller.ClientMainController;
import vn.edu.hust.student.dynamicpool.exception.NetworkException;


public class MainTestClient {
	public static void main(String[] args) {
		try {
			ClientMainController.getInstance().start(6742);
		} catch (NetworkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
