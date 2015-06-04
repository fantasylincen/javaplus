package util.taskscheduling;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import util.taskscheduling.task.CommandTask;

/**
 * 任务调度
 * @author DXF
 */
public class TaskScheduling {

	private final ScheduledExecutorService service;
	
	public TaskScheduling( ){
		
		service = Executors.newScheduledThreadPool(10);
	}
	
	/**
	 * 开始执行
	 * @param t
	 * @param command
	 */
	public void execute( T t, Runnable command ){
		long delay 	= t.getDelay();
		long period	= t.getPeriod();
		if( period > 0 )
			service.scheduleAtFixedRate( command, delay, period, TimeUnit.MILLISECONDS );
		else
			service.schedule( command, delay, TimeUnit.MILLISECONDS );
	}
	
	public void stop(){
		service.shutdownNow();
	}
	
	public static void main( String[] args ){
		
		System.out.println( "开始" );
		ScheduledExecutorService service1 = Executors.newScheduledThreadPool(10);
//		service1.scheduleAtFixedRate( new CommandTask(), 1, -1, TimeUnit.MILLISECONDS );
		service1.schedule( new CommandTask(), 1800000, TimeUnit.MILLISECONDS );
	}
	
}

