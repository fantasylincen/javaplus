package com.cnbizmedia.gen.dto;import java.net.UnknownHostException;import java.util.ArrayList;import java.util.HashMap;import java.util.Iterator;import java.util.List;import java.util.Map;import cn.javaplus.exception.UnImplMethodException;import com.mongodb.BasicDBList;import com.mongodb.BasicDBObject;import com.mongodb.DB;import com.mongodb.DBCollection;import com.mongodb.DBCursor;import com.mongodb.DBObject;import com.mongodb.Mongo;import com.mongodb.MongoClient;public class MongoGen {	public static class Lists {		public static<T> List<T> newArrayList() {			return new ArrayList<T>();		}	}	public static interface CollectionFetcher {			DBCollection getCollection(String string);		}			public static interface MongoDbProperties {			/**		 * 数据库名		 */		String getName();				/**		 * 主机地址		 */		String getHost();				/**		 * 端口		 */		int getPort();		}	public static final class Daos {			private static CollectionFetcher	fetcher;		private static MongoDbProperties	properties;		private static Map<String, DBCollection> cache = new HashMap<String, DBCollection>();				public static final void setProperties(MongoDbProperties properties) {			Daos.properties = properties;		}				public static final CollectionFetcher getCollectionFetcher() {			if(fetcher == null)				fetcher = new NormalFetcher();			return fetcher;		}				private static  class NormalFetcher implements CollectionFetcher {			@Override			public DBCollection getCollection(String name) {								Mongo m = getMongo();				DB db = m.getDB(properties.getName());				return db.getCollection(name);			}						private static Mongo mongo;			private static Mongo getMongo() {				if(mongo == null) {					try {						mongo = new MongoClient(properties.getHost(), properties.getPort());					} catch (UnknownHostException e) {						throw cn.javaplus.util.Util.Exception.toRuntimeException(e);					}				}				return mongo;			}		}		private static DBCollection getCollection(String collectionName) {			DBCollection c;			try {				c = cache.get(collectionName);			} catch (NullPointerException e) {				c = null;			}			if (c != null) {				return c;			}			c = getCollectionFetcher().getCollection(collectionName);			cache.put(collectionName, c);			return c;		}		public static OrderDao getOrderDao() {			return new OrderDao(getCollection("Order"));		}
		public static OrderFinishDao getOrderFinishDao() {			return new OrderFinishDao(getCollection("OrderFinish"));		}
		public static UserDao getUserDao() {			return new UserDao(getCollection("User"));		}
		}
		public static class OrderDao {			private DBCollection	collection;			public OrderDao(DBCollection collection) {			this.collection = collection;		}			public void save(OrderDto u) {			collection.save(u.toDBObject());		}			public void delete(OrderDto u) {			delete(u.getId());		}			public void delete(String id) {			collection.remove(key(id));		}			public OrderDto get(String id) {			DBObject o = collection.findOne(key(id));			if(o == null) {				return null;			}			OrderDto x = new OrderDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String id) {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);			return o;		}			public OrderDtoCursor find() {			return new OrderDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public OrderDtoCursor findById(String id) {			collection.ensureIndex("id");			BasicDBObject o = new BasicDBObject("id", id);			return new OrderDtoCursor(collection.find(o));		}
		public OrderDtoCursor findByServerId(String serverId) {						BasicDBObject o = new BasicDBObject("serverId", serverId);			return new OrderDtoCursor(collection.find(o));		}
		public OrderDtoCursor findByCount(int count) {						BasicDBObject o = new BasicDBObject("count", count);			return new OrderDtoCursor(collection.find(o));		}
		public OrderDtoCursor findByTime(long time) {						BasicDBObject o = new BasicDBObject("time", time);			return new OrderDtoCursor(collection.find(o));		}
		public OrderDtoCursor findByRechargeUserId(String rechargeUserId) {						BasicDBObject o = new BasicDBObject("rechargeUserId", rechargeUserId);			return new OrderDtoCursor(collection.find(o));		}
		public OrderDtoCursor findByUserId(String userId) {						BasicDBObject o = new BasicDBObject("userId", userId);			return new OrderDtoCursor(collection.find(o));		}
		public OrderDtoCursor findByIsError(boolean isError) {						BasicDBObject o = new BasicDBObject("isError", isError);			return new OrderDtoCursor(collection.find(o));		}
		public OrderDtoCursor findByLastProcessTime(long lastProcessTime) {						BasicDBObject o = new BasicDBObject("lastProcessTime", lastProcessTime);			return new OrderDtoCursor(collection.find(o));		}
		public OrderDtoCursor findByRetrySpace(long retrySpace) {						BasicDBObject o = new BasicDBObject("retrySpace", retrySpace);			return new OrderDtoCursor(collection.find(o));		}
		public OrderDtoCursor findByReason(String reason) {						BasicDBObject o = new BasicDBObject("reason", reason);			return new OrderDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public OrderDto createDTO() {			return new OrderDto();		}			public static class OrderDtoCursor implements Iterator<OrderDto>, Iterable<OrderDto>{				private DBCursor	cursor;				public OrderDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public OrderDto next() {				DBObject next = cursor.next();				OrderDto dto = new OrderDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<OrderDto> iterator() {				return this;			}		}	}
		public static class OrderFinishDao {			private DBCollection	collection;			public OrderFinishDao(DBCollection collection) {			this.collection = collection;		}			public void save(OrderFinishDto u) {			collection.save(u.toDBObject());		}			public void delete(OrderFinishDto u) {			delete(u.getId());		}			public void delete(String id) {			collection.remove(key(id));		}			public OrderFinishDto get(String id) {			DBObject o = collection.findOne(key(id));			if(o == null) {				return null;			}			OrderFinishDto x = new OrderFinishDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String id) {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);			return o;		}			public OrderFinishDtoCursor find() {			return new OrderFinishDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public OrderFinishDtoCursor findById(String id) {			collection.ensureIndex("id");			BasicDBObject o = new BasicDBObject("id", id);			return new OrderFinishDtoCursor(collection.find(o));		}
		public OrderFinishDtoCursor findByServerId(String serverId) {						BasicDBObject o = new BasicDBObject("serverId", serverId);			return new OrderFinishDtoCursor(collection.find(o));		}
		public OrderFinishDtoCursor findByCount(int count) {						BasicDBObject o = new BasicDBObject("count", count);			return new OrderFinishDtoCursor(collection.find(o));		}
		public OrderFinishDtoCursor findByTime(long time) {						BasicDBObject o = new BasicDBObject("time", time);			return new OrderFinishDtoCursor(collection.find(o));		}
		public OrderFinishDtoCursor findByRechargeUserId(String rechargeUserId) {						BasicDBObject o = new BasicDBObject("rechargeUserId", rechargeUserId);			return new OrderFinishDtoCursor(collection.find(o));		}
		public OrderFinishDtoCursor findByUserId(String userId) {						BasicDBObject o = new BasicDBObject("userId", userId);			return new OrderFinishDtoCursor(collection.find(o));		}
		public OrderFinishDtoCursor findByIsError(boolean isError) {						BasicDBObject o = new BasicDBObject("isError", isError);			return new OrderFinishDtoCursor(collection.find(o));		}
		public OrderFinishDtoCursor findByLastProcessTime(long lastProcessTime) {						BasicDBObject o = new BasicDBObject("lastProcessTime", lastProcessTime);			return new OrderFinishDtoCursor(collection.find(o));		}
		public OrderFinishDtoCursor findByRetrySpace(long retrySpace) {						BasicDBObject o = new BasicDBObject("retrySpace", retrySpace);			return new OrderFinishDtoCursor(collection.find(o));		}
		public OrderFinishDtoCursor findByReason(String reason) {						BasicDBObject o = new BasicDBObject("reason", reason);			return new OrderFinishDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public OrderFinishDto createDTO() {			return new OrderFinishDto();		}			public static class OrderFinishDtoCursor implements Iterator<OrderFinishDto>, Iterable<OrderFinishDto>{				private DBCursor	cursor;				public OrderFinishDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public OrderFinishDto next() {				DBObject next = cursor.next();				OrderFinishDto dto = new OrderFinishDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<OrderFinishDto> iterator() {				return this;			}		}	}
		public static class UserDao {			private DBCollection	collection;			public UserDao(DBCollection collection) {			this.collection = collection;		}			public void save(UserDto u) {			collection.save(u.toDBObject());		}			public void delete(UserDto u) {			delete(u.getId());		}			public void delete(String id) {			collection.remove(key(id));		}			public UserDto get(String id) {			DBObject o = collection.findOne(key(id));			if(o == null) {				return null;			}			UserDto x = new UserDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String id) {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);			return o;		}			public UserDtoCursor find() {			return new UserDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public UserDtoCursor findById(String id) {			collection.ensureIndex("id");			BasicDBObject o = new BasicDBObject("id", id);			return new UserDtoCursor(collection.find(o));		}
		public UserDtoCursor findByEmail(String email) {			collection.ensureIndex("email");			BasicDBObject o = new BasicDBObject("email", email);			return new UserDtoCursor(collection.find(o));		}
		public UserDtoCursor findByQQOpenId(String qQOpenId) {			collection.ensureIndex("qQOpenId");			BasicDBObject o = new BasicDBObject("qQOpenId", qQOpenId);			return new UserDtoCursor(collection.find(o));		}
		public UserDtoCursor findByPwdMD5(String pwdMD5) {						BasicDBObject o = new BasicDBObject("pwdMD5", pwdMD5);			return new UserDtoCursor(collection.find(o));		}
		public UserDtoCursor findByNick(String nick) {						BasicDBObject o = new BasicDBObject("nick", nick);			return new UserDtoCursor(collection.find(o));		}
		public UserDtoCursor findByIsMan(boolean isMan) {						BasicDBObject o = new BasicDBObject("isMan", isMan);			return new UserDtoCursor(collection.find(o));		}
		public UserDtoCursor findByAge(int age) {						BasicDBObject o = new BasicDBObject("age", age);			return new UserDtoCursor(collection.find(o));		}
		public UserDtoCursor findByVb(int vb) {						BasicDBObject o = new BasicDBObject("vb", vb);			return new UserDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public UserDto createDTO() {			return new UserDto();		}			public static class UserDtoCursor implements Iterator<UserDto>, Iterable<UserDto>{				private DBCursor	cursor;				public UserDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public UserDto next() {				DBObject next = cursor.next();				UserDto dto = new UserDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<UserDto> iterator() {				return this;			}		}	}
		public static class OrderDto implements com.cnbizmedia.recharge.IOrderDto{		private String id;
		private String serverId;
		private int count;
		private long time;
		private String rechargeUserId;
		private String userId;
		private boolean isError;
		private long lastProcessTime;
		private long retrySpace;
		private String reason;
		public String getId() {			return this.id;		}
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
		DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();		checkNull(id);
		checkNull(serverId);
		checkNull(rechargeUserId);
		checkNull(userId);
		checkNull(reason);
		o.put("_id", id);
			o.put("id", id);
			o.put("serverId", serverId);
			o.put("count", count);
			o.put("time", time);
			o.put("rechargeUserId", rechargeUserId);
			o.put("userId", userId);
			o.put("isError", isError);
			o.put("lastProcessTime", lastProcessTime);
			o.put("retrySpace", retrySpace);
			o.put("reason", reason);
			return o;		}		void fromDBObject(DBObject o) {		id = getString(o, "id");
		serverId = getString(o, "serverId");
		count = getInteger(o, "count");
		time = getLong(o, "time");
		rechargeUserId = getString(o, "rechargeUserId");
		userId = getString(o, "userId");
		isError = getBoolean(o, "isError");
		lastProcessTime = getLong(o, "lastProcessTime");
		retrySpace = getLong(o, "retrySpace");
		reason = getString(o, "reason");
		}			BasicDBList getBasicDBList(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return new BasicDBList();			}			return (BasicDBList) obj;		}			boolean getBoolean(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return false;			}			return (Boolean) obj;		}				int getInteger(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Integer) obj;		}				byte[] getBytes(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return null;			}			return (byte[]) obj;		}					float getFloat(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Float) obj;		}				String getString(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return "";			}			return (String) obj;		}			long getLong(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Long) obj;		}				double getDouble(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Double) obj;		}			private void checkNull(Object o) {			if(o == null) {				throw new RuntimeException("属性不能为null!");			}		}			@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class OrderFinishDto implements com.cnbizmedia.recharge.IOrderDto{		private String id;
		private String serverId;
		private int count;
		private long time;
		private String rechargeUserId;
		private String userId;
		private boolean isError;
		private long lastProcessTime;
		private long retrySpace;
		private String reason;
		public String getId() {			return this.id;		}
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
		DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();		checkNull(id);
		checkNull(serverId);
		checkNull(rechargeUserId);
		checkNull(userId);
		checkNull(reason);
		o.put("_id", id);
			o.put("id", id);
			o.put("serverId", serverId);
			o.put("count", count);
			o.put("time", time);
			o.put("rechargeUserId", rechargeUserId);
			o.put("userId", userId);
			o.put("isError", isError);
			o.put("lastProcessTime", lastProcessTime);
			o.put("retrySpace", retrySpace);
			o.put("reason", reason);
			return o;		}		void fromDBObject(DBObject o) {		id = getString(o, "id");
		serverId = getString(o, "serverId");
		count = getInteger(o, "count");
		time = getLong(o, "time");
		rechargeUserId = getString(o, "rechargeUserId");
		userId = getString(o, "userId");
		isError = getBoolean(o, "isError");
		lastProcessTime = getLong(o, "lastProcessTime");
		retrySpace = getLong(o, "retrySpace");
		reason = getString(o, "reason");
		}			BasicDBList getBasicDBList(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return new BasicDBList();			}			return (BasicDBList) obj;		}			boolean getBoolean(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return false;			}			return (Boolean) obj;		}				int getInteger(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Integer) obj;		}				byte[] getBytes(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return null;			}			return (byte[]) obj;		}					float getFloat(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Float) obj;		}				String getString(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return "";			}			return (String) obj;		}			long getLong(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Long) obj;		}				double getDouble(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Double) obj;		}			private void checkNull(Object o) {			if(o == null) {				throw new RuntimeException("属性不能为null!");			}		}			@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class UserDto {		private String id;
		private String email;
		private String qQOpenId;
		private String pwdMD5;
		private String nick;
		private boolean isMan;
		private int age;
		private int vb;
		public String getId() {			return this.id;		}
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
		DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();		checkNull(id);
		checkNull(email);
		checkNull(qQOpenId);
		checkNull(pwdMD5);
		checkNull(nick);
		o.put("_id", id);
			o.put("id", id);
			o.put("email", email);
			o.put("qQOpenId", qQOpenId);
			o.put("pwdMD5", pwdMD5);
			o.put("nick", nick);
			o.put("isMan", isMan);
			o.put("age", age);
			o.put("vb", vb);
			return o;		}		void fromDBObject(DBObject o) {		id = getString(o, "id");
		email = getString(o, "email");
		qQOpenId = getString(o, "qQOpenId");
		pwdMD5 = getString(o, "pwdMD5");
		nick = getString(o, "nick");
		isMan = getBoolean(o, "isMan");
		age = getInteger(o, "age");
		vb = getInteger(o, "vb");
		}			BasicDBList getBasicDBList(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return new BasicDBList();			}			return (BasicDBList) obj;		}			boolean getBoolean(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return false;			}			return (Boolean) obj;		}				int getInteger(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Integer) obj;		}				byte[] getBytes(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return null;			}			return (byte[]) obj;		}					float getFloat(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Float) obj;		}				String getString(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return "";			}			return (String) obj;		}			long getLong(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Long) obj;		}				double getDouble(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Double) obj;		}			private void checkNull(Object o) {			if(o == null) {				throw new RuntimeException("属性不能为null!");			}		}			@Override		public String toString() {			return toDBObject().toString();		}	}
}