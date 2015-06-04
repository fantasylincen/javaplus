package cn.mxz.base.logserver.command;

import java.io.PrintWriter;
import java.util.Date;
import java.util.Set;

import org.springframework.stereotype.Component;

import cn.javaplus.time.Time;
import cn.mxz.base.telnet.TelnetCommand;

/**
 * 查询某天的次日留存率
 * @author 林岑
 *
 */
@Component("telnetCommand:fufeilv")

public class FuFeiLv implements TelnetCommand {

	@Override
	public void run(PrintWriter out, String... args) throws Throwable {

		Set<String> allCreate = new CreatedUsersFinder().findUsers(new Date(0), new Date(System.currentTimeMillis() + Time.MILES_ONE_DAY * 100000));

		Set<String> allFuFei = new FuFeiFinder().findAll();

		out.println("付费率:" + ((allFuFei.size() + 0f) / allCreate.size()));
	}
}
