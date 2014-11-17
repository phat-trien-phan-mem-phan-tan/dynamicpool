package org.puppet.server.manager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.puppet.server.entity.User;


public class UserManager {
	protected Map<String, User> users;
	
	public UserManager(){
		users = new ConcurrentHashMap<String, User>();
	}

	public Map<String, User> getUsers() {
		return users;
	}

	public void setUsers(Map<String, User> users) {
		this.users = users;
	}
	
	public void addUser(User user){
		this.users.put(user.getUserName(), user);
	}
	
	public User getUser(String userName){
		return this.users.get(userName);
	}
}
