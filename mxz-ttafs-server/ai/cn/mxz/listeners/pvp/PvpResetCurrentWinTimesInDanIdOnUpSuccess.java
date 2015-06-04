package cn.mxz.listeners.pvp;

import cn.mxz.events.Listener;
import cn.mxz.events.pvp.PvpUpSuccessEvent;
import cn.mxz.newpvp.PvpPlayer;

/**
 * PVP 晋级成功   将当前段位胜利次数置为0
 * @author 林岑
 */
public class PvpResetCurrentWinTimesInDanIdOnUpSuccess implements Listener<PvpUpSuccessEvent> {

	@Override
	public void onEvent(PvpUpSuccessEvent e) {
		PvpPlayer p = 	e.getPlayer();
		p.setCurrentWinTimesInDanId(0);
	}

}
