package cn.mxz.base.telnet.command.system;

import java.io.PrintWriter;

import org.springframework.stereotype.Component;

import cn.mxz.base.telnet.TelnetCommand;

/**
 * 重启服务器
 * @author 林岑
 */
@Component("telnetCommand:restartserver")

public class RestartServer implements TelnetCommand {

	@Override
	public void run(PrintWriter out, final String... args) {


	}

}
