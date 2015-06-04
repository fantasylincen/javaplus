package cn.mxz.yuanshen;

import java.util.List;

import cn.mxz.FighterTemplet;
import cn.mxz.FighterTempletConfig;
import cn.mxz.city.City;
import cn.mxz.yuanshen.YuanShenServiceImpl.Need;

class YuanShenLevelUpCalc {

	/**
	 * 计算元神升级所增加的经验
	 *
	 * @param city
	 * @param fighterId
	 * @param spiriteIds
	 * @return
	 */
	public int calc(City city, List<Integer> fighterIds, List<Need> spiriteIds) {

		int exp = 0;

		for (Need id : spiriteIds) {
			Integer id2 = id.getId();
			FighterTemplet temp = FighterTempletConfig.get(id2);
			exp += temp.getSoulExp() * id.getCount();
		}
		for (Integer id : fighterIds) {

			FighterTemplet temp = FighterTempletConfig.get(id);
			exp += temp.getGodExp();
		}

		return exp;
	}

}
