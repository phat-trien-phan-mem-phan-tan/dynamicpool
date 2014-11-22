package vn.edu.hust.student.dynamicpool.dal.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
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

	@SuppressWarnings("unchecked")
	public void start(int key) throws NetworkException {
		String response;
		try {
			response = httpClientController.authentication(key);
			Map<String, Object> params = (Map<String, Object>) json
					.fromJSON(response);
			if (params.containsKey(Field.ERROR)) {
				if (params.get(Field.ERROR) != null) {
					throw new NetworkException(
							(String) params.get(Field.ERROR), null);
				} else {
					String ip = (String) params.get("ip");
					int port = (int) (long) params.get("port");
					this.getClientSocketController().start(ip, port);
				}
			}
		} catch (MalformedURLException e) {
			throw new NetworkException("URL không hợp lê", e);
		} catch (IOException e) {
			throw new NetworkException("không kết được đến server", e);
		}
	}

	public void stop() {
		this.getClientSocketController().stop();
	}

	@SuppressWarnings("unchecked")
	public int createHost() throws NetworkException {
		String response;
		try {
			response = httpClientController.regHost();
			Map<String, Object> map = (Map<String, Object>) json
					.fromJSON(response);
			if (map.containsKey(Field.ERROR)) {
				if (map.get(Field.ERROR) != null) {
					throw new NetworkException((String) map.get("error"), null);
				} else {
					int key = (int) (long) map.get(Field.KEY);
					String ip = (String) map.get(Field.IP);
					return key;
				}
			} else {
				throw new NetworkException("message receive not contain error",
						null);
			}
		} catch (MalformedURLException e) {
			throw new NetworkException("URL invalid", e);
		} catch (UnknownHostException e) {
			throw new NetworkException("IPv4 not found", e);
		} catch (IOException e) {
			throw new NetworkException("IO connect to server", e);
		}
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

	public void setClientSocketController(
			ClientSocketController clientSocketController) {
		this.clientSocketController = clientSocketController;
	}
}
