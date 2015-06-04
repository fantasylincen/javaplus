package cn.mxz.bag;



/**
 * 格子
 * @author  林岑
 * @since	2012年10月12日 13:13:27
 *
 */
public interface Grid {

	/**
	 * 剩余空间
	 *
	 * @link Grid#isEmpty() 时, 返回0
	 *
	 */
	int getFree();

	/**
	 * 是否为满
	 */
	boolean isFull();

	/**
	 * 物品数量
	 * @return
	 */
	int getCount();

	/**
	 * 格子ID
	 * @return
	 */
	int getGridId();

	/**
	 * 道具模板ID
	 * @return
	 */
	int getTempletId();

	/**
	 * 道具类型
	 * @return
	 */
	int getPropType();
}
