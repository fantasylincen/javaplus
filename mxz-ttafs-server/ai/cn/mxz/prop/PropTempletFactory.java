package cn.mxz.prop;


import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import message.S;
import cn.mxz.ConsumableTempletConfig;
import cn.mxz.EquipmentTempletConfig;
import cn.mxz.PropTemplet;
import cn.mxz.StuffTempletConfig;
import cn.mxz.base.exception.OperationFaildException;

import com.google.common.collect.Lists;

/**
 * 所有道具配置
 *
 * @author 	林岑
 * @since	2012年10月12日 11:51:59
 *
 */
public class PropTempletFactory {

	private static Map<Integer, PropTemplet> all;

//	private static long lastLoadTime;

	private static void init() {

		all = new ConcurrentHashMap<Integer, PropTemplet>();

		for (Integer id : EquipmentTempletConfig.getKeys()) {
			all.put(id, EquipmentTempletConfig.get(id));
		}
		for (Integer id : StuffTempletConfig.getKeys()) {
			all.put(id, StuffTempletConfig.get(id));
		}
		for (Integer id : ConsumableTempletConfig.getKeys()) {
			all.put(id, ConsumableTempletConfig.get(id));
		}

//		lastLoadTime = System.currentTimeMillis();
	}

	/**
	 * 获取道具模板
	 * @param typeId	道具类型ID
	 * @return
	 */
	public static PropTemplet get(int typeId) {

		update();

		return all.get(typeId);
	}

	private static void update() {
		if(all == null/* || System.currentTimeMillis() - lastLoadTime > 30000*/) {

			init();
		}
	}
	
	public static Collection<PropTemplet> getAll() {
		update();
		return all.values();
	}


	public static void checkExist(int typeId) {

		if(get(typeId) == null) {

			throw new OperationFaildException(S.S10011, typeId);
		}
	}

	public static int getPropType(int typeid) {


		if(EquipmentTempletConfig.get(typeid) != null) {

			return 15;

		} else if(ConsumableTempletConfig.get(typeid) != null) {

			return 14;

//		} else if(GemTempletConfig.get(typeid) != null) {
//
//			return 12;

		} else if(StuffTempletConfig.get(typeid) != null) {

			return 13;

		} else {

			return -1;
		}
	}

	public static List<Integer> getTypeIdsBySpotId(int id) {

		ensureInit();

		Collection<PropTemplet> values = all.values();

		List<Integer> ls = Lists.newArrayList();

		for (PropTemplet propTemplet : values) {

			int spot = propTemplet.getSpot();

			if(spot == id) {

				ls.add(propTemplet.getId());
			}
		}

		return ls;
	}

	private static void ensureInit() {

		if(all == null) {

			init();
		}
	}

	public static List<PropTemplet> findByName(String propName) {

		ensureInit();

		List<PropTemplet> ls = Lists.newArrayList();
		Collection<PropTemplet> values = all.values();
		for (PropTemplet pt : values) {
			if(pt.getName().equals(propName)) {
				ls.add(pt);
			}
		}
		return ls;
	}
}
