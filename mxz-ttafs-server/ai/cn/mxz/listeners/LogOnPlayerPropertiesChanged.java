package cn.mxz.listeners;

import cn.mxz.city.City;
import cn.mxz.events.Listener;
import cn.mxz.events.PlayerPropertyChangeEvent;
import cn.mxz.user.Player;
import cn.mxz.util.debuger.Debuger;

//每次扣除金贝壳时, 弹出提示
public class LogOnPlayerPropertiesChanged implements
		Listener<PlayerPropertyChangeEvent> {

	@Override
	public void onEvent(PlayerPropertyChangeEvent e) {

		try {
			City c = e.getCity();
			Player p = c.getPlayer();
			Debuger.debug(c.getId() + "," + p.getNick() + ","
					+ e.getPlayerProperty() + "," + e.getNewValue() + ","
					+ e.getOldValue() + "," + e.getAdd() + "," + e.getReduce());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
