package cn.javaplus.build;

import java.io.File;
import java.io.IOException;

import cn.javaplus.util.Util;

class Command {

	private String cmd;
	private String path;
	private Command next;

	public Command(String cmd, String path) {
		this.cmd = cmd;
		this.path = path;
	}

	public String getCmd() {
		return cmd;
	}

	public String getPath() {
		return path;
	}

	public void process() {
		Runtime r = Runtime.getRuntime();
		try {
			Process exec = r.exec(cmd, new String[0], new File(path));
			new ErrorReader(exec).run();
			new InfoReader(exec).run();

			if (next != null) {
				next.process();
			}

		} catch (IOException e) {
			throw Util.Exception.toRuntimeException(e);
		}
	}

	public void setCommandOverCommand(Command next) {
		this.next = next;
	}

}