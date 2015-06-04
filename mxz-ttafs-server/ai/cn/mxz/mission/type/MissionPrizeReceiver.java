package cn.mxz.mission.type;

import java.util.List;

import cn.mxz.battle.BattleExpPrize;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.mission.old.PropPrize;

/**
 * 关卡奖励接受者
 * @author 林岑
 *
 */
public interface MissionPrizeReceiver {

	/**
	 * 是否是主线
	 * @return
	 */
	public abstract boolean isMain();

	/**
	 * 是否是Boss
	 * @return
	 */
	public abstract boolean isBoss();

	/**
	 * 奖励列表
	 * @return
	 */
	public abstract List<PropPrize> getPropPrize();

	/**
	 * 地图ID
	 * @return
	 */
	int getMapId();

	/**
	 * 战士经验奖励
	 * @return
	 */
	List<BattleExpPrize> getFighterPrize();

	/**
	 * 下方战士阵容
	 * @return
	 */
	PlayerCamp getUnderPlayerCamp();

}