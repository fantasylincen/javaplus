package cn.mxz.base.telnet.command.user;

import java.io.PrintWriter;

import org.springframework.stereotype.Component;

import cn.mxz.bag.Snapsort;
import cn.mxz.bag.SuperBagSnapsort;
import cn.mxz.base.telnet.CommandException;
import cn.mxz.base.telnet.TelnetCommand;
import cn.mxz.base.world.PlayerFactory;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.city.PlayerProperty;
import cn.mxz.user.Player;

/**
 * 给某个玩家增加某种物品
 *
 * @author 林岑
 *
 */
@Component("telnetCommand:add")

public class AddSomeThing implements TelnetCommand {

	@Override
	public void run(PrintWriter out, String... args) {

		String id = args[0].trim();

		String uname = args[1];

		City city = CityFactory.getCity(uname);

		Snapsort b = new SuperBagSnapsort();
		b.snapsort(city);

		Player player = PlayerFactory.getPlayer(uname);

		int add = new Integer(args[2]);

		if(player == null) {

			throw new CommandException("玩家不存在:" + uname);
		}

		if(id.equals("1001") ) {
			player.addGiftGold(add);

		}else	if(id.equals("QI_SE_JING_SHI") ) {
			city.getHeishiManager().addQsjs(add);
		} else {
			player.add(PlayerProperty.value(id), add);
		}


		b.snapsort(city);
	}
}
