package cn.mxz.mission.type;

/**
 * 范围
 * @author 林岑
 *
 */
public interface Scope {

	/**
	 * 是否包含这个值
	 * @param round
	 * @return
	 */
	boolean contains(int round);

}
