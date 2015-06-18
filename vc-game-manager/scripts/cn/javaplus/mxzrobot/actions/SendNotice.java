package cn.javaplus.mxzrobot.actions;

import cn.javaplus.string.StringPrinter;

public class SendNotice extends ActionBase {

	public String execute(Args args) {

		StringPrinter out = new StringPrinter();
		out.println("<form method=\"POST\" action=\"/sendNotice\">");
		out.println("	<div>");
		out.println("		服务器名字<br><input name=\"serverName\"/>");
		out.println("	</div>");
		out.println("	<div>");
		out.println("		公告内容<br><textarea name=\"notice\" cols=120 rows=10></textarea>");
		out.println("	</div>");
		out.println("	<div>");
		out.println("		<button class=\"button primary\" type=\"submit\" tabindex=\"3\">send</button>");
		out.println("	</div>");
		out.println("	<input type=\"hidden\" value=\"TOKEN_VALUE\" name=\"q\">");
		out.println("	<input type=\"hidden\" value=\"USER_NAME_VALUE\" name=\"uname\">");
		out.println("</form>");

		return out.toString();
	}

}