package cn.vgame.a.log;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.util.Util;
import cn.vgame.a.Server;

public class ConsoleLog {

	private long time;

	public ConsoleLog(String log) {
		String[] s = log.split("\\|");
		String t = s[0].split("\\.")[0];
		try {
			time = DATE_FORMAT.parse(t).getTime();
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获得N分钟之前的日志
	 * 
	 * @param min
	 * @return
	 */
	public static List<ConsoleLog> get(int min) {
		
		List<File> files = getFiles();
		ArrayList<ConsoleLog> ls = Lists.newArrayList();
		for (File file : files) {
			if(isIn(min, file)) {
				ls.addAll(getLogs(file, min));
			}
		}
		return ls;
	}

	private static boolean isIn(int min, File file) {
		String name = file.getName();
		String[] ss = name.split("\\.");
		String date = ss[0].trim();
		long time;
		try {
			java.util.Date p = DATE_FORMAT.parse(date);
			time = p.getTime();
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}

		return isIn(min, time);
	}

	private static boolean isIn(int min, long time) {
		long now = System.currentTimeMillis();
		long start = now - min * 60 * 1000;
		
		return time > start;
	}

	private static List<File> getFiles() {
		String file = Server.getConfig().getString("logFilePath");
		File file2 = new File(file);
		return Lists.newArrayList(file2.listFiles());
	}

	private static List<ConsoleLog> getLogs(File file, int min) {
		List<String> logs = Util.File.getLines(file);
		ArrayList<ConsoleLog> ls = Lists.newArrayList();
		for (String log : logs) {
			ConsoleLog l = new ConsoleLog(log);
			if(isIn(min, l.getTime())) {
				ls.add(l);
			}
		}
		return ls;
	}


	private long getTime() {
		return time;
	}

	static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd");

}
