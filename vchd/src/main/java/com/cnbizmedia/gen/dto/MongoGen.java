package com.cnbizmedia.gen.dto;import java.net.UnknownHostException;import java.util.ArrayList;import java.util.Collection;import java.util.HashMap;import java.util.Iterator;import java.util.List;import java.util.Map;import java.util.Set;import cn.javaplus.exception.UnImplMethodException;import com.mongodb.BasicDBList;import com.mongodb.BasicDBObject;import com.mongodb.DB;import com.mongodb.DBCollection;import com.mongodb.DBCursor;import com.mongodb.DBObject;import com.mongodb.Mongo;import com.mongodb.MongoClient;import java.util.regex.Pattern;public class MongoGen {	public static class Lists {		public static<T> List<T> newArrayList() {			return new ArrayList<T>();		}	}	public static class Maps {		public static<K, V> Map<K, V> newHashMap() {			return new HashMap<K, V>();		}		public static<V> MongoMap<V> newMongoMap() {			return new MongoMapImpl<V>();		}	}			public static interface MongoMap<V> {		V get(String k);		void put(String k, V v);		Set<String> keySet();		Collection<V> values();	}		public static class MongoMapImpl<V> implements MongoMap<V> {		Map<String, V> map = Maps.newHashMap();				@Override		public V get(String k) {			return map.get(k);		}		@Override		public void put(String k, V v) {			map.put(k, v);		}		@Override		public Set<String> keySet() {			return map.keySet();		}		@Override		public Collection<V> values() {			return map.values();		}			}																static BasicDBObject toObjectBytes(MongoMap<byte[]> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			byte[] v = map.get(key);			o.put(key, toObject(v));		}		return o;	}	static BasicDBObject toObjectByte(MongoMap<Byte> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			Byte v = map.get(key);			o.put(key, toObject(v));		}		return o;	}	static BasicDBObject toObjectCharacter(MongoMap<Character> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			Character v = map.get(key);			o.put(key, toObject(v));		}		return o;	}	static BasicDBObject toObjectBoolean(MongoMap<Boolean> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			Boolean v = map.get(key);			o.put(key, toObject(v));		}		return o;	}	static BasicDBObject toObjectShort(MongoMap<Short> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			Short v = map.get(key);			o.put(key, toObject(v));		}		return o;	}	static BasicDBObject toObjectInteger(MongoMap<Integer> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			Integer v = map.get(key);			o.put(key, toObject(v));		}		return o;	}	static BasicDBObject toObjectLong(MongoMap<Long> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			Long v = map.get(key);			o.put(key, toObject(v));		}		return o;	}	static BasicDBObject toObjectFloat(MongoMap<Float> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			Float v = map.get(key);			o.put(key, toObject(v));		}		return o;	}	static BasicDBObject toObjectDouble(MongoMap<Double> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			Double v = map.get(key);			o.put(key, toObject(v));		}		return o;	}	static BasicDBObject toObjectString(MongoMap<String> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			String v = map.get(key);			o.put(key, toObject(v));		}		return o;	}	static BasicDBList toObjectBytes(Collection<byte[]> list) {		BasicDBList prices = new BasicDBList();		for (byte[] o : list) {			prices.add(toObject(o));		}		return prices;	}	static BasicDBList toObjectByte(Collection<Byte> list) {		BasicDBList prices = new BasicDBList();		for (Byte o : list) {			prices.add(toObject(o));		}		return prices;	}	static BasicDBList toObjectCharacter(Collection<Character> list) {		BasicDBList prices = new BasicDBList();		for (Character o : list) {			prices.add(toObject(o));		}		return prices;	}	static BasicDBList toObjectBoolean(Collection<Boolean> list) {		BasicDBList prices = new BasicDBList();		for (Boolean o : list) {			prices.add(toObject(o));		}		return prices;	}	static BasicDBList toObjectShort(Collection<Short> list) {		BasicDBList prices = new BasicDBList();		for (Short o : list) {			prices.add(toObject(o));		}		return prices;	}	static BasicDBList toObjectInteger(Collection<Integer> list) {		BasicDBList prices = new BasicDBList();		for (Integer o : list) {			prices.add(toObject(o));		}		return prices;	}	static BasicDBList toObjectLong(Collection<Long> list) {		BasicDBList prices = new BasicDBList();		for (Long o : list) {			prices.add(toObject(o));		}		return prices;	}	static BasicDBList toObjectFloat(Collection<Float> list) {		BasicDBList prices = new BasicDBList();		for (Float o : list) {			prices.add(toObject(o));		}		return prices;	}	static BasicDBList toObjectDouble(Collection<Double> list) {		BasicDBList prices = new BasicDBList();		for (Double o : list) {			prices.add(toObject(o));		}		return prices;	}	static BasicDBList toObjectString(Collection<String> list) {		BasicDBList prices = new BasicDBList();		for (String o : list) {			prices.add(toObject(o));		}		return prices;	}	static byte[] toObject(byte[] value) {		return value;	}	static byte toObject(byte value) {		return value;	}	static char toObject(char value) {		return value;	}	static boolean toObject(boolean value) {		return value;	}	static short toObject(short value) {		return value;	}	static int toObject(int value) {		return value;	}	static long toObject(long value) {		return value;	}	static float toObject(float value) {		return value;	}	static double toObject(double value) {		return value;	}	static String toObject(String value) {		return value;	}	static DBObject toObject(MongoDto value) {		if(value == null)			return null;		return value.toObject();	}	static <V extends MongoDto> BasicDBObject toObject(MongoMap<V> map) {		BasicDBObject o = new BasicDBObject();		for (String key : map.keySet()) {			V v = map.get(key);			o.put(key, toObject(v));		}		return o;	}		static <V extends MongoDto> BasicDBList toObject(List<V> ls) {		BasicDBList o = new BasicDBList();		for (V v : ls) {			o.add(toObject(v));		}		return o;	}	static MongoMap<byte[]> copyBytes(MongoMap<byte[]> value) {		MongoMap<byte[]> map = Maps.newMongoMap();		for (String key : value.keySet()) {			map.put(key, MongoGen.copy(value.get(key)));		}		return map;	}	static MongoMap<Byte> copyByte(MongoMap<Byte> value) {		MongoMap<Byte> map = Maps.newMongoMap();		for (String key : value.keySet()) {			map.put(key, value.get(key));		}		return map;	}	static MongoMap<Character> copyCharacter(MongoMap<Character> value) {		MongoMap<Character> map = Maps.newMongoMap();		for (String key : value.keySet()) {			map.put(key, value.get(key));		}		return map;	}	static MongoMap<Boolean> copyBoolean(MongoMap<Boolean> value) {		MongoMap<Boolean> map = Maps.newMongoMap();		for (String key : value.keySet()) {			map.put(key, value.get(key));		}		return map;	}	static MongoMap<Short> copyShort(MongoMap<Short> value) {		MongoMap<Short> map = Maps.newMongoMap();		for (String key : value.keySet()) {			map.put(key, value.get(key));		}		return map;	}	static MongoMap<Integer> copyInteger(MongoMap<Integer> value) {		MongoMap<Integer> map = Maps.newMongoMap();		for (String key : value.keySet()) {			map.put(key, value.get(key));		}		return map;	}	static MongoMap<Long> copyLong(MongoMap<Long> value) {		MongoMap<Long> map = Maps.newMongoMap();		for (String key : value.keySet()) {			map.put(key, value.get(key));		}		return map;	}	static MongoMap<Float> copyFloat(MongoMap<Float> value) {		MongoMap<Float> map = Maps.newMongoMap();		for (String key : value.keySet()) {			map.put(key, value.get(key));		}		return map;	}	static MongoMap<Double> copyDouble(MongoMap<Double> value) {		MongoMap<Double> map = Maps.newMongoMap();		for (String key : value.keySet()) {			map.put(key, value.get(key));		}		return map;	}	static MongoMap<String> copyString(MongoMap<String> value) {		MongoMap<String> map = Maps.newMongoMap();		for (String key : value.keySet()) {			map.put(key, new String(value.get(key)));		}		return map;	}	static List<byte[]> copyBytes(List<byte[]> value) {		List<byte[]> ls = Lists.newArrayList();		for (byte[] v : value) {			ls.add(copy(v));		}		return ls;	}	static List<Byte> copyByte(List<Byte> value) {		List<Byte> ls = Lists.newArrayList();		for (Byte v : value) {			ls.add(v);		}		return ls;	}	static List<Character> copyCharacter(List<Character> value) {		List<Character> ls = Lists.newArrayList();		for (Character v : value) {			ls.add(v);		}		return ls;	}	static List<Boolean> copyBoolean(List<Boolean> value) {		List<Boolean> ls = Lists.newArrayList();		for (Boolean v : value) {			ls.add(v);		}		return ls;	}	static List<Short> copyShort(List<Short> value) {		List<Short> ls = Lists.newArrayList();		for (Short v : value) {			ls.add(v);		}		return ls;	}	static List<Integer> copyInteger(List<Integer> value) {		List<Integer> ls = Lists.newArrayList();		for (Integer v : value) {			ls.add(v);		}		return ls;	}	static List<Long> copyLong(List<Long> value) {		List<Long> ls = Lists.newArrayList();		for (Long v : value) {			ls.add(v);		}		return ls;	}	static List<Float> copyFloat(List<Float> value) {		List<Float> ls = Lists.newArrayList();		for (Float v : value) {			ls.add(v);		}		return ls;	}	static List<Double> copyDouble(List<Double> value) {		List<Double> ls = Lists.newArrayList();		for (Double v : value) {			ls.add(v);		}		return ls;	}	static List<String> copyString(List<String> value) {		List<String> ls = Lists.newArrayList();		for (String v : value) {			ls.add(new String(v));		}		return ls;	}		static byte[] copy(byte[] value) {		return java.util.Arrays.copyOf(value, value.length);	}	static byte copy(byte value) {		return value;	}	static char copy(char value) {		return value;	}	static boolean copy(boolean value) {		return value;	}	static short copy(short value) {		return value;	}	static int copy(int value) {		return value;	}	static long copy(long value) {		return value;	}	static float copy(float value) {		return value;	}	static double copy(double value) {		return value;	}	static String copy(String value) {		return new String(value);	}												static BasicDBList getBasicDBList(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return new BasicDBList();		}		return (BasicDBList) obj;	}	static boolean getBoolean(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return false;		}		return (Boolean) obj;	}		static int getInteger(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return 0;		}		return (Integer) obj;	}		static byte[] getBytes(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return null;		}		return (byte[]) obj;	}		static float getFloat(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return 0;		}		return (Float) obj;	}		static String getString(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return "";		}		return (String) obj;	}	static long getLong(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return 0;		}		return (Long) obj;	}		static double getDouble(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return 0;		}		return (Double) obj;	}										 static void put(String key, BasicDBObject o, GameXmlFileDto value) {		o.put(key, MongoGen.toObject(value));	 }	 
							 static void put(String key, BasicDBObject o, KeyValueDto value) {		o.put(key, MongoGen.toObject(value));	 }	 
							 static void put(String key, BasicDBObject o, OrderDto value) {		o.put(key, MongoGen.toObject(value));	 }	 
							 static void put(String key, BasicDBObject o, OrderFinishDto value) {		o.put(key, MongoGen.toObject(value));	 }	 
							 static void put(String key, BasicDBObject o, ProjectDto value) {		o.put(key, MongoGen.toObject(value));	 }	 
							 static void put(String key, BasicDBObject o, SystemKeyValueDto value) {		o.put(key, MongoGen.toObject(value));	 }	 
							 static void put(String key, BasicDBObject o, UserDto value) {		o.put(key, MongoGen.toObject(value));	 }	 
							 static void put(String key, BasicDBObject o, ZfbOrderDto value) {		o.put(key, MongoGen.toObject(value));	 }	 
							 static void put(String key, BasicDBObject o, ZfbOrderFinishDto value) {		o.put(key, MongoGen.toObject(value));	 }	 
							 static void put(String key, BasicDBObject o, ZoneDto value) {		o.put(key, MongoGen.toObject(value));	 }	 
								 static GameXmlFileDto copy(GameXmlFileDto value) {		return new GameXmlFileDto(value);	 }
							 static KeyValueDto copy(KeyValueDto value) {		return new KeyValueDto(value);	 }
							 static OrderDto copy(OrderDto value) {		return new OrderDto(value);	 }
							 static OrderFinishDto copy(OrderFinishDto value) {		return new OrderFinishDto(value);	 }
							 static ProjectDto copy(ProjectDto value) {		return new ProjectDto(value);	 }
							 static SystemKeyValueDto copy(SystemKeyValueDto value) {		return new SystemKeyValueDto(value);	 }
							 static UserDto copy(UserDto value) {		return new UserDto(value);	 }
							 static ZfbOrderDto copy(ZfbOrderDto value) {		return new ZfbOrderDto(value);	 }
							 static ZfbOrderFinishDto copy(ZfbOrderFinishDto value) {		return new ZfbOrderFinishDto(value);	 }
							 static ZoneDto copy(ZoneDto value) {		return new ZoneDto(value);	 }
							static DBObject toObject(GameXmlFileDto value) {		if(value == null) 			return null;		return value.toObject();	}	 	static DBObject toObjectGameXmlFileDto(GameXmlFileDto value) {		return value.toObject();	}	 	static BasicDBList toObjectGameXmlFileDto(List<GameXmlFileDto> value) {		BasicDBList o = new BasicDBList();		for (GameXmlFileDto v : value) {			o.add(toObject(v));		}		return o;	}	 	 
							static DBObject toObject(KeyValueDto value) {		if(value == null) 			return null;		return value.toObject();	}	 	static DBObject toObjectKeyValueDto(KeyValueDto value) {		return value.toObject();	}	 	static BasicDBList toObjectKeyValueDto(List<KeyValueDto> value) {		BasicDBList o = new BasicDBList();		for (KeyValueDto v : value) {			o.add(toObject(v));		}		return o;	}	 	 
							static DBObject toObject(OrderDto value) {		if(value == null) 			return null;		return value.toObject();	}	 	static DBObject toObjectOrderDto(OrderDto value) {		return value.toObject();	}	 	static BasicDBList toObjectOrderDto(List<OrderDto> value) {		BasicDBList o = new BasicDBList();		for (OrderDto v : value) {			o.add(toObject(v));		}		return o;	}	 	 
							static DBObject toObject(OrderFinishDto value) {		if(value == null) 			return null;		return value.toObject();	}	 	static DBObject toObjectOrderFinishDto(OrderFinishDto value) {		return value.toObject();	}	 	static BasicDBList toObjectOrderFinishDto(List<OrderFinishDto> value) {		BasicDBList o = new BasicDBList();		for (OrderFinishDto v : value) {			o.add(toObject(v));		}		return o;	}	 	 
							static DBObject toObject(ProjectDto value) {		if(value == null) 			return null;		return value.toObject();	}	 	static DBObject toObjectProjectDto(ProjectDto value) {		return value.toObject();	}	 	static BasicDBList toObjectProjectDto(List<ProjectDto> value) {		BasicDBList o = new BasicDBList();		for (ProjectDto v : value) {			o.add(toObject(v));		}		return o;	}	 	 
							static DBObject toObject(SystemKeyValueDto value) {		if(value == null) 			return null;		return value.toObject();	}	 	static DBObject toObjectSystemKeyValueDto(SystemKeyValueDto value) {		return value.toObject();	}	 	static BasicDBList toObjectSystemKeyValueDto(List<SystemKeyValueDto> value) {		BasicDBList o = new BasicDBList();		for (SystemKeyValueDto v : value) {			o.add(toObject(v));		}		return o;	}	 	 
							static DBObject toObject(UserDto value) {		if(value == null) 			return null;		return value.toObject();	}	 	static DBObject toObjectUserDto(UserDto value) {		return value.toObject();	}	 	static BasicDBList toObjectUserDto(List<UserDto> value) {		BasicDBList o = new BasicDBList();		for (UserDto v : value) {			o.add(toObject(v));		}		return o;	}	 	 
							static DBObject toObject(ZfbOrderDto value) {		if(value == null) 			return null;		return value.toObject();	}	 	static DBObject toObjectZfbOrderDto(ZfbOrderDto value) {		return value.toObject();	}	 	static BasicDBList toObjectZfbOrderDto(List<ZfbOrderDto> value) {		BasicDBList o = new BasicDBList();		for (ZfbOrderDto v : value) {			o.add(toObject(v));		}		return o;	}	 	 
							static DBObject toObject(ZfbOrderFinishDto value) {		if(value == null) 			return null;		return value.toObject();	}	 	static DBObject toObjectZfbOrderFinishDto(ZfbOrderFinishDto value) {		return value.toObject();	}	 	static BasicDBList toObjectZfbOrderFinishDto(List<ZfbOrderFinishDto> value) {		BasicDBList o = new BasicDBList();		for (ZfbOrderFinishDto v : value) {			o.add(toObject(v));		}		return o;	}	 	 
							static DBObject toObject(ZoneDto value) {		if(value == null) 			return null;		return value.toObject();	}	 	static DBObject toObjectZoneDto(ZoneDto value) {		return value.toObject();	}	 	static BasicDBList toObjectZoneDto(List<ZoneDto> value) {		BasicDBList o = new BasicDBList();		for (ZoneDto v : value) {			o.add(toObject(v));		}		return o;	}	 	 
		public static interface CollectionFetcher {			DBCollection getCollection(String string);		void destroy();	}			public static interface MongoDto {			DBObject toObject();		void fromDBObject(DBObject o);	}			public static interface MongoDbProperties {			/**		 * 数据库名		 */		String getName();				/**		 * 主机地址		 */		String getHost();				/**		 * 端口		 */		int getPort();		}	public static final class Daos {			private static CollectionFetcher	fetcher;		private static MongoDbProperties	properties;		private static Map<String, DBCollection> cache = new HashMap<String, DBCollection>();				public static void destroy() {			getCollectionFetcher().destroy();		}				public static final void setProperties(MongoDbProperties properties) {			Daos.properties = properties;		}				public static final CollectionFetcher getCollectionFetcher() {			if(fetcher == null)				fetcher = new NormalFetcher();			return fetcher;		}				private static  class NormalFetcher implements CollectionFetcher {			@Override			public DBCollection getCollection(String name) {								Mongo m = getMongo();				DB db = m.getDB(properties.getName());				return db.getCollection(name);			}			@Override			public void destroy() {				if(mongo != null)					mongo.close();			}						private static Mongo mongo;			private static Mongo getMongo() {				if(mongo == null) {					try {						if(properties == null) {							throw new NullPointerException("请先setProperties");						}						mongo = new MongoClient(properties.getHost(), properties.getPort());					} catch (UnknownHostException e) {						throw cn.javaplus.util.Util.Exception.toRuntimeException(e);					}				}				return mongo;			}		}		private static DBCollection getCollection(String collectionName) {			DBCollection c;			try {				c = cache.get(collectionName);			} catch (NullPointerException e) {				c = null;			}			if (c != null) {				return c;			}			c = getCollectionFetcher().getCollection(collectionName);			cache.put(collectionName, c);			return c;		}		public static OrderDao getOrderDao() {			return new OrderDao(getCollection("Order"));		}
		public static OrderFinishDao getOrderFinishDao() {			return new OrderFinishDao(getCollection("OrderFinish"));		}
		public static ProjectDao getProjectDao() {			return new ProjectDao(getCollection("Project"));		}
		public static SystemKeyValueDao getSystemKeyValueDao() {			return new SystemKeyValueDao(getCollection("SystemKeyValue"));		}
		public static UserDao getUserDao() {			return new UserDao(getCollection("User"));		}
		public static ZfbOrderDao getZfbOrderDao() {			return new ZfbOrderDao(getCollection("ZfbOrder"));		}
		public static ZfbOrderFinishDao getZfbOrderFinishDao() {			return new ZfbOrderFinishDao(getCollection("ZfbOrderFinish"));		}
		}
		public static class OrderDao {			private DBCollection	collection;			public OrderDao(DBCollection collection) {			this.collection = collection;		}			public void save(OrderDto u) {			collection.save(u.toObject());		}			public void delete(OrderDto u) {			delete(u.getId());		}			public void delete(String id) {			collection.remove(key(id));		}			public OrderDto get(String id) {			DBObject o = collection.findOne(key(id));			if(o == null) {				return null;			}			OrderDto x = new OrderDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String id) {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);			return o;		}			public OrderDtoCursor find() {			return new OrderDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public OrderDtoCursor findById(String id) {			collection.ensureIndex("id");			BasicDBObject o = new BasicDBObject("id", id);			return new OrderDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public OrderDtoCursor findByIdFuzzy(String id) {			collection.ensureIndex("id");			id = id.replaceAll("\\*", ".*");			id = "^" + id + "$";			BasicDBObject o = new BasicDBObject("id", Pattern.compile(id, Pattern.CASE_INSENSITIVE));			return new OrderDtoCursor(collection.find(o));		}
		public OrderDtoCursor findByServerId(String serverId) {						BasicDBObject o = new BasicDBObject("serverId", serverId);			return new OrderDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public OrderDtoCursor findByServerIdFuzzy(String serverId) {						serverId = serverId.replaceAll("\\*", ".*");			serverId = "^" + serverId + "$";			BasicDBObject o = new BasicDBObject("serverId", Pattern.compile(serverId, Pattern.CASE_INSENSITIVE));			return new OrderDtoCursor(collection.find(o));		}
		public OrderDtoCursor findByCount(int count) {						BasicDBObject o = new BasicDBObject("count", count);			return new OrderDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public OrderDtoCursor findCountBetween(int min, int max) {						BasicDBObject o = new BasicDBObject();			o.put("count", new BasicDBObject("$gte", min).append("$lte", max));			return new OrderDtoCursor(collection.find(o));		}
		public OrderDtoCursor findByTime(long time) {						BasicDBObject o = new BasicDBObject("time", time);			return new OrderDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public OrderDtoCursor findTimeBetween(long min, long max) {						BasicDBObject o = new BasicDBObject();			o.put("time", new BasicDBObject("$gte", min).append("$lte", max));			return new OrderDtoCursor(collection.find(o));		}
		public OrderDtoCursor findByRechargeUserId(String rechargeUserId) {						BasicDBObject o = new BasicDBObject("rechargeUserId", rechargeUserId);			return new OrderDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public OrderDtoCursor findByRechargeUserIdFuzzy(String rechargeUserId) {						rechargeUserId = rechargeUserId.replaceAll("\\*", ".*");			rechargeUserId = "^" + rechargeUserId + "$";			BasicDBObject o = new BasicDBObject("rechargeUserId", Pattern.compile(rechargeUserId, Pattern.CASE_INSENSITIVE));			return new OrderDtoCursor(collection.find(o));		}
		public OrderDtoCursor findByUserId(String userId) {						BasicDBObject o = new BasicDBObject("userId", userId);			return new OrderDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public OrderDtoCursor findByUserIdFuzzy(String userId) {						userId = userId.replaceAll("\\*", ".*");			userId = "^" + userId + "$";			BasicDBObject o = new BasicDBObject("userId", Pattern.compile(userId, Pattern.CASE_INSENSITIVE));			return new OrderDtoCursor(collection.find(o));		}
		public OrderDtoCursor findByIsError(boolean isError) {						BasicDBObject o = new BasicDBObject("isError", isError);			return new OrderDtoCursor(collection.find(o));		}
		public OrderDtoCursor findByLastProcessTime(long lastProcessTime) {						BasicDBObject o = new BasicDBObject("lastProcessTime", lastProcessTime);			return new OrderDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public OrderDtoCursor findLastProcessTimeBetween(long min, long max) {						BasicDBObject o = new BasicDBObject();			o.put("lastProcessTime", new BasicDBObject("$gte", min).append("$lte", max));			return new OrderDtoCursor(collection.find(o));		}
		public OrderDtoCursor findByRetrySpace(long retrySpace) {						BasicDBObject o = new BasicDBObject("retrySpace", retrySpace);			return new OrderDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public OrderDtoCursor findRetrySpaceBetween(long min, long max) {						BasicDBObject o = new BasicDBObject();			o.put("retrySpace", new BasicDBObject("$gte", min).append("$lte", max));			return new OrderDtoCursor(collection.find(o));		}
		public OrderDtoCursor findByReason(String reason) {						BasicDBObject o = new BasicDBObject("reason", reason);			return new OrderDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public OrderDtoCursor findByReasonFuzzy(String reason) {						reason = reason.replaceAll("\\*", ".*");			reason = "^" + reason + "$";			BasicDBObject o = new BasicDBObject("reason", Pattern.compile(reason, Pattern.CASE_INSENSITIVE));			return new OrderDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public OrderDto createDTO() {			return new OrderDto();		}			public static class OrderDtoCursor implements Iterator<OrderDto>, Iterable<OrderDto>{				private DBCursor	cursor;			private int pageAll;				public OrderDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public OrderDto next() {				DBObject next = cursor.next();				OrderDto dto = new OrderDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void skip(int skip) {				cursor.skip(skip);			}						public void limit(int limit) {				cursor.limit(limit);			}						/**			 * 分页, page从1开始 countOfEveryPage必须大于0			 */			public void page(int page, int countOfEveryPage) {				if(countOfEveryPage <= 0) {					throw new RuntimeException("countOfEveryPage must > 0");				}				int count = getCount();				pageAll = count / countOfEveryPage;				if(count % countOfEveryPage != 0) {					pageAll ++;				}								if(page > pageAll)					page = pageAll;								if(page < 1)					page = 1;									int skip = (page - 1) * countOfEveryPage ;				skip(skip);				limit(countOfEveryPage);			}						public int getPageAll() {				return pageAll;			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<OrderDto> iterator() {				return this;			}		}	}
		public static class OrderFinishDao {			private DBCollection	collection;			public OrderFinishDao(DBCollection collection) {			this.collection = collection;		}			public void save(OrderFinishDto u) {			collection.save(u.toObject());		}			public void delete(OrderFinishDto u) {			delete(u.getId());		}			public void delete(String id) {			collection.remove(key(id));		}			public OrderFinishDto get(String id) {			DBObject o = collection.findOne(key(id));			if(o == null) {				return null;			}			OrderFinishDto x = new OrderFinishDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String id) {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);			return o;		}			public OrderFinishDtoCursor find() {			return new OrderFinishDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public OrderFinishDtoCursor findById(String id) {			collection.ensureIndex("id");			BasicDBObject o = new BasicDBObject("id", id);			return new OrderFinishDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public OrderFinishDtoCursor findByIdFuzzy(String id) {			collection.ensureIndex("id");			id = id.replaceAll("\\*", ".*");			id = "^" + id + "$";			BasicDBObject o = new BasicDBObject("id", Pattern.compile(id, Pattern.CASE_INSENSITIVE));			return new OrderFinishDtoCursor(collection.find(o));		}
		public OrderFinishDtoCursor findByServerId(String serverId) {						BasicDBObject o = new BasicDBObject("serverId", serverId);			return new OrderFinishDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public OrderFinishDtoCursor findByServerIdFuzzy(String serverId) {						serverId = serverId.replaceAll("\\*", ".*");			serverId = "^" + serverId + "$";			BasicDBObject o = new BasicDBObject("serverId", Pattern.compile(serverId, Pattern.CASE_INSENSITIVE));			return new OrderFinishDtoCursor(collection.find(o));		}
		public OrderFinishDtoCursor findByCount(int count) {						BasicDBObject o = new BasicDBObject("count", count);			return new OrderFinishDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public OrderFinishDtoCursor findCountBetween(int min, int max) {						BasicDBObject o = new BasicDBObject();			o.put("count", new BasicDBObject("$gte", min).append("$lte", max));			return new OrderFinishDtoCursor(collection.find(o));		}
		public OrderFinishDtoCursor findByTime(long time) {						BasicDBObject o = new BasicDBObject("time", time);			return new OrderFinishDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public OrderFinishDtoCursor findTimeBetween(long min, long max) {						BasicDBObject o = new BasicDBObject();			o.put("time", new BasicDBObject("$gte", min).append("$lte", max));			return new OrderFinishDtoCursor(collection.find(o));		}
		public OrderFinishDtoCursor findByRechargeUserId(String rechargeUserId) {						BasicDBObject o = new BasicDBObject("rechargeUserId", rechargeUserId);			return new OrderFinishDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public OrderFinishDtoCursor findByRechargeUserIdFuzzy(String rechargeUserId) {						rechargeUserId = rechargeUserId.replaceAll("\\*", ".*");			rechargeUserId = "^" + rechargeUserId + "$";			BasicDBObject o = new BasicDBObject("rechargeUserId", Pattern.compile(rechargeUserId, Pattern.CASE_INSENSITIVE));			return new OrderFinishDtoCursor(collection.find(o));		}
		public OrderFinishDtoCursor findByUserId(String userId) {						BasicDBObject o = new BasicDBObject("userId", userId);			return new OrderFinishDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public OrderFinishDtoCursor findByUserIdFuzzy(String userId) {						userId = userId.replaceAll("\\*", ".*");			userId = "^" + userId + "$";			BasicDBObject o = new BasicDBObject("userId", Pattern.compile(userId, Pattern.CASE_INSENSITIVE));			return new OrderFinishDtoCursor(collection.find(o));		}
		public OrderFinishDtoCursor findByIsError(boolean isError) {						BasicDBObject o = new BasicDBObject("isError", isError);			return new OrderFinishDtoCursor(collection.find(o));		}
		public OrderFinishDtoCursor findByLastProcessTime(long lastProcessTime) {						BasicDBObject o = new BasicDBObject("lastProcessTime", lastProcessTime);			return new OrderFinishDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public OrderFinishDtoCursor findLastProcessTimeBetween(long min, long max) {						BasicDBObject o = new BasicDBObject();			o.put("lastProcessTime", new BasicDBObject("$gte", min).append("$lte", max));			return new OrderFinishDtoCursor(collection.find(o));		}
		public OrderFinishDtoCursor findByRetrySpace(long retrySpace) {						BasicDBObject o = new BasicDBObject("retrySpace", retrySpace);			return new OrderFinishDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public OrderFinishDtoCursor findRetrySpaceBetween(long min, long max) {						BasicDBObject o = new BasicDBObject();			o.put("retrySpace", new BasicDBObject("$gte", min).append("$lte", max));			return new OrderFinishDtoCursor(collection.find(o));		}
		public OrderFinishDtoCursor findByReason(String reason) {						BasicDBObject o = new BasicDBObject("reason", reason);			return new OrderFinishDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public OrderFinishDtoCursor findByReasonFuzzy(String reason) {						reason = reason.replaceAll("\\*", ".*");			reason = "^" + reason + "$";			BasicDBObject o = new BasicDBObject("reason", Pattern.compile(reason, Pattern.CASE_INSENSITIVE));			return new OrderFinishDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public OrderFinishDto createDTO() {			return new OrderFinishDto();		}			public static class OrderFinishDtoCursor implements Iterator<OrderFinishDto>, Iterable<OrderFinishDto>{				private DBCursor	cursor;			private int pageAll;				public OrderFinishDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public OrderFinishDto next() {				DBObject next = cursor.next();				OrderFinishDto dto = new OrderFinishDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void skip(int skip) {				cursor.skip(skip);			}						public void limit(int limit) {				cursor.limit(limit);			}						/**			 * 分页, page从1开始 countOfEveryPage必须大于0			 */			public void page(int page, int countOfEveryPage) {				if(countOfEveryPage <= 0) {					throw new RuntimeException("countOfEveryPage must > 0");				}				int count = getCount();				pageAll = count / countOfEveryPage;				if(count % countOfEveryPage != 0) {					pageAll ++;				}								if(page > pageAll)					page = pageAll;								if(page < 1)					page = 1;									int skip = (page - 1) * countOfEveryPage ;				skip(skip);				limit(countOfEveryPage);			}						public int getPageAll() {				return pageAll;			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<OrderFinishDto> iterator() {				return this;			}		}	}
		public static class ProjectDao {			private DBCollection	collection;			public ProjectDao(DBCollection collection) {			this.collection = collection;		}			public void save(ProjectDto u) {			collection.save(u.toObject());		}			public void delete(ProjectDto u) {			delete(u.getId());		}			public void delete(String id) {			collection.remove(key(id));		}			public ProjectDto get(String id) {			DBObject o = collection.findOne(key(id));			if(o == null) {				return null;			}			ProjectDto x = new ProjectDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String id) {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);			return o;		}			public ProjectDtoCursor find() {			return new ProjectDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public ProjectDtoCursor findById(String id) {			collection.ensureIndex("id");			BasicDBObject o = new BasicDBObject("id", id);			return new ProjectDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public ProjectDtoCursor findByIdFuzzy(String id) {			collection.ensureIndex("id");			id = id.replaceAll("\\*", ".*");			id = "^" + id + "$";			BasicDBObject o = new BasicDBObject("id", Pattern.compile(id, Pattern.CASE_INSENSITIVE));			return new ProjectDtoCursor(collection.find(o));		}
		public ProjectDtoCursor findByName(String name) {						BasicDBObject o = new BasicDBObject("name", name);			return new ProjectDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public ProjectDtoCursor findByNameFuzzy(String name) {						name = name.replaceAll("\\*", ".*");			name = "^" + name + "$";			BasicDBObject o = new BasicDBObject("name", Pattern.compile(name, Pattern.CASE_INSENSITIVE));			return new ProjectDtoCursor(collection.find(o));		}
		public ProjectDtoCursor findByGmScript(String gmScript) {						BasicDBObject o = new BasicDBObject("gmScript", gmScript);			return new ProjectDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public ProjectDtoCursor findByGmScriptFuzzy(String gmScript) {						gmScript = gmScript.replaceAll("\\*", ".*");			gmScript = "^" + gmScript + "$";			BasicDBObject o = new BasicDBObject("gmScript", Pattern.compile(gmScript, Pattern.CASE_INSENSITIVE));			return new ProjectDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public ProjectDto createDTO() {			return new ProjectDto();		}			public static class ProjectDtoCursor implements Iterator<ProjectDto>, Iterable<ProjectDto>{				private DBCursor	cursor;			private int pageAll;				public ProjectDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public ProjectDto next() {				DBObject next = cursor.next();				ProjectDto dto = new ProjectDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void skip(int skip) {				cursor.skip(skip);			}						public void limit(int limit) {				cursor.limit(limit);			}						/**			 * 分页, page从1开始 countOfEveryPage必须大于0			 */			public void page(int page, int countOfEveryPage) {				if(countOfEveryPage <= 0) {					throw new RuntimeException("countOfEveryPage must > 0");				}				int count = getCount();				pageAll = count / countOfEveryPage;				if(count % countOfEveryPage != 0) {					pageAll ++;				}								if(page > pageAll)					page = pageAll;								if(page < 1)					page = 1;									int skip = (page - 1) * countOfEveryPage ;				skip(skip);				limit(countOfEveryPage);			}						public int getPageAll() {				return pageAll;			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<ProjectDto> iterator() {				return this;			}		}	}
		public static class SystemKeyValueDao {			private DBCollection	collection;			public SystemKeyValueDao(DBCollection collection) {			this.collection = collection;		}			public void save(SystemKeyValueDto u) {			collection.save(u.toObject());		}			public void delete(SystemKeyValueDto u) {			delete(u.getKey());		}			public void delete(String key) {			collection.remove(key(key));		}			public SystemKeyValueDto get(String key) {			DBObject o = collection.findOne(key(key));			if(o == null) {				return null;			}			SystemKeyValueDto x = new SystemKeyValueDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String key) {			BasicDBObject o = new BasicDBObject();		o.put("_id", key);			return o;		}			public SystemKeyValueDtoCursor find() {			return new SystemKeyValueDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public SystemKeyValueDtoCursor findByKey(String key) {			collection.ensureIndex("key");			BasicDBObject o = new BasicDBObject("key", key);			return new SystemKeyValueDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public SystemKeyValueDtoCursor findByKeyFuzzy(String key) {			collection.ensureIndex("key");			key = key.replaceAll("\\*", ".*");			key = "^" + key + "$";			BasicDBObject o = new BasicDBObject("key", Pattern.compile(key, Pattern.CASE_INSENSITIVE));			return new SystemKeyValueDtoCursor(collection.find(o));		}
		public SystemKeyValueDtoCursor findByValue(String value) {						BasicDBObject o = new BasicDBObject("value", value);			return new SystemKeyValueDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public SystemKeyValueDtoCursor findByValueFuzzy(String value) {						value = value.replaceAll("\\*", ".*");			value = "^" + value + "$";			BasicDBObject o = new BasicDBObject("value", Pattern.compile(value, Pattern.CASE_INSENSITIVE));			return new SystemKeyValueDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public SystemKeyValueDto createDTO() {			return new SystemKeyValueDto();		}			public static class SystemKeyValueDtoCursor implements Iterator<SystemKeyValueDto>, Iterable<SystemKeyValueDto>{				private DBCursor	cursor;			private int pageAll;				public SystemKeyValueDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public SystemKeyValueDto next() {				DBObject next = cursor.next();				SystemKeyValueDto dto = new SystemKeyValueDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void skip(int skip) {				cursor.skip(skip);			}						public void limit(int limit) {				cursor.limit(limit);			}						/**			 * 分页, page从1开始 countOfEveryPage必须大于0			 */			public void page(int page, int countOfEveryPage) {				if(countOfEveryPage <= 0) {					throw new RuntimeException("countOfEveryPage must > 0");				}				int count = getCount();				pageAll = count / countOfEveryPage;				if(count % countOfEveryPage != 0) {					pageAll ++;				}								if(page > pageAll)					page = pageAll;								if(page < 1)					page = 1;									int skip = (page - 1) * countOfEveryPage ;				skip(skip);				limit(countOfEveryPage);			}						public int getPageAll() {				return pageAll;			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<SystemKeyValueDto> iterator() {				return this;			}		}	}
		public static class UserDao {			private DBCollection	collection;			public UserDao(DBCollection collection) {			this.collection = collection;		}			public void save(UserDto u) {			collection.save(u.toObject());		}			public void delete(UserDto u) {			delete(u.getId());		}			public void delete(String id) {			collection.remove(key(id));		}			public UserDto get(String id) {			DBObject o = collection.findOne(key(id));			if(o == null) {				return null;			}			UserDto x = new UserDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String id) {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);			return o;		}			public UserDtoCursor find() {			return new UserDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public UserDtoCursor findById(String id) {			collection.ensureIndex("id");			BasicDBObject o = new BasicDBObject("id", id);			return new UserDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public UserDtoCursor findByIdFuzzy(String id) {			collection.ensureIndex("id");			id = id.replaceAll("\\*", ".*");			id = "^" + id + "$";			BasicDBObject o = new BasicDBObject("id", Pattern.compile(id, Pattern.CASE_INSENSITIVE));			return new UserDtoCursor(collection.find(o));		}
		public UserDtoCursor findByEmail(String email) {			collection.ensureIndex("email");			BasicDBObject o = new BasicDBObject("email", email);			return new UserDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public UserDtoCursor findByEmailFuzzy(String email) {			collection.ensureIndex("email");			email = email.replaceAll("\\*", ".*");			email = "^" + email + "$";			BasicDBObject o = new BasicDBObject("email", Pattern.compile(email, Pattern.CASE_INSENSITIVE));			return new UserDtoCursor(collection.find(o));		}
		public UserDtoCursor findByQQOpenId(String qQOpenId) {			collection.ensureIndex("qQOpenId");			BasicDBObject o = new BasicDBObject("qQOpenId", qQOpenId);			return new UserDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public UserDtoCursor findByQQOpenIdFuzzy(String qQOpenId) {			collection.ensureIndex("qQOpenId");			qQOpenId = qQOpenId.replaceAll("\\*", ".*");			qQOpenId = "^" + qQOpenId + "$";			BasicDBObject o = new BasicDBObject("qQOpenId", Pattern.compile(qQOpenId, Pattern.CASE_INSENSITIVE));			return new UserDtoCursor(collection.find(o));		}
		public UserDtoCursor findByPwdMD5(String pwdMD5) {						BasicDBObject o = new BasicDBObject("pwdMD5", pwdMD5);			return new UserDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public UserDtoCursor findByPwdMD5Fuzzy(String pwdMD5) {						pwdMD5 = pwdMD5.replaceAll("\\*", ".*");			pwdMD5 = "^" + pwdMD5 + "$";			BasicDBObject o = new BasicDBObject("pwdMD5", Pattern.compile(pwdMD5, Pattern.CASE_INSENSITIVE));			return new UserDtoCursor(collection.find(o));		}
		public UserDtoCursor findByNick(String nick) {						BasicDBObject o = new BasicDBObject("nick", nick);			return new UserDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public UserDtoCursor findByNickFuzzy(String nick) {						nick = nick.replaceAll("\\*", ".*");			nick = "^" + nick + "$";			BasicDBObject o = new BasicDBObject("nick", Pattern.compile(nick, Pattern.CASE_INSENSITIVE));			return new UserDtoCursor(collection.find(o));		}
		public UserDtoCursor findByIsMan(boolean isMan) {						BasicDBObject o = new BasicDBObject("isMan", isMan);			return new UserDtoCursor(collection.find(o));		}
		public UserDtoCursor findByAge(int age) {						BasicDBObject o = new BasicDBObject("age", age);			return new UserDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public UserDtoCursor findAgeBetween(int min, int max) {						BasicDBObject o = new BasicDBObject();			o.put("age", new BasicDBObject("$gte", min).append("$lte", max));			return new UserDtoCursor(collection.find(o));		}
		public UserDtoCursor findByVb(int vb) {						BasicDBObject o = new BasicDBObject("vb", vb);			return new UserDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public UserDtoCursor findVbBetween(int min, int max) {						BasicDBObject o = new BasicDBObject();			o.put("vb", new BasicDBObject("$gte", min).append("$lte", max));			return new UserDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public UserDto createDTO() {			return new UserDto();		}			public static class UserDtoCursor implements Iterator<UserDto>, Iterable<UserDto>{				private DBCursor	cursor;			private int pageAll;				public UserDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public UserDto next() {				DBObject next = cursor.next();				UserDto dto = new UserDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void skip(int skip) {				cursor.skip(skip);			}						public void limit(int limit) {				cursor.limit(limit);			}						/**			 * 分页, page从1开始 countOfEveryPage必须大于0			 */			public void page(int page, int countOfEveryPage) {				if(countOfEveryPage <= 0) {					throw new RuntimeException("countOfEveryPage must > 0");				}				int count = getCount();				pageAll = count / countOfEveryPage;				if(count % countOfEveryPage != 0) {					pageAll ++;				}								if(page > pageAll)					page = pageAll;								if(page < 1)					page = 1;									int skip = (page - 1) * countOfEveryPage ;				skip(skip);				limit(countOfEveryPage);			}						public int getPageAll() {				return pageAll;			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<UserDto> iterator() {				return this;			}		}	}
		public static class ZfbOrderDao {			private DBCollection	collection;			public ZfbOrderDao(DBCollection collection) {			this.collection = collection;		}			public void save(ZfbOrderDto u) {			collection.save(u.toObject());		}			public void delete(ZfbOrderDto u) {			delete(u.getId());		}			public void delete(String id) {			collection.remove(key(id));		}			public ZfbOrderDto get(String id) {			DBObject o = collection.findOne(key(id));			if(o == null) {				return null;			}			ZfbOrderDto x = new ZfbOrderDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String id) {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);			return o;		}			public ZfbOrderDtoCursor find() {			return new ZfbOrderDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public ZfbOrderDtoCursor findById(String id) {			collection.ensureIndex("id");			BasicDBObject o = new BasicDBObject("id", id);			return new ZfbOrderDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public ZfbOrderDtoCursor findByIdFuzzy(String id) {			collection.ensureIndex("id");			id = id.replaceAll("\\*", ".*");			id = "^" + id + "$";			BasicDBObject o = new BasicDBObject("id", Pattern.compile(id, Pattern.CASE_INSENSITIVE));			return new ZfbOrderDtoCursor(collection.find(o));		}
		public ZfbOrderDtoCursor findByCount(int count) {						BasicDBObject o = new BasicDBObject("count", count);			return new ZfbOrderDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public ZfbOrderDtoCursor findCountBetween(int min, int max) {						BasicDBObject o = new BasicDBObject();			o.put("count", new BasicDBObject("$gte", min).append("$lte", max));			return new ZfbOrderDtoCursor(collection.find(o));		}
		public ZfbOrderDtoCursor findByPrice(String price) {						BasicDBObject o = new BasicDBObject("price", price);			return new ZfbOrderDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public ZfbOrderDtoCursor findByPriceFuzzy(String price) {						price = price.replaceAll("\\*", ".*");			price = "^" + price + "$";			BasicDBObject o = new BasicDBObject("price", Pattern.compile(price, Pattern.CASE_INSENSITIVE));			return new ZfbOrderDtoCursor(collection.find(o));		}
		public ZfbOrderDtoCursor findByTime(long time) {						BasicDBObject o = new BasicDBObject("time", time);			return new ZfbOrderDtoCursor(collection.find(o));		}
		/**		 * 在min和max之间, 包含min和max		 */		public ZfbOrderDtoCursor findTimeBetween(long min, long max) {						BasicDBObject o = new BasicDBObject();			o.put("time", new BasicDBObject("$gte", min).append("$lte", max));			return new ZfbOrderDtoCursor(collection.find(o));		}
		public ZfbOrderDtoCursor findByUserId(String userId) {						BasicDBObject o = new BasicDBObject("userId", userId);			return new ZfbOrderDtoCursor(collection.find(o));		}
		/**		 * 模糊查找		 * 比如   pattern = *lyc*01*		 * 匹配  alyc12370121		 * 匹配  x123lycacbb0100 		 */		public ZfbOrderDtoCursor findByUserIdFuzzy(String userId) {						userId = userId.replaceAll("\\*", ".*");			userId = "^" + userId + "$";			BasicDBObject o = new BasicDBObject("userId", Pattern.compile(userId, Pattern.CASE_INSENSITIVE));			return new ZfbOrderDtoCursor(collection.find(o));		}
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
			public void clear () {			collection.drop();		}			public ZfbOrderFinishDto createDTO() {			return new ZfbOrderFinishDto();		}			public static class ZfbOrderFinishDtoCursor implements Iterator<ZfbOrderFinishDto>, Iterable<ZfbOrderFinishDto>{				private DBCursor	cursor;			private int pageAll;				public ZfbOrderFinishDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public ZfbOrderFinishDto next() {				DBObject next = cursor.next();				ZfbOrderFinishDto dto = new ZfbOrderFinishDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void skip(int skip) {				cursor.skip(skip);			}						public void limit(int limit) {				cursor.limit(limit);			}						/**			 * 分页, page从1开始 countOfEveryPage必须大于0			 */			public void page(int page, int countOfEveryPage) {				if(countOfEveryPage <= 0) {					throw new RuntimeException("countOfEveryPage must > 0");				}				int count = getCount();				pageAll = count / countOfEveryPage;				if(count % countOfEveryPage != 0) {					pageAll ++;				}								if(page > pageAll)					page = pageAll;								if(page < 1)					page = 1;									int skip = (page - 1) * countOfEveryPage ;				skip(skip);				limit(countOfEveryPage);			}						public int getPageAll() {				return pageAll;			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<ZfbOrderFinishDto> iterator() {				return this;			}		}	}
		public static class GameXmlFileDto implements MongoDto{		private long updateTime = 0;
		private int version = 0;
		private byte[] data = null;
		public GameXmlFileDto() {		}				/**		 * Copy new one		 */		public GameXmlFileDto(GameXmlFileDto src) {			updateTime = MongoGen.copy(src.updateTime);			
			version = MongoGen.copy(src.version);			
			data = MongoGen.copy(src.data);			
		}		public long getUpdateTime() {			return this.updateTime;		}
		public int getVersion() {			return this.version;		}
		public byte[] getData() {			return this.data;		}
		public void setUpdateTime(long updateTime) {			this.updateTime = updateTime;		}
		public void setVersion(int version) {			this.version = version;		}
		public void setData(byte[] data) {			this.data = data;		}
		@Override		public DBObject toObject() {			BasicDBObject o = new BasicDBObject();
			o.put("updateTime", MongoGen.toObject(updateTime));			
			o.put("version", MongoGen.toObject(version));			
			o.put("data", MongoGen.toObject(data));			
			return o;		}		@Override		public void fromDBObject(DBObject o) {			updateTime = getLong(o, "updateTime");
			version = getInteger(o, "version");
			data = getBytes(o, "data");
		}





	static MongoMap<GameXmlFileDto> copy(MongoMap<GameXmlFileDto> map) {		MongoMapImpl<GameXmlFileDto> m = new MongoMapImpl<GameXmlFileDto>();				for (String key : map.keySet()) {			GameXmlFileDto v = map.get(key);			m.put(key, MongoGen.copy(v));		}		return m;	}			static List<GameXmlFileDto> copy(List<GameXmlFileDto> list) {		List<GameXmlFileDto> ls = Lists.newArrayList();		for (GameXmlFileDto t : list) {			ls.add(MongoGen.copy(t));		}		return ls;	}		@Override		public String toString() {			return toObject().toString();		}	}
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
		public static class OrderDto implements MongoDto,com.cnbizmedia.recharge.IOrderDto{		private String id = "";
		private String serverId = "";
		private int count = 0;
		private long time = 0;
		private String rechargeUserId = "";
		private String userId = "";
		private boolean isError = false;
		private long lastProcessTime = 0;
		private long retrySpace = 0;
		private String reason = "";
		public OrderDto() {		}				/**		 * Copy new one		 */		public OrderDto(OrderDto src) {			id = MongoGen.copy(src.id);			
			serverId = MongoGen.copy(src.serverId);			
			count = MongoGen.copy(src.count);			
			time = MongoGen.copy(src.time);			
			rechargeUserId = MongoGen.copy(src.rechargeUserId);			
			userId = MongoGen.copy(src.userId);			
			isError = MongoGen.copy(src.isError);			
			lastProcessTime = MongoGen.copy(src.lastProcessTime);			
			retrySpace = MongoGen.copy(src.retrySpace);			
			reason = MongoGen.copy(src.reason);			
		}		public String getId() {			return this.id;		}
		public String getServerId() {			return this.serverId;		}
		public int getCount() {			return this.count;		}
		public long getTime() {			return this.time;		}
		public String getRechargeUserId() {			return this.rechargeUserId;		}
		public String getUserId() {			return this.userId;		}
		public boolean getIsError() {			return this.isError;		}
		public long getLastProcessTime() {			return this.lastProcessTime;		}
		public long getRetrySpace() {			return this.retrySpace;		}
		public String getReason() {			return this.reason;		}
		public void setId(String id) {			this.id = id;		}
		public void setServerId(String serverId) {			this.serverId = serverId;		}
		public void setCount(int count) {			this.count = count;		}
		public void setTime(long time) {			this.time = time;		}
		public void setRechargeUserId(String rechargeUserId) {			this.rechargeUserId = rechargeUserId;		}
		public void setUserId(String userId) {			this.userId = userId;		}
		public void setIsError(boolean isError) {			this.isError = isError;		}
		public void setLastProcessTime(long lastProcessTime) {			this.lastProcessTime = lastProcessTime;		}
		public void setRetrySpace(long retrySpace) {			this.retrySpace = retrySpace;		}
		public void setReason(String reason) {			this.reason = reason;		}
		@Override		public DBObject toObject() {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);
			o.put("id", MongoGen.toObject(id));			
			o.put("serverId", MongoGen.toObject(serverId));			
			o.put("count", MongoGen.toObject(count));			
			o.put("time", MongoGen.toObject(time));			
			o.put("rechargeUserId", MongoGen.toObject(rechargeUserId));			
			o.put("userId", MongoGen.toObject(userId));			
			o.put("isError", MongoGen.toObject(isError));			
			o.put("lastProcessTime", MongoGen.toObject(lastProcessTime));			
			o.put("retrySpace", MongoGen.toObject(retrySpace));			
			o.put("reason", MongoGen.toObject(reason));			
			return o;		}		@Override		public void fromDBObject(DBObject o) {			id = getString(o, "id");
			serverId = getString(o, "serverId");
			count = getInteger(o, "count");
			time = getLong(o, "time");
			rechargeUserId = getString(o, "rechargeUserId");
			userId = getString(o, "userId");
			isError = getBoolean(o, "isError");
			lastProcessTime = getLong(o, "lastProcessTime");
			retrySpace = getLong(o, "retrySpace");
			reason = getString(o, "reason");
		}



















	static MongoMap<OrderDto> copy(MongoMap<OrderDto> map) {		MongoMapImpl<OrderDto> m = new MongoMapImpl<OrderDto>();				for (String key : map.keySet()) {			OrderDto v = map.get(key);			m.put(key, MongoGen.copy(v));		}		return m;	}			static List<OrderDto> copy(List<OrderDto> list) {		List<OrderDto> ls = Lists.newArrayList();		for (OrderDto t : list) {			ls.add(MongoGen.copy(t));		}		return ls;	}		@Override		public String toString() {			return toObject().toString();		}	}
		public static class OrderFinishDto implements MongoDto,com.cnbizmedia.recharge.IOrderDto{		private String id = "";
		private String serverId = "";
		private int count = 0;
		private long time = 0;
		private String rechargeUserId = "";
		private String userId = "";
		private boolean isError = false;
		private long lastProcessTime = 0;
		private long retrySpace = 0;
		private String reason = "";
		public OrderFinishDto() {		}				/**		 * Copy new one		 */		public OrderFinishDto(OrderFinishDto src) {			id = MongoGen.copy(src.id);			
			serverId = MongoGen.copy(src.serverId);			
			count = MongoGen.copy(src.count);			
			time = MongoGen.copy(src.time);			
			rechargeUserId = MongoGen.copy(src.rechargeUserId);			
			userId = MongoGen.copy(src.userId);			
			isError = MongoGen.copy(src.isError);			
			lastProcessTime = MongoGen.copy(src.lastProcessTime);			
			retrySpace = MongoGen.copy(src.retrySpace);			
			reason = MongoGen.copy(src.reason);			
		}		public String getId() {			return this.id;		}
		public String getServerId() {			return this.serverId;		}
		public int getCount() {			return this.count;		}
		public long getTime() {			return this.time;		}
		public String getRechargeUserId() {			return this.rechargeUserId;		}
		public String getUserId() {			return this.userId;		}
		public boolean getIsError() {			return this.isError;		}
		public long getLastProcessTime() {			return this.lastProcessTime;		}
		public long getRetrySpace() {			return this.retrySpace;		}
		public String getReason() {			return this.reason;		}
		public void setId(String id) {			this.id = id;		}
		public void setServerId(String serverId) {			this.serverId = serverId;		}
		public void setCount(int count) {			this.count = count;		}
		public void setTime(long time) {			this.time = time;		}
		public void setRechargeUserId(String rechargeUserId) {			this.rechargeUserId = rechargeUserId;		}
		public void setUserId(String userId) {			this.userId = userId;		}
		public void setIsError(boolean isError) {			this.isError = isError;		}
		public void setLastProcessTime(long lastProcessTime) {			this.lastProcessTime = lastProcessTime;		}
		public void setRetrySpace(long retrySpace) {			this.retrySpace = retrySpace;		}
		public void setReason(String reason) {			this.reason = reason;		}
		@Override		public DBObject toObject() {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);
			o.put("id", MongoGen.toObject(id));			
			o.put("serverId", MongoGen.toObject(serverId));			
			o.put("count", MongoGen.toObject(count));			
			o.put("time", MongoGen.toObject(time));			
			o.put("rechargeUserId", MongoGen.toObject(rechargeUserId));			
			o.put("userId", MongoGen.toObject(userId));			
			o.put("isError", MongoGen.toObject(isError));			
			o.put("lastProcessTime", MongoGen.toObject(lastProcessTime));			
			o.put("retrySpace", MongoGen.toObject(retrySpace));			
			o.put("reason", MongoGen.toObject(reason));			
			return o;		}		@Override		public void fromDBObject(DBObject o) {			id = getString(o, "id");
			serverId = getString(o, "serverId");
			count = getInteger(o, "count");
			time = getLong(o, "time");
			rechargeUserId = getString(o, "rechargeUserId");
			userId = getString(o, "userId");
			isError = getBoolean(o, "isError");
			lastProcessTime = getLong(o, "lastProcessTime");
			retrySpace = getLong(o, "retrySpace");
			reason = getString(o, "reason");
		}



















	static MongoMap<OrderFinishDto> copy(MongoMap<OrderFinishDto> map) {		MongoMapImpl<OrderFinishDto> m = new MongoMapImpl<OrderFinishDto>();				for (String key : map.keySet()) {			OrderFinishDto v = map.get(key);			m.put(key, MongoGen.copy(v));		}		return m;	}			static List<OrderFinishDto> copy(List<OrderFinishDto> list) {		List<OrderFinishDto> ls = Lists.newArrayList();		for (OrderFinishDto t : list) {			ls.add(MongoGen.copy(t));		}		return ls;	}		@Override		public String toString() {			return toObject().toString();		}	}
		public static class ProjectDto implements MongoDto{		private String id = "";
		private String name = "";
		private String gmScript = "";
		private List<ZoneDto> zones = Lists.newArrayList();
		public ProjectDto() {		}				/**		 * Copy new one		 */		public ProjectDto(ProjectDto src) {			id = MongoGen.copy(src.id);			
			name = MongoGen.copy(src.name);			
			gmScript = MongoGen.copy(src.gmScript);			
			zones = ZoneDto.copy(src.zones);			
		}		public String getId() {			return this.id;		}
		public String getName() {			return this.name;		}
		public String getGmScript() {			return this.gmScript;		}
		public List<ZoneDto> getZones() {			return this.zones;		}
		public void setId(String id) {			this.id = id;		}
		public void setName(String name) {			this.name = name;		}
		public void setGmScript(String gmScript) {			this.gmScript = gmScript;		}
		public void setZones(List<ZoneDto> zones) {			this.zones = zones;		}
		@Override		public DBObject toObject() {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);
			o.put("id", MongoGen.toObject(id));			
			o.put("name", MongoGen.toObject(name));			
			o.put("gmScript", MongoGen.toObject(gmScript));			
			o.put("zones", MongoGen.toObjectZoneDto(zones));			
			return o;		}		@Override		public void fromDBObject(DBObject o) {			id = getString(o, "id");
			name = getString(o, "name");
			gmScript = getString(o, "gmScript");
			zones = loadZones(o);
		}






		List<ZoneDto> loadZones(DBObject o) {			List<ZoneDto> ls = Lists.newArrayList();						BasicDBList zones = getBasicDBList(o, "zones");			for (Object xxx : zones) {				ZoneDto tp = new ZoneDto();				tp.fromDBObject((DBObject) xxx);				ls.add(tp);			}			return ls;		}		
	static MongoMap<ProjectDto> copy(MongoMap<ProjectDto> map) {		MongoMapImpl<ProjectDto> m = new MongoMapImpl<ProjectDto>();				for (String key : map.keySet()) {			ProjectDto v = map.get(key);			m.put(key, MongoGen.copy(v));		}		return m;	}			static List<ProjectDto> copy(List<ProjectDto> list) {		List<ProjectDto> ls = Lists.newArrayList();		for (ProjectDto t : list) {			ls.add(MongoGen.copy(t));		}		return ls;	}		@Override		public String toString() {			return toObject().toString();		}	}
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
		public static class UserDto implements MongoDto{		private String id = "";
		private String email = "";
		private String qQOpenId = "";
		private String pwdMD5 = "";
		private String nick = "";
		private boolean isMan = false;
		private int age = 0;
		private int vb = 0;
		public UserDto() {		}				/**		 * Copy new one		 */		public UserDto(UserDto src) {			id = MongoGen.copy(src.id);			
			email = MongoGen.copy(src.email);			
			qQOpenId = MongoGen.copy(src.qQOpenId);			
			pwdMD5 = MongoGen.copy(src.pwdMD5);			
			nick = MongoGen.copy(src.nick);			
			isMan = MongoGen.copy(src.isMan);			
			age = MongoGen.copy(src.age);			
			vb = MongoGen.copy(src.vb);			
		}		public String getId() {			return this.id;		}
		public String getEmail() {			return this.email;		}
		public String getQQOpenId() {			return this.qQOpenId;		}
		public String getPwdMD5() {			return this.pwdMD5;		}
		public String getNick() {			return this.nick;		}
		public boolean getIsMan() {			return this.isMan;		}
		public int getAge() {			return this.age;		}
		public int getVb() {			return this.vb;		}
		public void setId(String id) {			this.id = id;		}
		public void setEmail(String email) {			this.email = email;		}
		public void setQQOpenId(String qQOpenId) {			this.qQOpenId = qQOpenId;		}
		public void setPwdMD5(String pwdMD5) {			this.pwdMD5 = pwdMD5;		}
		public void setNick(String nick) {			this.nick = nick;		}
		public void setIsMan(boolean isMan) {			this.isMan = isMan;		}
		public void setAge(int age) {			this.age = age;		}
		public void setVb(int vb) {			this.vb = vb;		}
		@Override		public DBObject toObject() {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);
			o.put("id", MongoGen.toObject(id));			
			o.put("email", MongoGen.toObject(email));			
			o.put("qQOpenId", MongoGen.toObject(qQOpenId));			
			o.put("pwdMD5", MongoGen.toObject(pwdMD5));			
			o.put("nick", MongoGen.toObject(nick));			
			o.put("isMan", MongoGen.toObject(isMan));			
			o.put("age", MongoGen.toObject(age));			
			o.put("vb", MongoGen.toObject(vb));			
			return o;		}		@Override		public void fromDBObject(DBObject o) {			id = getString(o, "id");
			email = getString(o, "email");
			qQOpenId = getString(o, "qQOpenId");
			pwdMD5 = getString(o, "pwdMD5");
			nick = getString(o, "nick");
			isMan = getBoolean(o, "isMan");
			age = getInteger(o, "age");
			vb = getInteger(o, "vb");
		}















	static MongoMap<UserDto> copy(MongoMap<UserDto> map) {		MongoMapImpl<UserDto> m = new MongoMapImpl<UserDto>();				for (String key : map.keySet()) {			UserDto v = map.get(key);			m.put(key, MongoGen.copy(v));		}		return m;	}			static List<UserDto> copy(List<UserDto> list) {		List<UserDto> ls = Lists.newArrayList();		for (UserDto t : list) {			ls.add(MongoGen.copy(t));		}		return ls;	}		@Override		public String toString() {			return toObject().toString();		}	}
		public static class ZfbOrderDto implements MongoDto{		private String id = "";
		private int count = 0;
		private String price = "";
		private long time = 0;
		private String userId = "";
		public ZfbOrderDto() {		}				/**		 * Copy new one		 */		public ZfbOrderDto(ZfbOrderDto src) {			id = MongoGen.copy(src.id);			
			count = MongoGen.copy(src.count);			
			price = MongoGen.copy(src.price);			
			time = MongoGen.copy(src.time);			
			userId = MongoGen.copy(src.userId);			
		}		public String getId() {			return this.id;		}
		public int getCount() {			return this.count;		}
		public String getPrice() {			return this.price;		}
		public long getTime() {			return this.time;		}
		public String getUserId() {			return this.userId;		}
		public void setId(String id) {			this.id = id;		}
		public void setCount(int count) {			this.count = count;		}
		public void setPrice(String price) {			this.price = price;		}
		public void setTime(long time) {			this.time = time;		}
		public void setUserId(String userId) {			this.userId = userId;		}
		@Override		public DBObject toObject() {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);
			o.put("id", MongoGen.toObject(id));			
			o.put("count", MongoGen.toObject(count));			
			o.put("price", MongoGen.toObject(price));			
			o.put("time", MongoGen.toObject(time));			
			o.put("userId", MongoGen.toObject(userId));			
			return o;		}		@Override		public void fromDBObject(DBObject o) {			id = getString(o, "id");
			count = getInteger(o, "count");
			price = getString(o, "price");
			time = getLong(o, "time");
			userId = getString(o, "userId");
		}









	static MongoMap<ZfbOrderDto> copy(MongoMap<ZfbOrderDto> map) {		MongoMapImpl<ZfbOrderDto> m = new MongoMapImpl<ZfbOrderDto>();				for (String key : map.keySet()) {			ZfbOrderDto v = map.get(key);			m.put(key, MongoGen.copy(v));		}		return m;	}			static List<ZfbOrderDto> copy(List<ZfbOrderDto> list) {		List<ZfbOrderDto> ls = Lists.newArrayList();		for (ZfbOrderDto t : list) {			ls.add(MongoGen.copy(t));		}		return ls;	}		@Override		public String toString() {			return toObject().toString();		}	}
		public static class ZfbOrderFinishDto implements MongoDto{		private String id = "";
		private int count = 0;
		private String price = "";
		private long time = 0;
		private String userId = "";
		public ZfbOrderFinishDto() {		}				/**		 * Copy new one		 */		public ZfbOrderFinishDto(ZfbOrderFinishDto src) {			id = MongoGen.copy(src.id);			
			count = MongoGen.copy(src.count);			
			price = MongoGen.copy(src.price);			
			time = MongoGen.copy(src.time);			
			userId = MongoGen.copy(src.userId);			
		}		public String getId() {			return this.id;		}
		public int getCount() {			return this.count;		}
		public String getPrice() {			return this.price;		}
		public long getTime() {			return this.time;		}
		public String getUserId() {			return this.userId;		}
		public void setId(String id) {			this.id = id;		}
		public void setCount(int count) {			this.count = count;		}
		public void setPrice(String price) {			this.price = price;		}
		public void setTime(long time) {			this.time = time;		}
		public void setUserId(String userId) {			this.userId = userId;		}
		@Override		public DBObject toObject() {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);
			o.put("id", MongoGen.toObject(id));			
			o.put("count", MongoGen.toObject(count));			
			o.put("price", MongoGen.toObject(price));			
			o.put("time", MongoGen.toObject(time));			
			o.put("userId", MongoGen.toObject(userId));			
			return o;		}		@Override		public void fromDBObject(DBObject o) {			id = getString(o, "id");
			count = getInteger(o, "count");
			price = getString(o, "price");
			time = getLong(o, "time");
			userId = getString(o, "userId");
		}









	static MongoMap<ZfbOrderFinishDto> copy(MongoMap<ZfbOrderFinishDto> map) {		MongoMapImpl<ZfbOrderFinishDto> m = new MongoMapImpl<ZfbOrderFinishDto>();				for (String key : map.keySet()) {			ZfbOrderFinishDto v = map.get(key);			m.put(key, MongoGen.copy(v));		}		return m;	}			static List<ZfbOrderFinishDto> copy(List<ZfbOrderFinishDto> list) {		List<ZfbOrderFinishDto> ls = Lists.newArrayList();		for (ZfbOrderFinishDto t : list) {			ls.add(MongoGen.copy(t));		}		return ls;	}		@Override		public String toString() {			return toObject().toString();		}	}
		public static class ZoneDto implements MongoDto{		private String id = "";
		private String name = "";
		private List<KeyValueDto> properties = Lists.newArrayList();
		private GameXmlFileDto gameXmlFile = null;
		private GameXmlFileDto clientXmlFile = null;
		public ZoneDto() {		}				/**		 * Copy new one		 */		public ZoneDto(ZoneDto src) {			id = MongoGen.copy(src.id);			
			name = MongoGen.copy(src.name);			
			properties = KeyValueDto.copy(src.properties);			
			gameXmlFile = MongoGen.copy(src.gameXmlFile);			
			clientXmlFile = MongoGen.copy(src.clientXmlFile);			
		}		public String getId() {			return this.id;		}
		public String getName() {			return this.name;		}
		public List<KeyValueDto> getProperties() {			return this.properties;		}
		public GameXmlFileDto getGameXmlFile() {			return this.gameXmlFile;		}
		public GameXmlFileDto getClientXmlFile() {			return this.clientXmlFile;		}
		public void setId(String id) {			this.id = id;		}
		public void setName(String name) {			this.name = name;		}
		public void setProperties(List<KeyValueDto> properties) {			this.properties = properties;		}
		public void setGameXmlFile(GameXmlFileDto gameXmlFile) {			this.gameXmlFile = gameXmlFile;		}
		public void setClientXmlFile(GameXmlFileDto clientXmlFile) {			this.clientXmlFile = clientXmlFile;		}
		@Override		public DBObject toObject() {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);
			o.put("id", MongoGen.toObject(id));			
			o.put("name", MongoGen.toObject(name));			
			o.put("properties", MongoGen.toObjectKeyValueDto(properties));			
			o.put("gameXmlFile", MongoGen.toObject(gameXmlFile));			
			o.put("clientXmlFile", MongoGen.toObject(clientXmlFile));			
			return o;		}		@Override		public void fromDBObject(DBObject o) {			id = getString(o, "id");
			name = getString(o, "name");
			properties = loadProperties(o);
			DBObject gameXmlFile_dto = (DBObject) o.get("gameXmlFile");			if (gameXmlFile_dto != null) {				gameXmlFile = new GameXmlFileDto();				gameXmlFile.fromDBObject(gameXmlFile_dto);			}									
			DBObject clientXmlFile_dto = (DBObject) o.get("clientXmlFile");			if (clientXmlFile_dto != null) {				clientXmlFile = new GameXmlFileDto();				clientXmlFile.fromDBObject(clientXmlFile_dto);			}									
		}






		List<KeyValueDto> loadProperties(DBObject o) {			List<KeyValueDto> ls = Lists.newArrayList();						BasicDBList properties = getBasicDBList(o, "properties");			for (Object xxx : properties) {				KeyValueDto tp = new KeyValueDto();				tp.fromDBObject((DBObject) xxx);				ls.add(tp);			}			return ls;		}		


	static MongoMap<ZoneDto> copy(MongoMap<ZoneDto> map) {		MongoMapImpl<ZoneDto> m = new MongoMapImpl<ZoneDto>();				for (String key : map.keySet()) {			ZoneDto v = map.get(key);			m.put(key, MongoGen.copy(v));		}		return m;	}			static List<ZoneDto> copy(List<ZoneDto> list) {		List<ZoneDto> ls = Lists.newArrayList();		for (ZoneDto t : list) {			ls.add(MongoGen.copy(t));		}		return ls;	}		@Override		public String toString() {			return toObject().toString();		}	}
}