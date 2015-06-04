package cn.javaplus.buffer;

/**
 * 这是一个事物缓冲区, 该缓冲区用于延时执行某一串事物
 * 
 * 比如用户上线的时候, 给用户发好友列表, 并不需要一次性将 所有好友列表 (100个) 10KB甚至更大 的数据一次性发完
 * 你就可以将"发送单个个好友数据" 作为一项事物, 逐个添加到该缓冲区中, 设定 间隔时间为 100 毫秒, 
 * 那么该缓冲区就会将这一百个好友的信息, 一个一个发给客户端.....间隔时间为100毫秒
 * 
 * @author 	林岑
 * @since	2012年9月6日 16:09:48
 *
 */
public interface ITransactionBuffer {

	/**
	 * 为新的一个事务流分配一个新的ID, 该id一定指向了一个空的事务
	 * @return
	 */
	int allot();

	/**
	 * 根据指定的id , 添加一个新任务, 如果id已经指向了某一串事务, 那么会将该任务追加到该事务的队尾依次执行
	 * 
	 * 如果需要一个不存在的ID, 那么就请使用 #allot()方法分配一个新的空id
	 * 
	 * @param task		任务
	 * @param period	
	 * 					该任务执行后的等待时间, 也就是下一任务执行时和该任务执行时完时的间隔时间
	 * 					最小值为10
	 */
	void add(int id, int period, Task task);

	/**
	 * 在id为id的事务队列中, 追加一个延时
	 * @param id
	 * @param period	延时
	 */
	void appendDelay(int id, int period);

}
