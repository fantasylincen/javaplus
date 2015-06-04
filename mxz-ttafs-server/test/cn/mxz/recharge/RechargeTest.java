package cn.mxz.recharge;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;

import cn.javaplus.util.Closer;
import cn.mxz.RechargeServerDefine;

/**
 * 充值测试
 * @author 林岑
 *
 */
public class RechargeTest {

	public static void main(String[] args) throws UnknownHostException, IOException {

		Socket socket = null;
		
		try {
			
			socket = new Socket("219.153.48.56", RechargeServerDefine.SERVER_PORT);

			OutputStream os = socket.getOutputStream();

			PrintWriter pw = new PrintWriter(os);

			pw.println("10003:qw100:1000");
			pw.println("10003:qw100:1002");
			pw.println("10003:qw100:1003");

			pw.flush();

			new ResultThread(socket.getInputStream()).start();

			System.out.println("发送成功");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Closer.close(socket);
		}
	}
	
	private static class ResultThread extends Thread {

		private InputStream		inputStream;
		private BufferedReader	br;

		public ResultThread(InputStream inputStream) {
			this.inputStream = inputStream;
			try {
				br = new BufferedReader(new InputStreamReader(inputStream, "utf8"));
			} catch (UnsupportedEncodingException e) {
				throw cn.javaplus.util.Util.Exception.toRuntimeException(e);
			}
		}

		@Override
		public void run() {
			String line;
			try {
				line = br.readLine();
				System.out.println(line);
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				Closer.close(br);
				Closer.close(inputStream);
			}
		}
	}
}
