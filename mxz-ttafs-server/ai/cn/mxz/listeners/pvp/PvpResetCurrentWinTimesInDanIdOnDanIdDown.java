package cn.mxz.listeners.pvp;

import cn.mxz.events.Listener;
import cn.mxz.events.pvp.PvpDanIdDownEvent;
import cn.mxz.newpvp.PvpPlayer;

/**
 * PVP 段位下降, 将当前段位胜利次数置为0
 * @author 林岑
 */
public class PvpResetCurrentWinTimesInDanIdOnDanIdDown implements Listener<PvpDanIdDownEvent> {

	@Override
	public void onEvent(PvpDanIdDownEvent e) {
		PvpPlayer p = 	e.getPlayer();
		p.setCurrentWinTimesInDanId(0);
	}

}
