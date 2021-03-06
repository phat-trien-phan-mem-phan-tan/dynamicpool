package vn.edu.hust.student.dynamicpool.controller;

import java.util.Map;

import org.eclipse.jetty.util.ajax.JSON;
import org.puppet.client.http.HttpClientController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vn.edu.hust.student.dynamicpool.exception.NetworkException;
import vn.edu.hust.student.dynamicpool.processor.Processor;
import vn.edu.hust.student.dynamicpool.server.socket.SocketServerController;
import vn.edu.hust.student.dynamicpool.statics.Field;
import vn.edu.hust.student.dynamicpool.utils.xml.ServerXMLConfigReader;

public class MainController {
	private static MainController _instance;

	private HttpClientController httpClientController;
	private SocketServerController socketServerController;
	private JSON json;
	private Logger logger = LoggerFactory.getLogger(MainController.class);

	private MainController() {

		getLogger().info("Reading config file from path conf/server.xml");
		ServerXMLConfigReader configReader;
		try {
			configReader = new ServerXMLConfigReader("conf/server.xml");
			socketServerController = new SocketServerController(
					configReader.getSocketClientConfig());
			Map<String, Class<? extends Processor>> processorMap = configReader
					.getProcessorMap();
			this.getSocketServerController().initProcessor(processorMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		httpClientController = new HttpClientController();
		json = new JSON();
	}

	public static MainController getInstance() {
		if (_instance == null)
			_instance = new MainController();
		return _instance;
	}

	public void start(int key) throws NetworkException {
		String response = httpClientController.authentication(key);
		@SuppressWarnings("unchecked")
		Map<String, Object> params = (Map<String, Object>) json
				.fromJSON(response);
		if (params.containsKey(Field.ERROR)) {
			if (params.get(Field.ERROR) != null) {
				throw new NetworkException((String) params.get(Field.ERROR));
			} else {
				String ip = (String) params.get("ip");
				int port = (int) (long) params.get("port");
				socketServerController.start(ip, port);
			}
		}
	}

	public void stop() {
		socketServerController.stop();
	}

	public int createHost() throws NetworkException {
		String response = httpClientController.regHost();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) json.fromJSON(response);
		if (map.containsKey(Field.ERROR)) {
			if (map.get(Field.ERROR) != null) {
				throw new NetworkException((String) map.get("error"));
			} else {
				int key = Integer.parseInt((String)map.get(Field.KEY));
				@SuppressWarnings("unused")
				String ip = (String)map.get(Field.IP);
				return key;
			}
		}
		return 0;
	}

	public HttpClientController getHttpClientController() {
		return httpClientController;
	}

	public void setHttpClientController(
			HttpClientController httpClientController) {
		this.httpClientController = httpClientController;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public SocketServerController getSocketServerController() {
		return socketServerController;
	}

	public void setSocketServerController(
			SocketServerController socketServerController) {
		this.socketServerController = socketServerController;
	}
}
