package cn.javaplus.cubie.driver;

import java.io.FileNotFoundException;
import java.io.PrintStream;

import cn.javaplus.cubie.driver.Pin;

public class App {
	public static void main(String[] args) throws FileNotFoundException {

//		Pin.PG9.setOut();
//		final PrintStream out;
//		try {
//			out = new PrintStream("/sys/class/gpio/gpio17_pg9/value");
//		} catch (FileNotFoundException e) {
//			throw new RuntimeException(e);
//		}

		
		new Thread() {
			public void run() {

				int i = 0;
				while (true) {
					i++;
					
					if(i % 1000 == 0)
						System.out.println(i);
//					if (Pin.PG9.isOpen()) {
						Pin.PG9.close();
//					} else {
						Pin.PG9.open();
//						sp(30);
//						sp(30);
//					}

//					out.println(0);
//					out.println(1);
				}
			}

			private void sp(int i) {
				try {
					sleep(i);
				} catch (InterruptedException e) {
				}
			};
		}.start();
	}
}
