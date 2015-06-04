package cn.mxz.newpvp;

import cn.mxz.city.City;
import cn.mxz.script.Script;
import cn.mxz.user.team.Formation;

public class PracticeCaculator {

	public int calc(City me, PvpFightUser pfu) {


		int danId = pfu.getPlayer().getDanId();
		int dan = me.getNewPvpManager().getPlayer().getDan();
		
		return (int) Script.Pvp.getWinPointsWin(danId, dan);
	}


//	/**
//	 * 根据对手和自己的战斗力的比值, 计算修行值加成
//	 *
//	 * @param scale
//	 * @return
//	 */
//	private float getAddition(float scale) {
//		Collection<AdditionRewardTemplet> all = AdditionRewardTempletConfig.getAll();
//		for (AdditionRewardTemplet temp : all) {
//			if (contains(temp, scale)) {
//				return temp.getAddition();
//			}
//		}
//		return 0;
//	}
//
//	private boolean contains(AdditionRewardTemplet temp, float scale) {
//		List<Integer> s = Util.Collection.getIntegers(temp.getRatio());
//		int min = s.get(0);
//		int max = s.get(1);
//
//		return min <= scale && scale <= max;
//	}

	private int getShenJia(City city) {
		Formation oF = city.getFormation();
		int shenJia = oF.getShenJia();
		return shenJia;
	}

}
