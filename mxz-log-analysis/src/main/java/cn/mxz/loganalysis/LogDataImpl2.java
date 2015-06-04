//package cn.mxz.loganalysis;
//
//import java.sql.Date;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class LogDataImpl2 implements LogData {
//
//	public LogDataImpl2(String s) {
////		INSERT INTO `logtable` VALUES ('2014-05-12 16:50:55', '559002', '', 'Init GameDB Successful!');
//		Pattern pattern = Pattern.compile("\'.+\'");
//		Matcher m = pattern.matcher(s);
//		m.find();
//		String group = m.group();
//		String[] ss = group.split(",");
////		roleId = ss[1].replaceAll("'", "");
////		nick = ss[2].replaceAll("'", "");
////		time = new Long(ss[3].replaceAll("'", "").trim());
////		isLogin = ss[4].replaceAll("'", "").trim().equals("1");
//	}
//
//	public int getServerId() {
//		return 0;
//	}
//
//	public String getHead() {
//		return null;
//	}
//
//	public String getLog() {
//		return null;
//	}
//
//
//	public Date getTime() {
//		return null;
//	}
//
//}
