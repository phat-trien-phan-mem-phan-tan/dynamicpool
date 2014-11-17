package org.puppet.server.manager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.puppet.server.entity.Room;
import org.puppet.server.entity.User;

public class RoomManager {
	private Map<String, Room> rooms;

	public RoomManager() {
		rooms = new ConcurrentHashMap<String, Room>();
	}

	public Room getLobby() {
		String roomName = "lobby";
		Room room = new Room(roomName);
		if(!this.rooms.containsKey("lobby")){
			this.rooms.put(roomName, room);
		}
		return this.rooms.get(roomName);
	}

	public User findUser(String userName) {
		for (String roomName : this.rooms.keySet()) {
			Room room = this.rooms.get(roomName);
			for (String name : room.getUsers().keySet()) {
				if (name.equals(userName)) {
					return room.getUser(name);
				}
			}
		}
		return null;
	}

	public User findUser(long sessionId) {
		for (String roomName : this.rooms.keySet()) {
			Room room = this.rooms.get(roomName);
			for (String name : room.getUsers().keySet()) {
				User user = room.getUser(name);
				if (user.getSession().getId() == sessionId)
					return user;
			}
		}
		return null;
	}

	public void moveUserToRoom(String userName, Room room) {
		User user = this.findUser(userName);
		if (user != null) {
			room.addUser(user);
		} else {
			System.out.println("user not found");
		}
	}

	public Map<String, Room> getRooms() {
		return rooms;
	}

	public void setRooms(Map<String, Room> rooms) {
		this.rooms = rooms;
	}

	public Room addRoom(Room room) {
		return this.rooms.put(room.getRoomName(), room);
	}
}
