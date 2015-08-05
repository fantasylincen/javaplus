package cn.javaplus.crazy.deploy;

import cn.javaplus.build.maven.Maven;
import cn.javaplus.build.ssh.SSHClient;
import cn.javaplus.crazy.define.D;
import cn.javaplus.log.Log;

public class ServerDeployRule implements DeployRule {

	@Override
	public void pack() {
		Maven maven = new Maven();
		maven.pack("git/games/javaplus-game-crazy/server/core");
	}

	@Override
	public void upload() {
		SSHClient c = new SSHClient(D.HOST_NAME, D.USER_NAME, D.PASSWORD);
		c.addListener(new SCPClientListenerImpl());
		c.upload("../../server/core/target/crazy-server.jar", "/mnt/upan/server/");
		Log.d("send over");
	}

	@Override
	public void backup() {

	}

	@Override
	public void start() {
		SSHClient c = new SSHClient(D.HOST_NAME, D.USER_NAME, D.PASSWORD);
		c.exec("screen -S server java -jar crazy-server.jar");
		c.close();
	}

	@Override
	public void stop() {
		SSHClient c = new SSHClient(D.HOST_NAME, D.USER_NAME, D.PASSWORD);
		c.exec("java -jar crazy-server.jar -stop");
		c.close();
	}
}
