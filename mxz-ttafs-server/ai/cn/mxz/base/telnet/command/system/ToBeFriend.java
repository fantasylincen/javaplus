package cn.mxz.base.telnet.command.system;

import java.io.PrintWriter;

import org.springframework.stereotype.Component;

import cn.mxz.base.telnet.CommandException;
import cn.mxz.base.telnet.TelnetCommand;
import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;

/**
 * 让2者成为好友
 * @author 林岑
 *
 */
@Component("telnetCommand:tobefriend")

public class ToBeFriend implements TelnetCommand {

	@Override
	public void run(PrintWriter out, String... args) {

		String user1 = args[0].trim();

		String user2 = args[1].trim();

		World world = WorldFactory.getWorld();

		City city = world.get(user1);

		City city2 = world.get(user2);

		if(city == null || city2 == null) {

			throw new CommandException("玩家不存在! user1(" + user1 + ") = " + city + " user2(" + user2 + ") = " + city2);
		}

		city.getFriendManager().add(user2);
	}

}
