package cn.mxz.gm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import cn.javaplus.exception.SQLRuntimeException;
import cn.javaplus.util.Closer;
import cn.mxz.base.config.KeyValueDefine;
import cn.mxz.base.config.KeyValueManagerImpl;
import cn.mxz.base.config.ServerConfig;
import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import db.GameDB;

public class WatchInfoHandler  extends AbstractHandler {

	
	@Override
	protected String doGet(Map<String, Object> parameters) {
		return buildXmlInfo();
		
	}

	/**
	 * 创建相应的xml信息
	 * @return
	 */
	private String buildXmlInfo() {
		StringBuilder sb = new StringBuilder("");
		//sb.append("<content>");
		sb.append( getOnlineSize() );
		sb.append( getRechargeCount() );
		sb.append( getRechargeAmount() );
		sb.append( getRunTimes() );	
		sb.append( getHistoryHigherCount() );
		sb.append( getAllUserCount() );
		//sb.append("</content>");
		return sb.toString();
	}

	
	/**
	 * 当前在线人数
	 * @return
	 */
	private String getOnlineSize() {
		StringBuilder sb = new StringBuilder();
		sb.append("");
		sb.append( getWorld().getOnlineAll().size() );
		sb.append(",");
		return sb.toString();
	}

	private World getWorld() {
		return WorldFactory.getWorld();
	}
	private String getSize() {
		//return getWorld().getAll().size();
		return "";
	}
	
	/**
	 * 充值单数
	 * @return
	 */
	private String getRechargeCount(){
		int count = 0;
		PreparedStatement ps = null;

		ResultSet rs = null;

		List<String> ls = new ArrayList<String>();

		Connection connection = GameDB.getInstance().getConnection();

		try {

			String sql = "select count(*) from recharge_record";

			ps = connection.prepareStatement(sql);

			

			rs = ps.executeQuery();

			if (rs.next()) {

				count = rs.getInt(1);

			}

		} catch (SQLException e) {

			throw new SQLRuntimeException(e);

		} finally {

			Closer.close(rs, ps);

			Closer.close(connection);
		}

		StringBuilder sb = new StringBuilder();
		sb.append("");
		sb.append(count );
		sb.append(",");
		return sb.toString();
	}
	
	/**
	 * 充值总元宝
	 * @return
	 */
	private String getRechargeAmount(){
		int count = 0;
		PreparedStatement ps = null;

		ResultSet rs = null;

		List<String> ls = new ArrayList<String>();

		Connection connection = GameDB.getInstance().getConnection();

		try {

			String sql = "select sum(amount) from recharge_record";

			ps = connection.prepareStatement(sql);

			

			rs = ps.executeQuery();

			if (rs.next()) {

				count = rs.getInt(1);

			}

		} catch (SQLException e) {

			throw new SQLRuntimeException(e);

		} finally {

			Closer.close(rs, ps);

			Closer.close(connection);
		}

		
		StringBuilder sb = new StringBuilder();
		sb.append("");
		sb.append( count );
		sb.append(",");
		return sb.toString();
	}
	
	/**
	 * 历史最高在线人数
	 * @return
	 */
	
	private String getHistoryHigherCount(){
		KeyValueManagerImpl kv = new KeyValueManagerImpl();
		String his = kv.get(KeyValueDefine.ONLINE_SIZE_MAX_HISTORY);
		
		return his+",";		
	}
	
	private String getAllUserCount(){
		
		int his = WorldFactory.getWorld().getAll().size();
		
		return his+",";		
	}
	
	
	/**
	 * 运行时长
	 * @return
	 */
	private String getRunTimes(){
		StringBuilder sb = new StringBuilder();
		sb.append("");
		int second = (int) (ServerConfig.getServerRunMillis() /1000);
		sb.append( second );
		sb.append(",");
		return sb.toString();
	}
	
}
