package org.puppet.server.config;

public class MySQLConfig {
	private String url = null;
	private String user = null;
	private String password = null;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String userName) {
		this.user = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
