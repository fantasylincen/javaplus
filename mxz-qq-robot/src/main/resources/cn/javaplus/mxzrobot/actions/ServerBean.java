package cn.javaplus.mxzrobot.actions;


public class ServerBean {

	private String ip;
	private Integer port;

	public ServerBean(String ip, Integer port) {
		this.ip = ip;
		this.port = port;
	}
	
	public String getIp() {
		return ip;
	}
	public Integer getPort() {
		return port;
	}
}
