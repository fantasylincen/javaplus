package cn.mxz.battle;

/**
 * 失效的Buff
 * @author 林岑
 *
 */
interface BuffDisappear {

	/**
	 * 失效的BuffId
	 * @return
	 */
	int getBuffId();

	/**
	 * 失效的Buff的位置
	 * @return
	 */
	int getPosition();

	boolean isUnder();

}
