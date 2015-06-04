package cn.mxz.base.telnet.command.user;

import java.io.PrintWriter;
import java.util.List;

import org.springframework.stereotype.Component;

import cn.mxz.FighterTemplet;
import cn.mxz.FighterTempletConfig;
import cn.mxz.IFighterTemplet;
import cn.mxz.base.telnet.CommandException;
import cn.mxz.base.telnet.TelnetCommand;
import cn.mxz.base.world.PlayerFactory;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.fighter.FighterSnapshoot;
import cn.mxz.user.Player;

/**
 * 赠送神将
 *
 * @author 林岑
 *
 */
@Component("telnetCommand:addfighterbyname")
public class AddFighterByName implements TelnetCommand {

	@Override
	public void run(PrintWriter out, String... args) {

		String fighterName = args[0];

		String c = args[1];

		String uname = args[2];

		Player player = PlayerFactory.getPlayer(uname);

		City city = CityFactory.getCity(uname);
		FighterSnapshoot s = new FighterSnapshoot(city);
		s.snapshoot();

		Integer count = new Integer(c);

		if (count > 20) {

			throw new CommandException("数量不可超过20:" + count);
		}

		if (player == null) {

			throw new CommandException("玩家不存在:" + uname);
		}

		List<FighterTemplet> fs = FighterTempletConfig.findByName(fighterName);

		if(fs.isEmpty()) {
			throw new RuntimeException("没有找到神将!" + fighterName);
		}

		for (IFighterTemplet f : fs) {

			int category = f.getCategory();

			if (category == 3 || category == 4) {

				addFighter(f.getId(), uname, count);
				out.println("添加成功:" + f.getId() + ":" + count);
			}
		}
		s.snapshoot();

	}

	private void addFighter(int typeId, String uname, int count) {

		City city = WorldFactory.getWorld().get(uname);

		for (int i = 0; i < count; i++) {

			/* Hero hero = */city.getTeam().createNewHero(typeId);

			// hero.setInTeam(false);
		}
	}
}
