package vn.edu.hust.student.dynamicpool.dal.client.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientController {
	private final Logger logger = LoggerFactory
			.getLogger(HttpClientController.class);

	private HttpHandler httpHandler;
	private int socketPort;

	public HttpClientController() {
		setHttpHandler(new HttpClient());
	}

	public HttpHandler getHttpHandler() {
		return httpHandler;
	}

	public void setHttpHandler(HttpHandler httpHandler) {
		this.httpHandler = httpHandler;
	}

	public String regHost() throws MalformedURLException, IOException {
		String url = "http://104.131.13.155:8990/dp_reghost";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("iplan", getIpLan());
		parameters.put("port", getSocketPort());
		String response = (String) this.httpHandler.sendGet(url, parameters);
		return response;
	}

	public String authentication(String key) throws MalformedURLException,
			IOException {
		String url = "http://104.131.13.155:8990/dp_auth";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("key", key);
		String response = (String) this.httpHandler.sendGet(url, parameters);
		return response;
	}

	public int getSocketPort() {
		return socketPort;
	}

	public void setSocketPort(int socketPort) {
		this.socketPort = socketPort;
	}

	public String getIpLan() throws UnknownHostException {
		String ip = InetAddress.getLocalHost().getHostAddress();
		return ip;
	}
}
