package cn.mxz.prop;



/**
 *
 * 道具类
 * 最简单的道具属于此类<br/>
 * 通常自身不具备任何附加属性，如十全大补丸，以及各种材料
 *
 * @author admin
 *
 */
public interface Prop {

	/**
	 * 获得类型Id
	 * @return
	 */
	int getTypeId();

	/**
	 * 获得该道具的叠加上限
	 * @return
	 */

	int getAddUp();

	/**
	 * 获得道具名字
	 * @return
	 */

	String getName();

	/**
	 * 道具等级
	 */

	int getLevel();

	/**
	 * 道具类型(装备?宝石?)
	 * @return
	 */

	int getPropType();

//	/**
//	 * 是否可以作为礼物赠送?
//	 * @return
//	 */
//
//	boolean giftable();
}
