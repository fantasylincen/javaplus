package cn.mxz.base.telnet.command.system;

import java.io.PrintWriter;

import org.springframework.stereotype.Component;

import cn.mxz.base.servertask.DailyTaskClear;
import cn.mxz.base.servertask.UserCountersZeroTask;
import cn.mxz.base.telnet.TelnetCommand;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;

/**
 * 清空所有用户今日计数器
 * @author 林岑
 */
@Component("telnetCommand:clearusercounters")

public class ClearUserCounters implements TelnetCommand {

	@Override
	public void run(PrintWriter out, final String... args) {

		new UserCountersZeroTask().runSafty();
		for (City c : WorldFactory.getWorld().getOnlineAll()) {
			c.reloadUserCounter();
		}
		new DailyTaskClear().run();
	}

}
