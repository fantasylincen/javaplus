package cn.mxz.gm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import cn.javaplus.exception.SQLRuntimeException;
import cn.javaplus.util.Closer;
import cn.mxz.city.City;

import com.lemon.commons.database.ConnectionFetcher;

import db.GameDB;

enum LogType{ MONEY,PROP}

public class LogViewHandler extends AbstractHandler {
	
	
	
	private static DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
	
	private final LogType logType;
	
	public LogViewHandler( LogType logType ) {
		this.logType = logType;
	}
	@Override
	protected String doGet(Map<String, Object> parameters) {
		
		
		City user = getUser( parameters );
		if( user == null ){			
			return responseErr( ErrorCode.USER_NOT_FOUND ); 
		}
		if( logType == LogType.MONEY){
			return buildMoneyLog( parameters, user );
		}
		else{
			return buildPropLog( parameters, user );
		}
	}

	private String buildPropLog(Map<String, Object> parameters, City user) {
		return null;
	}
	private String buildMoneyLog(Map<String, Object> parameters, City user) {
		StringBuilder sb = new StringBuilder( "<?xml version=\"1.0\" encoding=\"utf-8\"?><Response><result>1</result>");
		buildLog( parameters, user, sb );
		sb.append("</Response>");
		return sb.toString();
	}
	/**
	 * 
	 * @param parameters
	 * @param user
	 * @param sb 
	 * @param start
	 * @param end
	 * @param type		1:金钱   2元宝
	 * @return
	 */
	private void buildLog(Map<String, Object> parameters, City user, StringBuilder sb ) {
		DateTime start = DateTime.parse( parameters.get("start_time").toString(), format );
		DateTime end = DateTime.parse( parameters.get("end_time").toString(), format );
		int type = Integer.parseInt( (String) parameters.get("item_type") );
		String sql = "";
		if( type ==1 ){
			sql = "SELECT * FROM log_consume WHERE uname = ? and time BETWEEN ? AND ? and cash_count <> 0";// LIMIT 0, 1000";
		}
		else{
			sql = "SELECT * FROM log_consume WHERE uname = ? and time BETWEEN ? AND ? and gold_count <> 0";// LIMIT 0, 1000";
		}
		
		buildRecord(sql, user.getId(), start, end, type, sb );
	}
	
	private void buildRecord( String sql, String uname, DateTime start, DateTime end, int type, StringBuilder sb ){
		ConnectionFetcher fetcher = GameDB.getInstance();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connection = null;
		
		int startSec = (int) (start.getMillis() / 1000);
		int endSec = (int) (end.getMillis() / 1000);

		try {
			connection = fetcher.getConnection();
			ps = connection.prepareStatement(sql);

			ps.setString(1, uname);
			ps.setInt(2, startSec);
			ps.setInt(3, endSec);

			rs = ps.executeQuery();
			int count = 0;

			while (rs.next()) {

				builOne( rs, type, sb );
				count++;
			}
			sb.append("<count>").append(count).append("</count>");
			//System.out.println(sql);

		} catch (SQLException e) {

			throw new SQLRuntimeException(e);

		} finally {

			Closer.close(rs, ps);

			Closer.close(connection);
		}

	}
	
	private void builOne( ResultSet rs, int type, StringBuilder sb ) throws SQLException{
		sb.append("<details_info>");
		DateTime time = new DateTime( rs.getInt( "time" ) * 1000l );
		sb.append("<info name=\"time\" text=\"操作时间\" >").append( time.toString("yyyy/MM/dd HH:mm:ss ") ).append("</info>");
		if( type == 1){
			sb.append("<info name=\"item_num\" text=\"操作数量\" >").append( rs.getInt("cash_count") ).append("</info>");
		}
		else{
			sb.append("<info name=\"item_num\" text=\"操作数量\" >").append( rs.getInt("gold_count") ).append("</info>");
		}
		//sb.append("<info name=\"item_total\" text=\"总量\" >").append( rs.getInt("cash_count") ).append("</info>");数据库未存储
		sb.append("<info name=\"op_param1\" text=\"操作参数\" >").append( rs.getString("comment") ).append("</info>");
		sb.append("</details_info>");
	}
	
	public static void main(String[] args) {
		DateTime dateTime2 = DateTime.parse("2012-12-21 00:00:00", format );
		System.out.println( dateTime2 );
	}
	
	

}
