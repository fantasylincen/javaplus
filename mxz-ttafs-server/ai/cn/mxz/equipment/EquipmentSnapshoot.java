package cn.mxz.equipment;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.javaplus.util.Util;
import cn.mxz.city.City;
import cn.mxz.messagesender.MessageFactory;
import cn.mxz.protocols.user.equipment.EquipmentP.EquipmentsPro;
import cn.mxz.util.debuger.Debuger;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * 装备快照
 *
 * @author 林岑
 *
 */
public class EquipmentSnapshoot {

	public class Changes {
		Set<Integer>	removes	= Sets.newHashSet();
		List<Equipment>	changes	= Lists.newArrayList();
	}

	private City					city;

	private Map<Integer, String>	md5s	= Maps.newHashMap();

	public EquipmentSnapshoot(City city) {
		this.city = city;
	}

	/**
	 * 快照, 如果跟之前的快照的结果对比发生了变化, 就会主动通知客户端这些Equipment发生变化了
	 *
	 * 如果有新增加的Equipment, 也会发送过去
	 *
	 */
	public void snapshoot(Collection<? extends Equipment> equipments) {
		if (md5s.isEmpty()) {
			saveFirst(equipments);
		} else {
			snapshootToOld(equipments);
		}
	}

	private void snapshootToOld(Collection<? extends Equipment> equipments) {

		Changes changes = getChangedEquipments(equipments);

		if (!changes.changes.isEmpty()) {
			EquipmentsBuilder b = new EquipmentsBuilder();
			EquipmentsPro es = b.build(changes.changes);
			Debuger.debug("EquipmentSnapshoot.snapshootToOld() change" + changes.changes);
			MessageFactory.getEquipment().equipmentsUpdate(city.getSocket(), es);
		}

		if (!changes.removes.isEmpty()) {
//			Debuger.debug("EquipmentSnapshoot.snapshootToOld() change");
			MessageFactory.getEquipment().equipmentsRemove(city.getSocket(), buildIds(changes.removes));
		}
	}

	private String buildIds(Set<Integer> removes) {
		return Util.Collection.linkWith(",", removes);
	}

	/**
	 * 获得所有发生变化了的装备
	 *
	 * @param equipments
	 *
	 * @return
	 */
	private Changes getChangedEquipments(Collection<? extends Equipment> equipments) {
		Changes changes = new Changes();

		setRemoves(changes, equipments);

		for (Equipment e : equipments) {
			String old = md5(e);

			String newMd5 = md5s.get(e.getId());

			if (newMd5 == null || !newMd5.equals(old)) {

				changes.changes.add(e);
			}

			md5s.put(e.getId(), newMd5); // update
		}

		return changes;
	}

	private void setRemoves(Changes changes, Collection<? extends Equipment> equipments) {
		Set<Integer> idsOld = Sets.newHashSet(md5s.keySet());

		HashSet<Integer> keys = Sets.newHashSet();

		for (Equipment e : equipments) {
			keys.add(e.getId());
		}

		idsOld.removeAll(keys);

		changes.removes.addAll(idsOld);
	}

	private void saveFirst(Collection<? extends Equipment> equipments) {
		for (Equipment e : equipments) {
			md5s.put(e.getId(), md5(e));
		}
	}

	private String md5(Equipment e) {

		StringBuilder sb = new StringBuilder();

		sb.append(e.getHeroId());
		sb.append(e.getTypeId());
		sb.append(e.getTypeId());
		sb.append(e.getLevel());
		sb.append(e.getId());

		String md5 = Util.Secure.md5(sb.toString());
		return md5;
	}

}
