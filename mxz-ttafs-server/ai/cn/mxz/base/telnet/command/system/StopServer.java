package cn.mxz.base.telnet.command.system;

import java.io.PrintWriter;

import org.springframework.stereotype.Component;

import cn.mxz.base.server.Server;
import cn.mxz.base.telnet.TelnetCommand;
import cn.mxz.base.world.WorldFactory;

/**
 * 重启服务器
 * @author 林岑
 */
@Component("telnetCommand:stopserver")
public class StopServer implements TelnetCommand {

	@Override
	public void run(PrintWriter out, final String... args) {
		Server server = WorldFactory.getWorld().getServer();
		server.stop();
	}

}
