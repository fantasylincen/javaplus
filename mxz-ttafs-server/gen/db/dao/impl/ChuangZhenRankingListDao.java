package db.dao.impl;import java.util.List;import java.util.ArrayList;import java.sql.PreparedStatement;import java.sql.ResultSet;import java.sql.SQLException;import db.domain.ChuangZhenRankingList;import cn.javaplus.util.Closer;import java.sql.Connection;import com.lemon.commons.database.ConnectionFetcher;import cn.javaplus.util.Util;import db.domain.*;//该文件自动生成, 禁止手动修改!public class ChuangZhenRankingListDao {	private ConnectionFetcher	fetcher;	public ChuangZhenRankingListDao(ConnectionFetcher fetcher) {		this.fetcher = fetcher;	}	public List<ChuangZhenRankingList> getAll() {		PreparedStatement ps = null;		ResultSet rs = null;		Connection connection = null;		List<ChuangZhenRankingList> czrlos = new ArrayList<ChuangZhenRankingList>();		try {			String sql = "select * from chuang_zhen_ranking_list";			connection = fetcher.getConnection();			ps = connection.prepareStatement(sql);			rs = ps.executeQuery();			while (rs.next()) {				ChuangZhenRankingList czrlo = new ChuangZhenRankingListImpl();				czrlo = mapping(rs);				czrlos.add(czrlo);			}		} catch (SQLException e) {			throw Util.Exception.toRuntimeException(e);		} finally {			Closer.close(rs, ps);			Closer.close(connection);		}		return czrlos;	}	public int getCount() {		PreparedStatement ps = null; 		ResultSet rs = null;		Connection connection = null;		int count = 0; 		try {			String sql = "select count(*) from chuang_zhen_ranking_list";			connection = fetcher.getConnection();			ps = connection.prepareStatement(sql);			rs = ps.executeQuery();			while (rs.next()) {				count =  rs.getInt(1);			}		} catch (SQLException e) {			throw Util.Exception.toRuntimeException(e);		} finally { 			Closer.close(rs, ps);			Closer.close(connection);		} 		return count; 	}	private ChuangZhenRankingList mapping(ResultSet rs) throws SQLException {		ChuangZhenRankingList czrlo = new ChuangZhenRankingListImpl();				czrlo.setUname(rs.getString("uname"));		
		czrlo.setStar(rs.getInt("star"));		
		czrlo.setStarRemain(rs.getInt("star_remain"));		
		czrlo.setFloor(rs.getInt("floor"));		
		czrlo.setTimes(rs.getInt("times"));		
		czrlo.setAttack(rs.getFloat("attack"));		
		czrlo.setDefend(rs.getFloat("defend"));		
		czrlo.setMAttack(rs.getFloat("m_attack"));		
		czrlo.setMDefend(rs.getFloat("m_defend"));		
		czrlo.setHp(rs.getFloat("hp"));		
		czrlo.setSpeed(rs.getFloat("speed"));		
		czrlo.setStarMaxToday(rs.getInt("star_max_today"));		
		czrlo.setFloorMaxToday(rs.getInt("floor_max_today"));		return czrlo;	}}/* ChuangZhenRankingListczrlo		czrlo.setUname(rs.getString("uname"));		
		czrlo.setStar(rs.getInt("star"));		
		czrlo.setStarRemain(rs.getInt("star_remain"));		
		czrlo.setFloor(rs.getInt("floor"));		
		czrlo.setTimes(rs.getInt("times"));		
		czrlo.setAttack(rs.getFloat("attack"));		
		czrlo.setDefend(rs.getFloat("defend"));		
		czrlo.setMAttack(rs.getFloat("m_attack"));		
		czrlo.setMDefend(rs.getFloat("m_defend"));		
		czrlo.setHp(rs.getFloat("hp"));		
		czrlo.setSpeed(rs.getFloat("speed"));		
		czrlo.setStarMaxToday(rs.getInt("star_max_today"));		
		czrlo.setFloorMaxToday(rs.getInt("floor_max_today"));*/