import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class Package {

	/**
	 * 执行命令，获取返回值
	 * 
	 * @param cmd
	 *            要执行的dos命令
	 * @param path
	 * @return 执行结果按行返回List<String>
	 */
	private static List<String> runCmd(String cmd, String path) {

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
				System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}

	public static void main(String[] args) throws Exception {


		Workspace.svnUpdate("Z:/台湾版本数值");
		
		File file = new File("Z:/");
		File[] list = file.listFiles();
		Set<String> except = Sets.newHashSet(".svn");

		// 安装 javaplus-util
		List<String> runCmd = runCmd("cmd /c mvn clean install",
				"C:/Users/Administrator/git/javaplus/javaplus-util");
		checkCommandResult(runCmd, 6, "BUILD SUCCESS");

		Workspace.svnCommit("E:/workspace/MobileServer");
		Workspace.svnUpdate("D:/workspace/MobileServer");

		for (File f : list) {
			if (!except.contains(f.getName())) {
				packageVersion(f);
			}
		}
		Workspace.svnCommit("D:/workspace/MobileServer");
		Workspace.svnCommit("D:/asworkspace/ProtocolSDK/gen");
	}

	private static void packageVersion(File configPath) throws Exception {

		// 导Excel
		cn.javaplus.plugins.generator.excel.App.generate(configPath
				.getCanonicalPath());

		// 导D.java 任务等等
		Build.main(null);

		// //提交D:/workspace
		// Workspace.svnCommit("D:/workspace/MobileServer",
		// "E:/workspace/MobileServer");
		//
		// //SVN 更新
		List<String> runCmd;
		// runCmd = runCmd("cmd /c svn update", "D:/workspace/MobileServer");
		// checkCommandResult(runCmd, 1, "Updated to revision", "At revision");

		// 打包成jar
		// runCmd = runCmd("cmd /c buildjar",
		// "C:/Users/Administrator/Documents/server/MobileServer");
		runCmd = runCmd("cmd /c buildjar", "D:/workspace/MobileServer");
		checkCommandResult(runCmd, 6, "BUILD SUCCESS");

		// 打包成zip
		runCmd = runCmd("cmd /c package", "D:/workspace/MobileServer");
		checkCommandResult(runCmd, 1, "BUILD SUCCESS");

		Mover.moveFile(configPath);
	}

	/**
	 * 如果倒数第lastIndex行包含这s中的任意一个 表示成功
	 * 
	 * @param runCmd
	 * @param lastIndex
	 * @param s
	 */
	private static void checkCommandResult(List<String> runCmd, int lastIndex,
			String... s) {
		String last = runCmd.get(runCmd.size() - lastIndex);
		for (String ss : s) {
			if (last.contains(ss)) {
				return;
			}
		}
		throw new RuntimeException("命令执行出错:" + last);
	}

}
