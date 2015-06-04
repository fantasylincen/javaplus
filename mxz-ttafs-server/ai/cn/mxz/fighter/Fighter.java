package cn.mxz.fighter;

import java.util.List;

import cn.mxz.Attribute;
import cn.mxz.battle.Battle;
import cn.mxz.battle.SkillInBattle;
import cn.mxz.battle.buffer.BufferManager;
import cn.mxz.equipment.Equipment;
import cn.mxz.team.Skill;

public interface Fighter {

	/**
	 * 战斗属性
	 *
	 * @return
	 */
	Attribute getAttribute();

	/**
	 * 当前气血
	 */
	int getHpNow();

	/**
	 * 战士是否死亡
	 *
	 * @return
	 */
	boolean isDeath();

	/**
	 * Buff管理器
	 *
	 * @return
	 */
	BufferManager getBufferManager();

	/**
	 * 扣血
	 *
	 * @param reduce
	 */
	void reduceHp(int reduce);

	/**
	 * 创建战士技能
	 *
	 * @return
	 */
	SkillInBattle createSkill(Battle battle);

	/**
	 * 获得所有技能(主动技能, 被动技能)
	 *
	 * @return
	 */
	List<Skill> getSkills();

	/**
	 * 加血
	 *
	 * @param i
	 */
	void addHp(int i);

	/**
	 * 最大生命值
	 *
	 * @return
	 */
	int getHpMax();

	/**
	 * 神将的品质ID 对应到GodQurlity表第一列
	 *
	 * @return
	 */
	int getQuality();

	/**
	 * 战士类型ID
	 */
	int getTypeId();

	/**
	 * 战士等级
	 */
	int getLevel();

	/**
	 * 星星数量
	 *
	 * @return
	 */
	int getStar();

	/**
	 * 最大星星数量
	 *
	 * @return
	 */
	int getStarMax();

	/**
	 * 战士身上的所有装备
	 *
	 * @return
	 */
	List<Equipment> getEquipments();

	Attribute getBaseAttribute();

	/**
	 * 普通技能ID
	 *
	 * @return
	 */
	int getNormalSkillId();

	/**
	 * 攻击别人时 , 最低伤害值
	 *
	 * @return
	 */
	int getLowerestDamage();

	/**
	 * 触发连携技能, 如果没有触发, 返回null
	 *
	 * @param battle
	 * @return
	 */
	BattleUnionSkill createUnionSkill(Battle battle);

	/**
	 * 品阶 1 丁 2 丙 3 乙 4 甲 5 甲a 6 神
	 *
	 * @return
	 */
	int getStep();

	/**
	 * 战士名字
	 * @return
	 */
	String getName();
}