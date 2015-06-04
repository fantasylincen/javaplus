package cn.mxz.mission;

/**
 * 完成度
 * @author 林岑
 *
 */
public interface Completeness {

	/**
	 * 当前完成了多少
	 * @return
	 */
	int getN();

	/**
	 * 需要完成多少
	 * @return
	 */
	int getD();
}
