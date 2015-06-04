package db.dao.impl;import java.util.List;import java.util.ArrayList;import java.sql.PreparedStatement;import java.sql.ResultSet;import java.sql.Timestamp;import java.sql.SQLException;import db.domain.NewFighter;import cn.javaplus.util.Closer;import java.sql.Connection;import com.lemon.commons.database.ConnectionFetcher;import cn.javaplus.util.Util;import db.domain.*;import java.sql.Blob;import java.sql.Date;import java.sql.Time;//这一版本的Dao, 是第一版本的Dao, 不带任何缓存//该文件自动生成, 禁止手动修改!public class NewFighterDao1 implements NewFighterDao{class StatementSetter {		private PreparedStatement ps;		private int index = 1;		public StatementSetter(PreparedStatement ps) {			this.ps = ps;		}		void setNext(Integer value) throws SQLException {			ps.setInt(index++, value);		}		void setNext(Byte value) throws SQLException {			ps.setByte(index++, value);		}		void setNext(Blob value) throws SQLException {			ps.setBlob(index++, value);		}		void setNext(Short value) throws SQLException {			ps.setShort(index++, value);		}		void setNext(String value) throws SQLException {			ps.setString(index++, value);		}		void setNext(Float value) throws SQLException {			ps.setFloat(index++, value);		}		void setNext(byte[] value) throws SQLException {			ps.setBytes(index++, value);		}		void setNext(Boolean value) throws SQLException {			ps.setBoolean(index++, value);		}		void setNext(Date value) throws SQLException {			Timestamp tttt = new Timestamp(value.getTime());			ps.setTimestamp(index++, tttt);		}		void setNext(Timestamp value) throws SQLException {			ps.setTimestamp(index++, value);		}		void setNext(Long value) throws SQLException {			ps.setLong(index++, value);		}		void setNext(Double value) throws SQLException {			ps.setDouble(index++, value);		}		void setNext(Time value) throws SQLException {			ps.setTime(index++, value);		}		void setNext(Object value) throws SQLException {			if (value == null) {				throw new NullPointerException("不能为null");			}						if (value instanceof Integer) {				setNext((Integer) value);			} else if (value instanceof Byte) {				setNext((Byte) value);			} else if (value instanceof Blob) {				setNext((Blob) value);			} else if (value instanceof Short) {				setNext((Short) value);			} else if (value instanceof String) {				setNext((String) value);			} else if (value instanceof Float) {				setNext((Float) value);			} else if (value instanceof byte[]) {				setNext((byte[]) value);			} else if (value instanceof Boolean) {				setNext((Boolean) value);			} else if (value instanceof Date) {				Date date = (Date) value;				Timestamp tttt = new Timestamp(date.getTime());				setNext(tttt);			} else if (value instanceof Long) {				setNext((Long) value);			} else if (value instanceof Double) {				setNext((Double) value);			} else if (value instanceof Time) {				setNext((Time) value);			} else {				throw new RuntimeException("无法识别的类型:" + value.getClass());			}		}		public void addBatch() throws SQLException {			index = 1;			ps.addBatch();		}	}		private ConnectionFetcher	fetcher;	public NewFighterDao1(ConnectionFetcher fetcher) {		this.fetcher = fetcher;	}	private static boolean hasLog = db.dao.impl.DBConfig.hasLog;	public void add(NewFighter nfo) {		PreparedStatement ps = null;		ResultSet rs = null;		Connection c = fetcher.getConnection();		try {			String sql = "INSERT INTO new_fighter (uname, type_id, level, exp, v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19, v20, yuanshen_type1, yuanshen_type2, yuanshen_type3, yuanshen_type4, yuanshen_type5, yuanshen_type6, yuanshen_type7, yuanshen_type8, yuanshen_type9, yuanshen_type10, yuanshen_level1, yuanshen_level2, yuanshen_level3, yuanshen_level4, yuanshen_level5, yuanshen_level6, yuanshen_level7, yuanshen_level8, yuanshen_level9, yuanshen_level10, yuanshen_exp1, yuanshen_exp2, yuanshen_exp3, yuanshen_exp4, yuanshen_exp5, yuanshen_exp6, yuanshen_exp7, yuanshen_exp8, yuanshen_exp9, yuanshen_exp10) "					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";			ps = c.prepareStatement(sql);			StatementSetter ss = new StatementSetter(ps);				ss.setNext(nfo.getUname());
				ss.setNext(nfo.getTypeId());
				ss.setNext(nfo.getLevel());
				ss.setNext(nfo.getExp());
				for(int i = 0; i < 20; i++) {
					ss.setNext(nfo.getV(i));
				}
				for(int i = 0; i < 10; i++) {
					ss.setNext(nfo.getYuanshenType(i));
				}
				for(int i = 0; i < 10; i++) {
					ss.setNext(nfo.getYuanshenLevel(i));
				}
				for(int i = 0; i < 10; i++) {
					ss.setNext(nfo.getYuanshenExp(i));
				}
			ps.executeUpdate();			if(hasLog)				System.out.println(sql);		} catch (SQLException e) {			throw Util.Exception.toRuntimeException(e);		} finally {			Closer.close(rs, ps, c);		}	}		public void addAll(List<NewFighter> nfos) {		PreparedStatement ps = null;		ResultSet rs = null;		Connection c = fetcher.getConnection();		try {			String sql = "INSERT INTO new_fighter (uname, type_id, level, exp, v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19, v20, yuanshen_type1, yuanshen_type2, yuanshen_type3, yuanshen_type4, yuanshen_type5, yuanshen_type6, yuanshen_type7, yuanshen_type8, yuanshen_type9, yuanshen_type10, yuanshen_level1, yuanshen_level2, yuanshen_level3, yuanshen_level4, yuanshen_level5, yuanshen_level6, yuanshen_level7, yuanshen_level8, yuanshen_level9, yuanshen_level10, yuanshen_exp1, yuanshen_exp2, yuanshen_exp3, yuanshen_exp4, yuanshen_exp5, yuanshen_exp6, yuanshen_exp7, yuanshen_exp8, yuanshen_exp9, yuanshen_exp10) "					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";			ps = c.prepareStatement(sql);			StatementSetter ss = new StatementSetter(ps);			for (NewFighter nfo : nfos) {				ss.setNext(nfo.getUname());
				ss.setNext(nfo.getTypeId());
				ss.setNext(nfo.getLevel());
				ss.setNext(nfo.getExp());
				for(int i = 0; i < 20; i++) {
					ss.setNext(nfo.getV(i));
				}
				for(int i = 0; i < 10; i++) {
					ss.setNext(nfo.getYuanshenType(i));
				}
				for(int i = 0; i < 10; i++) {
					ss.setNext(nfo.getYuanshenLevel(i));
				}
				for(int i = 0; i < 10; i++) {
					ss.setNext(nfo.getYuanshenExp(i));
				}
				ss.addBatch();			}			ps.executeBatch();			if(hasLog)				System.out.println(sql);		} catch (SQLException e) {			throw Util.Exception.toRuntimeException(e);		} finally {			Closer.close(rs, ps, c);		}	}	public void delete(Integer type_id, String uname) {		ResultSet rs = null;		PreparedStatement ps = null;		Connection c = fetcher.getConnection();		try {			String sql = "DELETE FROM new_fighter WHERE type_id = ? AND uname = ?";			ps = c.prepareStatement(sql);			StatementSetter ss = new StatementSetter(ps);			ss.setNext(type_id);
			ss.setNext(uname);
			ps.executeUpdate();			if(hasLog)				System.out.println(sql); 		} catch (SQLException e) {			throw Util.Exception.toRuntimeException(e);		} finally {			Closer.close(rs, ps, c);		}	}	public void update(NewFighter nfo) {		PreparedStatement ps = null;		ResultSet rs = null;		Connection c = fetcher.getConnection();		try {			String sql = "UPDATE new_fighter SET level = ?, exp = ?, v1 = ?, v2 = ?, v3 = ?, v4 = ?, v5 = ?, v6 = ?, v7 = ?, v8 = ?, v9 = ?, v10 = ?, v11 = ?, v12 = ?, v13 = ?, v14 = ?, v15 = ?, v16 = ?, v17 = ?, v18 = ?, v19 = ?, v20 = ?, yuanshen_type1 = ?, yuanshen_type2 = ?, yuanshen_type3 = ?, yuanshen_type4 = ?, yuanshen_type5 = ?, yuanshen_type6 = ?, yuanshen_type7 = ?, yuanshen_type8 = ?, yuanshen_type9 = ?, yuanshen_type10 = ?, yuanshen_level1 = ?, yuanshen_level2 = ?, yuanshen_level3 = ?, yuanshen_level4 = ?, yuanshen_level5 = ?, yuanshen_level6 = ?, yuanshen_level7 = ?, yuanshen_level8 = ?, yuanshen_level9 = ?, yuanshen_level10 = ?, yuanshen_exp1 = ?, yuanshen_exp2 = ?, yuanshen_exp3 = ?, yuanshen_exp4 = ?, yuanshen_exp5 = ?, yuanshen_exp6 = ?, yuanshen_exp7 = ?, yuanshen_exp8 = ?, yuanshen_exp9 = ?, yuanshen_exp10 = ? WHERE type_id = ? AND uname = ?";			ps = c.prepareStatement(sql);			StatementSetter ss = new StatementSetter(ps);			ss.setNext(nfo.getLevel());
			ss.setNext(nfo.getExp());
			for(int i = 0; i < 20; i++) {
				ss.setNext(nfo.getV(i));
			}

			for(int i = 0; i < 10; i++) {
				ss.setNext(nfo.getYuanshenType(i));
			}

			for(int i = 0; i < 10; i++) {
				ss.setNext(nfo.getYuanshenLevel(i));
			}

			for(int i = 0; i < 10; i++) {
				ss.setNext(nfo.getYuanshenExp(i));
			}

			ss.setNext(nfo.getTypeId());
			ss.setNext(nfo.getUname());
			ps.executeUpdate();			if(hasLog)				System.out.println(sql);		} catch (SQLException e) {			throw Util.Exception.toRuntimeException(e);		} finally {			Closer.close(rs, ps, c);		}	}			public void update(List<NewFighter> nfos) {		PreparedStatement ps = null;		ResultSet rs = null;		Connection c = fetcher.getConnection();		try {			String sql = "UPDATE new_fighter SET level = ?, exp = ?, v1 = ?, v2 = ?, v3 = ?, v4 = ?, v5 = ?, v6 = ?, v7 = ?, v8 = ?, v9 = ?, v10 = ?, v11 = ?, v12 = ?, v13 = ?, v14 = ?, v15 = ?, v16 = ?, v17 = ?, v18 = ?, v19 = ?, v20 = ?, yuanshen_type1 = ?, yuanshen_type2 = ?, yuanshen_type3 = ?, yuanshen_type4 = ?, yuanshen_type5 = ?, yuanshen_type6 = ?, yuanshen_type7 = ?, yuanshen_type8 = ?, yuanshen_type9 = ?, yuanshen_type10 = ?, yuanshen_level1 = ?, yuanshen_level2 = ?, yuanshen_level3 = ?, yuanshen_level4 = ?, yuanshen_level5 = ?, yuanshen_level6 = ?, yuanshen_level7 = ?, yuanshen_level8 = ?, yuanshen_level9 = ?, yuanshen_level10 = ?, yuanshen_exp1 = ?, yuanshen_exp2 = ?, yuanshen_exp3 = ?, yuanshen_exp4 = ?, yuanshen_exp5 = ?, yuanshen_exp6 = ?, yuanshen_exp7 = ?, yuanshen_exp8 = ?, yuanshen_exp9 = ?, yuanshen_exp10 = ? WHERE type_id = ? AND uname = ?";			ps = c.prepareStatement(sql);			StatementSetter ss = new StatementSetter(ps);			for(NewFighter nfo: nfos) {			ss.setNext(nfo.getLevel());
			ss.setNext(nfo.getExp());
			for(int i = 0; i < 20; i++) {
				ss.setNext(nfo.getV(i));
			}

			for(int i = 0; i < 10; i++) {
				ss.setNext(nfo.getYuanshenType(i));
			}

			for(int i = 0; i < 10; i++) {
				ss.setNext(nfo.getYuanshenLevel(i));
			}

			for(int i = 0; i < 10; i++) {
				ss.setNext(nfo.getYuanshenExp(i));
			}

			ss.setNext(nfo.getTypeId());
			ss.setNext(nfo.getUname());
				ss.addBatch();			}			ps.executeUpdate();			if(hasLog)				System.out.println(sql);		} catch (SQLException e) {			throw Util.Exception.toRuntimeException(e);		} finally {			Closer.close(rs, ps, c);		}	}	public void delete(List<NewFighter> nfos) {		ResultSet rs = null;		PreparedStatement ps = null;		Connection c = fetcher.getConnection();		try {			String sql = "DELETE FROM new_fighter WHERE type_id = ? AND uname = ?";			ps = c.prepareStatement(sql);			StatementSetter ss = new StatementSetter(ps);			for (NewFighter nfo : nfos) {			ss.setNext(nfo.getTypeId());
			ss.setNext(nfo.getUname());
				ss.addBatch();			}			ps.executeBatch();			if(hasLog)				System.out.println(sql); 		} catch (SQLException e) {			throw Util.Exception.toRuntimeException(e);		} finally {			Closer.close(rs, ps, c);		}	}			public void delete(NewFighter nfo) {		ResultSet rs = null;		PreparedStatement ps = null;		Connection c = fetcher.getConnection();		try {			String sql = "DELETE FROM new_fighter WHERE type_id = ? AND uname = ?";			ps = c.prepareStatement(sql);			StatementSetter ss = new StatementSetter(ps);			ss.setNext(nfo.getTypeId());
			ss.setNext(nfo.getUname());
			ps.executeUpdate();			if(hasLog)				System.out.println(sql); 		} catch (SQLException e) {			throw Util.Exception.toRuntimeException(e);		} finally {			Closer.close(rs, ps, c);		}	}			public void save(NewFighter nfo) {	PreparedStatement ps = null;		ResultSet rs = null;		Connection c = fetcher.getConnection();		try {			String sql = "REPLACE INTO new_fighter (uname, type_id, level, exp, v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19, v20, yuanshen_type1, yuanshen_type2, yuanshen_type3, yuanshen_type4, yuanshen_type5, yuanshen_type6, yuanshen_type7, yuanshen_type8, yuanshen_type9, yuanshen_type10, yuanshen_level1, yuanshen_level2, yuanshen_level3, yuanshen_level4, yuanshen_level5, yuanshen_level6, yuanshen_level7, yuanshen_level8, yuanshen_level9, yuanshen_level10, yuanshen_exp1, yuanshen_exp2, yuanshen_exp3, yuanshen_exp4, yuanshen_exp5, yuanshen_exp6, yuanshen_exp7, yuanshen_exp8, yuanshen_exp9, yuanshen_exp10) "					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";			ps = c.prepareStatement(sql);			StatementSetter ss = new StatementSetter(ps);				ss.setNext(nfo.getUname());
				ss.setNext(nfo.getTypeId());
				ss.setNext(nfo.getLevel());
				ss.setNext(nfo.getExp());
				for(int i = 0; i < 20; i++) {
					ss.setNext(nfo.getV(i));
				}
				for(int i = 0; i < 10; i++) {
					ss.setNext(nfo.getYuanshenType(i));
				}
				for(int i = 0; i < 10; i++) {
					ss.setNext(nfo.getYuanshenLevel(i));
				}
				for(int i = 0; i < 10; i++) {
					ss.setNext(nfo.getYuanshenExp(i));
				}
			ps.executeUpdate();			if(hasLog)				System.out.println(sql);		} catch (SQLException e) {			throw Util.Exception.toRuntimeException(e);		} finally {			Closer.close(rs, ps, c);		}	}	public void save(List<NewFighter> nfos) {		PreparedStatement ps = null;		ResultSet rs = null;		Connection c = fetcher.getConnection();		try {			String sql = "REPLACE INTO new_fighter (uname, type_id, level, exp, v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19, v20, yuanshen_type1, yuanshen_type2, yuanshen_type3, yuanshen_type4, yuanshen_type5, yuanshen_type6, yuanshen_type7, yuanshen_type8, yuanshen_type9, yuanshen_type10, yuanshen_level1, yuanshen_level2, yuanshen_level3, yuanshen_level4, yuanshen_level5, yuanshen_level6, yuanshen_level7, yuanshen_level8, yuanshen_level9, yuanshen_level10, yuanshen_exp1, yuanshen_exp2, yuanshen_exp3, yuanshen_exp4, yuanshen_exp5, yuanshen_exp6, yuanshen_exp7, yuanshen_exp8, yuanshen_exp9, yuanshen_exp10) "					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";			ps = c.prepareStatement(sql);			StatementSetter ss = new StatementSetter(ps);			for (NewFighter nfo : nfos) {				ss.setNext(nfo.getUname());
				ss.setNext(nfo.getTypeId());
				ss.setNext(nfo.getLevel());
				ss.setNext(nfo.getExp());
				for(int i = 0; i < 20; i++) {
					ss.setNext(nfo.getV(i));
				}
				for(int i = 0; i < 10; i++) {
					ss.setNext(nfo.getYuanshenType(i));
				}
				for(int i = 0; i < 10; i++) {
					ss.setNext(nfo.getYuanshenLevel(i));
				}
				for(int i = 0; i < 10; i++) {
					ss.setNext(nfo.getYuanshenExp(i));
				}
				ss.addBatch();			}			ps.executeBatch();			if(hasLog)				System.out.println(sql);		} catch (SQLException e) {			throw Util.Exception.toRuntimeException(e);		} finally {			Closer.close(rs, ps, c);		}	}		public NewFighter get(Integer type_id, String uname) {		PreparedStatement ps = null;		ResultSet rs = null;		NewFighter nfo = null;		Connection c = fetcher.getConnection();		try {			String sql = "SELECT * FROM new_fighter WHERE type_id = ? AND uname = ?";			ps = c.prepareStatement(sql);			StatementSetter ss = new StatementSetter(ps);			ss.setNext(type_id);
			ss.setNext(uname);
			rs = ps.executeQuery();			while (rs.next()) {			nfo = mapping(rs);			}			if(hasLog)				System.out.println(sql);		} catch (SQLException e) {			throw Util.Exception.toRuntimeException(e);		} finally {			Closer.close(rs, ps, c);		}		return nfo;	}		public List<NewFighter> getAll() {		PreparedStatement ps = null;		ResultSet rs = null;		List<NewFighter> nfos = new ArrayList<NewFighter>();		Connection c = fetcher.getConnection();		try {			String sql = "SELECT * FROM new_fighter";			ps = c.prepareStatement(sql);			rs = ps.executeQuery();			while (rs.next()) {				NewFighter nfo = new NewFighterImpl();				nfo = mapping(rs);				nfos.add(nfo);			}			if(hasLog)				System.out.println(sql);		} catch (SQLException e) {			throw Util.Exception.toRuntimeException(e);		} finally {			Closer.close(rs, ps, c);		}		return nfos;	}	public int getCount() {		PreparedStatement ps = null; 		ResultSet rs = null;		int count = 0;		Connection c = fetcher.getConnection(); 		try {			String sql = "SELECT COUNT(*) FROM new_fighter";			ps = c.prepareStatement(sql);			rs = ps.executeQuery();			while (rs.next()) {				count =  rs.getInt(1);			}			if(hasLog)				System.out.println(sql);		} catch (SQLException e) {			throw Util.Exception.toRuntimeException(e);		} finally { 			Closer.close(rs, ps, c);		} 		return count; 	}	private NewFighter mapping(ResultSet rs) throws SQLException {		NewFighter nfo = new NewFighterImpl();		nfo.setUname(rs.getString("uname"));		
		nfo.setTypeId(rs.getInt("type_id"));		
		nfo.setLevel(rs.getInt("level"));		
		nfo.setExp(rs.getInt("exp"));		

		for(int i = 0; i < 20; i++) {
			nfo.setV( i, rs.getInt("v" + (i + 1)));
		}


		for(int i = 0; i < 10; i++) {
			nfo.setYuanshenType( i, rs.getInt("yuanshen_type" + (i + 1)));
		}


		for(int i = 0; i < 10; i++) {
			nfo.setYuanshenLevel( i, rs.getInt("yuanshen_level" + (i + 1)));
		}


		for(int i = 0; i < 10; i++) {
			nfo.setYuanshenExp( i, rs.getInt("yuanshen_exp" + (i + 1)));
		}

		return nfo;	}/*		public List<NewFighter> findBy(String field, String o){		PreparedStatement ps = null;		ResultSet rs = null;		List<NewFighter> ls = new ArrayList<NewFighter>();		Connection c = fetcher.getConnection();		try {			String sql = "SELECT * FROM new_fighter WHERE " + field + " = ?";			ps = c.prepareStatement(sql);			StatementSetter ss = new StatementSetter(ps);			ss.setNext(o);			rs = ps.executeQuery();			while (rs.next()) {				ls.add(mapping(rs));			}			if(hasLog)				System.out.println(sql);		} catch (SQLException e) {			throw Util.Exception.toRuntimeException(e);		} finally {			Closer.close(rs, ps, c);		}		return ls;	}*/		public List<NewFighter> findByUname(String o){		PreparedStatement ps = null;		ResultSet rs = null;		List<NewFighter> ls = new ArrayList<NewFighter>();		Connection c = fetcher.getConnection();		try {			String sql = "SELECT * FROM new_fighter WHERE uname = ?";			ps = c.prepareStatement(sql);			StatementSetter ss = new StatementSetter(ps);			ss.setNext(o);			rs = ps.executeQuery();			while (rs.next()) {				ls.add(mapping(rs));			}			if(hasLog)				System.out.println(sql);		} catch (SQLException e) {			throw Util.Exception.toRuntimeException(e);		} finally {			Closer.close(rs, ps, c);		}		return ls;	}
		public List<NewFighter> findByTypeId(Integer o){		PreparedStatement ps = null;		ResultSet rs = null;		List<NewFighter> ls = new ArrayList<NewFighter>();		Connection c = fetcher.getConnection();		try {			String sql = "SELECT * FROM new_fighter WHERE type_id = ?";			ps = c.prepareStatement(sql);			StatementSetter ss = new StatementSetter(ps);			ss.setNext(o);			rs = ps.executeQuery();			while (rs.next()) {				ls.add(mapping(rs));			}			if(hasLog)				System.out.println(sql);		} catch (SQLException e) {			throw Util.Exception.toRuntimeException(e);		} finally {			Closer.close(rs, ps, c);		}		return ls;	}
		public List<NewFighter> findByLevel(Integer o){		PreparedStatement ps = null;		ResultSet rs = null;		List<NewFighter> ls = new ArrayList<NewFighter>();		Connection c = fetcher.getConnection();		try {			String sql = "SELECT * FROM new_fighter WHERE level = ?";			ps = c.prepareStatement(sql);			StatementSetter ss = new StatementSetter(ps);			ss.setNext(o);			rs = ps.executeQuery();			while (rs.next()) {				ls.add(mapping(rs));			}			if(hasLog)				System.out.println(sql);		} catch (SQLException e) {			throw Util.Exception.toRuntimeException(e);		} finally {			Closer.close(rs, ps, c);		}		return ls;	}
		public List<NewFighter> findByExp(Integer o){		PreparedStatement ps = null;		ResultSet rs = null;		List<NewFighter> ls = new ArrayList<NewFighter>();		Connection c = fetcher.getConnection();		try {			String sql = "SELECT * FROM new_fighter WHERE exp = ?";			ps = c.prepareStatement(sql);			StatementSetter ss = new StatementSetter(ps);			ss.setNext(o);			rs = ps.executeQuery();			while (rs.next()) {				ls.add(mapping(rs));			}			if(hasLog)				System.out.println(sql);		} catch (SQLException e) {			throw Util.Exception.toRuntimeException(e);		} finally {			Closer.close(rs, ps, c);		}		return ls;	}
		public List<NewFighter> findBy(String field, String o, int limit){		PreparedStatement ps = null;		ResultSet rs = null;		List<NewFighter> ls = new ArrayList<NewFighter>();		Connection c = fetcher.getConnection();		try {			String sql = "SELECT * FROM new_fighter WHERE " + field + " = ? limit ?";			ps = c.prepareStatement(sql);						StatementSetter ss = new StatementSetter(ps);			ss.setNext(o);			ss.setNext(limit);			rs = ps.executeQuery();			while (rs.next()) {				ls.add(mapping(rs));			}			if(hasLog)				System.out.println(sql);		} catch (SQLException e) {			throw Util.Exception.toRuntimeException(e);		} finally {			Closer.close(rs, ps, c);		}		return ls;	}		public NewFighter createDTO() {		return new NewFighterImpl();	}		public void clear() {		PreparedStatement ps = null;		Connection c = fetcher.getConnection(); 		try {			String sql = "DELETE FROM new_fighter";			ps = c.prepareStatement(sql);			ps.executeUpdate();			if(hasLog)				System.out.println(sql);		} catch (SQLException e) {			throw Util.Exception.toRuntimeException(e);		} finally {			Closer.close(ps, c);		}	}	public void update(String fieldName, Object value) {		PreparedStatement ps = null;		ResultSet rs = null;		Connection c = fetcher.getConnection();		try {			String sql = "UPDATE new_fighter SET " + fieldName + " = ?";			ps = c.prepareStatement(sql);			StatementSetter ss = new StatementSetter(ps);			ss.setNext(value);			ps.executeUpdate();			if(hasLog)				System.out.println(sql);		} catch (SQLException e) {			throw Util.Exception.toRuntimeException(e);		} finally {			Closer.close(rs, ps, c);		}	}		public List<NewFighter> findBy(String field, String symbol, String o, int limit){		PreparedStatement ps = null;		ResultSet rs = null;		List<NewFighter> ls = new ArrayList<NewFighter>();		Connection c = fetcher.getConnection();		try {			String sql = "SELECT * FROM new_fighter WHERE " + field + " " + symbol + " ? LIMIT ?";			ps = c.prepareStatement(sql);			StatementSetter ss = new StatementSetter(ps);			ss.setNext(o);			ss.setNext(limit);			rs = ps.executeQuery();			while (rs.next()) {				ls.add(mapping(rs));			}			if(hasLog)				System.out.println(sql);		} catch (SQLException e) {			throw Util.Exception.toRuntimeException(e);		} finally {			Closer.close(rs, ps, c);		}		return ls;	}		public List<NewFighter> findWhere(String where){		PreparedStatement ps = null;		ResultSet rs = null;		List<NewFighter> ls = new ArrayList<NewFighter>();		Connection c = fetcher.getConnection();		try {			String sql = "SELECT * FROM new_fighter WHERE " + where + ";";			ps = c.prepareStatement(sql);			rs = ps.executeQuery();			while (rs.next()) {				ls.add(mapping(rs));			}			if(hasLog)				System.out.println(sql);		} catch (SQLException e) {			throw Util.Exception.toRuntimeException(e);		} finally {			Closer.close(rs, ps, c);		}		return ls;	}		public List<NewFighter> findByScope(String field, int min, int max, int limit){		PreparedStatement ps = null;		ResultSet rs = null;		List<NewFighter> ls = new ArrayList<NewFighter>();		Connection c = fetcher.getConnection();		try {			String sql = "SELECT * FROM new_fighter WHERE " + field + " >= ? AND " + field + " <= ? LIMIT ?";			ps = c.prepareStatement(sql);			StatementSetter ss = new StatementSetter(ps);			ss.setNext(min);			ss.setNext(max);			ss.setNext(limit);			rs = ps.executeQuery();			while (rs.next()) {				ls.add(mapping(rs));			}			if(hasLog)				System.out.println(sql);		} catch (SQLException e) {			throw Util.Exception.toRuntimeException(e);		} finally {			Closer.close(rs, ps, c);		}		return ls;	}}