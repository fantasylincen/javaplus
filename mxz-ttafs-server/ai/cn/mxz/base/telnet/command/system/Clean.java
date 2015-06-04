package cn.mxz.base.telnet.command.system;

import java.io.PrintWriter;

import org.springframework.stereotype.Component;

import cn.mxz.base.telnet.TelnetCommand;

/**
 * 清屏
 * @author 林岑
 *
 */
@Component("telnetCommand:clean")

public class Clean implements TelnetCommand {

	@Override
	public void run(PrintWriter out, String... args) {

		out.print(new char[]{0x1B, 0x5B, 0x48, 0x1B, 0x5B, 0x4A});
	}

}
