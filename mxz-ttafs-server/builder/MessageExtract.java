import java.io.File;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.javaplus.util.Util;

public class MessageExtract {

	private static final String TALE = "\"\\);";
	private static final String HEAD = "throw new IllegalOperationException\\(\"";
	private static final String ALL = HEAD + ".+" + TALE;
	private int idStart = 10279;

	/**
	 * 将所有的 throw new IllOperatingFaildException 中的汉字, 全部提取出来, 同时 会修改Java文件
	 */
	public void extractIllegalOperationException() {
		Iterator<File> fit = Util.File.getFilesIterator(".");
		Pattern compile = Pattern.compile(ALL);

//		if(true) throw new RuntimeException("请确保全部提交后, 屏蔽此行, 再操作! 因为会损毁代码!");
		
		while (fit.hasNext()) {
			File file = fit.next();
			if (!file.getName().endsWith(".java")) {
				continue;
			}

			String content = Util.File.getContent(file);
			Matcher m = compile.matcher(content);
			process(file, content, m);
		}
	}

	private void process(File file, String content, Matcher m) {
		while (m.find()) {
			String group = m.group();
			String r = group.replaceAll(HEAD, "").replaceAll(TALE, "");
			System.out.println(idStart + "	" + r);
			content = content.replaceFirst(ALL, "throw new (S.S" + idStart + ");     -------" + r );
			Util.File.write(file, content);
			
			
			idStart ++;
		}
	}

	public static void main(String[] args) {
		new MessageExtract().printWhere();
	}

	/**
	 * 打印所有消息在哪个地方出现的
	 */
	private void printWhere() {
		Iterator<File> fit = Util.File.getFilesIterator(".");
		Pattern compile = Pattern.compile("S.S[0-9]{1,5}");

		while (fit.hasNext()) {
			File file = fit.next();
			if (!file.getName().endsWith(".java")) {
				continue;
			}

			String content = Util.File.getContent(file);
			Matcher m = compile.matcher(content);
			
			while(m.find()) {
				String group = m.group();
				System.out.println(group.replace("S\\.S", "") + "	" + file.getName());
			}
		}
	}

}
