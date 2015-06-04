package cn.mxz.mission.star;

import cn.mxz.MapTemplet;
import cn.mxz.MissionMapTempletConfig;
import cn.mxz.city.City;
import cn.mxz.mission.templet.MissionNodeTemplet;
import cn.mxz.mission.templet.MissionPathCfg;
import cn.mxz.mission.templet.MissionPathTemplet;

import com.google.common.collect.Multimap;

import define.D;

public class MissionStarImpl implements MissionStar {

	private db.domain.MissionStar	star;
	private City					city;
	private int						max;
//	private int						maxMissionId;

	public MissionStarImpl(City city, db.domain.MissionStar star ) {
		this.city = city;
		this.star = star;
		max = initMax();
//		this.maxMissionId = maxMissionId;
	}

	@Override
	public int getMissionId() {
		return star.getMissionId();
	}

	@Override
	public int getCount() {
		return new BranchBossStarController(star).getCurrentCount();
	}

	@Override
	public int getMax() {
		return max;
		// return city.getMission().getMaxStar( getMissionId() );
	}

//	private boolean isGuide(){
//		return maxMissionId < D.MAX_GUIDE_MISSION_ID;
//	}
	private int initMax() {
//		if (getMissionId() == 1 && isGuide()	) {
//			return 9;
//		}
//		if (getMissionId() == 2 && isGuide() ) {
//			return 9;
//		}

		MapTemplet mTemp = MissionMapTempletConfig.get(getMissionId());
		MissionPathTemplet temp = MissionPathCfg.getMapTemplet(getMissionId());
		if (temp == null) {
//			Debuger.error("json地图数量必须和配置表里面的记录数量一致:" + getMissionId());
			return 0;
		}
		Multimap<Integer, MissionNodeTemplet> pathMap = temp.getPathMap();
		int maxBattleSize = 0;
		int maxStar = 0;
		boolean hasBranch = false;

		for (Integer path : pathMap.keySet()) {
			int allSize = pathMap.get(path).size();
			int randomSize = allSize;
			if (path == 1) {
				randomSize -= 2;
			} else {
				randomSize -= 1;
				hasBranch = true;
			}
			int realSize = (int) (randomSize * mTemp.getDemonScale());
			maxBattleSize += realSize;
		}
		if (hasBranch) {
			maxBattleSize += 2;// 2 for 主线和支线的尾部的boss战斗节点
		} else {
			maxBattleSize += 1;
		}
		return maxBattleSize * D.MISSION_STAR_MAX;
	}
	
	static private int initMax( int id ) {
//		if (getMissionId() == 1 && isGuide()	) {
//			return 9;
//		}
//		if (getMissionId() == 2 && isGuide() ) {
//			return 9;
//		}

		MapTemplet mTemp = MissionMapTempletConfig.get(id);
		MissionPathTemplet temp = MissionPathCfg.getMapTemplet(id);
		if (temp == null) {
//			Debuger.error("json地图数量必须和配置表里面的记录数量一致:" + getMissionId());
			return 0;
		}
		Multimap<Integer, MissionNodeTemplet> pathMap = temp.getPathMap();
		int maxBattleSize = 0;
		int maxStar = 0;
		boolean hasBranch = false;

		for (Integer path : pathMap.keySet()) {
			int allSize = pathMap.get(path).size();
			int randomSize = allSize;
			if (path == 1) {
				randomSize -= 2;
			} else {
				randomSize -= 1;
				hasBranch = true;
			}
			int realSize = (int) (randomSize * mTemp.getDemonScale());
			maxBattleSize += realSize;
		}
		if (hasBranch) {
			maxBattleSize += 2;// 2 for 主线和支线的尾部的boss战斗节点
		} else {
			maxBattleSize += 1;
		}
		return maxBattleSize * D.MISSION_STAR_MAX;
	}
	
	public static void main(String[] args) {
		for( int i = 1; i <= 108; i++ ){
			
			System.out.println("第" + i + "关:" +MissionStarImpl.initMax(i) /3);
		}
	}

}
