package vn.edu.hust.student.dynamicpool.dal.config;

public class ServerNetworkConfig {
	private String host = "0.0.0.0";
	private int port;
	private String path = "/";

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
	
	@Override
	public String toString() {
		return this.host + ":" + this.port + (this.path != null ? this.path : "");
	}
}
