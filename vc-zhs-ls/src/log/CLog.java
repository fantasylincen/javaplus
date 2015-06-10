package log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.SystemTimer;
import util.UtilBase;

/**
 * 屏幕输出打印
 * @author DXF
 *
 */
public class CLog {

	private final static Logger 			logger = LoggerFactory.getLogger(CLog.class);
	
	/**
	 * 正常输出
	 * @param log
	 */
	public static void log( String log )
	{
		System.out.println( getNow() + "：\t" + log );
	}
	
	/**
	 * 错误输出
	 * @param log
	 */
	public static void error( String log )
	{
		System.err.println( getNow() + "：\t" + log );
	}
	
	/**
	 * debug输出
	 * @param log
	 */
	public static void debug( String log )
	{
		System.out.println( getNow() + "：\t" + log );
	}
	
	/** 获取当前系统时间  以字符串模式   */
	public static String getNow(){
		return UtilBase.secondsToDateStr( SystemTimer.currentTimeSecond() );
	}
	
	public static void main(String[] args){
		
		long startTime = System.nanoTime();
		for( int i = 0; i < 100000; i ++ )
		{
			log("asdsadsa" + i);
			error("asdasdsadas" + i );
		}
		System.out.println( "用时 ："  + ( System.nanoTime() - startTime ) / 1000000000f );
		
		logger.debug( "asdasdsadsa" );
		logger.error( "asdasdsadsa" );
		
	}
}
