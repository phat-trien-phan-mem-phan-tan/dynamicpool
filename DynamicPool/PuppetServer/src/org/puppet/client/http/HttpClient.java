package org.puppet.client.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpClient implements HttpHandler {

	@Override
	public Object sendGet(String url, Map<String, Object> parameter) {
		// TODO Auto-generated method stub
		URL obj;
		try {
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
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();
			return response.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object sendPost(String url, Map<String, Object> parameter) {
		// TODO Auto-generated method stub
		URL obj;
		try {
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
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			return response.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
