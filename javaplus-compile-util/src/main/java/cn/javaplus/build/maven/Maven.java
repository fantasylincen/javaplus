package cn.javaplus.build.maven;

import java.util.List;

import cn.javaplus.cmd.Cmd;
import cn.javaplus.log.Log;
import cn.javaplus.system.SystemEnv;

public class Maven {

	public void install(String path) {
		Log.d(path);
		path = getPath(path);
		List<String> exec = Cmd.exec("cmd /c mvn clean install", path);
		Cmd.checkResult(exec, 6, "BUILD SUCCESS");
	}

	private String getPath(String path) {
		if (!isJueDui(path)) { //绝对路径
			String home = SystemEnv.getHomeDir();
			if (!home.endsWith("/")) {
				home = home + "/";
			}
			path = home + path;
		}
		return path;
	}

	private boolean isJueDui(String path) {
		return path.charAt(1) == ':';
	}

	public void pack(String path) {
		Log.d(path);
		path = getPath(path);
		List<String> exec = Cmd.exec("cmd /c mvn clean package", path);
		Cmd.checkResult(exec, 6, "BUILD SUCCESS");
	}

}
