package cn.mxz.battle;

import java.util.List;

import cn.mxz.BuffTempletConfig;
import cn.mxz.ISkillTemplet;
import cn.mxz.SkillTempletConfig;
import cn.mxz.city.City;
import cn.mxz.city.FailEvent;
import cn.mxz.protocols.user.battle.WarSituationP.ActionPro;
import cn.mxz.protocols.user.battle.WarSituationP.BuffEffectPro;
import cn.mxz.protocols.user.battle.WarSituationP.BuffEffectPro.Builder;
import cn.mxz.protocols.user.battle.WarSituationP.SkillPointPro;
import cn.mxz.protocols.user.battle.WarSituationP.WarSituationPro;
import cn.mxz.util.debuger.Debuger;

public class WarSituationBuilder {

	public WarSituationPro build(City city, WarSituation ws) {

		final WarSituationPro.Builder b = WarSituationPro.newBuilder();

		b.setIsWin(ws.isWin());

		b.setIsLose(!ws.isWin());

		b.setFormation(ws.getFormation());

		for (AttackAction a : ws.getActions()) {

			b.addActions(buildActions(a));
		}
		
		for (BuffEffect a : ws.getBuffEffects()) {

			b.addEffects(buildBuffEffect(a));
		}
		

		// Battle bt = ws.getBattle();

		int[] calcResult = new FailEvent(city, ws).calcResult();
		for (int i : calcResult) {
			b.addFaildGuide(i);
		}

		return b.build();
	}


	private BuffEffectPro buildBuffEffect(BuffEffect a) {
		Builder b = BuffEffectPro.newBuilder();
		b.setAttack(a.getAttack());
		b.setBufferId(a.getBufferId());
		b.setCrit(a.getCrit());
		b.setDefend(a.getDefend());
		b.setDodge(a.getDodge());
		b.setHp(a.getHp());
		b.setIsUnder(a.isUnder());
		b.setMAttack(a.getmAttack());
		b.setMDefend(a.getmDefend());
		b.setPosition(a.getPosition());
		b.setRound(a.getRound());
		b.setSpeed(a.getSpeed());
		return b.build();
	}


	private ActionPro buildActions(AttackAction aa) {

		final ActionPro.Builder b = ActionPro.newBuilder();

		b.setIsAttackerUnder(aa.isAttackerUnder());

		b.setSkillId(aa.getSkillId());

		b.setAttackerPosition(new AttackerPositionBuilder().build(aa));

		b.setRound(aa.getRound());

//		Debuger.debug(" 战斗回合 WarSituationBuilder.buildActions() round = "
//				+ aa.getRound());

		int udV = aa.getUnderDogzAngryValue();
		b.setUnderDogzAngryValue(udV);
		int upV = aa.getUpperDogzAngryValue();
		b.setUpperDogzAngryValue(upV);

		b.setIsCounterAttack(aa.isCounterAttack());

		List<BuffDisappear> bufs = aa.getBuffDisappears();

		for (BuffDisappear bd : bufs) {

			b.addBuffDisappears(new BuffDisapearProBuilder().build(bd));
		}

		for (SkillPoint a : aa.getSkillPoints()) {

			SkillPointPro p = buildSkillPoint(a);

			b.addPoint(p);
		}

		int skillId = aa.getSkillId();

		ISkillTemplet temp = SkillTempletConfig.get(skillId);

		if (temp == null) {
			b.setHasLethality(1);
		} else {
			b.setHasLethality((temp.getReleaseType() == 1) ? 1 : 0);
		}

		return b.build();
	}

	private SkillPointPro buildSkillPoint(SkillPoint a) {

		final SkillPointPro.Builder b = SkillPointPro.newBuilder();

		b.setIsCrit(a.isCrit());

		b.setIsBlock(a.isBlock());

		b.setIsHit(a.isHit());

		b.setHp(a.getHpEnd());

		b.setPosition(a.getPosition());

		b.setIsUnder(a.isUnder());

		b.setAttack(a.getAttack());

		b.setDefend(a.getDefend());

		b.setMAttack(a.getMAttack());

		b.setMDefend(a.getMDefend());

		b.setCrit(a.getCrit());

		b.setDodge(a.getDodge());

		int bufferId = a.getBufferId();

		if (bufferId != -1) {
			Debuger.debug("WarSituationBuilder.buildSkillPoint() 生成 buffer:" +  BuffTempletConfig.get(bufferId).getName() + ", position = " + a.getPosition());
		}
		
		b.setBufferId(bufferId);

		b.setSpeed(a.getSpeed());

		return b.build();
	}
}
