package cn.javaplus.mxzrobot.httpserver;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import cn.javaplus.file.IProperties;
import cn.javaplus.log.Debuger;
import cn.javaplus.log.Log;
import cn.javaplus.mxzrobot.actions.Action;
import cn.javaplus.mxzrobot.actions.Args;
import cn.javaplus.mxzrobot.events.Ask;
import cn.javaplus.mxzrobot.events.Ask.AskWay;
import cn.javaplus.mxzrobot.listeners.AskFinder;
import cn.javaplus.mxzrobot.log.CommandLog;
import cn.javaplus.mxzrobot.token.TokenManager;
import cn.javaplus.string.StringPrinter;
import cn.javaplus.util.Closer;
import cn.javaplus.util.Util;

public class ChatHandler extends AbstractHandler {

	public ChatHandler() {
	}

	@Override
	protected String doGet(Map<String, Object> parameters) {

		if (!hasLogin(parameters)) {
			return LoginHtml.build("Please Login.");
		}

		Object object = parameters.get("message");
		String username = parameters.get("uname") + "";
		String token = parameters.get("q") + "";
		if (object == null) {
			return MessageHtml.build("帮助", "错误了!", username, token);
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

			return MessageHtml.build(heSay, sp.toString(), username, token);
		} else {
			Action ac = a.getAction();
			String result;
			try {
				Args args = a.getArgs();
				result = ac.execute(args);
				CommandLog.log(true, heSay);
			} catch (Exception e) {
				CommandLog.log(false, heSay);
				e.printStackTrace();
				return buildErrorHtml(a, e);
			}
			Debuger.debug("ChatHandler.doGet() result:" + result);
			return MessageHtml.build(heSay, result, username, token);
		}
	}

	private boolean hasLogin(Map<String, Object> parameters) {
		String uname = parameters.get("uname") + "";
		String token = parameters.get("q") + "";
		IProperties p = Util.Property.getProperties("config/users.properties");
		Object value = p.get(uname);
		if (value == null) {
			Log.d("未知用户登陆:" + value);
			return false;
		}
		return token.equals(TokenManager.get(uname));
	}

	private String buildErrorHtml(Ask a, Exception e) {
		StringWriter sw = null;
		PrintWriter p = null;
		try {
			sw = new StringWriter();
			p = new PrintWriter(sw);
			e.printStackTrace(p);
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		} finally {
			Closer.close(p);
			Closer.close(sw);
		}
		return sw.toString();
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

	private String trim(String c) {
		c = c.trim();
		c = c.replaceAll(" ", "");// 去掉所有空格
		c = c.replaceAll("\\+", "");// 去掉所有+
		return c;
	}

}
