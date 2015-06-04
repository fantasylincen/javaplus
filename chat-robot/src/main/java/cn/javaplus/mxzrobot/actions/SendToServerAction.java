package cn.javaplus.mxzrobot.actions;

import java.util.Set;

import org.dom4j.Attribute;
import org.dom4j.Element;

import cn.javaplus.mxzrobot.actions.ActionBase;
import cn.javaplus.mxzrobot.actions.ServerBean;
import cn.javaplus.mxzrobot.smessender.ServerMessageSender;
import cn.javaplus.string.StringPrinter;
import cn.javaplus.util.Util;

public class SendToServerAction extends ActionBase {

	private String script;
	private String scriptFilePath;

	public SendToServerAction(Element sendToServer) {
		script = sendToServer.getTextTrim();
		Attribute attribute = sendToServer.attribute("scriptFile");
		if (attribute == null) {
			throw new RuntimeException("必须在 <sendToServer> 节点中指定 脚本文件路径, 比如: <sendToServer scriptFile=\"Set.java\">");
		}
		scriptFilePath = attribute.getText().trim();

	}

	private void packageScript() {
		String content = Util.File.getContent(scriptFilePath);
		script = content.replaceAll("return \"RUN_FOUNCTION\";", script);
	}

	public String execute(Args args) {
		Set<String> keys = args.getKeys();
		for (String s : keys) {
			String string = args.getString(s);
			script = script.replaceAll(s, "\"" + string + "\"");
		}

		packageScript();

		ServerBean server = getServer(args);

		String back = new ServerMessageSender().send(

		server.getIp(), server.getPort(), script);

		return translate(back);
	}

	private String translate(String back) {
		// String SP = "LSHOIUBNNLHIUYQR55555";
		// back = back.replaceFirst(":", SP);
		// back = back.replaceFirst(":", SP);

		// String[] ss = back.split(SP);
		StringPrinter sp = new StringPrinter();
		// sp.println("服务器ID:" + ss[0]);
		// sp.println("服务器名字:" + ss[1]);
		// sp.println("服务器反馈:" + ss[2]);
		sp.println(back);
		sp.replaceAll("\r\n", "<br>");
		return sp.toString();
	}

}
