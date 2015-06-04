package cn.mxz.loganalysis;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Set;

import com.google.common.collect.Sets;

import mongo.gen.Daos;
import mongo.gen.KeyValueDao;
import mongo.gen.KeyValueDto;
import mongo.gen.MongoCollectionFetcher;
import cn.javaplus.util.Closer;
import cn.javaplus.util.Util;
import cn.mxz.define.ConfigProperties;

public class UserCreateTime {

	private static boolean isInit;
	private static final String TABLE_NAME = ConfigProperties
			.getString("TABLE_NAME");

	public UserCreateTime() {

		Daos.setCollectionFetcher(new MongoCollectionFetcher());

		if (!isInit) {
			init();
			isInit = true;
		}
	}

	private void init() {
		System.out.println("初始化所有玩家创建时间...");

		initUserCreateTimes();

		System.out.println("初始化所有玩家创建时间完毕");
	}

	private void initUserCreateTimes() {

		Connection connection = LogDB.getInstance().getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String string = "SELECT * FROM "
					+ TABLE_NAME
					+ " WHERE log_head = 'Service' AND log_text like '%InitServiceImpl.createUser(%nick:%';";
			ps = connection.prepareStatement(string);
			rs = ps.executeQuery();

			while (rs.next()) {

				String log;

				log = rs.getString("log_text");
				Timestamp time = rs.getTimestamp("log_time");

				String nick = log.replaceAll(".*nick:", "").replaceAll("\\|.*",
						"");
				if (!nick.contains("InitServiceImpl.createUser")) {
					// s.add(nick);
					long time2 = time.getTime();

					save(nick, time2);
				}
			}

		} catch (SQLException e) {
			throw Util.Exception.toRuntimeException(e);
		} finally {
			Closer.close(rs, ps, connection);
		}

	}

	private void save(String nick, long time2) {
		KeyValueDao dao = Daos.getKeyValueDao();
		KeyValueDto dto = dao.createDTO();
		dto.setK(key(nick));
		dto.setV(time2 + "");
		dao.save(dto);
	}

	public long getCreateTime(String nick) {
		KeyValueDao dao = Daos.getKeyValueDao();
		KeyValueDto dto = dao.get(key(nick));
		if (dto != null)
			return new Long(dto.getV());

		Connection connection = LogDB.getInstance().getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String string = "SELECT * FROM "
					+ TABLE_NAME
					+ " WHERE log_head = 'Service' AND log_text like '%InitServiceImpl.createUser(%nick:";
			ps = connection.prepareStatement(string);
			rs = ps.executeQuery();

			while (rs.next()) {

				String log;

				log = rs.getString("log_text");

				nick = log.replaceAll(".*nick:", "").replaceAll("\\|.*", "");
				if (!nick.contains("InitServiceImpl.createUser")) {
					Timestamp t = rs.getTimestamp("log_time");
					long time = t.getTime();
					save(nick, time);
					return time;
				}
			}
			throw new RuntimeException("没有找到:" + nick + "的创建时间");

		} catch (SQLException e) {
			throw Util.Exception.toRuntimeException(e);
		} finally {
			Closer.close(rs, ps, connection);
		}

	}

	private static String key(String nick) {
		return "UserCreateTime:" + nick;
	}

	public static void main(String[] args) {
		Daos.setCollectionFetcher(new MongoCollectionFetcher());
		Iterator<LogData> it = new LogDataIterator("2014-12-01", "2014-12-11");
		while (it.hasNext()) {
			LogData logData = (LogData) it.next();
			String log = logData.getLog();

			if (log.contains("InitServiceImpl.createUser(")) {

				Date time = logData.getTime();
				String nick = log.replaceAll(".*nick:", "").replaceAll("\\|.*",
						"");

				KeyValueDao dao = Daos.getKeyValueDao();
				KeyValueDto dto = dao.createDTO();
				dto.setK(key(nick));
				dto.setV(time.getTime() + "");
				dao.save(dto);

				System.out.println("create time " + nick + "  "
						+ time.getTime());
			}
		}

	}
}
