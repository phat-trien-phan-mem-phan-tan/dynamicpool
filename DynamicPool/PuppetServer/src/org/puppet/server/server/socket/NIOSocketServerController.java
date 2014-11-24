package org.puppet.server.server.socket;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.eclipse.jetty.util.ajax.JSON;
import org.puppet.server.config.SocketServerConfig;
import org.puppet.server.controller.MainController;
import org.puppet.server.entity.User;
import org.puppet.server.processor.Processor;
import org.puppet.server.processor.ProcessorExecutionRequest;
import org.puppet.server.processor.ProcessorExecutionResponse;
import org.puppet.server.processor.ProcessorManager;
import org.puppet.server.statics.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NIOSocketServerController extends SocketServerController implements
		IoHandler {
	private static final Logger logger = LoggerFactory
			.getLogger(NIOSocketServerController.class);

	private SocketServerConfig config;
	private IoAcceptor acceptor;
	private JSON json;

	public NIOSocketServerController(SocketServerConfig config) {
		this.setConfig(config);
		this.acceptor = new NioSocketAcceptor();

		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		acceptor.getFilterChain().addLast(
				"codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset
						.forName("UTF-8"))));

		acceptor.setHandler(this);

		acceptor.getSessionConfig().setReadBufferSize(2048);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);

		json = new JSON();
		this.processorManager = new ProcessorManager();
	}

	@Override
	public void start() {
		try {
			this.acceptor.bind(new InetSocketAddress(this.getConfig()
					.getNetworkConfigs().get(0).getPort()));
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	@Override
	public void exceptionCaught(IoSession arg0, Throwable arg1)
			throws Exception {
		// test
	}

	@Override
	@SuppressWarnings("unchecked")
	public void messageReceived(final IoSession session, Object message)
			throws Exception {
		logger.debug("data recieved: " + message);
		final Map<String, Object> params = (Map<String, Object>) json
				.fromJSON((String) message);
		User user = MainController.getInstance().getRoomManager()
				.findUser(session.getId());
		if (user != null) {
			params.put("user", user);
		}
		if (params.containsKey(Field.COMMAND)) {
			String command = (String) params.get(Field.COMMAND);
			ProcessorExecutionResponse result = this.processorManager
					.processCommand(command, new ProcessorExecutionRequest() {
						@Override
						public Map<String, Object> getParameters() {
							return params;
						}

						@Override
						public void write(Object data) {
							session.write(data instanceof String ? data : json
									.toJSON(data));
						}

						@Override
						public IoSession getSession() {
							return session;
						}

						@Override
						public void callback(Object... data) {

						}

					});

			if (result != null) {
				session.write(json.toJSON(result.getData()));
			}
		}
	}

	@Override
	public void messageSent(IoSession arg0, Object arg1) throws Exception {

	}

	@Override
	public void sessionClosed(IoSession arg0) {
	}

	@Override
	public void sessionCreated(IoSession arg0) throws Exception {

	}

	@Override
	public void sessionIdle(IoSession arg0, IdleStatus arg1) throws Exception {

	}

	@Override
	public void sessionOpened(IoSession arg0) throws Exception {

	}

	public SocketServerConfig getConfig() {
		return config;
	}

	public void setConfig(SocketServerConfig config) {
		this.config = config;
	}

	@Override
	protected void finalize() throws Throwable {
		this.acceptor.unbind();
	}

	@Override
	public void sendMessage(IoSession target, Object data) {
		try {
			target.write(data);
		} catch (Exception e) {
			logger.error("fuck");
		}
	}

	@Override
	public void stop() {
		logger.info("stopping socket server");
		this.acceptor.unbind();
	}
}
