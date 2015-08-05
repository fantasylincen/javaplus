package cn.javaplus.twolegs.compiler;

import cn.javaplus.build.deploy.DeployRule;
import cn.javaplus.build.maven.Maven;
import cn.javaplus.build.ssh.SSHClient;
import cn.javaplus.log.Log;
import cn.javaplus.util.MainArgs;

public class GameServerDeployRule implements DeployRule {


	@Override
	public void pack() {
		Maven maven = new Maven();
		maven.pack("git/games/two-legs/server");
	}

	@Override
	public void upload() {
		SSHClient c = getClient();
		c.addListener(new SCPClientListenerImpl());
		c.upload("../server/target/two-legs-server.jar", "/mnt/upan/two-legs-server/");
		c.close();
		Log.d("send over");
	}

	@Override
	public void start() {
		SSHClient c = getClient();
		c.exec("screen -S tls java -jar two-legs-server.jar");
		c.close();
	}

	private SSHClient getClient() {
		String find = MainArgs.find("host");
		String find2 = MainArgs.find("user");
		String find3 = MainArgs.find("password");
		SSHClient c = new SSHClient(find, find2, find3);
		return c;
	}

	@Override
	public void stop() {
		SSHClient c = getClient();
		c.exec("java -jar two-legs-server.jar -stop");
		c.close();
	}
}
