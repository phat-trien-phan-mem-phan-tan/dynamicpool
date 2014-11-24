package vn.edu.hust.student.dynamicpool.utils.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import vn.edu.hust.student.dynamicpool.config.HttpClientConfig;
import vn.edu.hust.student.dynamicpool.config.SocketClientConfig;
import vn.edu.hust.student.dynamicpool.processor.Processor;

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

	public SocketClientConfig getSocketClientConfig() {
		NodeList nodeList = readPath("socket");
		if (nodeList != null && nodeList.getLength() > 0) {
			int i;
			Node socketConfigNode = nodeList.item(0);
			SocketClientConfig result = new SocketClientConfig();
			// Retrieve handler list
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
	
	public HttpClientConfig getHttpClientConfig() {
		NodeList nodeList = readPath("http");
		if (nodeList != null && nodeList.getLength() > 0) {
			int i;
			Node httpConfigNode = nodeList.item(0);
			HttpClientConfig result = new HttpClientConfig();
			// Retrieve handler list
			NodeList handlerConfigNodeList = this.querySelectorAll(httpConfigNode, "handlers/handler");
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
