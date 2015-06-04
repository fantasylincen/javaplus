package cn.mxz.battle.buffer;

import java.util.List;

import cn.mxz.Attribute;
import cn.mxz.battle.Battle;
import cn.mxz.battle.WarSituation;


/**
 * Buff管理器
 * @author 林岑
 */
public interface BufferManager {

	/**
	 * 在该Buff的作用下, 是否可以出手攻击
	 * @return
	 */
	boolean canHit();

	/**
	 * 清空所有buff
	 */
	void clear();

	/**
	 * 增加一个Buffer
	 * @param buffer
	 */
	void add(Buffer buffer);

	/**
	 * 今日新回合
	 * @param situation 
	 * @param currentRound 
	 * @return 被移除了的Buff
	 */
	List<Buffer> newRound(WarSituation situation, int currentRound);

	/**
	 * 所有Buffer
	 * @return
	 */
	List<Buffer> getBuffers();

	/**
	 * 所有Buff的加成
	 * @return
	 */
	Attribute getAddition();

	/**
	 * 技能前 处理
	 * @param battle
	 */
	void beforeSkill(Battle battle);

	/**
	 * 是否只能触发普通技能
	 * @return
	 */
	boolean isNormalAttackOnly();

}
