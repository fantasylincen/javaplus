package cn.mxz.mission.old;

import cn.mxz.battle.Camp;
import cn.mxz.mission.old.demon.Demon;

/**
 * 地图上的怪物
 * @author 林岑
 *
 */
public interface MapDemon extends LocationAble {

	/**
	 * 主怪物ID
	 * @return
	 */
	int getId();

	/**
	 * 是不是Boss
	 * @return
	 */
	
	boolean isBoss();

	/**
	 * 是不是地图上第一个怪物
	 * @return
	 */
	
	boolean isFirstDemon();

	/**
	 * 是不是地图上最后一个怪物
	 * @return
	 */
	
	boolean isLastDemon();

	/**
	 
	 * 这个怪物身上绑定的剧情ID1
	 * @return
	 */
	int getFirstStoryId();

	/**
	 
	 * 这个怪物身上绑定的剧情ID2
	 * @return
	 */
	int getSecondStoryId();

	Camp<Demon> getCamp();

	/**
	 
	 * 怪物组合ID
	 * @return
	 */
	int getGroupId();

}