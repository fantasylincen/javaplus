import java.util.Map;

import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.messagesender.MessageFactory;
import cn.mxz.protocols.user.UserP.UserPro;
import cn.mxz.user.builder.UserBuilder;

import com.linekong.platform.protocol.erating.server.RechargeLogic;

public class ChongZhi {

	public String run() {
		return "RUN_FOUNCTION";
	}

	/**
	 * 设置战士等级
	 */
	public void chongZhi(String user, String cnt) {
		City city = getCity(user);
		if (city == null) {
			throw new RuntimeException("玩家不存在" + user);
		}
		int count = trim(cnt);

		new RechargeLogic(city).recharge(count);

		update(city);
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