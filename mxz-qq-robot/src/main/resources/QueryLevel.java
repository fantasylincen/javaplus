import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.javaplus.collections.counter.Counter;
import cn.javaplus.collections.counter.ICounter;
import cn.javaplus.util.Closer;
import cn.javaplus.util.Util;

import com.lemon.commons.database.ConnectionFetcher;

import db.GameDB;

public class QueryLevel {

	public String run() {
		return "RUN_FOUNCTION";
	}

	public String query() {
		ConnectionFetcher fetcher = GameDB.getInstance();

		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection c = fetcher.getConnection();

		StringBuilder sb = new StringBuilder();

		try {
			String sql = "SELECT level FROM new_fighter where type_id like '3%'";
			ps = c.prepareStatement(sql);
			rs = ps.executeQuery();

			ICounter<Integer> counter = new Counter<Integer>();

			while (rs.next()) {
				int level = getLevel(rs);
				counter.add(level, 1);
			}

			for (int level = 0; level < 101; level++) {
				sb.append("等级:" + level + " 人数:" + counter.get(level));
				sb.append("<br>");
			}

		} catch (SQLException e) {
			throw Util.Exception.toRuntimeException(e);
		} finally {
			Closer.close(rs, ps, c);
		}

		return sb.toString();
	}

	private int getLevel(ResultSet rs) {
		try {
			return rs.getInt("level");
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
}