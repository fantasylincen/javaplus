package cn.mxz.listeners;

import cn.javaplus.util.Util;
import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.events.Listener;
import cn.mxz.events.UserCreateEvent;
import cn.mxz.user.Player;
import cn.mxz.util.debuger.Debuger;

/**
 * 当玩家创建完毕后的 一系列处理
 * 
 * @author 林岑
 * 
 */
// 角色创建完成
public class OnCreateUserCompleteListener implements Listener<UserCreateEvent> {

	@Override
	public void onEvent(UserCreateEvent event) {

		long t = Util.Time.getCurrentTimeMillis();
		City city = event.getCity();

		try {
			Player player = city.getPlayer();
			player.set(PlayerProperty.CREATE_SEC, Util.Time.getCurrentSec());
			player.add(PlayerProperty.POWER, player.getPowerMax());
			player.add(PlayerProperty.PHYSICAL, player.getPhysicalMax());

		} catch (Exception e) {
			e.printStackTrace();
		}
		String string = "OnCreateUserCompleteListener.onEvent() 耗时";
		Debuger.debug(string + (Util.Time.getCurrentTimeMillis() - t));

	}

}
