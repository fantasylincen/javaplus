package cn.mxz.base.telnet.command.system;

import java.io.PrintWriter;
import java.util.Arrays;

import org.springframework.stereotype.Component;

import cn.mxz.base.telnet.CommandException;
import cn.mxz.base.telnet.TelnetCommand;
import cn.mxz.util.debuger.Debuger;

/**
 * 设置服务器是否允许调试模式
 *
 * @author 林岑
 *
 */
@Component("telnetCommand:setdebugerstatus")

public class SetDebugerStatus implements TelnetCommand {

	@Override
	public void run(PrintWriter out, String... args) {

		if(args[0].equals("0")) {

			Debuger.setRelease();

		} else if(args[0].equals("1")) {

			Debuger.setDebug();
		} else {

			throw new CommandException("Arg Error:" + Arrays.toString(args));
		}
	}
}
