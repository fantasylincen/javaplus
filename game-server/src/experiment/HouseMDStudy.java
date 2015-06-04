package experiment;

import java.util.Random;

/**
 * 用于测试在线调试技术HouseMD 

 * @author admin
 * 2012-8-23 下午12:09:35
 */
public class HouseMDStudy {

	int count = 0;
	void excute( int n ) throws InterruptedException{
		count++;
		Thread.sleep( n * 1000 );
		System.out.println( n );
	}
	public static void main(String[] args) throws InterruptedException {
		HouseMDStudy t = new HouseMDStudy();
		while( true ){
			int n = new Random().nextInt(3) + 1;
			t.excute(n);
		}
	}
}
