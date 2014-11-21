package vn.edu.hust.student.dynamicpool.config;

import java.util.ArrayList;
import java.util.List;


public class HttpClientConfig {
	private List<String> handlers;
	
	public HttpClientConfig(){
		handlers = new ArrayList<>();
	}

	public List<String> getHandlers() {
		return handlers;
	}

	public void setHandlers(List<String> handlers) {
		this.handlers = handlers;
	}
	
	
}
