package cn.mxz.base.telnet.command.system.sendmessage;

import java.io.PrintWriter;

import message.S;

import org.springframework.stereotype.Component;

import cn.mxz.base.telnet.TelnetCommand;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;

/**
 * 发送某条消息给一个人
 * @author 林岑
 *
 */
@Component("telnetCommand:sendmessagetooneuser")

public class SendMessageToOneUser implements TelnetCommand {

	@Override
	public void run(PrintWriter out, String... args) {

		String userId = args[0];

		String message = args[1];

		City city = CityFactory.getCity(userId);

		city.getMessageSender().send(S.S0, message);
	}
}
