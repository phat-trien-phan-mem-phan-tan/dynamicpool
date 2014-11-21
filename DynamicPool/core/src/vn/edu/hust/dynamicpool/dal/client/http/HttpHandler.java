package vn.edu.hust.dynamicpool.dal.client.http;

import java.util.Map;

public interface HttpHandler {
	Object sendGet(String url, Map<String, Object> parameters);
	Object sendPost(String url, Map<String, Object> parameter);
}
