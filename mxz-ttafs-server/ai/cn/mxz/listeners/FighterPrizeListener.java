package cn.mxz.listeners;

import java.util.List;

import cn.mxz.MissionMapTemplet;
import cn.mxz.MissionMapTempletConfig;
import cn.mxz.battle.Battle;
import cn.mxz.battle.BattleExpPrize;
import cn.mxz.battle.MissionBattle;
import cn.mxz.city.City;
import cn.mxz.events.FightingWinEvent;
import cn.mxz.events.Listener;
import cn.mxz.fighter.Fighter;
import cn.mxz.formation.AlternateFormation;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.mission.old.FighterPrizeImpl;
import cn.mxz.mission.type.GuidePlayer;
import cn.mxz.mission.type.MissionPrizeReceiver;
import cn.mxz.user.team.Formation;
import cn.mxz.user.team.god.Hero;

import com.google.common.collect.Lists;

import define.D;

public class FighterPrizeListener implements Listener<FightingWinEvent> {

	/**
	 * 在关卡奖励接收器中, 添加奖励, 不发送
	 * 
	 * @param battle
	 */
	public void generatePrizes(MissionPrizeReceiver battle) {

		// Set<Integer> set = getFighterIds(battle);

		PlayerCamp under = battle.getUnderPlayerCamp();

		City city = under.getCity();
		Formation formation = city.getFormation();
		AlternateFormation alternate = formation.getAlternate();

		List<Hero> fs = under.getFighters();

		fs = Lists.newArrayList(fs);

		for (Hero hero : under.getFighters()) { //上阵伙伴获得经验
			addExpPrize(battle, city, hero, 1);
		}

		for (Hero hero : alternate.getFighters()) {	//替补获得经验
			addExpPrize(battle, city, hero, D.TI_BU_EXP_SCALE);
		}
	}

	private void addExpPrize(MissionPrizeReceiver battle, City city,
			Fighter fighter, double expScale) {
		int exp = (int) (city.getPlayer().getLevel() * D.MISSION_EXP_RECEIVE);

		if (battle.isBoss()) {
			exp *= D.BOSS_SINGLE_COINS_X;
		}

		Hero hero = (Hero) fighter;

		final FighterPrizeImpl p = new FighterPrizeImpl();
		p.setExp((int) (exp * expScale));
		p.setFighterId(hero.getTypeId());

		battle.getFighterPrize().add(p);
	}

	// private Set<Integer> getFighterIds(MissionPrizeReceiver battle) {
	// List<Hero> fighters = battle.getUnderPlayerCamp().getFighters();
	//
	// Set<Integer> set = Sets.newHashSet();
	// for (Hero hero : fighters) {
	// if (hero.isDeath()) {
	// set.add(hero.getTypeId());
	// }
	// }
	// return set;
	// }

	/**
	 * 发放奖励给玩家
	 * 
	 * @param battle
	 */
	public void sendPrizes(MissionBattle battle) {
		List<BattleExpPrize> ps = battle.getExpPrize();
		for (BattleExpPrize b : ps) {
			b.award(battle.getUnderPlayerCamp().getCity().getPlayer());
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

			generatePrizes(battle);

			sendPrizes(battle);
		}
	}

	// /**
	// * 是否一开始就死掉了
	// *
	// * @param f
	// * @param set
	// * @return
	// */
	// private boolean isDeathStart(Hero f, Set<Integer> set) {
	// return set.contains(f.getTypeId());
	// }
}