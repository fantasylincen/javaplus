package cn.vgame.a.log;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.util.Util;
import cn.vgame.a.Server;

public class ConsoleLog {

	private final String log;
	private String date;

	public ConsoleLog(File file, String log) {
		this.log = log;
		date = file.getName().split("\\.")[0];
	}

	@Override
	public String toString() {
		String translate = Translate.translate(log);
		return date + " " + translate;
	}


	/**
	 * 获得最后N条日志记录
	 * 
	 * @param min
	 * @return
	 */
	public static List<ConsoleLog> get(String fileName, int count, String find) {

//		List<File> files = getFiles();
		ArrayList<ConsoleLog> ls = Lists.newArrayList();
//		Collections.reverse(files);
//		for (File f : files) {
		
		File f = getFile(fileName);
		
			List<ConsoleLog> logs = getLogs(f);
			Collections.reverse(logs);
			for (ConsoleLog l : logs) {
				String s = l.toString();
				s = s.toLowerCase();
				String regex = ".*" + find + ".*";
				regex = regex.toLowerCase();
				if (s.matches(regex))
					ls.add(l);
				if (ls.size() >= count) {
					break;
				}
			}
//		}
		return ls;
	}
	
	
	public static void main(String[] args) {
		String s = "at cn.vgame.a.result.ErrorResult.toException(ErrorResult.java:66)";
		System.out.println(s.toLowerCase().matches(".*exception.*"));
	}

	private static File getFile(String fileName) {
		String file = Server.getConfig().getString("logFilePath") + "/" + fileName;
		File file2 = new File(file);
		return file2;
	}

	public static List<File> getFiles() {
		String file = Server.getConfig().getString("logFilePath");
		File file2 = new File(file);
		return Lists.newArrayList(file2.listFiles());
	}

	private static List<ConsoleLog> getLogs(File file) {
		List<String> logs = Util.File.getLines(file);
		ArrayList<ConsoleLog> ls = Lists.newArrayList();
		for (String log : logs) {
			ConsoleLog l = new ConsoleLog(file, log);
			ls.add(l);
		}
		return ls;
	}

}
