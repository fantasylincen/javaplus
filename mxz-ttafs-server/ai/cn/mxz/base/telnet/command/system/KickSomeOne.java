package cn.mxz.base.telnet.command.system;

import java.io.PrintWriter;

import org.springframework.stereotype.Component;

import cn.mxz.base.telnet.TelnetCommand;
import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;

/**
 * 踢某人下线
 * @author 林岑
 */
@Component("telnetCommand:kicksomeone")

public class KickSomeOne implements TelnetCommand {

	@Override
	public void run(PrintWriter out, final String... args) {

		World world = WorldFactory.getWorld();

		City city = world.get(args[0].trim());

		city.getSocket().close();
	}

}
