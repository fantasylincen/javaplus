package cn.mxz.listeners.city;

import cn.javaplus.plugins.generator.excel.generator.Debuger;
import cn.mxz.events.Listener;
import cn.mxz.events.PlayerHeroLevelUpEvent;

public class LogPlayerLevelUp implements Listener<PlayerHeroLevelUpEvent> {

	@Override
	public void onEvent(PlayerHeroLevelUpEvent e) {

		String nick = e.getCity().getPlayer().getNick();
		Debuger.debug("LogPlayerLevelUp.onEvent() level up:" + nick + "|"
				+ e.getCity().getId() + "|" + e.getLevel());
	}

}
