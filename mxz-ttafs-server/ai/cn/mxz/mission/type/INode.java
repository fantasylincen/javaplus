package cn.mxz.mission.type;


public interface INode {
	
//	boolean hasNext();
//	
//	/**
//	 * 移动到下一个节点,这里指的是主节点，而非支线
//	 * @return
//	 */
//	INode next( );
//	
//	/**
//	 * 获取支线的第一个节点
//	 * @return
//	 */
//	INode getBranch();
//	
//	/**
//	 * 是否支线任务
//	 * @return
//	 */
//	boolean isBranch();
//	
//	/**
//	 * 此节点的任务是否完成
//	 * @return
//	 */
//	boolean isDone();
//
//	/**
//	 * 初始化的时候设置下一个节点
//	 * @param next
//	 * @return
//	 */
//	void setNext( INode next );

	

	Object run();
	
}
