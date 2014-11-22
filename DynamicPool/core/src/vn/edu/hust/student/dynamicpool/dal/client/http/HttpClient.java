package vn.edu.hust.student.dynamicpool.dal.client.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClient implements HttpHandler {
	private Logger logger = LoggerFactory.getLogger(HttpClient.class);

	@Override
	public Object sendGet(String url, Map<String, Object> parameter)
			throws IOException {
		// TODO Auto-generated method stub
		URL obj;

		StringBuilder params = new StringBuilder();
		for (String key : parameter.keySet()) {
			params.append(key);
			params.append('=');
			params.append(parameter.get(key));
			params.append('&');
		}
		params.replace(params.length() - 1, params.length(), "");
		if (parameter.size() > 0) {
			url += "?" + params;
		}
		obj = new URL(url);

		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		int responseCode = con.getResponseCode();
		logger.debug("Sending 'GET' request to URL : " + url);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}

		in.close();
		logger.debug("HTTP RESPONSE: {} - {}", responseCode,
				response.toString());
		return response.toString();

	}

	@Override
	public Object sendPost(String url, Map<String, Object> parameter)
			throws IOException {
		// TODO Auto-generated method stub
		URL obj;

		StringBuilder params = new StringBuilder();
		for (String key : parameter.keySet()) {
			params.append(key);
			params.append('=');
			params.append(parameter.get(key));
			params.append('&');
		}
		params.replace(params.length() - 1, params.length(), "");
		if (parameter.size() > 0) {
			url += "?" + params;
		}
		obj = new URL(url);

		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		int responseCode = con.getResponseCode();
		logger.debug("Sending 'POST' request to URL : " + url);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		logger.debug("HTTP RESPONSE: {} - {}", responseCode,
				response.toString());
		return response.toString();

	}
}
