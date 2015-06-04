package cn.javaplus.cubie.driver;

import java.io.Closeable;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

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
