package cn.javaplus.monichaogu.gen.dto;import java.net.UnknownHostException;import java.util.ArrayList;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import java.util.List;import java.util.Map;import java.util.Set;import cn.javaplus.exception.UnImplMethodException;import com.mongodb.BasicDBList;import com.mongodb.BasicDBObject;import com.mongodb.DB;import com.mongodb.DBCollection;import com.mongodb.DBCursor;import com.mongodb.DBObject;import com.mongodb.Mongo;import com.mongodb.MongoClient;public class MongoGen {	public static class Lists {		public static<T> List<T> newArrayList() {			return new ArrayList<T>();		}	}	public static class Maps {		public static<K, V> Map<K, V> newHashMap() {			return new HashMap<K, V>();		}		public static<V> MongoMap<V> newMongoMap() {			return new MongoMapImpl<V>();		}	}			public static interface MongoMap<V> {		V get(String k);		void put(String k, V v);		Set<String> keySet();		Collection<V> values();	}		public static class MongoMapImpl<V> implements MongoMap<V> {		Map<String, V> map = Maps.newHashMap();				@Override		public V get(String k) {			return map.get(k);		}		@Override		public void put(String k, V v) {			map.put(k, v);		}		@Override		public Set<String> keySet() {			return map.keySet();		}		@Override		public Collection<V> values() {			return map.values();		}			}																private static <V extends MongoDto> BasicDBObject toDBObject(MongoMap<V> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			V v = map.get(key);			o.put(key, toDBObject(v));		}		return o;	}	static DBObject toDBObject(MongoDto value) {		return value.toDBObject();	}	static <T extends MongoDto> BasicDBList toDBObject(Collection<T> list) {		BasicDBList prices = new BasicDBList();		for (T o : list) {			prices.add(toDBObject(o));		}		return prices;	}	static byte toDBObject(byte value) {		return value;	}	static char toDBObject(char value) {		return value;	}	static boolean toDBObject(boolean value) {		return value;	}	static short toDBObject(short value) {		return value;	}	static int toDBObject(int value) {		return value;	}	static long toDBObject(long value) {		return value;	}	static float toDBObject(float value) {		return value;	}	static double toDBObject(double value) {		return value;	}	static String toDBObject(String value) {		return value;	}	static <T extends MongoDto> void put(String key, BasicDBObject o, MongoMap<T> value) {		o.put(key, MongoGen.toDBObject(value));	}		static <T extends MongoDto> void put(String key, BasicDBObject o, Collection<T> value) {		o.put(key, MongoGen.toDBObject(value));	}	static void put(String key, BasicDBObject o, byte value) {		o.put(key, MongoGen.toDBObject(value));	}	static void put(String key, BasicDBObject o, char value) {		o.put(key, MongoGen.toDBObject(value));	}	static void put(String key, BasicDBObject o, boolean value) {		o.put(key, MongoGen.toDBObject(value));	}	static void put(String key, BasicDBObject o, short value) {		o.put(key, MongoGen.toDBObject(value));	}	static void put(String key, BasicDBObject o, int value) {		o.put(key, MongoGen.toDBObject(value));	}	static void put(String key, BasicDBObject o, long value) {		o.put(key, MongoGen.toDBObject(value));	}	static void put(String key, BasicDBObject o, float value) {		o.put(key, MongoGen.toDBObject(value));	}	static void put(String key, BasicDBObject o, double value) {		o.put(key, MongoGen.toDBObject(value));	}	static void put(String key, BasicDBObject o, String str) {		if (str == null) {			throw new RuntimeException("属性不能为null!");		}		o.put(key, MongoGen.toDBObject(str));	}																 static ChuQuanDateDto copy(ChuQuanDateDto value) {		return new ChuQuanDateDto(value);	 }			
							 static GuPiaoDto copy(GuPiaoDto value) {		return new GuPiaoDto(value);	 }			
							 static KeyValueDto copy(KeyValueDto value) {		return new KeyValueDto(value);	 }			
							 static PriceDto copy(PriceDto value) {		return new PriceDto(value);	 }			
							 static SelectDto copy(SelectDto value) {		return new SelectDto(value);	 }			
							 static StockDto copy(StockDto value) {		return new StockDto(value);	 }			
							 static UserDto copy(UserDto value) {		return new UserDto(value);	 }			
	static byte copy(byte value) {		return value;	}	static char copy(char value) {		return value;	}	static boolean copy(boolean value) {		return value;	}	static short copy(short value) {		return value;	}	static int copy(int value) {		return value;	}	static long copy(long value) {		return value;	}	static float copy(float value) {		return value;	}	static double copy(double value) {		return value;	}	static String copy(String value) {		return new String(value);	}														static BasicDBList getBasicDBList(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return new BasicDBList();		}		return (BasicDBList) obj;	}	static boolean getBoolean(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return false;		}		return (Boolean) obj;	}		static int getInteger(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return 0;		}		return (Integer) obj;	}		static byte[] getBytes(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return null;		}		return (byte[]) obj;	}		static float getFloat(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return 0;		}		return (Float) obj;	}		static String getString(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return "";		}		return (String) obj;	}	static long getLong(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return 0;		}		return (Long) obj;	}		static double getDouble(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return 0;		}		return (Double) obj;	}			public static interface CollectionFetcher {			DBCollection getCollection(String string);		void destroy();	}			public static interface MongoDto {			DBObject toDBObject();		void fromDBObject(DBObject o);	}			public static interface MongoDbProperties {			/**		 * 数据库名		 */		String getName();				/**		 * 主机地址		 */		String getHost();				/**		 * 端口		 */		int getPort();		}	public static final class Daos {			private static CollectionFetcher	fetcher;		private static MongoDbProperties	properties;		private static Map<String, DBCollection> cache = new HashMap<String, DBCollection>();				public static void destroy() {			getCollectionFetcher().destroy();		}				public static final void setProperties(MongoDbProperties properties) {			Daos.properties = properties;		}				public static final CollectionFetcher getCollectionFetcher() {			if(fetcher == null)				fetcher = new NormalFetcher();			return fetcher;		}				private static  class NormalFetcher implements CollectionFetcher {			@Override			public DBCollection getCollection(String name) {								Mongo m = getMongo();				DB db = m.getDB(properties.getName());				return db.getCollection(name);			}			@Override			public void destroy() {				if(mongo != null)					mongo.close();			}						private static Mongo mongo;			private static Mongo getMongo() {				if(mongo == null) {					try {						if(properties == null) {							throw new NullPointerException("请先setProperties");						}						mongo = new MongoClient(properties.getHost(), properties.getPort());					} catch (UnknownHostException e) {						throw cn.javaplus.util.Util.Exception.toRuntimeException(e);					}				}				return mongo;			}		}		private static DBCollection getCollection(String collectionName) {			DBCollection c;			try {				c = cache.get(collectionName);			} catch (NullPointerException e) {				c = null;			}			if (c != null) {				return c;			}			c = getCollectionFetcher().getCollection(collectionName);			cache.put(collectionName, c);			return c;		}		public static KeyValueDao getKeyValueDao() {			return new KeyValueDao(getCollection("KeyValue"));		}
		public static PriceDao getPriceDao() {			return new PriceDao(getCollection("Price"));		}
		public static StockDao getStockDao() {			return new StockDao(getCollection("Stock"));		}
		public static UserDao getUserDao() {			return new UserDao(getCollection("User"));		}
		}
		public static class KeyValueDao {			private DBCollection	collection;			public KeyValueDao(DBCollection collection) {			this.collection = collection;		}			public void save(KeyValueDto u) {			collection.save(u.toDBObject());		}			public void delete(KeyValueDto u) {			delete(u.getKey());		}			public void delete(String key) {			collection.remove(key(key));		}			public KeyValueDto get(String key) {			DBObject o = collection.findOne(key(key));			if(o == null) {				return null;			}			KeyValueDto x = new KeyValueDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String key) {			BasicDBObject o = new BasicDBObject();		o.put("_id", key);			return o;		}			public KeyValueDtoCursor find() {			return new KeyValueDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public KeyValueDtoCursor findByKey(String key) {			collection.ensureIndex("key");			BasicDBObject o = new BasicDBObject("key", key);			return new KeyValueDtoCursor(collection.find(o));		}
		public KeyValueDtoCursor findByValue(String value) {						BasicDBObject o = new BasicDBObject("value", value);			return new KeyValueDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public KeyValueDto createDTO() {			return new KeyValueDto();		}			public static class KeyValueDtoCursor implements Iterator<KeyValueDto>, Iterable<KeyValueDto>{				private DBCursor	cursor;				public KeyValueDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public KeyValueDto next() {				DBObject next = cursor.next();				KeyValueDto dto = new KeyValueDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<KeyValueDto> iterator() {				return this;			}		}	}
		public static class PriceDao {			private DBCollection	collection;			public PriceDao(DBCollection collection) {			this.collection = collection;		}			public void save(PriceDto u) {			collection.save(u.toDBObject());		}			public void delete(PriceDto u) {			delete();		}			public void delete() {			collection.remove(key());		}			public PriceDto get() {			DBObject o = collection.findOne(key());			if(o == null) {				return null;			}			PriceDto x = new PriceDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key() {			BasicDBObject o = new BasicDBObject();			return o;		}			public PriceDtoCursor find() {			return new PriceDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public PriceDtoCursor findByDate(String date) {						BasicDBObject o = new BasicDBObject("date", date);			return new PriceDtoCursor(collection.find(o));		}
		public PriceDtoCursor findByOpen(double open) {						BasicDBObject o = new BasicDBObject("open", open);			return new PriceDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public PriceDtoCursor findOpenBetween(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("open", new BasicDBObject("$gte", min).append("$lte", max));			return new PriceDtoCursor(collection.find(o));		}
		public PriceDtoCursor findByClose(double close) {						BasicDBObject o = new BasicDBObject("close", close);			return new PriceDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public PriceDtoCursor findCloseBetween(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("close", new BasicDBObject("$gte", min).append("$lte", max));			return new PriceDtoCursor(collection.find(o));		}
		public PriceDtoCursor findByHigh(double high) {						BasicDBObject o = new BasicDBObject("high", high);			return new PriceDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public PriceDtoCursor findHighBetween(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("high", new BasicDBObject("$gte", min).append("$lte", max));			return new PriceDtoCursor(collection.find(o));		}
		public PriceDtoCursor findByLow(double low) {						BasicDBObject o = new BasicDBObject("low", low);			return new PriceDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public PriceDtoCursor findLowBetween(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("low", new BasicDBObject("$gte", min).append("$lte", max));			return new PriceDtoCursor(collection.find(o));		}
		public PriceDtoCursor findByUp(double up) {						BasicDBObject o = new BasicDBObject("up", up);			return new PriceDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public PriceDtoCursor findUpBetween(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("up", new BasicDBObject("$gte", min).append("$lte", max));			return new PriceDtoCursor(collection.find(o));		}
		public PriceDtoCursor findByVolume(int volume) {						BasicDBObject o = new BasicDBObject("volume", volume);			return new PriceDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public PriceDtoCursor findVolumeBetween(int min, int max) {						BasicDBObject o = new BasicDBObject();			o.put("volume", new BasicDBObject("$gte", min).append("$lte", max));			return new PriceDtoCursor(collection.find(o));		}
		public PriceDtoCursor findByAvg5(double avg5) {						BasicDBObject o = new BasicDBObject("avg5", avg5);			return new PriceDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public PriceDtoCursor findAvg5Between(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("avg5", new BasicDBObject("$gte", min).append("$lte", max));			return new PriceDtoCursor(collection.find(o));		}
		public PriceDtoCursor findByAvg10(double avg10) {						BasicDBObject o = new BasicDBObject("avg10", avg10);			return new PriceDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public PriceDtoCursor findAvg10Between(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("avg10", new BasicDBObject("$gte", min).append("$lte", max));			return new PriceDtoCursor(collection.find(o));		}
		public PriceDtoCursor findByAvg15(double avg15) {						BasicDBObject o = new BasicDBObject("avg15", avg15);			return new PriceDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public PriceDtoCursor findAvg15Between(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("avg15", new BasicDBObject("$gte", min).append("$lte", max));			return new PriceDtoCursor(collection.find(o));		}
		public PriceDtoCursor findByAvg20(double avg20) {						BasicDBObject o = new BasicDBObject("avg20", avg20);			return new PriceDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public PriceDtoCursor findAvg20Between(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("avg20", new BasicDBObject("$gte", min).append("$lte", max));			return new PriceDtoCursor(collection.find(o));		}
		public PriceDtoCursor findByAvg30(double avg30) {						BasicDBObject o = new BasicDBObject("avg30", avg30);			return new PriceDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public PriceDtoCursor findAvg30Between(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("avg30", new BasicDBObject("$gte", min).append("$lte", max));			return new PriceDtoCursor(collection.find(o));		}
		public PriceDtoCursor findByAvg45(double avg45) {						BasicDBObject o = new BasicDBObject("avg45", avg45);			return new PriceDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public PriceDtoCursor findAvg45Between(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("avg45", new BasicDBObject("$gte", min).append("$lte", max));			return new PriceDtoCursor(collection.find(o));		}
		public PriceDtoCursor findByAvg60(double avg60) {						BasicDBObject o = new BasicDBObject("avg60", avg60);			return new PriceDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public PriceDtoCursor findAvg60Between(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("avg60", new BasicDBObject("$gte", min).append("$lte", max));			return new PriceDtoCursor(collection.find(o));		}
		public PriceDtoCursor findByAvg100(double avg100) {						BasicDBObject o = new BasicDBObject("avg100", avg100);			return new PriceDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public PriceDtoCursor findAvg100Between(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("avg100", new BasicDBObject("$gte", min).append("$lte", max));			return new PriceDtoCursor(collection.find(o));		}
		public PriceDtoCursor findByAvg120(double avg120) {						BasicDBObject o = new BasicDBObject("avg120", avg120);			return new PriceDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public PriceDtoCursor findAvg120Between(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("avg120", new BasicDBObject("$gte", min).append("$lte", max));			return new PriceDtoCursor(collection.find(o));		}
		public PriceDtoCursor findByMax30(double max30) {						BasicDBObject o = new BasicDBObject("max30", max30);			return new PriceDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public PriceDtoCursor findMax30Between(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("max30", new BasicDBObject("$gte", min).append("$lte", max));			return new PriceDtoCursor(collection.find(o));		}
		public PriceDtoCursor findByMax60(double max60) {						BasicDBObject o = new BasicDBObject("max60", max60);			return new PriceDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public PriceDtoCursor findMax60Between(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("max60", new BasicDBObject("$gte", min).append("$lte", max));			return new PriceDtoCursor(collection.find(o));		}
		public PriceDtoCursor findByMax90(double max90) {						BasicDBObject o = new BasicDBObject("max90", max90);			return new PriceDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public PriceDtoCursor findMax90Between(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("max90", new BasicDBObject("$gte", min).append("$lte", max));			return new PriceDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public PriceDto createDTO() {			return new PriceDto();		}			public static class PriceDtoCursor implements Iterator<PriceDto>, Iterable<PriceDto>{				private DBCursor	cursor;				public PriceDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public PriceDto next() {				DBObject next = cursor.next();				PriceDto dto = new PriceDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<PriceDto> iterator() {				return this;			}		}	}
		public static class StockDao {			private DBCollection	collection;			public StockDao(DBCollection collection) {			this.collection = collection;		}			public void save(StockDto u) {			collection.save(u.toDBObject());		}			public void delete(StockDto u) {			delete(u.getId());		}			public void delete(String id) {			collection.remove(key(id));		}			public StockDto get(String id) {			DBObject o = collection.findOne(key(id));			if(o == null) {				return null;			}			StockDto x = new StockDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String id) {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);			return o;		}			public StockDtoCursor find() {			return new StockDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public StockDtoCursor findById(String id) {			collection.ensureIndex("id");			BasicDBObject o = new BasicDBObject("id", id);			return new StockDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public StockDto createDTO() {			return new StockDto();		}			public static class StockDtoCursor implements Iterator<StockDto>, Iterable<StockDto>{				private DBCursor	cursor;				public StockDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public StockDto next() {				DBObject next = cursor.next();				StockDto dto = new StockDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<StockDto> iterator() {				return this;			}		}	}
		public static class UserDao {			private DBCollection	collection;			public UserDao(DBCollection collection) {			this.collection = collection;		}			public void save(UserDto u) {			collection.save(u.toDBObject());		}			public void delete(UserDto u) {			delete(u.getId());		}			public void delete(String id) {			collection.remove(key(id));		}			public UserDto get(String id) {			DBObject o = collection.findOne(key(id));			if(o == null) {				return null;			}			UserDto x = new UserDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String id) {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);			return o;		}			public UserDtoCursor find() {			return new UserDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public UserDtoCursor findById(String id) {			collection.ensureIndex("id");			BasicDBObject o = new BasicDBObject("id", id);			return new UserDtoCursor(collection.find(o));		}
		public UserDtoCursor findByRmb(double rmb) {						BasicDBObject o = new BasicDBObject("rmb", rmb);			return new UserDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public UserDtoCursor findRmbBetween(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("rmb", new BasicDBObject("$gte", min).append("$lte", max));			return new UserDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public UserDto createDTO() {			return new UserDto();		}			public static class UserDtoCursor implements Iterator<UserDto>, Iterable<UserDto>{				private DBCursor	cursor;				public UserDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public UserDto next() {				DBObject next = cursor.next();				UserDto dto = new UserDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<UserDto> iterator() {				return this;			}		}	}
		public static class ChuQuanDateDto implements MongoDto{		private String date = "";
		public ChuQuanDateDto() {		}				/**		 * Copy new one		 */		public ChuQuanDateDto(ChuQuanDateDto src) {			date = MongoGen.copy(src.date);			
		}		public String getDate() {			return this.date;		}
		public void setDate(String date) {			this.date = date;		}
		@Override		public DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();
			put("date", o, date);
			return o;		}		@Override		public void fromDBObject(DBObject o) {			date = getString(o, "date");
		}

	static MongoMap<ChuQuanDateDto> copy(MongoMap<ChuQuanDateDto> map) {		MongoMapImpl<ChuQuanDateDto> m = new MongoMapImpl<ChuQuanDateDto>();				for (String key : map.keySet()) {			ChuQuanDateDto v = map.get(key);			m.put(key, MongoGen.copy(v));		}		return m;	}			static List<ChuQuanDateDto> copy(List<ChuQuanDateDto> list) {		List<ChuQuanDateDto> ls = Lists.newArrayList();		for (ChuQuanDateDto t : list) {			ls.add(MongoGen.copy(t));		}		return ls;	}		@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class GuPiaoDto implements MongoDto{		private String id = "";
		private int count = 0;
		public GuPiaoDto() {		}				/**		 * Copy new one		 */		public GuPiaoDto(GuPiaoDto src) {			id = MongoGen.copy(src.id);			
			count = MongoGen.copy(src.count);			
		}		public String getId() {			return this.id;		}
		public int getCount() {			return this.count;		}
		public void setId(String id) {			this.id = id;		}
		public void setCount(int count) {			this.count = count;		}
		@Override		public DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();
			put("id", o, id);
			put("count", o, count);
			return o;		}		@Override		public void fromDBObject(DBObject o) {			id = getString(o, "id");
			count = getInteger(o, "count");
		}



	static MongoMap<GuPiaoDto> copy(MongoMap<GuPiaoDto> map) {		MongoMapImpl<GuPiaoDto> m = new MongoMapImpl<GuPiaoDto>();				for (String key : map.keySet()) {			GuPiaoDto v = map.get(key);			m.put(key, MongoGen.copy(v));		}		return m;	}			static List<GuPiaoDto> copy(List<GuPiaoDto> list) {		List<GuPiaoDto> ls = Lists.newArrayList();		for (GuPiaoDto t : list) {			ls.add(MongoGen.copy(t));		}		return ls;	}		@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class KeyValueDto implements MongoDto{		private String key = "";
		private String value = "";
		public KeyValueDto() {		}				/**		 * Copy new one		 */		public KeyValueDto(KeyValueDto src) {			key = MongoGen.copy(src.key);			
			value = MongoGen.copy(src.value);			
		}		public String getKey() {			return this.key;		}
		public String getValue() {			return this.value;		}
		public void setKey(String key) {			this.key = key;		}
		public void setValue(String value) {			this.value = value;		}
		@Override		public DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();		o.put("_id", key);
			put("key", o, key);
			put("value", o, value);
			return o;		}		@Override		public void fromDBObject(DBObject o) {			key = getString(o, "key");
			value = getString(o, "value");
		}



	static MongoMap<KeyValueDto> copy(MongoMap<KeyValueDto> map) {		MongoMapImpl<KeyValueDto> m = new MongoMapImpl<KeyValueDto>();				for (String key : map.keySet()) {			KeyValueDto v = map.get(key);			m.put(key, MongoGen.copy(v));		}		return m;	}			static List<KeyValueDto> copy(List<KeyValueDto> list) {		List<KeyValueDto> ls = Lists.newArrayList();		for (KeyValueDto t : list) {			ls.add(MongoGen.copy(t));		}		return ls;	}		@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class PriceDto implements MongoDto{		private String date = "";
		private double open = 0;
		private double close = 0;
		private double high = 0;
		private double low = 0;
		private double up = 0;
		private int volume = 0;
		private double avg5 = 0;
		private double avg10 = 0;
		private double avg15 = 0;
		private double avg20 = 0;
		private double avg30 = 0;
		private double avg45 = 0;
		private double avg60 = 0;
		private double avg100 = 0;
		private double avg120 = 0;
		private double max30 = 0;
		private double max60 = 0;
		private double max90 = 0;
		public PriceDto() {		}				/**		 * Copy new one		 */		public PriceDto(PriceDto src) {			date = MongoGen.copy(src.date);			
			open = MongoGen.copy(src.open);			
			close = MongoGen.copy(src.close);			
			high = MongoGen.copy(src.high);			
			low = MongoGen.copy(src.low);			
			up = MongoGen.copy(src.up);			
			volume = MongoGen.copy(src.volume);			
			avg5 = MongoGen.copy(src.avg5);			
			avg10 = MongoGen.copy(src.avg10);			
			avg15 = MongoGen.copy(src.avg15);			
			avg20 = MongoGen.copy(src.avg20);			
			avg30 = MongoGen.copy(src.avg30);			
			avg45 = MongoGen.copy(src.avg45);			
			avg60 = MongoGen.copy(src.avg60);			
			avg100 = MongoGen.copy(src.avg100);			
			avg120 = MongoGen.copy(src.avg120);			
			max30 = MongoGen.copy(src.max30);			
			max60 = MongoGen.copy(src.max60);			
			max90 = MongoGen.copy(src.max90);			
		}		public String getDate() {			return this.date;		}
		public double getOpen() {			return this.open;		}
		public double getClose() {			return this.close;		}
		public double getHigh() {			return this.high;		}
		public double getLow() {			return this.low;		}
		public double getUp() {			return this.up;		}
		public int getVolume() {			return this.volume;		}
		public double getAvg5() {			return this.avg5;		}
		public double getAvg10() {			return this.avg10;		}
		public double getAvg15() {			return this.avg15;		}
		public double getAvg20() {			return this.avg20;		}
		public double getAvg30() {			return this.avg30;		}
		public double getAvg45() {			return this.avg45;		}
		public double getAvg60() {			return this.avg60;		}
		public double getAvg100() {			return this.avg100;		}
		public double getAvg120() {			return this.avg120;		}
		public double getMax30() {			return this.max30;		}
		public double getMax60() {			return this.max60;		}
		public double getMax90() {			return this.max90;		}
		public void setDate(String date) {			this.date = date;		}
		public void setOpen(double open) {			this.open = open;		}
		public void setClose(double close) {			this.close = close;		}
		public void setHigh(double high) {			this.high = high;		}
		public void setLow(double low) {			this.low = low;		}
		public void setUp(double up) {			this.up = up;		}
		public void setVolume(int volume) {			this.volume = volume;		}
		public void setAvg5(double avg5) {			this.avg5 = avg5;		}
		public void setAvg10(double avg10) {			this.avg10 = avg10;		}
		public void setAvg15(double avg15) {			this.avg15 = avg15;		}
		public void setAvg20(double avg20) {			this.avg20 = avg20;		}
		public void setAvg30(double avg30) {			this.avg30 = avg30;		}
		public void setAvg45(double avg45) {			this.avg45 = avg45;		}
		public void setAvg60(double avg60) {			this.avg60 = avg60;		}
		public void setAvg100(double avg100) {			this.avg100 = avg100;		}
		public void setAvg120(double avg120) {			this.avg120 = avg120;		}
		public void setMax30(double max30) {			this.max30 = max30;		}
		public void setMax60(double max60) {			this.max60 = max60;		}
		public void setMax90(double max90) {			this.max90 = max90;		}
		@Override		public DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();
			put("date", o, date);
			put("open", o, open);
			put("close", o, close);
			put("high", o, high);
			put("low", o, low);
			put("up", o, up);
			put("volume", o, volume);
			put("avg5", o, avg5);
			put("avg10", o, avg10);
			put("avg15", o, avg15);
			put("avg20", o, avg20);
			put("avg30", o, avg30);
			put("avg45", o, avg45);
			put("avg60", o, avg60);
			put("avg100", o, avg100);
			put("avg120", o, avg120);
			put("max30", o, max30);
			put("max60", o, max60);
			put("max90", o, max90);
			return o;		}		@Override		public void fromDBObject(DBObject o) {			date = getString(o, "date");
			open = getDouble(o, "open");
			close = getDouble(o, "close");
			high = getDouble(o, "high");
			low = getDouble(o, "low");
			up = getDouble(o, "up");
			volume = getInteger(o, "volume");
			avg5 = getDouble(o, "avg5");
			avg10 = getDouble(o, "avg10");
			avg15 = getDouble(o, "avg15");
			avg20 = getDouble(o, "avg20");
			avg30 = getDouble(o, "avg30");
			avg45 = getDouble(o, "avg45");
			avg60 = getDouble(o, "avg60");
			avg100 = getDouble(o, "avg100");
			avg120 = getDouble(o, "avg120");
			max30 = getDouble(o, "max30");
			max60 = getDouble(o, "max60");
			max90 = getDouble(o, "max90");
		}





































	static MongoMap<PriceDto> copy(MongoMap<PriceDto> map) {		MongoMapImpl<PriceDto> m = new MongoMapImpl<PriceDto>();				for (String key : map.keySet()) {			PriceDto v = map.get(key);			m.put(key, MongoGen.copy(v));		}		return m;	}			static List<PriceDto> copy(List<PriceDto> list) {		List<PriceDto> ls = Lists.newArrayList();		for (PriceDto t : list) {			ls.add(MongoGen.copy(t));		}		return ls;	}		@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class SelectDto implements MongoDto{		private String id = "";
		public SelectDto() {		}				/**		 * Copy new one		 */		public SelectDto(SelectDto src) {			id = MongoGen.copy(src.id);			
		}		public String getId() {			return this.id;		}
		public void setId(String id) {			this.id = id;		}
		@Override		public DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();
			put("id", o, id);
			return o;		}		@Override		public void fromDBObject(DBObject o) {			id = getString(o, "id");
		}

	static MongoMap<SelectDto> copy(MongoMap<SelectDto> map) {		MongoMapImpl<SelectDto> m = new MongoMapImpl<SelectDto>();				for (String key : map.keySet()) {			SelectDto v = map.get(key);			m.put(key, MongoGen.copy(v));		}		return m;	}			static List<SelectDto> copy(List<SelectDto> list) {		List<SelectDto> ls = Lists.newArrayList();		for (SelectDto t : list) {			ls.add(MongoGen.copy(t));		}		return ls;	}		@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class StockDto implements MongoDto{		private String id = "";
		private MongoMap<PriceDto> pricesNormal = Maps.newMongoMap();
		private MongoMap<PriceDto> pricesFuQuan = Maps.newMongoMap();
		private List<ChuQuanDateDto> chuQuanDates = Lists.newArrayList();
		public StockDto() {		}				/**		 * Copy new one		 */		public StockDto(StockDto src) {			id = MongoGen.copy(src.id);			
			pricesNormal = PriceDto.copy(src.pricesNormal);			
			pricesFuQuan = PriceDto.copy(src.pricesFuQuan);			
			chuQuanDates = ChuQuanDateDto.copy(src.chuQuanDates);			
		}		public String getId() {			return this.id;		}
		public MongoMap<PriceDto> getPricesNormal() {			return this.pricesNormal;		}
		public MongoMap<PriceDto> getPricesFuQuan() {			return this.pricesFuQuan;		}
		public List<ChuQuanDateDto> getChuQuanDates() {			return this.chuQuanDates;		}
		public void setId(String id) {			this.id = id;		}
		public void setPricesNormal(MongoMap<PriceDto> pricesNormal) {			this.pricesNormal = pricesNormal;		}
		public void setPricesFuQuan(MongoMap<PriceDto> pricesFuQuan) {			this.pricesFuQuan = pricesFuQuan;		}
		public void setChuQuanDates(List<ChuQuanDateDto> chuQuanDates) {			this.chuQuanDates = chuQuanDates;		}
		@Override		public DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);
			put("id", o, id);
			put("pricesNormal", o, pricesNormal);
			put("pricesFuQuan", o, pricesFuQuan);
			put("chuQuanDates", o, chuQuanDates);
			return o;		}		@Override		public void fromDBObject(DBObject o) {			id = getString(o, "id");
			pricesNormal = loadPricesNormal(o);
			pricesFuQuan = loadPricesFuQuan(o);
			chuQuanDates = loadChuQuanDates(o);
		}
		MongoMap<PriceDto> loadPricesNormal(DBObject o) {			BasicDBObject dto = (BasicDBObject) o.get("pricesNormal");			if (dto == null) {				return null;			}			MongoMap<PriceDto> map = Maps.newMongoMap();			for (String key : dto.keySet()) {				PriceDto d = new PriceDto();				d.fromDBObject((BasicDBObject)dto.get(key));				map.put(key, d);			}			return map;		}						
		MongoMap<PriceDto> loadPricesFuQuan(DBObject o) {			BasicDBObject dto = (BasicDBObject) o.get("pricesFuQuan");			if (dto == null) {				return null;			}			MongoMap<PriceDto> map = Maps.newMongoMap();			for (String key : dto.keySet()) {				PriceDto d = new PriceDto();				d.fromDBObject((BasicDBObject)dto.get(key));				map.put(key, d);			}			return map;		}						




		List<ChuQuanDateDto> loadChuQuanDates(DBObject o) {			List<ChuQuanDateDto> ls = Lists.newArrayList();						BasicDBList chuQuanDates = getBasicDBList(o, "chuQuanDates");			for (Object xxx : chuQuanDates) {				ChuQuanDateDto tp = new ChuQuanDateDto();				tp.fromDBObject((DBObject) xxx);				ls.add(tp);			}			return ls;		}										
	static MongoMap<StockDto> copy(MongoMap<StockDto> map) {		MongoMapImpl<StockDto> m = new MongoMapImpl<StockDto>();				for (String key : map.keySet()) {			StockDto v = map.get(key);			m.put(key, MongoGen.copy(v));		}		return m;	}			static List<StockDto> copy(List<StockDto> list) {		List<StockDto> ls = Lists.newArrayList();		for (StockDto t : list) {			ls.add(MongoGen.copy(t));		}		return ls;	}		@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class UserDto implements MongoDto{		private String id = "";
		private double rmb = 0;
		private List<GuPiaoDto> gupiaos = Lists.newArrayList();
		private List<SelectDto> selects = Lists.newArrayList();
		public UserDto() {		}				/**		 * Copy new one		 */		public UserDto(UserDto src) {			id = MongoGen.copy(src.id);			
			rmb = MongoGen.copy(src.rmb);			
			gupiaos = GuPiaoDto.copy(src.gupiaos);			
			selects = SelectDto.copy(src.selects);			
		}		public String getId() {			return this.id;		}
		public double getRmb() {			return this.rmb;		}
		public List<GuPiaoDto> getGupiaos() {			return this.gupiaos;		}
		public List<SelectDto> getSelects() {			return this.selects;		}
		public void setId(String id) {			this.id = id;		}
		public void setRmb(double rmb) {			this.rmb = rmb;		}
		public void setGupiaos(List<GuPiaoDto> gupiaos) {			this.gupiaos = gupiaos;		}
		public void setSelects(List<SelectDto> selects) {			this.selects = selects;		}
		@Override		public DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);
			put("id", o, id);
			put("rmb", o, rmb);
			put("gupiaos", o, gupiaos);
			put("selects", o, selects);
			return o;		}		@Override		public void fromDBObject(DBObject o) {			id = getString(o, "id");
			rmb = getDouble(o, "rmb");
			gupiaos = loadGupiaos(o);
			selects = loadSelects(o);
		}





		List<GuPiaoDto> loadGupiaos(DBObject o) {			List<GuPiaoDto> ls = Lists.newArrayList();						BasicDBList gupiaos = getBasicDBList(o, "gupiaos");			for (Object xxx : gupiaos) {				GuPiaoDto tp = new GuPiaoDto();				tp.fromDBObject((DBObject) xxx);				ls.add(tp);			}			return ls;		}										
		List<SelectDto> loadSelects(DBObject o) {			List<SelectDto> ls = Lists.newArrayList();						BasicDBList selects = getBasicDBList(o, "selects");			for (Object xxx : selects) {				SelectDto tp = new SelectDto();				tp.fromDBObject((DBObject) xxx);				ls.add(tp);			}			return ls;		}										
	static MongoMap<UserDto> copy(MongoMap<UserDto> map) {		MongoMapImpl<UserDto> m = new MongoMapImpl<UserDto>();				for (String key : map.keySet()) {			UserDto v = map.get(key);			m.put(key, MongoGen.copy(v));		}		return m;	}			static List<UserDto> copy(List<UserDto> list) {		List<UserDto> ls = Lists.newArrayList();		for (UserDto t : list) {			ls.add(MongoGen.copy(t));		}		return ls;	}		@Override		public String toString() {			return toDBObject().toString();		}	}
}