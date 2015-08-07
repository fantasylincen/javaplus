package cn.vgame.b.gen.dto;import java.net.UnknownHostException;import java.util.ArrayList;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import java.util.List;import java.util.Map;import java.util.Set;import cn.javaplus.exception.UnImplMethodException;import com.mongodb.BasicDBList;import com.mongodb.BasicDBObject;import com.mongodb.DB;import com.mongodb.DBCollection;import com.mongodb.DBCursor;import com.mongodb.DBObject;import com.mongodb.Mongo;import com.mongodb.MongoClient;import java.util.regex.Pattern;public class MongoGen {	public static class Lists {		public static<T> List<T> newArrayList() {			return new ArrayList<T>();		}	}	public static class Maps {		public static<K, V> Map<K, V> newHashMap() {			return new HashMap<K, V>();		}		public static<V> MongoMap<V> newMongoMap() {			return new MongoMapImpl<V>();		}	}			public static interface MongoMap<V> {		V get(String k);		void put(String k, V v);		Set<String> keySet();		Collection<V> values();	}		public static class MongoMapImpl<V> implements MongoMap<V> {		Map<String, V> map = Maps.newHashMap();				@Override		public V get(String k) {			return map.get(k);		}		@Override		public void put(String k, V v) {			map.put(k, v);		}		@Override		public Set<String> keySet() {			return map.keySet();		}		@Override		public Collection<V> values() {			return map.values();		}			}																static BasicDBObject toObjectBytes(MongoMap<byte[]> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			byte[] v = map.get(key);			o.put(key, toObject(v));		}		return o;	}	static BasicDBObject toObjectByte(MongoMap<Byte> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			Byte v = map.get(key);			o.put(key, toObject(v));		}		return o;	}	static BasicDBObject toObjectCharacter(MongoMap<Character> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			Character v = map.get(key);			o.put(key, toObject(v));		}		return o;	}	static BasicDBObject toObjectBoolean(MongoMap<Boolean> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			Boolean v = map.get(key);			o.put(key, toObject(v));		}		return o;	}	static BasicDBObject toObjectShort(MongoMap<Short> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			Short v = map.get(key);			o.put(key, toObject(v));		}		return o;	}	static BasicDBObject toObjectInteger(MongoMap<Integer> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			Integer v = map.get(key);			o.put(key, toObject(v));		}		return o;	}	static BasicDBObject toObjectLong(MongoMap<Long> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			Long v = map.get(key);			o.put(key, toObject(v));		}		return o;	}	static BasicDBObject toObjectFloat(MongoMap<Float> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			Float v = map.get(key);			o.put(key, toObject(v));		}		return o;	}	static BasicDBObject toObjectDouble(MongoMap<Double> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			Double v = map.get(key);			o.put(key, toObject(v));		}		return o;	}	static BasicDBObject toObjectString(MongoMap<String> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			String v = map.get(key);			o.put(key, toObject(v));		}		return o;	}	static BasicDBList toObjectBytes(Collection<byte[]> list) {		BasicDBList prices = new BasicDBList();		for (byte[] o : list) {			prices.add(toObject(o));		}		return prices;	}	static BasicDBList toObjectByte(Collection<Byte> list) {		BasicDBList prices = new BasicDBList();		for (Byte o : list) {			prices.add(toObject(o));		}		return prices;	}	static BasicDBList toObjectCharacter(Collection<Character> list) {		BasicDBList prices = new BasicDBList();		for (Character o : list) {			prices.add(toObject(o));		}		return prices;	}	static BasicDBList toObjectBoolean(Collection<Boolean> list) {		BasicDBList prices = new BasicDBList();		for (Boolean o : list) {			prices.add(toObject(o));		}		return prices;	}	static BasicDBList toObjectShort(Collection<Short> list) {		BasicDBList prices = new BasicDBList();		for (Short o : list) {			prices.add(toObject(o));		}		return prices;	}	static BasicDBList toObjectInteger(Collection<Integer> list) {		BasicDBList prices = new BasicDBList();		for (Integer o : list) {			prices.add(toObject(o));		}		return prices;	}	static BasicDBList toObjectLong(Collection<Long> list) {		BasicDBList prices = new BasicDBList();		for (Long o : list) {			prices.add(toObject(o));		}		return prices;	}	static BasicDBList toObjectFloat(Collection<Float> list) {		BasicDBList prices = new BasicDBList();		for (Float o : list) {			prices.add(toObject(o));		}		return prices;	}	static BasicDBList toObjectDouble(Collection<Double> list) {		BasicDBList prices = new BasicDBList();		for (Double o : list) {			prices.add(toObject(o));		}		return prices;	}	static BasicDBList toObjectString(Collection<String> list) {		BasicDBList prices = new BasicDBList();		for (String o : list) {			prices.add(toObject(o));		}		return prices;	}	static byte[] toObject(byte[] value) {		return value;	}	static byte toObject(byte value) {		return value;	}	static char toObject(char value) {		return value;	}	static boolean toObject(boolean value) {		return value;	}	static short toObject(short value) {		return value;	}	static int toObject(int value) {		return value;	}	static long toObject(long value) {		return value;	}	static float toObject(float value) {		return value;	}	static double toObject(double value) {		return value;	}	static String toObject(String value) {		return value;	}	static DBObject toObject(MongoDto value) {		if(value == null)			return null;		return value.toObject();	}	static <V extends MongoDto> BasicDBObject toObject(MongoMap<V> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			V v = map.get(key);			o.put(key, toObject(v));		}		return o;	}		static <V extends MongoDto> BasicDBList toObject(List<V> ls) {		BasicDBList o = new BasicDBList();		for (V v : ls) {			o.add(toObject(v));		}		return o;	}	static MongoMap<byte[]> copyBytes(MongoMap<byte[]> value) {		MongoMap<byte[]> map = Maps.newMongoMap();		for (String key : value.keySet()) {			map.put(key, MongoGen.copy(value.get(key)));		}		return map;	}	static MongoMap<Byte> copyByte(MongoMap<Byte> value) {		MongoMap<Byte> map = Maps.newMongoMap();		for (String key : value.keySet()) {			map.put(key, value.get(key));		}		return map;	}	static MongoMap<Character> copyCharacter(MongoMap<Character> value) {		MongoMap<Character> map = Maps.newMongoMap();		for (String key : value.keySet()) {			map.put(key, value.get(key));		}		return map;	}	static MongoMap<Boolean> copyBoolean(MongoMap<Boolean> value) {		MongoMap<Boolean> map = Maps.newMongoMap();		for (String key : value.keySet()) {			map.put(key, value.get(key));		}		return map;	}	static MongoMap<Short> copyShort(MongoMap<Short> value) {		MongoMap<Short> map = Maps.newMongoMap();		for (String key : value.keySet()) {			map.put(key, value.get(key));		}		return map;	}	static MongoMap<Integer> copyInteger(MongoMap<Integer> value) {		MongoMap<Integer> map = Maps.newMongoMap();		for (String key : value.keySet()) {			map.put(key, value.get(key));		}		return map;	}	static MongoMap<Long> copyLong(MongoMap<Long> value) {		MongoMap<Long> map = Maps.newMongoMap();		for (String key : value.keySet()) {			map.put(key, value.get(key));		}		return map;	}	static MongoMap<Float> copyFloat(MongoMap<Float> value) {		MongoMap<Float> map = Maps.newMongoMap();		for (String key : value.keySet()) {			map.put(key, value.get(key));		}		return map;	}	static MongoMap<Double> copyDouble(MongoMap<Double> value) {		MongoMap<Double> map = Maps.newMongoMap();		for (String key : value.keySet()) {			map.put(key, value.get(key));		}		return map;	}	static MongoMap<String> copyString(MongoMap<String> value) {		MongoMap<String> map = Maps.newMongoMap();		for (String key : value.keySet()) {			map.put(key, new String(value.get(key)));		}		return map;	}	static List<byte[]> copyBytes(List<byte[]> value) {		List<byte[]> ls = Lists.newArrayList();		for (byte[] v : value) {			ls.add(copy(v));		}		return ls;	}	static List<Byte> copyByte(List<Byte> value) {		List<Byte> ls = Lists.newArrayList();		for (Byte v : value) {			ls.add(v);		}		return ls;	}	static List<Character> copyCharacter(List<Character> value) {		List<Character> ls = Lists.newArrayList();		for (Character v : value) {			ls.add(v);		}		return ls;	}	static List<Boolean> copyBoolean(List<Boolean> value) {		List<Boolean> ls = Lists.newArrayList();		for (Boolean v : value) {			ls.add(v);		}		return ls;	}	static List<Short> copyShort(List<Short> value) {		List<Short> ls = Lists.newArrayList();		for (Short v : value) {			ls.add(v);		}		return ls;	}	static List<Integer> copyInteger(List<Integer> value) {		List<Integer> ls = Lists.newArrayList();		for (Integer v : value) {			ls.add(v);		}		return ls;	}	static List<Long> copyLong(List<Long> value) {		List<Long> ls = Lists.newArrayList();		for (Long v : value) {			ls.add(v);		}		return ls;	}	static List<Float> copyFloat(List<Float> value) {		List<Float> ls = Lists.newArrayList();		for (Float v : value) {			ls.add(v);		}		return ls;	}	static List<Double> copyDouble(List<Double> value) {		List<Double> ls = Lists.newArrayList();		for (Double v : value) {			ls.add(v);		}		return ls;	}	static List<String> copyString(List<String> value) {		List<String> ls = Lists.newArrayList();		for (String v : value) {			ls.add(new String(v));		}		return ls;	}		static byte[] copy(byte[] value) {		return java.util.Arrays.copyOf(value, value.length);	}	static byte copy(byte value) {		return value;	}	static char copy(char value) {		return value;	}	static boolean copy(boolean value) {		return value;	}	static short copy(short value) {		return value;	}	static int copy(int value) {		return value;	}	static long copy(long value) {		return value;	}	static float copy(float value) {		return value;	}	static double copy(double value) {		return value;	}	static String copy(String value) {		return new String(value);	}												static BasicDBList getBasicDBList(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return new BasicDBList();		}		return (BasicDBList) obj;	}	static boolean getBoolean(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return false;		}		return (Boolean) obj;	}		static int getInteger(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return 0;		}		return (Integer) obj;	}		static byte[] getBytes(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return null;		}		return (byte[]) obj;	}		static float getFloat(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return 0;		}		return (Float) obj;	}		static String getString(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return "";		}		return (String) obj;	}	static long getLong(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return 0;		}		return (Long) obj;	}		static double getDouble(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return 0;		}		return (Double) obj;	}										 static void put(String key, BasicDBObject o, ConsoleLogDto value) {		o.put(key, MongoGen.toObject(value));	 }	 
							 static void put(String key, BasicDBObject o, GmLogDto value) {		o.put(key, MongoGen.toObject(value));	 }	 
							 static void put(String key, BasicDBObject o, MissionDataDto value) {		o.put(key, MongoGen.toObject(value));	 }	 
							 static void put(String key, BasicDBObject o, RoleDto value) {		o.put(key, MongoGen.toObject(value));	 }	 
							 static void put(String key, BasicDBObject o, SystemKeyValueDto value) {		o.put(key, MongoGen.toObject(value));	 }	 
							 static void put(String key, BasicDBObject o, YiJieRechargeLogDto value) {		o.put(key, MongoGen.toObject(value));	 }	 
								 static ConsoleLogDto copy(ConsoleLogDto value) {		return new ConsoleLogDto(value);	 }
							 static GmLogDto copy(GmLogDto value) {		return new GmLogDto(value);	 }
							 static MissionDataDto copy(MissionDataDto value) {		return new MissionDataDto(value);	 }
							 static RoleDto copy(RoleDto value) {		return new RoleDto(value);	 }
							 static SystemKeyValueDto copy(SystemKeyValueDto value) {		return new SystemKeyValueDto(value);	 }
							 static YiJieRechargeLogDto copy(YiJieRechargeLogDto value) {		return new YiJieRechargeLogDto(value);	 }
							static DBObject toObject(ConsoleLogDto value) {		if(value == null) 			return null;		return value.toObject();	}	 	static DBObject toObjectConsoleLogDto(ConsoleLogDto value) {		return value.toObject();	}	 	static BasicDBList toObjectConsoleLogDto(List<ConsoleLogDto> value) {		BasicDBList o = new BasicDBList();		for (ConsoleLogDto v : value) {			o.add(toObject(v));		}		return o;	}	 	 
							static DBObject toObject(GmLogDto value) {		if(value == null) 			return null;		return value.toObject();	}	 	static DBObject toObjectGmLogDto(GmLogDto value) {		return value.toObject();	}	 	static BasicDBList toObjectGmLogDto(List<GmLogDto> value) {		BasicDBList o = new BasicDBList();		for (GmLogDto v : value) {			o.add(toObject(v));		}		return o;	}	 	 
							static DBObject toObject(MissionDataDto value) {		if(value == null) 			return null;		return value.toObject();	}	 	static DBObject toObjectMissionDataDto(MissionDataDto value) {		return value.toObject();	}	 	static BasicDBList toObjectMissionDataDto(List<MissionDataDto> value) {		BasicDBList o = new BasicDBList();		for (MissionDataDto v : value) {			o.add(toObject(v));		}		return o;	}	 	 
							static DBObject toObject(RoleDto value) {		if(value == null) 			return null;		return value.toObject();	}	 	static DBObject toObjectRoleDto(RoleDto value) {		return value.toObject();	}	 	static BasicDBList toObjectRoleDto(List<RoleDto> value) {		BasicDBList o = new BasicDBList();		for (RoleDto v : value) {			o.add(toObject(v));		}		return o;	}	 	 
							static DBObject toObject(SystemKeyValueDto value) {		if(value == null) 			return null;		return value.toObject();	}	 	static DBObject toObjectSystemKeyValueDto(SystemKeyValueDto value) {		return value.toObject();	}	 	static BasicDBList toObjectSystemKeyValueDto(List<SystemKeyValueDto> value) {		BasicDBList o = new BasicDBList();		for (SystemKeyValueDto v : value) {			o.add(toObject(v));		}		return o;	}	 	 
							static DBObject toObject(YiJieRechargeLogDto value) {		if(value == null) 			return null;		return value.toObject();	}	 	static DBObject toObjectYiJieRechargeLogDto(YiJieRechargeLogDto value) {		return value.toObject();	}	 	static BasicDBList toObjectYiJieRechargeLogDto(List<YiJieRechargeLogDto> value) {		BasicDBList o = new BasicDBList();		for (YiJieRechargeLogDto v : value) {			o.add(toObject(v));		}		return o;	}	 	 
		public static interface CollectionFetcher {			DBCollection getCollection(String string);		void destroy();	}			public static interface MongoDto {			DBObject toObject();		void fromDBObject(DBObject o);	}			public static interface MongoDbProperties {			/**		 * 数据库名		 */		String getName();				/**		 * 主机地址		 */		String getHost();				/**		 * 端口		 */		int getPort();		}	public static final class Daos {			private static CollectionFetcher	fetcher;		private static MongoDbProperties	properties;		private static Map<String, DBCollection> cache = new HashMap<String, DBCollection>();				public static void destroy() {			getCollectionFetcher().destroy();		}				public static final void setProperties(MongoDbProperties properties) {			Daos.properties = properties;		}				public static final CollectionFetcher getCollectionFetcher() {			if(fetcher == null)				fetcher = new NormalFetcher();			return fetcher;		}				private static  class NormalFetcher implements CollectionFetcher {			@Override			public DBCollection getCollection(String name) {								Mongo m = getMongo();				DB db = m.getDB(properties.getName());				return db.getCollection(name);			}			@Override			public void destroy() {				if(mongo != null)					mongo.close();			}						private static Mongo mongo;			private static Mongo getMongo() {				if(mongo == null) {					try {						if(properties == null) {							throw new NullPointerException("请先setProperties");						}						mongo = new MongoClient(properties.getHost(), properties.getPort());					} catch (UnknownHostException e) {						throw cn.javaplus.util.Util.Exception.toRuntimeException(e);					}				}				return mongo;			}		}		public static DBCollection getCollection(String collectionName) {			DBCollection c;			try {				c = cache.get(collectionName);			} catch (NullPointerException e) {				c = null;			}			if (c != null) {				return c;			}			c = getCollectionFetcher().getCollection(collectionName);			cache.put(collectionName, c);			return c;		}		public static ConsoleLogDao getConsoleLogDao() {			return new ConsoleLogDao(getCollection("ConsoleLog"));		}
		public static GmLogDao getGmLogDao() {			return new GmLogDao(getCollection("GmLog"));		}
		public static RoleDao getRoleDao() {			return new RoleDao(getCollection("Role"));		}
		public static SystemKeyValueDao getSystemKeyValueDao() {			return new SystemKeyValueDao(getCollection("SystemKeyValue"));		}
		public static YiJieRechargeLogDao getYiJieRechargeLogDao() {			return new YiJieRechargeLogDao(getCollection("YiJieRechargeLog"));		}
		}
		public static class ConsoleLogDao {			private DBCollection	collection;			public ConsoleLogDao(DBCollection collection) {			this.collection = collection;		}			public void save(ConsoleLogDto u) {			collection.save(u.toObject());		}			public void delete(ConsoleLogDto u) {			delete(u.getId());		}			public void delete(String id) {			collection.remove(key(id));		}			public ConsoleLogDto get(String id) {			DBObject o = collection.findOne(key(id));			if(o == null) {				return null;			}			ConsoleLogDto x = new ConsoleLogDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String id) {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);			return o;		}			public ConsoleLogDtoCursor find() {			return new ConsoleLogDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public ConsoleLogDtoCursor findById(String id) {			collection.ensureIndex("id");			BasicDBObject o = new BasicDBObject("id", id);			return new ConsoleLogDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public ConsoleLogDtoCursor findByIdFuzzy(String id) {			collection.ensureIndex("id");			id = id.replaceAll("\\*", ".*");			id = "^" + id + "$";			BasicDBObject o = new BasicDBObject("id", Pattern.compile(id, Pattern.CASE_INSENSITIVE));			return new ConsoleLogDtoCursor(collection.find(o));		}
		public ConsoleLogDtoCursor findByDate(String date) {						BasicDBObject o = new BasicDBObject("date", date);			return new ConsoleLogDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public ConsoleLogDtoCursor findByDateFuzzy(String date) {						date = date.replaceAll("\\*", ".*");			date = "^" + date + "$";			BasicDBObject o = new BasicDBObject("date", Pattern.compile(date, Pattern.CASE_INSENSITIVE));			return new ConsoleLogDtoCursor(collection.find(o));		}
		public ConsoleLogDtoCursor findByLog(String log) {						BasicDBObject o = new BasicDBObject("log", log);			return new ConsoleLogDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public ConsoleLogDtoCursor findByLogFuzzy(String log) {						log = log.replaceAll("\\*", ".*");			log = "^" + log + "$";			BasicDBObject o = new BasicDBObject("log", Pattern.compile(log, Pattern.CASE_INSENSITIVE));			return new ConsoleLogDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public ConsoleLogDto createDTO() {			return new ConsoleLogDto();		}			public static class ConsoleLogDtoCursor implements Iterator<ConsoleLogDto>, Iterable<ConsoleLogDto>{				private DBCursor	cursor;			private int pageAll;				public ConsoleLogDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public ConsoleLogDto next() {				DBObject next = cursor.next();				ConsoleLogDto dto = new ConsoleLogDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void skip(int skip) {				cursor.skip(skip);			}						public void limit(int limit) {				cursor.limit(limit);			}						/**			 * 分页, page从1开始 countOfEveryPage必须大于0			 */			public void page(int page, int countOfEveryPage) {				if(countOfEveryPage <= 0) {					throw new RuntimeException("countOfEveryPage must > 0");				}				int count = getCount();				pageAll = count / countOfEveryPage;				if(count % countOfEveryPage != 0) {					pageAll ++;				}								if(page > pageAll)					page = pageAll;								if(page < 1)					page = 1;									int skip = (page - 1) * countOfEveryPage ;				skip(skip);				limit(countOfEveryPage);			}						public int getPageAll() {				return pageAll;			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<ConsoleLogDto> iterator() {				return this;			}		}	}
		public static class GmLogDao {			private DBCollection	collection;			public GmLogDao(DBCollection collection) {			this.collection = collection;		}			public void save(GmLogDto u) {			collection.save(u.toObject());		}			public void delete(GmLogDto u) {			delete(u.getId());		}			public void delete(String id) {			collection.remove(key(id));		}			public GmLogDto get(String id) {			DBObject o = collection.findOne(key(id));			if(o == null) {				return null;			}			GmLogDto x = new GmLogDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String id) {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);			return o;		}			public GmLogDtoCursor find() {			return new GmLogDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public GmLogDtoCursor findById(String id) {			collection.ensureIndex("id");			BasicDBObject o = new BasicDBObject("id", id);			return new GmLogDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public GmLogDtoCursor findByIdFuzzy(String id) {			collection.ensureIndex("id");			id = id.replaceAll("\\*", ".*");			id = "^" + id + "$";			BasicDBObject o = new BasicDBObject("id", Pattern.compile(id, Pattern.CASE_INSENSITIVE));			return new GmLogDtoCursor(collection.find(o));		}
		public GmLogDtoCursor findByDate(String date) {						BasicDBObject o = new BasicDBObject("date", date);			return new GmLogDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public GmLogDtoCursor findByDateFuzzy(String date) {						date = date.replaceAll("\\*", ".*");			date = "^" + date + "$";			BasicDBObject o = new BasicDBObject("date", Pattern.compile(date, Pattern.CASE_INSENSITIVE));			return new GmLogDtoCursor(collection.find(o));		}
		public GmLogDtoCursor findByUser(String user) {						BasicDBObject o = new BasicDBObject("user", user);			return new GmLogDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public GmLogDtoCursor findByUserFuzzy(String user) {						user = user.replaceAll("\\*", ".*");			user = "^" + user + "$";			BasicDBObject o = new BasicDBObject("user", Pattern.compile(user, Pattern.CASE_INSENSITIVE));			return new GmLogDtoCursor(collection.find(o));		}
		public GmLogDtoCursor findByClassName(String className) {						BasicDBObject o = new BasicDBObject("className", className);			return new GmLogDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public GmLogDtoCursor findByClassNameFuzzy(String className) {						className = className.replaceAll("\\*", ".*");			className = "^" + className + "$";			BasicDBObject o = new BasicDBObject("className", Pattern.compile(className, Pattern.CASE_INSENSITIVE));			return new GmLogDtoCursor(collection.find(o));		}
		public GmLogDtoCursor findByMethodName(String methodName) {						BasicDBObject o = new BasicDBObject("methodName", methodName);			return new GmLogDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public GmLogDtoCursor findByMethodNameFuzzy(String methodName) {						methodName = methodName.replaceAll("\\*", ".*");			methodName = "^" + methodName + "$";			BasicDBObject o = new BasicDBObject("methodName", Pattern.compile(methodName, Pattern.CASE_INSENSITIVE));			return new GmLogDtoCursor(collection.find(o));		}
		public GmLogDtoCursor findByResult(String result) {						BasicDBObject o = new BasicDBObject("result", result);			return new GmLogDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public GmLogDtoCursor findByResultFuzzy(String result) {						result = result.replaceAll("\\*", ".*");			result = "^" + result + "$";			BasicDBObject o = new BasicDBObject("result", Pattern.compile(result, Pattern.CASE_INSENSITIVE));			return new GmLogDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public GmLogDto createDTO() {			return new GmLogDto();		}			public static class GmLogDtoCursor implements Iterator<GmLogDto>, Iterable<GmLogDto>{				private DBCursor	cursor;			private int pageAll;				public GmLogDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public GmLogDto next() {				DBObject next = cursor.next();				GmLogDto dto = new GmLogDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void skip(int skip) {				cursor.skip(skip);			}						public void limit(int limit) {				cursor.limit(limit);			}						/**			 * 分页, page从1开始 countOfEveryPage必须大于0			 */			public void page(int page, int countOfEveryPage) {				if(countOfEveryPage <= 0) {					throw new RuntimeException("countOfEveryPage must > 0");				}				int count = getCount();				pageAll = count / countOfEveryPage;				if(count % countOfEveryPage != 0) {					pageAll ++;				}								if(page > pageAll)					page = pageAll;								if(page < 1)					page = 1;									int skip = (page - 1) * countOfEveryPage ;				skip(skip);				limit(countOfEveryPage);			}						public int getPageAll() {				return pageAll;			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<GmLogDto> iterator() {				return this;			}		}	}
		public static class RoleDao {			private DBCollection	collection;			public RoleDao(DBCollection collection) {			this.collection = collection;		}			public void save(RoleDto u) {			collection.save(u.toObject());		}			public void delete(RoleDto u) {			delete(u.getId());		}			public void delete(String id) {			collection.remove(key(id));		}			public RoleDto get(String id) {			DBObject o = collection.findOne(key(id));			if(o == null) {				return null;			}			RoleDto x = new RoleDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String id) {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);			return o;		}			public RoleDtoCursor find() {			return new RoleDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public RoleDtoCursor findById(String id) {			collection.ensureIndex("id");			BasicDBObject o = new BasicDBObject("id", id);			return new RoleDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public RoleDtoCursor findByIdFuzzy(String id) {			collection.ensureIndex("id");			id = id.replaceAll("\\*", ".*");			id = "^" + id + "$";			BasicDBObject o = new BasicDBObject("id", Pattern.compile(id, Pattern.CASE_INSENSITIVE));			return new RoleDtoCursor(collection.find(o));		}
		public RoleDtoCursor findByOwnerId(String ownerId) {			collection.ensureIndex("ownerId");			BasicDBObject o = new BasicDBObject("ownerId", ownerId);			return new RoleDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public RoleDtoCursor findByOwnerIdFuzzy(String ownerId) {			collection.ensureIndex("ownerId");			ownerId = ownerId.replaceAll("\\*", ".*");			ownerId = "^" + ownerId + "$";			BasicDBObject o = new BasicDBObject("ownerId", Pattern.compile(ownerId, Pattern.CASE_INSENSITIVE));			return new RoleDtoCursor(collection.find(o));		}
		public RoleDtoCursor findByIsRobot(boolean isRobot) {						BasicDBObject o = new BasicDBObject("isRobot", isRobot);			return new RoleDtoCursor(collection.find(o));		}
		public RoleDtoCursor findByNick(String nick) {						BasicDBObject o = new BasicDBObject("nick", nick);			return new RoleDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public RoleDtoCursor findByNickFuzzy(String nick) {						nick = nick.replaceAll("\\*", ".*");			nick = "^" + nick + "$";			BasicDBObject o = new BasicDBObject("nick", Pattern.compile(nick, Pattern.CASE_INSENSITIVE));			return new RoleDtoCursor(collection.find(o));		}
		public RoleDtoCursor findByHead(int head) {						BasicDBObject o = new BasicDBObject("head", head);			return new RoleDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public RoleDtoCursor findHeadBetween(int min, int max) {						BasicDBObject o = new BasicDBObject();			o.put("head", new BasicDBObject("$gte", min).append("$lte", max));			return new RoleDtoCursor(collection.find(o));		}
		public RoleDtoCursor findByCoin(long coin) {						BasicDBObject o = new BasicDBObject("coin", coin);			return new RoleDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public RoleDtoCursor findCoinBetween(long min, long max) {						BasicDBObject o = new BasicDBObject();			o.put("coin", new BasicDBObject("$gte", min).append("$lte", max));			return new RoleDtoCursor(collection.find(o));		}
		public RoleDtoCursor findByCreateTime(long createTime) {						BasicDBObject o = new BasicDBObject("createTime", createTime);			return new RoleDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public RoleDtoCursor findCreateTimeBetween(long min, long max) {						BasicDBObject o = new BasicDBObject();			o.put("createTime", new BasicDBObject("$gte", min).append("$lte", max));			return new RoleDtoCursor(collection.find(o));		}
		public RoleDtoCursor findByMasonry(long masonry) {						BasicDBObject o = new BasicDBObject("masonry", masonry);			return new RoleDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public RoleDtoCursor findMasonryBetween(long min, long max) {						BasicDBObject o = new BasicDBObject();			o.put("masonry", new BasicDBObject("$gte", min).append("$lte", max));			return new RoleDtoCursor(collection.find(o));		}
		public RoleDtoCursor findByRechargeHistory(long rechargeHistory) {						BasicDBObject o = new BasicDBObject("rechargeHistory", rechargeHistory);			return new RoleDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public RoleDtoCursor findRechargeHistoryBetween(long min, long max) {						BasicDBObject o = new BasicDBObject();			o.put("rechargeHistory", new BasicDBObject("$gte", min).append("$lte", max));			return new RoleDtoCursor(collection.find(o));		}
		public RoleDtoCursor findByHasJinYan(boolean hasJinYan) {						BasicDBObject o = new BasicDBObject("hasJinYan", hasJinYan);			return new RoleDtoCursor(collection.find(o));		}
		public RoleDtoCursor findByIsOnline(boolean isOnline) {						BasicDBObject o = new BasicDBObject("isOnline", isOnline);			return new RoleDtoCursor(collection.find(o));		}
		public RoleDtoCursor findByHasFengHao(boolean hasFengHao) {						BasicDBObject o = new BasicDBObject("hasFengHao", hasFengHao);			return new RoleDtoCursor(collection.find(o));		}
		public RoleDtoCursor findByMaxMissionId(int maxMissionId) {						BasicDBObject o = new BasicDBObject("maxMissionId", maxMissionId);			return new RoleDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public RoleDtoCursor findMaxMissionIdBetween(int min, int max) {						BasicDBObject o = new BasicDBObject();			o.put("maxMissionId", new BasicDBObject("$gte", min).append("$lte", max));			return new RoleDtoCursor(collection.find(o));		}
		public RoleDtoCursor findByPhysical(int physical) {						BasicDBObject o = new BasicDBObject("physical", physical);			return new RoleDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public RoleDtoCursor findPhysicalBetween(int min, int max) {						BasicDBObject o = new BasicDBObject();			o.put("physical", new BasicDBObject("$gte", min).append("$lte", max));			return new RoleDtoCursor(collection.find(o));		}
		public RoleDtoCursor findByCreateIp(String createIp) {						BasicDBObject o = new BasicDBObject("createIp", createIp);			return new RoleDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public RoleDtoCursor findByCreateIpFuzzy(String createIp) {						createIp = createIp.replaceAll("\\*", ".*");			createIp = "^" + createIp + "$";			BasicDBObject o = new BasicDBObject("createIp", Pattern.compile(createIp, Pattern.CASE_INSENSITIVE));			return new RoleDtoCursor(collection.find(o));		}
		public RoleDtoCursor findByPhysicalCd(int physicalCd) {						BasicDBObject o = new BasicDBObject("physicalCd", physicalCd);			return new RoleDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public RoleDtoCursor findPhysicalCdBetween(int min, int max) {						BasicDBObject o = new BasicDBObject();			o.put("physicalCd", new BasicDBObject("$gte", min).append("$lte", max));			return new RoleDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public RoleDto createDTO() {			return new RoleDto();		}			public static class RoleDtoCursor implements Iterator<RoleDto>, Iterable<RoleDto>{				private DBCursor	cursor;			private int pageAll;				public RoleDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public RoleDto next() {				DBObject next = cursor.next();				RoleDto dto = new RoleDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void skip(int skip) {				cursor.skip(skip);			}						public void limit(int limit) {				cursor.limit(limit);			}						/**			 * 分页, page从1开始 countOfEveryPage必须大于0			 */			public void page(int page, int countOfEveryPage) {				if(countOfEveryPage <= 0) {					throw new RuntimeException("countOfEveryPage must > 0");				}				int count = getCount();				pageAll = count / countOfEveryPage;				if(count % countOfEveryPage != 0) {					pageAll ++;				}								if(page > pageAll)					page = pageAll;								if(page < 1)					page = 1;									int skip = (page - 1) * countOfEveryPage ;				skip(skip);				limit(countOfEveryPage);			}						public int getPageAll() {				return pageAll;			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<RoleDto> iterator() {				return this;			}		}	}
		public static class SystemKeyValueDao {			private DBCollection	collection;			public SystemKeyValueDao(DBCollection collection) {			this.collection = collection;		}			public void save(SystemKeyValueDto u) {			collection.save(u.toObject());		}			public void delete(SystemKeyValueDto u) {			delete(u.getKey());		}			public void delete(String key) {			collection.remove(key(key));		}			public SystemKeyValueDto get(String key) {			DBObject o = collection.findOne(key(key));			if(o == null) {				return null;			}			SystemKeyValueDto x = new SystemKeyValueDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String key) {			BasicDBObject o = new BasicDBObject();		o.put("_id", key);			return o;		}			public SystemKeyValueDtoCursor find() {			return new SystemKeyValueDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public SystemKeyValueDtoCursor findByKey(String key) {			collection.ensureIndex("key");			BasicDBObject o = new BasicDBObject("key", key);			return new SystemKeyValueDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public SystemKeyValueDtoCursor findByKeyFuzzy(String key) {			collection.ensureIndex("key");			key = key.replaceAll("\\*", ".*");			key = "^" + key + "$";			BasicDBObject o = new BasicDBObject("key", Pattern.compile(key, Pattern.CASE_INSENSITIVE));			return new SystemKeyValueDtoCursor(collection.find(o));		}
		public SystemKeyValueDtoCursor findByValue(String value) {						BasicDBObject o = new BasicDBObject("value", value);			return new SystemKeyValueDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public SystemKeyValueDtoCursor findByValueFuzzy(String value) {						value = value.replaceAll("\\*", ".*");			value = "^" + value + "$";			BasicDBObject o = new BasicDBObject("value", Pattern.compile(value, Pattern.CASE_INSENSITIVE));			return new SystemKeyValueDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public SystemKeyValueDto createDTO() {			return new SystemKeyValueDto();		}			public static class SystemKeyValueDtoCursor implements Iterator<SystemKeyValueDto>, Iterable<SystemKeyValueDto>{				private DBCursor	cursor;			private int pageAll;				public SystemKeyValueDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public SystemKeyValueDto next() {				DBObject next = cursor.next();				SystemKeyValueDto dto = new SystemKeyValueDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void skip(int skip) {				cursor.skip(skip);			}						public void limit(int limit) {				cursor.limit(limit);			}						/**			 * 分页, page从1开始 countOfEveryPage必须大于0			 */			public void page(int page, int countOfEveryPage) {				if(countOfEveryPage <= 0) {					throw new RuntimeException("countOfEveryPage must > 0");				}				int count = getCount();				pageAll = count / countOfEveryPage;				if(count % countOfEveryPage != 0) {					pageAll ++;				}								if(page > pageAll)					page = pageAll;								if(page < 1)					page = 1;									int skip = (page - 1) * countOfEveryPage ;				skip(skip);				limit(countOfEveryPage);			}						public int getPageAll() {				return pageAll;			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<SystemKeyValueDto> iterator() {				return this;			}		}	}
		public static class YiJieRechargeLogDao {			private DBCollection	collection;			public YiJieRechargeLogDao(DBCollection collection) {			this.collection = collection;		}			public void save(YiJieRechargeLogDto u) {			collection.save(u.toObject());		}			public void delete(YiJieRechargeLogDto u) {			delete(u.getSsid());		}			public void delete(String ssid) {			collection.remove(key(ssid));		}			public YiJieRechargeLogDto get(String ssid) {			DBObject o = collection.findOne(key(ssid));			if(o == null) {				return null;			}			YiJieRechargeLogDto x = new YiJieRechargeLogDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String ssid) {			BasicDBObject o = new BasicDBObject();		o.put("_id", ssid);			return o;		}			public YiJieRechargeLogDtoCursor find() {			return new YiJieRechargeLogDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public YiJieRechargeLogDtoCursor findBySsid(String ssid) {			collection.ensureIndex("ssid");			BasicDBObject o = new BasicDBObject("ssid", ssid);			return new YiJieRechargeLogDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public YiJieRechargeLogDtoCursor findBySsidFuzzy(String ssid) {			collection.ensureIndex("ssid");			ssid = ssid.replaceAll("\\*", ".*");			ssid = "^" + ssid + "$";			BasicDBObject o = new BasicDBObject("ssid", Pattern.compile(ssid, Pattern.CASE_INSENSITIVE));			return new YiJieRechargeLogDtoCursor(collection.find(o));		}
		public YiJieRechargeLogDtoCursor findByApp(String app) {						BasicDBObject o = new BasicDBObject("app", app);			return new YiJieRechargeLogDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public YiJieRechargeLogDtoCursor findByAppFuzzy(String app) {						app = app.replaceAll("\\*", ".*");			app = "^" + app + "$";			BasicDBObject o = new BasicDBObject("app", Pattern.compile(app, Pattern.CASE_INSENSITIVE));			return new YiJieRechargeLogDtoCursor(collection.find(o));		}
		public YiJieRechargeLogDtoCursor findByCbi(String cbi) {						BasicDBObject o = new BasicDBObject("cbi", cbi);			return new YiJieRechargeLogDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public YiJieRechargeLogDtoCursor findByCbiFuzzy(String cbi) {						cbi = cbi.replaceAll("\\*", ".*");			cbi = "^" + cbi + "$";			BasicDBObject o = new BasicDBObject("cbi", Pattern.compile(cbi, Pattern.CASE_INSENSITIVE));			return new YiJieRechargeLogDtoCursor(collection.find(o));		}
		public YiJieRechargeLogDtoCursor findByCt(long ct) {						BasicDBObject o = new BasicDBObject("ct", ct);			return new YiJieRechargeLogDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public YiJieRechargeLogDtoCursor findCtBetween(long min, long max) {						BasicDBObject o = new BasicDBObject();			o.put("ct", new BasicDBObject("$gte", min).append("$lte", max));			return new YiJieRechargeLogDtoCursor(collection.find(o));		}
		public YiJieRechargeLogDtoCursor findByFee(int fee) {						BasicDBObject o = new BasicDBObject("fee", fee);			return new YiJieRechargeLogDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public YiJieRechargeLogDtoCursor findFeeBetween(int min, int max) {						BasicDBObject o = new BasicDBObject();			o.put("fee", new BasicDBObject("$gte", min).append("$lte", max));			return new YiJieRechargeLogDtoCursor(collection.find(o));		}
		public YiJieRechargeLogDtoCursor findByPt(long pt) {						BasicDBObject o = new BasicDBObject("pt", pt);			return new YiJieRechargeLogDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public YiJieRechargeLogDtoCursor findPtBetween(long min, long max) {						BasicDBObject o = new BasicDBObject();			o.put("pt", new BasicDBObject("$gte", min).append("$lte", max));			return new YiJieRechargeLogDtoCursor(collection.find(o));		}
		public YiJieRechargeLogDtoCursor findBySdk(String sdk) {						BasicDBObject o = new BasicDBObject("sdk", sdk);			return new YiJieRechargeLogDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public YiJieRechargeLogDtoCursor findBySdkFuzzy(String sdk) {						sdk = sdk.replaceAll("\\*", ".*");			sdk = "^" + sdk + "$";			BasicDBObject o = new BasicDBObject("sdk", Pattern.compile(sdk, Pattern.CASE_INSENSITIVE));			return new YiJieRechargeLogDtoCursor(collection.find(o));		}
		public YiJieRechargeLogDtoCursor findBySign(String sign) {						BasicDBObject o = new BasicDBObject("sign", sign);			return new YiJieRechargeLogDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public YiJieRechargeLogDtoCursor findBySignFuzzy(String sign) {						sign = sign.replaceAll("\\*", ".*");			sign = "^" + sign + "$";			BasicDBObject o = new BasicDBObject("sign", Pattern.compile(sign, Pattern.CASE_INSENSITIVE));			return new YiJieRechargeLogDtoCursor(collection.find(o));		}
		public YiJieRechargeLogDtoCursor findBySt(int st) {						BasicDBObject o = new BasicDBObject("st", st);			return new YiJieRechargeLogDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public YiJieRechargeLogDtoCursor findStBetween(int min, int max) {						BasicDBObject o = new BasicDBObject();			o.put("st", new BasicDBObject("$gte", min).append("$lte", max));			return new YiJieRechargeLogDtoCursor(collection.find(o));		}
		public YiJieRechargeLogDtoCursor findByTcd(String tcd) {						BasicDBObject o = new BasicDBObject("tcd", tcd);			return new YiJieRechargeLogDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public YiJieRechargeLogDtoCursor findByTcdFuzzy(String tcd) {						tcd = tcd.replaceAll("\\*", ".*");			tcd = "^" + tcd + "$";			BasicDBObject o = new BasicDBObject("tcd", Pattern.compile(tcd, Pattern.CASE_INSENSITIVE));			return new YiJieRechargeLogDtoCursor(collection.find(o));		}
		public YiJieRechargeLogDtoCursor findByUid(String uid) {						BasicDBObject o = new BasicDBObject("uid", uid);			return new YiJieRechargeLogDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public YiJieRechargeLogDtoCursor findByUidFuzzy(String uid) {						uid = uid.replaceAll("\\*", ".*");			uid = "^" + uid + "$";			BasicDBObject o = new BasicDBObject("uid", Pattern.compile(uid, Pattern.CASE_INSENSITIVE));			return new YiJieRechargeLogDtoCursor(collection.find(o));		}
		public YiJieRechargeLogDtoCursor findByVer(String ver) {						BasicDBObject o = new BasicDBObject("ver", ver);			return new YiJieRechargeLogDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public YiJieRechargeLogDtoCursor findByVerFuzzy(String ver) {						ver = ver.replaceAll("\\*", ".*");			ver = "^" + ver + "$";			BasicDBObject o = new BasicDBObject("ver", Pattern.compile(ver, Pattern.CASE_INSENSITIVE));			return new YiJieRechargeLogDtoCursor(collection.find(o));		}
		public YiJieRechargeLogDtoCursor findByRoleId(String roleId) {						BasicDBObject o = new BasicDBObject("roleId", roleId);			return new YiJieRechargeLogDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public YiJieRechargeLogDtoCursor findByRoleIdFuzzy(String roleId) {						roleId = roleId.replaceAll("\\*", ".*");			roleId = "^" + roleId + "$";			BasicDBObject o = new BasicDBObject("roleId", Pattern.compile(roleId, Pattern.CASE_INSENSITIVE));			return new YiJieRechargeLogDtoCursor(collection.find(o));		}
		public YiJieRechargeLogDtoCursor findByNick(String nick) {						BasicDBObject o = new BasicDBObject("nick", nick);			return new YiJieRechargeLogDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public YiJieRechargeLogDtoCursor findByNickFuzzy(String nick) {						nick = nick.replaceAll("\\*", ".*");			nick = "^" + nick + "$";			BasicDBObject o = new BasicDBObject("nick", Pattern.compile(nick, Pattern.CASE_INSENSITIVE));			return new YiJieRechargeLogDtoCursor(collection.find(o));		}
		public YiJieRechargeLogDtoCursor findByCoin(long coin) {						BasicDBObject o = new BasicDBObject("coin", coin);			return new YiJieRechargeLogDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public YiJieRechargeLogDtoCursor findCoinBetween(long min, long max) {						BasicDBObject o = new BasicDBObject();			o.put("coin", new BasicDBObject("$gte", min).append("$lte", max));			return new YiJieRechargeLogDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public YiJieRechargeLogDto createDTO() {			return new YiJieRechargeLogDto();		}			public static class YiJieRechargeLogDtoCursor implements Iterator<YiJieRechargeLogDto>, Iterable<YiJieRechargeLogDto>{				private DBCursor	cursor;			private int pageAll;				public YiJieRechargeLogDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public YiJieRechargeLogDto next() {				DBObject next = cursor.next();				YiJieRechargeLogDto dto = new YiJieRechargeLogDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void skip(int skip) {				cursor.skip(skip);			}						public void limit(int limit) {				cursor.limit(limit);			}						/**			 * 分页, page从1开始 countOfEveryPage必须大于0			 */			public void page(int page, int countOfEveryPage) {				if(countOfEveryPage <= 0) {					throw new RuntimeException("countOfEveryPage must > 0");				}				int count = getCount();				pageAll = count / countOfEveryPage;				if(count % countOfEveryPage != 0) {					pageAll ++;				}								if(page > pageAll)					page = pageAll;								if(page < 1)					page = 1;									int skip = (page - 1) * countOfEveryPage ;				skip(skip);				limit(countOfEveryPage);			}						public int getPageAll() {				return pageAll;			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<YiJieRechargeLogDto> iterator() {				return this;			}		}	}
		public static class ConsoleLogDto implements MongoDto{		private String id = "";
		private String date = "";
		private String log = "";
		public ConsoleLogDto() {		}				/**		 * Copy new one		 */		public ConsoleLogDto(ConsoleLogDto src) {			id = MongoGen.copy(src.id);			
			date = MongoGen.copy(src.date);			
			log = MongoGen.copy(src.log);			
		}		public String getId() {			return this.id;		}
		public String getDate() {			return this.date;		}
		public String getLog() {			return this.log;		}
		public void setId(String id) {			this.id = id;		}
		public void setDate(String date) {			this.date = date;		}
		public void setLog(String log) {			this.log = log;		}
		@Override		public DBObject toObject() {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);
			o.put("id", MongoGen.toObject(id));			
			o.put("date", MongoGen.toObject(date));			
			o.put("log", MongoGen.toObject(log));			
			return o;		}		@Override		public void fromDBObject(DBObject o) {			id = getString(o, "id");
			date = getString(o, "date");
			log = getString(o, "log");
		}





	static MongoMap<ConsoleLogDto> copy(MongoMap<ConsoleLogDto> map) {		MongoMapImpl<ConsoleLogDto> m = new MongoMapImpl<ConsoleLogDto>();				for (String key : map.keySet()) {			ConsoleLogDto v = map.get(key);			m.put(key, MongoGen.copy(v));		}		return m;	}			static List<ConsoleLogDto> copy(List<ConsoleLogDto> list) {		List<ConsoleLogDto> ls = Lists.newArrayList();		for (ConsoleLogDto t : list) {			ls.add(MongoGen.copy(t));		}		return ls;	}		@Override		public String toString() {			return toObject().toString();		}	}
		public static class GmLogDto implements MongoDto{		private String id = "";
		private String date = "";
		private String user = "";
		private String className = "";
		private String methodName = "";
		private List<String> args = Lists.newArrayList();
		private String result = "";
		public GmLogDto() {		}				/**		 * Copy new one		 */		public GmLogDto(GmLogDto src) {			id = MongoGen.copy(src.id);			
			date = MongoGen.copy(src.date);			
			user = MongoGen.copy(src.user);			
			className = MongoGen.copy(src.className);			
			methodName = MongoGen.copy(src.methodName);			
			args = MongoGen.copyString(src.args);			
			result = MongoGen.copy(src.result);			
		}		public String getId() {			return this.id;		}
		public String getDate() {			return this.date;		}
		public String getUser() {			return this.user;		}
		public String getClassName() {			return this.className;		}
		public String getMethodName() {			return this.methodName;		}
		public List<String> getArgs() {			return this.args;		}
		public String getResult() {			return this.result;		}
		public void setId(String id) {			this.id = id;		}
		public void setDate(String date) {			this.date = date;		}
		public void setUser(String user) {			this.user = user;		}
		public void setClassName(String className) {			this.className = className;		}
		public void setMethodName(String methodName) {			this.methodName = methodName;		}
		public void setArgs(List<String> args) {			this.args = args;		}
		public void setResult(String result) {			this.result = result;		}
		@Override		public DBObject toObject() {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);
			o.put("id", MongoGen.toObject(id));			
			o.put("date", MongoGen.toObject(date));			
			o.put("user", MongoGen.toObject(user));			
			o.put("className", MongoGen.toObject(className));			
			o.put("methodName", MongoGen.toObject(methodName));			
			o.put("args", MongoGen.toObjectString(args));			
			o.put("result", MongoGen.toObject(result));			
			return o;		}		@Override		public void fromDBObject(DBObject o) {			id = getString(o, "id");
			date = getString(o, "date");
			user = getString(o, "user");
			className = getString(o, "className");
			methodName = getString(o, "methodName");
			args = loadArgs(o);
			result = getString(o, "result");
		}











		List<String> loadArgs(DBObject o) {			List<String> ls = Lists.newArrayList();						BasicDBList args = getBasicDBList(o, "args");			for (Object xxx : args) {				ls.add((String)xxx);			}			return ls;		}										

	static MongoMap<GmLogDto> copy(MongoMap<GmLogDto> map) {		MongoMapImpl<GmLogDto> m = new MongoMapImpl<GmLogDto>();				for (String key : map.keySet()) {			GmLogDto v = map.get(key);			m.put(key, MongoGen.copy(v));		}		return m;	}			static List<GmLogDto> copy(List<GmLogDto> list) {		List<GmLogDto> ls = Lists.newArrayList();		for (GmLogDto t : list) {			ls.add(MongoGen.copy(t));		}		return ls;	}		@Override		public String toString() {			return toObject().toString();		}	}
		public static class MissionDataDto implements MongoDto{		private String status = "";
		public MissionDataDto() {		}				/**		 * Copy new one		 */		public MissionDataDto(MissionDataDto src) {			status = MongoGen.copy(src.status);			
		}		public String getStatus() {			return this.status;		}
		public void setStatus(String status) {			this.status = status;		}
		@Override		public DBObject toObject() {			BasicDBObject o = new BasicDBObject();
			o.put("status", MongoGen.toObject(status));			
			return o;		}		@Override		public void fromDBObject(DBObject o) {			status = getString(o, "status");
		}

	static MongoMap<MissionDataDto> copy(MongoMap<MissionDataDto> map) {		MongoMapImpl<MissionDataDto> m = new MongoMapImpl<MissionDataDto>();				for (String key : map.keySet()) {			MissionDataDto v = map.get(key);			m.put(key, MongoGen.copy(v));		}		return m;	}			static List<MissionDataDto> copy(List<MissionDataDto> list) {		List<MissionDataDto> ls = Lists.newArrayList();		for (MissionDataDto t : list) {			ls.add(MongoGen.copy(t));		}		return ls;	}		@Override		public String toString() {			return toObject().toString();		}	}
		public static class RoleDto implements MongoDto{		private String id = "";
		private String ownerId = "";
		private boolean isRobot = false;
		private String nick = "";
		private int head = 0;
		private long coin = 0;
		private long createTime = 0;
		private long masonry = 0;
		private long rechargeHistory = 0;
		private boolean hasJinYan = false;
		private boolean isOnline = false;
		private boolean hasFengHao = false;
		private int maxMissionId = 0;
		private int physical = 0;
		private String createIp = "";
		private int physicalCd = 0;
		private MongoMap<MissionDataDto> missionData = Maps.newMongoMap();
		private MongoMap<String> keyValueDaily = Maps.newMongoMap();
		private MongoMap<String> keyValueForever = Maps.newMongoMap();
		public RoleDto() {		}				/**		 * Copy new one		 */		public RoleDto(RoleDto src) {			id = MongoGen.copy(src.id);			
			ownerId = MongoGen.copy(src.ownerId);			
			isRobot = MongoGen.copy(src.isRobot);			
			nick = MongoGen.copy(src.nick);			
			head = MongoGen.copy(src.head);			
			coin = MongoGen.copy(src.coin);			
			createTime = MongoGen.copy(src.createTime);			
			masonry = MongoGen.copy(src.masonry);			
			rechargeHistory = MongoGen.copy(src.rechargeHistory);			
			hasJinYan = MongoGen.copy(src.hasJinYan);			
			isOnline = MongoGen.copy(src.isOnline);			
			hasFengHao = MongoGen.copy(src.hasFengHao);			
			maxMissionId = MongoGen.copy(src.maxMissionId);			
			physical = MongoGen.copy(src.physical);			
			createIp = MongoGen.copy(src.createIp);			
			physicalCd = MongoGen.copy(src.physicalCd);			
			missionData = MissionDataDto.copy(src.missionData);			
			keyValueDaily = MongoGen.copyString(src.keyValueDaily);			
			keyValueForever = MongoGen.copyString(src.keyValueForever);			
		}		public String getId() {			return this.id;		}
		public String getOwnerId() {			return this.ownerId;		}
		public boolean getIsRobot() {			return this.isRobot;		}
		public String getNick() {			return this.nick;		}
		public int getHead() {			return this.head;		}
		public long getCoin() {			return this.coin;		}
		public long getCreateTime() {			return this.createTime;		}
		public long getMasonry() {			return this.masonry;		}
		public long getRechargeHistory() {			return this.rechargeHistory;		}
		public boolean getHasJinYan() {			return this.hasJinYan;		}
		public boolean getIsOnline() {			return this.isOnline;		}
		public boolean getHasFengHao() {			return this.hasFengHao;		}
		public int getMaxMissionId() {			return this.maxMissionId;		}
		public int getPhysical() {			return this.physical;		}
		public String getCreateIp() {			return this.createIp;		}
		public int getPhysicalCd() {			return this.physicalCd;		}
		public MongoMap<MissionDataDto> getMissionData() {			return this.missionData;		}
		public MongoMap<String> getKeyValueDaily() {			return this.keyValueDaily;		}
		public MongoMap<String> getKeyValueForever() {			return this.keyValueForever;		}
		public void setId(String id) {			this.id = id;		}
		public void setOwnerId(String ownerId) {			this.ownerId = ownerId;		}
		public void setIsRobot(boolean isRobot) {			this.isRobot = isRobot;		}
		public void setNick(String nick) {			this.nick = nick;		}
		public void setHead(int head) {			this.head = head;		}
		public void setCoin(long coin) {			this.coin = coin;		}
		public void setCreateTime(long createTime) {			this.createTime = createTime;		}
		public void setMasonry(long masonry) {			this.masonry = masonry;		}
		public void setRechargeHistory(long rechargeHistory) {			this.rechargeHistory = rechargeHistory;		}
		public void setHasJinYan(boolean hasJinYan) {			this.hasJinYan = hasJinYan;		}
		public void setIsOnline(boolean isOnline) {			this.isOnline = isOnline;		}
		public void setHasFengHao(boolean hasFengHao) {			this.hasFengHao = hasFengHao;		}
		public void setMaxMissionId(int maxMissionId) {			this.maxMissionId = maxMissionId;		}
		public void setPhysical(int physical) {			this.physical = physical;		}
		public void setCreateIp(String createIp) {			this.createIp = createIp;		}
		public void setPhysicalCd(int physicalCd) {			this.physicalCd = physicalCd;		}
		public void setMissionData(MongoMap<MissionDataDto> missionData) {			this.missionData = missionData;		}
		public void setKeyValueDaily(MongoMap<String> keyValueDaily) {			this.keyValueDaily = keyValueDaily;		}
		public void setKeyValueForever(MongoMap<String> keyValueForever) {			this.keyValueForever = keyValueForever;		}
		@Override		public DBObject toObject() {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);
			o.put("id", MongoGen.toObject(id));			
			o.put("ownerId", MongoGen.toObject(ownerId));			
			o.put("isRobot", MongoGen.toObject(isRobot));			
			o.put("nick", MongoGen.toObject(nick));			
			o.put("head", MongoGen.toObject(head));			
			o.put("coin", MongoGen.toObject(coin));			
			o.put("createTime", MongoGen.toObject(createTime));			
			o.put("masonry", MongoGen.toObject(masonry));			
			o.put("rechargeHistory", MongoGen.toObject(rechargeHistory));			
			o.put("hasJinYan", MongoGen.toObject(hasJinYan));			
			o.put("isOnline", MongoGen.toObject(isOnline));			
			o.put("hasFengHao", MongoGen.toObject(hasFengHao));			
			o.put("maxMissionId", MongoGen.toObject(maxMissionId));			
			o.put("physical", MongoGen.toObject(physical));			
			o.put("createIp", MongoGen.toObject(createIp));			
			o.put("physicalCd", MongoGen.toObject(physicalCd));			
			o.put("missionData", MongoGen.toObject(missionData));
			o.put("keyValueDaily", MongoGen.toObjectString(keyValueDaily));
			o.put("keyValueForever", MongoGen.toObjectString(keyValueForever));
			return o;		}		@Override		public void fromDBObject(DBObject o) {			id = getString(o, "id");
			ownerId = getString(o, "ownerId");
			isRobot = getBoolean(o, "isRobot");
			nick = getString(o, "nick");
			head = getInteger(o, "head");
			coin = getLong(o, "coin");
			createTime = getLong(o, "createTime");
			masonry = getLong(o, "masonry");
			rechargeHistory = getLong(o, "rechargeHistory");
			hasJinYan = getBoolean(o, "hasJinYan");
			isOnline = getBoolean(o, "isOnline");
			hasFengHao = getBoolean(o, "hasFengHao");
			maxMissionId = getInteger(o, "maxMissionId");
			physical = getInteger(o, "physical");
			createIp = getString(o, "createIp");
			physicalCd = getInteger(o, "physicalCd");
			missionData = loadMissionData(o);
			keyValueDaily = loadKeyValueDaily(o);
			keyValueForever = loadKeyValueForever(o);
		}















		MongoMap<MissionDataDto> loadMissionData(DBObject o) {			BasicDBObject dto = (BasicDBObject) o.get("missionData");			if (dto == null) {				return null;			}			MongoMap<MissionDataDto> map = Maps.newMongoMap();			for (String key : dto.keySet()) {				MissionDataDto d = new MissionDataDto();				d.fromDBObject((BasicDBObject)dto.get(key));				map.put(key, d);			}			return map;		}						
		MongoMap<String> loadKeyValueDaily(DBObject o) {			BasicDBObject dto = (BasicDBObject) o.get("keyValueDaily");			if (dto == null) {				return null;			}			MongoMap<String> map = Maps.newMongoMap();			for (String key : dto.keySet()) {				map.put(key, (String)dto.get(key));			}			return map;		}						
		MongoMap<String> loadKeyValueForever(DBObject o) {			BasicDBObject dto = (BasicDBObject) o.get("keyValueForever");			if (dto == null) {				return null;			}			MongoMap<String> map = Maps.newMongoMap();			for (String key : dto.keySet()) {				map.put(key, (String)dto.get(key));			}			return map;		}						



















	static MongoMap<RoleDto> copy(MongoMap<RoleDto> map) {		MongoMapImpl<RoleDto> m = new MongoMapImpl<RoleDto>();				for (String key : map.keySet()) {			RoleDto v = map.get(key);			m.put(key, MongoGen.copy(v));		}		return m;	}			static List<RoleDto> copy(List<RoleDto> list) {		List<RoleDto> ls = Lists.newArrayList();		for (RoleDto t : list) {			ls.add(MongoGen.copy(t));		}		return ls;	}		@Override		public String toString() {			return toObject().toString();		}	}
		public static class SystemKeyValueDto implements MongoDto{		private String key = "";
		private String value = "";
		public SystemKeyValueDto() {		}				/**		 * Copy new one		 */		public SystemKeyValueDto(SystemKeyValueDto src) {			key = MongoGen.copy(src.key);			
			value = MongoGen.copy(src.value);			
		}		public String getKey() {			return this.key;		}
		public String getValue() {			return this.value;		}
		public void setKey(String key) {			this.key = key;		}
		public void setValue(String value) {			this.value = value;		}
		@Override		public DBObject toObject() {			BasicDBObject o = new BasicDBObject();		o.put("_id", key);
			o.put("key", MongoGen.toObject(key));			
			o.put("value", MongoGen.toObject(value));			
			return o;		}		@Override		public void fromDBObject(DBObject o) {			key = getString(o, "key");
			value = getString(o, "value");
		}



	static MongoMap<SystemKeyValueDto> copy(MongoMap<SystemKeyValueDto> map) {		MongoMapImpl<SystemKeyValueDto> m = new MongoMapImpl<SystemKeyValueDto>();				for (String key : map.keySet()) {			SystemKeyValueDto v = map.get(key);			m.put(key, MongoGen.copy(v));		}		return m;	}			static List<SystemKeyValueDto> copy(List<SystemKeyValueDto> list) {		List<SystemKeyValueDto> ls = Lists.newArrayList();		for (SystemKeyValueDto t : list) {			ls.add(MongoGen.copy(t));		}		return ls;	}		@Override		public String toString() {			return toObject().toString();		}	}
		public static class YiJieRechargeLogDto implements MongoDto{		private String ssid = "";
		private String app = "";
		private String cbi = "";
		private long ct = 0;
		private int fee = 0;
		private long pt = 0;
		private String sdk = "";
		private String sign = "";
		private int st = 0;
		private String tcd = "";
		private String uid = "";
		private String ver = "";
		private String roleId = "";
		private String nick = "";
		private long coin = 0;
		public YiJieRechargeLogDto() {		}				/**		 * Copy new one		 */		public YiJieRechargeLogDto(YiJieRechargeLogDto src) {			ssid = MongoGen.copy(src.ssid);			
			app = MongoGen.copy(src.app);			
			cbi = MongoGen.copy(src.cbi);			
			ct = MongoGen.copy(src.ct);			
			fee = MongoGen.copy(src.fee);			
			pt = MongoGen.copy(src.pt);			
			sdk = MongoGen.copy(src.sdk);			
			sign = MongoGen.copy(src.sign);			
			st = MongoGen.copy(src.st);			
			tcd = MongoGen.copy(src.tcd);			
			uid = MongoGen.copy(src.uid);			
			ver = MongoGen.copy(src.ver);			
			roleId = MongoGen.copy(src.roleId);			
			nick = MongoGen.copy(src.nick);			
			coin = MongoGen.copy(src.coin);			
		}		public String getSsid() {			return this.ssid;		}
		public String getApp() {			return this.app;		}
		public String getCbi() {			return this.cbi;		}
		public long getCt() {			return this.ct;		}
		public int getFee() {			return this.fee;		}
		public long getPt() {			return this.pt;		}
		public String getSdk() {			return this.sdk;		}
		public String getSign() {			return this.sign;		}
		public int getSt() {			return this.st;		}
		public String getTcd() {			return this.tcd;		}
		public String getUid() {			return this.uid;		}
		public String getVer() {			return this.ver;		}
		public String getRoleId() {			return this.roleId;		}
		public String getNick() {			return this.nick;		}
		public long getCoin() {			return this.coin;		}
		public void setSsid(String ssid) {			this.ssid = ssid;		}
		public void setApp(String app) {			this.app = app;		}
		public void setCbi(String cbi) {			this.cbi = cbi;		}
		public void setCt(long ct) {			this.ct = ct;		}
		public void setFee(int fee) {			this.fee = fee;		}
		public void setPt(long pt) {			this.pt = pt;		}
		public void setSdk(String sdk) {			this.sdk = sdk;		}
		public void setSign(String sign) {			this.sign = sign;		}
		public void setSt(int st) {			this.st = st;		}
		public void setTcd(String tcd) {			this.tcd = tcd;		}
		public void setUid(String uid) {			this.uid = uid;		}
		public void setVer(String ver) {			this.ver = ver;		}
		public void setRoleId(String roleId) {			this.roleId = roleId;		}
		public void setNick(String nick) {			this.nick = nick;		}
		public void setCoin(long coin) {			this.coin = coin;		}
		@Override		public DBObject toObject() {			BasicDBObject o = new BasicDBObject();		o.put("_id", ssid);
			o.put("ssid", MongoGen.toObject(ssid));			
			o.put("app", MongoGen.toObject(app));			
			o.put("cbi", MongoGen.toObject(cbi));			
			o.put("ct", MongoGen.toObject(ct));			
			o.put("fee", MongoGen.toObject(fee));			
			o.put("pt", MongoGen.toObject(pt));			
			o.put("sdk", MongoGen.toObject(sdk));			
			o.put("sign", MongoGen.toObject(sign));			
			o.put("st", MongoGen.toObject(st));			
			o.put("tcd", MongoGen.toObject(tcd));			
			o.put("uid", MongoGen.toObject(uid));			
			o.put("ver", MongoGen.toObject(ver));			
			o.put("roleId", MongoGen.toObject(roleId));			
			o.put("nick", MongoGen.toObject(nick));			
			o.put("coin", MongoGen.toObject(coin));			
			return o;		}		@Override		public void fromDBObject(DBObject o) {			ssid = getString(o, "ssid");
			app = getString(o, "app");
			cbi = getString(o, "cbi");
			ct = getLong(o, "ct");
			fee = getInteger(o, "fee");
			pt = getLong(o, "pt");
			sdk = getString(o, "sdk");
			sign = getString(o, "sign");
			st = getInteger(o, "st");
			tcd = getString(o, "tcd");
			uid = getString(o, "uid");
			ver = getString(o, "ver");
			roleId = getString(o, "roleId");
			nick = getString(o, "nick");
			coin = getLong(o, "coin");
		}





























	static MongoMap<YiJieRechargeLogDto> copy(MongoMap<YiJieRechargeLogDto> map) {		MongoMapImpl<YiJieRechargeLogDto> m = new MongoMapImpl<YiJieRechargeLogDto>();				for (String key : map.keySet()) {			YiJieRechargeLogDto v = map.get(key);			m.put(key, MongoGen.copy(v));		}		return m;	}			static List<YiJieRechargeLogDto> copy(List<YiJieRechargeLogDto> list) {		List<YiJieRechargeLogDto> ls = Lists.newArrayList();		for (YiJieRechargeLogDto t : list) {			ls.add(MongoGen.copy(t));		}		return ls;	}		@Override		public String toString() {			return toObject().toString();		}	}
}