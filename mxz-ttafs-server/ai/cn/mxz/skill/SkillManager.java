package cn.mxz.skill;

import java.util.List;

import cn.mxz.team.Skill;
import cn.mxz.user.team.god.Hero;

public interface SkillManager {

	/**
	 * 所有可装备的技能
	 * @return
	 */
	List<Skill> getSkillsCanEquip();

	/**
	 * 获得指定ID的技能
	 * @param skillId
	 * @return
	 */
	List<Skill> getSkills(int skillId);

	/**
	 * 将指定的技能装备到指定的战士身上去
	 * @param fighterTypeId
	 * @param skillId
	 */
	void equipmentToFighter(int fighterTypeId, int skillId);

	/**
	 * 卸下技能
	 * @param oldId
	 */
	void tackOff(int oldId);

	/**
	 * 获得指定英雄的所有技能
	 * @param hero
	 * @return
	 */
	List<Skill> getSkills(Hero hero);

	/**
	 * 移除这个技能
	 * @param id 技能唯一ID
	 */
	void removeByIds(Integer id);

	/**
	 * 添加一个技能
	 * @param typeId
	 * @param count
	 */
	Skill add(int typeId);

	/**
	 * 增加一批技能
	 * @param all
	 */
	List<Skill> add(List<Integer> all);

	/**
	 * 合成一个技能
	 * @param id
	 * @return
	 */
	Skill generate(int id);

	/**
	 * 根据技能唯一标识符获取某个技能
	 * @param id
	 * @return
	 */
	Skill getByIdentification(int id);

	/**
	 * 所有技能
	 * @return
	 */
	List<Skill> getAll();

	/**
	 * 保证这个战士有天赋技能
	 * @param hero
	 */
	void ensureTianFu(Hero hero);

	/**
	 * 所有未装备 的技能
	 * @return
	 */
	List<Skill> getSkillsUnEquipment();

	List<Skill> getSkillsByFighterId(int fighterId);

}
