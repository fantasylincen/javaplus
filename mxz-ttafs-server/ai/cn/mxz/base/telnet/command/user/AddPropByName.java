package cn.mxz.base.telnet.command.user;

import java.io.PrintWriter;
import java.util.List;

import org.springframework.stereotype.Component;

import cn.mxz.EquipmentTempletConfig;
import cn.mxz.PropTemplet;
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
@Component("telnetCommand:addpropbyname")
public class AddPropByName implements TelnetCommand {

	@Override
	public void run(PrintWriter out, String... args) {

		String propName = args[0];

		String c = args[1];

		String uname = args[2];

		Player player = PlayerFactory.getPlayer(uname);

		City city = CityFactory.getCity(uname);

		Snapsort b = new SuperBagSnapsort();
		b.snapsort(city);

		if (player == null) {

			throw new CommandException("玩家不存在:" + uname);
		}

		List<PropTemplet> all = PropTempletFactory.findByName(propName);

		for (PropTemplet pt : all) {

			Integer count = new Integer(c);

			addProp(pt.getId(), uname, count);

			out.print(pt.getId() + ", " + count + "个 添加成功!");
		}

		b.snapsort(city);
	}

	private void addProp(int id, String uname, int count) {

		City city = WorldFactory.getWorld().get(uname);

		if (EquipmentTempletConfig.get(id) != null) {

			for (int i = 0; i < count; i++) {

				city.getEquipmentManager().createNewEquipment(id);
			}

			return;
		}

		city.getBagAuto().addProp(id, count);
	}
}
