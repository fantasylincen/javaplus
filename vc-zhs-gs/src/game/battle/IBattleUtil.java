package game.battle;

import game.battle.auto.AttackInfo;
import game.fighter.FighterBase;
import game.pvp.DanGrad;
import game.skill.SkillBase;
import game.talent.TalentBase;
import game.talent.TalentType;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public interface IBattleUtil {

	/**
	 * 确定所有参战人员出手顺序的算法
	 */
	Comparator<FighterBase> getOrderComparator();

	/**
	 * 根据公式计算伤害值
	 * @param attacker
	 * @param defender
	 * @param defendTalents 
	 * @param attackTalents 
	 * @param dCaptain 
	 * @param aCaptain 
	 * @param canSkill  
	 * @param formula
	 * @param arguments
	 * @return
	 */
	AttackInfo calcAttackInfo(FighterBase attacker, FighterBase defender,
			Map<TalentType, TalentBase> attackTalents, Map<TalentType, TalentBase> defendTalents,
			List<FighterBase> aCaptain, List<FighterBase> dCaptain, 
			DanGrad attackDanGrad, DanGrad defendDanGrad, SkillBase skill  );

}
