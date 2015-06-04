package cn.mxz.base.telnet.command.system.sendmessage;

import java.io.PrintWriter;

import message.S;

import org.springframework.stereotype.Component;

import cn.mxz.base.telnet.TelnetCommand;
import cn.mxz.util.message.MessageSenderToAllUp;

/**
 * 全服公告
 * @author 林岑
 *
 */
@Component("telnetCommand:sendmessagetoalluser")

public class SendMessageToAllUser implements TelnetCommand {

	@Override
	public void run(PrintWriter out, String... args) {

		String message = args[0];

		new MessageSenderToAllUp().sendMessage(S.S0, message);
	}
}
