package cn.javaplus.cmd;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.management.RuntimeErrorException;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.log.Log;

public class Cmd {

	/**
	 * 执行命令，获取返回值
	 * 
	 * @param cmd
	 *            要执行的dos命令
	 *            
	 *            例子: cmd /c dir  
	 *              记住要加cmd /c
	 * @param path
	 * @return 执行结果按行返回List<String>
	 */
	public final static List<String> exec(String cmd, String path) {

		List<String> ret = Lists.newArrayList();
		try {
			Process process = Runtime.getRuntime().exec(cmd, null,
					new File(path));
			BufferedInputStream in = new BufferedInputStream(
					process.getInputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(in,
					"GBK"));
			String s;
			while ((s = br.readLine()) != null) {
				ret.add(s);
//				Log.d(s);
			}
		} catch (IOException e) {
//			e.printStackTrace();
//			Log.e("error", e.getMessage());
			throw new RuntimeException(e);
		}
		return ret;
	}

	public final static List<String> exec(String cmd) {
		return exec(cmd, ".");
	}

	/**
	 * 如果倒数第lastIndex行包含这s中的任意一个 表示成功
	 * 
	 * @param runCmd
	 * @param lastIndex
	 * @param s
	 */
	public final static void checkResult(List<String> runCmd, int lastIndex,
			String... s) {
		String last = runCmd.get(runCmd.size() - lastIndex);
		for (String ss : s) {
			if (last.contains(ss)) {
				return;
			}
		}
		throw new RuntimeException("命令执行出错:" + last);
	}

	public static void main(String[] args) throws Exception {

		// SVN 更新
		List<String> runCmd;
		runCmd = exec("cmd /c svn update",
				"C:/Users/Administrator/Documents/server/MobileServer");
		checkResult(runCmd, 1, "Updated to revision", "At revision");

		// 安装 javaplus-util
		runCmd = exec("cmd /c mvn clean install",
				"C:/Users/Administrator/git/javaplus/javaplus-util");
		checkResult(runCmd, 6, "BUILD SUCCESS");

		// 打包成jar
		runCmd = exec("cmd /c buildjar",
				"C:/Users/Administrator/Documents/server/MobileServer");
		checkResult(runCmd, 6, "BUILD SUCCESS");

		// 打包成zip
		runCmd = exec("cmd /c package",
				"C:/Users/Administrator/Documents/server/MobileServer");
		checkResult(runCmd, 1, "BUILD SUCCESS");
	}

}
