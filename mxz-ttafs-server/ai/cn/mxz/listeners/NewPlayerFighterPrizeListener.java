package cn.mxz.listeners;

import java.util.List;

import cn.mxz.MissionMapTemplet;
import cn.mxz.MissionMapTempletConfig;
import cn.mxz.battle.Battle;
import cn.mxz.battle.MissionBattle;
import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.events.FightingWinEvent;
import cn.mxz.events.Listener;
import cn.mxz.fighter.Fighter;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.mission.old.FighterPrizeImpl;
import cn.mxz.mission.type.CashPrize;
import cn.mxz.mission.type.GuidePlayer;
import cn.mxz.user.Player;
import cn.mxz.user.team.god.Hero;

import com.google.common.collect.Lists;

import define.D;

public class NewPlayerFighterPrizeListener implements
		Listener<FightingWinEvent> {

	@Override
	public void onEvent(FightingWinEvent event) {
		Battle b = event.getBattle();
		if (b instanceof MissionBattle) {
			MissionBattle battle = (MissionBattle) b;

			City user = event.getCity();

			int mapId = battle.getMapId();
			MissionMapTemplet temp = MissionMapTempletConfig.get(mapId);
			GuidePlayer gp = new GuidePlayer(temp, user); // 引导玩家
			if (!gp.isNew()) {
				return;
			}

			addExp(battle);
			addCash(battle);
		}
	}

	private void addCash(MissionBattle battle) {
		int cash = getCash(battle);
		PlayerCamp camp = battle.getUnderPlayerCamp();
		City city = camp.getCity();
		city.getPlayer().add(PlayerProperty.CASH, cash);

		battle.getPropPrize().add(new CashPrize(cash));
	}

	private int getCash(MissionBattle battle) {
		int cash = getBaseCash(battle);
		if (battle.isBoss()) {
			cash *= D.BOSS_SINGLE_COINS_X;
		}
		return cash;
	}

	private int getBaseCash(MissionBattle battle) {
		PlayerCamp camp = battle.getUnderPlayerCamp();
		City city = camp.getCity();
		int level = city.getLevel();

		if (level == 1) {
			return D.CASH_NEW_PLAYER_LEVEL1;
		} else {
			return D.CASH_NEW_PLAYER_LEVEL2;
		}
	}

	private void addExp(MissionBattle battle) {
		PlayerCamp camp = battle.getUnderPlayerCamp();
		List<Hero> fs = camp.getFighters();
		fs = Lists.newArrayList(fs);
		int exp = getExp(battle);
		for (Fighter f : fs) {
			Hero hero = (Hero) f;

			final FighterPrizeImpl b = new FighterPrizeImpl();
			b.setExp(exp);
			b.setFighterId(hero.getTypeId());

			battle.getExpPrize().add(b);
			City city = camp.getCity();
			Player player = city.getPlayer();
			b.award(player);
		}
	}

	private int getExp(MissionBattle battle) {
		PlayerCamp camp = battle.getUnderPlayerCamp();
		City city = camp.getCity();
		int level = city.getLevel();
		int exp = (int) (level * D.MISSION_EXP_RECEIVE);
		if (battle.isBoss()) {
			exp *= D.BOSS_SINGLE_COINS_X;
		}
		return exp;
	}

//	private int getBaseExp(MissionBattle battle) {
//
//		PlayerCamp camp = battle.getUnderPlayerCamp();
//		City city = camp.getCity();
//		int level = city.getLevel();
//
//		if (level == 1) { // 1级用户 15点经验 2级用户20经验
//			return D.EXP_NEW_PLAYER_LEVEL1;
//		} else {
//			return D.EXP_NEW_PLAYER_LEVEL2;
//		}
//	}
}
