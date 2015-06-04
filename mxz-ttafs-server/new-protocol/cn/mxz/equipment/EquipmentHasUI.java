package cn.mxz.equipment;

import java.util.List;

/**
 * 玩家是否拥有某个装备
 * @author 林岑
 *
 */
public interface EquipmentHasUI {

	/**
	 * 是否拥有这些装备
	 * @return
	 */
	List<EquipmentResult> getResult();

}
