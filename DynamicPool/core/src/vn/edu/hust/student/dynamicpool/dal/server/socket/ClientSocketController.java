package vn.edu.hust.student.dynamicpool.dal.server.socket;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.mina.core.session.IoSession;
import org.eclipse.jetty.util.ajax.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vn.edu.hust.student.dynamicpool.dal.client.socket.SocketClientController;
import vn.edu.hust.student.dynamicpool.dal.client.socket.SocketClientHandler;
import vn.edu.hust.student.dynamicpool.dal.config.SocketServerConfig;
import vn.edu.hust.student.dynamicpool.dal.processor.Processor;
import vn.edu.hust.student.dynamicpool.dal.processor.ProcessorExecutionRequest;
import vn.edu.hust.student.dynamicpool.dal.processor.ProcessorExecutionResponse;
import vn.edu.hust.student.dynamicpool.dal.processor.ProcessorManager;
import vn.edu.hust.student.dynamicpool.dal.statics.Field;
import vn.edu.hust.student.dynamicpool.events.EventDestination;
import vn.edu.hust.student.dynamicpool.events.EventType;
import flexjson.JSONSerializer;

public class ClientSocketController implements SocketClientHandler {
	private final Logger logger = LoggerFactory
			.getLogger(ClientSocketController.class);

	private SocketClientController socketClientController;
	private SocketServerConfig config;
	private ProcessorManager processorManager;
	private JSONSerializer jsonSerializer;
	private JSON json;

	public ClientSocketController(SocketServerConfig config) {
		socketClientController = new SocketClientController(this);
		processorManager = new ProcessorManager();
		this.config = config;
		jsonSerializer = new JSONSerializer();
		json = new JSON();
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
						if (processor != null) {
							this.getProcessorManager().register(command,
									processor);
						} else {
							logger.debug("processor {} is null", command);
						}
					} catch (NoSuchMethodException e) {
						logger.debug(e.getMessage());
						e.printStackTrace();
					} catch (SecurityException e) {
						logger.debug(e.getMessage());
						e.printStackTrace();
					} catch (InstantiationException e) {
						logger.debug(e.getMessage());
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						logger.debug(e.getMessage());
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						logger.debug(e.getMessage());
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						logger.debug(e.getMessage());
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
		logger.info("MESSAGE RECEIVE: {}", s);
		@SuppressWarnings("unchecked")
		final Map<String, Object> params = (Map<String, Object>) json
				.fromJSON((String) s);

		if (params.containsKey(Field.COMMAND)) {
			String command = (String) params.get(Field.COMMAND);
			ProcessorExecutionResponse result;
			try {
				result = this.processorManager.processCommand(command,
						new ProcessorExecutionRequest() {
							@Override
							public Map<String, Object> getParameters() {
								return params;
							}

							@Override
							public void write(Object data) {

							}

							@Override
							public IoSession getSession() {
								return null;
							}

							@Override
							public void callback(Object... data) {

							}

						});

				if (result != null) {
					sendMessage(json.toJSON(result.getData()));
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public SocketClientController getSocketClientController() {
		return socketClientController;
	}

	public void setSocketClientController(
			SocketClientController socketClientController) {
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

	@Override
	public void disconnect() {
		logger.debug("dispatch event {}", EventType.DAL_DISCONNECT_FROM_SERVER);
		EventDestination.getInstance().dispatchSuccessEvent(
				EventType.DAL_DISCONNECT_FROM_SERVER);
	}

}
