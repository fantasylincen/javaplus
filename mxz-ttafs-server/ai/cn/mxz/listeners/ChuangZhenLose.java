package cn.mxz.listeners;

import cn.mxz.battle.Battle;
import cn.mxz.chuangzhen.ChuangZhenBattle;
import cn.mxz.chuangzhen.ChuangZhenHeadsImpl;
import cn.mxz.chuangzhen.ChuangZhenPlayer;
import cn.mxz.city.City;
import cn.mxz.events.FightingLoseEvent;
import cn.mxz.events.Listener;

public class ChuangZhenLose implements Listener<FightingLoseEvent> {

	@Override
	public void onEvent(FightingLoseEvent event) {
		Battle b = event.getBattle();
		if (b instanceof ChuangZhenBattle) {
			ChuangZhenBattle battle = (ChuangZhenBattle) b;
			City city = battle.getUnderPlayerCamp().getCity();
			ChuangZhenPlayer p = city.getChuangZhenPlayer();
			p.reset();
			ChuangZhenHeadsImpl heads = (ChuangZhenHeadsImpl) p.getHeads();
			heads.reset();
		}
	}
}
