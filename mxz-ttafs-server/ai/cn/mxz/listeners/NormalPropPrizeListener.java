package cn.mxz.listeners;

import java.util.List;

import cn.javaplus.util.Util.Random;
import cn.mxz.MissionMapTemplet;
import cn.mxz.MissionMapTempletConfig;
import cn.mxz.base.prize.PrizeSender;
import cn.mxz.base.prize.PrizeSenderFactory;
import cn.mxz.battle.Battle;
import cn.mxz.battle.MissionBattle;
import cn.mxz.bossbattle.Prize;
import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.events.FightingWinEvent;
import cn.mxz.events.Listener;
import cn.mxz.log.Logs;
import cn.mxz.mission.type.GuidePlayer;
import cn.mxz.mission.type.MissionBattleImpl;
import cn.mxz.mission.type.MissionPrizeReceiver;
import cn.mxz.user.Player;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;

import com.google.common.collect.Lists;

import define.D;

public class NormalPropPrizeListener implements Listener<FightingWinEvent> {

	private final class PrizeTemp implements Prize {
		private final int count;

		private PrizeTemp(int count) {
			this.count = count;
		}

		@Override
		public void award(Player player) {
			player.add(PlayerProperty.CASH, count);
		}

		@Override
		public int getId() {
			return D.CASH_ID;
		}

		@Override
		public int getCount() {
			return count;
		}

		public void award(City city) {
			award(city.getPlayer());
		}
	}

	@Override
	public void onEvent(FightingWinEvent event) {

		Battle b = event.getBattle();

		if (b instanceof MissionBattle) {
			MissionBattle battle = (MissionBattle) b;

			City user = event.getCity();

			int mapId = battle.getMapId();
			MissionMapTemplet temp = MissionMapTempletConfig.get(mapId);
			GuidePlayer gp = new GuidePlayer(temp, user); // 引导玩家
			if (gp.isNew()) {
				return;
			}

			generatePrizes(user, battle);
		}
	}

	public void generatePrizes(City city, MissionPrizeReceiver battle) {

		int mapId = battle.getMapId();
		MissionMapTemplet mapTemplet = MissionMapTempletConfig.get(mapId);
		int singleCoins = mapTemplet.getSingleCoins();
		int count = singleCoins + Random.get(-5, 10);

		add(buildCashPrize(count), battle);

		PrizeSender sender = PrizeSenderFactory.getPrizeSender2();

		if (battle.isBoss()) {
			List<Prize> send2;

			String bossDropOut;
			if (battle.isMain()) {
				bossDropOut = generateMainBossPrize(mapTemplet, battle, city);
			} else {
				bossDropOut = generateLineBossPrize(mapTemplet, battle, city);
			}
			send2 = sender.buildPrizes(city.getPlayer(), bossDropOut);
			add(send2, battle);

			Logs.bossLog.addLog(send2, city);

		} else {

			String monster;
			if (battle.isMain()) {
				monster = mapTemplet.getMonsterDropOut();
			} else {
				monster = mapTemplet.getLineMonsterDropOut();
			}

			List<Prize> send1 = sender.buildPrizes(city.getPlayer(), monster);
			// Debuger.debug("NormalPropPrizeListener.onEvent() 小怪掉落::" +
			// monster
			// + " : " + send1);
			add(send1, battle);
		}
	}

	private String generateMainBossPrize(MissionMapTemplet mt,
			MissionPrizeReceiver battle, City city) {
		UserCounter uc = city.getUserCounterHistory();
		int times = uc.get(CounterKey.MAIN_BOSS_PRIZE_TIMES, battle.getMapId());
		uc.add(CounterKey.MAIN_BOSS_PRIZE_TIMES, 1, battle.getMapId());

		String dot = mt.getBossDropOut();
		String f = mt.getBossDropOutFirst();

		if (f.trim().isEmpty()) {
			return dot;
		}

		if (times != 0) {
			return dot;
		}

		return f;
	}

	private String generateLineBossPrize(MissionMapTemplet mt,
			MissionPrizeReceiver battle, City city) {
		UserCounter uc = city.getUserCounterHistory();
		int times = uc.get(CounterKey.LINE_BOSS_PRIZE_TIMES, battle.getMapId());
		uc.add(CounterKey.LINE_BOSS_PRIZE_TIMES, 1, battle.getMapId());

		String dot = mt.getLineBossDropOut();
		String f = mt.getLineBossDropOutFirst();

		if (f.trim().isEmpty()) {
			return dot;
		}

		if (times != 0) {
			return dot;
		}

		return f;
	}

	private List<Prize> buildCashPrize(final int count) {
		List<Prize> ls = Lists.newArrayList();

		ls.add(new PrizeTemp(count));

		return ls;
	}

	private void add(List<Prize> s, MissionPrizeReceiver battle) {
		for (Prize prize : s) {
			battle.getPropPrize().add(new MissionBattleImpl.MyPrize(prize));
		}
	}

}