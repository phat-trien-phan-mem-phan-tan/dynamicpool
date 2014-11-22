package vn.edu.hust.student.dynamicpool.dal.utils.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import vn.edu.hust.student.dynamicpool.dal.config.ServerNetworkConfig;
import vn.edu.hust.student.dynamicpool.dal.config.SocketServerConfig;
import vn.edu.hust.student.dynamicpool.dal.processor.Processor;

public class ServerXMLConfigReader extends XMLReader {

	public ServerXMLConfigReader(String path) throws Exception {
		super(path);
		this.rootNodeName = "/server";
	}
	
	public Map<String, Class<? extends Processor>> getProcessorMap() {
		NodeList nodeList = readPath("processors/command");
		if (nodeList.getLength() > 0) {
			Map<String, Class<? extends Processor>> result = new HashMap<String, Class<? extends Processor>>();
			for (int i=0; i<nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				String classPath = node.getTextContent().trim();
				try {
					@SuppressWarnings("unchecked")
					Class<? extends Processor> clazz = (Class<? extends Processor>) Class.forName(classPath);
					System.out.println(query(node, "@name") + " => " + node.getTextContent());
					result.put(query(node, "@name"), clazz);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
			}
			return result;
		}
		return null;
	}

	public SocketServerConfig getSocketServerConfig() {
		NodeList nodeList = readPath("socket");
		if (nodeList != null && nodeList.getLength() > 0) {
			int i;
			String pattern = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
					+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
			Node socketConfigNode = nodeList.item(0);
			SocketServerConfig result = new SocketServerConfig();
			// get network config
			NodeList networkConfigNodeList = this.querySelectorAll(socketConfigNode, "network/entry");
			for (i = 0; i < networkConfigNodeList.getLength(); i++) {
				Node entry = networkConfigNodeList.item(i);
				String portStr = this.query(entry, "port").trim();
				String host = this.query(entry, "host");
				if (portStr.length() > 0) {
					int port = Integer.valueOf(portStr);
					ServerNetworkConfig networkConfig = new ServerNetworkConfig();
					networkConfig.setPort(port);
					if (host.trim().length() > 0 && host.trim().matches(pattern)) {
						networkConfig.setHost(host);
					}
					result.addNetworkConfig(networkConfig);
				}
			}
			// retrive handler list
			NodeList handlerConfigNodeList = this.querySelectorAll(socketConfigNode, "handlers/handler");
			if (handlerConfigNodeList != null && handlerConfigNodeList.getLength() > 0) {
				List<String> handlers = new ArrayList<String>();
				for (i=0; i<handlerConfigNodeList.getLength(); i++) {
					handlers.add(handlerConfigNodeList.item(i).getTextContent().trim());
				}
				result.setHandlers(handlers);
				System.out.println(handlers);
			}
			return result;
		}
		return null;
	}
}
