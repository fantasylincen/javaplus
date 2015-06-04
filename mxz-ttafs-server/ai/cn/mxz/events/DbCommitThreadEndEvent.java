package cn.mxz.events;

/**
 * DB提交线程 正常结束事件
 * @author 林岑
 *
 */
public class DbCommitThreadEndEvent {

	private int liveThreadCount;

	public DbCommitThreadEndEvent(int liveThreadCount) {
		this.liveThreadCount = liveThreadCount;
	}
	
	/**
	 * 当前存活的数据库提交线程数量
	 * @return
	 */
	public int getLiveThreadCount() {
		return liveThreadCount;
	}
}
