package cn.mxz.battle.skill.attacktype;

import java.util.List;

import cn.mxz.battle.BattleCamp;
import cn.mxz.battle.skill.AttackMode;
import cn.mxz.battle.skill.FighterBeAttack;

/**
 * 攻击类型
 * @author 林岑
 *
 */
public interface AttackType {

	/**
	 * 获得该攻击类型对应该攻击的人
	 * @param attacker
	 *
	 * @param ans		受众方阵营
	 *
	 * @param position	施法者坐在阵营中的位置
	 *
	 * @return	被技能打中的目标战士列表
	 */
	List<FighterBeAttack> getFighters(BattleCamp ans, int position);

	/**
	 * 攻击模式
	 * @return
	 */
	AttackMode getAttakMode();

	/**
	 * 是否作用给队友?
	 * @return
	 */
	boolean isReleaseToFriend();
}
