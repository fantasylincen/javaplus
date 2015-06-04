package cn.mxz.listeners;

import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.events.Listener;
import cn.mxz.events.PlayerHeroLevelUpEvent;
import cn.mxz.user.Player;
import define.D;

/**
 * 恢复体力
 * @author 林岑
 *
 */
//玩家升级时, 体力恢复满
public class PhysicalToMax implements Listener<PlayerHeroLevelUpEvent> {


	@Override
	public void onEvent(PlayerHeroLevelUpEvent e) {

		City c = e.getCity();

		Player player = c.getPlayer();

		player.add(PlayerProperty.PHYSICAL, D.PHYSICAL_ADD_ON_LEVEL_UP);
	}
}
