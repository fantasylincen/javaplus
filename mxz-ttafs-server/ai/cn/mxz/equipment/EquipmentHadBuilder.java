package cn.mxz.equipment;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import cn.mxz.protocols.user.equipment.EquipmentP.EquipmentHadPro;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 可穿戴的装备数量
 * @author 林岑
 *
 */
class EquipmentHadBuilder {

	public EquipmentHadPro build(EquipmentManager m) {

		EquipmentHadPro.Builder b = EquipmentHadPro.newBuilder();
		List<EquipmentImpl> allUnEquip = getUnEquipAll(m);

//		（0气血 1攻击 2物防 3法抗 4速度）
		b.setHpHad(getCount(allUnEquip, 0));
		b.setAttackHad(getCount(allUnEquip, 1));
		b.setDefendHad(getCount(allUnEquip, 2) + getCount(allUnEquip, 3));
		b.setSpeedHad(getCount(allUnEquip, 4));

		return b.build();
	}

	private int getCount(List<EquipmentImpl> allUnEquip, int i) {

		int count = 0;

		for (Equipment equipment : allUnEquip) {
			if(equipment.getAdditionType() == i) {
				count++;
			}
		}
		return count;
	}

	private List<EquipmentImpl> getUnEquipAll(EquipmentManager m) {
		Map<Integer, EquipmentImpl> all = Maps.newHashMap(m.getAll());
		Map<Integer, EquipmentImpl> equippedAll = m.getEquippedAll();
		removeAll(all, equippedAll);
		return Lists.newArrayList(all.values());
	}

	private void removeAll(Map<Integer, EquipmentImpl> all, Map<Integer, EquipmentImpl> equippedAll) {
		Set<Entry<Integer, EquipmentImpl>> entrySet = all.entrySet();
		Iterator<Entry<Integer, EquipmentImpl>> it = entrySet.iterator();
		while (it.hasNext()) {
			Map.Entry<java.lang.Integer, cn.mxz.equipment.EquipmentImpl> entry = (Map.Entry<java.lang.Integer, cn.mxz.equipment.EquipmentImpl>) it.next();
			if(equippedAll.containsKey(entry.getKey())){
				it.remove();
			}
		}
	}

}
