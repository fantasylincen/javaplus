package cn.javaplus.crazy.util;

import java.io.Closeable;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;
import com.lemon.commons.socket.ISocket;

public class Closer {

	public static void close(ResultSet rs, Statement stat) {

		close(rs);

		close(stat);
	}

	public static void close(ResultSet rs, Statement stat, Connection c) {

		close(rs);

		close(stat);

		close(c);
	}

	public static void close(ResultSet o) {

		if (o == null) {
			return;
		}

		try {
			o.close();
		} catch (Throwable e) {
			// System.err.println("close(ResultSet ss) 异常:" + e.getMessage());
		}
	}

	public static void close(Statement o) {

		if (o == null) {
			return;
		}

		try {
			o.close();
		} catch (Throwable e) {
			// System.err.println("close(Statement ss) 异常:" + e.getMessage());
		}
	}

	public static void close(Closeable... o) {
		for (Closeable c : o) {
			if (c != null) {
				try {
					c.close();
				} catch (Throwable e) {
					// System.err.println("close(Closeable... o) 异常:"
					// + e.getMessage());
				}
			}
		}
	}

	private static CloserThread t = new CloserThread();

	/**
	 * 5秒后 关闭old
	 * 
	 * @param old
	 */
	public static void closeDelay(ISocket old) {

		if (t == null) {

			t = new CloserThread();

			t.push(old);

			t.start();

		} else {

			t.push(old);
		}

	}

	public static class DelayObject {

		private ISocket old;
		private long addTime;

		public DelayObject(ISocket old) {
			this.old = old;
			addTime = System.currentTimeMillis();
		}

		public long getAddTime() {
			return addTime;
		}

		public ISocket getOld() {
			return old;
		}

	}

	/**
	 * 延时5秒关闭套接字线程
	 * 
	 * @author 林岑
	 * 
	 */
	public static class CloserThread extends Thread {

		private List<DelayObject> list = Lists.newArrayList();

		@Override
		public void run() {

			while (!list.isEmpty()) {

				closeAll();

				cn.javaplus.util.Util.Thread.sleep(1000);
			}

			t = null;
		}

		private void closeAll() {

			Iterator<DelayObject> it = list.iterator();

			while (it.hasNext()) {

				Closer.DelayObject d = (Closer.DelayObject) it.next();

				if (System.currentTimeMillis() - d.getAddTime() > 5000) {

					d.getOld().close();

					it.remove();
				}
			}
		}

		public void push(ISocket old) {

			DelayObject d = new DelayObject(old);

			this.list.add(d);
		}
	}

	public static void close(Connection o) {

		if (o == null) {

			return;
		}
		try {
			o.close();
		} catch (Throwable e) {
			// System.err.println("close(Connection o) 异常:" + e.getMessage());
		}
	}

	public static void close(Socket o) {
		if (o == null) {
			return;
		}

		try {
			o.close();
		} catch (Throwable e) {
			// System.err.println("close(Socket o) 异常:" + e.getMessage());
		}
	}

	public static void close(ServerSocket ss) {
		if (ss != null) {
			try {
				ss.close();
			} catch (Throwable e) {
				// System.err.println("close(ServerSocket ss) 异常:"
				// + e.getMessage());
			}
		}
	}

	public static void close(PreparedStatement ps, Connection c) {
		close(ps);
		close(c);
	}

}
