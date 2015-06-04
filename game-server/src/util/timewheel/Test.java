package util.timewheel;

import java.util.concurrent.atomic.AtomicInteger;

//import util.SystemTimer;

class Task1 implements ITimerTask {

	static AtomicInteger a = new AtomicInteger();
	int i = 0;
	ITimer timer;

	public Task1(ITimer timer) {
		this.timer = timer;

		i = a.incrementAndGet();
	}

	@Override
	public void run(ITimeout timeout) throws Exception {
		System.out.println(i);
		if (i == 100000) {
			// System.out.println(timer);
		}
	}

}

public class Test {

	public static void main(String[] args) {

		// long begin = System.nanoTime();
		// long time = 0;
		// for( int i = 0; i < 20000000; i++ ){
		// time = System.currentTimeMillis();
		// }
		// System.out.println( System.nanoTime() - begin );
		//
		//
		// begin = System.nanoTime();
		// time = 0;
		// for( int i = 0; i < 20000000; i++ ){
		// time = SystemTimer.currentTimeMillis();
		// }
		// System.out.println( System.nanoTime() - begin );

		// HashedWheelTimer timer = new HashedWheelTimer();
		// timer.start();
		// Task1 t = new Task1( timer );
		//
		// for( int i = 0; i < 10; i++ ){
		// timer.newTimeout( t, 1000 );
		//
		// }

		// for(int number : 1 - 10000) {
		//
		// }

		long start = System.nanoTime();

		AtomicIntegerCompareTest test = new AtomicIntegerCompareTest(0);
		for (int i = 0; i < 1000000; i++) {
			test.increase();
		}
		long end = System.nanoTime();
		System.out.println("time elapse:" + (end - start) / 1000000000f );

		long start1 = System.nanoTime();

		AtomicInteger atomic = new AtomicInteger(0);

		for (int i = 0; i < 1000000; i++) {
			atomic.incrementAndGet();
		}
		long end1 = System.nanoTime();
		System.out.println("time elapse:" + (end1 - start1) / 1000000000f);
	}

}

class AtomicIntegerCompareTest {

	private int value;

	public AtomicIntegerCompareTest(int value) {
		this.value = value;
	}

	public synchronized int increase() {
		return value++;
	}
}
