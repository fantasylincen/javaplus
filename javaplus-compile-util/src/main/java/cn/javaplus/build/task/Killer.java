package cn.javaplus.build.task;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.javaplus.build.ssh.SSHClient;

public class Killer {

	public void kill(String host, String userName, String password, String sss) {
		SSHClient c = new SSHClient(host, userName, password);
		List<String> all = c.exec("screen -ls");
		Pattern p = Pattern.compile("[0-9]{1,5}\\.gate");
		for (String ss : all) {
			Matcher m = p.matcher(ss);
			if(m.find()) {
				String find = m.group();
				String[] split = find.split("\\.");
				int id = new Integer(split[0]);
				c.exec("kill -9 " + id);
				c.exec("screen -wipe");
			}
		}
		c.close();
	}

}
