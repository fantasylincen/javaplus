package cn.mxz.equipment;

/**
 * 是否拥有这个装备
 * @author 林岑
 *
 */
public interface EquipmentResult {

	/**
	 * 装备templetId    Excel中的ID
	 * @return
	 */
	int getEquipmentId();

	/**
	 * 是否拥有这个装备
	 * @return
	 */
	boolean isExist();
}
