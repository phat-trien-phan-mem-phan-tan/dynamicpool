import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.puppet.server.bussinesslayer.DeviceInfo;
import org.puppet.server.bussinesslayer.Pool;
import org.puppet.server.bussinesslayer.Segment;
import org.puppet.server.controller.MainController;

public class MainTest {
	public static void main(String[] argv) {
		String log4JPropertyFile = "conf/log4j.properties";
		Properties p = new Properties();

		try {
			p.load(new FileInputStream(log4JPropertyFile));
			PropertyConfigurator.configure(p);
		} catch (Exception e) {

		}
		MainController.getInstance().getPoolManager()
				.add(new Pool(new DeviceInfo(1366, 768, 14)));

		MainController.getInstance().getPoolManager()
				.add(new Pool(new DeviceInfo(1366, 768, 14)));
		
		MainController.getInstance().getPoolManager()
		.add(new Pool(new DeviceInfo(1366, 768, 14)));
		
		MainController.getInstance().getPoolManager().calculate();

		List<Segment> segments = MainController.getInstance().getPoolManager()
				.getPools().get(1).getSegments();
		
		for (Segment segment : segments) {
			System.out.println(segment.toString());
		}
		System.out.println(segments.size());
	}
}
