package cn.mxz.listeners;

import java.util.Collection;

import cn.javaplus.code.KeyGenerator;
import cn.javaplus.random.Fetcher;
import cn.javaplus.util.Util;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.events.Listener2;
import cn.mxz.events.UserCreateEvent;
import cn.mxz.user.Player;

/**
 * 为玩家生成一个随机邀请码
 *
 * @author 林岑
 *
 */
public class GenerateInvicateCodeListener implements Listener2 {

	@Override
	public void onEvent(Object e) {
		UserCreateEvent event = (UserCreateEvent) e;
		City city = event.getCity();
		String code = generate();

		Player player = city.getPlayer();
		player.setInvitationCode(code);
	}

	private String generate() {
		Collection<String> allCode = getAllCode();

		while (true) {
			String random = KeyGenerator.getRandomString(6);
			if (!allCode.contains(random)) {
				return random;
			}
		}
	}

	private Collection<String> getAllCode() {

		Collection<City> all = WorldFactory.getWorld().getAll();

		Fetcher<City, String> fetcher = new Fetcher<City, String>() {

			@Override
			public String get(City t) {
				return t.getPlayer().getInvitationCode();
			}
		};

		return Util.Collection.getListByOneFields(fetcher, all);
	}

	@Override
	public Class<?> getEventListendClass() {
		return UserCreateEvent.class;
	}

}
