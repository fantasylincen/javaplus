package db.dao.impl;import java.util.List;import java.util.ArrayList;import java.sql.PreparedStatement;import java.sql.ResultSet;import java.sql.SQLException;import db.domain.ChuangZhen;import db.domain.ChuangZhen;import dbutils.IdUtil;import java.util.concurrent.atomic.AtomicLong;import cn.javaplus.util.Closer;import java.sql.Connection;import com.lemon.commons.database.ConnectionFetcher;import db.domain.*;import java.sql.Blob;import java.sql.Date;import java.sql.Time;public interface ChuangZhenDao {	/**	 * 增加	 */	void add(ChuangZhen czo);	/**	 * 增加一批	 */	void addAll(List<ChuangZhen> czos);	/**	 * 删除	 */	void delete(String uname);	/**	 * 更新	 */	void update(ChuangZhen czo);		/**	 * 更新一批	 */		void update(List<ChuangZhen> czos);	/**	 * 删除一批	 */	void delete(List<ChuangZhen> czos);		/**	 * 删除	 */			void delete(ChuangZhen czo);		/**	 * 存入 有则覆盖 无则增加	 */			void save(ChuangZhen czo);		/**	 * 存入一批 有则覆盖 无则增加	 */		void save(List<ChuangZhen> czos);		/**	 * 取得	 */			ChuangZhen get(String uname);		/**	 * 取得所有	 */			List<ChuangZhen> getAll();		/**	 * 表记录数量	 */		int getCount();/*	@Deprecated	List<ChuangZhen> findBy(String field, String o);*/		public List<ChuangZhen> findByUname(String o);
		public List<ChuangZhen> findByStar(Integer o);
		public List<ChuangZhen> findByStarRemain(Integer o);
		public List<ChuangZhen> findByFloor(Integer o);
		public List<ChuangZhen> findByTimes(Integer o);
		public List<ChuangZhen> findByAttack(Float o);
		public List<ChuangZhen> findByDefend(Float o);
		public List<ChuangZhen> findByMAttack(Float o);
		public List<ChuangZhen> findByMDefend(Float o);
		public List<ChuangZhen> findByHp(Float o);
		public List<ChuangZhen> findBySpeed(Float o);
		public List<ChuangZhen> findByStarMaxToday(Integer o);
		public List<ChuangZhen> findByFloorMaxToday(Integer o);
		@Deprecated	List<ChuangZhen> findBy(String field, String o, int limit);		/**	 * 生成一个对应的数据传输对象	 */			ChuangZhen createDTO();		/**	 * 清空	 */			void clear();		@Deprecated	void update(String fieldName, Object value);		@Deprecated	List<ChuangZhen> findBy(String field, String symbol, String o, int limit);		@Deprecated	List<ChuangZhen> findWhere(String where);		@Deprecated	List<ChuangZhen> findByScope(String field, int min, int max, int limit);}