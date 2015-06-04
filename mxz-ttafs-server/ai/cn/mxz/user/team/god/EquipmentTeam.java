package cn.mxz.user.team.god;


/**
 * 装备队伍操作
 *
 * @author PC
 *
 */

public interface EquipmentTeam {

	/**
	 * 将战士存放到库存
	 *
	 * @param fighterId 战士ID
	 *
	 * @return 移除位置
	 */
	void moveFighterToDepot(int fighterId);


	/**
	 * 将战士库存从库存中取出
	 *
	 * @param fighterId 战士ID
	 * @param position 位置
	 *
	 */
	void moveFighterToTeam(int fighterId);
}
