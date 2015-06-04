package cn.mxz.mission.old;

import java.util.Collection;
import java.util.Set;

import cn.mxz.AchieveTaskTemplet;
import cn.mxz.AchieveTaskTempletConfig;

import com.google.common.collect.Sets;


public class MapDemonStarConfig {

	Set<String> set ;
	
	public MapDemonStarConfig() {
		set = Sets.newHashSet();
	}
	
	public boolean contains(int mapId, int typeId) {
		return set.contains(mapId + "," + typeId);
	}

	/**
	 * 从成就任务表中读取所有需要记录的怪物ID和地图ID, 放到配置中
	 * @return
	 */
	public static MapDemonStarConfig initFromAchieveTaskTempletConfig() {
		MapDemonStarConfig c = new MapDemonStarConfig();
		Collection<AchieveTaskTemplet> all = AchieveTaskTempletConfig.getAll();
		for (AchieveTaskTemplet t : all) {
			
			String arg1 = t.getArg1();
			
			if(arg1.contains("getDemonStar")) {
//				getDemonStar(50, 200094) >= 3
				arg1 = arg1.replaceAll("getDemonStar\\(", "").replaceAll("\\).*", "");
				String[] split = arg1.split(",");
				Integer mId = new Integer(split[0].trim());
				Integer dId = new Integer(split[1].trim());
				c.set.add(mId + "," + dId);
			}
		}
		return c;
	}

}
