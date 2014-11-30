import java.util.ArrayList;
import java.util.List;

import vn.edu.hust.student.dynamicpool.bll.model.DeviceInfo;
import vn.edu.hust.student.dynamicpool.bll.model.FindCommonEdgeFunction;
import vn.edu.hust.student.dynamicpool.bll.model.Point;
import vn.edu.hust.student.dynamicpool.bll.model.Pool;
import vn.edu.hust.student.dynamicpool.bll.model.Segment;

public class Main {
	public static void main(String[] argv) {
		Pool pool1 = new Pool(new DeviceInfo(1366, 768, 14));
		Pool pool2 = new Pool(new DeviceInfo(1366, 768, 14));
		Pool pool3 = new Pool(new DeviceInfo(1366, 768, 14));
		Pool pool4 = new Pool(new DeviceInfo(683, 384, 14));
		pool2.getBoundary().setLocation(new Point(1366, 0));
		pool3.getBoundary().setLocation(new Point(0, 768));
		pool4.getBoundary().setLocation(new Point(1366, 1152));
		List<Pool> list = new ArrayList<>();
		list.add(pool1);
		list.add(pool2);
		list.add(pool3);
		list.add(pool4);
		List<Pool> pools = FindCommonEdgeFunction.calucalteCommonEdge(list);
		for (Pool pool : pools) {
			System.out.println("---------------------");
			if (pool.getSegments().size() > 0) {
				for (Segment segment : pool.getSegments()) {
					System.out.println(segment);
				}
			}
		}
	}
}
