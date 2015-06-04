package cn.mxz.base.world;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import message.S;
import cn.javaplus.util.Util;
import cn.mxz.RoleNameTemplet;
import cn.mxz.RoleNameTempletConfig;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.city.City;

public class NickManager {

	/**
	 * 随机生成一个昵称 当随机次数过多, 切找不到一个不冲突的昵称时 会报出 IllegalOperationException 昵称随机失败异常
	 *
	 * @return
	 */
	public String getRandomNick() {

		Map<String, String> na = getNickAll();

		for (int i = 0; i < 200; i++) {

			List<RoleNameTemplet> all = RoleNameTempletConfig.getAll();

			RoleNameTemplet r1 = Util.Random.getRandomOne(all);
			RoleNameTemplet r2 = Util.Random.getRandomOne(all);

			String nick = r1.getFirst() + r2.getLast();

			if (na.containsKey(nick)) {
				continue;
			}

			return nick;
		}

		throw new OperationFaildException(S.S10248);
	}


	/**
	 * 所有用户的昵称 <昵称, 帐号>
	 */
	public Map<String, String> getNickAll() {

		final Map<String, String> all = new HashMap<String, String>();

		for (City city : WorldFactory.getWorld().getAll()) {

			all.put(city.getPlayer().getNick(), city.getId());
		}

		return all;
	}


}
