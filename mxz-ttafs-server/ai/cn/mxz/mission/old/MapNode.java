package cn.mxz.mission.old;

import java.util.List;


/**
 * 地图节点
 * @author 林岑
 *
 */
interface MapNode extends LocationAble{

	/**
	 * 是否有后续节点
	 * @return
	 */
	
	boolean hasNext();

	/**
	 * 这个节点后面跟的节点
	 * @return
	 */
	List<MapNode> next();

	/**
	 * 是否是大节点(大石块)
	 * @return
	 */
	boolean isBig();

	/**
	 * 最临近的后续大石头节点
	 * @return
	 */
	
	List<MapNode> getNearest();

	/**
	 * 下一个大石头节点
	 * @return
	 */
	
	MapNode nextBig();

	/**
	 * 这个节点上是不是空的?
	 
	 * @return
	 */
	
	boolean isEmpty();

	boolean isEnd();

}
