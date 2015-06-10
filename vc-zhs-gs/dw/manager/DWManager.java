package manager;

import game.log.Logs;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * 数据仓库 管理中心
 * @author DXF
 */
public class DWManager{

	public static DWManager Instance 					= new DWManager();
	private final ScheduledExecutorService s 			= Executors.newSingleThreadScheduledExecutor();
	private final int TICK_UNIT 						= 500;
	
//	private List<IDWBase> lists							= new ArrayList<IDWBase>();
	
	private DWManager(){
		
		s.scheduleAtFixedRate( new Ticker(), TICK_UNIT, TICK_UNIT, TimeUnit.MILLISECONDS);
		
		Runtime.getRuntime().addShutdownHook( new Thread() {
			@Override
			public void run() {
				s.shutdown();
			}
		});
	};
	
	/** 加入一条记录 */
	public void put( IDWBase idw ){
		
//		if( idw == null ) return;
//		
//		lists.add( idw );
	}

	private class Ticker implements Runnable {

		@Override
		public void run() {
			
//			if( lists.isEmpty() ) return;
//			
//			IDWBase idw = lists.get( 0 );
//			
//			synchronized ( idw ) {
//				
//				if( idw.add() )
//					lists.remove( 0 );
//			}
//			
//			CLog.debug( "数据仓库 数据还剩余个数:" + lists.size() );
		}

	}
	
	public static void main(String[] args) throws InterruptedException {
		
		for( int i = 0; i < 1000; i++ ){
			
//			DWManager.Instance.put( new RecordGoldBase(1111, DWType.BUY_FRIENDS_LIMIT, 15060 ) );
			
			Logs.debug( "添加了" + (i + 1) + "个" );
		}
		
	}
}
