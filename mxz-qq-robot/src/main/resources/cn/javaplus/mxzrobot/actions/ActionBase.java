package cn.javaplus.mxzrobot.actions;

public abstract class ActionBase implements Action {

	protected ServerBean getServer(Args args) {
		String server = args.getString("server");
		String[] split = server.split("-");
		String ip = split[0];
		String port = split[1];
		return new ServerBean(ip, new Integer(port));
	}
}
