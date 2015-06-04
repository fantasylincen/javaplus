package cn.mxz.bag;


/**
 * 可以加入道具的东西
 * @author 林岑
 *
 */

public interface AddPropAble {
	
	/**
	 * 判断背包中能否放下count个typeId
	 */
	boolean canAdd(Integer typeId, Integer count);
}
