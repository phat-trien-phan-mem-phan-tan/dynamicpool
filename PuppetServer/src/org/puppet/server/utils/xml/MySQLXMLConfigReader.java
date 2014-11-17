package org.puppet.server.utils.xml;

import org.puppet.server.config.MySQLConfig;

public class MySQLXMLConfigReader extends XMLReader {

	public MySQLXMLConfigReader(String path) throws Exception {
		super(path);
		this.rootNodeName = "/mysql";
	}

	public MySQLConfig getConfig() {
		MySQLConfig result = new MySQLConfig();
		result.setUser(this.query(this.rootNodeName + "/user"));
		result.setPassword(this.query(this.rootNodeName + "/password"));
		result.setUrl(this.query(this.rootNodeName + "/url"));
		return result;
	}
}
