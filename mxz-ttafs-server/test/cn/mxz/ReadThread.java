package cn.mxz;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

public class ReadThread extends Thread {
	private Socket	socket;

	public ReadThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		while(true) {
			byte[] data = new byte[10240];

			try {
				int read = socket.getInputStream().read(data);
				data = Arrays.copyOf(data, read);
				System.out.println("ReadThread.run()" + new String(data));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
