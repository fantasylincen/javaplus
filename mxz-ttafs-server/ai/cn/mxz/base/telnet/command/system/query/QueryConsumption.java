package cn.mxz.base.telnet.command.system.query;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.KeyValueDao;
import mongo.gen.MongoGen.KeyValueDto;

import org.springframework.stereotype.Component;

import cn.javaplus.exception.SQLRuntimeException;
import cn.javaplus.util.Closer;
import cn.javaplus.util.Util;
import cn.mxz.base.telnet.TelnetCommand;
import db.GameDB;

/**
 * 查询系统消费情况
 * @author 林岑
 *
 */
@Component("telnetCommand:queryconsumption")

public class QueryConsumption implements TelnetCommand {

	@Override
	public void run(PrintWriter out, String... args) {

		PreparedStatement ps = null;

		ResultSet rs = null;

		List<String> ls = new ArrayList<String>();

		Connection connection = GameDB.getInstance().getConnection();

		try {

			String sql = "select k from key_value where k like ?";

			ps = connection.prepareStatement(sql);

			ps.setString(1, "GOLD_REDUCE%");

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

			String t = Util.Str.polishing(kv.getK(), 30) + " 消耗 : " + kv.getV() + "元宝";

			out.println(t);
		}
	}

}
