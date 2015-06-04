package cn.mxz.listeners;

import cn.mxz.battle.Battle;
import cn.mxz.events.Listener;
import cn.mxz.events.pvp.PvpBattleWinEvent;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.newpvp.PvpBattle;
import cn.mxz.newpvp.PvpManager;
import cn.mxz.newpvp.PvpPlace;
import cn.mxz.newpvp.PvpPlaceImpl;
import cn.mxz.newpvp.PvpPlayer;

public class UpdateWarsituationId implements Listener<PvpBattleWinEvent> {

	@Override
	public void onEvent(PvpBattleWinEvent event) {

		Battle b = event.getBattle();
		if (!(b instanceof PvpBattle)) {
			return ;
		}
		
		//记录最强王者争霸赛中今日连胜次数
		PlayerCamp under = b.getUnderPlayerCamp();

		PvpManager pm = under.getCity().getNewPvpManager();

		PvpPlace place = PvpPlaceImpl.getInstance();

		int od = place.getRankInAll(pm.getPlayer());

		place.resort();

		int nw = place.getRankInAll(pm.getPlayer());

		PvpPlayer player = pm.getPlayer();
		if(nw < od) {
			player.updateBestRank(nw, event.getSituationId());
		}

		int warsituationId = player.getWarsituationId();

		if(warsituationId == 0) {
			player.updateWarSituationId(event.getSituationId());
		}
	}
}
