package org.puppet.server.processor;

import java.util.HashMap;
import java.util.Map;

import org.puppet.server.controller.MainController;
import org.puppet.server.entity.Room;
import org.puppet.server.entity.User;
import org.puppet.server.executor.ExecutorServiceFactory;

public class CreateGameProcessor extends Processor {

	@Override
	public ProcessorExecutionResponse execute(final ProcessorExecutionRequest request) {
		// TODO Auto-generated method stub
		ExecutorServiceFactory.getInstance().execute(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Map<String, Object> params = request.getParameters();
				Map<String, Object> result = new HashMap<String, Object>();
				if(params.containsKey("roomName") && params.containsKey("userName")){
					String roomName = (String)params.get("roomName");
					String userName = (String)params.get("userName");
					User user = (User)params.get("user");
					Room room = new Room(roomName);
					Room newRoom = MainController.getInstance().getRoomManager().addRoom(room);
					MainController.getInstance().getRoomManager().moveUserToRoom(userName, newRoom);
					MainController.getInstance().getRoomManager().moveUserToRoom(user.getUserName(), newRoom);
					result.put("createGame", true);
					newRoom.writeMessage(result);
				}else{
					result.put("createGame", false);
					request.write(result);
				}
			}
		});
		return null;
	}

}
