package cn.javaplus.twolegs;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

import cn.javaplus.log.Log;
import cn.javaplus.util.Closer;

public class StopServerMonitor extends Thread {

	private static final String COMMAND = "whosyourdaddy";
	private static final int PORT = 63754;

	private StopListener listener;

	public StopServerMonitor(StopListener listener) {
		this.listener = listener;
	}

	public void sendStopRequest() {
		Socket s = null;
		OutputStream os = null;
		try {
			s = new Socket("localhost", PORT);
			os = s.getOutputStream();
			os.write(COMMAND.getBytes());
			os.flush();
		} catch (Exception e) {
			Log.d("Stop server faild : " + e.getMessage());
		} finally {
			Closer.close(os);
			Closer.close(s);
		}
	}

	@Override
	public void run() {
		try {
			ServerSocket ss = new ServerSocket(PORT);
			while (true) {
				Socket s = ss.accept();
				try {
					byte[] data = new byte[1024];
					int read = s.getInputStream().read(data);
					data = Arrays.copyOf(data, read);
					if (new String(data).equals(COMMAND)) {
						if (listener != null) {
							listener.onStop();
							break;
						}
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				} finally {
					s.close();
					Closer.close(ss);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
