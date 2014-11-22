package vn.edu.hust.student.dynamicpool.dal.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.eclipse.jetty.util.ajax.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eposi.eventdriven.Event;
import com.eposi.eventdriven.exceptions.InvalidHandlerMethod;
import com.eposi.eventdriven.exceptions.NoContextToExecute;
import com.eposi.eventdriven.implementors.BaseEventDispatcher;

import vn.edu.hust.student.dynamicpool.dal.client.http.HttpClientController;
import vn.edu.hust.student.dynamicpool.dal.client.socket.SocketClientController;
import vn.edu.hust.student.dynamicpool.dal.manager.ClientManager;
import vn.edu.hust.student.dynamicpool.dal.processor.Processor;
import vn.edu.hust.student.dynamicpool.dal.server.logic.PoolManager;
import vn.edu.hust.student.dynamicpool.dal.server.socket.NIOSocketServerController;
import vn.edu.hust.student.dynamicpool.dal.server.socket.SocketServerController;
import vn.edu.hust.student.dynamicpool.dal.statics.Field;
import vn.edu.hust.student.dynamicpool.dal.utils.xml.ServerXMLConfigReader;
import vn.edu.hust.student.dynamicpool.exception.DALException;

public class HostMainController {
	private static HostMainController _instance;

	public static HostMainController getInstance() {
		if (_instance == null) {
			_instance = new HostMainController();
		}
		return _instance;
	}

	private SocketServerController socketController;
	private HttpClientController httpClientController;
	private SocketClientController socketClientController;
	private PoolManager poolManager;
	private ClientManager clientManager;
	private List<BaseEventDispatcher> dispatchers;

	private Logger logger = LoggerFactory.getLogger(HostMainController.class);

	private HostMainController() {
		if (_instance != null) {
			throw new IllegalAccessError(
					"MainController is singleton Class, use MainController.getInstance() instead");
		}
		try {
			loadLog4j();
			getLogger().info("Reading config file from path conf/server.xml");
			ServerXMLConfigReader configReader = new ServerXMLConfigReader(
					"conf/server.xml");

			setSocketController(new NIOSocketServerController(
					configReader.getSocketServerConfig()));

			Map<String, Class<? extends Processor>> processorMap = configReader
					.getProcessorMap();
			this.getSocketController().initProcessor(processorMap);

			httpClientController = new HttpClientController();
			httpClientController.setSocketPort(configReader
					.getSocketServerConfig().getNetworkConfigs().get(0)
					.getPort());
		} catch (Exception e) {
			getLogger().error("Cannot start Main controller: ", e);
			e.printStackTrace();
		}

		socketClientController = new SocketClientController();
		setPoolManager(new PoolManager());
		setClientManager(new ClientManager());
		setDispatchers(new ArrayList<BaseEventDispatcher>());
	}

	public SocketServerController getSocketController() {
		return socketController;
	}

	public void setSocketController(SocketServerController socketController) {
		this.socketController = socketController;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public void stop() {
		this.socketController.stop();
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

	public PoolManager getPoolManager() {
		return poolManager;
	}

	public void setPoolManager(PoolManager poolManager) {
		this.poolManager = poolManager;
	}

	public ClientManager getClientManager() {
		return clientManager;
	}

	public void setClientManager(ClientManager clientManager) {
		this.clientManager = clientManager;
	}

	public void start() {
		logger.debug("Starting Puppet Server.........");
		this.getSocketController().start();
		logger.debug("Puppet Server Started Successfully");
	}

	public void loadLog4j() {
		String log4JPropertyFile = "conf/log4j.properties";
		Properties p = new Properties();

		try {
			p.load(new FileInputStream(log4JPropertyFile));
			PropertyConfigurator.configure(p);
		} catch (IOException e) {
			System.out.println("Opps, cannot load log4j.properties");
		}
	}

	@SuppressWarnings("unchecked")
	public String connectServer() throws DALException {
		String res;
		try {
			res = this.httpClientController.regHost();
			JSON json = new JSON();
			Map<String, Object> hostInfo = (Map<String, Object>) json
					.fromJSON(res);
			if (hostInfo.get(Field.ERROR) == null) {
				String key = (String) hostInfo.get("key");
				this.socketClientController.start("104.131.13.155", 2225);
				return key;
			} else {
				throw new DALException((String) hostInfo.get("error"), null);
			}
		} catch (MalformedURLException e) {
			throw new DALException("URL không hợp lệ", e);
		} catch (IOException e) {
			throw new DALException("Không kết nối được đến server", e);
		}
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
