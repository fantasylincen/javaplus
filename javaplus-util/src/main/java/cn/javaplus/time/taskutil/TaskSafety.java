package cn.javaplus.time.taskutil;

import java.util.TimerTask;

/**
 * 安全的计时器任务, 不会把计时器的线程给弄死
 * @author 	林岑
 * @since	2012年11月1日 23:00:48
 *
 */
public abstract class TaskSafety extends TimerTask {

	@Override
	public final void run() {
		try {
			long t1 = System.currentTimeMillis();
			printBeforeRun();
			runSafty();
			print(System.currentTimeMillis() - t1);
		} catch (Exception e) {
			process(e);
		} finally {
		}
	}

	/**
	 * 处理异常
	 * @param e
	 */
	protected  void process(Exception e) {
		
	}

	protected void printBeforeRun(){
//		System.out.println("任务开始执行:	[" + this.getClass().getSimpleName() + "]");
	}

	protected void print(long timeUsed){
//		System.out.println("任务执行完毕:	[" + this.getClass().getSimpleName() + "] 累计耗时:" + timeUsed + "ms" );
	}

	/**
	 * 该方法抛出的异常会被捕获, 不会把线程停掉
	 */
	public abstract void runSafty();
}
