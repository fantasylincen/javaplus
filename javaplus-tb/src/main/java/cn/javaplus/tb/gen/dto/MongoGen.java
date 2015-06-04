package cn.javaplus.tb.gen.dto;import java.net.UnknownHostException;import java.util.ArrayList;import java.util.HashMap;import java.util.Iterator;import java.util.List;import java.util.Map;import cn.javaplus.exception.UnImplMethodException;import com.mongodb.BasicDBList;import com.mongodb.BasicDBObject;import com.mongodb.DB;import com.mongodb.DBCollection;import com.mongodb.DBCursor;import com.mongodb.DBObject;import com.mongodb.Mongo;import com.mongodb.MongoClient;public class MongoGen {	public static class Lists {		public static<T> List<T> newArrayList() {			return new ArrayList<T>();		}	}	public static interface CollectionFetcher {			DBCollection getCollection(String string);		}			public static interface MongoDbProperties {			/**		 * 数据库名		 */		String getName();				/**		 * 主机地址		 */		String getHost();				/**		 * 端口		 */		int getPort();		}	public static final class Daos {			private static CollectionFetcher	fetcher;		private static MongoDbProperties	properties;		private static Map<String, DBCollection> cache = new HashMap<String, DBCollection>();				public static final void setProperties(MongoDbProperties properties) {			Daos.properties = properties;		}				public static final CollectionFetcher getCollectionFetcher() {			if(fetcher == null)				fetcher = new NormalFetcher();			return fetcher;		}				private static  class NormalFetcher implements CollectionFetcher {			@Override			public DBCollection getCollection(String name) {								Mongo m = getMongo();				DB db = m.getDB(properties.getName());				return db.getCollection(name);			}						private static Mongo mongo;			private static Mongo getMongo() {				if(mongo == null) {					try {						mongo = new MongoClient(properties.getHost(), properties.getPort());					} catch (UnknownHostException e) {						throw cn.javaplus.util.Util.Exception.toRuntimeException(e);					}				}				return mongo;			}		}		private static DBCollection getCollection(String collectionName) {			DBCollection c;			try {				c = cache.get(collectionName);			} catch (NullPointerException e) {				c = null;			}			if (c != null) {				return c;			}			c = getCollectionFetcher().getCollection(collectionName);			cache.put(collectionName, c);			return c;		}		public static RecordDao getRecordDao() {			return new RecordDao(getCollection("Record"));		}
		}
		public static class RecordDao {			private DBCollection	collection;			public RecordDao(DBCollection collection) {			this.collection = collection;		}			public void save(RecordDto u) {			collection.save(u.toDBObject());		}			public void delete(RecordDto u) {			delete(u.getSourceId());		}			public void delete(String sourceId) {			collection.remove(key(sourceId));		}			public RecordDto get(String sourceId) {			DBObject o = collection.findOne(key(sourceId));			if(o == null) {				return null;			}			RecordDto x = new RecordDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String sourceId) {			BasicDBObject o = new BasicDBObject();		o.put("_id", sourceId);			return o;		}			public RecordDtoCursor find() {			return new RecordDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public RecordDtoCursor findBySourceId(String sourceId) {			collection.ensureIndex("sourceId");			BasicDBObject o = new BasicDBObject("sourceId", sourceId);			return new RecordDtoCursor(collection.find(o));		}
		public RecordDtoCursor findByMineId(String mineId) {						BasicDBObject o = new BasicDBObject("mineId", mineId);			return new RecordDtoCursor(collection.find(o));		}
		public RecordDtoCursor findByLastUpdateTime(long lastUpdateTime) {						BasicDBObject o = new BasicDBObject("lastUpdateTime", lastUpdateTime);			return new RecordDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public RecordDto createDTO() {			return new RecordDto();		}			public static class RecordDtoCursor implements Iterator<RecordDto>, Iterable<RecordDto>{				private DBCursor	cursor;				public RecordDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public RecordDto next() {				DBObject next = cursor.next();				RecordDto dto = new RecordDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<RecordDto> iterator() {				return this;			}		}	}
		public static class TbItemDto {		private String price = "";
		private String count = "";
		public String getPrice() {			return this.price;		}
		public String getCount() {			return this.count;		}
		public void setPrice(String price) {			this.price = price;		}
		public void setCount(String count) {			this.count = count;		}
		DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();		checkNull(price);
		checkNull(count);

			o.put("price", price);
			o.put("count", count);
			return o;		}		void fromDBObject(DBObject o) {		price = getString(o, "price");
		count = getString(o, "count");
		}			BasicDBList getBasicDBList(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return new BasicDBList();			}			return (BasicDBList) obj;		}			boolean getBoolean(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return false;			}			return (Boolean) obj;		}				int getInteger(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Integer) obj;		}				byte[] getBytes(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return null;			}			return (byte[]) obj;		}					float getFloat(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Float) obj;		}				String getString(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return "";			}			return (String) obj;		}			long getLong(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Long) obj;		}				double getDouble(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Double) obj;		}			private void checkNull(Object o) {			if(o == null) {				throw new RuntimeException("属性不能为null!");			}		}			@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class RecordDto {		private String sourceId = "";
		private String mineId = "";
		private TbItemDto source = null;
		private TbItemDto mine = null;
		private long lastUpdateTime = 0;
		public String getSourceId() {			return this.sourceId;		}
		public String getMineId() {			return this.mineId;		}
		public TbItemDto getSource() {			return this.source;		}
		public TbItemDto getMine() {			return this.mine;		}
		public long getLastUpdateTime() {			return this.lastUpdateTime;		}
		public void setSourceId(String sourceId) {			this.sourceId = sourceId;		}
		public void setMineId(String mineId) {			this.mineId = mineId;		}
		public void setSource(TbItemDto source) {			this.source = source;		}
		public void setMine(TbItemDto mine) {			this.mine = mine;		}
		public void setLastUpdateTime(long lastUpdateTime) {			this.lastUpdateTime = lastUpdateTime;		}
		DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();		checkNull(sourceId);
		checkNull(mineId);
		o.put("_id", sourceId);
			o.put("sourceId", sourceId);
			o.put("mineId", mineId);
			if(source != null) {				o.put("source", source.toDBObject());			}
			if(mine != null) {				o.put("mine", mine.toDBObject());			}
			o.put("lastUpdateTime", lastUpdateTime);
			return o;		}		void fromDBObject(DBObject o) {		sourceId = getString(o, "sourceId");
		mineId = getString(o, "mineId");
			DBObject source_dto = (DBObject) o.get("source");			if (source_dto != null) {				source = new TbItemDto();				source.fromDBObject(source_dto);			}
			DBObject mine_dto = (DBObject) o.get("mine");			if (mine_dto != null) {				mine = new TbItemDto();				mine.fromDBObject(mine_dto);			}
		lastUpdateTime = getLong(o, "lastUpdateTime");
		}			BasicDBList getBasicDBList(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return new BasicDBList();			}			return (BasicDBList) obj;		}			boolean getBoolean(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return false;			}			return (Boolean) obj;		}				int getInteger(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Integer) obj;		}				byte[] getBytes(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return null;			}			return (byte[]) obj;		}					float getFloat(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Float) obj;		}				String getString(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return "";			}			return (String) obj;		}			long getLong(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Long) obj;		}				double getDouble(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Double) obj;		}			private void checkNull(Object o) {			if(o == null) {				throw new RuntimeException("属性不能为null!");			}		}			@Override		public String toString() {			return toDBObject().toString();		}	}
}