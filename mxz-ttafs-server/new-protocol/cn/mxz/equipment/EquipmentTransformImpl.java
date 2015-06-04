package cn.mxz.equipment;

import java.util.List;
import java.util.Map;

import cn.javaplus.util.Util;
import cn.mxz.city.City;

public class EquipmentTransformImpl implements EquipmentTransform {

	private City	user;

	@Override
	public EquipmentHasUI hasEquipments(String equipmentIds) {
		List<Integer> ids = Util.Collection.getIntegers(equipmentIds);

		EquipmentHasUIImpl has = new EquipmentHasUIImpl();

		Map<Integer, EquipmentImpl> all = user.getEquipmentManager().getAll();
		for (Integer id : ids) {
			has.add(id, all.containsKey(id));
		}

		return has;
	}

	@Override
	public void setUser(City user) {
		this.user = user;
	}

//	@Override
//	public EquipmentLevelUpMaxUI getEquipmentLevelUpMax(int equipmentId) {
//	}
//
//	@Override
//	public void levelUpToBest(int equipmentId) {
//		
//		EquipmentLevelUpMaxUI ui = getEquipmentLevelUpMax(equipmentId);
//		
//		int add = ui.getLevelMax();
//		
//		FighterSnapshoot s = new FighterSnapshoot(user);
//		s.snapshoot();
//
//		user.getEquipmentManager().levelUp(equipmentId, add);
//
//		Equipment e1 = user.getEquipmentManager().getAll().get(equipmentId);
//
//		s.snapshoot();
//
//	}

}
