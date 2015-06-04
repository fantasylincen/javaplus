package cn.mxz.user.mission;

import cn.mxz.mission.old.GiveUpAble;

/**
 * 玩家副本信息
 *
 * @author 	林岑
 * @since	2013年6月4日 17:43:38
 *
 */
public interface Mission extends GiveUpAble{
//
//	/**
//	 * 通关到下一关
//	 */
//	void next();

	/**
	 * 副本标记
	 * @return
	 */
	
	MissionMark getMark();
//
//	/**
//	 * 当前副本地图
//	 */
//	MissionMap getCurrentMap();
//
//	/**
//	 * 是否选定了地图
//	 * @return
//	 */
//	boolean hasMap();
//
//	/**
//	 * 构建指定剧情ID的副本
//	 * @param storyId
//	 */
//	void buildMap(int storyId);
//
//	/**
//	 * 地图走完时
//	 * @param markBeforePersonMove 人物移动之前的关卡信息
//	 * @param map
//	 */
//	void onEnd(MissionMark markBeforePersonMove, MissionMap map);
//
//	/**
//	 * 添加副本侦听
//	 * @param l
//	 */
//	void addListener(MissionListener l);
//
//	/**
//	 * 关卡奖励
//	 * @return
//	 */
//	MissionPrize pickPrize();
//
//	/**
//	 * 保存关卡奖励
//	 * @param fighterPrize
//	 * @param propPrize
//	 * @param id
//	 */
//	void save(List<BattleExpPrize> fighterPrize, List<PropPrize> propPrize, int id);
//
//	/**
//	 * 移除当前地图
//	 */
//	void deleteCurrentMap();
//
//	/**
//	 * 进入副本方法最开始调用
//	 * @param storyId
//	 */
//	void onBeforeEnter(int storyId);
}
