package cn.mxz.mission.old;

/**
 * 路段
 * @author 林岑
 *
 */

public interface MapPath {

	/**
	 * 获得指定索引的地图节点
	 * @param index
	 * @return
	 */
	MapNode getNode(int index);

}
