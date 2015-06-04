package cn.mxz.listeners;

import java.util.List;

import cn.mxz.DogzTemplet;
import cn.mxz.DogzTempletConfig;
import cn.mxz.battle.Battle;
import cn.mxz.battle.BattleCamp;
import cn.mxz.battle.SkillInBattle;
import cn.mxz.battle.skill.FighterBeAttack;
import cn.mxz.dogz.Dogz;
import cn.mxz.events.BeAttackEvent;
import cn.mxz.events.Listener;
import cn.mxz.fighter.Fighter;
import define.D;

//神兽怒气增加
public class DogzAddSpListener implements Listener<BeAttackEvent> {


	@Override
	public void onEvent(BeAttackEvent event) {
//		Debuger.debug("DogzAddSpListener.onEvent()" + event.isUpperBeAttack());
		if (enemyHasBeAttack(event)) {
			Dogz dogz = getDogzBeAttack(event);
//			Debuger.debug("DogzAddSpListener.onEvent()" + dogz);
			if (dogz != null) {
				SkillInBattle skill = event.getSkill();
				int id = skill.getId();

				DogzTemplet t = DogzTempletConfig.get(dogz.getTypeId());
				int s = t.getCommonSkill();

//				Debuger.debug("DogzAddSpListener.onEvent()" + dogz + " addAngry" + event.getAttacker().getName());
				
				if (s == id) {
					dogz.addAngry(D.DOGZ_ANGRY_EVERY_TIME_ATTACK);
				} else {
					dogz.addAngry(D.DOGZ_ANGRY_EVERY_TIME_SKILL);
				}
			}
		}
	}

	/**
	 * 获得被击方神兽
	 * 
	 * @return
	 */
	private Dogz getDogzBeAttack(BeAttackEvent event) {
		BattleCamp camp = getFriend(event);
		return camp.getDogz();
	}

	private boolean enemyHasBeAttack(BeAttackEvent event) {
		List<FighterBeAttack> beAttacks = event.getBeAttacks();
		Fighter attacker = event.getAttacker();
//		Debuger.debug("DogzAddSpListener.enemyHasBeAttack()" + attacker.getName());
		Battle battle = event.getBattle();
		BattleCamp enemy = battle.getEnemy(attacker);
		if (beAttacks.isEmpty()) {
			return false;
		}
		return enemy == getFriend(event);
	}

	private BattleCamp getFriend(BeAttackEvent event) {
		List<FighterBeAttack> beAttacks = event.getBeAttacks();
		Battle battle = event.getBattle();
		FighterBeAttack defender = beAttacks.get(0);
		BattleCamp friends = battle.getFriends(defender.getTarget());
		return friends;
	}

}
