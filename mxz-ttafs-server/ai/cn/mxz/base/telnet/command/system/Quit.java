package cn.mxz.base.telnet.command.system;

import java.io.PrintWriter;

import org.springframework.stereotype.Component;

import cn.javaplus.util.Closer;
import cn.mxz.base.telnet.TelnetCommand;

/**
 * 退出命令
 * @author 林岑
 *
 */
@Component("telnetCommand:quit")

public class Quit implements TelnetCommand {

	@Override
	public void run(PrintWriter out, String... args) {

		Closer.close(out);
	}

}
