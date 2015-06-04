package cn.mxz.listeners;

import cn.mxz.battle.Battle;
import cn.mxz.chuangzhen.ChuangZhenBattle;
import cn.mxz.chuangzhen.ChuangZhenHeadsImpl;
import cn.mxz.chuangzhen.ChuangZhenPlayer;
import cn.mxz.city.City;
import cn.mxz.events.FightingWinEvent;
import cn.mxz.events.Listener;
import cn.mxz.util.debuger.Debuger;

public class ChuangZhenWin implements Listener<FightingWinEvent> {

	@Override
	public void onEvent(FightingWinEvent event) {

		Battle b = event.getBattle();

		if (b instanceof ChuangZhenBattle) {
			ChuangZhenBattle battle = (ChuangZhenBattle) b;

			int received = battle.getStarReceived();

			Debuger.debug("ChuangZhenWin.onEvent() 闯阵得星:" + received);

			City city = battle.getUnderPlayerCamp().getCity();

			ChuangZhenPlayer p = city.getChuangZhenPlayer();

			p.addStar(received);

			p.floorUp();

			ChuangZhenHeadsImpl heads = (ChuangZhenHeadsImpl) p.getHeads();
			heads.reset();
		}
	}
}
