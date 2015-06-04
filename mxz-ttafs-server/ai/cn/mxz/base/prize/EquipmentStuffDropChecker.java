package cn.mxz.base.prize;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.javaplus.collections.map.ListMap;
import cn.mxz.EquipmentTemplet;
import cn.mxz.EquipmentTempletConfig;
import cn.mxz.battle.PropPrizeImpl;
import cn.mxz.equipment.Equipment;
import cn.mxz.equipment.EquipmentManager;
import cn.mxz.user.Player;
import cn.mxz.util.debuger.Debuger;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class EquipmentStuffDropChecker {

	
	/**
	 * 玩家player是否有这个材料对应的装备
	 * @param player
	 * @param o
	 * @return
	 */
	public boolean hasEquipment(Player player, PropPrizeImpl o) {
		EquipmentManager em = player.getCity().getEquipmentManager();
		Set<Integer> equipmentIds = getEquipments(o);
		if(equipmentIds != null) {
			for (Integer id : equipmentIds) {
				if (contains(em, id)) {
					return true;
				}
			}
		}
		return false;
	}

	private Set<Integer> getEquipments(PropPrizeImpl o) {
		return STUFF_EQUIPMENT.get(o.getId());
	}
	
	public boolean needCheck(PropPrizeImpl o) {
		return STUFF_EQUIPMENT.containsKey(o.getId());
	}

	private boolean contains(EquipmentManager em, int id) {
		try {
			Collection<? extends Equipment> values = Lists.newArrayList(em.getAll().values());
			for (Equipment e : values) {
				int tid = e.getTypeId();
				if(tid == id) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			Debuger.error("装备异常", e);
			return false;
		}
	}

	private static Map<Integer, Set<Integer>> STUFF_EQUIPMENT = Maps.newConcurrentMap();
	
	public static void init() {
		ListMap<Integer, Integer> lll = new ListMap<Integer, Integer>();
		List<EquipmentTemplet> all = EquipmentTempletConfig.getAll();
		for (EquipmentTemplet e : all) {
			put(e, lll);
		}
		Set<Integer> keySet = lll.keySet();
		for (Integer id : keySet) {
			Set<Integer> list = Sets.newHashSet(lll.get(id));
			STUFF_EQUIPMENT.put(id, list);
		}
	}

	private static void put(EquipmentTemplet e, ListMap<Integer, Integer> lll) {

		List<Integer> a1 = get(e.getChip());
		List<Integer> a2 = get(e.getStuffId());

		for (Integer id : a2) {
			lll.add(id, e.getId());
		}
		for (Integer id : a1) {
			lll.add(id, e.getId());
		}
	}

	private static List<Integer> get(String ss) {
		String[] split = ss.split("\\|");
		ArrayList<Integer> newArrayList = Lists.newArrayList();
		for (String string : split) {
			if(string.trim().isEmpty()) {
				continue;
			}
			String[] split2 = string.split(",");
			int id = new Integer(split2[0]);
			newArrayList.add(id);
		}
		return newArrayList;
	}
}
