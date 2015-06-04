package user;

import game.events.Event;
import game.log.Logs;

import util.SystemTimer;

/**
 * 玩家的包管理器，主要处理客户端刷包等情况
 * 
 * @author admin
 * 2012-8-15 上午11:50:42
 */
class UserPackageManager {

	/**
	 * 接收相同包号两个包之间允许的最短时间间隔，如果小于这个值则认定客户端有刷包嫌疑，丢弃这个包
	 * 单位	毫秒
	 */
	private static final long 			MIN_INTERVAL_MILS = 0;
	

	/**
	 * 接收的上一个包
	 */
	private Event						lastPack;
	
	/**
	 * 上一次收包时间
	 */
	private	long						lastReceiveTime = 0;

	/**
	 * 检查玩家是否存在断时间内恶意刷大量包的情况<br>
	 * 只有在连续两个包的包号相同并且间隔时间少于规定值的时候，才返回false<br>
	 * 具体规则可能还需要根据实际情况进一步调整
	 * @param packageNo
	 * @return
	 */
	public boolean safeCheck( Event pack ){
		long current = SystemTimer.currentTimeMillis();
		if( pack == lastPack && current - lastReceiveTime < MIN_INTERVAL_MILS ){
			Logs.error( "packag:" + pack + current + "-" + lastReceiveTime  + "=" + (current - lastReceiveTime) );
			return false;
		}
		lastPack 		= pack;
		lastReceiveTime = current;
		return true;
	}
	
	
	public static void main ( String[] args ) {
		//PackageUtil.printAllPakcets( PackageManager.packages );
	}	
}
