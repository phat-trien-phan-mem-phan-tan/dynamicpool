package org.puppet.server.processor;

import org.puppet.server.controller.MainController;
import org.puppet.server.entity.User;
import org.puppet.server.executor.ExecutorServiceFactory;

public class SendMessageProcessor extends Processor {

	@Override
	public ProcessorExecutionResponse execute(final ProcessorExecutionRequest request) {
		// TODO Auto-generated method stub
		ExecutorServiceFactory.getInstance().execute(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(request.getParameters().containsKey("userName") && request.getParameters().containsKey("message")){
					String message = (String)request.getParameters().get("message");
					String userName = (String) request.getParameters().get("userName");
					User user = MainController.getInstance().getUserManager().getUser(userName);
					user.getSession().write(message);
					request.write("successful");
				}
			}
		});
		
		return null;
	}

}
