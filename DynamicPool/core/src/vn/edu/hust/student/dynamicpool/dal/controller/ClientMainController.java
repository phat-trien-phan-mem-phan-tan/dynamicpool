package vn.edu.hust.student.dynamicpool.dal.controller;

import java.util.Map;

import org.eclipse.jetty.util.ajax.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vn.edu.hust.student.dynamicpool.dal.client.http.HttpClientController;
import vn.edu.hust.student.dynamicpool.dal.processor.Processor;
import vn.edu.hust.student.dynamicpool.dal.server.socket.ClientSocketController;
import vn.edu.hust.student.dynamicpool.dal.statics.Field;
import vn.edu.hust.student.dynamicpool.dal.utils.xml.ServerXMLConfigReader;
import vn.edu.hust.student.dynamicpool.exception.NetworkException;

public class ClientMainController {
	private static ClientMainController _instance;

	private HttpClientController httpClientController;
	private ClientSocketController clientSocketController;
	private JSON json;
	private Logger logger = LoggerFactory.getLogger(ClientMainController.class);

	private ClientMainController() {

		getLogger().info("Reading config file from path conf/client.xml");
		ServerXMLConfigReader configReader;
		try {
			configReader = new ServerXMLConfigReader("conf/client.xml");
			clientSocketController = new ClientSocketController(
					configReader.getSocketServerConfig());
			Map<String, Class<? extends Processor>> processorMap = configReader
					.getProcessorMap();
			this.getClientSocketController().initProcessor(processorMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		httpClientController = new HttpClientController();
		json = new JSON();
	}

	public static ClientMainController getInstance() {
		if (_instance == null)
			_instance = new ClientMainController();
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
				this.getClientSocketController().start(ip, port);
			}
		}
	}

	public void stop() {
		this.getClientSocketController().stop();
	}

	public int createHost() throws NetworkException {
		String response = httpClientController.regHost();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) json.fromJSON(response);
		if (map.containsKey(Field.ERROR)) {
			if (map.get(Field.ERROR) != null) {
				throw new NetworkException((String) map.get("error"));
			} else {
				int key = (int)(long) map.get(Field.KEY);
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

	public ClientSocketController getClientSocketController() {
		return clientSocketController;
	}

	public void setClientSocketController(ClientSocketController clientSocketController) {
		this.clientSocketController = clientSocketController;
	}
}
