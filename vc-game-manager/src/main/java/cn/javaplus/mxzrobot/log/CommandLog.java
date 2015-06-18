package cn.javaplus.mxzrobot.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import util.CmdLog;
import cn.javaplus.util.Closer;
import cn.javaplus.util.Util;

import com.google.common.collect.Lists;

public class CommandLog {

	private static final String S = "XXKHSDFLJKJLA0912739846STLJL";
	private static final String FILE_NAME = createFileName();

	public static List<CmdLog> getCommandList() {
		List<String> lines = Util.File.getLines(getFile());
		ArrayList<CmdLog> ls = Lists.newArrayList();
		for (String s : lines) {
			ls.add(create(s));
		}
		Collections.reverse(ls);

		return Util.Collection.sub(ls, 100);
	}

	private static CmdLog create(String s) {
		s = s.replaceFirst("\\|", S);
		s = s.replaceFirst("\\|", S);
		String[] split = s.split(S);
		boolean isSuccess = "true".equals(split[0]);
		if (split.length >= 3) {
			CmdLog log = new CmdLog(isSuccess, split[1], split[2]);
			return log;
		} else {
			CmdLog log = new CmdLog(isSuccess, split[1], "");
			return log;
		}
	}

	/**
	 * 记录执行过的命令
	 * 
	 * @param isSuccess
	 *            命令是否执行成功
	 * @param heSay
	 */
	public static void log(boolean isSuccess, String heSay) {
		FileWriter fw = null;
		try {
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String format = f.format(new Date(System.currentTimeMillis()));

			fw = new FileWriter(getFile(), true);
			fw.write(isSuccess  + "|" + heSay+ "|" + format);
			fw.write("\r\n");
			fw.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			Closer.close(fw);
		}
	}

	private static File getFile() {
		File f = new File(FILE_NAME);
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return f;
	}

	private static String createFileName() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		String format = f.format(new Date(System.currentTimeMillis()));
		return "logs/" + format + ".log";
	}

}
