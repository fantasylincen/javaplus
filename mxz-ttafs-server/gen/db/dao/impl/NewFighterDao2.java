package db.dao.impl;//该类自动生成, 禁止手动修改//这一版本的Dao, 带写缓存import com.google.common.collect.Maps;import com.google.common.collect.Lists;import java.util.Map;import java.sql.Date;import java.util.List;import com.lemon.commons.database.ConnectionFetcher;import cn.javaplus.exception.MySQLNonTransientConnectionRuntimeExceptionJDBC4;import db.domain.*;import cn.javaplus.exception.SQLRuntimeException;import org.apache.log4j.Logger;import cn.javaplus.util.Util;import cn.javaplus.cache.Cache;import cn.javaplus.cache.CacheImpl;//该文件自动生成, 禁止手动修改!public class NewFighterDao2 implements NewFighterDao {	private NewFighterDao1 dao;		private static Cache<String, NewFighter> cache = new CacheImpl<String, NewFighter>();	NewFighterDao2(ConnectionFetcher fetcher) {		dao = new NewFighterDao1(fetcher);	}	void commitAllSafety() {			List<NewFighter> values;				synchronized (cache) {			values = Lists.newArrayList(cache.values());			cache.clear();		}				if(values.isEmpty()) {			return;		}				try {			long time = System.currentTimeMillis();			dao.save(values);			if(!values.isEmpty()) {//				Logger.getLogger(NewFighterDao2.class).debug("线程:" + Thread.currentThread().getId() + " 批量提交 NewFighter 数据量: " + values.size() + " [NewFighterDao2] [time = " + (System.currentTimeMillis() - time) + "]");			}				} catch (MySQLNonTransientConnectionRuntimeExceptionJDBC4 e) {			save(values);			Logger.getLogger(NewFighterDao2.class).debug("线程:" + Thread.currentThread().getId() + " 批量提交 NewFighter 失败, 重新写入缓存, 数据量: " + values.size() + " [NewFighterDao2]");			Logger.getLogger(NewFighterDao2.class).error(e.getClass().getName() + ":" + e.getMessage());		} catch (Throwable e) {			e.printStackTrace();		}	}	private String key(Object... keys) {		return Util.Collection.linkWith(",", keys);	}	public void delete(Integer type_id, String uname) {		cache.remove(key(type_id, uname));		dao.delete(type_id, uname);	}		public void delete(List<NewFighter> nfos) {		for (NewFighter nfo : nfos) {			cache.remove(key(nfo.getTypeId(),nfo.getUname()));		}		dao.delete(nfos);	}		public void save(NewFighter nfo) {		cache.put(key(nfo.getTypeId(),nfo.getUname()), /*new NewFighterImpl( */nfo/*)*/);	}		public NewFighter get(Integer type_id, String uname) {		NewFighter nfo = cache.get(key(type_id, uname));		if(nfo == null) {			nfo = dao.get(type_id, uname);		}		return nfo;	}	public int getCount() {		return dao.getCount() + cache.size(); 	}		public void clear() {		cache.clear();		dao.clear();	}	/*	@Deprecated 	public List<NewFighter> findBy(String field, String o) {		return dao.findBy(field, o);	}*/	@Deprecated	public List<NewFighter> findBy(String field, String o, int limit) {		return dao.findBy(field, o, limit);	}	public NewFighter createDTO() {		return new NewFighterImpl();	}		@Deprecated	public List<NewFighter> findBy(String field, String symbol, String o, int limit) {		return dao.findBy(field, symbol, o, limit);	}	@Deprecated	public List<NewFighter> findWhere(String where) {		return dao.findWhere(where);	}		@Deprecated	public List<NewFighter> findByScope(String field, int min, int max, int limit) {		return dao.findByScope(field, min, max, limit);	}	public void add(NewFighter nfo) {		save(nfo);	}		public void addAll(List<NewFighter> nfos) {		save(nfos);	}	public void update(NewFighter nfo) {		save(nfo);	}		public void update(List<NewFighter> nfos) {		save(nfos);	}		public void delete(NewFighter nfo) {		delete(nfo.getTypeId(),nfo.getUname());	}	public List<NewFighter> getAll() {		List<NewFighter> all = Lists.newArrayList(cache.values());		all.addAll(dao.getAll());		return all;	}	public void save(List<NewFighter> nfos) {		for (NewFighter nfo : nfos) {			save(nfo);		}	}		@Deprecated	public void update(String fieldName, Object value) {		dao.update(fieldName, value);	}		@Override	public List<NewFighter> findByUname(String o) {		List<NewFighter> all = dao.findByUname(o);		for (NewFighter nfo : cache.values()) {			if(equals(o, nfo.getUname())) {				all.add(nfo);			}		}		return all;	}	
	@Override	public List<NewFighter> findByTypeId(Integer o) {		List<NewFighter> all = dao.findByTypeId(o);		for (NewFighter nfo : cache.values()) {			if(equals(o, nfo.getTypeId())) {				all.add(nfo);			}		}		return all;	}	
	@Override	public List<NewFighter> findByLevel(Integer o) {		List<NewFighter> all = dao.findByLevel(o);		for (NewFighter nfo : cache.values()) {			if(equals(o, nfo.getLevel())) {				all.add(nfo);			}		}		return all;	}	
	@Override	public List<NewFighter> findByExp(Integer o) {		List<NewFighter> all = dao.findByExp(o);		for (NewFighter nfo : cache.values()) {			if(equals(o, nfo.getExp())) {				all.add(nfo);			}		}		return all;	}	
		private boolean equals(Object o1, Object o2) {		return o1.equals(o2);	}}/*CLASS_$PARAMETERsnfos---------------CLASS_$NAMENewFighter---------------KS_$GETTERnfo.getTypeId(),nfo.getUname()---------------PACKAGE_$NAMEdb---------------COLUMN_$NAMESuname, type_id, level, exp, v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19, v20, yuanshen_type1, yuanshen_type2, yuanshen_type3, yuanshen_type4, yuanshen_type5, yuanshen_type6, yuanshen_type7, yuanshen_type8, yuanshen_type9, yuanshen_type10, yuanshen_level1, yuanshen_level2, yuanshen_level3, yuanshen_level4, yuanshen_level5, yuanshen_level6, yuanshen_level7, yuanshen_level8, yuanshen_level9, yuanshen_level10, yuanshen_exp1, yuanshen_exp2, yuanshen_exp3, yuanshen_exp4, yuanshen_exp5, yuanshen_exp6, yuanshen_exp7, yuanshen_exp8, yuanshen_exp9, yuanshen_exp10---------------COLUMN_$?S?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?---------------PS_$SETS_$ADD				ss.setNext(nfo.getUname());
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
---------------PK_$PARAMETERInteger type_id, String uname---------------PKCOLUMNS_$Stype_id = ? AND uname = ?---------------PK_$PS_$SETS			ss.setNext(type_id);
			ss.setNext(uname);
---------------COLUMN_$PARAMETERSlevel = ?, exp = ?, v1 = ?, v2 = ?, v3 = ?, v4 = ?, v5 = ?, v6 = ?, v7 = ?, v8 = ?, v9 = ?, v10 = ?, v11 = ?, v12 = ?, v13 = ?, v14 = ?, v15 = ?, v16 = ?, v17 = ?, v18 = ?, v19 = ?, v20 = ?, yuanshen_type1 = ?, yuanshen_type2 = ?, yuanshen_type3 = ?, yuanshen_type4 = ?, yuanshen_type5 = ?, yuanshen_type6 = ?, yuanshen_type7 = ?, yuanshen_type8 = ?, yuanshen_type9 = ?, yuanshen_type10 = ?, yuanshen_level1 = ?, yuanshen_level2 = ?, yuanshen_level3 = ?, yuanshen_level4 = ?, yuanshen_level5 = ?, yuanshen_level6 = ?, yuanshen_level7 = ?, yuanshen_level8 = ?, yuanshen_level9 = ?, yuanshen_level10 = ?, yuanshen_exp1 = ?, yuanshen_exp2 = ?, yuanshen_exp3 = ?, yuanshen_exp4 = ?, yuanshen_exp5 = ?, yuanshen_exp6 = ?, yuanshen_exp7 = ?, yuanshen_exp8 = ?, yuanshen_exp9 = ?, yuanshen_exp10 = ?---------------PS_$SETS_$UPDATE			ss.setNext(nfo.getLevel());
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
---------------TABLE_$NAMEnew_fighter---------------PK_$COLUMNStype_id = ? AND uname = ?---------------DELETE_$SETS			ss.setNext(nfo.getTypeId());
			ss.setNext(nfo.getUname());
---------------CLASS_$PARAMETERnfo---------------RS_$GETS		nfo.setUname(rs.getString("uname"));		
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

---------------PAGE_$SETS			ps.setInt(1, (pageNo - 1) * pageSize);
			ps.setInt(2, pageSize);
---------------TA$IL2---------------KE$YSInteger, String, ---------------KEY_$COLUMN_$NAMEtype_id---------------CACHE_$DEFINECACHE_DEFINE---------------Integer, String, _$GETTERSInteger, String, _GETTERS---------------KEY$NAMES_LINK_BY_COMMAtype_id, uname---------------KEY_$TYPE_1KEY_TYPE_1---------------KEY_$TYPE_2KEY_TYPE_2---------------FIN&DERS		public List<NewFighter> findByUname(String o){		PreparedStatement ps = null;		ResultSet rs = null;		List<NewFighter> ls = new ArrayList<NewFighter>();		Connection c = fetcher.getConnection();		try {			String sql = "SELECT * FROM new_fighter WHERE uname = ?";			ps = c.prepareStatement(sql);			StatementSetter ss = new StatementSetter(ps);			ss.setNext(o);			rs = ps.executeQuery();			while (rs.next()) {				ls.add(mapping(rs));			}			if(hasLog)				System.out.println(sql);		} catch (SQLException e) {			throw Util.Exception.toRuntimeException(e);		} finally {			Closer.close(rs, ps, c);		}		return ls;	}
		public List<NewFighter> findByTypeId(Integer o){		PreparedStatement ps = null;		ResultSet rs = null;		List<NewFighter> ls = new ArrayList<NewFighter>();		Connection c = fetcher.getConnection();		try {			String sql = "SELECT * FROM new_fighter WHERE type_id = ?";			ps = c.prepareStatement(sql);			StatementSetter ss = new StatementSetter(ps);			ss.setNext(o);			rs = ps.executeQuery();			while (rs.next()) {				ls.add(mapping(rs));			}			if(hasLog)				System.out.println(sql);		} catch (SQLException e) {			throw Util.Exception.toRuntimeException(e);		} finally {			Closer.close(rs, ps, c);		}		return ls;	}
		public List<NewFighter> findByLevel(Integer o){		PreparedStatement ps = null;		ResultSet rs = null;		List<NewFighter> ls = new ArrayList<NewFighter>();		Connection c = fetcher.getConnection();		try {			String sql = "SELECT * FROM new_fighter WHERE level = ?";			ps = c.prepareStatement(sql);			StatementSetter ss = new StatementSetter(ps);			ss.setNext(o);			rs = ps.executeQuery();			while (rs.next()) {				ls.add(mapping(rs));			}			if(hasLog)				System.out.println(sql);		} catch (SQLException e) {			throw Util.Exception.toRuntimeException(e);		} finally {			Closer.close(rs, ps, c);		}		return ls;	}
		public List<NewFighter> findByExp(Integer o){		PreparedStatement ps = null;		ResultSet rs = null;		List<NewFighter> ls = new ArrayList<NewFighter>();		Connection c = fetcher.getConnection();		try {			String sql = "SELECT * FROM new_fighter WHERE exp = ?";			ps = c.prepareStatement(sql);			StatementSetter ss = new StatementSetter(ps);			ss.setNext(o);			rs = ps.executeQuery();			while (rs.next()) {				ls.add(mapping(rs));			}			if(hasLog)				System.out.println(sql);		} catch (SQLException e) {			throw Util.Exception.toRuntimeException(e);		} finally {			Closer.close(rs, ps, c);		}		return ls;	}
--------------UPPER_$FNAMEUPPER_FNAME--------------FILED_&NAME_IN_TABLEFILED_NAME_IN_TABLE--------------PACKAGE_$FTYPEPACKAGE_FTYPE--------------UPPER_$FTYPEUPPER_FTYPE--------------INTERFAC_$FINDS		public List<NewFighter> findByUname(String o);
		public List<NewFighter> findByTypeId(Integer o);
		public List<NewFighter> findByLevel(Integer o);
		public List<NewFighter> findByExp(Integer o);
--------------DAO2_$		public List<NewFighter> findByUname(String o){		PreparedStatement ps = null;		ResultSet rs = null;		List<NewFighter> ls = new ArrayList<NewFighter>();		Connection c = fetcher.getConnection();		try {			String sql = "SELECT * FROM new_fighter WHERE uname = ?";			ps = c.prepareStatement(sql);			StatementSetter ss = new StatementSetter(ps);			ss.setNext(o);			rs = ps.executeQuery();			while (rs.next()) {				ls.add(mapping(rs));			}			if(hasLog)				System.out.println(sql);		} catch (SQLException e) {			throw Util.Exception.toRuntimeException(e);		} finally {			Closer.close(rs, ps, c);		}		return ls;	}
		public List<NewFighter> findByTypeId(Integer o){		PreparedStatement ps = null;		ResultSet rs = null;		List<NewFighter> ls = new ArrayList<NewFighter>();		Connection c = fetcher.getConnection();		try {			String sql = "SELECT * FROM new_fighter WHERE type_id = ?";			ps = c.prepareStatement(sql);			StatementSetter ss = new StatementSetter(ps);			ss.setNext(o);			rs = ps.executeQuery();			while (rs.next()) {				ls.add(mapping(rs));			}			if(hasLog)				System.out.println(sql);		} catch (SQLException e) {			throw Util.Exception.toRuntimeException(e);		} finally {			Closer.close(rs, ps, c);		}		return ls;	}
		public List<NewFighter> findByLevel(Integer o){		PreparedStatement ps = null;		ResultSet rs = null;		List<NewFighter> ls = new ArrayList<NewFighter>();		Connection c = fetcher.getConnection();		try {			String sql = "SELECT * FROM new_fighter WHERE level = ?";			ps = c.prepareStatement(sql);			StatementSetter ss = new StatementSetter(ps);			ss.setNext(o);			rs = ps.executeQuery();			while (rs.next()) {				ls.add(mapping(rs));			}			if(hasLog)				System.out.println(sql);		} catch (SQLException e) {			throw Util.Exception.toRuntimeException(e);		} finally {			Closer.close(rs, ps, c);		}		return ls;	}
		public List<NewFighter> findByExp(Integer o){		PreparedStatement ps = null;		ResultSet rs = null;		List<NewFighter> ls = new ArrayList<NewFighter>();		Connection c = fetcher.getConnection();		try {			String sql = "SELECT * FROM new_fighter WHERE exp = ?";			ps = c.prepareStatement(sql);			StatementSetter ss = new StatementSetter(ps);			ss.setNext(o);			rs = ps.executeQuery();			while (rs.next()) {				ls.add(mapping(rs));			}			if(hasLog)				System.out.println(sql);		} catch (SQLException e) {			throw Util.Exception.toRuntimeException(e);		} finally {			Closer.close(rs, ps, c);		}		return ls;	}
	@Override	public List<NewFighter> findByUname(String o) {		List<NewFighter> all = dao.findByUname(o);		for (NewFighter nfo : cache.values()) {			if(equals(o, nfo.getUname())) {				all.add(nfo);			}		}		return all;	}	
	@Override	public List<NewFighter> findByTypeId(Integer o) {		List<NewFighter> all = dao.findByTypeId(o);		for (NewFighter nfo : cache.values()) {			if(equals(o, nfo.getTypeId())) {				all.add(nfo);			}		}		return all;	}	
	@Override	public List<NewFighter> findByLevel(Integer o) {		List<NewFighter> all = dao.findByLevel(o);		for (NewFighter nfo : cache.values()) {			if(equals(o, nfo.getLevel())) {				all.add(nfo);			}		}		return all;	}	
	@Override	public List<NewFighter> findByExp(Integer o) {		List<NewFighter> all = dao.findByExp(o);		for (NewFighter nfo : cache.values()) {			if(equals(o, nfo.getExp())) {				all.add(nfo);			}		}		return all;	}	
*/