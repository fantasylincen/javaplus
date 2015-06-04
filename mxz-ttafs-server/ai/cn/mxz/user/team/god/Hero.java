package cn.mxz.user.team.god;

import java.util.List;

import cn.javaplus.math.Fraction;
import cn.mxz.city.City;
import cn.mxz.city.EmptyGrid;
import cn.mxz.equipment.Equipment;
import cn.mxz.fighter.Additions;
import cn.mxz.fighter.Fighter;
import cn.mxz.fighter.HeroProperty;
import cn.mxz.fighter.SkillTianMing;
import cn.mxz.team.HeroTianMing;
import cn.mxz.team.builder.EquipmentTianMing;
import cn.mxz.team.builder.UnionSkill;
import cn.mxz.tianming.WuXingTianMing;
import db.domain.NewFighter;

/**
 *
 * 英雄
 * @author 	林岑
 * @since	2013年6月3日 18:14:26
 *
 */
public interface Hero extends Fighter, ShenJiaAble{

	/**
	 * 神将累计获得经验值
	 */
	Fraction getExp();

	/**
	 * @return	历史获得总经验
	 */
	int getExpAll();

	/**
	 * 增加神将经验, 增加之后, 等级可能发生变化
	 * @param count
	 */
	void addExp(int count);

	/**
	 * 增加一次传承次数
	 */
	void addInheritCount();

	/**
	 * 传承次数
	 * @return
	 */
	int getInheritCount();

	/**
	 * 最大等级
	 * @return
	 */
	int getMaxLevel();

	/**
	 * 被触发的连携技能
	 * @return
	 */
	List<UnionSkill> getUnionSkills();

	/**
	 * 战士所有的加成
	 * @return
	 */
	Additions getAdditions();

//	/**
//	 * 剩余复活时间
//	 * @return
//	 */
//	int getReviseRemainSec();

	/**
	 * 提交至数据库
	 */
	void commit();

	/**
	 * 法力
	 * @return
	 */
	Fraction getFaLi();

	/**
	 * 所属城池
	 * @return
	 */
	City getCity();

	/**
	 * 元神管理器
	 * @return
	 */
	YuanShenManager getYuanShenManager();

	/**
	 * 战士天命
	 * @return
	 */
	HeroTianMing getTianMing();

	/**
	 * 获得当前装备在英雄身上的所有专属装备
	 * @return
	 */
	List<Equipment> getZhuanShuEquipments();

	/**
	 * 获得战士的装备天命
	 * @return
	 */
	EquipmentTianMing getEquipmentTianMing();

	NewFighter getDto();

	int get(HeroProperty property);

	String getName();

	SkillTianMing getSkillTianMing();

	/**
	 * 天赋技能
	 * @return
	 */
	ITianFuSkill getTianFuSkill();

	List<EmptyGrid> getEmptyGrids();

	/**
	 * 是否是主角神将
	 * @return
	 */
	boolean isPlayer();

	/**
	 * 战士品阶上升一级
	 */
	void qualityLevelUp();

	/**
	 * 五行天命
	 * @return
	 */
	WuXingTianMing getWuXingTianMing();

	int getShenJiaBase();
}
