import java.util.List;
import java.util.Map;

import cn.javaplus.util.Util;
import cn.mxz.FighterTemplet;
import cn.mxz.FighterTempletConfig;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.fighter.FighterSnapshoot;
import cn.mxz.messagesender.MessageFactory;
import cn.mxz.prizecenter.PropIdCheck;
import cn.mxz.protocols.user.UserP.UserPro;
import cn.mxz.user.builder.UserBuilder;
import db.dao.impl.DaoFactory;
import db.domain.NewFighter;

public class SendReward {

	public String run() {
		return "RUN_FOUNCTION";
	}

	public void send(String user, String type, String cnt) {

		City city = getCity(user);
		if (city == null) {
			throw new RuntimeException("玩家不存在" + user);
		}
		int count = trim(cnt);

		if (count <= 0) {
			throw new RuntimeException("数量必须大于0");
		}

		FighterSnapshoot s = new FighterSnapshoot(city);
		s.snapshoot();
		
		if (type.equals("金币")) {
			type = "铜钱";

		} else if (type.equals("等级")) {

			NewFighter dto = city.getTeam().getPlayer().getDto();
			dto.addLevel(count);
			DaoFactory.getNewFighterDao().save(dto);
			city.reloadTeam();
			update(city);
			return;

		} else if (type.equals("金锭") || type.equals("金贝壳") || type.equals("贝壳")) {
			type = "金锭";
		}

		int id;
		if(type.matches("[0-9]+")) {
			
			id = new Integer(type);
		}  else {
			id = PropIdCheck.getId(type);
			id = resetFighterId(type, id);
		}
		

		city.getPrizeSender1().send(id + "," + count);
		
		city.reloadTeam();
		s.snapshoot();
		
		update(city);
	}

	private int resetFighterId(String type, int id) {
		int fighterId = getFighterId(type);
		if(fighterId != -1) {
			id = fighterId;
		}
		return id;
	}
	
	private int getFighterId(String type) {

		if (type.matches("[0-9]+")) {
			return new Integer(type);
		}

		List<FighterTemplet> fs = FighterTempletConfig.findByName(type.trim());
		for (FighterTemplet ft : fs) {
			if (ft.getCategory() == 4) {
				return ft.getId();
			}
		}
		return -1;
	}

	/**
	 * 更新客户端数据
	 * 
	 * @param city
	 */
	private void update(City city) {	
		UserBuilder bd = new UserBuilder();
		UserPro d = bd.build(city);
		MessageFactory.getUser().onUpdateUserList(city.getSocket(), d);
	}

	private int trim(String cnt) {

		cnt = cnt.replaceAll("个", "");
		cnt = cnt.replaceAll("点", "");
		cnt = cnt.replaceAll("枚", "");
		cnt = cnt.replaceAll("颗", "");
		cnt = cnt.replaceAll("匹", "");
		cnt = cnt.replaceAll("张", "");
		cnt = cnt.trim();
		
		if(!cnt.matches("[0-9]+")) {
			cnt = "" + Util.Chinese.chineseToDigit(cnt);
		}
		
		return new Integer(cnt);
	}

	/**
	 * 根据 昵称 或者 id 获取用户
	 */
	private City getCity(String user) {
		City city = CityFactory.getCity(user);
		if (city != null) {
			return city;
		}

		Map<String, String> all = WorldFactory.getWorld().getNickManager()
				.getNickAll();
		String id = all.get(user);
		if (id == null) {
			return null;
		}

		return getCity(id);
	}
}