package cn.javaplus.crazy.deploy;

import java.sql.Date;
import java.text.SimpleDateFormat;

import cn.javaplus.build.file.CopyFile;
import cn.javaplus.build.maven.Maven;
import cn.javaplus.build.ssh.SSHClient;
import cn.javaplus.crazy.define.D;
import cn.javaplus.log.Log;

public class GateDeployRule implements DeployRule {

	@Override
	public void pack() {
		Maven maven = new Maven();
		maven.pack("git/games/javaplus-game-crazy/server/gate");
	}

	@Override
	public void upload() {
		SSHClient c = new SSHClient(D.HOST_NAME, D.USER_NAME, D.PASSWORD);
		c.addListener(new SCPClientListenerImpl());
		c.upload("../../server/gate/target/crazy-gate.jar", "/mnt/upan/gate/");
		Log.d("send over");
	}

	@Override
	public void backup() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String fileName = sf.format(new Date(System.currentTimeMillis()));
		fileName = fileName + ".jar";

		new CopyFile().copy("../../server/gate/target/crazy-gate.jar",
				D.BACK_UP_PATH + "gate/" + fileName);
	}

	@Override
	public void start() {
		SSHClient c = new SSHClient(D.HOST_NAME, D.USER_NAME, D.PASSWORD);
		c.exec("screen -S gate java -jar crazy-gate.jar");
		c.close();
	}

	@Override
	public void stop() {
		SSHClient c = new SSHClient(D.HOST_NAME, D.USER_NAME, D.PASSWORD);
		c.exec("java -jar crazy-gate.jar -stop");
		c.close();
	}
}
