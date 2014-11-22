package vn.edu.hust.student.dynamicpool.dal.server.socket;

import org.apache.mina.core.session.IoSession;

import vn.edu.hust.student.dynamicpool.dal.server.AbstractServerController;

public abstract class SocketServerController extends AbstractServerController {
	public SocketServerController() {
		super();
	}

	public abstract void sendMessage(IoSession session, Object data);

	public abstract void stop();
}
