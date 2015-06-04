

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import cn.javaplus.debug.Debuger;
import cn.javaplus.server.GameThread;

public class Server {

	public static void main(String[] args) throws IOException {

		ServerSocket ss = new ServerSocket(16464);
		Debuger.debug("ServerTest.main() Server start!");

		while (true) {
			Socket socket = ss.accept();
			new GameThread(socket).start();
			Debuger.debug("ServerTest.main() Listening....");
		}
	}
}
