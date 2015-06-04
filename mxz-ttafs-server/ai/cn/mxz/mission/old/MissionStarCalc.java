package cn.mxz.mission.old;

import cn.mxz.util.debuger.Debuger;


public class MissionStarCalc {


	public int getCount(int round) {

		Debuger.debug("MissionStarCalc.getCount() 计算分数时 round = " + round);

		if(round <= 5) {
			return 3;
		} else if( 5 < round && round <= 12) {
			return 2;
		} else if (12 < round && round <= 18) {
			return 1;
		} else {
			return 0;
		}
	}
}
