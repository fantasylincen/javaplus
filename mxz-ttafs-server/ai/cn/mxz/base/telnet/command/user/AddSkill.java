package cn.mxz.base.telnet.command.user;

import java.io.PrintWriter;

import org.springframework.stereotype.Component;

import cn.mxz.ISkillTemplet;
import cn.mxz.SkillTempletConfig;
import cn.mxz.base.telnet.CommandException;
import cn.mxz.base.telnet.TelnetCommand;
import cn.mxz.base.world.PlayerFactory;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.skill.SkillManager;
import cn.mxz.user.Player;

/**
 * 赠送技能
 *
 * @author 林岑
 *
 */
@Component("telnetCommand:addskill")

public class AddSkill implements TelnetCommand {

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

		ISkillTemplet temp = SkillTempletConfig.get(typeId);
		
		if(temp == null) {
			throw new CommandException("技能不存在:" + typeId);
		}
		City city = CityFactory.getCity(uname);
		
		SkillManager manager = city.getSkillManager();
		
		manager.add(typeId);
		
	}

}
