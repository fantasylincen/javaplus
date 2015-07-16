package cn.javaplus.other.ipupdator;

public class App {
	public static void main(String[] args) {
		new Thread(new UpdateThread()).start();
	}
}
