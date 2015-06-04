package cn.mxz.base.telnet.command.user;

import java.io.PrintWriter;

import org.springframework.stereotype.Component;

import cn.mxz.EquipmentTempletConfig;
import cn.mxz.base.telnet.CommandException;
import cn.mxz.base.telnet.TelnetCommand;
import cn.mxz.base.world.PlayerFactory;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.user.Player;

/**
 * 赠送装备
 *
 * @author 林岑
 *
 */
@Component("telnetCommand:addequipment")

public class AddEquipment implements TelnetCommand {

	@Override
	public void run(PrintWriter out, String... args) {

		int typeId = new Integer(args[0]);

		String count = args[1];

		String uname = args[2];

		Player player = PlayerFactory.getPlayer(uname);

		if(player == null) {

			throw new CommandException("玩家不存在:" + uname);
		}

		if(EquipmentTempletConfig.get(typeId) == null) {

			throw new CommandException("装备不存在:" + typeId);
		}

		addEquipment(typeId, uname, count);
	}

	private void addEquipment(int typeId, String uname, String count) {

		Integer c = new Integer(count.trim());

		City city = CityFactory.getCity(uname);

		for (int i = 0; i < c; i++) {

			city.getEquipmentManager().createNewEquipment(typeId);
		}

	}
}
