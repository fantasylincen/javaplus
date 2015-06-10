package util.runnable;

import util.runnable.task.DBHandlePool;

/**
 * 所有线程 管理 
 * @author DXF
 *
 */
public class ThreadManager {
	private static ThreadManager instance = new ThreadManager();
	public static ThreadManager getInstance(){ return instance; }
	private ThreadManager(){ }
	
	
	private DBHandlePool 	dBHandlePool 	= null;
	
	/** 开启所有 线程 */
	public void start(){
		
		// 数据库 线程  
		dBHandlePool = new DBHandlePool();
		dBHandlePool.start( 1000 );
		
	}
	
	/** 关闭所有 线程 */
	public void stop(){
		dBHandlePool.stop();
		dBHandlePool = null;
	}
	
	public static void main( String[] args ) throws InterruptedException{
		
		System.out.println( "开启" );
		ThreadManager.getInstance().start();
		
		Thread.sleep( 3000 );
		System.out.println( "关闭" );
		ThreadManager.getInstance().stop();
		
		Thread.sleep( 5000 );
		System.out.println( "开启" );
		ThreadManager.getInstance().start();
		
		Thread.sleep( 3000 );
		System.out.println( "关闭" );
		ThreadManager.getInstance().stop();
	}
}
