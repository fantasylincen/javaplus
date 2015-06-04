package cn.mxz.team.builder;

import java.util.List;

import cn.mxz.equipment.Equipment;

/**
 * 战士的装备天命
 * @author 林岑
 *
 */
public interface EquipmentTianMing {

	/**
	 * 当天被激活的装备天命ID列表
	 * @return
	 */
	List<Integer> getTianMingIds();

	/**
	 * 专属装备列表
	 * @return
	 */
	List<Equipment> getZSEquipments();

	boolean isZhuanShu(Equipment e);

}
