package game.battle.formation;


import java.util.List;

import config.skill.accord.ChooseFighters;

import game.fighter.FighterBase;

public interface IFormation {

	/**
	 * 获取所有的战士
	 * @return
	 */
	List<FighterBase> 		getAllFighters();
	
	  /**
     * 获取死亡英雄列表
     * @return
     */
	List<FighterBase> 		getDieFighters();
	
	/**
	 * 获取普通攻击时应该被攻击的玩家
	 * @param attacker	攻方
	 * @return
	 */
	FighterBase getBaseDefender( FighterBase attacker );
	
	/**
	 * 根据类型来得到相应的受到效果的战士列表
	 *
     * @param attacker      攻方
     * @param type          选择类型
     * @param all           按出手速度排序的所有的战士列表
     * @return
	 */
	List<FighterBase> getFighterOnEffect( FighterBase attacker, ChooseFighters type );
	
	/**
	 * 是否所有的战士都已经死亡
     * @return
     *          true for all death
	 */
	boolean isAllDie();
	
}
