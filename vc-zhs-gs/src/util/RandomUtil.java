package util;

import java.util.Random;

/**
 * 游戏中和随机相关的函数
 * @author admin
 * 2012-12-4 下午03:25:09
 */
public class RandomUtil {

	private static final Random r = new Random(); 
	
	/**
	 * min和max都必须大于0
	 * @param min
	 * @param max
	 * @return
	 * 			在min和max中随机一个数字，包括上下限[min,max]
	 */
	public static int getRandomInt( int min, int max ) {
		if( min < 0 || max == Integer.MAX_VALUE ){
			throw new IllegalArgumentException( "随机数下限不得<0，上限不得>=2的32次方" );
		}
		++max;
		int n = max - min;
		if( n <= 0 ) n = 1;
		
		int ret = r.nextInt( n );
		
		return min + ret;
	}
	public static void main(String[] args) {
		
		int n = Integer.MAX_VALUE;
		System.out.println( n );
		n++;
		System.out.println( n );
		
		System.out.println( r.nextInt() );
	}
}
