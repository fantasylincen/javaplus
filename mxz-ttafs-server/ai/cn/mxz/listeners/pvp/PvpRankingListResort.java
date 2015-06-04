package cn.mxz.listeners.pvp;

import cn.mxz.events.Listener;
import cn.mxz.events.RateSystemEvent;
import cn.mxz.newpvp.PvpPlaceImpl;

//定时排序
public class PvpRankingListResort implements Listener<RateSystemEvent> {

	@Override
	public void onEvent(RateSystemEvent e) {
		PvpPlaceImpl.getInstance().resort();
	}

}
