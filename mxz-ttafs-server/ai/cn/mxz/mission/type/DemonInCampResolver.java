package cn.mxz.mission.type;

import java.util.Arrays;
import java.util.List;

import cn.mxz.FighterTemplet;
import cn.mxz.FighterTempletConfig;
import cn.mxz.MissionMapTemplet;
import cn.mxz.MissionMapTempletConfig;
import cn.mxz.city.City;
import cn.mxz.mission.old.demon.DemonImpl;

import com.google.common.collect.Lists;

/**
 *
 * 怪物阵容解析器
 * @author 林岑
 *
 */
public class DemonInCampResolver {

	/**
	 * 解析
	 * @param monsterId 怪物,位置,怪物,位置.....
	 * @param mapId		地图ID
	 * @param isBoss
	 * @param isMain
	 * @param city 
	 * @param isDodge	小怪是否会闪避
	 * @return
	 */
	public List<DemonInCamp> resolve(int[] monsterId, int mapId, boolean isBoss, boolean isMain, City city) {

		List<DemonInCamp> ls = Lists.newArrayList();
		MissionMapTemplet mt = MissionMapTempletConfig.get(mapId);
		for (int i = 0; i < monsterId.length; i+=2) {
			FighterTemplet ft = FighterTempletConfig.get(monsterId[i]);
			if(ft == null) {
				throw new IllegalArgumentException("" + Arrays.toString(monsterId));
			}

			DemonImpl dm = new DemonImpl(ft, mt, isBoss, isMain, city);
			DemonInCamp dp = new DemonInCamp(dm, monsterId[i + 1]);
//			Debuger.debug("DemonInCampResolver.resolve() + 怪物属性:" + dm.getAttribute());
			ls.add(dp);
		}
		return ls;
	}
}
