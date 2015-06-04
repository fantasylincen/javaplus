package cn.mxz.base.telnet.command.user;

import java.io.PrintWriter;

import org.springframework.stereotype.Component;

import cn.mxz.FighterTempletConfig;
import cn.mxz.IFighterTemplet;
import cn.mxz.base.telnet.CommandException;
import cn.mxz.base.telnet.TelnetCommand;
import cn.mxz.base.world.PlayerFactory;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.spirite.SpiriteManager;
import cn.mxz.user.Player;

/**
 * 赠送魂魄
 *
 * @author 林岑
 *
 */
@Component("telnetCommand:addspirite")

public class AddSpirite implements TelnetCommand {

	@Override
	public void run(PrintWriter out, String... args) {

		int typeId = new Integer(args[0]);

		String c = args[1];

		String uname = args[2];

		Player player = PlayerFactory.getPlayer(uname);

		Integer count = new Integer(c);

		if(count > 20) {

			throw new CommandException("数量不可超过20:" + count);
		}

		if(player == null) {

			throw new CommandException("玩家不存在:" + uname);
		}

		IFighterTemplet fighterTemplet = FighterTempletConfig.get(typeId);

		if(fighterTemplet == null) {

			throw new CommandException("神将不存在:" + typeId);
		}

		int category = fighterTemplet.getCategory();

		if(!(category == 3 || category == 4)) {

			throw new CommandException("只能携带 category == 3 || 4 的神将! typeId = " + typeId + " category = " + category);
		}

		addSpirite(typeId, uname, count);
	}

	private void addSpirite(int typeId, String uname, int count) {

		City city = WorldFactory.getWorld().get(uname);

		for (int i = 0; i < count; i++) {

			SpiriteManager s = city.getSpiriteManager();

			s.add(typeId);
		}
	}
}
