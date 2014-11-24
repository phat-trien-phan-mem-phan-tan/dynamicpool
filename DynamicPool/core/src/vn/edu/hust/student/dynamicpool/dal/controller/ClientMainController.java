package vn.edu.hust.student.dynamicpool.dal.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jetty.util.ajax.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eposi.eventdriven.Event;
import com.eposi.eventdriven.exceptions.InvalidHandlerMethod;
import com.eposi.eventdriven.exceptions.NoContextToExecute;
import com.eposi.eventdriven.implementors.BaseEventDispatcher;

import vn.edu.hust.student.dynamicpool.dal.client.http.HttpClientController;
import vn.edu.hust.student.dynamicpool.dal.processor.Processor;
import vn.edu.hust.student.dynamicpool.dal.server.socket.ClientSocketController;
import vn.edu.hust.student.dynamicpool.dal.statics.Field;
import vn.edu.hust.student.dynamicpool.dal.utils.xml.ServerXMLConfigReader;
import vn.edu.hust.student.dynamicpool.exception.DALException;

public class ClientMainController {
	private static ClientMainController _instance;

	private HttpClientController httpClientController;
	private ClientSocketController clientSocketController;
	private JSON json;
	private Logger logger = LoggerFactory.getLogger(ClientMainController.class);
	private List<BaseEventDispatcher> dispatchers;
	
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
		dispatchers = new ArrayList<BaseEventDispatcher>();
	}

	public static ClientMainController getInstance() {
		if (_instance == null)
			_instance = new ClientMainController();
		return _instance;
	}

	@SuppressWarnings("unchecked")
	public void start(String key) throws DALException {
		String response;
		try {
			response = httpClientController.authentication(key);
			Map<String, Object> params = (Map<String, Object>) json
					.fromJSON(response);
			if (params.containsKey(Field.ERROR)) {
				if (params.get(Field.ERROR) != null) {
					throw new DALException(
							(String) params.get(Field.ERROR), null);
				} else {
					String ip = (String) params.get("ip");
					int port = Integer.parseInt(params.get("port").toString());
					this.getClientSocketController().start(ip, port);
				}
			}
		} catch (NumberFormatException e) {
			throw new DALException("port is not integer", e);
		} catch (MalformedURLException e) {
			throw new DALException("URL invalide", e);
		} catch (IOException e) {
			throw new DALException("cannot connect to server", e);
		}
	}

	public void stop() {
		this.getClientSocketController().stop();
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
	
	public void addDispatcher(BaseEventDispatcher target) {
		this.dispatchers.add(target);
	}

	public List<BaseEventDispatcher> getDispatchers() {
		return dispatchers;
	}

	public void setDispatchers(List<BaseEventDispatcher> dispatchers) {
		this.dispatchers = dispatchers;
	}
	
	public void dispatchAll(Event e){
		for (BaseEventDispatcher dispatcher : this.dispatchers) {
			try {
				dispatcher.dispatchEvent(e);
			} catch (InvocationTargetException | IllegalAccessException
					| NoSuchMethodException | InvalidHandlerMethod
					| NoContextToExecute e1) {
				logger.debug("Cannot dispatch event");
			}
		}
	}
}
