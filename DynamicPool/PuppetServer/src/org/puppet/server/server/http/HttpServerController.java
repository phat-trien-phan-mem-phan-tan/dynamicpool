package org.puppet.server.server.http;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.mina.core.session.IoSession;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.ajax.JSON;
import org.puppet.server.config.HttpServerConfig;
import org.puppet.server.processor.Processor;
import org.puppet.server.processor.ProcessorExecutionRequest;
import org.puppet.server.processor.ProcessorExecutionResponse;
import org.puppet.server.processor.ProcessorManager;
import org.puppet.server.server.AbstractServerController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpServerController extends AbstractServerController implements
		HttpHandler {

	private static final Logger logger = LoggerFactory
			.getLogger(HttpServerController.class);
	private HttpServerConfig config;
	
	private Server httpServer;

	public HttpServerController(HttpServerConfig config) {
		this.setConfig(config);
		httpServer = new Server(this.config.getNetworkConfigs().get(0)
				.getPort());
		ServletContextHandler context = new ServletContextHandler(
				ServletContextHandler.SESSIONS);
		httpServer.setHandler(context);
		context.addServlet(new ServletHolder(new SimpleServlet(this)),
				this.config.getNetworkConfigs().get(0).getPath());
		this.processorManager = new ProcessorManager();
	}

	@Override
	public void start() {
		try {
			httpServer.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private ProcessorExecutionRequest buildProcessorExecutionData(
			final Map<String, Object> parameters, final HttpServletResponse res) {
		return new ProcessorExecutionRequest() {
			@Override
			public Map<String, Object> getParameters() {
				return parameters;
			}

			@Override
			public void write(Object data) {
				try {
					if (data != null) {
						if (data instanceof String) {
							res.getWriter().write((String) data);
						} else {
							JSON json = new JSON();
							res.getWriter().write(json.toJSON(data));
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			@Override
			public IoSession getSession() {
				return null;
			}

			@Override
			public void callback(Object... data) {

			}
		};
	}

	private Object _onRequest(String command,
			final Map<String, String[]> parameterMap, HttpServletResponse res) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		for (String key : parameterMap.keySet()) {
			String[] value = parameterMap.get(key);
			if (value != null) {
				parameters.put(key,
						value.length == 1 ? value[0] : Arrays.asList(value));
			}
		}
		if (command != null) {
			try {
				ProcessorExecutionResponse response = this.processorManager
						.processCommand(command, this
								.buildProcessorExecutionData(parameters, res));
				if (response != null) {
					return response.getData();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public void onGet(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String uri = req.getRequestURI().trim();
			System.out.println("uri: " + uri);
			logger.info("data receive " + req.getParameterMap().toString());
			String[] arr = uri.split("/");
			String command = null;
			for (String str : arr) {
				if (str.trim().length() > 0) {
					command = str;
					break;
				}
			}

			if (command != null && !command.toLowerCase().equals("favicon.ico")) {
				Object result = this._onRequest(command, req.getParameterMap(),
						resp);
				if (result != null) {
					if (result instanceof String) {
						resp.getWriter().write((String) result);
					} else {
						JSON json = new JSON();
						resp.getWriter().write(json.toJSON(result));
					}
				}
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onPost(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String uri = req.getRequestURI().trim();
			System.out.println("uri: " + uri);
			logger.info("data receive on post " + req.getParameterMap().toString());
			String[] arr = uri.split("/");
			String command = null;
			for (String str : arr) {
				if (str.trim().length() > 0) {
					command = str;
					break;
				}
			}

			if (command != null && !command.toLowerCase().equals("favicon.ico")) {
				Object result = this._onRequest(command, req.getParameterMap(),
						resp);
				if (result != null) {
					if (result instanceof String) {
						resp.getWriter().write((String) result);
					} else {
						JSON json = new JSON();
						resp.getWriter().write(json.toJSON(result));
					}
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public HttpServerConfig getConfig() {
		return config;
	}

	public void setConfig(HttpServerConfig config) {
		this.config = config;
	}

	@Override
	public void initProcessor(Map<String, Class<? extends Processor>> map) {
		List<String> handlers = this.config.getHandlers();
		if (handlers != null && handlers.size() > 0) {
			for (String command : handlers) {
				if (map.containsKey(command)) {
					Class<? extends Processor> clazz = map.get(command);
					try {
						Constructor<?> ctor = clazz.getConstructor();
						Processor processor = (Processor) ctor.newInstance();
						this.processorManager.register(command, processor);
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public void stop() {
		try {
			logger.info("stopping http server...");
			this.httpServer.stop();
		} catch (Exception e) {
			logger.error("cannot stop http server: ", e);
		}
	}
}
