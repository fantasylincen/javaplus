package cn.mxz.equipment;

import cn.mxz.Attribute;
import cn.mxz.EquipmentTemplet;
import cn.mxz.fighter.Part;
import cn.mxz.user.team.god.Hero;
import cn.mxz.user.team.god.ShenJiaAble;

/**
 * 装备
 *
 * @author 林岑
 *
 */
public interface Equipment extends ShenJiaAble {

	/**
	 * 装备加成
	 *
	 * @param currentHeroAttribute
	 * @return
	 */
	Attribute getAddition();

	/**
	 * 等级
	 *
	 * @return
	 */
	int getLevel();

	/**
	 * 卖价
	 *
	 * @return
	 */
	int getPrice();

	/**
	 * 品阶 1 丁 2 丙 3 乙 4 甲 5 甲a 6 神
	 *
	 * @return
	 */
	int getStep();

	/**
	 * 类型ID
	 *
	 * @return
	 */
	int getTypeId();

	/**
	 * 是否被装备了
	 *
	 * @return
	 */
	boolean isEquipped();

	/**
	 * 交换两个装备的位置
	 *
	 * @param e2
	 */
	void exchangePosition(Equipment e2);

	/**
	 * 品质上升一阶(操作之后 , getTypeId()会发生变化)
	 */
	void qualityLevelUp();

	/**
	 * 装备ID
	 *
	 * @return
	 */
	Integer getId();

	/**
	 * 加成类型（0气血 1物攻 2法强 3物防 4法抗 5速度）
	 *
	 * @return
	 */
	int getAdditionType();

	/**
	 * 获得当前装备在哪个英雄身上
	 *
	 * @return
	 */
	Hero getHero();

	/**
	 * 历史强化所消耗的钱
	 *
	 * @return
	 */
	int getPriceLevelUpHistory();

	/**
	 * 升级所需金币
	 * @return
	 */
	int getLevelUpCashNeed();

	/**
	 * 该装备在那个战士身上
	 * @return
	 */
	int getHeroId();

	/**
	 * 套装ID
	 * @return
	 */
	int getSuitId();

	/**
	 * 装备模板
	 * @return
	 */
	EquipmentTemplet getTemplet();

	/**
	 * 装备部位
	 * @return
	 */
	Part getPart();

	
	Attribute getBase();

}
