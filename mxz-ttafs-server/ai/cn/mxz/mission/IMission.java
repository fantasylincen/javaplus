package cn.mxz.mission;

import java.util.List;

public interface IMission {

	/**
	 * path用于考虑主线，支线交叉点上分路的情况
	 * @param path
	 * @return
	 */
	public abstract Object run(int path);

	/**
	 * 获取下一个可以执行run()方法的节点,有几种情况</br>
	 * 1、主线正常移动到下一个</br>
	 * 2、支线正常移动到下一个</br>
	 * 3、主线移动到支线第一个节点</br>
	 * 
	 * 注意	<b>不包含</b>      支线完毕回到到主线的情况
	 * 
	 * @param path
	 * @return
	 */
	public abstract MissionNode getNextRunNode(int path);

	/**
	 * 回到主线
	 */
	public abstract int backMainBranch();

	/**
	 * @return missionId
	 */
	public abstract int getMissionId();

	public abstract List<MissionNode> getAllNodeS();

	public abstract void end();

	/**
	 * 关卡是否完结
	 * @return
	 */
	public abstract boolean isDone();

	public abstract List<Integer> getPrizeBox();

	/**
	 * @return currentNode
	 */
	public abstract MissionNode getCurrentNode();

	public abstract Boolean branchIsCross();
	
	/**
	 * 是否可进入支线
	 * 1、从未进过支线的情况下才可以进入支线，如果进入过支线了又主动放弃，则不能再进入
	 * @return
	 */
	public abstract Boolean canJumpBranch();

	/**
	 * 允许通过了主线的玩家直接跳到分叉点
	 * @return
	 */
	int directJumpBranch();

	Boolean bossIsCross();
	
	

}