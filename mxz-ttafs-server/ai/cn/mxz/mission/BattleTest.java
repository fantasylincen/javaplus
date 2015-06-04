package cn.mxz.mission;

import java.util.ArrayList;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.FighterTempletConfig;
import cn.mxz.MissionMapTemplet;
import cn.mxz.MissionMapTempletConfig;
import cn.mxz.base.service.AbstractService;
import cn.mxz.battle.Camp;
import cn.mxz.battle.CampBuilder;
import cn.mxz.fighter.Fighter;
import cn.mxz.handler.BattleService;
import cn.mxz.mission.old.demon.Demon;
import cn.mxz.mission.old.demon.DemonCampContainsBoss;
import cn.mxz.mission.old.demon.DemonImpl;
import cn.mxz.mission.type.MissionBattleImpl;
import cn.mxz.mission.type.MissionWarSituationBuilder;
import cn.mxz.protocols.user.battle.WarSituationP.CampPro;
import cn.mxz.protocols.user.mission.MissionP.MissionWarSituationPro;

import com.google.common.collect.Lists;

@Component("battleService")
@Scope("prototype")
public class BattleTest extends AbstractService implements BattleService {

	@Override
	public MissionWarSituationPro fightingTest() {
		// City city = CityFactory.getCity("lc1");

		MissionBattleImpl battle = new MissionBattleImpl(new int[] { 100004, 1, 100003, 5 }, getCity(), 1, true, false, false);
		// MissionBattleImpl battle = new MissionBattleImpl(new int[]{100004, 1,
		// 100003, 5, 100005, 3}, getCity(), 1, true, false);

		battle.fighting();

		return null;
	}

	private Camp<? extends Fighter> buildDefenderCamp() {
		MissionMapTemplet temp = MissionMapTempletConfig.get(3);
		Demon d = new DemonImpl(FighterTempletConfig.findByCategory(1).get(0), temp, false, false, getCity());
		ArrayList<Demon> ls = Lists.newArrayList(d);
		return new DemonCampContainsBoss(ls, temp);
	}

	@Override
	public CampPro getDemonCamp(int path, int index) {
		return new CampBuilder().build(buildDefenderCamp());
	}

	@Override
	public MissionWarSituationPro getWelcomeFighting() {
		MissionBattleImpl battle = new MissionBattleImpl(new int[] { 100004, 1, 100003, 5 }, getCity(), 1, true, false, false);
		// MissionBattleImpl battle = new MissionBattleImpl(new int[]{100004, 1,
		// 100003, 5, 100005, 3}, getCity(), 1, true, false);

		battle.fighting();

		return new MissionWarSituationBuilder().build(battle);
	}

}
