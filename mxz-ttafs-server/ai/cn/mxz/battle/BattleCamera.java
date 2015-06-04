package cn.mxz.battle;

import java.util.List;

import cn.mxz.battle.buffer.Buffer;
import cn.mxz.battle.skill.FighterBeAttack;
import cn.mxz.fighter.Fighter;

/**
 *
 * 战场照相机
 * @author 林岑
 *
 */
public interface BattleCamera {

	/**
	 * 对战场作一次快照
	 *
	 * @param att		施法者
	 *
	 * @param battle	战场
	 */
	void snapshot(Fighter att, Battle battle, int skillId);

	/**
	 * 与之前快照进行对比
	 * @param bs
	 *
	 * @param battle	战场
	 * @param removes 
	 *
	 * @return	技能攻击效果
	 */
	AttackAction contrast(List<FighterBeAttack> bs, Battle battle, List<Buffer> removes);

}
