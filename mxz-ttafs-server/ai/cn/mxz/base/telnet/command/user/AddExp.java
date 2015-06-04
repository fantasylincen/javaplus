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
import cn.mxz.city.CityFactory;
import cn.mxz.user.Player;
import cn.mxz.user.team.god.Hero;

/**
 * 赠送经验
 *
 * @author 林岑
 *
 */
@Component("telnetCommand:addexp")
public class AddExp implements TelnetCommand {

	@Override
	public void run(PrintWriter out, String... args) {

		String uname = args[0];

		int typeId = new Integer(args[1]);

		int add = new Integer(args[2]);

		Player player = PlayerFactory.getPlayer(uname);

		if (player == null) {

			throw new CommandException("玩家不存在:" + uname);
		}

		IFighterTemplet fighterTemplet = FighterTempletConfig.get(typeId);

		if (fighterTemplet == null) {

			throw new CommandException("神将不存在:" + typeId);
		}

		City city = CityFactory.getCity(uname);

		Hero hero = city.getTeam().get(typeId);

		if (hero == null) {

			throw new CommandException("神将不存在:" + typeId);
		}

		hero.addExp(add);
	}

	private void addFighter(int typeId, String uname, int count) {

		City city = WorldFactory.getWorld().get(uname);

		for (int i = 0; i < count; i++) {

			/* Hero hero = */city.getTeam().createNewHero(typeId);

			// hero.setInTeam(false);
		}
	}
}
