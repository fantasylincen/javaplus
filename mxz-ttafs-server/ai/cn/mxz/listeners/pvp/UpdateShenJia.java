package cn.mxz.listeners.pvp;

import cn.mxz.events.AttributeChangeEvent;
import cn.mxz.events.Listener;
import cn.mxz.newpvp.PvpManager;
import cn.mxz.newpvp.PvpPlayer;

/**
 * 玩家属性变化时, 更新pvp战斗力缓存
 * @author 林岑
 *
 */
public class UpdateShenJia implements Listener<AttributeChangeEvent> {

	@Override
	public void onEvent(AttributeChangeEvent event) {

		PvpManager pm = event.getCity().getNewPvpManager();
		PvpPlayer player = pm.getPlayer();
		player.updateShenJia();
	}


}
