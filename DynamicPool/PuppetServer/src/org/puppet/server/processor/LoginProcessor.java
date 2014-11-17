package org.puppet.server.processor;

import java.util.Map;

import org.puppet.server.controller.MainController;
import org.puppet.server.entity.Room;
import org.puppet.server.entity.User;

public class LoginProcessor extends Processor {

	@Override
	public ProcessorExecutionResponse execute(ProcessorExecutionRequest request) {
		// TODO Auto-generated method stub
		Map<String, Object> map = request.getParameters();
		if (map.containsKey("userName")) {
			User user = new User();
			String userName = (String) map.get("userName");
			user.setUserName(userName);
			user.setSession(request.getSession());
			Room lobby = MainController.getInstance().getRoomManager().getLobby();
			lobby.addUser(user);
			request.write("Login sucessful");
		} else {
			request.write("Message not contain userName");
		}
		return null;
	}

}
