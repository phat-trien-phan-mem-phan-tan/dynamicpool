package vn.edu.hust.student.dynamicpool.dal.config;

import java.util.ArrayList;
import java.util.List;

public class SocketServerConfig {
	private List<ServerNetworkConfig> networkConfigs;
	private List<String> handlers;
	
	public SocketServerConfig() {
		setNetworkConfigs(new ArrayList<ServerNetworkConfig>());
	}
	
	public void addNetworkConfig(ServerNetworkConfig config) {
		if (config != null) {
			this.getNetworkConfigs().add(config);
		}
	}
	
	public void addNetworkConfigs(ServerNetworkConfig...configs) {
		if (configs != null && configs.length > 0) {
			for (int i=0; i<configs.length; i++) {
				this.getNetworkConfigs().add(configs[i]);
			}
		}
	}

	public List<ServerNetworkConfig> getNetworkConfigs() {
		return networkConfigs;
	}

	public void setNetworkConfigs(List<ServerNetworkConfig> networkConfigs) {
		this.networkConfigs = networkConfigs;
	}

	public List<String> getHandlers() {
		return handlers;
	}

	public void setHandlers(List<String> handlers) {
		this.handlers = handlers;
	}
}
