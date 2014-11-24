package org.puppet.server.server.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HttpHandler {
	public void onGet(HttpServletRequest req, HttpServletResponse resp);
	public void onPost(HttpServletRequest req, HttpServletResponse resp);
}
