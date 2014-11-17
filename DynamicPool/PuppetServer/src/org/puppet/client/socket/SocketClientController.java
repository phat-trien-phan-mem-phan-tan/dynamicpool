package org.puppet.client.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

import org.eclipse.jetty.util.ajax.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SocketClientController implements SocketClientHandler {
	private Socket socketClient;
	private Logger log = LoggerFactory.getLogger(SocketClientController.class);
	private SocketClientHandler handler;
	
	public SocketClientController(SocketClientHandler handler){
		setHandler(handler);
	}
	
	public SocketClientController(){
		setHandler(this);
	}

	public SocketClientHandler getHandler() {
		return handler;
	}

	public void setHandler(SocketClientHandler handler) {
		this.handler = handler;
	}
	
	public void start(String serverIpAddress, int port) {
		try {
			socketClient = new Socket(serverIpAddress, port);
			log.info("Socket Client Start Sucessfully");
			while (true) {
				BufferedReader input;
				try {
					input = new BufferedReader(new InputStreamReader(
							socketClient.getInputStream()));
					String answer = input.readLine();
					if (answer != null) {
						handler.messageReceive(answer);
					} else {
						log.debug("diconnect from server");
						disconnect();
						break;
					}
				} catch (SocketException e) {
					e.printStackTrace();
					disconnect();
					break;
				} catch (IOException ioException) {
					log.debug("diconnect from server");
					ioException.printStackTrace();
					disconnect();
					break;
				}

			}
		} catch (IOException e) {
			log.debug("Socket Client connot connect to server");
			e.printStackTrace();
		}
	}


	@Override
	public void messageReceive(String s) {
		log.debug("message receive: {}", s);
	}

	public void send(Object data) {
		try {
			PrintWriter out = new PrintWriter(socketClient.getOutputStream(),
					true);
			if(data instanceof String) out.println(data);
			else{
				JSON json = new JSON();
				String message = json.toJSON(data);
				out.println(message);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void disconnect() {

	}

	public void stop() {
		try {
			socketClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
