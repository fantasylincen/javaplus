package cn.mxz.enemy;

import message.S;
import cn.javaplus.time.Time;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.friend.Request;
import cn.mxz.protocols.user.enemy.EnemyP.SystemItemPro;
import cn.mxz.user.builder.UserBaseBuilder;

public class SystemItemBuilder {

	public SystemItemPro buildFriend(Request u) {
		SystemItemPro.Builder b = SystemItemPro.newBuilder();
		b.setType(1);
		b.setSec(0);// 前端没用

		City city = CityFactory.getCity(u.getApplicant());
		b.setUser(new UserBaseBuilder().build(city.getPlayer()));

		long time = u.getRequestTime();

		long s = System.currentTimeMillis() - time;

		s /= Time.MILES_ONE_HOUR;

		if (s == 0) {
			b.setTimeText("");
		} else {
			b.setTimeText(s + S.STR10306);
		}
		return b.build();
	}

	/**
	 * 测试
	 * @return
	 */
	public SystemItemPro buildDev() {
		SystemItemPro.Builder b = SystemItemPro.newBuilder();
		b.setSec((int) (System.currentTimeMillis() / 1000));
		b.setTimeText("5分钟前");
		b.setType(1);
		b.setUser(new UserBaseBuilder().build(CityFactory.getCity("fl88").getPlayer()));
		return b.build();
	}

}
