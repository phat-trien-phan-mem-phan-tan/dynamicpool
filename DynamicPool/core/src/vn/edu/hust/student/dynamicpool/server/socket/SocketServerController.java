package vn.edu.hust.student.dynamicpool.server.socket;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;


import vn.edu.hust.student.dynamicpool.client.socket.SocketClientController;
import vn.edu.hust.student.dynamicpool.client.socket.SocketClientHandler;
import vn.edu.hust.student.dynamicpool.config.SocketClientConfig;
import vn.edu.hust.student.dynamicpool.processor.Processor;
import vn.edu.hust.student.dynamicpool.server.AbstractServerController;

public class SocketServerController extends AbstractServerController implements
		SocketClientHandler {

	private SocketClientConfig config;
	private SocketClientController socketClientController;

	public SocketServerController(SocketClientConfig config) {
		setConfig(config);
		socketClientController = new SocketClientController(this);
	}

	@Override
	public void messageReceive(String message) {
		System.out.println(message);
	}

	public SocketClientConfig getConfig() {
		return config;
	}

	public void setConfig(SocketClientConfig config) {
		this.config = config;
	}

	@Override
	public void initProcessor(Map<String, Class<? extends Processor>> map) {
		// TODO Auto-generated method stub
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

	@Override
	public void start(String ip, int port) {
		socketClientController.start(ip, port);
	}

	public void sendMessage(Object data) {
		this.socketClientController.send(data);
	}

	public void stop() {
		// TODO Auto-generated method stub

	}
}
