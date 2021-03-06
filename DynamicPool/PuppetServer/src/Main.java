import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.puppet.gui.MainFrame;
import org.puppet.server.controller.MainController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

	private Logger logger;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		String log4JPropertyFile = "conf/log4j.properties";
		Properties p = new Properties();

		try {
			p.load(new FileInputStream(log4JPropertyFile));
			PropertyConfigurator.configure(p);
			final Main main = new Main();
			main.start();
			
			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {
					main.stop();
				}
			});
		} catch (IOException e) {
			System.out.println("Opps, cannot load log4j.properties");
		}
	}

	private void start() {
		logger = LoggerFactory.getLogger(Main.class);
		logger.debug("Starting Puppet Server.........");
		MainController.getInstance().start();
		logger.debug("Puppet Server Started Successfully");
//		MainController.getInstance().getHttpClientController().regHost();
//		MainController.getInstance().getSocketClientController()
//				.start("104.131.13.155", 2225);
	}

	private void stop() {
		logger.info("stopping socket server and http server...");
		MainController.getInstance().stop();
	}

}
