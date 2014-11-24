package org.puppet.server.controller;

import java.util.Map;

import org.puppet.client.http.HttpClientController;
import org.puppet.client.socket.SocketClientController;
import org.puppet.server.bussinesslayer.PoolManager;
import org.puppet.server.manager.RoomManager;
import org.puppet.server.manager.UserManager;
import org.puppet.server.processor.Processor;
import org.puppet.server.server.http.HttpServerController;
import org.puppet.server.server.socket.NIOSocketServerController;
import org.puppet.server.server.socket.SocketServerController;
import org.puppet.server.utils.xml.ServerXMLConfigReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainController {
	private static MainController _instance;

	public static MainController getInstance() {
		if (_instance == null) {
			_instance = new MainController();
		}
		return _instance;
	}

	private SocketServerController socketController;
	private HttpServerController httpController;
	private UserManager userManager;
	private RoomManager roomManager;
	private HttpClientController httpClientController;
	private SocketClientController socketClientController;
	private PoolManager poolManager;

	private Logger logger = LoggerFactory.getLogger(MainController.class);

	private MainController() {
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
			setHttpController(new HttpServerController(
					configReader.getHttpServerConfig()));

			Map<String, Class<? extends Processor>> processorMap = configReader
					.getProcessorMap();
			this.getHttpController().initProcessor(processorMap);
			this.getSocketController().initProcessor(processorMap);

			httpClientController = new HttpClientController();
			httpClientController.setSocketPort(configReader
					.getSocketServerConfig().getNetworkConfigs().get(0)
					.getPort());
		} catch (Exception e) {
			getLogger().error("Cannot start Main controller: ", e);
			e.printStackTrace();
		}

		userManager = new UserManager();
		roomManager = new RoomManager();
		socketClientController = new SocketClientController();
		setPoolManager(new PoolManager());
	}

	public void start() {
		getHttpController().start();
		getSocketController().start();
	}

	public SocketServerController getSocketController() {
		return socketController;
	}

	public void setSocketController(SocketServerController socketController) {
		this.socketController = socketController;
	}

	public HttpServerController getHttpController() {
		return httpController;
	}

	public void setHttpController(HttpServerController httpController) {
		this.httpController = httpController;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public void stop() {
		this.socketController.stop();
		this.httpController.stop();
	}

	public RoomManager getRoomManager() {
		return roomManager;
	}

	public void setRoomManager(RoomManager roomManager) {
		this.roomManager = roomManager;
	}

	public HttpClientController getHttpClientController() {
		return httpClientController;
	}

	public void setHttpClientController(
			HttpClientController httpClientController) {
		this.httpClientController = httpClientController;
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
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
}
