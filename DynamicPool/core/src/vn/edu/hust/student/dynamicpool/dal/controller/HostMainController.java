package vn.edu.hust.student.dynamicpool.dal.controller;

import java.util.Map;

import org.puppet.client.http.HttpClientController;
import org.puppet.client.socket.SocketClientController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vn.edu.hust.student.dynamicpool.dal.processor.Processor;
import vn.edu.hust.student.dynamicpool.dal.server.logic.PoolManager;
import vn.edu.hust.student.dynamicpool.dal.server.socket.NIOSocketServerController;
import vn.edu.hust.student.dynamicpool.dal.server.socket.SocketServerController;
import vn.edu.hust.student.dynamicpool.dal.utils.xml.ServerXMLConfigReader;

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

	private Logger logger = LoggerFactory.getLogger(HostMainController.class);

	private HostMainController() {
		if (_instance != null) {
			throw new IllegalAccessError(
					"MainController is singleton Class, use MainController.getInstance() instead");
		}
		try {
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

	public void setSocketClientController(SocketClientController socketClientController) {
		this.socketClientController = socketClientController;
	}

	public PoolManager getPoolManager() {
		return poolManager;
	}

	public void setPoolManager(PoolManager poolManager) {
		this.poolManager = poolManager;
	}

	public void start() {
		this.getSocketController().start();
	}
}
