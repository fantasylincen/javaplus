package cn.javaplus.mxzrobot.actions;

import java.util.List;

import util.CmdLog;
import cn.javaplus.mxzrobot.log.CommandLog;
import cn.javaplus.string.StringPrinter;

public class ListCommand extends ActionBase {

	public String execute(Args args) {
		StringPrinter out = new StringPrinter("最后100条执行过的命令：<br><br>");
		List<CmdLog> list = CommandLog.getCommandList();
		if(list.isEmpty()) {
			out.print("&nbsp;&nbsp;&nbsp;无");
		}
		for (CmdLog c : list) {
			out.print("&nbsp;&nbsp;&nbsp;" + c);
			out.print("<br>");
		}
		return out.toString();
	}

}