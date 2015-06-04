package cn.mxz.fighter;

import java.util.List;
import java.util.Map;

import cn.javaplus.util.Util;
import cn.mxz.ExclusiveTemplet;
import cn.mxz.ExclusiveTempletConfig;

import com.google.common.collect.Maps;

/**
 * 装备天命配置
 *
 * @author 林岑
 *
 */
public class EquipmentTianMingConfig {

	private static Map<String, Integer>	map;

	/**
	 * 获得2者在一起时, 被激活的天命ID
	 *
	 * @param typeId
	 *            战士ID
	 * @param equipmentId
	 *            天命ID
	 * @return
	 */
	public static Integer getId(int typeId, int equipmentId) {
		if (map == null) {
			init();
		}
		return map.get(typeId + ":" + equipmentId);
	}

	private static void init() {
		map = Maps.newHashMap();

		List<ExclusiveTemplet> all = ExclusiveTempletConfig.findByType(2);

		for (ExclusiveTemplet et : all) {

			if(et.getJudge() == 0) {
				continue;
			}
			init(et);
		}
	}

	private static void init(ExclusiveTemplet et) {
		int fId = et.getFighterId();
		List<Integer> ids = Util.Collection.getIntegers(et.getExclusiveId());
		for (Integer equipmentId : ids) {
			map.put(fId + ":" + equipmentId, et.getId());
		}
	}

	// public static void main(String[] args) {
	// ExclusiveTempletConfig.load();
	// Integer id = getId(4000871, 151009);
	// System.out.println(id);
	// }

}
