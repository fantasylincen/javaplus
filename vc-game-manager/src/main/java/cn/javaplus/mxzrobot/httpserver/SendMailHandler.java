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

public class SendMailHandler extends AbstractHandler {

	@Override
	protected String doGet(Map<String, Object> parameters) {

		if (!hasLogin(parameters)) {
			return LoginHtml.build("Please Login.");
		}
		String username = parameters.get("uname") + "";
		String token = parameters.get("q") + "";

		String serverName = parameters.get("serverName") + "";
		String playerName = parameters.get("playerName") + "";
		String mail = parameters.get("email") + "";
		Log.d("uname:" + serverName);
		Log.d("uname:" + mail);
		
		
		String mailHtml = mail.replaceAll("\r\n", "<br>");
		mailHtml = mailHtml.replaceAll("\r", "<br>");
		
		try {
			String sendNotice = sendMail(serverName, playerName,  mail);
			return MessageHtml.build("", serverName + "<br><br>" + mail + "<br><br>" + sendNotice, username, token);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			return MessageHtml.build("", "错误:" + mailHtml, username, token);
		}

	}

	private String sendMail(String serverName, String playerName, String mail) {
		ServerBean server = getServer(serverName);
		ServerMessageSender s = new ServerMessageSender();
		String ip = server.getIp();
		Integer port = server.getPort();
		String script = buildSendMailScript(serverName, playerName, mail);
		return s.send(ip, port, script);
	}

	private String buildSendMailScript(String serverName, String playerName, String mail) {
		StringPrinter sp = new StringPrinter();
		sp.println("import game.mail.MailBase;");
		sp.println("import cn.javaplus.string.StringPrinter;");
		
		sp.println("public class SMail {");
		sp.println("	public String run() {");
		mail = mail.replaceAll("\r\n", "\r");
		mail = mail.replaceAll("\"", "\\\\\"");
		String[] ss = mail.split("\r");
		sp.println("		user.UserInfo u = getUser(\"" + playerName + "\");");
		sp.println("		StringPrinter s = new StringPrinter();");
		for (String string : ss) {
			sp.println("		s.println(\"" + string + "\");");
		}
		sp.println("		MailBase mail = new MailBase(\"\", game.mail.MailType.SYSTEM_NOTICE, \"-1|\" + s.toString());");
		sp.println("		u.getMailManager().addMail(mail);");
		sp.println("		return \"发送成功!\" + \"<br> 玩家名字:" + playerName + "<br>内容:<br>\" + s.toString() ;");
		sp.println("	}");
		
		sp.println("	public user.UserInfo getUser(String name) {");

		sp.println("		user.UserInfo u = user.UserManager.getInstance().getByNickName(name);");
		sp.println("		if (u == null) {");
		sp.println("			Integer id;");
		sp.println("			try {");
		sp.println("				id = new Integer(name);");
		sp.println("			} catch (NumberFormatException e) {");
		sp.println("				throw new RuntimeException(\"玩家不存在:\" + name);");
		sp.println("			}");
		sp.println("			u = user.UserManager.getInstance().getByName(id);");
		sp.println("		}");
		sp.println("		if(u == null)");
		sp.println("			throw new RuntimeException(\"玩家不存在:\" + name);");
		sp.println("		return u;");

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