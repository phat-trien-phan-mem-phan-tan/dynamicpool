package vn.edu.hust.student.dynamicpool.dal.manager;

import java.util.HashMap;
import java.util.Map;

import vn.edu.hust.student.dynamicpool.dal.client.entity.Client;

public class ClientManager {
	private Map<String, Client> clientManager;
	
	public ClientManager(){
		clientManager = new HashMap<String, Client>();
	}

	public Map<String, Client> getClientManager() {
		return clientManager;
	}

	public void setClientManager(Map<String, Client> clientManager) {
		this.clientManager = clientManager;
	}
}
