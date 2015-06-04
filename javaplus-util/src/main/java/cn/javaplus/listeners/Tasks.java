package cn.javaplus.listeners;

import java.util.ArrayList;
import java.util.List;

/**
 * 任务
 * @author Administrator
 *
 */
public class Tasks {

	/**
	 * {@link Tasks} 实例
	 */
	private static final Tasks instance = new Tasks();

	/**
	 * 私有化构造
	 */
	private Tasks() {
		
		Thread t = new Thread() {
			
			@Override
			public void run() {
				while(true) {
					
					
					try {

						Thread.sleep(60000);
						
						for (Task t : allTask) {
							t.doIt();
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			}
		};
		
		t.setDaemon(true);
		t.start();
	}

	/**
	 * 获取该对象唯一实例
	 * @return
	 */
	public static final Tasks getInstance() {
		return instance;
	}
	
	
	private List<Task> allTask = new ArrayList<Task>();
	
	/**
	 * 增加任务, 该任务, 会每60秒执行一次
	 * @param task
	 */
	public synchronized void addTask(Task task) {
		this.allTask.add(task);
	}
}
