package vn.edu.hust.student.dynamicpool.dal.client.http;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

public interface HttpHandler {
	Object sendGet(String url, Map<String, Object> parameters) throws MalformedURLException, IOException;
	Object sendPost(String url, Map<String, Object> parameter) throws MalformedURLException, IOException;
}
