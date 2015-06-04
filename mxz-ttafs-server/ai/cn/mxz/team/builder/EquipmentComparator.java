package cn.mxz.team.builder;

import java.util.Comparator;
import java.util.Map;

import cn.mxz.equipment.Equipment;
import cn.mxz.formation.AdditionType;

import com.google.common.collect.Maps;

public class EquipmentComparator implements Comparator<Equipment> {

	@Override
	public int compare(Equipment o1, Equipment o2) {

		int v1 = o1.getAdditionType();
		int v2 = o2.getAdditionType();

		Map<AdditionType, Integer> map = Maps.newHashMap();

		map.put(AdditionType.ATTACK, 0);
		map.put(AdditionType.MATTACK, 1);
		map.put(AdditionType.HP, 2);
		map.put(AdditionType.DEFEND, 3);
		map.put(AdditionType.MDEFEND, 4);
		map.put(AdditionType.SPEED, 5);

		int a1 = get(map, v1);
		int a2 = get(map, v2);

		return a1 - a2;

	}

	private int get(Map<AdditionType, Integer> map, int v1) {
		AdditionType fromNum = AdditionType.fromNum(v1);
		Integer integer = map.get(fromNum);
		if (integer == null) {
			integer = 1000;
		}
		return integer;
	}
}
