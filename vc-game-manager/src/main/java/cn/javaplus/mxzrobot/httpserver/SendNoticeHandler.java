package cn.javaplus.mxzrobot.httpserver;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.Set;

import cn.javaplus.file.IProperties;
import cn.javaplus.log.Log;
import cn.javaplus.mxzrobot.actions.ServerBean;
import cn.javaplus.mxzrobot.smessender.ServerMessageSender;
import cn.javaplus.mxzrobot.token.TokenManager;
import cn.javaplus.string.StringPrinter;
import cn.javaplus.util.Util;

public class SendNoticeHandler extends AbstractHandler {

	@Override
	protected String doGet(Map<String, Object> parameters) {

		if (!hasLogin(parameters)) {
			return LoginHtml.build("Please Login.");
		}

		String username = parameters.get("uname") + "";
		String token = parameters.get("q") + "";

		String serverName = parameters.get("serverName") + "";
		String notice = parameters.get("notice") + "";
		Log.d("uname:" + serverName);
		Log.d("uname:" + notice);

		
		String noticeHtml = notice.replaceAll("\r\n", "<br>");
		noticeHtml = noticeHtml.replaceAll("\r", "<br>");
		
		try {
			String sendNotice = sendNotice(serverName, notice);
			return MessageHtml.build("", serverName + "<br><br>" + notice + "<br><br>" + sendNotice, username, token);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			return MessageHtml.build("", "错误:" + noticeHtml, username, token);
		}

	}

	private String sendNotice(String serverName, String notice) {
		ServerBean server = getServer(serverName);
		ServerMessageSender s = new ServerMessageSender();
		String ip = server.getIp();
		Integer port = server.getPort();
		String script = buildSendNoticeScript(serverName, notice);
		return s.send(ip, port, script);
	}

	private String buildSendNoticeScript(String serverName, String notice) {
		StringPrinter sp = new StringPrinter();
		sp.println("import notice.NoticeManager;");
		sp.println("import cn.javaplus.string.StringPrinter;");
		
		sp.println("public class SNotice {");
		sp.println("	public String run() {");
		notice = notice.replaceAll("\r\n", "\r");
		String[] ss = notice.split("\r");
		sp.println("		StringPrinter s = new StringPrinter();");
		for (String string : ss) {
			sp.println("		s.println(\"" + string + "\");");
		}
		sp.println("		NoticeManager.getInstance().addTimely(-1, s.toString())");
		sp.println("		return \"发送成功!\";");
		sp.println("	}");
		sp.println("}");
		return sp.toString();
	}

	private ServerBean getServer(String serverName) {
		IProperties p = Util.Property.getProperties("config/translate.properties");

		Set<Object> keySet = p.keySet();
		for (Object k : keySet) {
			String key = k.toString();
			String value = p.getProperty(k);
			if (value.equals(serverName)) {
				return parseServer(key);
			}
		}
		throw new RuntimeException("服务器不存在:" + serverName);
	}

	private ServerBean parseServer(String s) {
		String[] ss = s.split("\\-");
		String ip = ss[0];
		Integer port = new Integer(ss[1]);
		return new ServerBean(ip, port);
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
}
