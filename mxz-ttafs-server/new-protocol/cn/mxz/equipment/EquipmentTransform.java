package cn.mxz.equipment;

import cn.javaplus.comunication.annotations.Communication;
import cn.mxz.city.City;

/**
 *  装备
 * @author 林岑
 *
 */
@Communication
public interface EquipmentTransform {

	
	/**
	 * 判断玩家是否有指定ID的装备
	 * @param equipmentIds 装备templetId列表  用逗号分隔的   比如:   "122341,122541,122111"
	 * @return
	 */
	EquipmentHasUI hasEquipments(String equipmentIds);

	void setUser(City user);
}
