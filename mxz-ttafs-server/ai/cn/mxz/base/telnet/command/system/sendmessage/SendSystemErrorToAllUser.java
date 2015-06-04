package cn.mxz.base.telnet.command.system.sendmessage;

import java.io.PrintWriter;
import java.util.Collection;

import org.springframework.stereotype.Component;

import cn.mxz.base.telnet.TelnetCommand;
import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.messagesender.MessageFactory;

/**
 * 全服发送系统错误
 * @author 林岑
 *
 */
@Component("telnetCommand:sendsystemerror")

public class SendSystemErrorToAllUser implements TelnetCommand {

	@Override
	public void run(PrintWriter out, String... args) {

		World world = WorldFactory.getWorld();

		Collection<City> all = world.getOnlineAll();

		for (City city : all) {

			MessageFactory.getSystem().error(city.getSocket());
		}
	}
}
