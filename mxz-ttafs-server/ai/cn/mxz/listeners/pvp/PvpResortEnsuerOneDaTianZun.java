package cn.mxz.listeners.pvp;

import java.util.List;

import cn.mxz.events.Listener;
import cn.mxz.events.newpvp.PvpPlaceResortEvent;
import cn.mxz.newpvp.PvpPlayer;
import define.D;

public class PvpResortEnsuerOneDaTianZun implements
		Listener<PvpPlaceResortEvent> {

	@Override
	public void onEvent(PvpPlaceResortEvent e) {
		List<PvpPlayer> players = e.getPlayers();

		boolean isFirst = true;
		for (PvpPlayer p : players) {
			if (isFirst) {
				if (p.getDan() == D.XIAO_TIAN_ZUN_DAN) {
					p.getDto().setDanId(D.DA_TIAN_ZUN_DAN);
					p.commit();
				}
			} else {
				if (p.getDan() == D.DA_TIAN_ZUN_DAN) {
					p.getDto().setDanId(D.XIAO_TIAN_ZUN_DAN);
					p.commit();
				}
			}
			isFirst = false;
		}
	}

}
