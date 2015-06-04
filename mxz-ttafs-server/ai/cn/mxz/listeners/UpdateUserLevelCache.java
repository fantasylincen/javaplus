package cn.mxz.listeners;

import cn.mxz.city.PlayerProperty;
import cn.mxz.events.Listener;
import cn.mxz.events.PlayerHeroLevelUpEvent;
import cn.mxz.user.Player;

/**
 * 更新玩家等级缓存
 * @author 林岑
 */
public class UpdateUserLevelCache implements Listener<PlayerHeroLevelUpEvent> {

	@Override
	public void onEvent(PlayerHeroLevelUpEvent event) {

		int level = event.getLevel();
		Player p = event.getCity().getPlayer();
		p.set(PlayerProperty.USER_LEVEL_CACHE, level);
	}

}
