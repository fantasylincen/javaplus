package cn.mxz.base.telnet.command.system.query;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.KeyValueDao;
import mongo.gen.MongoGen.KeyValueDto;

import org.springframework.stereotype.Component;

import cn.javaplus.exception.SQLRuntimeException;
import cn.javaplus.util.Closer;
import cn.javaplus.util.Util;
import cn.mxz.base.telnet.TelnetCommand;
import cn.mxz.util.debuger.Debuger;
import db.GameDB;

/**
 * 查询系统消费情况
 * @author 林岑
 *
 */
@Component("telnetCommand:queryoperationlog")

public class QueryOperationLog implements TelnetCommand {

	public class MyComparator implements Comparator<String> {

		@Override
		public int compare(String o1, String o2) {

			return getJunZhi(o2) - getJunZhi(o1);
		}

		private int getJunZhi(String o1) {

			String replaceAll = o1.replaceAll(".*均值:", "").replaceAll(" ms", "");

			return new Integer(replaceAll);
		}

	}

	@Override
	public void run(PrintWriter out, String... args) {

		PreparedStatement ps = null;

		ResultSet rs = null;

		List<String> ls = new ArrayList<String>();

		Connection connection = GameDB.getInstance().getConnection();

		try {

			String sql = "select k from key_value where k like 'SERVICE|%'";

			ps = connection.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {

				String string = rs.getString(1);

				ls.add(string);
			}

		} catch (SQLException e) {

			throw new SQLRuntimeException(e);

		} finally {

			Closer.close(rs, ps);

			Closer.close(connection);
		}

		find(out, ls);
	}

	private void find(PrintWriter out, List<String> ls) {


		KeyValueDao DAO = Daos.getKeyValueDao();

		for (String text : ls) {

			KeyValueDto kv = DAO.get(text);

			int times = getTimes(kv.getV());

			Double timeUsed2 = getTimeUsed(kv.getV());

			String a = (timeUsed2 / times + "").replaceAll("\\..*", "");;

			String timeUsed = (timeUsed2 + "").replaceAll("\\..*", "");

			String k = kv.getK().replaceAll("SERVICE\\|", "").replaceAll("ServiceImpl", "");

			String times2 = times + "";

			out.println(Util.Str.polishing(k, 28) + "操作次数:"  + Util.Str.polishing(times2, 9) + "累计耗时: " + Util.Str.polishing(timeUsed, 10) + " ms" + " 均值:" + a + " ms");
		}

		Debuger.debug("QueryOperationLog.find()");

		for (String string : ls) {

			Debuger.debug(string);
		}
	}

	private Integer getTimes(String v) {

		String[] split = v.split("\\|");

		return new Integer(split[0]);
	}

	private Double getTimeUsed(String kv) {
		return new Double(kv.split("\\|")[1]);
	}
}
