package cn.javaplus.mxzrobot.actions;

public abstract class ActionBase implements Action {

	protected ServerBean getServer(Args args) {
		String server = args.getString("server");
		return getServer(server);
	}

	public static ServerBean getServer(String server) {
		String[] split = server.split("-");
		String ip = split[0];
		String port;
		try {
			port = split[1];
		} catch (Exception e) {
			throw new RuntimeException("是否没有指定服务器的IP和端口:" + server);
		}
		return new ServerBean(ip, new Integer(port));
	}
}
