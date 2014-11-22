import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vn.edu.hust.student.dynamicpool.dal.controller.HostMainController;


public class Main {
	private Logger logger;
	
	public static void main(String[] args) {
		final Main main = new Main();
		main.start();
	}

	private void start() {
		HostMainController.getInstance().start();
	}

	private void stop() {
		logger = LoggerFactory.getLogger(Main.class);
		logger.info("stopping socket server and http server...");
		HostMainController.getInstance().stop();
	}
}
