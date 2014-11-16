package vn.edu.hust.student.controller;

import java.util.Map;

import org.eclipse.jetty.util.ajax.JSON;
import org.puppet.client.http.HttpClientController;
import org.puppet.client.socket.SocketClientController;

import vn.edu.hust.student.dynamicpool.exception.NetworkException;
import vn.edu.hust.student.dynamicpool.server.socket.SocketServerController;
import vn.edu.hust.student.dynamicpool.statics.Field;

public class MainController {
	private HttpClientController httpClientController;
	private SocketClientController socketClientController;
	private JSON json;

	public MainController() {
		httpClientController = new HttpClientController();
		socketClientController = new SocketClientController(
				new SocketServerController());
		json = new JSON();
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
				socketClientController.start(ip, port);
			}
		}
	}
	
	public void stop(){
		socketClientController.stop();
	}

	public HttpClientController getHttpClientController() {
		return httpClientController;
	}

	public void setHttpClientController(
			HttpClientController httpClientController) {
		this.httpClientController = httpClientController;
	}

	public SocketClientController getSocketClientController() {
		return socketClientController;
	}

	public void setSocketClientController(
			SocketClientController socketClientController) {
		this.socketClientController = socketClientController;
	}
}
