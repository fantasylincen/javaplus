package cn.javaplus.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.javaplus.cmd.Cmd;

public class Killer {
	public void kill(String name) {
		List<String> all = Cmd.exec("screen -ls");
		Pattern p = Pattern.compile("[0-9]{1,5}\\." + name);
		for (String ss : all) {
			Matcher m = p.matcher(ss);
			if (m.find()) {
				String find = m.group();
				String[] split = find.split("\\.");
				int id = new Integer(split[0]);
				Cmd.exec("kill -9 " + id);
				Cmd.exec("screen -wipe");
			}
		}
	}
}
