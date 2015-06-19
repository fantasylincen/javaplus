package cn.vgame.a.gen.dto;import java.net.UnknownHostException;import java.util.ArrayList;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import java.util.List;import java.util.Map;import java.util.Set;import cn.javaplus.exception.UnImplMethodException;import com.mongodb.BasicDBList;import com.mongodb.BasicDBObject;import com.mongodb.DB;import com.mongodb.DBCollection;import com.mongodb.DBCursor;import com.mongodb.DBObject;import com.mongodb.Mongo;import com.mongodb.MongoClient;import java.util.regex.Pattern;public class MongoGen {	public static class Lists {		public static<T> List<T> newArrayList() {			return new ArrayList<T>();		}	}	public static class Maps {		public static<K, V> Map<K, V> newHashMap() {			return new HashMap<K, V>();		}		public static<V> MongoMap<V> newMongoMap() {			return new MongoMapImpl<V>();		}	}			public static interface MongoMap<V> {		V get(String k);		void put(String k, V v);		Set<String> keySet();		Collection<V> values();	}		public static class MongoMapImpl<V> implements MongoMap<V> {		Map<String, V> map = Maps.newHashMap();				@Override		public V get(String k) {			return map.get(k);		}		@Override		public void put(String k, V v) {			map.put(k, v);		}		@Override		public Set<String> keySet() {			return map.keySet();		}		@Override		public Collection<V> values() {			return map.values();		}			}																static BasicDBObject toObjectBytes(MongoMap<byte[]> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			byte[] v = map.get(key);			o.put(key, toObject(v));		}		return o;	}	static BasicDBObject toObjectByte(MongoMap<Byte> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			Byte v = map.get(key);			o.put(key, toObject(v));		}		return o;	}	static BasicDBObject toObjectCharacter(MongoMap<Character> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			Character v = map.get(key);			o.put(key, toObject(v));		}		return o;	}	static BasicDBObject toObjectBoolean(MongoMap<Boolean> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			Boolean v = map.get(key);			o.put(key, toObject(v));		}		return o;	}	static BasicDBObject toObjectShort(MongoMap<Short> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			Short v = map.get(key);			o.put(key, toObject(v));		}		return o;	}	static BasicDBObject toObjectInteger(MongoMap<Integer> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			Integer v = map.get(key);			o.put(key, toObject(v));		}		return o;	}	static BasicDBObject toObjectLong(MongoMap<Long> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			Long v = map.get(key);			o.put(key, toObject(v));		}		return o;	}	static BasicDBObject toObjectFloat(MongoMap<Float> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			Float v = map.get(key);			o.put(key, toObject(v));		}		return o;	}	static BasicDBObject toObjectDouble(MongoMap<Double> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			Double v = map.get(key);			o.put(key, toObject(v));		}		return o;	}	static BasicDBObject toObjectString(MongoMap<String> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			String v = map.get(key);			o.put(key, toObject(v));		}		return o;	}	static BasicDBList toObjectBytes(Collection<byte[]> list) {		BasicDBList prices = new BasicDBList();		for (byte[] o : list) {			prices.add(toObject(o));		}		return prices;	}	static BasicDBList toObjectByte(Collection<Byte> list) {		BasicDBList prices = new BasicDBList();		for (Byte o : list) {			prices.add(toObject(o));		}		return prices;	}	static BasicDBList toObjectCharacter(Collection<Character> list) {		BasicDBList prices = new BasicDBList();		for (Character o : list) {			prices.add(toObject(o));		}		return prices;	}	static BasicDBList toObjectBoolean(Collection<Boolean> list) {		BasicDBList prices = new BasicDBList();		for (Boolean o : list) {			prices.add(toObject(o));		}		return prices;	}	static BasicDBList toObjectShort(Collection<Short> list) {		BasicDBList prices = new BasicDBList();		for (Short o : list) {			prices.add(toObject(o));		}		return prices;	}	static BasicDBList toObjectInteger(Collection<Integer> list) {		BasicDBList prices = new BasicDBList();		for (Integer o : list) {			prices.add(toObject(o));		}		return prices;	}	static BasicDBList toObjectLong(Collection<Long> list) {		BasicDBList prices = new BasicDBList();		for (Long o : list) {			prices.add(toObject(o));		}		return prices;	}	static BasicDBList toObjectFloat(Collection<Float> list) {		BasicDBList prices = new BasicDBList();		for (Float o : list) {			prices.add(toObject(o));		}		return prices;	}	static BasicDBList toObjectDouble(Collection<Double> list) {		BasicDBList prices = new BasicDBList();		for (Double o : list) {			prices.add(toObject(o));		}		return prices;	}	static BasicDBList toObjectString(Collection<String> list) {		BasicDBList prices = new BasicDBList();		for (String o : list) {			prices.add(toObject(o));		}		return prices;	}	static byte[] toObject(byte[] value) {		return value;	}	static byte toObject(byte value) {		return value;	}	static char toObject(char value) {		return value;	}	static boolean toObject(boolean value) {		return value;	}	static short toObject(short value) {		return value;	}	static int toObject(int value) {		return value;	}	static long toObject(long value) {		return value;	}	static float toObject(float value) {		return value;	}	static double toObject(double value) {		return value;	}	static String toObject(String value) {		return value;	}	static DBObject toObject(MongoDto value) {		if(value == null)			return null;		return value.toObject();	}	static <V extends MongoDto> BasicDBObject toObject(MongoMap<V> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			V v = map.get(key);			o.put(key, toObject(v));		}		return o;	}		static <V extends MongoDto> BasicDBList toObject(List<V> ls) {		BasicDBList o = new BasicDBList();		for (V v : ls) {			o.add(toObject(v));		}		return o;	}	static MongoMap<byte[]> copyBytes(MongoMap<byte[]> value) {		MongoMap<byte[]> map = Maps.newMongoMap();		for (String key : value.keySet()) {			map.put(key, MongoGen.copy(value.get(key)));		}		return map;	}	static MongoMap<Byte> copyByte(MongoMap<Byte> value) {		MongoMap<Byte> map = Maps.newMongoMap();		for (String key : value.keySet()) {			map.put(key, value.get(key));		}		return map;	}	static MongoMap<Character> copyCharacter(MongoMap<Character> value) {		MongoMap<Character> map = Maps.newMongoMap();		for (String key : value.keySet()) {			map.put(key, value.get(key));		}		return map;	}	static MongoMap<Boolean> copyBoolean(MongoMap<Boolean> value) {		MongoMap<Boolean> map = Maps.newMongoMap();		for (String key : value.keySet()) {			map.put(key, value.get(key));		}		return map;	}	static MongoMap<Short> copyShort(MongoMap<Short> value) {		MongoMap<Short> map = Maps.newMongoMap();		for (String key : value.keySet()) {			map.put(key, value.get(key));		}		return map;	}	static MongoMap<Integer> copyInteger(MongoMap<Integer> value) {		MongoMap<Integer> map = Maps.newMongoMap();		for (String key : value.keySet()) {			map.put(key, value.get(key));		}		return map;	}	static MongoMap<Long> copyLong(MongoMap<Long> value) {		MongoMap<Long> map = Maps.newMongoMap();		for (String key : value.keySet()) {			map.put(key, value.get(key));		}		return map;	}	static MongoMap<Float> copyFloat(MongoMap<Float> value) {		MongoMap<Float> map = Maps.newMongoMap();		for (String key : value.keySet()) {			map.put(key, value.get(key));		}		return map;	}	static MongoMap<Double> copyDouble(MongoMap<Double> value) {		MongoMap<Double> map = Maps.newMongoMap();		for (String key : value.keySet()) {			map.put(key, value.get(key));		}		return map;	}	static MongoMap<String> copyString(MongoMap<String> value) {		MongoMap<String> map = Maps.newMongoMap();		for (String key : value.keySet()) {			map.put(key, new String(value.get(key)));		}		return map;	}	static List<byte[]> copyBytes(List<byte[]> value) {		List<byte[]> ls = Lists.newArrayList();		for (byte[] v : value) {			ls.add(copy(v));		}		return ls;	}	static List<Byte> copyByte(List<Byte> value) {		List<Byte> ls = Lists.newArrayList();		for (Byte v : value) {			ls.add(v);		}		return ls;	}	static List<Character> copyCharacter(List<Character> value) {		List<Character> ls = Lists.newArrayList();		for (Character v : value) {			ls.add(v);		}		return ls;	}	static List<Boolean> copyBoolean(List<Boolean> value) {		List<Boolean> ls = Lists.newArrayList();		for (Boolean v : value) {			ls.add(v);		}		return ls;	}	static List<Short> copyShort(List<Short> value) {		List<Short> ls = Lists.newArrayList();		for (Short v : value) {			ls.add(v);		}		return ls;	}	static List<Integer> copyInteger(List<Integer> value) {		List<Integer> ls = Lists.newArrayList();		for (Integer v : value) {			ls.add(v);		}		return ls;	}	static List<Long> copyLong(List<Long> value) {		List<Long> ls = Lists.newArrayList();		for (Long v : value) {			ls.add(v);		}		return ls;	}	static List<Float> copyFloat(List<Float> value) {		List<Float> ls = Lists.newArrayList();		for (Float v : value) {			ls.add(v);		}		return ls;	}	static List<Double> copyDouble(List<Double> value) {		List<Double> ls = Lists.newArrayList();		for (Double v : value) {			ls.add(v);		}		return ls;	}	static List<String> copyString(List<String> value) {		List<String> ls = Lists.newArrayList();		for (String v : value) {			ls.add(new String(v));		}		return ls;	}		static byte[] copy(byte[] value) {		return java.util.Arrays.copyOf(value, value.length);	}	static byte copy(byte value) {		return value;	}	static char copy(char value) {		return value;	}	static boolean copy(boolean value) {		return value;	}	static short copy(short value) {		return value;	}	static int copy(int value) {		return value;	}	static long copy(long value) {		return value;	}	static float copy(float value) {		return value;	}	static double copy(double value) {		return value;	}	static String copy(String value) {		return new String(value);	}												static BasicDBList getBasicDBList(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return new BasicDBList();		}		return (BasicDBList) obj;	}	static boolean getBoolean(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return false;		}		return (Boolean) obj;	}		static int getInteger(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return 0;		}		return (Integer) obj;	}		static byte[] getBytes(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return null;		}		return (byte[]) obj;	}		static float getFloat(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return 0;		}		return (Float) obj;	}		static String getString(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return "";		}		return (String) obj;	}	static long getLong(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return 0;		}		return (Long) obj;	}		static double getDouble(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return 0;		}		return (Double) obj;	}										 static void put(String key, BasicDBObject o, AppStoreRechargeLogDto value) {		o.put(key, MongoGen.toObject(value));	 }	 
							 static void put(String key, BasicDBObject o, CaiJinLogDto value) {		o.put(key, MongoGen.toObject(value));	 }	 
							 static void put(String key, BasicDBObject o, CoinLogDto value) {		o.put(key, MongoGen.toObject(value));	 }	 
							 static void put(String key, BasicDBObject o, ConsoleLogDto value) {		o.put(key, MongoGen.toObject(value));	 }	 
							 static void put(String key, BasicDBObject o, GmLogDto value) {		o.put(key, MongoGen.toObject(value));	 }	 
							 static void put(String key, BasicDBObject o, RoleDto value) {		o.put(key, MongoGen.toObject(value));	 }	 
							 static void put(String key, BasicDBObject o, SystemKeyValueDto value) {		o.put(key, MongoGen.toObject(value));	 }	 
							 static void put(String key, BasicDBObject o, YiJieRechargeLogDto value) {		o.put(key, MongoGen.toObject(value));	 }	 
							 static void put(String key, BasicDBObject o, ZfbOrderDto value) {		o.put(key, MongoGen.toObject(value));	 }	 
							 static void put(String key, BasicDBObject o, ZfbOrderFinishDto value) {		o.put(key, MongoGen.toObject(value));	 }	 
							 static void put(String key, BasicDBObject o, ZhuangDto value) {		o.put(key, MongoGen.toObject(value));	 }	 
								 static AppStoreRechargeLogDto copy(AppStoreRechargeLogDto value) {		return new AppStoreRechargeLogDto(value);	 }
							 static CaiJinLogDto copy(CaiJinLogDto value) {		return new CaiJinLogDto(value);	 }
							 static CoinLogDto copy(CoinLogDto value) {		return new CoinLogDto(value);	 }
							 static ConsoleLogDto copy(ConsoleLogDto value) {		return new ConsoleLogDto(value);	 }
							 static GmLogDto copy(GmLogDto value) {		return new GmLogDto(value);	 }
							 static RoleDto copy(RoleDto value) {		return new RoleDto(value);	 }
							 static SystemKeyValueDto copy(SystemKeyValueDto value) {		return new SystemKeyValueDto(value);	 }
							 static YiJieRechargeLogDto copy(YiJieRechargeLogDto value) {		return new YiJieRechargeLogDto(value);	 }
							 static ZfbOrderDto copy(ZfbOrderDto value) {		return new ZfbOrderDto(value);	 }
							 static ZfbOrderFinishDto copy(ZfbOrderFinishDto value) {		return new ZfbOrderFinishDto(value);	 }
							 static ZhuangDto copy(ZhuangDto value) {		return new ZhuangDto(value);	 }
							static DBObject toObject(AppStoreRechargeLogDto value) {		if(value == null) 			return null;		return value.toObject();	}	 	static DBObject toObjectAppStoreRechargeLogDto(AppStoreRechargeLogDto value) {		return value.toObject();	}	 	static BasicDBList toObjectAppStoreRechargeLogDto(List<AppStoreRechargeLogDto> value) {		BasicDBList o = new BasicDBList();		for (AppStoreRechargeLogDto v : value) {			o.add(toObject(v));		}		return o;	}	 	 
							static DBObject toObject(CaiJinLogDto value) {		if(value == null) 			return null;		return value.toObject();	}	 	static DBObject toObjectCaiJinLogDto(CaiJinLogDto value) {		return value.toObject();	}	 	static BasicDBList toObjectCaiJinLogDto(List<CaiJinLogDto> value) {		BasicDBList o = new BasicDBList();		for (CaiJinLogDto v : value) {			o.add(toObject(v));		}		return o;	}	 	 
							static DBObject toObject(CoinLogDto value) {		if(value == null) 			return null;		return value.toObject();	}	 	static DBObject toObjectCoinLogDto(CoinLogDto value) {		return value.toObject();	}	 	static BasicDBList toObjectCoinLogDto(List<CoinLogDto> value) {		BasicDBList o = new BasicDBList();		for (CoinLogDto v : value) {			o.add(toObject(v));		}		return o;	}	 	 
							static DBObject toObject(ConsoleLogDto value) {		if(value == null) 			return null;		return value.toObject();	}	 	static DBObject toObjectConsoleLogDto(ConsoleLogDto value) {		return value.toObject();	}	 	static BasicDBList toObjectConsoleLogDto(List<ConsoleLogDto> value) {		BasicDBList o = new BasicDBList();		for (ConsoleLogDto v : value) {			o.add(toObject(v));		}		return o;	}	 	 
							static DBObject toObject(GmLogDto value) {		if(value == null) 			return null;		return value.toObject();	}	 	static DBObject toObjectGmLogDto(GmLogDto value) {		return value.toObject();	}	 	static BasicDBList toObjectGmLogDto(List<GmLogDto> value) {		BasicDBList o = new BasicDBList();		for (GmLogDto v : value) {			o.add(toObject(v));		}		return o;	}	 	 
							static DBObject toObject(RoleDto value) {		if(value == null) 			return null;		return value.toObject();	}	 	static DBObject toObjectRoleDto(RoleDto value) {		return value.toObject();	}	 	static BasicDBList toObjectRoleDto(List<RoleDto> value) {		BasicDBList o = new BasicDBList();		for (RoleDto v : value) {			o.add(toObject(v));		}		return o;	}	 	 
							static DBObject toObject(SystemKeyValueDto value) {		if(value == null) 			return null;		return value.toObject();	}	 	static DBObject toObjectSystemKeyValueDto(SystemKeyValueDto value) {		return value.toObject();	}	 	static BasicDBList toObjectSystemKeyValueDto(List<SystemKeyValueDto> value) {		BasicDBList o = new BasicDBList();		for (SystemKeyValueDto v : value) {			o.add(toObject(v));		}		return o;	}	 	 
							static DBObject toObject(YiJieRechargeLogDto value) {		if(value == null) 			return null;		return value.toObject();	}	 	static DBObject toObjectYiJieRechargeLogDto(YiJieRechargeLogDto value) {		return value.toObject();	}	 	static BasicDBList toObjectYiJieRechargeLogDto(List<YiJieRechargeLogDto> value) {		BasicDBList o = new BasicDBList();		for (YiJieRechargeLogDto v : value) {			o.add(toObject(v));		}		return o;	}	 	 
							static DBObject toObject(ZfbOrderDto value) {		if(value == null) 			return null;		return value.toObject();	}	 	static DBObject toObjectZfbOrderDto(ZfbOrderDto value) {		return value.toObject();	}	 	static BasicDBList toObjectZfbOrderDto(List<ZfbOrderDto> value) {		BasicDBList o = new BasicDBList();		for (ZfbOrderDto v : value) {			o.add(toObject(v));		}		return o;	}	 	 
							static DBObject toObject(ZfbOrderFinishDto value) {		if(value == null) 			return null;		return value.toObject();	}	 	static DBObject toObjectZfbOrderFinishDto(ZfbOrderFinishDto value) {		return value.toObject();	}	 	static BasicDBList toObjectZfbOrderFinishDto(List<ZfbOrderFinishDto> value) {		BasicDBList o = new BasicDBList();		for (ZfbOrderFinishDto v : value) {			o.add(toObject(v));		}		return o;	}	 	 
							static DBObject toObject(ZhuangDto value) {		if(value == null) 			return null;		return value.toObject();	}	 	static DBObject toObjectZhuangDto(ZhuangDto value) {		return value.toObject();	}	 	static BasicDBList toObjectZhuangDto(List<ZhuangDto> value) {		BasicDBList o = new BasicDBList();		for (ZhuangDto v : value) {			o.add(toObject(v));		}		return o;	}	 	 
		public static interface CollectionFetcher {			DBCollection getCollection(String string);		void destroy();	}			public static interface MongoDto {			DBObject toObject();		void fromDBObject(DBObject o);	}			public static interface MongoDbProperties {			/**		 * 数据库名		 */		String getName();				/**		 * 主机地址		 */		String getHost();				/**		 * 端口		 */		int getPort();		}	public static final class Daos {			private static CollectionFetcher	fetcher;		private static MongoDbProperties	properties;		private static Map<String, DBCollection> cache = new HashMap<String, DBCollection>();				public static void destroy() {			getCollectionFetcher().destroy();		}				public static final void setProperties(MongoDbProperties properties) {			Daos.properties = properties;		}				public static final CollectionFetcher getCollectionFetcher() {			if(fetcher == null)				fetcher = new NormalFetcher();			return fetcher;		}				private static  class NormalFetcher implements CollectionFetcher {			@Override			public DBCollection getCollection(String name) {								Mongo m = getMongo();				DB db = m.getDB(properties.getName());				return db.getCollection(name);			}			@Override			public void destroy() {				if(mongo != null)					mongo.close();			}						private static Mongo mongo;			private static Mongo getMongo() {				if(mongo == null) {					try {						if(properties == null) {							throw new NullPointerException("请先setProperties");						}						mongo = new MongoClient(properties.getHost(), properties.getPort());					} catch (UnknownHostException e) {						throw cn.javaplus.util.Util.Exception.toRuntimeException(e);					}				}				return mongo;			}		}		private static DBCollection getCollection(String collectionName) {			DBCollection c;			try {				c = cache.get(collectionName);			} catch (NullPointerException e) {				c = null;			}			if (c != null) {				return c;			}			c = getCollectionFetcher().getCollection(collectionName);			cache.put(collectionName, c);			return c;		}		public static AppStoreRechargeLogDao getAppStoreRechargeLogDao() {			return new AppStoreRechargeLogDao(getCollection("AppStoreRechargeLog"));		}
		public static CaiJinLogDao getCaiJinLogDao() {			return new CaiJinLogDao(getCollection("CaiJinLog"));		}
		public static CoinLogDao getCoinLogDao() {			return new CoinLogDao(getCollection("CoinLog"));		}
		public static ConsoleLogDao getConsoleLogDao() {			return new ConsoleLogDao(getCollection("ConsoleLog"));		}
		public static GmLogDao getGmLogDao() {			return new GmLogDao(getCollection("GmLog"));		}
		public static RoleDao getRoleDao() {			return new RoleDao(getCollection("Role"));		}
		public static SystemKeyValueDao getSystemKeyValueDao() {			return new SystemKeyValueDao(getCollection("SystemKeyValue"));		}
		public static YiJieRechargeLogDao getYiJieRechargeLogDao() {			return new YiJieRechargeLogDao(getCollection("YiJieRechargeLog"));		}
		public static ZfbOrderDao getZfbOrderDao() {			return new ZfbOrderDao(getCollection("ZfbOrder"));		}
		public static ZfbOrderFinishDao getZfbOrderFinishDao() {			return new ZfbOrderFinishDao(getCollection("ZfbOrderFinish"));		}
		public static ZhuangDao getZhuangDao() {			return new ZhuangDao(getCollection("Zhuang"));		}
		}
		public static class AppStoreRechargeLogDao {			private DBCollection	collection;			public AppStoreRechargeLogDao(DBCollection collection) {			this.collection = collection;		}			public void save(AppStoreRechargeLogDto u) {			collection.save(u.toObject());		}			public void delete(AppStoreRechargeLogDto u) {			delete(u.getId());		}			public void delete(String id) {			collection.remove(key(id));		}			public AppStoreRechargeLogDto get(String id) {			DBObject o = collection.findOne(key(id));			if(o == null) {				return null;			}			AppStoreRechargeLogDto x = new AppStoreRechargeLogDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String id) {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);			return o;		}			public AppStoreRechargeLogDtoCursor find() {			return new AppStoreRechargeLogDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public AppStoreRechargeLogDtoCursor findById(String id) {			collection.ensureIndex("id");			BasicDBObject o = new BasicDBObject("id", id);			return new AppStoreRechargeLogDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public AppStoreRechargeLogDtoCursor findByIdFuzzy(String id) {			collection.ensureIndex("id");			id = id.replaceAll("\\*", ".*");			id = "^" + id + "$";			BasicDBObject o = new BasicDBObject("id", Pattern.compile(id, Pattern.CASE_INSENSITIVE));			return new AppStoreRechargeLogDtoCursor(collection.find(o));		}
		public AppStoreRechargeLogDtoCursor findByProductId(String productId) {						BasicDBObject o = new BasicDBObject("productId", productId);			return new AppStoreRechargeLogDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public AppStoreRechargeLogDtoCursor findByProductIdFuzzy(String productId) {						productId = productId.replaceAll("\\*", ".*");			productId = "^" + productId + "$";			BasicDBObject o = new BasicDBObject("productId", Pattern.compile(productId, Pattern.CASE_INSENSITIVE));			return new AppStoreRechargeLogDtoCursor(collection.find(o));		}
		public AppStoreRechargeLogDtoCursor findByFee(int fee) {						BasicDBObject o = new BasicDBObject("fee", fee);			return new AppStoreRechargeLogDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public AppStoreRechargeLogDtoCursor findFeeBetween(int min, int max) {						BasicDBObject o = new BasicDBObject();			o.put("fee", new BasicDBObject("$gte", min).append("$lte", max));			return new AppStoreRechargeLogDtoCursor(collection.find(o));		}
		public AppStoreRechargeLogDtoCursor findByRoleId(String roleId) {						BasicDBObject o = new BasicDBObject("roleId", roleId);			return new AppStoreRechargeLogDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public AppStoreRechargeLogDtoCursor findByRoleIdFuzzy(String roleId) {						roleId = roleId.replaceAll("\\*", ".*");			roleId = "^" + roleId + "$";			BasicDBObject o = new BasicDBObject("roleId", Pattern.compile(roleId, Pattern.CASE_INSENSITIVE));			return new AppStoreRechargeLogDtoCursor(collection.find(o));		}
		public AppStoreRechargeLogDtoCursor findByNick(String nick) {						BasicDBObject o = new BasicDBObject("nick", nick);			return new AppStoreRechargeLogDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public AppStoreRechargeLogDtoCursor findByNickFuzzy(String nick) {						nick = nick.replaceAll("\\*", ".*");			nick = "^" + nick + "$";			BasicDBObject o = new BasicDBObject("nick", Pattern.compile(nick, Pattern.CASE_INSENSITIVE));			return new AppStoreRechargeLogDtoCursor(collection.find(o));		}
		public AppStoreRechargeLogDtoCursor findByCoin(long coin) {						BasicDBObject o = new BasicDBObject("coin", coin);			return new AppStoreRechargeLogDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public AppStoreRechargeLogDtoCursor findCoinBetween(long min, long max) {						BasicDBObject o = new BasicDBObject();			o.put("coin", new BasicDBObject("$gte", min).append("$lte", max));			return new AppStoreRechargeLogDtoCursor(collection.find(o));		}
		public AppStoreRechargeLogDtoCursor findByJiangQuan(long jiangQuan) {						BasicDBObject o = new BasicDBObject("jiangQuan", jiangQuan);			return new AppStoreRechargeLogDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public AppStoreRechargeLogDtoCursor findJiangQuanBetween(long min, long max) {						BasicDBObject o = new BasicDBObject();			o.put("jiangQuan", new BasicDBObject("$gte", min).append("$lte", max));			return new AppStoreRechargeLogDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public AppStoreRechargeLogDto createDTO() {			return new AppStoreRechargeLogDto();		}			public static class AppStoreRechargeLogDtoCursor implements Iterator<AppStoreRechargeLogDto>, Iterable<AppStoreRechargeLogDto>{				private DBCursor	cursor;			private int pageAll;				public AppStoreRechargeLogDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public AppStoreRechargeLogDto next() {				DBObject next = cursor.next();				AppStoreRechargeLogDto dto = new AppStoreRechargeLogDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void skip(int skip) {				cursor.skip(skip);			}						public void limit(int limit) {				cursor.limit(limit);			}						/**			 * 分页, page从1开始 countOfEveryPage必须大于0			 */			public void page(int page, int countOfEveryPage) {				if(countOfEveryPage <= 0) {					throw new RuntimeException("countOfEveryPage must > 0");				}				int count = getCount();				pageAll = count / countOfEveryPage;				if(count % countOfEveryPage != 0) {					pageAll ++;				}								if(page > pageAll)					page = pageAll;								if(page < 1)					page = 1;									int skip = (page - 1) * countOfEveryPage ;				skip(skip);				limit(countOfEveryPage);			}						public int getPageAll() {				return pageAll;			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<AppStoreRechargeLogDto> iterator() {				return this;			}		}	}
		public static class CaiJinLogDao {			private DBCollection	collection;			public CaiJinLogDao(DBCollection collection) {			this.collection = collection;		}			public void save(CaiJinLogDto u) {			collection.save(u.toObject());		}			public void delete(CaiJinLogDto u) {			delete(u.getId());		}			public void delete(String id) {			collection.remove(key(id));		}			public CaiJinLogDto get(String id) {			DBObject o = collection.findOne(key(id));			if(o == null) {				return null;			}			CaiJinLogDto x = new CaiJinLogDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String id) {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);			return o;		}			public CaiJinLogDtoCursor find() {			return new CaiJinLogDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public CaiJinLogDtoCursor findById(String id) {			collection.ensureIndex("id");			BasicDBObject o = new BasicDBObject("id", id);			return new CaiJinLogDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public CaiJinLogDtoCursor findByIdFuzzy(String id) {			collection.ensureIndex("id");			id = id.replaceAll("\\*", ".*");			id = "^" + id + "$";			BasicDBObject o = new BasicDBObject("id", Pattern.compile(id, Pattern.CASE_INSENSITIVE));			return new CaiJinLogDtoCursor(collection.find(o));		}
		public CaiJinLogDtoCursor findByRoleId(String roleId) {						BasicDBObject o = new BasicDBObject("roleId", roleId);			return new CaiJinLogDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public CaiJinLogDtoCursor findByRoleIdFuzzy(String roleId) {						roleId = roleId.replaceAll("\\*", ".*");			roleId = "^" + roleId + "$";			BasicDBObject o = new BasicDBObject("roleId", Pattern.compile(roleId, Pattern.CASE_INSENSITIVE));			return new CaiJinLogDtoCursor(collection.find(o));		}
		public CaiJinLogDtoCursor findByNick(String nick) {						BasicDBObject o = new BasicDBObject("nick", nick);			return new CaiJinLogDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public CaiJinLogDtoCursor findByNickFuzzy(String nick) {						nick = nick.replaceAll("\\*", ".*");			nick = "^" + nick + "$";			BasicDBObject o = new BasicDBObject("nick", Pattern.compile(nick, Pattern.CASE_INSENSITIVE));			return new CaiJinLogDtoCursor(collection.find(o));		}
		public CaiJinLogDtoCursor findByCaiJin(long caiJin) {						BasicDBObject o = new BasicDBObject("caiJin", caiJin);			return new CaiJinLogDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public CaiJinLogDtoCursor findCaiJinBetween(long min, long max) {						BasicDBObject o = new BasicDBObject();			o.put("caiJin", new BasicDBObject("$gte", min).append("$lte", max));			return new CaiJinLogDtoCursor(collection.find(o));		}
		public CaiJinLogDtoCursor findByIsSmall(boolean isSmall) {						BasicDBObject o = new BasicDBObject("isSmall", isSmall);			return new CaiJinLogDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public CaiJinLogDto createDTO() {			return new CaiJinLogDto();		}			public static class CaiJinLogDtoCursor implements Iterator<CaiJinLogDto>, Iterable<CaiJinLogDto>{				private DBCursor	cursor;			private int pageAll;				public CaiJinLogDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public CaiJinLogDto next() {				DBObject next = cursor.next();				CaiJinLogDto dto = new CaiJinLogDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void skip(int skip) {				cursor.skip(skip);			}						public void limit(int limit) {				cursor.limit(limit);			}						/**			 * 分页, page从1开始 countOfEveryPage必须大于0			 */			public void page(int page, int countOfEveryPage) {				if(countOfEveryPage <= 0) {					throw new RuntimeException("countOfEveryPage must > 0");				}				int count = getCount();				pageAll = count / countOfEveryPage;				if(count % countOfEveryPage != 0) {					pageAll ++;				}								if(page > pageAll)					page = pageAll;								if(page < 1)					page = 1;									int skip = (page - 1) * countOfEveryPage ;				skip(skip);				limit(countOfEveryPage);			}						public int getPageAll() {				return pageAll;			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<CaiJinLogDto> iterator() {				return this;			}		}	}
		public static class CoinLogDao {			private DBCollection	collection;			public CoinLogDao(DBCollection collection) {			this.collection = collection;		}			public void save(CoinLogDto u) {			collection.save(u.toObject());		}			public void delete(CoinLogDto u) {			delete(u.getId());		}			public void delete(String id) {			collection.remove(key(id));		}			public CoinLogDto get(String id) {			DBObject o = collection.findOne(key(id));			if(o == null) {				return null;			}			CoinLogDto x = new CoinLogDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String id) {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);			return o;		}			public CoinLogDtoCursor find() {			return new CoinLogDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public CoinLogDtoCursor findById(String id) {			collection.ensureIndex("id");			BasicDBObject o = new BasicDBObject("id", id);			return new CoinLogDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public CoinLogDtoCursor findByIdFuzzy(String id) {			collection.ensureIndex("id");			id = id.replaceAll("\\*", ".*");			id = "^" + id + "$";			BasicDBObject o = new BasicDBObject("id", Pattern.compile(id, Pattern.CASE_INSENSITIVE));			return new CoinLogDtoCursor(collection.find(o));		}
		public CoinLogDtoCursor findByTime(String time) {						BasicDBObject o = new BasicDBObject("time", time);			return new CoinLogDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public CoinLogDtoCursor findByTimeFuzzy(String time) {						time = time.replaceAll("\\*", ".*");			time = "^" + time + "$";			BasicDBObject o = new BasicDBObject("time", Pattern.compile(time, Pattern.CASE_INSENSITIVE));			return new CoinLogDtoCursor(collection.find(o));		}
		public CoinLogDtoCursor findByFrom(String from) {						BasicDBObject o = new BasicDBObject("from", from);			return new CoinLogDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public CoinLogDtoCursor findByFromFuzzy(String from) {						from = from.replaceAll("\\*", ".*");			from = "^" + from + "$";			BasicDBObject o = new BasicDBObject("from", Pattern.compile(from, Pattern.CASE_INSENSITIVE));			return new CoinLogDtoCursor(collection.find(o));		}
		public CoinLogDtoCursor findByTo(String to) {						BasicDBObject o = new BasicDBObject("to", to);			return new CoinLogDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public CoinLogDtoCursor findByToFuzzy(String to) {						to = to.replaceAll("\\*", ".*");			to = "^" + to + "$";			BasicDBObject o = new BasicDBObject("to", Pattern.compile(to, Pattern.CASE_INSENSITIVE));			return new CoinLogDtoCursor(collection.find(o));		}
		public CoinLogDtoCursor findByFromTo(String fromTo) {						BasicDBObject o = new BasicDBObject("fromTo", fromTo);			return new CoinLogDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public CoinLogDtoCursor findByFromToFuzzy(String fromTo) {						fromTo = fromTo.replaceAll("\\*", ".*");			fromTo = "^" + fromTo + "$";			BasicDBObject o = new BasicDBObject("fromTo", Pattern.compile(fromTo, Pattern.CASE_INSENSITIVE));			return new CoinLogDtoCursor(collection.find(o));		}
		public CoinLogDtoCursor findByCoin(long coin) {						BasicDBObject o = new BasicDBObject("coin", coin);			return new CoinLogDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public CoinLogDtoCursor findCoinBetween(long min, long max) {						BasicDBObject o = new BasicDBObject();			o.put("coin", new BasicDBObject("$gte", min).append("$lte", max));			return new CoinLogDtoCursor(collection.find(o));		}
		public CoinLogDtoCursor findByDsc(String dsc) {						BasicDBObject o = new BasicDBObject("dsc", dsc);			return new CoinLogDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public CoinLogDtoCursor findByDscFuzzy(String dsc) {						dsc = dsc.replaceAll("\\*", ".*");			dsc = "^" + dsc + "$";			BasicDBObject o = new BasicDBObject("dsc", Pattern.compile(dsc, Pattern.CASE_INSENSITIVE));			return new CoinLogDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public CoinLogDto createDTO() {			return new CoinLogDto();		}			public static class CoinLogDtoCursor implements Iterator<CoinLogDto>, Iterable<CoinLogDto>{				private DBCursor	cursor;			private int pageAll;				public CoinLogDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public CoinLogDto next() {				DBObject next = cursor.next();				CoinLogDto dto = new CoinLogDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void skip(int skip) {				cursor.skip(skip);			}						public void limit(int limit) {				cursor.limit(limit);			}						/**			 * 分页, page从1开始 countOfEveryPage必须大于0			 */			public void page(int page, int countOfEveryPage) {				if(countOfEveryPage <= 0) {					throw new RuntimeException("countOfEveryPage must > 0");				}				int count = getCount();				pageAll = count / countOfEveryPage;				if(count % countOfEveryPage != 0) {					pageAll ++;				}								if(page > pageAll)					page = pageAll;								if(page < 1)					page = 1;									int skip = (page - 1) * countOfEveryPage ;				skip(skip);				limit(countOfEveryPage);			}						public int getPageAll() {				return pageAll;			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<CoinLogDto> iterator() {				return this;			}		}	}
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
		public RoleDtoCursor findByCoin(long coin) {						BasicDBObject o = new BasicDBObject("coin", coin);			return new RoleDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public RoleDtoCursor findCoinBetween(long min, long max) {						BasicDBObject o = new BasicDBObject();			o.put("coin", new BasicDBObject("$gte", min).append("$lte", max));			return new RoleDtoCursor(collection.find(o));		}
		public RoleDtoCursor findByJiangQuan(long jiangQuan) {						BasicDBObject o = new BasicDBObject("jiangQuan", jiangQuan);			return new RoleDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public RoleDtoCursor findJiangQuanBetween(long min, long max) {						BasicDBObject o = new BasicDBObject();			o.put("jiangQuan", new BasicDBObject("$gte", min).append("$lte", max));			return new RoleDtoCursor(collection.find(o));		}
		public RoleDtoCursor findByBankPassword(String bankPassword) {						BasicDBObject o = new BasicDBObject("bankPassword", bankPassword);			return new RoleDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public RoleDtoCursor findByBankPasswordFuzzy(String bankPassword) {						bankPassword = bankPassword.replaceAll("\\*", ".*");			bankPassword = "^" + bankPassword + "$";			BasicDBObject o = new BasicDBObject("bankPassword", Pattern.compile(bankPassword, Pattern.CASE_INSENSITIVE));			return new RoleDtoCursor(collection.find(o));		}
		public RoleDtoCursor findByCreateTime(long createTime) {						BasicDBObject o = new BasicDBObject("createTime", createTime);			return new RoleDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public RoleDtoCursor findCreateTimeBetween(long min, long max) {						BasicDBObject o = new BasicDBObject();			o.put("createTime", new BasicDBObject("$gte", min).append("$lte", max));			return new RoleDtoCursor(collection.find(o));		}
		public RoleDtoCursor findByBankCoin(long bankCoin) {						BasicDBObject o = new BasicDBObject("bankCoin", bankCoin);			return new RoleDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public RoleDtoCursor findBankCoinBetween(long min, long max) {						BasicDBObject o = new BasicDBObject();			o.put("bankCoin", new BasicDBObject("$gte", min).append("$lte", max));			return new RoleDtoCursor(collection.find(o));		}
		public RoleDtoCursor findByRechargeHistory(long rechargeHistory) {						BasicDBObject o = new BasicDBObject("rechargeHistory", rechargeHistory);			return new RoleDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public RoleDtoCursor findRechargeHistoryBetween(long min, long max) {						BasicDBObject o = new BasicDBObject();			o.put("rechargeHistory", new BasicDBObject("$gte", min).append("$lte", max));			return new RoleDtoCursor(collection.find(o));		}
		public RoleDtoCursor findByHasJinYan(boolean hasJinYan) {						BasicDBObject o = new BasicDBObject("hasJinYan", hasJinYan);			return new RoleDtoCursor(collection.find(o));		}
		public RoleDtoCursor findByIsOnline(boolean isOnline) {						BasicDBObject o = new BasicDBObject("isOnline", isOnline);			return new RoleDtoCursor(collection.find(o));		}
		public RoleDtoCursor findByHasFengHao(boolean hasFengHao) {						BasicDBObject o = new BasicDBObject("hasFengHao", hasFengHao);			return new RoleDtoCursor(collection.find(o));		}
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
		public YiJieRechargeLogDtoCursor findByJiangQuan(long jiangQuan) {						BasicDBObject o = new BasicDBObject("jiangQuan", jiangQuan);			return new YiJieRechargeLogDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public YiJieRechargeLogDtoCursor findJiangQuanBetween(long min, long max) {						BasicDBObject o = new BasicDBObject();			o.put("jiangQuan", new BasicDBObject("$gte", min).append("$lte", max));			return new YiJieRechargeLogDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public YiJieRechargeLogDto createDTO() {			return new YiJieRechargeLogDto();		}			public static class YiJieRechargeLogDtoCursor implements Iterator<YiJieRechargeLogDto>, Iterable<YiJieRechargeLogDto>{				private DBCursor	cursor;			private int pageAll;				public YiJieRechargeLogDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public YiJieRechargeLogDto next() {				DBObject next = cursor.next();				YiJieRechargeLogDto dto = new YiJieRechargeLogDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void skip(int skip) {				cursor.skip(skip);			}						public void limit(int limit) {				cursor.limit(limit);			}						/**			 * 分页, page从1开始 countOfEveryPage必须大于0			 */			public void page(int page, int countOfEveryPage) {				if(countOfEveryPage <= 0) {					throw new RuntimeException("countOfEveryPage must > 0");				}				int count = getCount();				pageAll = count / countOfEveryPage;				if(count % countOfEveryPage != 0) {					pageAll ++;				}								if(page > pageAll)					page = pageAll;								if(page < 1)					page = 1;									int skip = (page - 1) * countOfEveryPage ;				skip(skip);				limit(countOfEveryPage);			}						public int getPageAll() {				return pageAll;			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<YiJieRechargeLogDto> iterator() {				return this;			}		}	}
		public static class ZfbOrderDao {			private DBCollection	collection;			public ZfbOrderDao(DBCollection collection) {			this.collection = collection;		}			public void save(ZfbOrderDto u) {			collection.save(u.toObject());		}			public void delete(ZfbOrderDto u) {			delete(u.getId());		}			public void delete(String id) {			collection.remove(key(id));		}			public ZfbOrderDto get(String id) {			DBObject o = collection.findOne(key(id));			if(o == null) {				return null;			}			ZfbOrderDto x = new ZfbOrderDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String id) {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);			return o;		}			public ZfbOrderDtoCursor find() {			return new ZfbOrderDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public ZfbOrderDtoCursor findById(String id) {			collection.ensureIndex("id");			BasicDBObject o = new BasicDBObject("id", id);			return new ZfbOrderDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public ZfbOrderDtoCursor findByIdFuzzy(String id) {			collection.ensureIndex("id");			id = id.replaceAll("\\*", ".*");			id = "^" + id + "$";			BasicDBObject o = new BasicDBObject("id", Pattern.compile(id, Pattern.CASE_INSENSITIVE));			return new ZfbOrderDtoCursor(collection.find(o));		}
		public ZfbOrderDtoCursor findByCount(int count) {						BasicDBObject o = new BasicDBObject("count", count);			return new ZfbOrderDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public ZfbOrderDtoCursor findCountBetween(int min, int max) {						BasicDBObject o = new BasicDBObject();			o.put("count", new BasicDBObject("$gte", min).append("$lte", max));			return new ZfbOrderDtoCursor(collection.find(o));		}
		public ZfbOrderDtoCursor findByPrice(String price) {						BasicDBObject o = new BasicDBObject("price", price);			return new ZfbOrderDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public ZfbOrderDtoCursor findByPriceFuzzy(String price) {						price = price.replaceAll("\\*", ".*");			price = "^" + price + "$";			BasicDBObject o = new BasicDBObject("price", Pattern.compile(price, Pattern.CASE_INSENSITIVE));			return new ZfbOrderDtoCursor(collection.find(o));		}
		public ZfbOrderDtoCursor findByTime(long time) {						BasicDBObject o = new BasicDBObject("time", time);			return new ZfbOrderDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public ZfbOrderDtoCursor findTimeBetween(long min, long max) {						BasicDBObject o = new BasicDBObject();			o.put("time", new BasicDBObject("$gte", min).append("$lte", max));			return new ZfbOrderDtoCursor(collection.find(o));		}
		public ZfbOrderDtoCursor findByRoleId(String roleId) {						BasicDBObject o = new BasicDBObject("roleId", roleId);			return new ZfbOrderDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public ZfbOrderDtoCursor findByRoleIdFuzzy(String roleId) {						roleId = roleId.replaceAll("\\*", ".*");			roleId = "^" + roleId + "$";			BasicDBObject o = new BasicDBObject("roleId", Pattern.compile(roleId, Pattern.CASE_INSENSITIVE));			return new ZfbOrderDtoCursor(collection.find(o));		}
		public ZfbOrderDtoCursor findByJiangQuan(long jiangQuan) {						BasicDBObject o = new BasicDBObject("jiangQuan", jiangQuan);			return new ZfbOrderDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public ZfbOrderDtoCursor findJiangQuanBetween(long min, long max) {						BasicDBObject o = new BasicDBObject();			o.put("jiangQuan", new BasicDBObject("$gte", min).append("$lte", max));			return new ZfbOrderDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public ZfbOrderDto createDTO() {			return new ZfbOrderDto();		}			public static class ZfbOrderDtoCursor implements Iterator<ZfbOrderDto>, Iterable<ZfbOrderDto>{				private DBCursor	cursor;			private int pageAll;				public ZfbOrderDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public ZfbOrderDto next() {				DBObject next = cursor.next();				ZfbOrderDto dto = new ZfbOrderDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void skip(int skip) {				cursor.skip(skip);			}						public void limit(int limit) {				cursor.limit(limit);			}						/**			 * 分页, page从1开始 countOfEveryPage必须大于0			 */			public void page(int page, int countOfEveryPage) {				if(countOfEveryPage <= 0) {					throw new RuntimeException("countOfEveryPage must > 0");				}				int count = getCount();				pageAll = count / countOfEveryPage;				if(count % countOfEveryPage != 0) {					pageAll ++;				}								if(page > pageAll)					page = pageAll;								if(page < 1)					page = 1;									int skip = (page - 1) * countOfEveryPage ;				skip(skip);				limit(countOfEveryPage);			}						public int getPageAll() {				return pageAll;			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<ZfbOrderDto> iterator() {				return this;			}		}	}
		public static class ZfbOrderFinishDao {			private DBCollection	collection;			public ZfbOrderFinishDao(DBCollection collection) {			this.collection = collection;		}			public void save(ZfbOrderFinishDto u) {			collection.save(u.toObject());		}			public void delete(ZfbOrderFinishDto u) {			delete(u.getId());		}			public void delete(String id) {			collection.remove(key(id));		}			public ZfbOrderFinishDto get(String id) {			DBObject o = collection.findOne(key(id));			if(o == null) {				return null;			}			ZfbOrderFinishDto x = new ZfbOrderFinishDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String id) {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);			return o;		}			public ZfbOrderFinishDtoCursor find() {			return new ZfbOrderFinishDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public ZfbOrderFinishDtoCursor findById(String id) {			collection.ensureIndex("id");			BasicDBObject o = new BasicDBObject("id", id);			return new ZfbOrderFinishDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public ZfbOrderFinishDtoCursor findByIdFuzzy(String id) {			collection.ensureIndex("id");			id = id.replaceAll("\\*", ".*");			id = "^" + id + "$";			BasicDBObject o = new BasicDBObject("id", Pattern.compile(id, Pattern.CASE_INSENSITIVE));			return new ZfbOrderFinishDtoCursor(collection.find(o));		}
		public ZfbOrderFinishDtoCursor findByCount(int count) {						BasicDBObject o = new BasicDBObject("count", count);			return new ZfbOrderFinishDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public ZfbOrderFinishDtoCursor findCountBetween(int min, int max) {						BasicDBObject o = new BasicDBObject();			o.put("count", new BasicDBObject("$gte", min).append("$lte", max));			return new ZfbOrderFinishDtoCursor(collection.find(o));		}
		public ZfbOrderFinishDtoCursor findByPrice(String price) {						BasicDBObject o = new BasicDBObject("price", price);			return new ZfbOrderFinishDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public ZfbOrderFinishDtoCursor findByPriceFuzzy(String price) {						price = price.replaceAll("\\*", ".*");			price = "^" + price + "$";			BasicDBObject o = new BasicDBObject("price", Pattern.compile(price, Pattern.CASE_INSENSITIVE));			return new ZfbOrderFinishDtoCursor(collection.find(o));		}
		public ZfbOrderFinishDtoCursor findByTime(long time) {						BasicDBObject o = new BasicDBObject("time", time);			return new ZfbOrderFinishDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public ZfbOrderFinishDtoCursor findTimeBetween(long min, long max) {						BasicDBObject o = new BasicDBObject();			o.put("time", new BasicDBObject("$gte", min).append("$lte", max));			return new ZfbOrderFinishDtoCursor(collection.find(o));		}
		public ZfbOrderFinishDtoCursor findByUserId(String userId) {						BasicDBObject o = new BasicDBObject("userId", userId);			return new ZfbOrderFinishDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public ZfbOrderFinishDtoCursor findByUserIdFuzzy(String userId) {						userId = userId.replaceAll("\\*", ".*");			userId = "^" + userId + "$";			BasicDBObject o = new BasicDBObject("userId", Pattern.compile(userId, Pattern.CASE_INSENSITIVE));			return new ZfbOrderFinishDtoCursor(collection.find(o));		}
		public ZfbOrderFinishDtoCursor findByJiangQuan(long jiangQuan) {						BasicDBObject o = new BasicDBObject("jiangQuan", jiangQuan);			return new ZfbOrderFinishDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public ZfbOrderFinishDtoCursor findJiangQuanBetween(long min, long max) {						BasicDBObject o = new BasicDBObject();			o.put("jiangQuan", new BasicDBObject("$gte", min).append("$lte", max));			return new ZfbOrderFinishDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public ZfbOrderFinishDto createDTO() {			return new ZfbOrderFinishDto();		}			public static class ZfbOrderFinishDtoCursor implements Iterator<ZfbOrderFinishDto>, Iterable<ZfbOrderFinishDto>{				private DBCursor	cursor;			private int pageAll;				public ZfbOrderFinishDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public ZfbOrderFinishDto next() {				DBObject next = cursor.next();				ZfbOrderFinishDto dto = new ZfbOrderFinishDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void skip(int skip) {				cursor.skip(skip);			}						public void limit(int limit) {				cursor.limit(limit);			}						/**			 * 分页, page从1开始 countOfEveryPage必须大于0			 */			public void page(int page, int countOfEveryPage) {				if(countOfEveryPage <= 0) {					throw new RuntimeException("countOfEveryPage must > 0");				}				int count = getCount();				pageAll = count / countOfEveryPage;				if(count % countOfEveryPage != 0) {					pageAll ++;				}								if(page > pageAll)					page = pageAll;								if(page < 1)					page = 1;									int skip = (page - 1) * countOfEveryPage ;				skip(skip);				limit(countOfEveryPage);			}						public int getPageAll() {				return pageAll;			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<ZfbOrderFinishDto> iterator() {				return this;			}		}	}
		public static class ZhuangDao {			private DBCollection	collection;			public ZhuangDao(DBCollection collection) {			this.collection = collection;		}			public void save(ZhuangDto u) {			collection.save(u.toObject());		}			public void delete(ZhuangDto u) {			delete(u.getId());		}			public void delete(String id) {			collection.remove(key(id));		}			public ZhuangDto get(String id) {			DBObject o = collection.findOne(key(id));			if(o == null) {				return null;			}			ZhuangDto x = new ZhuangDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String id) {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);			return o;		}			public ZhuangDtoCursor find() {			return new ZhuangDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public ZhuangDtoCursor findById(String id) {			collection.ensureIndex("id");			BasicDBObject o = new BasicDBObject("id", id);			return new ZhuangDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public ZhuangDtoCursor findByIdFuzzy(String id) {			collection.ensureIndex("id");			id = id.replaceAll("\\*", ".*");			id = "^" + id + "$";			BasicDBObject o = new BasicDBObject("id", Pattern.compile(id, Pattern.CASE_INSENSITIVE));			return new ZhuangDtoCursor(collection.find(o));		}
		public ZhuangDtoCursor findByTime(long time) {						BasicDBObject o = new BasicDBObject("time", time);			return new ZhuangDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public ZhuangDtoCursor findTimeBetween(long min, long max) {						BasicDBObject o = new BasicDBObject();			o.put("time", new BasicDBObject("$gte", min).append("$lte", max));			return new ZhuangDtoCursor(collection.find(o));		}
		public ZhuangDtoCursor findByIsZhuang(boolean isZhuang) {						BasicDBObject o = new BasicDBObject("isZhuang", isZhuang);			return new ZhuangDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public ZhuangDto createDTO() {			return new ZhuangDto();		}			public static class ZhuangDtoCursor implements Iterator<ZhuangDto>, Iterable<ZhuangDto>{				private DBCursor	cursor;			private int pageAll;				public ZhuangDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public ZhuangDto next() {				DBObject next = cursor.next();				ZhuangDto dto = new ZhuangDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void skip(int skip) {				cursor.skip(skip);			}						public void limit(int limit) {				cursor.limit(limit);			}						/**			 * 分页, page从1开始 countOfEveryPage必须大于0			 */			public void page(int page, int countOfEveryPage) {				if(countOfEveryPage <= 0) {					throw new RuntimeException("countOfEveryPage must > 0");				}				int count = getCount();				pageAll = count / countOfEveryPage;				if(count % countOfEveryPage != 0) {					pageAll ++;				}								if(page > pageAll)					page = pageAll;								if(page < 1)					page = 1;									int skip = (page - 1) * countOfEveryPage ;				skip(skip);				limit(countOfEveryPage);			}						public int getPageAll() {				return pageAll;			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<ZhuangDto> iterator() {				return this;			}		}	}
		public static class AppStoreRechargeLogDto implements MongoDto{		private String id = "";
		private String productId = "";
		private int fee = 0;
		private String roleId = "";
		private String nick = "";
		private long coin = 0;
		private long jiangQuan = 0;
		public AppStoreRechargeLogDto() {		}				/**		 * Copy new one		 */		public AppStoreRechargeLogDto(AppStoreRechargeLogDto src) {			id = MongoGen.copy(src.id);			
			productId = MongoGen.copy(src.productId);			
			fee = MongoGen.copy(src.fee);			
			roleId = MongoGen.copy(src.roleId);			
			nick = MongoGen.copy(src.nick);			
			coin = MongoGen.copy(src.coin);			
			jiangQuan = MongoGen.copy(src.jiangQuan);			
		}		public String getId() {			return this.id;		}
		public String getProductId() {			return this.productId;		}
		public int getFee() {			return this.fee;		}
		public String getRoleId() {			return this.roleId;		}
		public String getNick() {			return this.nick;		}
		public long getCoin() {			return this.coin;		}
		public long getJiangQuan() {			return this.jiangQuan;		}
		public void setId(String id) {			this.id = id;		}
		public void setProductId(String productId) {			this.productId = productId;		}
		public void setFee(int fee) {			this.fee = fee;		}
		public void setRoleId(String roleId) {			this.roleId = roleId;		}
		public void setNick(String nick) {			this.nick = nick;		}
		public void setCoin(long coin) {			this.coin = coin;		}
		public void setJiangQuan(long jiangQuan) {			this.jiangQuan = jiangQuan;		}
		@Override		public DBObject toObject() {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);
			o.put("id", MongoGen.toObject(id));			
			o.put("productId", MongoGen.toObject(productId));			
			o.put("fee", MongoGen.toObject(fee));			
			o.put("roleId", MongoGen.toObject(roleId));			
			o.put("nick", MongoGen.toObject(nick));			
			o.put("coin", MongoGen.toObject(coin));			
			o.put("jiangQuan", MongoGen.toObject(jiangQuan));			
			return o;		}		@Override		public void fromDBObject(DBObject o) {			id = getString(o, "id");
			productId = getString(o, "productId");
			fee = getInteger(o, "fee");
			roleId = getString(o, "roleId");
			nick = getString(o, "nick");
			coin = getLong(o, "coin");
			jiangQuan = getLong(o, "jiangQuan");
		}













	static MongoMap<AppStoreRechargeLogDto> copy(MongoMap<AppStoreRechargeLogDto> map) {		MongoMapImpl<AppStoreRechargeLogDto> m = new MongoMapImpl<AppStoreRechargeLogDto>();				for (String key : map.keySet()) {			AppStoreRechargeLogDto v = map.get(key);			m.put(key, MongoGen.copy(v));		}		return m;	}			static List<AppStoreRechargeLogDto> copy(List<AppStoreRechargeLogDto> list) {		List<AppStoreRechargeLogDto> ls = Lists.newArrayList();		for (AppStoreRechargeLogDto t : list) {			ls.add(MongoGen.copy(t));		}		return ls;	}		@Override		public String toString() {			return toObject().toString();		}	}
		public static class CaiJinLogDto implements MongoDto{		private String id = "";
		private String roleId = "";
		private String nick = "";
		private long caiJin = 0;
		private boolean isSmall = false;
		public CaiJinLogDto() {		}				/**		 * Copy new one		 */		public CaiJinLogDto(CaiJinLogDto src) {			id = MongoGen.copy(src.id);			
			roleId = MongoGen.copy(src.roleId);			
			nick = MongoGen.copy(src.nick);			
			caiJin = MongoGen.copy(src.caiJin);			
			isSmall = MongoGen.copy(src.isSmall);			
		}		public String getId() {			return this.id;		}
		public String getRoleId() {			return this.roleId;		}
		public String getNick() {			return this.nick;		}
		public long getCaiJin() {			return this.caiJin;		}
		public boolean getIsSmall() {			return this.isSmall;		}
		public void setId(String id) {			this.id = id;		}
		public void setRoleId(String roleId) {			this.roleId = roleId;		}
		public void setNick(String nick) {			this.nick = nick;		}
		public void setCaiJin(long caiJin) {			this.caiJin = caiJin;		}
		public void setIsSmall(boolean isSmall) {			this.isSmall = isSmall;		}
		@Override		public DBObject toObject() {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);
			o.put("id", MongoGen.toObject(id));			
			o.put("roleId", MongoGen.toObject(roleId));			
			o.put("nick", MongoGen.toObject(nick));			
			o.put("caiJin", MongoGen.toObject(caiJin));			
			o.put("isSmall", MongoGen.toObject(isSmall));			
			return o;		}		@Override		public void fromDBObject(DBObject o) {			id = getString(o, "id");
			roleId = getString(o, "roleId");
			nick = getString(o, "nick");
			caiJin = getLong(o, "caiJin");
			isSmall = getBoolean(o, "isSmall");
		}









	static MongoMap<CaiJinLogDto> copy(MongoMap<CaiJinLogDto> map) {		MongoMapImpl<CaiJinLogDto> m = new MongoMapImpl<CaiJinLogDto>();				for (String key : map.keySet()) {			CaiJinLogDto v = map.get(key);			m.put(key, MongoGen.copy(v));		}		return m;	}			static List<CaiJinLogDto> copy(List<CaiJinLogDto> list) {		List<CaiJinLogDto> ls = Lists.newArrayList();		for (CaiJinLogDto t : list) {			ls.add(MongoGen.copy(t));		}		return ls;	}		@Override		public String toString() {			return toObject().toString();		}	}
		public static class CoinLogDto implements MongoDto{		private String id = "";
		private String time = "";
		private String from = "";
		private String to = "";
		private String fromTo = "";
		private long coin = 0;
		private String dsc = "";
		public CoinLogDto() {		}				/**		 * Copy new one		 */		public CoinLogDto(CoinLogDto src) {			id = MongoGen.copy(src.id);			
			time = MongoGen.copy(src.time);			
			from = MongoGen.copy(src.from);			
			to = MongoGen.copy(src.to);			
			fromTo = MongoGen.copy(src.fromTo);			
			coin = MongoGen.copy(src.coin);			
			dsc = MongoGen.copy(src.dsc);			
		}		public String getId() {			return this.id;		}
		public String getTime() {			return this.time;		}
		public String getFrom() {			return this.from;		}
		public String getTo() {			return this.to;		}
		public String getFromTo() {			return this.fromTo;		}
		public long getCoin() {			return this.coin;		}
		public String getDsc() {			return this.dsc;		}
		public void setId(String id) {			this.id = id;		}
		public void setTime(String time) {			this.time = time;		}
		public void setFrom(String from) {			this.from = from;		}
		public void setTo(String to) {			this.to = to;		}
		public void setFromTo(String fromTo) {			this.fromTo = fromTo;		}
		public void setCoin(long coin) {			this.coin = coin;		}
		public void setDsc(String dsc) {			this.dsc = dsc;		}
		@Override		public DBObject toObject() {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);
			o.put("id", MongoGen.toObject(id));			
			o.put("time", MongoGen.toObject(time));			
			o.put("from", MongoGen.toObject(from));			
			o.put("to", MongoGen.toObject(to));			
			o.put("fromTo", MongoGen.toObject(fromTo));			
			o.put("coin", MongoGen.toObject(coin));			
			o.put("dsc", MongoGen.toObject(dsc));			
			return o;		}		@Override		public void fromDBObject(DBObject o) {			id = getString(o, "id");
			time = getString(o, "time");
			from = getString(o, "from");
			to = getString(o, "to");
			fromTo = getString(o, "fromTo");
			coin = getLong(o, "coin");
			dsc = getString(o, "dsc");
		}













	static MongoMap<CoinLogDto> copy(MongoMap<CoinLogDto> map) {		MongoMapImpl<CoinLogDto> m = new MongoMapImpl<CoinLogDto>();				for (String key : map.keySet()) {			CoinLogDto v = map.get(key);			m.put(key, MongoGen.copy(v));		}		return m;	}			static List<CoinLogDto> copy(List<CoinLogDto> list) {		List<CoinLogDto> ls = Lists.newArrayList();		for (CoinLogDto t : list) {			ls.add(MongoGen.copy(t));		}		return ls;	}		@Override		public String toString() {			return toObject().toString();		}	}
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
		public static class RoleDto implements MongoDto{		private String id = "";
		private String ownerId = "";
		private boolean isRobot = false;
		private String nick = "";
		private long coin = 0;
		private long jiangQuan = 0;
		private String bankPassword = "";
		private long createTime = 0;
		private long bankCoin = 0;
		private long rechargeHistory = 0;
		private boolean hasJinYan = false;
		private boolean isOnline = false;
		private boolean hasFengHao = false;
		private MongoMap<String> keyValueDaily = Maps.newMongoMap();
		private MongoMap<String> keyValueForever = Maps.newMongoMap();
		public RoleDto() {		}				/**		 * Copy new one		 */		public RoleDto(RoleDto src) {			id = MongoGen.copy(src.id);			
			ownerId = MongoGen.copy(src.ownerId);			
			isRobot = MongoGen.copy(src.isRobot);			
			nick = MongoGen.copy(src.nick);			
			coin = MongoGen.copy(src.coin);			
			jiangQuan = MongoGen.copy(src.jiangQuan);			
			bankPassword = MongoGen.copy(src.bankPassword);			
			createTime = MongoGen.copy(src.createTime);			
			bankCoin = MongoGen.copy(src.bankCoin);			
			rechargeHistory = MongoGen.copy(src.rechargeHistory);			
			hasJinYan = MongoGen.copy(src.hasJinYan);			
			isOnline = MongoGen.copy(src.isOnline);			
			hasFengHao = MongoGen.copy(src.hasFengHao);			
			keyValueDaily = MongoGen.copyString(src.keyValueDaily);			
			keyValueForever = MongoGen.copyString(src.keyValueForever);			
		}		public String getId() {			return this.id;		}
		public String getOwnerId() {			return this.ownerId;		}
		public boolean getIsRobot() {			return this.isRobot;		}
		public String getNick() {			return this.nick;		}
		public long getCoin() {			return this.coin;		}
		public long getJiangQuan() {			return this.jiangQuan;		}
		public String getBankPassword() {			return this.bankPassword;		}
		public long getCreateTime() {			return this.createTime;		}
		public long getBankCoin() {			return this.bankCoin;		}
		public long getRechargeHistory() {			return this.rechargeHistory;		}
		public boolean getHasJinYan() {			return this.hasJinYan;		}
		public boolean getIsOnline() {			return this.isOnline;		}
		public boolean getHasFengHao() {			return this.hasFengHao;		}
		public MongoMap<String> getKeyValueDaily() {			return this.keyValueDaily;		}
		public MongoMap<String> getKeyValueForever() {			return this.keyValueForever;		}
		public void setId(String id) {			this.id = id;		}
		public void setOwnerId(String ownerId) {			this.ownerId = ownerId;		}
		public void setIsRobot(boolean isRobot) {			this.isRobot = isRobot;		}
		public void setNick(String nick) {			this.nick = nick;		}
		public void setCoin(long coin) {			this.coin = coin;		}
		public void setJiangQuan(long jiangQuan) {			this.jiangQuan = jiangQuan;		}
		public void setBankPassword(String bankPassword) {			this.bankPassword = bankPassword;		}
		public void setCreateTime(long createTime) {			this.createTime = createTime;		}
		public void setBankCoin(long bankCoin) {			this.bankCoin = bankCoin;		}
		public void setRechargeHistory(long rechargeHistory) {			this.rechargeHistory = rechargeHistory;		}
		public void setHasJinYan(boolean hasJinYan) {			this.hasJinYan = hasJinYan;		}
		public void setIsOnline(boolean isOnline) {			this.isOnline = isOnline;		}
		public void setHasFengHao(boolean hasFengHao) {			this.hasFengHao = hasFengHao;		}
		public void setKeyValueDaily(MongoMap<String> keyValueDaily) {			this.keyValueDaily = keyValueDaily;		}
		public void setKeyValueForever(MongoMap<String> keyValueForever) {			this.keyValueForever = keyValueForever;		}
		@Override		public DBObject toObject() {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);
			o.put("id", MongoGen.toObject(id));			
			o.put("ownerId", MongoGen.toObject(ownerId));			
			o.put("isRobot", MongoGen.toObject(isRobot));			
			o.put("nick", MongoGen.toObject(nick));			
			o.put("coin", MongoGen.toObject(coin));			
			o.put("jiangQuan", MongoGen.toObject(jiangQuan));			
			o.put("bankPassword", MongoGen.toObject(bankPassword));			
			o.put("createTime", MongoGen.toObject(createTime));			
			o.put("bankCoin", MongoGen.toObject(bankCoin));			
			o.put("rechargeHistory", MongoGen.toObject(rechargeHistory));			
			o.put("hasJinYan", MongoGen.toObject(hasJinYan));			
			o.put("isOnline", MongoGen.toObject(isOnline));			
			o.put("hasFengHao", MongoGen.toObject(hasFengHao));			
			o.put("keyValueDaily", MongoGen.toObjectString(keyValueDaily));
			o.put("keyValueForever", MongoGen.toObjectString(keyValueForever));
			return o;		}		@Override		public void fromDBObject(DBObject o) {			id = getString(o, "id");
			ownerId = getString(o, "ownerId");
			isRobot = getBoolean(o, "isRobot");
			nick = getString(o, "nick");
			coin = getLong(o, "coin");
			jiangQuan = getLong(o, "jiangQuan");
			bankPassword = getString(o, "bankPassword");
			createTime = getLong(o, "createTime");
			bankCoin = getLong(o, "bankCoin");
			rechargeHistory = getLong(o, "rechargeHistory");
			hasJinYan = getBoolean(o, "hasJinYan");
			isOnline = getBoolean(o, "isOnline");
			hasFengHao = getBoolean(o, "hasFengHao");
			keyValueDaily = loadKeyValueDaily(o);
			keyValueForever = loadKeyValueForever(o);
		}












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
		private long jiangQuan = 0;
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
			jiangQuan = MongoGen.copy(src.jiangQuan);			
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
		public long getJiangQuan() {			return this.jiangQuan;		}
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
		public void setJiangQuan(long jiangQuan) {			this.jiangQuan = jiangQuan;		}
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
			o.put("jiangQuan", MongoGen.toObject(jiangQuan));			
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
			jiangQuan = getLong(o, "jiangQuan");
		}































	static MongoMap<YiJieRechargeLogDto> copy(MongoMap<YiJieRechargeLogDto> map) {		MongoMapImpl<YiJieRechargeLogDto> m = new MongoMapImpl<YiJieRechargeLogDto>();				for (String key : map.keySet()) {			YiJieRechargeLogDto v = map.get(key);			m.put(key, MongoGen.copy(v));		}		return m;	}			static List<YiJieRechargeLogDto> copy(List<YiJieRechargeLogDto> list) {		List<YiJieRechargeLogDto> ls = Lists.newArrayList();		for (YiJieRechargeLogDto t : list) {			ls.add(MongoGen.copy(t));		}		return ls;	}		@Override		public String toString() {			return toObject().toString();		}	}
		public static class ZfbOrderDto implements MongoDto{		private String id = "";
		private int count = 0;
		private String price = "";
		private long time = 0;
		private String roleId = "";
		private long jiangQuan = 0;
		public ZfbOrderDto() {		}				/**		 * Copy new one		 */		public ZfbOrderDto(ZfbOrderDto src) {			id = MongoGen.copy(src.id);			
			count = MongoGen.copy(src.count);			
			price = MongoGen.copy(src.price);			
			time = MongoGen.copy(src.time);			
			roleId = MongoGen.copy(src.roleId);			
			jiangQuan = MongoGen.copy(src.jiangQuan);			
		}		public String getId() {			return this.id;		}
		public int getCount() {			return this.count;		}
		public String getPrice() {			return this.price;		}
		public long getTime() {			return this.time;		}
		public String getRoleId() {			return this.roleId;		}
		public long getJiangQuan() {			return this.jiangQuan;		}
		public void setId(String id) {			this.id = id;		}
		public void setCount(int count) {			this.count = count;		}
		public void setPrice(String price) {			this.price = price;		}
		public void setTime(long time) {			this.time = time;		}
		public void setRoleId(String roleId) {			this.roleId = roleId;		}
		public void setJiangQuan(long jiangQuan) {			this.jiangQuan = jiangQuan;		}
		@Override		public DBObject toObject() {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);
			o.put("id", MongoGen.toObject(id));			
			o.put("count", MongoGen.toObject(count));			
			o.put("price", MongoGen.toObject(price));			
			o.put("time", MongoGen.toObject(time));			
			o.put("roleId", MongoGen.toObject(roleId));			
			o.put("jiangQuan", MongoGen.toObject(jiangQuan));			
			return o;		}		@Override		public void fromDBObject(DBObject o) {			id = getString(o, "id");
			count = getInteger(o, "count");
			price = getString(o, "price");
			time = getLong(o, "time");
			roleId = getString(o, "roleId");
			jiangQuan = getLong(o, "jiangQuan");
		}











	static MongoMap<ZfbOrderDto> copy(MongoMap<ZfbOrderDto> map) {		MongoMapImpl<ZfbOrderDto> m = new MongoMapImpl<ZfbOrderDto>();				for (String key : map.keySet()) {			ZfbOrderDto v = map.get(key);			m.put(key, MongoGen.copy(v));		}		return m;	}			static List<ZfbOrderDto> copy(List<ZfbOrderDto> list) {		List<ZfbOrderDto> ls = Lists.newArrayList();		for (ZfbOrderDto t : list) {			ls.add(MongoGen.copy(t));		}		return ls;	}		@Override		public String toString() {			return toObject().toString();		}	}
		public static class ZfbOrderFinishDto implements MongoDto{		private String id = "";
		private int count = 0;
		private String price = "";
		private long time = 0;
		private String userId = "";
		private long jiangQuan = 0;
		public ZfbOrderFinishDto() {		}				/**		 * Copy new one		 */		public ZfbOrderFinishDto(ZfbOrderFinishDto src) {			id = MongoGen.copy(src.id);			
			count = MongoGen.copy(src.count);			
			price = MongoGen.copy(src.price);			
			time = MongoGen.copy(src.time);			
			userId = MongoGen.copy(src.userId);			
			jiangQuan = MongoGen.copy(src.jiangQuan);			
		}		public String getId() {			return this.id;		}
		public int getCount() {			return this.count;		}
		public String getPrice() {			return this.price;		}
		public long getTime() {			return this.time;		}
		public String getUserId() {			return this.userId;		}
		public long getJiangQuan() {			return this.jiangQuan;		}
		public void setId(String id) {			this.id = id;		}
		public void setCount(int count) {			this.count = count;		}
		public void setPrice(String price) {			this.price = price;		}
		public void setTime(long time) {			this.time = time;		}
		public void setUserId(String userId) {			this.userId = userId;		}
		public void setJiangQuan(long jiangQuan) {			this.jiangQuan = jiangQuan;		}
		@Override		public DBObject toObject() {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);
			o.put("id", MongoGen.toObject(id));			
			o.put("count", MongoGen.toObject(count));			
			o.put("price", MongoGen.toObject(price));			
			o.put("time", MongoGen.toObject(time));			
			o.put("userId", MongoGen.toObject(userId));			
			o.put("jiangQuan", MongoGen.toObject(jiangQuan));			
			return o;		}		@Override		public void fromDBObject(DBObject o) {			id = getString(o, "id");
			count = getInteger(o, "count");
			price = getString(o, "price");
			time = getLong(o, "time");
			userId = getString(o, "userId");
			jiangQuan = getLong(o, "jiangQuan");
		}











	static MongoMap<ZfbOrderFinishDto> copy(MongoMap<ZfbOrderFinishDto> map) {		MongoMapImpl<ZfbOrderFinishDto> m = new MongoMapImpl<ZfbOrderFinishDto>();				for (String key : map.keySet()) {			ZfbOrderFinishDto v = map.get(key);			m.put(key, MongoGen.copy(v));		}		return m;	}			static List<ZfbOrderFinishDto> copy(List<ZfbOrderFinishDto> list) {		List<ZfbOrderFinishDto> ls = Lists.newArrayList();		for (ZfbOrderFinishDto t : list) {			ls.add(MongoGen.copy(t));		}		return ls;	}		@Override		public String toString() {			return toObject().toString();		}	}
		public static class ZhuangDto implements MongoDto{		private String id = "";
		private long time = 0;
		private boolean isZhuang = false;
		public ZhuangDto() {		}				/**		 * Copy new one		 */		public ZhuangDto(ZhuangDto src) {			id = MongoGen.copy(src.id);			
			time = MongoGen.copy(src.time);			
			isZhuang = MongoGen.copy(src.isZhuang);			
		}		public String getId() {			return this.id;		}
		public long getTime() {			return this.time;		}
		public boolean getIsZhuang() {			return this.isZhuang;		}
		public void setId(String id) {			this.id = id;		}
		public void setTime(long time) {			this.time = time;		}
		public void setIsZhuang(boolean isZhuang) {			this.isZhuang = isZhuang;		}
		@Override		public DBObject toObject() {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);
			o.put("id", MongoGen.toObject(id));			
			o.put("time", MongoGen.toObject(time));			
			o.put("isZhuang", MongoGen.toObject(isZhuang));			
			return o;		}		@Override		public void fromDBObject(DBObject o) {			id = getString(o, "id");
			time = getLong(o, "time");
			isZhuang = getBoolean(o, "isZhuang");
		}





	static MongoMap<ZhuangDto> copy(MongoMap<ZhuangDto> map) {		MongoMapImpl<ZhuangDto> m = new MongoMapImpl<ZhuangDto>();				for (String key : map.keySet()) {			ZhuangDto v = map.get(key);			m.put(key, MongoGen.copy(v));		}		return m;	}			static List<ZhuangDto> copy(List<ZhuangDto> list) {		List<ZhuangDto> ls = Lists.newArrayList();		for (ZhuangDto t : list) {			ls.add(MongoGen.copy(t));		}		return ls;	}		@Override		public String toString() {			return toObject().toString();		}	}
}