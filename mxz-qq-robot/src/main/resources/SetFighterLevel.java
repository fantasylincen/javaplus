import java.util.List;
import java.util.Map;

import cn.mxz.FighterTemplet;
import cn.mxz.FighterTempletConfig;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.fighter.FighterSnapshoot;
import cn.mxz.handler.UserService;
import cn.mxz.messagesender.MessageFactory;
import cn.mxz.protocols.user.UserP.UserPro;
import cn.mxz.user.builder.UserBuilder;
import cn.mxz.user.team.god.Hero;
import db.dao.impl.DaoFactory;

public class SetFighterLevel {

	public String run() {
		return "RUN_FOUNCTION";
	}

	/**
	 * 设置战士等级
	 */
	public void setLevel(String user, String type, String cnt) {
		City city = getCity(user);
		if (city == null) {
			throw new RuntimeException("玩家不存在" + user);
		}
		int count = trim(cnt);
		if (count < 0 || count > 100) {
			throw new RuntimeException("数量必须大于0 小于 101");
		}
		int id = getFighterId(type);

		Hero hero = city.getTeam().get(id);
		if (hero == null) {
			throw new RuntimeException("玩家没有这个神将:" + type);
		}

		FighterSnapshoot s = new FighterSnapshoot(city);
		s.snapshoot();

		hero.getDto().setLevel(count);
		DaoFactory.getNewFighterDao().update(hero.getDto());
		s.snapshoot();

		update(city);
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
		throw new RuntimeException("未找到这个神将:" + type);
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