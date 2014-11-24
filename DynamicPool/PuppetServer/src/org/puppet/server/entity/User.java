package org.puppet.server.entity;

import org.apache.mina.core.session.IoSession;

public class User {
	private String userName;
	private IoSession session;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public IoSession getSession() {
		return session;
	}
	public void setSession(IoSession session) {
		this.session = session;
	}
}
