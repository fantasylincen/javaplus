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

import mongo.gen.Daos;
import mongo.gen.KeyValueDao;
import mongo.gen.KeyValueDto;
import mongo.gen.MongoCollectionFetcher;
import cn.javaplus.util.Closer;
import cn.javaplus.util.Util;
import cn.mxz.define.ConfigProperties;

import com.google.common.collect.Sets;

public class CreateUserCount {

	private static final String SP = ",,,,,";

	private static final String TABLE_NAME = ConfigProperties
			.getString("TABLE_NAME");

	/**
	 * @param date
	 *            格式 "2014-08-13"
	 * @return
	 */
	public Set<String> getUsers(String timeStart, String timeEnd) {
		// delete(date);
		// Set<String> getFromCache = getFromCache(date);
		// if (getFromCache == null) {
		// Set<String> loadFromDb = loadFromDb(date);
		// saveCache(date, loadFromDb);
		// return loadFromDb;
		// }
		// return getFromCache;

		return loadFromDb(timeStart, timeEnd);
	}

	private void delete(String date) {
		String key = key(date);
		Daos.getKeyValueDao().delete(key);
	}

	private Set<String> getFromCache(String date) {
		KeyValueDao dao = Daos.getKeyValueDao();
		KeyValueDto dto = dao.get(key(date));
		if (dto == null) {
			return null;
		}

		String value = dto.getV();

		String[] split = value.split(SP);
		Set<String> set = Sets.newHashSet();
		for (String ss : split) {
			if (!ss.trim().isEmpty()) {
				set.add(ss);
			}
		}
		return set;

	}

	private void saveCache(String date, Set<String> loadFromDb) {
		KeyValueDao dao = Daos.getKeyValueDao();
		KeyValueDto dto = dao.createDTO();
		dto.setK(key(date));
		dto.setV(buildValue(loadFromDb));
		dao.save(dto);
	}

	private String key(String date) {
		return "CreateTimeCache:" + date;
	}

	private String buildValue(Set<String> loadFromDb) {
		String value = Util.Collection.linkWith(SP, loadFromDb);
		return value;
	}

	public CreateUserCount() {
		Daos.setCollectionFetcher(new MongoCollectionFetcher());
	}

	private Set<String> loadFromDb(String timeStart, String timeEnd) {
		Set<String> s = Sets.newHashSet();

		Connection connection = LogDB.getInstance().getConnection();
		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date start = null;
		java.util.Date end = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			start = sp.parse(timeStart);
			end = sp.parse(timeEnd);
		} catch (ParseException e1) {
			throw new RuntimeException(e1);
		}
		try {
			String string = "SELECT * FROM "
					+ TABLE_NAME
					+ " WHERE log_time > ? AND log_time < ? AND log_head = 'Service' AND log_text like '%InitServiceImpl.createUser(%nick:%';";
			ps = connection.prepareStatement(string);
			ps.setDate(1, new java.sql.Date(start.getTime()));
			ps.setDate(2, new java.sql.Date(end.getTime()));
			rs = ps.executeQuery();

			while (rs.next()) {

				String log;

				log = rs.getString("log_text");

				String nick = log.replaceAll(".*nick:", "").replaceAll("\\|.*",
						"");
				if (!nick.contains("InitServiceImpl.createUser")) {
					s.add(nick);
				}
			}

		} catch (SQLException e) {
			throw Util.Exception.toRuntimeException(e);
		} finally {
			Closer.close(rs, ps, connection);
		}

		return s;
	}

	public static void main(String[] args) {
		Daos.setCollectionFetcher(new MongoCollectionFetcher());
		CreateUserCount create = new CreateUserCount();
		// CreateUserCount create = new CreateUserCount("2014-08-17",
		// "2014-08-18");

		Set<String> all = create.getUsers("2014-12-03", "2014-12-04");
		for (String string : all) {
			System.out.println(string);
		}
	}

	public Set<String> getUsers(String date) {
		return getUsers(date, getNext(date));
	}

	private String getNext(String date) {

		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
		try {
			java.util.Date d = sp.parse(date);

			long time = d.getTime();
			time += Util.Time.MILES_ONE_DAY;
			d = new Date(time);

			return sp.format(d);

		} catch (ParseException e) {
			throw new RuntimeException(e);
		}

	}
}
