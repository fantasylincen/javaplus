package cn.mxz.base.logserver.command;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.springframework.stereotype.Component;

import cn.javaplus.util.Util;
import cn.mxz.base.telnet.TelnetCommand;

/**
 * 查询某天的7日留存率
 * @author 林岑
 *
 */
@Component("telnetCommand:queryqiriliucunlv")

public class QueryQiRiLiuCunLv implements TelnetCommand {

	@Override
	public void run(PrintWriter out, String... args) throws Throwable {

		String dt = args[0].trim();

		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

		Date start = f.parse(dt);

		Date end = Util.Time.nextDay(1, start);

		Set<String> allCreate = new CreatedUsersFinder().findUsers(start, end);

		start = Util.Time.nextDay(1, start);

		end = Util.Time.nextDay(7, end);

		Set<String> allLogin = new LoginUsersFinder().findUsers(start, end);


		int countLogind = new LiuCunLvLoginCountor().getLogind(allCreate, allLogin);

		out.println("总计创建数:" + allCreate.size());

		out.println("后7日登陆数:" + countLogind);

		out.println("7日留存率:" + (allCreate.isEmpty() ? 0 : (countLogind + 0f) / allCreate.size()));
	}
}
