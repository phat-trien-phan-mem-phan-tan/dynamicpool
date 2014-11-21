package vn.edu.hust.student.dynamicpool.dal.server.socket;

import java.util.Map;

import org.apache.mina.core.session.IoSession;

import vn.edu.hust.student.dynamicpool.dal.processor.Processor;

public class SocketClientControlller extends SocketServerController {

	@Override
	public void sendMessage(IoSession session, Object data) {
		
	}

	@Override
	public void stop() {
		
	}

	@Override
	public void initProcessor(Map<String, Class<? extends Processor>> map) {
		
	}

	@Override
	public void start() {
		
	}

}
