//package cn.mxz.c3p0;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//import cn.javaplus.common.util.Closer;
//import cn.mxz.base.config.Cfg;
//
//import com.lemon.commons.database.DBProperties;
//
//import db.DataBasePoolC3P0;
//
//public class Test {
//	public static void main(String[] args) throws SQLException {
//		DBProperties p = new DBProperties();
//		p.setDataCon("jdbc:mysql://192.168.1.54:3306/lk");
//		p.setDrives(Cfg.DBDriver);
//		p.setUser("root");
//		p.setPassword("mxz2013");
//
//		DataBasePoolC3P0 db = new DataBasePoolC3P0(p);
//
//	long t1 = System.currentTimeMillis();
//
//		for (int i = 5000; i < 10000; i++) {
//			Connection connection = null;
//			PreparedStatement ps = null;
//
//			try {
//				connection = db.getConnection();
//				ps = connection.prepareStatement("insert into formation ( userName,templetId,levels ) values ( ?,?,? )");
//				ps.setString(1, "userxxx" + i);
//				ps.setInt(2, i);
//				ps.setInt(3, i);
//				ps.executeUpdate();
//				System.out.println(i);
//			} finally {
//				Closer.close(connection);
//				Closer.close(ps);
//			}
//		}
//		System.out.println("耗时:" + (System.currentTimeMillis() - t1));
//	}
//}
