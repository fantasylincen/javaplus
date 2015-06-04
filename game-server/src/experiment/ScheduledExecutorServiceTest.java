package experiment;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author admin
 * 2012-8-15 下午05:01:33
 * 
 * 结论：
 * 		如果由于任务太复杂，导致执行时间过长，让下个任务不得不延期执行，那么会尽可能把未执行的任务补上来
 */
public class ScheduledExecutorServiceTest {

	public static void main ( String[] args ) {
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable beeper = new Runnable() {
        	boolean init = true;
        	public void run() {
        		if( init ){
        			init = false;
        			try {
						Thread.sleep( 2000 );
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
        		}
        		System.out.println( System.currentTimeMillis() );
        	}
		};
		scheduler.scheduleAtFixedRate(beeper, 1, 1, TimeUnit.SECONDS);

	}
}
