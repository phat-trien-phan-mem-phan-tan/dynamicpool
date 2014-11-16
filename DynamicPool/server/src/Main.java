import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

import vn.edu.hust.student.dynamicpool.controller.MainController;
import vn.edu.hust.student.dynamicpool.exception.NetworkException;


public class Main {
	public static void main(String[] argv) {
		String log4JPropertyFile = "conf/log4j.properties";
		Properties p = new Properties();
		try {
			p.load(new FileInputStream(log4JPropertyFile));
			PropertyConfigurator.configure(p);
			MainController.getInstance().start(2095);
			
		} catch (NetworkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
