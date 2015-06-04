package cn.mxz.base.telnet.command.user;

import java.io.PrintWriter;

import org.springframework.stereotype.Component;

import cn.mxz.base.telnet.CommandException;
import cn.mxz.base.telnet.TelnetCommand;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.city.PlayerProperty;
import cn.mxz.util.counter.CounterKey;

/**
 * 设置玩家某种属性
 *
 * @author 林岑
 *
 */
@Component("telnetCommand:set")

public class SetSomeThing implements TelnetCommand {

	@Override
	public void run(PrintWriter out, String... args) {

		String id = args[0].trim();

		String uanme = args[1];

		City city =CityFactory.getCity(uanme);

		int set = new Integer(args[2]);

		if (city == null) {

			throw new CommandException("玩家不存在:" + uanme);
		}

		if (id .equals( "1000") ){

			city.getUserCounterHistory().set(CounterKey.MAX_MISSION_ID, set );

			WorldFactory.getWorld().reload();
		} else {

			city.getPlayer().set(PlayerProperty.value(id), set);
		}
	}
}
