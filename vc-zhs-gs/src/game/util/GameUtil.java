package game.util;

import java.util.Date;

import define.SystemCfg;

/**
 * 游戏工具
 * @author DXF
 *
 */
public class GameUtil {

	private static final long ID_PREFIX = SystemCfg.GAME_DISTRICT * 100000;//

	/**
	 * 生成带有游戏区信息的ID
	 * 方法为：直接把服务器ID放到前两位
	 * @param id
	 * @return
	 */
	public static long buildIdWithDistrict( long id ){
		return id + ID_PREFIX;
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		long begin = System.nanoTime();
		for( int i = 0; i < 100; i++ ){
			System.out.println( buildIdWithDistrict( i ) );
		}
		System.out.println( "耗时" + (System.nanoTime() - begin) / 1000000000f );

		Date date = new Date( 1383183195 * 1000l );
		System.out.println( date );
	}
}
