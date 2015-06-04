import game.log.Logs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import user.UserManager;
import util.db.DatabaseUtil;
import cn.javaplus.string.StringPrinter;
import cn.javaplus.util.Util;

public class OnlineSize {

	public String run() {
		return "RUN_FOUNCTION";
	}

	public String getOnlineSize() {

		StringPrinter out = new StringPrinter();

		out.print("在线人数:");
		out.print(UserManager.getInstance().getMaps().size());
		out.print("<br>");
		out.print("注册人数:");
		out.print(getRegistNum());
		out.print("<br>");
		out.print("累计充值:");
		out.print(getNum());
		out.print("<br>");
		out.print("<br>");
		out.print("<br>");
		out.print("<br>");
		out.print("今日充值:");
		out.print("<br>");
		out.print("<br>");
		out.print("时间&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;帐号&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;充值数");
		out.print("<br>");

		Connection con = DatabaseUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sf.format(new Date(System.currentTimeMillis()));

		String sql = "SELECT uname,money,purchase_date from log_purcharse where purchase_date like '" + today + "%';";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				out.println(rs.getString("purchase_date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("uname") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getFloat("money"));
				out.print("<br>");
			}

		} catch (SQLException e) {
			Logs.error(e.getLocalizedMessage(), e);
		} finally {
			DatabaseUtil.close(null, pst, con);
		}

		return out.toString();
	}

	private int getRegistNum() {
		return (int) DatabaseUtil.getCount( "user_base" );
	}

	private int getNum() {
		Connection con = DatabaseUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;

		String sql = "SELECT sum(money) from log_purcharse";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			if (rs.next())
				return (int) rs.getFloat(1);

		} catch (SQLException e) {
			Logs.error(e.getLocalizedMessage(), e);
		} finally {
			DatabaseUtil.close(null, pst, con);
		}

		return 0;
	}

}