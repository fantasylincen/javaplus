package cn.mxz.base.telnet.command.user;

import java.io.PrintWriter;

import org.springframework.stereotype.Component;

import cn.mxz.base.prize.PrizeSender;
import cn.mxz.base.prize.PrizeSenderFactory;
import cn.mxz.base.telnet.CommandException;
import cn.mxz.base.telnet.TelnetCommand;
import cn.mxz.base.world.PlayerFactory;
import cn.mxz.user.Player;

/**
 * 给某个玩家增加某种物品
 *
 * @author 林岑
 *
 */
@Component("telnetCommand:addreward")
public class AddReward implements TelnetCommand {

	@Override
	public void run(PrintWriter out, String... args) {

		String uname = new String(args[0].trim());
		String reward = new String(args[1].trim());
		Player player = PlayerFactory.getPlayer(uname);

		if (player == null) {

			throw new CommandException("玩家不存在:" + uname);
		}
		PrizeSender s = PrizeSenderFactory.getPrizeSender();
		s.send(player, reward);
	}
}
