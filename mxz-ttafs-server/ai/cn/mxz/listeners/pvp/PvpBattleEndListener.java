package cn.mxz.listeners.pvp;

import java.util.List;

import cn.mxz.battle.Battle;
import cn.mxz.city.City;
import cn.mxz.events.FightEndEvent;
import cn.mxz.events.Listener;
import cn.mxz.newpvp.PvpBattle;
import cn.mxz.newpvp.PvpManager;
import cn.mxz.newpvp.PvpPlaceImpl;
import cn.mxz.newpvp.PvpPlayer;
import cn.mxz.newpvp.PvpPlayerProperties;
import cn.mxz.script.Script;
import cn.mxz.util.debuger.Debuger;
import db.domain.PvpExtra;
import define.D;

public class PvpBattleEndListener implements Listener<FightEndEvent> {


	@Override
	public void onEvent(FightEndEvent e) {

		Battle b = e.getBattle();
		
		if (!(b instanceof PvpBattle)) {
			return ;
		}
		
		PvpBattle battle = (PvpBattle) b;

		boolean isWin = battle.isWin();

		City under = getUnder(e);

		City upper = getUpper(e);

		PvpManager pm = under.getNewPvpManager();

		PvpPlayer player = pm.getPlayer();

		if (player.isInUpMatch()) {

			player.upWatchEnd(isWin);

			checkResult(battle, under,upper, player, 3, 2); // 3场2胜

			// 普通赛事
		} else {

			if (isWin) {

				// 胜点
				player.normalWatchWin(battle);

			}
			if (player.canEnterUpWatch()) {

				player.enterUpWatch();
			}
		}

		record(isWin, under, player, upper);
	}

	private City getUpper(FightEndEvent e) {
		Battle source = e.getBattle();
		City under = source.getUpperPlayerCamp().getCity();
		return under;
	}

	private void checkResult(PvpBattle battle, City under, City upper, PvpPlayer player, int all, int win) {

		if (canLevelUp(player, win)) { // 可以晋级了

			player.levelUp();

			player.toNormal();

//			player.sendPrize();

			battle.setUp(true);
			battle.setLastUpMatch(true);
			
			return;
		}

		int fightingTimes = player.getFightingTimes();
		if (fightingTimes >= all) {// 晋级结束了

			player.upWatchFaild(upper);
		
			battle.setLastUpMatch(true);

			Debuger.debug("PvpBattleEndListener.checkResult() 晋级失败!" + under.getId());
			return;
		}
	}

	/**
	 * 达到晋级条件
	 * @param player
	 * @param win
	 * @return
	 */
	private boolean canLevelUp(PvpPlayer player, int win) {
		
		List<PvpPlayer> tianZuns = PvpPlaceImpl.getInstance().getTianZuns();
		
		int dan = player.getDan();
		
		if(dan == D.XIAO_TIAN_ZUN_DAN - 1 && tianZuns.size() >= 10) {  //必须要比最后一个天尊的胜率大, 才可以晋级
			PvpPlayer last = tianZuns.get(tianZuns.size() - 1);
			float percent = last.getWinPercent();
			if(player.getWinPercent() < percent) {
				return false;
			}
		}
		
		int winTimesInUpWatch = player.getWinTimesInUpWatch();
		return winTimesInUpWatch >= win;
	}

	/**
	 * 记录战绩
	 *
	 * @param isWin
	 * @param under
	 * @param player
	 * @param upper
	 */
	private void record(boolean isWin, City under, PvpPlayer player, City upper) {

		if (isWin) {

			// 胜利数

			add(player.getExtraDto(), 1, PvpPlayerProperties.PVP_WINNING_STREAK_COUNT);

		} else {

			
			PvpExtra dto = player.getExtraDto();
			dto.setV(PvpPlayerProperties.PVP_WINNING_STREAK_COUNT.toNum(), 0);

			if(!player.isProtected()) { //剩余保护次数小于等于0  扣除胜点

				if(player.isInUpMatch() ) { //在晋级赛时, 输了不扣胜点
					return;
				}
				int danId = upper.getNewPvpManager().getPlayer().getDan();
				int dan = player.getCity().getNewPvpManager().getPlayer().getDan();
				player.reducePractice((int) Script.Pvp.getWinPointsLose(danId, dan)); //只降级, 不会降段
			}
		}

		player.commit();
	}

	private void add(PvpExtra um, int i, PvpPlayerProperties v) {
		int o = um.getV(v.toNum());
		int now = o + i;
		um.setV(v.toNum(), now);
	}

	private City getUnder(FightEndEvent e) {
		Battle battle = e.getBattle();
		City under = battle.getUnderPlayerCamp().getCity();
		return under;
	}

}
