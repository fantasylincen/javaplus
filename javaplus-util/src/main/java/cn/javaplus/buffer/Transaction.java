package cn.javaplus.buffer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 单项事务, 该事务中包含了一串指定间隔时间的任务, 这些任务可由 ITransactionBuffer 一项一项的执行
 * 
 * @author 	林岑
 * @since	2012年9月7日 10:53:59
 *
 */
class Transaction {
	
	/**
	 * 任务队列
	 */
	private Queue<Task> tasks = new LinkedList<Task>();
	
	/**
	 * 间隔时间队列
	 */
	private Queue<Long> periods = new LinkedList<Long>();
	
	/**
	 * 下一任务执行的间隔时间
	 */
	private long nextPeriod = 0;
	
	/**
	 * 最后一个任务执行完时的时间
	 */
	private long runLastTime = 0;
	
	/**
	 * 在事务队尾追加一项任务
	 * @param task		任务
	 * @param period	该任务执行完毕后, 下一任务启动的间隔时间
	 */
	synchronized void append(Task task, long period) {
		this.tasks.add(task);
		this.periods.add(period);
	}
	
	/**
	 * 将队列中的任务取出同时执行
	 * @return	是否还有后续任务
	 */
	synchronized boolean pollAndRun() {
		Task t = tasks.poll();
		if(t != null) {
			t.doIt();
			nextPeriod = tasks.isEmpty() ? 0 : periods.poll();//如果已经腾空了所有任务, 那么nextPeriod = 0
			runLastTime = System.currentTimeMillis();
		} else {
			nextPeriod = 0;
		}
		return t != null;
	}
	
	/**
	 * 获得下一任务执行的间隔时间, 如果没有后续任务 或者是pollAndRun方法从来没有执行过, 返回0
	 * @return
	 */
	synchronized long getNextPeriod() {
		return nextPeriod;
	}

	/**
	 * 判断当前时间是否可以执行下一个任务
	 * @return
	 */
	boolean couldRunNext() {
		return System.currentTimeMillis() >= runLastTime + nextPeriod;
	}
}
