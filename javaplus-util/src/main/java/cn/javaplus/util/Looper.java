package cn.javaplus.util;

/**
 * 循环器
 * 
 * 该循环器不一定保证每次循环间隔时间相等, 但是能够保证每间隔时间段内 仅执行过一次, 而且至少有一次
 * 
 * 也就是说, 如果sec 为10
 * 
 * 那么该循环器必然保证24小时内执行过 24 * 60 / 10 次! 在这一点上是精准的
 * 
 * (每次执行间的误差最大为800毫秒)
 * 
 * @author 	林岑
 * @since	2012年9月23日 14:44:34
 *
 */
public abstract class Looper implements Runnable{
	
	/**
	 * 间隔秒数
	 */
	private final int sec;

	/**
	 * 
	 * @param se	循环间隔时间(秒)
	 */
	public Looper( int sec ) {
		this.sec = sec;
	}

	@Override
	public void run() {
		long T1 = 0;
		while(true) {

			long m = System.currentTimeMillis() / 1000 ;
			
			if(T1 != m && m % sec  == 0) {	//1分钟刷一次
				
				action();
				T1 = m;
			}

			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 循环中将会调用的方法
	 */
	public abstract void action() ;
	
}
