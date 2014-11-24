package org.puppet.server.entity;

import java.util.concurrent.ConcurrentHashMap;

import org.puppet.server.manager.UserManager;

public class Room extends UserManager {
	private String roomName;
	
	public Room(String name){
		setRoomName(name);
		users = new ConcurrentHashMap<String, User>();
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
	public void writeMessage(Object message){
		for (String userName : this.users.keySet()) {
			User user = this.getUser(userName);
			user.getSession().write(message);
		}
	}
}
