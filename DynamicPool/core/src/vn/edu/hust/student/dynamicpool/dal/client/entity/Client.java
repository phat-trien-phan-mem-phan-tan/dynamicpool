package vn.edu.hust.student.dynamicpool.dal.client.entity;

import org.apache.mina.core.session.IoSession;

import vn.edu.hust.student.dynamicpool.bll.model.Pool;
import flexjson.JSONSerializer;

public class Client {
	private Pool pool;
	private String clientName;
	private IoSession session;

	public Pool getPool() {
		return pool;
	}

	public void setPool(Pool pool) {
		this.pool = pool;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public IoSession getSession() {
		return session;
	}

	public void setSession(IoSession session) {
		this.session = session;
	}

	public void send(Object data) {
		if (data instanceof String) {
			this.getSession().write(data);
		} else {
			JSONSerializer serializer = new JSONSerializer();
			String message = serializer.exclude("*.class").deepSerialize(data);
			this.getSession().write(message);
		}
	}
}
