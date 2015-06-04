package cn.mxz.base.logserver.command;

import java.io.PrintWriter;
import java.util.Date;
import java.util.Set;

import org.springframework.stereotype.Component;

import cn.javaplus.time.AutoDateFormat;
import cn.javaplus.util.Util;
import cn.mxz.base.telnet.TelnetCommand;

/**
 * 查询指定时间段内创建过的玩家
 * @author 林岑
 *
 */
@Component("telnetCommand:querycreatetoday")

public class QueryCreatedUsers implements TelnetCommand {

	@Override
	public void run(PrintWriter out, String... args) throws Throwable {

		String dateStart = args[0].trim();

		String dateEnd = args[1].trim();

		AutoDateFormat f = new AutoDateFormat();

		Date start = f.parse(dateStart);

		Date end = f.parse(dateEnd);

		Set<String> allUser = new CreatedUsersFinder().findUsers(start, end);

		out.println("");
		out.println("---------" + dateStart + "至" + dateEnd + " 创建的玩家列表如下----------");

		int count = 1;

		for (String uId : allUser) {


			out.print(Util.Str.polishing(uId, 15));

			if(count % 5 == 0) {

				out.println("");
			}

			count ++;
		}

		out.println("");
		out.println("------------------------END--------------------------");

		out.println("玩家总数:" + allUser.size());
	}
}
