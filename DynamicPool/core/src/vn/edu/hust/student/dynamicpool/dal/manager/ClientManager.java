package vn.edu.hust.student.dynamicpool.dal.manager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import vn.edu.hust.student.dynamicpool.dal.client.entity.Client;

public class ClientManager {
	private Map<String, Client> clientManager;
	
	public ClientManager(){
		clientManager = new ConcurrentHashMap<String, Client>();
	}
	
	public boolean addClient(Client client){
		Client old = this.clientManager.put(client.getClientName(), client);
		return old == null;
	}
	
	public Client getClient(String clientName){
		return this.clientManager.get(clientName);
	}
	
	public int size(){
		return this.clientManager.size();
	}
}
