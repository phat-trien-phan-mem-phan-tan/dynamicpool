package org.puppet.server.server.http;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleServlet extends HttpServlet {

	private static final long serialVersionUID = 6528767649740722981L;
	private static final Logger log = LoggerFactory.getLogger(SimpleServlet.class);

	private HttpHandler httpHandler;

	public SimpleServlet(HttpHandler httpService) {
		this.httpHandler = httpService;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("GET: {} from {}", req.getRequestURI(), req.getRemoteAddr());
		this.httpHandler.onGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("POST: {} from {}", req.getRequestURI(), req.getRemoteAddr());
		this.httpHandler.onPost(req, resp);
	}
}
