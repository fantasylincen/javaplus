package experiment;

public class TestDeadLock implements Runnable {

	public static void main(String[] args) {
		Thread x1 = new Thread( new TestDeadLock(1) );
		Thread x2 = new Thread( new TestDeadLock(0) );
		x1.start();
		x2.start();
	}

	public int flag;

	TestDeadLock(int flag) {
		this.flag = flag;
	}

	static Object t1 = new Object(), t2 = new Object();// 为什么这里为静态变量就会出现死锁？，不是静态变量就不会！

	public void run() {
		System.out.println("falg == " + flag);
		if (flag == 1) {
			synchronized (t1) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (t2) {
					System.out.println("1");
				}
			}
		}

		if (flag == 0) {
			synchronized (t2) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
				}
				synchronized (t1) {
					System.out.println("0");
				}

			}
		}
	}

}
