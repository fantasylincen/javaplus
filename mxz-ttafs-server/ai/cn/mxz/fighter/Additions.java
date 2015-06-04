package cn.mxz.fighter;

import cn.mxz.Attribute;


/**
 * 英雄所有加成
 * @author 林岑
 *
 */
public interface Additions {

	/**
	 * 所有加成总计值
	 * @return
	 */
	Attribute getAll();

	/**
	 * 神兽加成
	 * @return
	 */
	Attribute getDogzAddition();

	/**
	 * 神兽技能加成
	 * @return
	 */
	Attribute getDogzSkillAddition();

//	/**
//	 * 连携技能加成
//	 * @return
//	 */
//	List<Attribute> getUnionSkillAddition();

	/**
	 * 装备加成
	 * @return
	 */
	Attribute getEquipmentAddition(Attribute nowHeroAttribute);

//	/**
//	 * PVP 加成
//	 * @return
//	 */
//	Attribute getPvpAddition();

	/**
	 * 基础加成
	 * @return
	 */
	Attribute getBase();
	
	/**
	 * 基础加成
	 * 人物（等级+进阶），装备主属性，装备套装属性，元神
	 */
	Attribute getBase2();

	/**
	 * 元神加成
	 * @return
	 */
	Attribute getYuanShenAddition();

	/**
	 * 天命加成
	 * @return
	 */
	Attribute getTianMingAddition();


}
