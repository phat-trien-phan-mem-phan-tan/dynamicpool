package vn.edu.hust.student.dynamicpool.dal.client.entity;

import org.apache.mina.core.session.IoSession;

import flexjson.JSONSerializer;

import vn.edu.hust.student.dynamicpool.dal.server.logic.PoolServer;

public class Client {
	private PoolServer pool;
	private String clientName;
	private IoSession session;

	public PoolServer getPool() {
		return pool;
	}

	public void setPool(PoolServer pool) {
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
			String message = serializer.exclude("*.class").serialize(data);
			this.getSession().write(message);
		}
	}
}
