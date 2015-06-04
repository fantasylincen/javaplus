package cn.mxz.mission.star;

import cn.mxz.MapTemplet;
import cn.mxz.MissionMapTempletConfig;
import cn.mxz.city.City;
import cn.mxz.mission.templet.MissionNodeTemplet;
import cn.mxz.mission.templet.MissionPathCfg;
import cn.mxz.mission.templet.MissionPathTemplet;

import com.google.common.collect.Multimap;

import define.D;

public class MissionStarHistoryImpl implements MissionStar {


	private db.domain.MissionStar	star;
	private City					city;
	private int						max;

	public MissionStarHistoryImpl(City city, db.domain.MissionStar star ) {
		this.city = city;
		this.star = star;
		max = initMax();
	}

	@Override
	public int getMissionId() {
		return star.getMissionId();
	}

	@Override
	public int getCount() {
		return new BranchBossStarController(star).getCountMax();
	}

	@Override
	public int getMax() {
		return max;
	}

	private int initMax() {

		MapTemplet mTemp = MissionMapTempletConfig.get(getMissionId());
		MissionPathTemplet temp = MissionPathCfg.getMapTemplet(getMissionId());
		if (temp == null) {
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

}
