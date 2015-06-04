package cn.mxz.base.telnet.command.user;

import java.io.PrintWriter;

import org.springframework.stereotype.Component;

import cn.mxz.EquipmentTempletConfig;
import cn.mxz.bag.Snapsort;
import cn.mxz.bag.SuperBagSnapsort;
import cn.mxz.base.telnet.CommandException;
import cn.mxz.base.telnet.TelnetCommand;
import cn.mxz.base.world.PlayerFactory;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.prop.PropTempletFactory;
import cn.mxz.user.Player;

/**
 * 赠送道具
 *
 * @author 林岑
 *
 */
@Component("telnetCommand:addprop")

public class AddProp implements TelnetCommand {

	@Override
	public void run(PrintWriter out, String... args) {


		int typeId = new Integer(args[0]);

		String c = args[1];

		String uname = args[2];

		Player player = PlayerFactory.getPlayer(uname);

		City city = CityFactory.getCity(uname);

		Snapsort b = new SuperBagSnapsort();
		b.snapsort(city);


		if(player == null) {

			throw new CommandException("玩家不存在:" + uname);
		}

		if(PropTempletFactory.get(typeId) == null) {

			throw new CommandException("道具不存在:" + typeId);
		}

		Integer count = new Integer(c);

		addProp(typeId, uname, count);

		b.snapsort(city);
	}

	private void addProp(int id, String uname, int count) {

		City city = WorldFactory.getWorld().get(uname);

		if(EquipmentTempletConfig.get(id) != null) {

			for (int i = 0; i < count; i++) {

				city.getEquipmentManager().createNewEquipment(id);
			}

			return;
		}

		city.getBagAuto().addProp(id, count);
	}
}
