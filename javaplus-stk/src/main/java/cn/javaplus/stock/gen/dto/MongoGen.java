package cn.javaplus.stock.gen.dto;import java.net.UnknownHostException;import java.util.ArrayList;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import java.util.List;import java.util.Map;import java.util.Set;import cn.javaplus.exception.UnImplMethodException;import com.mongodb.BasicDBList;import com.mongodb.BasicDBObject;import com.mongodb.DB;import com.mongodb.DBCollection;import com.mongodb.DBCursor;import com.mongodb.DBObject;import com.mongodb.Mongo;import com.mongodb.MongoClient;import java.util.regex.Pattern;public class MongoGen {	public static class Lists {		public static<T> List<T> newArrayList() {			return new ArrayList<T>();		}	}	public static class Maps {		public static<K, V> Map<K, V> newHashMap() {			return new HashMap<K, V>();		}		public static<V> MongoMap<V> newMongoMap() {			return new MongoMapImpl<V>();		}	}			public static interface MongoMap<V> {		V get(String k);		void put(String k, V v);		Set<String> keySet();		Collection<V> values();	}		public static class MongoMapImpl<V> implements MongoMap<V> {		Map<String, V> map = Maps.newHashMap();				@Override		public V get(String k) {			return map.get(k);		}		@Override		public void put(String k, V v) {			map.put(k, v);		}		@Override		public Set<String> keySet() {			return map.keySet();		}		@Override		public Collection<V> values() {			return map.values();		}			}																static BasicDBObject toObjectBytes(MongoMap<byte[]> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			byte[] v = map.get(key);			o.put(key, toObject(v));		}		return o;	}	static BasicDBObject toObjectByte(MongoMap<Byte> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			Byte v = map.get(key);			o.put(key, toObject(v));		}		return o;	}	static BasicDBObject toObjectCharacter(MongoMap<Character> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			Character v = map.get(key);			o.put(key, toObject(v));		}		return o;	}	static BasicDBObject toObjectBoolean(MongoMap<Boolean> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			Boolean v = map.get(key);			o.put(key, toObject(v));		}		return o;	}	static BasicDBObject toObjectShort(MongoMap<Short> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			Short v = map.get(key);			o.put(key, toObject(v));		}		return o;	}	static BasicDBObject toObjectInteger(MongoMap<Integer> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			Integer v = map.get(key);			o.put(key, toObject(v));		}		return o;	}	static BasicDBObject toObjectLong(MongoMap<Long> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			Long v = map.get(key);			o.put(key, toObject(v));		}		return o;	}	static BasicDBObject toObjectFloat(MongoMap<Float> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			Float v = map.get(key);			o.put(key, toObject(v));		}		return o;	}	static BasicDBObject toObjectDouble(MongoMap<Double> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			Double v = map.get(key);			o.put(key, toObject(v));		}		return o;	}	static BasicDBObject toObjectString(MongoMap<String> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			String v = map.get(key);			o.put(key, toObject(v));		}		return o;	}	static BasicDBList toObjectBytes(Collection<byte[]> list) {		BasicDBList prices = new BasicDBList();		for (byte[] o : list) {			prices.add(toObject(o));		}		return prices;	}	static BasicDBList toObjectByte(Collection<Byte> list) {		BasicDBList prices = new BasicDBList();		for (Byte o : list) {			prices.add(toObject(o));		}		return prices;	}	static BasicDBList toObjectCharacter(Collection<Character> list) {		BasicDBList prices = new BasicDBList();		for (Character o : list) {			prices.add(toObject(o));		}		return prices;	}	static BasicDBList toObjectBoolean(Collection<Boolean> list) {		BasicDBList prices = new BasicDBList();		for (Boolean o : list) {			prices.add(toObject(o));		}		return prices;	}	static BasicDBList toObjectShort(Collection<Short> list) {		BasicDBList prices = new BasicDBList();		for (Short o : list) {			prices.add(toObject(o));		}		return prices;	}	static BasicDBList toObjectInteger(Collection<Integer> list) {		BasicDBList prices = new BasicDBList();		for (Integer o : list) {			prices.add(toObject(o));		}		return prices;	}	static BasicDBList toObjectLong(Collection<Long> list) {		BasicDBList prices = new BasicDBList();		for (Long o : list) {			prices.add(toObject(o));		}		return prices;	}	static BasicDBList toObjectFloat(Collection<Float> list) {		BasicDBList prices = new BasicDBList();		for (Float o : list) {			prices.add(toObject(o));		}		return prices;	}	static BasicDBList toObjectDouble(Collection<Double> list) {		BasicDBList prices = new BasicDBList();		for (Double o : list) {			prices.add(toObject(o));		}		return prices;	}	static BasicDBList toObjectString(Collection<String> list) {		BasicDBList prices = new BasicDBList();		for (String o : list) {			prices.add(toObject(o));		}		return prices;	}	static byte[] toObject(byte[] value) {		return value;	}	static byte toObject(byte value) {		return value;	}	static char toObject(char value) {		return value;	}	static boolean toObject(boolean value) {		return value;	}	static short toObject(short value) {		return value;	}	static int toObject(int value) {		return value;	}	static long toObject(long value) {		return value;	}	static float toObject(float value) {		return value;	}	static double toObject(double value) {		return value;	}	static String toObject(String value) {		return value;	}	static DBObject toObject(MongoDto value) {		if(value == null)			return null;		return value.toObject();	}	static <V extends MongoDto> BasicDBObject toObject(MongoMap<V> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			V v = map.get(key);			o.put(key, toObject(v));		}		return o;	}		static <V extends MongoDto> BasicDBList toObject(List<V> ls) {		BasicDBList o = new BasicDBList();		for (V v : ls) {			o.add(toObject(v));		}		return o;	}	static MongoMap<byte[]> copyBytes(MongoMap<byte[]> value) {		MongoMap<byte[]> map = Maps.newMongoMap();		for (String key : value.keySet()) {			map.put(key, MongoGen.copy(value.get(key)));		}		return map;	}	static MongoMap<Byte> copyByte(MongoMap<Byte> value) {		MongoMap<Byte> map = Maps.newMongoMap();		for (String key : value.keySet()) {			map.put(key, value.get(key));		}		return map;	}	static MongoMap<Character> copyCharacter(MongoMap<Character> value) {		MongoMap<Character> map = Maps.newMongoMap();		for (String key : value.keySet()) {			map.put(key, value.get(key));		}		return map;	}	static MongoMap<Boolean> copyBoolean(MongoMap<Boolean> value) {		MongoMap<Boolean> map = Maps.newMongoMap();		for (String key : value.keySet()) {			map.put(key, value.get(key));		}		return map;	}	static MongoMap<Short> copyShort(MongoMap<Short> value) {		MongoMap<Short> map = Maps.newMongoMap();		for (String key : value.keySet()) {			map.put(key, value.get(key));		}		return map;	}	static MongoMap<Integer> copyInteger(MongoMap<Integer> value) {		MongoMap<Integer> map = Maps.newMongoMap();		for (String key : value.keySet()) {			map.put(key, value.get(key));		}		return map;	}	static MongoMap<Long> copyLong(MongoMap<Long> value) {		MongoMap<Long> map = Maps.newMongoMap();		for (String key : value.keySet()) {			map.put(key, value.get(key));		}		return map;	}	static MongoMap<Float> copyFloat(MongoMap<Float> value) {		MongoMap<Float> map = Maps.newMongoMap();		for (String key : value.keySet()) {			map.put(key, value.get(key));		}		return map;	}	static MongoMap<Double> copyDouble(MongoMap<Double> value) {		MongoMap<Double> map = Maps.newMongoMap();		for (String key : value.keySet()) {			map.put(key, value.get(key));		}		return map;	}	static MongoMap<String> copyString(MongoMap<String> value) {		MongoMap<String> map = Maps.newMongoMap();		for (String key : value.keySet()) {			map.put(key, new String(value.get(key)));		}		return map;	}	static List<byte[]> copyBytes(List<byte[]> value) {		List<byte[]> ls = Lists.newArrayList();		for (byte[] v : value) {			ls.add(copy(v));		}		return ls;	}	static List<Byte> copyByte(List<Byte> value) {		List<Byte> ls = Lists.newArrayList();		for (Byte v : value) {			ls.add(v);		}		return ls;	}	static List<Character> copyCharacter(List<Character> value) {		List<Character> ls = Lists.newArrayList();		for (Character v : value) {			ls.add(v);		}		return ls;	}	static List<Boolean> copyBoolean(List<Boolean> value) {		List<Boolean> ls = Lists.newArrayList();		for (Boolean v : value) {			ls.add(v);		}		return ls;	}	static List<Short> copyShort(List<Short> value) {		List<Short> ls = Lists.newArrayList();		for (Short v : value) {			ls.add(v);		}		return ls;	}	static List<Integer> copyInteger(List<Integer> value) {		List<Integer> ls = Lists.newArrayList();		for (Integer v : value) {			ls.add(v);		}		return ls;	}	static List<Long> copyLong(List<Long> value) {		List<Long> ls = Lists.newArrayList();		for (Long v : value) {			ls.add(v);		}		return ls;	}	static List<Float> copyFloat(List<Float> value) {		List<Float> ls = Lists.newArrayList();		for (Float v : value) {			ls.add(v);		}		return ls;	}	static List<Double> copyDouble(List<Double> value) {		List<Double> ls = Lists.newArrayList();		for (Double v : value) {			ls.add(v);		}		return ls;	}	static List<String> copyString(List<String> value) {		List<String> ls = Lists.newArrayList();		for (String v : value) {			ls.add(new String(v));		}		return ls;	}		static byte[] copy(byte[] value) {		return java.util.Arrays.copyOf(value, value.length);	}	static byte copy(byte value) {		return value;	}	static char copy(char value) {		return value;	}	static boolean copy(boolean value) {		return value;	}	static short copy(short value) {		return value;	}	static int copy(int value) {		return value;	}	static long copy(long value) {		return value;	}	static float copy(float value) {		return value;	}	static double copy(double value) {		return value;	}	static String copy(String value) {		return new String(value);	}												static BasicDBList getBasicDBList(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return new BasicDBList();		}		return (BasicDBList) obj;	}	static boolean getBoolean(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return false;		}		return (Boolean) obj;	}		static int getInteger(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return 0;		}		return (Integer) obj;	}		static byte[] getBytes(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return null;		}		return (byte[]) obj;	}		static float getFloat(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return 0;		}		return (Float) obj;	}		static String getString(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return "";		}		return (String) obj;	}	static long getLong(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return 0;		}		return (Long) obj;	}		static double getDouble(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return 0;		}		return (Double) obj;	}										 static void put(String key, BasicDBObject o, ChuQuanDateDto value) {		o.put(key, MongoGen.toObject(value));	 }	 
							 static void put(String key, BasicDBObject o, DayDto value) {		o.put(key, MongoGen.toObject(value));	 }	 
							 static void put(String key, BasicDBObject o, KeyValueDto value) {		o.put(key, MongoGen.toObject(value));	 }	 
							 static void put(String key, BasicDBObject o, MoniPlayerDto value) {		o.put(key, MongoGen.toObject(value));	 }	 
							 static void put(String key, BasicDBObject o, MyStockDto value) {		o.put(key, MongoGen.toObject(value));	 }	 
							 static void put(String key, BasicDBObject o, PriceDto value) {		o.put(key, MongoGen.toObject(value));	 }	 
							 static void put(String key, BasicDBObject o, StockDto value) {		o.put(key, MongoGen.toObject(value));	 }	 
							 static void put(String key, BasicDBObject o, StockIdDto value) {		o.put(key, MongoGen.toObject(value));	 }	 
							 static void put(String key, BasicDBObject o, StockNameDto value) {		o.put(key, MongoGen.toObject(value));	 }	 
							 static void put(String key, BasicDBObject o, StockPriceDto value) {		o.put(key, MongoGen.toObject(value));	 }	 
								 static ChuQuanDateDto copy(ChuQuanDateDto value) {		return new ChuQuanDateDto(value);	 }
							 static DayDto copy(DayDto value) {		return new DayDto(value);	 }
							 static KeyValueDto copy(KeyValueDto value) {		return new KeyValueDto(value);	 }
							 static MoniPlayerDto copy(MoniPlayerDto value) {		return new MoniPlayerDto(value);	 }
							 static MyStockDto copy(MyStockDto value) {		return new MyStockDto(value);	 }
							 static PriceDto copy(PriceDto value) {		return new PriceDto(value);	 }
							 static StockDto copy(StockDto value) {		return new StockDto(value);	 }
							 static StockIdDto copy(StockIdDto value) {		return new StockIdDto(value);	 }
							 static StockNameDto copy(StockNameDto value) {		return new StockNameDto(value);	 }
							 static StockPriceDto copy(StockPriceDto value) {		return new StockPriceDto(value);	 }
							static DBObject toObject(ChuQuanDateDto value) {		if(value == null) 			return null;		return value.toObject();	}	 	static DBObject toObjectChuQuanDateDto(ChuQuanDateDto value) {		return value.toObject();	}	 	static BasicDBList toObjectChuQuanDateDto(List<ChuQuanDateDto> value) {		BasicDBList o = new BasicDBList();		for (ChuQuanDateDto v : value) {			o.add(toObject(v));		}		return o;	}	 	 
							static DBObject toObject(DayDto value) {		if(value == null) 			return null;		return value.toObject();	}	 	static DBObject toObjectDayDto(DayDto value) {		return value.toObject();	}	 	static BasicDBList toObjectDayDto(List<DayDto> value) {		BasicDBList o = new BasicDBList();		for (DayDto v : value) {			o.add(toObject(v));		}		return o;	}	 	 
							static DBObject toObject(KeyValueDto value) {		if(value == null) 			return null;		return value.toObject();	}	 	static DBObject toObjectKeyValueDto(KeyValueDto value) {		return value.toObject();	}	 	static BasicDBList toObjectKeyValueDto(List<KeyValueDto> value) {		BasicDBList o = new BasicDBList();		for (KeyValueDto v : value) {			o.add(toObject(v));		}		return o;	}	 	 
							static DBObject toObject(MoniPlayerDto value) {		if(value == null) 			return null;		return value.toObject();	}	 	static DBObject toObjectMoniPlayerDto(MoniPlayerDto value) {		return value.toObject();	}	 	static BasicDBList toObjectMoniPlayerDto(List<MoniPlayerDto> value) {		BasicDBList o = new BasicDBList();		for (MoniPlayerDto v : value) {			o.add(toObject(v));		}		return o;	}	 	 
							static DBObject toObject(MyStockDto value) {		if(value == null) 			return null;		return value.toObject();	}	 	static DBObject toObjectMyStockDto(MyStockDto value) {		return value.toObject();	}	 	static BasicDBList toObjectMyStockDto(List<MyStockDto> value) {		BasicDBList o = new BasicDBList();		for (MyStockDto v : value) {			o.add(toObject(v));		}		return o;	}	 	 
							static DBObject toObject(PriceDto value) {		if(value == null) 			return null;		return value.toObject();	}	 	static DBObject toObjectPriceDto(PriceDto value) {		return value.toObject();	}	 	static BasicDBList toObjectPriceDto(List<PriceDto> value) {		BasicDBList o = new BasicDBList();		for (PriceDto v : value) {			o.add(toObject(v));		}		return o;	}	 	 
							static DBObject toObject(StockDto value) {		if(value == null) 			return null;		return value.toObject();	}	 	static DBObject toObjectStockDto(StockDto value) {		return value.toObject();	}	 	static BasicDBList toObjectStockDto(List<StockDto> value) {		BasicDBList o = new BasicDBList();		for (StockDto v : value) {			o.add(toObject(v));		}		return o;	}	 	 
							static DBObject toObject(StockIdDto value) {		if(value == null) 			return null;		return value.toObject();	}	 	static DBObject toObjectStockIdDto(StockIdDto value) {		return value.toObject();	}	 	static BasicDBList toObjectStockIdDto(List<StockIdDto> value) {		BasicDBList o = new BasicDBList();		for (StockIdDto v : value) {			o.add(toObject(v));		}		return o;	}	 	 
							static DBObject toObject(StockNameDto value) {		if(value == null) 			return null;		return value.toObject();	}	 	static DBObject toObjectStockNameDto(StockNameDto value) {		return value.toObject();	}	 	static BasicDBList toObjectStockNameDto(List<StockNameDto> value) {		BasicDBList o = new BasicDBList();		for (StockNameDto v : value) {			o.add(toObject(v));		}		return o;	}	 	 
							static DBObject toObject(StockPriceDto value) {		if(value == null) 			return null;		return value.toObject();	}	 	static DBObject toObjectStockPriceDto(StockPriceDto value) {		return value.toObject();	}	 	static BasicDBList toObjectStockPriceDto(List<StockPriceDto> value) {		BasicDBList o = new BasicDBList();		for (StockPriceDto v : value) {			o.add(toObject(v));		}		return o;	}	 	 
		public static interface CollectionFetcher {			DBCollection getCollection(String string);		void destroy();	}			public static interface MongoDto {			DBObject toObject();		void fromDBObject(DBObject o);	}			public static interface MongoDbProperties {			/**		 * 数据库名		 */		String getName();				/**		 * 主机地址		 */		String getHost();				/**		 * 端口		 */		int getPort();		}	public static final class Daos {			private static CollectionFetcher	fetcher;		private static MongoDbProperties	properties;		private static Map<String, DBCollection> cache = new HashMap<String, DBCollection>();				public static void destroy() {			getCollectionFetcher().destroy();		}				public static final void setProperties(MongoDbProperties properties) {			Daos.properties = properties;		}				public static final CollectionFetcher getCollectionFetcher() {			if(fetcher == null)				fetcher = new NormalFetcher();			return fetcher;		}				private static  class NormalFetcher implements CollectionFetcher {			@Override			public DBCollection getCollection(String name) {								Mongo m = getMongo();				DB db = m.getDB(properties.getName());				return db.getCollection(name);			}			@Override			public void destroy() {				if(mongo != null)					mongo.close();			}						private static Mongo mongo;			private static Mongo getMongo() {				if(mongo == null) {					try {						if(properties == null) {							throw new NullPointerException("请先setProperties");						}						mongo = new MongoClient(properties.getHost(), properties.getPort());					} catch (UnknownHostException e) {						throw cn.javaplus.util.Util.Exception.toRuntimeException(e);					}				}				return mongo;			}		}		private static DBCollection getCollection(String collectionName) {			DBCollection c;			try {				c = cache.get(collectionName);			} catch (NullPointerException e) {				c = null;			}			if (c != null) {				return c;			}			c = getCollectionFetcher().getCollection(collectionName);			cache.put(collectionName, c);			return c;		}		public static DayDao getDayDao() {			return new DayDao(getCollection("Day"));		}
		public static MoniPlayerDao getMoniPlayerDao() {			return new MoniPlayerDao(getCollection("MoniPlayer"));		}
		public static PriceDao getPriceDao() {			return new PriceDao(getCollection("Price"));		}
		public static StockDao getStockDao() {			return new StockDao(getCollection("Stock"));		}
		public static StockIdDao getStockIdDao() {			return new StockIdDao(getCollection("StockId"));		}
		public static StockNameDao getStockNameDao() {			return new StockNameDao(getCollection("StockName"));		}
		public static StockPriceDao getStockPriceDao() {			return new StockPriceDao(getCollection("StockPrice"));		}
		}
		public static class DayDao {			private DBCollection	collection;			public DayDao(DBCollection collection) {			this.collection = collection;		}			public void save(DayDto u) {			collection.save(u.toObject());		}			public void delete(DayDto u) {			delete();		}			public void delete() {			collection.remove(key());		}			public DayDto get() {			DBObject o = collection.findOne(key());			if(o == null) {				return null;			}			DayDto x = new DayDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key() {			BasicDBObject o = new BasicDBObject();			return o;		}			public DayDtoCursor find() {			return new DayDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public DayDtoCursor findById(String id) {						BasicDBObject o = new BasicDBObject("id", id);			return new DayDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public DayDtoCursor findByIdFuzzy(String id) {						id = id.replaceAll("\\*", ".*");			id = "^" + id + "$";			BasicDBObject o = new BasicDBObject("id", Pattern.compile(id, Pattern.CASE_INSENSITIVE));			return new DayDtoCursor(collection.find(o));		}
		public DayDtoCursor findByDate(String date) {						BasicDBObject o = new BasicDBObject("date", date);			return new DayDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public DayDtoCursor findByDateFuzzy(String date) {						date = date.replaceAll("\\*", ".*");			date = "^" + date + "$";			BasicDBObject o = new BasicDBObject("date", Pattern.compile(date, Pattern.CASE_INSENSITIVE));			return new DayDtoCursor(collection.find(o));		}
		public DayDtoCursor findByOpen(double open) {						BasicDBObject o = new BasicDBObject("open", open);			return new DayDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public DayDtoCursor findOpenBetween(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("open", new BasicDBObject("$gte", min).append("$lte", max));			return new DayDtoCursor(collection.find(o));		}
		public DayDtoCursor findByClose(double close) {						BasicDBObject o = new BasicDBObject("close", close);			return new DayDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public DayDtoCursor findCloseBetween(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("close", new BasicDBObject("$gte", min).append("$lte", max));			return new DayDtoCursor(collection.find(o));		}
		public DayDtoCursor findByHigh(double high) {						BasicDBObject o = new BasicDBObject("high", high);			return new DayDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public DayDtoCursor findHighBetween(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("high", new BasicDBObject("$gte", min).append("$lte", max));			return new DayDtoCursor(collection.find(o));		}
		public DayDtoCursor findByLow(double low) {						BasicDBObject o = new BasicDBObject("low", low);			return new DayDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public DayDtoCursor findLowBetween(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("low", new BasicDBObject("$gte", min).append("$lte", max));			return new DayDtoCursor(collection.find(o));		}
		public DayDtoCursor findByUp(double up) {						BasicDBObject o = new BasicDBObject("up", up);			return new DayDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public DayDtoCursor findUpBetween(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("up", new BasicDBObject("$gte", min).append("$lte", max));			return new DayDtoCursor(collection.find(o));		}
		public DayDtoCursor findByVolume(long volume) {						BasicDBObject o = new BasicDBObject("volume", volume);			return new DayDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public DayDtoCursor findVolumeBetween(long min, long max) {						BasicDBObject o = new BasicDBObject();			o.put("volume", new BasicDBObject("$gte", min).append("$lte", max));			return new DayDtoCursor(collection.find(o));		}
		public DayDtoCursor findByAvg5(double avg5) {						BasicDBObject o = new BasicDBObject("avg5", avg5);			return new DayDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public DayDtoCursor findAvg5Between(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("avg5", new BasicDBObject("$gte", min).append("$lte", max));			return new DayDtoCursor(collection.find(o));		}
		public DayDtoCursor findByAvg10(double avg10) {						BasicDBObject o = new BasicDBObject("avg10", avg10);			return new DayDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public DayDtoCursor findAvg10Between(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("avg10", new BasicDBObject("$gte", min).append("$lte", max));			return new DayDtoCursor(collection.find(o));		}
		public DayDtoCursor findByAvg15(double avg15) {						BasicDBObject o = new BasicDBObject("avg15", avg15);			return new DayDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public DayDtoCursor findAvg15Between(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("avg15", new BasicDBObject("$gte", min).append("$lte", max));			return new DayDtoCursor(collection.find(o));		}
		public DayDtoCursor findByAvg20(double avg20) {						BasicDBObject o = new BasicDBObject("avg20", avg20);			return new DayDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public DayDtoCursor findAvg20Between(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("avg20", new BasicDBObject("$gte", min).append("$lte", max));			return new DayDtoCursor(collection.find(o));		}
		public DayDtoCursor findByAvg30(double avg30) {						BasicDBObject o = new BasicDBObject("avg30", avg30);			return new DayDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public DayDtoCursor findAvg30Between(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("avg30", new BasicDBObject("$gte", min).append("$lte", max));			return new DayDtoCursor(collection.find(o));		}
		public DayDtoCursor findByAvg45(double avg45) {						BasicDBObject o = new BasicDBObject("avg45", avg45);			return new DayDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public DayDtoCursor findAvg45Between(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("avg45", new BasicDBObject("$gte", min).append("$lte", max));			return new DayDtoCursor(collection.find(o));		}
		public DayDtoCursor findByAvg60(double avg60) {						BasicDBObject o = new BasicDBObject("avg60", avg60);			return new DayDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public DayDtoCursor findAvg60Between(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("avg60", new BasicDBObject("$gte", min).append("$lte", max));			return new DayDtoCursor(collection.find(o));		}
		public DayDtoCursor findByAvg100(double avg100) {						BasicDBObject o = new BasicDBObject("avg100", avg100);			return new DayDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public DayDtoCursor findAvg100Between(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("avg100", new BasicDBObject("$gte", min).append("$lte", max));			return new DayDtoCursor(collection.find(o));		}
		public DayDtoCursor findByAvg120(double avg120) {						BasicDBObject o = new BasicDBObject("avg120", avg120);			return new DayDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public DayDtoCursor findAvg120Between(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("avg120", new BasicDBObject("$gte", min).append("$lte", max));			return new DayDtoCursor(collection.find(o));		}
		public DayDtoCursor findByMax2(double max2) {						BasicDBObject o = new BasicDBObject("max2", max2);			return new DayDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public DayDtoCursor findMax2Between(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("max2", new BasicDBObject("$gte", min).append("$lte", max));			return new DayDtoCursor(collection.find(o));		}
		public DayDtoCursor findByMax3(double max3) {						BasicDBObject o = new BasicDBObject("max3", max3);			return new DayDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public DayDtoCursor findMax3Between(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("max3", new BasicDBObject("$gte", min).append("$lte", max));			return new DayDtoCursor(collection.find(o));		}
		public DayDtoCursor findByMax4(double max4) {						BasicDBObject o = new BasicDBObject("max4", max4);			return new DayDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public DayDtoCursor findMax4Between(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("max4", new BasicDBObject("$gte", min).append("$lte", max));			return new DayDtoCursor(collection.find(o));		}
		public DayDtoCursor findByMax5(double max5) {						BasicDBObject o = new BasicDBObject("max5", max5);			return new DayDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public DayDtoCursor findMax5Between(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("max5", new BasicDBObject("$gte", min).append("$lte", max));			return new DayDtoCursor(collection.find(o));		}
		public DayDtoCursor findByMax6(double max6) {						BasicDBObject o = new BasicDBObject("max6", max6);			return new DayDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public DayDtoCursor findMax6Between(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("max6", new BasicDBObject("$gte", min).append("$lte", max));			return new DayDtoCursor(collection.find(o));		}
		public DayDtoCursor findByMax10(double max10) {						BasicDBObject o = new BasicDBObject("max10", max10);			return new DayDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public DayDtoCursor findMax10Between(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("max10", new BasicDBObject("$gte", min).append("$lte", max));			return new DayDtoCursor(collection.find(o));		}
		public DayDtoCursor findByMax15(double max15) {						BasicDBObject o = new BasicDBObject("max15", max15);			return new DayDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public DayDtoCursor findMax15Between(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("max15", new BasicDBObject("$gte", min).append("$lte", max));			return new DayDtoCursor(collection.find(o));		}
		public DayDtoCursor findByMax20(double max20) {						BasicDBObject o = new BasicDBObject("max20", max20);			return new DayDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public DayDtoCursor findMax20Between(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("max20", new BasicDBObject("$gte", min).append("$lte", max));			return new DayDtoCursor(collection.find(o));		}
		public DayDtoCursor findByMax30(double max30) {						BasicDBObject o = new BasicDBObject("max30", max30);			return new DayDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public DayDtoCursor findMax30Between(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("max30", new BasicDBObject("$gte", min).append("$lte", max));			return new DayDtoCursor(collection.find(o));		}
		public DayDtoCursor findByMax60(double max60) {						BasicDBObject o = new BasicDBObject("max60", max60);			return new DayDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public DayDtoCursor findMax60Between(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("max60", new BasicDBObject("$gte", min).append("$lte", max));			return new DayDtoCursor(collection.find(o));		}
		public DayDtoCursor findByMax90(double max90) {						BasicDBObject o = new BasicDBObject("max90", max90);			return new DayDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public DayDtoCursor findMax90Between(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("max90", new BasicDBObject("$gte", min).append("$lte", max));			return new DayDtoCursor(collection.find(o));		}
		public DayDtoCursor findByDea(double dea) {						BasicDBObject o = new BasicDBObject("dea", dea);			return new DayDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public DayDtoCursor findDeaBetween(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("dea", new BasicDBObject("$gte", min).append("$lte", max));			return new DayDtoCursor(collection.find(o));		}
		public DayDtoCursor findByMacd(double macd) {						BasicDBObject o = new BasicDBObject("macd", macd);			return new DayDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public DayDtoCursor findMacdBetween(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("macd", new BasicDBObject("$gte", min).append("$lte", max));			return new DayDtoCursor(collection.find(o));		}
		public DayDtoCursor findByEmaShort(double emaShort) {						BasicDBObject o = new BasicDBObject("emaShort", emaShort);			return new DayDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public DayDtoCursor findEmaShortBetween(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("emaShort", new BasicDBObject("$gte", min).append("$lte", max));			return new DayDtoCursor(collection.find(o));		}
		public DayDtoCursor findByEmaLong(double emaLong) {						BasicDBObject o = new BasicDBObject("emaLong", emaLong);			return new DayDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public DayDtoCursor findEmaLongBetween(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("emaLong", new BasicDBObject("$gte", min).append("$lte", max));			return new DayDtoCursor(collection.find(o));		}
		public DayDtoCursor findByDiff(double diff) {						BasicDBObject o = new BasicDBObject("diff", diff);			return new DayDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public DayDtoCursor findDiffBetween(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("diff", new BasicDBObject("$gte", min).append("$lte", max));			return new DayDtoCursor(collection.find(o));		}
		public DayDtoCursor findByPercentOfFuQuan(double percentOfFuQuan) {						BasicDBObject o = new BasicDBObject("percentOfFuQuan", percentOfFuQuan);			return new DayDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public DayDtoCursor findPercentOfFuQuanBetween(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("percentOfFuQuan", new BasicDBObject("$gte", min).append("$lte", max));			return new DayDtoCursor(collection.find(o));		}
		public DayDtoCursor findByHighLowZhenFu(double highLowZhenFu) {						BasicDBObject o = new BasicDBObject("highLowZhenFu", highLowZhenFu);			return new DayDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public DayDtoCursor findHighLowZhenFuBetween(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("highLowZhenFu", new BasicDBObject("$gte", min).append("$lte", max));			return new DayDtoCursor(collection.find(o));		}
		public DayDtoCursor findByOpenCloseZhenFu(double openCloseZhenFu) {						BasicDBObject o = new BasicDBObject("openCloseZhenFu", openCloseZhenFu);			return new DayDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public DayDtoCursor findOpenCloseZhenFuBetween(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("openCloseZhenFu", new BasicDBObject("$gte", min).append("$lte", max));			return new DayDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public DayDto createDTO() {			return new DayDto();		}			public static class DayDtoCursor implements Iterator<DayDto>, Iterable<DayDto>{				private DBCursor	cursor;			private int pageAll;				public DayDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public DayDto next() {				DBObject next = cursor.next();				DayDto dto = new DayDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void skip(int skip) {				cursor.skip(skip);			}						public void limit(int limit) {				cursor.limit(limit);			}						/**			 * 分页, page从1开始 countOfEveryPage必须大于0			 */			public void page(int page, int countOfEveryPage) {				if(countOfEveryPage <= 0) {					throw new RuntimeException("countOfEveryPage must > 0");				}				int count = getCount();				pageAll = count / countOfEveryPage;				if(count % countOfEveryPage != 0) {					pageAll ++;				}								if(page > pageAll)					page = pageAll;								if(page < 1)					page = 1;									skip(page * countOfEveryPage);				limit(countOfEveryPage);			}						public int getPageAll() {				return pageAll;			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<DayDto> iterator() {				return this;			}		}	}
		public static class MoniPlayerDao {			private DBCollection	collection;			public MoniPlayerDao(DBCollection collection) {			this.collection = collection;		}			public void save(MoniPlayerDto u) {			collection.save(u.toObject());		}			public void delete(MoniPlayerDto u) {			delete(u.getId());		}			public void delete(String id) {			collection.remove(key(id));		}			public MoniPlayerDto get(String id) {			DBObject o = collection.findOne(key(id));			if(o == null) {				return null;			}			MoniPlayerDto x = new MoniPlayerDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String id) {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);			return o;		}			public MoniPlayerDtoCursor find() {			return new MoniPlayerDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public MoniPlayerDtoCursor findById(String id) {			collection.ensureIndex("id");			BasicDBObject o = new BasicDBObject("id", id);			return new MoniPlayerDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public MoniPlayerDtoCursor findByIdFuzzy(String id) {			collection.ensureIndex("id");			id = id.replaceAll("\\*", ".*");			id = "^" + id + "$";			BasicDBObject o = new BasicDBObject("id", Pattern.compile(id, Pattern.CASE_INSENSITIVE));			return new MoniPlayerDtoCursor(collection.find(o));		}
		public MoniPlayerDtoCursor findByRmb(double rmb) {						BasicDBObject o = new BasicDBObject("rmb", rmb);			return new MoniPlayerDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public MoniPlayerDtoCursor findRmbBetween(double min, double max) {						BasicDBObject o = new BasicDBObject();			o.put("rmb", new BasicDBObject("$gte", min).append("$lte", max));			return new MoniPlayerDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public MoniPlayerDto createDTO() {			return new MoniPlayerDto();		}			public static class MoniPlayerDtoCursor implements Iterator<MoniPlayerDto>, Iterable<MoniPlayerDto>{				private DBCursor	cursor;			private int pageAll;				public MoniPlayerDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public MoniPlayerDto next() {				DBObject next = cursor.next();				MoniPlayerDto dto = new MoniPlayerDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void skip(int skip) {				cursor.skip(skip);			}						public void limit(int limit) {				cursor.limit(limit);			}						/**			 * 分页, page从1开始 countOfEveryPage必须大于0			 */			public void page(int page, int countOfEveryPage) {				if(countOfEveryPage <= 0) {					throw new RuntimeException("countOfEveryPage must > 0");				}				int count = getCount();				pageAll = count / countOfEveryPage;				if(count % countOfEveryPage != 0) {					pageAll ++;				}								if(page > pageAll)					page = pageAll;								if(page < 1)					page = 1;									skip(page * countOfEveryPage);				limit(countOfEveryPage);			}						public int getPageAll() {				return pageAll;			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<MoniPlayerDto> iterator() {				return this;			}		}	}
		public static class PriceDao {			private DBCollection	collection;			public PriceDao(DBCollection collection) {			this.collection = collection;		}			public void save(PriceDto u) {			collection.save(u.toObject());		}			public void delete(PriceDto u) {			delete();		}			public void delete() {			collection.remove(key());		}			public PriceDto get() {			DBObject o = collection.findOne(key());			if(o == null) {				return null;			}			PriceDto x = new PriceDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key() {			BasicDBObject o = new BasicDBObject();			return o;		}			public PriceDtoCursor find() {			return new PriceDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public PriceDtoCursor findByDate(String date) {						BasicDBObject o = new BasicDBObject("date", date);			return new PriceDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public PriceDtoCursor findByDateFuzzy(String date) {						date = date.replaceAll("\\*", ".*");			date = "^" + date + "$";			BasicDBObject o = new BasicDBObject("date", Pattern.compile(date, Pattern.CASE_INSENSITIVE));			return new PriceDtoCursor(collection.find(o));		}
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
			public void clear () {			collection.drop();		}			public PriceDto createDTO() {			return new PriceDto();		}			public static class PriceDtoCursor implements Iterator<PriceDto>, Iterable<PriceDto>{				private DBCursor	cursor;			private int pageAll;				public PriceDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public PriceDto next() {				DBObject next = cursor.next();				PriceDto dto = new PriceDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void skip(int skip) {				cursor.skip(skip);			}						public void limit(int limit) {				cursor.limit(limit);			}						/**			 * 分页, page从1开始 countOfEveryPage必须大于0			 */			public void page(int page, int countOfEveryPage) {				if(countOfEveryPage <= 0) {					throw new RuntimeException("countOfEveryPage must > 0");				}				int count = getCount();				pageAll = count / countOfEveryPage;				if(count % countOfEveryPage != 0) {					pageAll ++;				}								if(page > pageAll)					page = pageAll;								if(page < 1)					page = 1;									skip(page * countOfEveryPage);				limit(countOfEveryPage);			}						public int getPageAll() {				return pageAll;			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<PriceDto> iterator() {				return this;			}		}	}
		public static class StockDao {			private DBCollection	collection;			public StockDao(DBCollection collection) {			this.collection = collection;		}			public void save(StockDto u) {			collection.save(u.toObject());		}			public void delete(StockDto u) {			delete(u.getId());		}			public void delete(String id) {			collection.remove(key(id));		}			public StockDto get(String id) {			DBObject o = collection.findOne(key(id));			if(o == null) {				return null;			}			StockDto x = new StockDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String id) {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);			return o;		}			public StockDtoCursor find() {			return new StockDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public StockDtoCursor findById(String id) {			collection.ensureIndex("id");			BasicDBObject o = new BasicDBObject("id", id);			return new StockDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public StockDtoCursor findByIdFuzzy(String id) {			collection.ensureIndex("id");			id = id.replaceAll("\\*", ".*");			id = "^" + id + "$";			BasicDBObject o = new BasicDBObject("id", Pattern.compile(id, Pattern.CASE_INSENSITIVE));			return new StockDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public StockDto createDTO() {			return new StockDto();		}			public static class StockDtoCursor implements Iterator<StockDto>, Iterable<StockDto>{				private DBCursor	cursor;			private int pageAll;				public StockDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public StockDto next() {				DBObject next = cursor.next();				StockDto dto = new StockDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void skip(int skip) {				cursor.skip(skip);			}						public void limit(int limit) {				cursor.limit(limit);			}						/**			 * 分页, page从1开始 countOfEveryPage必须大于0			 */			public void page(int page, int countOfEveryPage) {				if(countOfEveryPage <= 0) {					throw new RuntimeException("countOfEveryPage must > 0");				}				int count = getCount();				pageAll = count / countOfEveryPage;				if(count % countOfEveryPage != 0) {					pageAll ++;				}								if(page > pageAll)					page = pageAll;								if(page < 1)					page = 1;									skip(page * countOfEveryPage);				limit(countOfEveryPage);			}						public int getPageAll() {				return pageAll;			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<StockDto> iterator() {				return this;			}		}	}
		public static class StockIdDao {			private DBCollection	collection;			public StockIdDao(DBCollection collection) {			this.collection = collection;		}			public void save(StockIdDto u) {			collection.save(u.toObject());		}			public void delete(StockIdDto u) {			delete(u.getId());		}			public void delete(String id) {			collection.remove(key(id));		}			public StockIdDto get(String id) {			DBObject o = collection.findOne(key(id));			if(o == null) {				return null;			}			StockIdDto x = new StockIdDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String id) {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);			return o;		}			public StockIdDtoCursor find() {			return new StockIdDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public StockIdDtoCursor findById(String id) {			collection.ensureIndex("id");			BasicDBObject o = new BasicDBObject("id", id);			return new StockIdDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public StockIdDtoCursor findByIdFuzzy(String id) {			collection.ensureIndex("id");			id = id.replaceAll("\\*", ".*");			id = "^" + id + "$";			BasicDBObject o = new BasicDBObject("id", Pattern.compile(id, Pattern.CASE_INSENSITIVE));			return new StockIdDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public StockIdDto createDTO() {			return new StockIdDto();		}			public static class StockIdDtoCursor implements Iterator<StockIdDto>, Iterable<StockIdDto>{				private DBCursor	cursor;			private int pageAll;				public StockIdDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public StockIdDto next() {				DBObject next = cursor.next();				StockIdDto dto = new StockIdDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void skip(int skip) {				cursor.skip(skip);			}						public void limit(int limit) {				cursor.limit(limit);			}						/**			 * 分页, page从1开始 countOfEveryPage必须大于0			 */			public void page(int page, int countOfEveryPage) {				if(countOfEveryPage <= 0) {					throw new RuntimeException("countOfEveryPage must > 0");				}				int count = getCount();				pageAll = count / countOfEveryPage;				if(count % countOfEveryPage != 0) {					pageAll ++;				}								if(page > pageAll)					page = pageAll;								if(page < 1)					page = 1;									skip(page * countOfEveryPage);				limit(countOfEveryPage);			}						public int getPageAll() {				return pageAll;			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<StockIdDto> iterator() {				return this;			}		}	}
		public static class StockNameDao {			private DBCollection	collection;			public StockNameDao(DBCollection collection) {			this.collection = collection;		}			public void save(StockNameDto u) {			collection.save(u.toObject());		}			public void delete(StockNameDto u) {			delete(u.getId());		}			public void delete(String id) {			collection.remove(key(id));		}			public StockNameDto get(String id) {			DBObject o = collection.findOne(key(id));			if(o == null) {				return null;			}			StockNameDto x = new StockNameDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String id) {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);			return o;		}			public StockNameDtoCursor find() {			return new StockNameDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public StockNameDtoCursor findById(String id) {			collection.ensureIndex("id");			BasicDBObject o = new BasicDBObject("id", id);			return new StockNameDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public StockNameDtoCursor findByIdFuzzy(String id) {			collection.ensureIndex("id");			id = id.replaceAll("\\*", ".*");			id = "^" + id + "$";			BasicDBObject o = new BasicDBObject("id", Pattern.compile(id, Pattern.CASE_INSENSITIVE));			return new StockNameDtoCursor(collection.find(o));		}
		public StockNameDtoCursor findByName(String name) {						BasicDBObject o = new BasicDBObject("name", name);			return new StockNameDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public StockNameDtoCursor findByNameFuzzy(String name) {						name = name.replaceAll("\\*", ".*");			name = "^" + name + "$";			BasicDBObject o = new BasicDBObject("name", Pattern.compile(name, Pattern.CASE_INSENSITIVE));			return new StockNameDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public StockNameDto createDTO() {			return new StockNameDto();		}			public static class StockNameDtoCursor implements Iterator<StockNameDto>, Iterable<StockNameDto>{				private DBCursor	cursor;			private int pageAll;				public StockNameDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public StockNameDto next() {				DBObject next = cursor.next();				StockNameDto dto = new StockNameDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void skip(int skip) {				cursor.skip(skip);			}						public void limit(int limit) {				cursor.limit(limit);			}						/**			 * 分页, page从1开始 countOfEveryPage必须大于0			 */			public void page(int page, int countOfEveryPage) {				if(countOfEveryPage <= 0) {					throw new RuntimeException("countOfEveryPage must > 0");				}				int count = getCount();				pageAll = count / countOfEveryPage;				if(count % countOfEveryPage != 0) {					pageAll ++;				}								if(page > pageAll)					page = pageAll;								if(page < 1)					page = 1;									skip(page * countOfEveryPage);				limit(countOfEveryPage);			}						public int getPageAll() {				return pageAll;			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<StockNameDto> iterator() {				return this;			}		}	}
		public static class StockPriceDao {			private DBCollection	collection;			public StockPriceDao(DBCollection collection) {			this.collection = collection;		}			public void save(StockPriceDto u) {			collection.save(u.toObject());		}			public void delete(StockPriceDto u) {			delete(u.getId(), u.getTime());		}			public void delete(String id, String time) {			collection.remove(key(id, time));		}			public StockPriceDto get(String id, String time) {			DBObject o = collection.findOne(key(id, time));			if(o == null) {				return null;			}			StockPriceDto x = new StockPriceDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String id, String time) {			BasicDBObject o = new BasicDBObject();		o.put("_id", id + ":" + time);			return o;		}			public StockPriceDtoCursor find() {			return new StockPriceDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public StockPriceDtoCursor findById(String id) {			collection.ensureIndex("id");			BasicDBObject o = new BasicDBObject("id", id);			return new StockPriceDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public StockPriceDtoCursor findByIdFuzzy(String id) {			collection.ensureIndex("id");			id = id.replaceAll("\\*", ".*");			id = "^" + id + "$";			BasicDBObject o = new BasicDBObject("id", Pattern.compile(id, Pattern.CASE_INSENSITIVE));			return new StockPriceDtoCursor(collection.find(o));		}
		public StockPriceDtoCursor findByTime(String time) {			collection.ensureIndex("time");			BasicDBObject o = new BasicDBObject("time", time);			return new StockPriceDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public StockPriceDtoCursor findByTimeFuzzy(String time) {			collection.ensureIndex("time");			time = time.replaceAll("\\*", ".*");			time = "^" + time + "$";			BasicDBObject o = new BasicDBObject("time", Pattern.compile(time, Pattern.CASE_INSENSITIVE));			return new StockPriceDtoCursor(collection.find(o));		}
		public StockPriceDtoCursor findByPrice(String price) {						BasicDBObject o = new BasicDBObject("price", price);			return new StockPriceDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public StockPriceDtoCursor findByPriceFuzzy(String price) {						price = price.replaceAll("\\*", ".*");			price = "^" + price + "$";			BasicDBObject o = new BasicDBObject("price", Pattern.compile(price, Pattern.CASE_INSENSITIVE));			return new StockPriceDtoCursor(collection.find(o));		}
		public StockPriceDtoCursor findByCloseYestoday(String closeYestoday) {						BasicDBObject o = new BasicDBObject("closeYestoday", closeYestoday);			return new StockPriceDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public StockPriceDtoCursor findByCloseYestodayFuzzy(String closeYestoday) {						closeYestoday = closeYestoday.replaceAll("\\*", ".*");			closeYestoday = "^" + closeYestoday + "$";			BasicDBObject o = new BasicDBObject("closeYestoday", Pattern.compile(closeYestoday, Pattern.CASE_INSENSITIVE));			return new StockPriceDtoCursor(collection.find(o));		}
		public StockPriceDtoCursor findByHighToday(String highToday) {						BasicDBObject o = new BasicDBObject("highToday", highToday);			return new StockPriceDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public StockPriceDtoCursor findByHighTodayFuzzy(String highToday) {						highToday = highToday.replaceAll("\\*", ".*");			highToday = "^" + highToday + "$";			BasicDBObject o = new BasicDBObject("highToday", Pattern.compile(highToday, Pattern.CASE_INSENSITIVE));			return new StockPriceDtoCursor(collection.find(o));		}
		public StockPriceDtoCursor findByLowToday(String lowToday) {						BasicDBObject o = new BasicDBObject("lowToday", lowToday);			return new StockPriceDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public StockPriceDtoCursor findByLowTodayFuzzy(String lowToday) {						lowToday = lowToday.replaceAll("\\*", ".*");			lowToday = "^" + lowToday + "$";			BasicDBObject o = new BasicDBObject("lowToday", Pattern.compile(lowToday, Pattern.CASE_INSENSITIVE));			return new StockPriceDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public StockPriceDto createDTO() {			return new StockPriceDto();		}			public static class StockPriceDtoCursor implements Iterator<StockPriceDto>, Iterable<StockPriceDto>{				private DBCursor	cursor;			private int pageAll;				public StockPriceDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public StockPriceDto next() {				DBObject next = cursor.next();				StockPriceDto dto = new StockPriceDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void skip(int skip) {				cursor.skip(skip);			}						public void limit(int limit) {				cursor.limit(limit);			}						/**			 * 分页, page从1开始 countOfEveryPage必须大于0			 */			public void page(int page, int countOfEveryPage) {				if(countOfEveryPage <= 0) {					throw new RuntimeException("countOfEveryPage must > 0");				}				int count = getCount();				pageAll = count / countOfEveryPage;				if(count % countOfEveryPage != 0) {					pageAll ++;				}								if(page > pageAll)					page = pageAll;								if(page < 1)					page = 1;									skip(page * countOfEveryPage);				limit(countOfEveryPage);			}						public int getPageAll() {				return pageAll;			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<StockPriceDto> iterator() {				return this;			}		}	}
		public static class ChuQuanDateDto implements MongoDto{		private String date = "";
		public ChuQuanDateDto() {		}				/**		 * Copy new one		 */		public ChuQuanDateDto(ChuQuanDateDto src) {			date = MongoGen.copy(src.date);			
		}		public String getDate() {			return this.date;		}
		public void setDate(String date) {			this.date = date;		}
		@Override		public DBObject toObject() {			BasicDBObject o = new BasicDBObject();
			o.put("date", MongoGen.toObject(date));			
			return o;		}		@Override		public void fromDBObject(DBObject o) {			date = getString(o, "date");
		}

	static MongoMap<ChuQuanDateDto> copy(MongoMap<ChuQuanDateDto> map) {		MongoMapImpl<ChuQuanDateDto> m = new MongoMapImpl<ChuQuanDateDto>();				for (String key : map.keySet()) {			ChuQuanDateDto v = map.get(key);			m.put(key, MongoGen.copy(v));		}		return m;	}			static List<ChuQuanDateDto> copy(List<ChuQuanDateDto> list) {		List<ChuQuanDateDto> ls = Lists.newArrayList();		for (ChuQuanDateDto t : list) {			ls.add(MongoGen.copy(t));		}		return ls;	}		@Override		public String toString() {			return toObject().toString();		}	}
		public static class DayDto implements MongoDto{		private String id = "";
		private String date = "";
		private double open = 0;
		private double close = 0;
		private double high = 0;
		private double low = 0;
		private double up = 0;
		private long volume = 0;
		private double avg5 = 0;
		private double avg10 = 0;
		private double avg15 = 0;
		private double avg20 = 0;
		private double avg30 = 0;
		private double avg45 = 0;
		private double avg60 = 0;
		private double avg100 = 0;
		private double avg120 = 0;
		private double max2 = 0;
		private double max3 = 0;
		private double max4 = 0;
		private double max5 = 0;
		private double max6 = 0;
		private double max10 = 0;
		private double max15 = 0;
		private double max20 = 0;
		private double max30 = 0;
		private double max60 = 0;
		private double max90 = 0;
		private double dea = 0;
		private double macd = 0;
		private double emaShort = 0;
		private double emaLong = 0;
		private double diff = 0;
		private double percentOfFuQuan = 0;
		private double highLowZhenFu = 0;
		private double openCloseZhenFu = 0;
		public DayDto() {		}				/**		 * Copy new one		 */		public DayDto(DayDto src) {			id = MongoGen.copy(src.id);			
			date = MongoGen.copy(src.date);			
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
			max2 = MongoGen.copy(src.max2);			
			max3 = MongoGen.copy(src.max3);			
			max4 = MongoGen.copy(src.max4);			
			max5 = MongoGen.copy(src.max5);			
			max6 = MongoGen.copy(src.max6);			
			max10 = MongoGen.copy(src.max10);			
			max15 = MongoGen.copy(src.max15);			
			max20 = MongoGen.copy(src.max20);			
			max30 = MongoGen.copy(src.max30);			
			max60 = MongoGen.copy(src.max60);			
			max90 = MongoGen.copy(src.max90);			
			dea = MongoGen.copy(src.dea);			
			macd = MongoGen.copy(src.macd);			
			emaShort = MongoGen.copy(src.emaShort);			
			emaLong = MongoGen.copy(src.emaLong);			
			diff = MongoGen.copy(src.diff);			
			percentOfFuQuan = MongoGen.copy(src.percentOfFuQuan);			
			highLowZhenFu = MongoGen.copy(src.highLowZhenFu);			
			openCloseZhenFu = MongoGen.copy(src.openCloseZhenFu);			
		}		public String getId() {			return this.id;		}
		public String getDate() {			return this.date;		}
		public double getOpen() {			return this.open;		}
		public double getClose() {			return this.close;		}
		public double getHigh() {			return this.high;		}
		public double getLow() {			return this.low;		}
		public double getUp() {			return this.up;		}
		public long getVolume() {			return this.volume;		}
		public double getAvg5() {			return this.avg5;		}
		public double getAvg10() {			return this.avg10;		}
		public double getAvg15() {			return this.avg15;		}
		public double getAvg20() {			return this.avg20;		}
		public double getAvg30() {			return this.avg30;		}
		public double getAvg45() {			return this.avg45;		}
		public double getAvg60() {			return this.avg60;		}
		public double getAvg100() {			return this.avg100;		}
		public double getAvg120() {			return this.avg120;		}
		public double getMax2() {			return this.max2;		}
		public double getMax3() {			return this.max3;		}
		public double getMax4() {			return this.max4;		}
		public double getMax5() {			return this.max5;		}
		public double getMax6() {			return this.max6;		}
		public double getMax10() {			return this.max10;		}
		public double getMax15() {			return this.max15;		}
		public double getMax20() {			return this.max20;		}
		public double getMax30() {			return this.max30;		}
		public double getMax60() {			return this.max60;		}
		public double getMax90() {			return this.max90;		}
		public double getDea() {			return this.dea;		}
		public double getMacd() {			return this.macd;		}
		public double getEmaShort() {			return this.emaShort;		}
		public double getEmaLong() {			return this.emaLong;		}
		public double getDiff() {			return this.diff;		}
		public double getPercentOfFuQuan() {			return this.percentOfFuQuan;		}
		public double getHighLowZhenFu() {			return this.highLowZhenFu;		}
		public double getOpenCloseZhenFu() {			return this.openCloseZhenFu;		}
		public void setId(String id) {			this.id = id;		}
		public void setDate(String date) {			this.date = date;		}
		public void setOpen(double open) {			this.open = open;		}
		public void setClose(double close) {			this.close = close;		}
		public void setHigh(double high) {			this.high = high;		}
		public void setLow(double low) {			this.low = low;		}
		public void setUp(double up) {			this.up = up;		}
		public void setVolume(long volume) {			this.volume = volume;		}
		public void setAvg5(double avg5) {			this.avg5 = avg5;		}
		public void setAvg10(double avg10) {			this.avg10 = avg10;		}
		public void setAvg15(double avg15) {			this.avg15 = avg15;		}
		public void setAvg20(double avg20) {			this.avg20 = avg20;		}
		public void setAvg30(double avg30) {			this.avg30 = avg30;		}
		public void setAvg45(double avg45) {			this.avg45 = avg45;		}
		public void setAvg60(double avg60) {			this.avg60 = avg60;		}
		public void setAvg100(double avg100) {			this.avg100 = avg100;		}
		public void setAvg120(double avg120) {			this.avg120 = avg120;		}
		public void setMax2(double max2) {			this.max2 = max2;		}
		public void setMax3(double max3) {			this.max3 = max3;		}
		public void setMax4(double max4) {			this.max4 = max4;		}
		public void setMax5(double max5) {			this.max5 = max5;		}
		public void setMax6(double max6) {			this.max6 = max6;		}
		public void setMax10(double max10) {			this.max10 = max10;		}
		public void setMax15(double max15) {			this.max15 = max15;		}
		public void setMax20(double max20) {			this.max20 = max20;		}
		public void setMax30(double max30) {			this.max30 = max30;		}
		public void setMax60(double max60) {			this.max60 = max60;		}
		public void setMax90(double max90) {			this.max90 = max90;		}
		public void setDea(double dea) {			this.dea = dea;		}
		public void setMacd(double macd) {			this.macd = macd;		}
		public void setEmaShort(double emaShort) {			this.emaShort = emaShort;		}
		public void setEmaLong(double emaLong) {			this.emaLong = emaLong;		}
		public void setDiff(double diff) {			this.diff = diff;		}
		public void setPercentOfFuQuan(double percentOfFuQuan) {			this.percentOfFuQuan = percentOfFuQuan;		}
		public void setHighLowZhenFu(double highLowZhenFu) {			this.highLowZhenFu = highLowZhenFu;		}
		public void setOpenCloseZhenFu(double openCloseZhenFu) {			this.openCloseZhenFu = openCloseZhenFu;		}
		@Override		public DBObject toObject() {			BasicDBObject o = new BasicDBObject();
			o.put("id", MongoGen.toObject(id));			
			o.put("date", MongoGen.toObject(date));			
			o.put("open", MongoGen.toObject(open));			
			o.put("close", MongoGen.toObject(close));			
			o.put("high", MongoGen.toObject(high));			
			o.put("low", MongoGen.toObject(low));			
			o.put("up", MongoGen.toObject(up));			
			o.put("volume", MongoGen.toObject(volume));			
			o.put("avg5", MongoGen.toObject(avg5));			
			o.put("avg10", MongoGen.toObject(avg10));			
			o.put("avg15", MongoGen.toObject(avg15));			
			o.put("avg20", MongoGen.toObject(avg20));			
			o.put("avg30", MongoGen.toObject(avg30));			
			o.put("avg45", MongoGen.toObject(avg45));			
			o.put("avg60", MongoGen.toObject(avg60));			
			o.put("avg100", MongoGen.toObject(avg100));			
			o.put("avg120", MongoGen.toObject(avg120));			
			o.put("max2", MongoGen.toObject(max2));			
			o.put("max3", MongoGen.toObject(max3));			
			o.put("max4", MongoGen.toObject(max4));			
			o.put("max5", MongoGen.toObject(max5));			
			o.put("max6", MongoGen.toObject(max6));			
			o.put("max10", MongoGen.toObject(max10));			
			o.put("max15", MongoGen.toObject(max15));			
			o.put("max20", MongoGen.toObject(max20));			
			o.put("max30", MongoGen.toObject(max30));			
			o.put("max60", MongoGen.toObject(max60));			
			o.put("max90", MongoGen.toObject(max90));			
			o.put("dea", MongoGen.toObject(dea));			
			o.put("macd", MongoGen.toObject(macd));			
			o.put("emaShort", MongoGen.toObject(emaShort));			
			o.put("emaLong", MongoGen.toObject(emaLong));			
			o.put("diff", MongoGen.toObject(diff));			
			o.put("percentOfFuQuan", MongoGen.toObject(percentOfFuQuan));			
			o.put("highLowZhenFu", MongoGen.toObject(highLowZhenFu));			
			o.put("openCloseZhenFu", MongoGen.toObject(openCloseZhenFu));			
			return o;		}		@Override		public void fromDBObject(DBObject o) {			id = getString(o, "id");
			date = getString(o, "date");
			open = getDouble(o, "open");
			close = getDouble(o, "close");
			high = getDouble(o, "high");
			low = getDouble(o, "low");
			up = getDouble(o, "up");
			volume = getLong(o, "volume");
			avg5 = getDouble(o, "avg5");
			avg10 = getDouble(o, "avg10");
			avg15 = getDouble(o, "avg15");
			avg20 = getDouble(o, "avg20");
			avg30 = getDouble(o, "avg30");
			avg45 = getDouble(o, "avg45");
			avg60 = getDouble(o, "avg60");
			avg100 = getDouble(o, "avg100");
			avg120 = getDouble(o, "avg120");
			max2 = getDouble(o, "max2");
			max3 = getDouble(o, "max3");
			max4 = getDouble(o, "max4");
			max5 = getDouble(o, "max5");
			max6 = getDouble(o, "max6");
			max10 = getDouble(o, "max10");
			max15 = getDouble(o, "max15");
			max20 = getDouble(o, "max20");
			max30 = getDouble(o, "max30");
			max60 = getDouble(o, "max60");
			max90 = getDouble(o, "max90");
			dea = getDouble(o, "dea");
			macd = getDouble(o, "macd");
			emaShort = getDouble(o, "emaShort");
			emaLong = getDouble(o, "emaLong");
			diff = getDouble(o, "diff");
			percentOfFuQuan = getDouble(o, "percentOfFuQuan");
			highLowZhenFu = getDouble(o, "highLowZhenFu");
			openCloseZhenFu = getDouble(o, "openCloseZhenFu");
		}







































































	static MongoMap<DayDto> copy(MongoMap<DayDto> map) {		MongoMapImpl<DayDto> m = new MongoMapImpl<DayDto>();				for (String key : map.keySet()) {			DayDto v = map.get(key);			m.put(key, MongoGen.copy(v));		}		return m;	}			static List<DayDto> copy(List<DayDto> list) {		List<DayDto> ls = Lists.newArrayList();		for (DayDto t : list) {			ls.add(MongoGen.copy(t));		}		return ls;	}		@Override		public String toString() {			return toObject().toString();		}	}
		public static class KeyValueDto implements MongoDto{		private String key = "";
		private String value = "";
		private boolean isClientVisible = false;
		private String dsc = "";
		public KeyValueDto() {		}				/**		 * Copy new one		 */		public KeyValueDto(KeyValueDto src) {			key = MongoGen.copy(src.key);			
			value = MongoGen.copy(src.value);			
			isClientVisible = MongoGen.copy(src.isClientVisible);			
			dsc = MongoGen.copy(src.dsc);			
		}		public String getKey() {			return this.key;		}
		public String getValue() {			return this.value;		}
		public boolean getIsClientVisible() {			return this.isClientVisible;		}
		public String getDsc() {			return this.dsc;		}
		public void setKey(String key) {			this.key = key;		}
		public void setValue(String value) {			this.value = value;		}
		public void setIsClientVisible(boolean isClientVisible) {			this.isClientVisible = isClientVisible;		}
		public void setDsc(String dsc) {			this.dsc = dsc;		}
		@Override		public DBObject toObject() {			BasicDBObject o = new BasicDBObject();
			o.put("key", MongoGen.toObject(key));			
			o.put("value", MongoGen.toObject(value));			
			o.put("isClientVisible", MongoGen.toObject(isClientVisible));			
			o.put("dsc", MongoGen.toObject(dsc));			
			return o;		}		@Override		public void fromDBObject(DBObject o) {			key = getString(o, "key");
			value = getString(o, "value");
			isClientVisible = getBoolean(o, "isClientVisible");
			dsc = getString(o, "dsc");
		}







	static MongoMap<KeyValueDto> copy(MongoMap<KeyValueDto> map) {		MongoMapImpl<KeyValueDto> m = new MongoMapImpl<KeyValueDto>();				for (String key : map.keySet()) {			KeyValueDto v = map.get(key);			m.put(key, MongoGen.copy(v));		}		return m;	}			static List<KeyValueDto> copy(List<KeyValueDto> list) {		List<KeyValueDto> ls = Lists.newArrayList();		for (KeyValueDto t : list) {			ls.add(MongoGen.copy(t));		}		return ls;	}		@Override		public String toString() {			return toObject().toString();		}	}
		public static class MoniPlayerDto implements MongoDto{		private String id = "";
		private List<MyStockDto> stocks = Lists.newArrayList();
		private double rmb = 0;
		private List<String> tradeLogs = Lists.newArrayList();
		public MoniPlayerDto() {		}				/**		 * Copy new one		 */		public MoniPlayerDto(MoniPlayerDto src) {			id = MongoGen.copy(src.id);			
			stocks = MyStockDto.copy(src.stocks);			
			rmb = MongoGen.copy(src.rmb);			
			tradeLogs = MongoGen.copyString(src.tradeLogs);			
		}		public String getId() {			return this.id;		}
		public List<MyStockDto> getStocks() {			return this.stocks;		}
		public double getRmb() {			return this.rmb;		}
		public List<String> getTradeLogs() {			return this.tradeLogs;		}
		public void setId(String id) {			this.id = id;		}
		public void setStocks(List<MyStockDto> stocks) {			this.stocks = stocks;		}
		public void setRmb(double rmb) {			this.rmb = rmb;		}
		public void setTradeLogs(List<String> tradeLogs) {			this.tradeLogs = tradeLogs;		}
		@Override		public DBObject toObject() {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);
			o.put("id", MongoGen.toObject(id));			
			o.put("stocks", MongoGen.toObjectMyStockDto(stocks));			
			o.put("rmb", MongoGen.toObject(rmb));			
			o.put("tradeLogs", MongoGen.toObjectString(tradeLogs));			
			return o;		}		@Override		public void fromDBObject(DBObject o) {			id = getString(o, "id");
			stocks = loadStocks(o);
			rmb = getDouble(o, "rmb");
			tradeLogs = loadTradeLogs(o);
		}




		List<MyStockDto> loadStocks(DBObject o) {			List<MyStockDto> ls = Lists.newArrayList();						BasicDBList stocks = getBasicDBList(o, "stocks");			for (Object xxx : stocks) {				MyStockDto tp = new MyStockDto();				tp.fromDBObject((DBObject) xxx);				ls.add(tp);			}			return ls;		}		

		List<String> loadTradeLogs(DBObject o) {			List<String> ls = Lists.newArrayList();						BasicDBList tradeLogs = getBasicDBList(o, "tradeLogs");			for (Object xxx : tradeLogs) {				ls.add((String)xxx);			}			return ls;		}										
	static MongoMap<MoniPlayerDto> copy(MongoMap<MoniPlayerDto> map) {		MongoMapImpl<MoniPlayerDto> m = new MongoMapImpl<MoniPlayerDto>();				for (String key : map.keySet()) {			MoniPlayerDto v = map.get(key);			m.put(key, MongoGen.copy(v));		}		return m;	}			static List<MoniPlayerDto> copy(List<MoniPlayerDto> list) {		List<MoniPlayerDto> ls = Lists.newArrayList();		for (MoniPlayerDto t : list) {			ls.add(MongoGen.copy(t));		}		return ls;	}		@Override		public String toString() {			return toObject().toString();		}	}
		public static class MyStockDto implements MongoDto{		private String id = "";
		private int count = 0;
		private double buyPrice = 0;
		private String buyDate = "";
		public MyStockDto() {		}				/**		 * Copy new one		 */		public MyStockDto(MyStockDto src) {			id = MongoGen.copy(src.id);			
			count = MongoGen.copy(src.count);			
			buyPrice = MongoGen.copy(src.buyPrice);			
			buyDate = MongoGen.copy(src.buyDate);			
		}		public String getId() {			return this.id;		}
		public int getCount() {			return this.count;		}
		public double getBuyPrice() {			return this.buyPrice;		}
		public String getBuyDate() {			return this.buyDate;		}
		public void setId(String id) {			this.id = id;		}
		public void setCount(int count) {			this.count = count;		}
		public void setBuyPrice(double buyPrice) {			this.buyPrice = buyPrice;		}
		public void setBuyDate(String buyDate) {			this.buyDate = buyDate;		}
		@Override		public DBObject toObject() {			BasicDBObject o = new BasicDBObject();
			o.put("id", MongoGen.toObject(id));			
			o.put("count", MongoGen.toObject(count));			
			o.put("buyPrice", MongoGen.toObject(buyPrice));			
			o.put("buyDate", MongoGen.toObject(buyDate));			
			return o;		}		@Override		public void fromDBObject(DBObject o) {			id = getString(o, "id");
			count = getInteger(o, "count");
			buyPrice = getDouble(o, "buyPrice");
			buyDate = getString(o, "buyDate");
		}







	static MongoMap<MyStockDto> copy(MongoMap<MyStockDto> map) {		MongoMapImpl<MyStockDto> m = new MongoMapImpl<MyStockDto>();				for (String key : map.keySet()) {			MyStockDto v = map.get(key);			m.put(key, MongoGen.copy(v));		}		return m;	}			static List<MyStockDto> copy(List<MyStockDto> list) {		List<MyStockDto> ls = Lists.newArrayList();		for (MyStockDto t : list) {			ls.add(MongoGen.copy(t));		}		return ls;	}		@Override		public String toString() {			return toObject().toString();		}	}
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
		@Override		public DBObject toObject() {			BasicDBObject o = new BasicDBObject();
			o.put("date", MongoGen.toObject(date));			
			o.put("open", MongoGen.toObject(open));			
			o.put("close", MongoGen.toObject(close));			
			o.put("high", MongoGen.toObject(high));			
			o.put("low", MongoGen.toObject(low));			
			o.put("up", MongoGen.toObject(up));			
			o.put("volume", MongoGen.toObject(volume));			
			o.put("avg5", MongoGen.toObject(avg5));			
			o.put("avg10", MongoGen.toObject(avg10));			
			o.put("avg15", MongoGen.toObject(avg15));			
			o.put("avg20", MongoGen.toObject(avg20));			
			o.put("avg30", MongoGen.toObject(avg30));			
			o.put("avg45", MongoGen.toObject(avg45));			
			o.put("avg60", MongoGen.toObject(avg60));			
			o.put("avg100", MongoGen.toObject(avg100));			
			o.put("avg120", MongoGen.toObject(avg120));			
			o.put("max30", MongoGen.toObject(max30));			
			o.put("max60", MongoGen.toObject(max60));			
			o.put("max90", MongoGen.toObject(max90));			
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





































	static MongoMap<PriceDto> copy(MongoMap<PriceDto> map) {		MongoMapImpl<PriceDto> m = new MongoMapImpl<PriceDto>();				for (String key : map.keySet()) {			PriceDto v = map.get(key);			m.put(key, MongoGen.copy(v));		}		return m;	}			static List<PriceDto> copy(List<PriceDto> list) {		List<PriceDto> ls = Lists.newArrayList();		for (PriceDto t : list) {			ls.add(MongoGen.copy(t));		}		return ls;	}		@Override		public String toString() {			return toObject().toString();		}	}
		public static class StockDto implements MongoDto{		private String id = "";
		private MongoMap<DayDto> days = Maps.newMongoMap();
		private List<ChuQuanDateDto> chuQuanDates = Lists.newArrayList();
		public StockDto() {		}				/**		 * Copy new one		 */		public StockDto(StockDto src) {			id = MongoGen.copy(src.id);			
			days = DayDto.copy(src.days);			
			chuQuanDates = ChuQuanDateDto.copy(src.chuQuanDates);			
		}		public String getId() {			return this.id;		}
		public MongoMap<DayDto> getDays() {			return this.days;		}
		public List<ChuQuanDateDto> getChuQuanDates() {			return this.chuQuanDates;		}
		public void setId(String id) {			this.id = id;		}
		public void setDays(MongoMap<DayDto> days) {			this.days = days;		}
		public void setChuQuanDates(List<ChuQuanDateDto> chuQuanDates) {			this.chuQuanDates = chuQuanDates;		}
		@Override		public DBObject toObject() {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);
			o.put("id", MongoGen.toObject(id));			
			o.put("days", MongoGen.toObject(days));
			o.put("chuQuanDates", MongoGen.toObjectChuQuanDateDto(chuQuanDates));			
			return o;		}		@Override		public void fromDBObject(DBObject o) {			id = getString(o, "id");
			days = loadDays(o);
			chuQuanDates = loadChuQuanDates(o);
		}
		MongoMap<DayDto> loadDays(DBObject o) {			BasicDBObject dto = (BasicDBObject) o.get("days");			if (dto == null) {				return null;			}			MongoMap<DayDto> map = Maps.newMongoMap();			for (String key : dto.keySet()) {				DayDto d = new DayDto();				d.fromDBObject((BasicDBObject)dto.get(key));				map.put(key, d);			}			return map;		}						



		List<ChuQuanDateDto> loadChuQuanDates(DBObject o) {			List<ChuQuanDateDto> ls = Lists.newArrayList();						BasicDBList chuQuanDates = getBasicDBList(o, "chuQuanDates");			for (Object xxx : chuQuanDates) {				ChuQuanDateDto tp = new ChuQuanDateDto();				tp.fromDBObject((DBObject) xxx);				ls.add(tp);			}			return ls;		}		
	static MongoMap<StockDto> copy(MongoMap<StockDto> map) {		MongoMapImpl<StockDto> m = new MongoMapImpl<StockDto>();				for (String key : map.keySet()) {			StockDto v = map.get(key);			m.put(key, MongoGen.copy(v));		}		return m;	}			static List<StockDto> copy(List<StockDto> list) {		List<StockDto> ls = Lists.newArrayList();		for (StockDto t : list) {			ls.add(MongoGen.copy(t));		}		return ls;	}		@Override		public String toString() {			return toObject().toString();		}	}
		public static class StockIdDto implements MongoDto{		private String id = "";
		public StockIdDto() {		}				/**		 * Copy new one		 */		public StockIdDto(StockIdDto src) {			id = MongoGen.copy(src.id);			
		}		public String getId() {			return this.id;		}
		public void setId(String id) {			this.id = id;		}
		@Override		public DBObject toObject() {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);
			o.put("id", MongoGen.toObject(id));			
			return o;		}		@Override		public void fromDBObject(DBObject o) {			id = getString(o, "id");
		}

	static MongoMap<StockIdDto> copy(MongoMap<StockIdDto> map) {		MongoMapImpl<StockIdDto> m = new MongoMapImpl<StockIdDto>();				for (String key : map.keySet()) {			StockIdDto v = map.get(key);			m.put(key, MongoGen.copy(v));		}		return m;	}			static List<StockIdDto> copy(List<StockIdDto> list) {		List<StockIdDto> ls = Lists.newArrayList();		for (StockIdDto t : list) {			ls.add(MongoGen.copy(t));		}		return ls;	}		@Override		public String toString() {			return toObject().toString();		}	}
		public static class StockNameDto implements MongoDto{		private String id = "";
		private String name = "";
		public StockNameDto() {		}				/**		 * Copy new one		 */		public StockNameDto(StockNameDto src) {			id = MongoGen.copy(src.id);			
			name = MongoGen.copy(src.name);			
		}		public String getId() {			return this.id;		}
		public String getName() {			return this.name;		}
		public void setId(String id) {			this.id = id;		}
		public void setName(String name) {			this.name = name;		}
		@Override		public DBObject toObject() {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);
			o.put("id", MongoGen.toObject(id));			
			o.put("name", MongoGen.toObject(name));			
			return o;		}		@Override		public void fromDBObject(DBObject o) {			id = getString(o, "id");
			name = getString(o, "name");
		}



	static MongoMap<StockNameDto> copy(MongoMap<StockNameDto> map) {		MongoMapImpl<StockNameDto> m = new MongoMapImpl<StockNameDto>();				for (String key : map.keySet()) {			StockNameDto v = map.get(key);			m.put(key, MongoGen.copy(v));		}		return m;	}			static List<StockNameDto> copy(List<StockNameDto> list) {		List<StockNameDto> ls = Lists.newArrayList();		for (StockNameDto t : list) {			ls.add(MongoGen.copy(t));		}		return ls;	}		@Override		public String toString() {			return toObject().toString();		}	}
		public static class StockPriceDto implements MongoDto{		private String id = "";
		private String time = "";
		private String price = "";
		private String closeYestoday = "";
		private String highToday = "";
		private String lowToday = "";
		public StockPriceDto() {		}				/**		 * Copy new one		 */		public StockPriceDto(StockPriceDto src) {			id = MongoGen.copy(src.id);			
			time = MongoGen.copy(src.time);			
			price = MongoGen.copy(src.price);			
			closeYestoday = MongoGen.copy(src.closeYestoday);			
			highToday = MongoGen.copy(src.highToday);			
			lowToday = MongoGen.copy(src.lowToday);			
		}		public String getId() {			return this.id;		}
		public String getTime() {			return this.time;		}
		public String getPrice() {			return this.price;		}
		public String getCloseYestoday() {			return this.closeYestoday;		}
		public String getHighToday() {			return this.highToday;		}
		public String getLowToday() {			return this.lowToday;		}
		public void setId(String id) {			this.id = id;		}
		public void setTime(String time) {			this.time = time;		}
		public void setPrice(String price) {			this.price = price;		}
		public void setCloseYestoday(String closeYestoday) {			this.closeYestoday = closeYestoday;		}
		public void setHighToday(String highToday) {			this.highToday = highToday;		}
		public void setLowToday(String lowToday) {			this.lowToday = lowToday;		}
		@Override		public DBObject toObject() {			BasicDBObject o = new BasicDBObject();		o.put("_id", id + ":" + time);
			o.put("id", MongoGen.toObject(id));			
			o.put("time", MongoGen.toObject(time));			
			o.put("price", MongoGen.toObject(price));			
			o.put("closeYestoday", MongoGen.toObject(closeYestoday));			
			o.put("highToday", MongoGen.toObject(highToday));			
			o.put("lowToday", MongoGen.toObject(lowToday));			
			return o;		}		@Override		public void fromDBObject(DBObject o) {			id = getString(o, "id");
			time = getString(o, "time");
			price = getString(o, "price");
			closeYestoday = getString(o, "closeYestoday");
			highToday = getString(o, "highToday");
			lowToday = getString(o, "lowToday");
		}











	static MongoMap<StockPriceDto> copy(MongoMap<StockPriceDto> map) {		MongoMapImpl<StockPriceDto> m = new MongoMapImpl<StockPriceDto>();				for (String key : map.keySet()) {			StockPriceDto v = map.get(key);			m.put(key, MongoGen.copy(v));		}		return m;	}			static List<StockPriceDto> copy(List<StockPriceDto> list) {		List<StockPriceDto> ls = Lists.newArrayList();		for (StockPriceDto t : list) {			ls.add(MongoGen.copy(t));		}		return ls;	}		@Override		public String toString() {			return toObject().toString();		}	}
}