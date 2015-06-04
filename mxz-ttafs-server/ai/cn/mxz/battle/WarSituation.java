package cn.mxz.battle;

import java.util.List;

import cn.mxz.formation.PlayerCamp;
import cn.mxz.protocols.user.battle.WarSituationP.FormationPro;


/**
 * 战况信息
 * @author 林岑
 */
public interface WarSituation {

	/**
	 * 是否胜利
	 */
	boolean isWin();

	/**
	 * 攻击动作
	 */
	List<AttackAction> getActions();

	/**
	 * 增加一个攻击动作
	 */
	void add(AttackAction attackAction);

	/**
	 * 站位数据
	 * @return
	 */
	FormationPro getFormation();

	/**
	 * 记录站位数据
	 * @param under
	 * @param upper
	 */
	void saveFormation(PlayerCamp under, BattleCamp upper);

	Battle getBattle();

	/**
	 * Buff作用效果
	 * @return
	 */
	List<BuffEffect> getBuffEffects();
	
	void addEffect(BuffEffect effect);
}
