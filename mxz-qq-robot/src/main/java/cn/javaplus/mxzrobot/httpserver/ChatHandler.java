package cn.javaplus.mxzrobot.httpserver;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import cn.javaplus.log.Debuger;
import cn.javaplus.mxzrobot.actions.Action;
import cn.javaplus.mxzrobot.actions.Args;
import cn.javaplus.mxzrobot.events.Ask;
import cn.javaplus.mxzrobot.events.Ask.AskWay;
import cn.javaplus.mxzrobot.listeners.AskFinder;
import cn.javaplus.string.StringPrinter;
import cn.javaplus.util.Closer;
import cn.javaplus.util.Util;

import com.google.common.io.Resources;

public class ChatHandler extends AbstractHandler {

	public ChatHandler() {
	}

	@Override
	protected String doGet(Map<String, Object> parameters) {

		Object object = parameters.get("message");
		if (object == null) {
			return buildHtml("错误了!");
		}
		String heSay = object.toString();

		heSay = trim(heSay);

		Ask a = new AskFinder().find(heSay);

		if (a == null) {
			List<AskWay> aa = new AskFinder().findSimilar(heSay);

			StringPrinter sp = new StringPrinter();
			sp.println("听不懂,哥哥!");
			sp.println("  你看看你要的答案在下面没有:");
			sp.println("");

			for (AskWay ask : aa) {
				sp.println(ask);
			}

			saveToLog(heSay);

			return buildHtml(sp.toString());
		} else {
			Action ac = a.getAction();
			String result;
			try {
				Args args = a.getArgs();
				result = ac.execute(args);
			} catch (Exception e) {
				throw new RuntimeException(a + "", e);
			}
			Debuger.debug("ChatHandler.doGet() result:" + result);
			return buildHtml(result);
		}
	}

	private void saveToLog(String heSay) {
		PrintWriter out = null;
		FileWriter fw = null;
		try {
			File file = new File("logs/questions" + ".txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			fw = new FileWriter(file, true);
			out = new PrintWriter(fw);
			out.println(heSay);
			out.flush();
		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			Closer.close(out, fw);
		}
	}

	private String buildHtml(String string) {
		String content = Util.File.getContent(Resources
				.getResource("index.temp"));
		string = string.replaceAll("\\$", "\\\\\\$");
		content = content.replaceAll("BODY_CONTENT", string);
		content = content.replaceAll("\r\n", "<br>");
		return content;
	}

	private String trim(String c) {
		c = c.trim();
		c = c.replaceAll(" ", "");// 去掉所有空格
		c = c.replaceAll("\\+", "");// 去掉所有+
		return c;
	}

}
