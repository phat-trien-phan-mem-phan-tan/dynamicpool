package vn.edu.hust.student.dynamicpool.dal.server.socket;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import flexjson.JSONSerializer;

import vn.edu.hust.student.dynamicpool.dal.client.socket.SocketClientController;
import vn.edu.hust.student.dynamicpool.dal.client.socket.SocketClientHandler;
import vn.edu.hust.student.dynamicpool.dal.config.SocketServerConfig;
import vn.edu.hust.student.dynamicpool.dal.processor.Processor;
import vn.edu.hust.student.dynamicpool.dal.processor.ProcessorManager;

public class ClientSocketController implements SocketClientHandler {
	private SocketClientController socketClientController;
	private SocketServerConfig config;
	private ProcessorManager processorManager;
	private JSONSerializer jsonSerializer;
	
	public ClientSocketController(SocketServerConfig config){
		socketClientController = new SocketClientController(this);
		this.config = config;
		jsonSerializer = new JSONSerializer();
	}
	
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
						this.getProcessorManager().register(command, processor);
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

	public void start(String ip, int port) {
		socketClientController.start(ip, port);
	}

	public void sendMessage(Object data) {
		String message = jsonSerializer.exclude("*.class").serialize(data);
		this.socketClientController.send(message);
	}
	
	@Override
	public void messageReceive(String s) {
	
	}


	public SocketClientController getSocketClientController() {
		return socketClientController;
	}


	public void setSocketClientController(SocketClientController socketClientController) {
		this.socketClientController = socketClientController;
	}

	public ProcessorManager getProcessorManager() {
		return processorManager;
	}

	public void setProcessorManager(ProcessorManager processorManager) {
		this.processorManager = processorManager;
	}

	public void stop() {
		this.socketClientController.stop();
	}
	
}
