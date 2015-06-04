package ch.ethz.ssh2.examples;

import java.util.List;

import cn.javaplus.build.ssh.SSHClient;
import cn.javaplus.build.ssh.SSHExecException;
import cn.javaplus.build.ssh.SSHStatusException;
import cn.javaplus.log.Log;

public class Basic {
	public static void main(String[] args) {
		String hostname = "125.xxx.xxx.xxx";
		String username = "root";
		String password = "222222222";

		SSHClient c = null;
		try {
			c = new SSHClient(hostname, username, password);
//			c.connect();

//			List<String> back1 = c.exec("echo \"Text on STDOUT\"; echo \"Text on STDERR\" >&2");
//			print(back1);
			List<String> back2 = c.exec("echo aaa");
			print(back2);
			
			
			
		} catch (SSHStatusException e) {
			Log.e(e.getStatus());
		} catch (SSHExecException e) {
			print(e.getBackLines());
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			c.close();
		}
		
	}

	private static void print(List<String> back2) {
		for (String s : back2) {
			Log.d(s);
		}
	}
}
