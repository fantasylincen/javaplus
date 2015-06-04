package mongo.gen;import java.util.ArrayList;import java.util.HashMap;import java.util.Iterator;import java.util.List;import java.util.Map;import cn.javaplus.exception.UnImplMethodException;import com.mongodb.BasicDBList;import com.mongodb.BasicDBObject;import com.mongodb.DBCollection;import com.mongodb.DBCursor;import com.mongodb.DBObject;public class MongoGen {	public static class Lists {		public static<T> List<T> newArrayList() {			return new ArrayList<T>();		}	}	public static interface CollectionFetcher {			DBCollection getCollection(String string);		}	public static final class Daos {			private static CollectionFetcher	fetcher;		private static Map<String, DBCollection> cache = new HashMap<String, DBCollection>();		private static DBCollection getCollection(String collectionName) {			DBCollection c = cache.get(collectionName);			if (c != null) {				return c;			}			c = fetcher.getCollection(collectionName);			cache.put(collectionName, c);			return c;		}		public static AchieveTaskDao getAchieveTaskDao() {			return new AchieveTaskDao(getCollection("AchieveTask"));		}
		public static BossRankInfoHistoryDao getBossRankInfoHistoryDao() {			return new BossRankInfoHistoryDao(getCollection("BossRankInfoHistory"));		}
		public static ColdDownDao getColdDownDao() {			return new ColdDownDao(getCollection("ColdDown"));		}
		public static DailyTaskDao getDailyTaskDao() {			return new DailyTaskDao(getCollection("DailyTask"));		}
		public static EnemyDao getEnemyDao() {			return new EnemyDao(getCollection("Enemy"));		}
		public static FeedBackDao getFeedBackDao() {			return new FeedBackDao(getCollection("FeedBack"));		}
		public static FeedBack2Dao getFeedBack2Dao() {			return new FeedBack2Dao(getCollection("FeedBack2"));		}
		public static FighterHadDao getFighterHadDao() {			return new FighterHadDao(getCollection("FighterHad"));		}
		public static FriendDao getFriendDao() {			return new FriendDao(getCollection("Friend"));		}
		public static HeishiDao getHeishiDao() {			return new HeishiDao(getCollection("Heishi"));		}
		public static HeiShiStoreDao getHeiShiStoreDao() {			return new HeiShiStoreDao(getCollection("HeiShiStore"));		}
		public static InviteCodeDao getInviteCodeDao() {			return new InviteCodeDao(getCollection("InviteCode"));		}
		public static InviteUsersDao getInviteUsersDao() {			return new InviteUsersDao(getCollection("InviteUsers"));		}
		public static KeyValueDao getKeyValueDao() {			return new KeyValueDao(getCollection("KeyValue"));		}
		public static KeyValueDataDao getKeyValueDataDao() {			return new KeyValueDataDao(getCollection("KeyValueData"));		}
		public static LogSnatchDao getLogSnatchDao() {			return new LogSnatchDao(getCollection("LogSnatch"));		}
		public static MxzTokenDao getMxzTokenDao() {			return new MxzTokenDao(getCollection("MxzToken"));		}
		public static NvwaDao getNvwaDao() {			return new NvwaDao(getCollection("Nvwa"));		}
		public static PropAddLogDao getPropAddLogDao() {			return new PropAddLogDao(getCollection("PropAddLog"));		}
		public static PropConsumeLogDao getPropConsumeLogDao() {			return new PropConsumeLogDao(getCollection("PropConsumeLog"));		}
		public static PvpWarSituationDao getPvpWarSituationDao() {			return new PvpWarSituationDao(getCollection("PvpWarSituation"));		}
		public static UserChatRecordDao getUserChatRecordDao() {			return new UserChatRecordDao(getCollection("UserChatRecord"));		}
		public static UserCountersDao getUserCountersDao() {			return new UserCountersDao(getCollection("UserCounters"));		}
		public static UserCountersAllDao getUserCountersAllDao() {			return new UserCountersAllDao(getCollection("UserCountersAll"));		}
		public static UserFriendRequestDao getUserFriendRequestDao() {			return new UserFriendRequestDao(getCollection("UserFriendRequest"));		}
		public static UserGuideDao getUserGuideDao() {			return new UserGuideDao(getCollection("UserGuide"));		}
		public static WorldChatRecordDao getWorldChatRecordDao() {			return new WorldChatRecordDao(getCollection("WorldChatRecord"));		}
		public static WorldChatRecordAllDao getWorldChatRecordAllDao() {			return new WorldChatRecordAllDao(getCollection("WorldChatRecordAll"));		}
		public static ZbsrDao getZbsrDao() {			return new ZbsrDao(getCollection("Zbsr"));		}
			public static void setCollectionFetcher(CollectionFetcher fetcher) {			Daos.fetcher = fetcher;		}		public static CollectionFetcher getFetcher() {			return fetcher;		}	}
		public static class AchieveTaskDao implements cn.mxz.base.task.TaskDAO<AchieveTaskDto>{			private DBCollection	collection;			public AchieveTaskDao(DBCollection collection) {			this.collection = collection;		}			public void save(AchieveTaskDto u) {			collection.save(u.toDBObject());		}			public void delete(AchieveTaskDto u) {			collection.remove(u.toDBObject());		}			public void delete(int taskId, String uname) {			collection.remove(key(taskId, uname));		}			public AchieveTaskDto get(int taskId, String uname) {			DBObject o = collection.findOne(key(taskId, uname));			if(o == null) {				return null;			}			AchieveTaskDto x = new AchieveTaskDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(int taskId, String uname) {			BasicDBObject o = new BasicDBObject();		o.put("_id", taskId + ":" + uname);			return o;		}			public AchieveTaskDtoCursor find() {			return new AchieveTaskDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public AchieveTaskDtoCursor findByTaskId(int taskId) {			collection.ensureIndex("taskId");			BasicDBObject o = new BasicDBObject("taskId", taskId);			return new AchieveTaskDtoCursor(collection.find(o));		}
		public AchieveTaskDtoCursor findByUname(String uname) {			collection.ensureIndex("uname");			BasicDBObject o = new BasicDBObject("uname", uname);			return new AchieveTaskDtoCursor(collection.find(o));		}
		public AchieveTaskDtoCursor findByFinishtimes(int finishtimes) {						BasicDBObject o = new BasicDBObject("finishtimes", finishtimes);			return new AchieveTaskDtoCursor(collection.find(o));		}
		public AchieveTaskDtoCursor findByIsDraw(boolean isDraw) {						BasicDBObject o = new BasicDBObject("isDraw", isDraw);			return new AchieveTaskDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public AchieveTaskDto createDTO() {			return new AchieveTaskDto();		}			public static class AchieveTaskDtoCursor implements Iterator<AchieveTaskDto>, Iterable<AchieveTaskDto>{				private DBCursor	cursor;				public AchieveTaskDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public AchieveTaskDto next() {				DBObject next = cursor.next();				AchieveTaskDto dto = new AchieveTaskDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<AchieveTaskDto> iterator() {				return this;			}		}	}
		public static class BossRankInfoHistoryDao {			private DBCollection	collection;			public BossRankInfoHistoryDao(DBCollection collection) {			this.collection = collection;		}			public void save(BossRankInfoHistoryDto u) {			collection.save(u.toDBObject());		}			public void delete(BossRankInfoHistoryDto u) {			collection.remove(u.toDBObject());		}			public void delete() {			collection.remove(key());		}			public BossRankInfoHistoryDto get() {			DBObject o = collection.findOne(key());			if(o == null) {				return null;			}			BossRankInfoHistoryDto x = new BossRankInfoHistoryDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key() {			BasicDBObject o = new BasicDBObject();			return o;		}			public BossRankInfoHistoryDtoCursor find() {			return new BossRankInfoHistoryDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public BossRankInfoHistoryDtoCursor findByBossHpMax(int bossHpMax) {						BasicDBObject o = new BasicDBObject("bossHpMax", bossHpMax);			return new BossRankInfoHistoryDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public BossRankInfoHistoryDto createDTO() {			return new BossRankInfoHistoryDto();		}			public static class BossRankInfoHistoryDtoCursor implements Iterator<BossRankInfoHistoryDto>, Iterable<BossRankInfoHistoryDto>{				private DBCursor	cursor;				public BossRankInfoHistoryDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public BossRankInfoHistoryDto next() {				DBObject next = cursor.next();				BossRankInfoHistoryDto dto = new BossRankInfoHistoryDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<BossRankInfoHistoryDto> iterator() {				return this;			}		}	}
		public static class ColdDownDao {			private DBCollection	collection;			public ColdDownDao(DBCollection collection) {			this.collection = collection;		}			public void save(ColdDownDto u) {			collection.save(u.toDBObject());		}			public void delete(ColdDownDto u) {			collection.remove(u.toDBObject());		}			public void delete(String uname, int index) {			collection.remove(key(uname, index));		}			public ColdDownDto get(String uname, int index) {			DBObject o = collection.findOne(key(uname, index));			if(o == null) {				return null;			}			ColdDownDto x = new ColdDownDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String uname, int index) {			BasicDBObject o = new BasicDBObject();		o.put("_id", uname + ":" + index);			return o;		}			public ColdDownDtoCursor find() {			return new ColdDownDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public ColdDownDtoCursor findByUname(String uname) {			collection.ensureIndex("uname");			BasicDBObject o = new BasicDBObject("uname", uname);			return new ColdDownDtoCursor(collection.find(o));		}
		public ColdDownDtoCursor findByIndex(int index) {			collection.ensureIndex("index");			BasicDBObject o = new BasicDBObject("index", index);			return new ColdDownDtoCursor(collection.find(o));		}
		public ColdDownDtoCursor findByEndTime(long endTime) {						BasicDBObject o = new BasicDBObject("endTime", endTime);			return new ColdDownDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public ColdDownDto createDTO() {			return new ColdDownDto();		}			public static class ColdDownDtoCursor implements Iterator<ColdDownDto>, Iterable<ColdDownDto>{				private DBCursor	cursor;				public ColdDownDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public ColdDownDto next() {				DBObject next = cursor.next();				ColdDownDto dto = new ColdDownDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<ColdDownDto> iterator() {				return this;			}		}	}
		public static class DailyTaskDao implements cn.mxz.base.task.TaskDAO<DailyTaskDto>{			private DBCollection	collection;			public DailyTaskDao(DBCollection collection) {			this.collection = collection;		}			public void save(DailyTaskDto u) {			collection.save(u.toDBObject());		}			public void delete(DailyTaskDto u) {			collection.remove(u.toDBObject());		}			public void delete(int taskId, String uname) {			collection.remove(key(taskId, uname));		}			public DailyTaskDto get(int taskId, String uname) {			DBObject o = collection.findOne(key(taskId, uname));			if(o == null) {				return null;			}			DailyTaskDto x = new DailyTaskDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(int taskId, String uname) {			BasicDBObject o = new BasicDBObject();		o.put("_id", taskId + ":" + uname);			return o;		}			public DailyTaskDtoCursor find() {			return new DailyTaskDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public DailyTaskDtoCursor findByTaskId(int taskId) {			collection.ensureIndex("taskId");			BasicDBObject o = new BasicDBObject("taskId", taskId);			return new DailyTaskDtoCursor(collection.find(o));		}
		public DailyTaskDtoCursor findByUname(String uname) {			collection.ensureIndex("uname");			BasicDBObject o = new BasicDBObject("uname", uname);			return new DailyTaskDtoCursor(collection.find(o));		}
		public DailyTaskDtoCursor findByFinishtimes(int finishtimes) {						BasicDBObject o = new BasicDBObject("finishtimes", finishtimes);			return new DailyTaskDtoCursor(collection.find(o));		}
		public DailyTaskDtoCursor findByIsDraw(boolean isDraw) {						BasicDBObject o = new BasicDBObject("isDraw", isDraw);			return new DailyTaskDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public DailyTaskDto createDTO() {			return new DailyTaskDto();		}			public static class DailyTaskDtoCursor implements Iterator<DailyTaskDto>, Iterable<DailyTaskDto>{				private DBCursor	cursor;				public DailyTaskDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public DailyTaskDto next() {				DBObject next = cursor.next();				DailyTaskDto dto = new DailyTaskDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<DailyTaskDto> iterator() {				return this;			}		}	}
		public static class EnemyDao {			private DBCollection	collection;			public EnemyDao(DBCollection collection) {			this.collection = collection;		}			public void save(EnemyDto u) {			collection.save(u.toDBObject());		}			public void delete(EnemyDto u) {			collection.remove(u.toDBObject());		}			public void delete(String uname, String enemyId) {			collection.remove(key(uname, enemyId));		}			public EnemyDto get(String uname, String enemyId) {			DBObject o = collection.findOne(key(uname, enemyId));			if(o == null) {				return null;			}			EnemyDto x = new EnemyDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String uname, String enemyId) {			BasicDBObject o = new BasicDBObject();		o.put("_id", uname + ":" + enemyId);			return o;		}			public EnemyDtoCursor find() {			return new EnemyDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public EnemyDtoCursor findByUname(String uname) {			collection.ensureIndex("uname");			BasicDBObject o = new BasicDBObject("uname", uname);			return new EnemyDtoCursor(collection.find(o));		}
		public EnemyDtoCursor findByEnemyId(String enemyId) {			collection.ensureIndex("enemyId");			BasicDBObject o = new BasicDBObject("enemyId", enemyId);			return new EnemyDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public EnemyDto createDTO() {			return new EnemyDto();		}			public static class EnemyDtoCursor implements Iterator<EnemyDto>, Iterable<EnemyDto>{				private DBCursor	cursor;				public EnemyDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public EnemyDto next() {				DBObject next = cursor.next();				EnemyDto dto = new EnemyDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<EnemyDto> iterator() {				return this;			}		}	}
		public static class FeedBackDao {			private DBCollection	collection;			public FeedBackDao(DBCollection collection) {			this.collection = collection;		}			public void save(FeedBackDto u) {			collection.save(u.toDBObject());		}			public void delete(FeedBackDto u) {			collection.remove(u.toDBObject());		}			public void delete(String uname) {			collection.remove(key(uname));		}			public FeedBackDto get(String uname) {			DBObject o = collection.findOne(key(uname));			if(o == null) {				return null;			}			FeedBackDto x = new FeedBackDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String uname) {			BasicDBObject o = new BasicDBObject();		o.put("_id", uname);			return o;		}			public FeedBackDtoCursor find() {			return new FeedBackDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public FeedBackDtoCursor findByUname(String uname) {			collection.ensureIndex("uname");			BasicDBObject o = new BasicDBObject("uname", uname);			return new FeedBackDtoCursor(collection.find(o));		}
		public FeedBackDtoCursor findByRechargeGold(int rechargeGold) {						BasicDBObject o = new BasicDBObject("rechargeGold", rechargeGold);			return new FeedBackDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public FeedBackDto createDTO() {			return new FeedBackDto();		}			public static class FeedBackDtoCursor implements Iterator<FeedBackDto>, Iterable<FeedBackDto>{				private DBCursor	cursor;				public FeedBackDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public FeedBackDto next() {				DBObject next = cursor.next();				FeedBackDto dto = new FeedBackDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<FeedBackDto> iterator() {				return this;			}		}	}
		public static class FeedBack2Dao {			private DBCollection	collection;			public FeedBack2Dao(DBCollection collection) {			this.collection = collection;		}			public void save(FeedBack2Dto u) {			collection.save(u.toDBObject());		}			public void delete(FeedBack2Dto u) {			collection.remove(u.toDBObject());		}			public void delete(String uname) {			collection.remove(key(uname));		}			public FeedBack2Dto get(String uname) {			DBObject o = collection.findOne(key(uname));			if(o == null) {				return null;			}			FeedBack2Dto x = new FeedBack2Dto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String uname) {			BasicDBObject o = new BasicDBObject();		o.put("_id", uname);			return o;		}			public FeedBack2DtoCursor find() {			return new FeedBack2DtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public FeedBack2DtoCursor findByUname(String uname) {			collection.ensureIndex("uname");			BasicDBObject o = new BasicDBObject("uname", uname);			return new FeedBack2DtoCursor(collection.find(o));		}
		public FeedBack2DtoCursor findByRechargeGold(int rechargeGold) {						BasicDBObject o = new BasicDBObject("rechargeGold", rechargeGold);			return new FeedBack2DtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public FeedBack2Dto createDTO() {			return new FeedBack2Dto();		}			public static class FeedBack2DtoCursor implements Iterator<FeedBack2Dto>, Iterable<FeedBack2Dto>{				private DBCursor	cursor;				public FeedBack2DtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public FeedBack2Dto next() {				DBObject next = cursor.next();				FeedBack2Dto dto = new FeedBack2Dto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<FeedBack2Dto> iterator() {				return this;			}		}	}
		public static class FighterHadDao {			private DBCollection	collection;			public FighterHadDao(DBCollection collection) {			this.collection = collection;		}			public void save(FighterHadDto u) {			collection.save(u.toDBObject());		}			public void delete(FighterHadDto u) {			collection.remove(u.toDBObject());		}			public void delete(String uname, int fighterTypeId) {			collection.remove(key(uname, fighterTypeId));		}			public FighterHadDto get(String uname, int fighterTypeId) {			DBObject o = collection.findOne(key(uname, fighterTypeId));			if(o == null) {				return null;			}			FighterHadDto x = new FighterHadDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String uname, int fighterTypeId) {			BasicDBObject o = new BasicDBObject();		o.put("_id", uname + ":" + fighterTypeId);			return o;		}			public FighterHadDtoCursor find() {			return new FighterHadDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public FighterHadDtoCursor findByUname(String uname) {			collection.ensureIndex("uname");			BasicDBObject o = new BasicDBObject("uname", uname);			return new FighterHadDtoCursor(collection.find(o));		}
		public FighterHadDtoCursor findByFighterTypeId(int fighterTypeId) {			collection.ensureIndex("fighterTypeId");			BasicDBObject o = new BasicDBObject("fighterTypeId", fighterTypeId);			return new FighterHadDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public FighterHadDto createDTO() {			return new FighterHadDto();		}			public static class FighterHadDtoCursor implements Iterator<FighterHadDto>, Iterable<FighterHadDto>{				private DBCursor	cursor;				public FighterHadDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public FighterHadDto next() {				DBObject next = cursor.next();				FighterHadDto dto = new FighterHadDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<FighterHadDto> iterator() {				return this;			}		}	}
		public static class FriendDao {			private DBCollection	collection;			public FriendDao(DBCollection collection) {			this.collection = collection;		}			public void save(FriendDto u) {			collection.save(u.toDBObject());		}			public void delete(FriendDto u) {			collection.remove(u.toDBObject());		}			public void delete(String uname, String friendName) {			collection.remove(key(uname, friendName));		}			public FriendDto get(String uname, String friendName) {			DBObject o = collection.findOne(key(uname, friendName));			if(o == null) {				return null;			}			FriendDto x = new FriendDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String uname, String friendName) {			BasicDBObject o = new BasicDBObject();		o.put("_id", uname + ":" + friendName);			return o;		}			public FriendDtoCursor find() {			return new FriendDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public FriendDtoCursor findByUname(String uname) {			collection.ensureIndex("uname");			BasicDBObject o = new BasicDBObject("uname", uname);			return new FriendDtoCursor(collection.find(o));		}
		public FriendDtoCursor findByFriendName(String friendName) {			collection.ensureIndex("friendName");			BasicDBObject o = new BasicDBObject("friendName", friendName);			return new FriendDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public FriendDto createDTO() {			return new FriendDto();		}			public static class FriendDtoCursor implements Iterator<FriendDto>, Iterable<FriendDto>{				private DBCursor	cursor;				public FriendDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public FriendDto next() {				DBObject next = cursor.next();				FriendDto dto = new FriendDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<FriendDto> iterator() {				return this;			}		}	}
		public static class HeishiDao {			private DBCollection	collection;			public HeishiDao(DBCollection collection) {			this.collection = collection;		}			public void save(HeishiDto u) {			collection.save(u.toDBObject());		}			public void delete(HeishiDto u) {			collection.remove(u.toDBObject());		}			public void delete(String userName) {			collection.remove(key(userName));		}			public HeishiDto get(String userName) {			DBObject o = collection.findOne(key(userName));			if(o == null) {				return null;			}			HeishiDto x = new HeishiDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String userName) {			BasicDBObject o = new BasicDBObject();		o.put("_id", userName);			return o;		}			public HeishiDtoCursor find() {			return new HeishiDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public HeishiDtoCursor findByUserName(String userName) {			collection.ensureIndex("userName");			BasicDBObject o = new BasicDBObject("userName", userName);			return new HeishiDtoCursor(collection.find(o));		}
		public HeishiDtoCursor findByQsjs(int qsjs) {						BasicDBObject o = new BasicDBObject("qsjs", qsjs);			return new HeishiDtoCursor(collection.find(o));		}
		public HeishiDtoCursor findByBuyStr(String buyStr) {						BasicDBObject o = new BasicDBObject("buyStr", buyStr);			return new HeishiDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public HeishiDto createDTO() {			return new HeishiDto();		}			public static class HeishiDtoCursor implements Iterator<HeishiDto>, Iterable<HeishiDto>{				private DBCursor	cursor;				public HeishiDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public HeishiDto next() {				DBObject next = cursor.next();				HeishiDto dto = new HeishiDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<HeishiDto> iterator() {				return this;			}		}	}
		public static class HeiShiStoreDao {			private DBCollection	collection;			public HeiShiStoreDao(DBCollection collection) {			this.collection = collection;		}			public void save(HeiShiStoreDto u) {			collection.save(u.toDBObject());		}			public void delete(HeiShiStoreDto u) {			collection.remove(u.toDBObject());		}			public void delete(String uname) {			collection.remove(key(uname));		}			public HeiShiStoreDto get(String uname) {			DBObject o = collection.findOne(key(uname));			if(o == null) {				return null;			}			HeiShiStoreDto x = new HeiShiStoreDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String uname) {			BasicDBObject o = new BasicDBObject();		o.put("_id", uname);			return o;		}			public HeiShiStoreDtoCursor find() {			return new HeiShiStoreDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public HeiShiStoreDtoCursor findByUname(String uname) {			collection.ensureIndex("uname");			BasicDBObject o = new BasicDBObject("uname", uname);			return new HeiShiStoreDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public HeiShiStoreDto createDTO() {			return new HeiShiStoreDto();		}			public static class HeiShiStoreDtoCursor implements Iterator<HeiShiStoreDto>, Iterable<HeiShiStoreDto>{				private DBCursor	cursor;				public HeiShiStoreDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public HeiShiStoreDto next() {				DBObject next = cursor.next();				HeiShiStoreDto dto = new HeiShiStoreDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<HeiShiStoreDto> iterator() {				return this;			}		}	}
		public static class InviteCodeDao {			private DBCollection	collection;			public InviteCodeDao(DBCollection collection) {			this.collection = collection;		}			public void save(InviteCodeDto u) {			collection.save(u.toDBObject());		}			public void delete(InviteCodeDto u) {			collection.remove(u.toDBObject());		}			public void delete(String uname) {			collection.remove(key(uname));		}			public InviteCodeDto get(String uname) {			DBObject o = collection.findOne(key(uname));			if(o == null) {				return null;			}			InviteCodeDto x = new InviteCodeDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String uname) {			BasicDBObject o = new BasicDBObject();		o.put("_id", uname);			return o;		}			public InviteCodeDtoCursor find() {			return new InviteCodeDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public InviteCodeDtoCursor findByUname(String uname) {			collection.ensureIndex("uname");			BasicDBObject o = new BasicDBObject("uname", uname);			return new InviteCodeDtoCursor(collection.find(o));		}
		public InviteCodeDtoCursor findByCode(String code) {			collection.ensureIndex("code");			BasicDBObject o = new BasicDBObject("code", code);			return new InviteCodeDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public InviteCodeDto createDTO() {			return new InviteCodeDto();		}			public static class InviteCodeDtoCursor implements Iterator<InviteCodeDto>, Iterable<InviteCodeDto>{				private DBCursor	cursor;				public InviteCodeDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public InviteCodeDto next() {				DBObject next = cursor.next();				InviteCodeDto dto = new InviteCodeDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<InviteCodeDto> iterator() {				return this;			}		}	}
		public static class InviteUsersDao {			private DBCollection	collection;			public InviteUsersDao(DBCollection collection) {			this.collection = collection;		}			public void save(InviteUsersDto u) {			collection.save(u.toDBObject());		}			public void delete(InviteUsersDto u) {			collection.remove(u.toDBObject());		}			public void delete(String a) {			collection.remove(key(a));		}			public InviteUsersDto get(String a) {			DBObject o = collection.findOne(key(a));			if(o == null) {				return null;			}			InviteUsersDto x = new InviteUsersDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String a) {			BasicDBObject o = new BasicDBObject();		o.put("_id", a);			return o;		}			public InviteUsersDtoCursor find() {			return new InviteUsersDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public InviteUsersDtoCursor findByA(String a) {			collection.ensureIndex("a");			BasicDBObject o = new BasicDBObject("a", a);			return new InviteUsersDtoCursor(collection.find(o));		}
		public InviteUsersDtoCursor findByB(String b) {			collection.ensureIndex("b");			BasicDBObject o = new BasicDBObject("b", b);			return new InviteUsersDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public InviteUsersDto createDTO() {			return new InviteUsersDto();		}			public static class InviteUsersDtoCursor implements Iterator<InviteUsersDto>, Iterable<InviteUsersDto>{				private DBCursor	cursor;				public InviteUsersDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public InviteUsersDto next() {				DBObject next = cursor.next();				InviteUsersDto dto = new InviteUsersDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<InviteUsersDto> iterator() {				return this;			}		}	}
		public static class KeyValueDao {			private DBCollection	collection;			public KeyValueDao(DBCollection collection) {			this.collection = collection;		}			public void save(KeyValueDto u) {			collection.save(u.toDBObject());		}			public void delete(KeyValueDto u) {			collection.remove(u.toDBObject());		}			public void delete(String k) {			collection.remove(key(k));		}			public KeyValueDto get(String k) {			DBObject o = collection.findOne(key(k));			if(o == null) {				return null;			}			KeyValueDto x = new KeyValueDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String k) {			BasicDBObject o = new BasicDBObject();		o.put("_id", k);			return o;		}			public KeyValueDtoCursor find() {			return new KeyValueDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public KeyValueDtoCursor findByK(String k) {			collection.ensureIndex("k");			BasicDBObject o = new BasicDBObject("k", k);			return new KeyValueDtoCursor(collection.find(o));		}
		public KeyValueDtoCursor findByV(String v) {						BasicDBObject o = new BasicDBObject("v", v);			return new KeyValueDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public KeyValueDto createDTO() {			return new KeyValueDto();		}			public static class KeyValueDtoCursor implements Iterator<KeyValueDto>, Iterable<KeyValueDto>{				private DBCursor	cursor;				public KeyValueDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public KeyValueDto next() {				DBObject next = cursor.next();				KeyValueDto dto = new KeyValueDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<KeyValueDto> iterator() {				return this;			}		}	}
		public static class KeyValueDataDao {			private DBCollection	collection;			public KeyValueDataDao(DBCollection collection) {			this.collection = collection;		}			public void save(KeyValueDataDto u) {			collection.save(u.toDBObject());		}			public void delete(KeyValueDataDto u) {			collection.remove(u.toDBObject());		}			public void delete(String uname) {			collection.remove(key(uname));		}			public KeyValueDataDto get(String uname) {			DBObject o = collection.findOne(key(uname));			if(o == null) {				return null;			}			KeyValueDataDto x = new KeyValueDataDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String uname) {			BasicDBObject o = new BasicDBObject();		o.put("_id", uname);			return o;		}			public KeyValueDataDtoCursor find() {			return new KeyValueDataDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public KeyValueDataDtoCursor findByUname(String uname) {			collection.ensureIndex("uname");			BasicDBObject o = new BasicDBObject("uname", uname);			return new KeyValueDataDtoCursor(collection.find(o));		}
		public KeyValueDataDtoCursor findByData(String data) {						BasicDBObject o = new BasicDBObject("data", data);			return new KeyValueDataDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public KeyValueDataDto createDTO() {			return new KeyValueDataDto();		}			public static class KeyValueDataDtoCursor implements Iterator<KeyValueDataDto>, Iterable<KeyValueDataDto>{				private DBCursor	cursor;				public KeyValueDataDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public KeyValueDataDto next() {				DBObject next = cursor.next();				KeyValueDataDto dto = new KeyValueDataDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<KeyValueDataDto> iterator() {				return this;			}		}	}
		public static class LogSnatchDao {			private DBCollection	collection;			public LogSnatchDao(DBCollection collection) {			this.collection = collection;		}			public void save(LogSnatchDto u) {			collection.save(u.toDBObject());		}			public void delete(LogSnatchDto u) {			collection.remove(u.toDBObject());		}			public void delete(int id) {			collection.remove(key(id));		}			public LogSnatchDto get(int id) {			DBObject o = collection.findOne(key(id));			if(o == null) {				return null;			}			LogSnatchDto x = new LogSnatchDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(int id) {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);			return o;		}			public LogSnatchDtoCursor find() {			return new LogSnatchDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public LogSnatchDtoCursor findById(int id) {			collection.ensureIndex("id");			BasicDBObject o = new BasicDBObject("id", id);			return new LogSnatchDtoCursor(collection.find(o));		}
		public LogSnatchDtoCursor findByUname(String uname) {						BasicDBObject o = new BasicDBObject("uname", uname);			return new LogSnatchDtoCursor(collection.find(o));		}
		public LogSnatchDtoCursor findByDatatype(int datatype) {						BasicDBObject o = new BasicDBObject("datatype", datatype);			return new LogSnatchDtoCursor(collection.find(o));		}
		public LogSnatchDtoCursor findByNub(int nub) {						BasicDBObject o = new BasicDBObject("nub", nub);			return new LogSnatchDtoCursor(collection.find(o));		}
		public LogSnatchDtoCursor findByRobber(String robber) {						BasicDBObject o = new BasicDBObject("robber", robber);			return new LogSnatchDtoCursor(collection.find(o));		}
		public LogSnatchDtoCursor findByTime(int time) {						BasicDBObject o = new BasicDBObject("time", time);			return new LogSnatchDtoCursor(collection.find(o));		}
		public LogSnatchDtoCursor findByWarsituationid(int warsituationid) {						BasicDBObject o = new BasicDBObject("warsituationid", warsituationid);			return new LogSnatchDtoCursor(collection.find(o));		}
		public LogSnatchDtoCursor findByIswin(boolean iswin) {						BasicDBObject o = new BasicDBObject("iswin", iswin);			return new LogSnatchDtoCursor(collection.find(o));		}
		public LogSnatchDtoCursor findByIsSaw(boolean isSaw) {						BasicDBObject o = new BasicDBObject("isSaw", isSaw);			return new LogSnatchDtoCursor(collection.find(o));		}
		public LogSnatchDtoCursor findByIsSnatchSuccess(boolean isSnatchSuccess) {						BasicDBObject o = new BasicDBObject("isSnatchSuccess", isSnatchSuccess);			return new LogSnatchDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public LogSnatchDto createDTO() {			return new LogSnatchDto();		}			public static class LogSnatchDtoCursor implements Iterator<LogSnatchDto>, Iterable<LogSnatchDto>{				private DBCursor	cursor;				public LogSnatchDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public LogSnatchDto next() {				DBObject next = cursor.next();				LogSnatchDto dto = new LogSnatchDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<LogSnatchDto> iterator() {				return this;			}		}	}
		public static class MxzTokenDao {			private DBCollection	collection;			public MxzTokenDao(DBCollection collection) {			this.collection = collection;		}			public void save(MxzTokenDto u) {			collection.save(u.toDBObject());		}			public void delete(MxzTokenDto u) {			collection.remove(u.toDBObject());		}			public void delete(String lineKongUname, String token) {			collection.remove(key(lineKongUname, token));		}			public MxzTokenDto get(String lineKongUname, String token) {			DBObject o = collection.findOne(key(lineKongUname, token));			if(o == null) {				return null;			}			MxzTokenDto x = new MxzTokenDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String lineKongUname, String token) {			BasicDBObject o = new BasicDBObject();		o.put("_id", lineKongUname + ":" + token);			return o;		}			public MxzTokenDtoCursor find() {			return new MxzTokenDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public MxzTokenDtoCursor findByLineKongUname(String lineKongUname) {			collection.ensureIndex("lineKongUname");			BasicDBObject o = new BasicDBObject("lineKongUname", lineKongUname);			return new MxzTokenDtoCursor(collection.find(o));		}
		public MxzTokenDtoCursor findByToken(String token) {			collection.ensureIndex("token");			BasicDBObject o = new BasicDBObject("token", token);			return new MxzTokenDtoCursor(collection.find(o));		}
		public MxzTokenDtoCursor findByUserId(String userId) {						BasicDBObject o = new BasicDBObject("userId", userId);			return new MxzTokenDtoCursor(collection.find(o));		}
		public MxzTokenDtoCursor findByGenerateTime(long generateTime) {						BasicDBObject o = new BasicDBObject("generateTime", generateTime);			return new MxzTokenDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public MxzTokenDto createDTO() {			return new MxzTokenDto();		}			public static class MxzTokenDtoCursor implements Iterator<MxzTokenDto>, Iterable<MxzTokenDto>{				private DBCursor	cursor;				public MxzTokenDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public MxzTokenDto next() {				DBObject next = cursor.next();				MxzTokenDto dto = new MxzTokenDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<MxzTokenDto> iterator() {				return this;			}		}	}
		public static class NvwaDao {			private DBCollection	collection;			public NvwaDao(DBCollection collection) {			this.collection = collection;		}			public void save(NvwaDto u) {			collection.save(u.toDBObject());		}			public void delete(NvwaDto u) {			collection.remove(u.toDBObject());		}			public void delete(String uname) {			collection.remove(key(uname));		}			public NvwaDto get(String uname) {			DBObject o = collection.findOne(key(uname));			if(o == null) {				return null;			}			NvwaDto x = new NvwaDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String uname) {			BasicDBObject o = new BasicDBObject();		o.put("_id", uname);			return o;		}			public NvwaDtoCursor find() {			return new NvwaDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public NvwaDtoCursor findByUname(String uname) {			collection.ensureIndex("uname");			BasicDBObject o = new BasicDBObject("uname", uname);			return new NvwaDtoCursor(collection.find(o));		}
		public NvwaDtoCursor findByBuyCount(int buyCount) {						BasicDBObject o = new BasicDBObject("buyCount", buyCount);			return new NvwaDtoCursor(collection.find(o));		}
		public NvwaDtoCursor findByGoldAll(int goldAll) {						BasicDBObject o = new BasicDBObject("goldAll", goldAll);			return new NvwaDtoCursor(collection.find(o));		}
		public NvwaDtoCursor findByIsSendBack(boolean isSendBack) {						BasicDBObject o = new BasicDBObject("isSendBack", isSendBack);			return new NvwaDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public NvwaDto createDTO() {			return new NvwaDto();		}			public static class NvwaDtoCursor implements Iterator<NvwaDto>, Iterable<NvwaDto>{				private DBCursor	cursor;				public NvwaDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public NvwaDto next() {				DBObject next = cursor.next();				NvwaDto dto = new NvwaDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<NvwaDto> iterator() {				return this;			}		}	}
		public static class PropAddLogDao {			private DBCollection	collection;			public PropAddLogDao(DBCollection collection) {			this.collection = collection;		}			public void save(PropAddLogDto u) {			collection.save(u.toDBObject());		}			public void delete(PropAddLogDto u) {			collection.remove(u.toDBObject());		}			public void delete() {			collection.remove(key());		}			public PropAddLogDto get() {			DBObject o = collection.findOne(key());			if(o == null) {				return null;			}			PropAddLogDto x = new PropAddLogDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key() {			BasicDBObject o = new BasicDBObject();			return o;		}			public PropAddLogDtoCursor find() {			return new PropAddLogDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public PropAddLogDtoCursor findByTime(long time) {						BasicDBObject o = new BasicDBObject("time", time);			return new PropAddLogDtoCursor(collection.find(o));		}
		public PropAddLogDtoCursor findByUname(String uname) {			collection.ensureIndex("uname");			BasicDBObject o = new BasicDBObject("uname", uname);			return new PropAddLogDtoCursor(collection.find(o));		}
		public PropAddLogDtoCursor findByPropId(int propId) {						BasicDBObject o = new BasicDBObject("propId", propId);			return new PropAddLogDtoCursor(collection.find(o));		}
		public PropAddLogDtoCursor findByCount(int count) {						BasicDBObject o = new BasicDBObject("count", count);			return new PropAddLogDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public PropAddLogDto createDTO() {			return new PropAddLogDto();		}			public static class PropAddLogDtoCursor implements Iterator<PropAddLogDto>, Iterable<PropAddLogDto>{				private DBCursor	cursor;				public PropAddLogDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public PropAddLogDto next() {				DBObject next = cursor.next();				PropAddLogDto dto = new PropAddLogDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<PropAddLogDto> iterator() {				return this;			}		}	}
		public static class PropConsumeLogDao {			private DBCollection	collection;			public PropConsumeLogDao(DBCollection collection) {			this.collection = collection;		}			public void save(PropConsumeLogDto u) {			collection.save(u.toDBObject());		}			public void delete(PropConsumeLogDto u) {			collection.remove(u.toDBObject());		}			public void delete() {			collection.remove(key());		}			public PropConsumeLogDto get() {			DBObject o = collection.findOne(key());			if(o == null) {				return null;			}			PropConsumeLogDto x = new PropConsumeLogDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key() {			BasicDBObject o = new BasicDBObject();			return o;		}			public PropConsumeLogDtoCursor find() {			return new PropConsumeLogDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public PropConsumeLogDtoCursor findByTime(long time) {						BasicDBObject o = new BasicDBObject("time", time);			return new PropConsumeLogDtoCursor(collection.find(o));		}
		public PropConsumeLogDtoCursor findByUname(String uname) {			collection.ensureIndex("uname");			BasicDBObject o = new BasicDBObject("uname", uname);			return new PropConsumeLogDtoCursor(collection.find(o));		}
		public PropConsumeLogDtoCursor findByPropId(int propId) {						BasicDBObject o = new BasicDBObject("propId", propId);			return new PropConsumeLogDtoCursor(collection.find(o));		}
		public PropConsumeLogDtoCursor findByCount(int count) {						BasicDBObject o = new BasicDBObject("count", count);			return new PropConsumeLogDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public PropConsumeLogDto createDTO() {			return new PropConsumeLogDto();		}			public static class PropConsumeLogDtoCursor implements Iterator<PropConsumeLogDto>, Iterable<PropConsumeLogDto>{				private DBCursor	cursor;				public PropConsumeLogDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public PropConsumeLogDto next() {				DBObject next = cursor.next();				PropConsumeLogDto dto = new PropConsumeLogDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<PropConsumeLogDto> iterator() {				return this;			}		}	}
		public static class PvpWarSituationDao {			private DBCollection	collection;			public PvpWarSituationDao(DBCollection collection) {			this.collection = collection;		}			public void save(PvpWarSituationDto u) {			collection.save(u.toDBObject());		}			public void delete(PvpWarSituationDto u) {			collection.remove(u.toDBObject());		}			public void delete(int situationId) {			collection.remove(key(situationId));		}			public PvpWarSituationDto get(int situationId) {			DBObject o = collection.findOne(key(situationId));			if(o == null) {				return null;			}			PvpWarSituationDto x = new PvpWarSituationDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(int situationId) {			BasicDBObject o = new BasicDBObject();		o.put("_id", situationId);			return o;		}			public PvpWarSituationDtoCursor find() {			return new PvpWarSituationDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public PvpWarSituationDtoCursor findBySituationId(int situationId) {			collection.ensureIndex("situationId");			BasicDBObject o = new BasicDBObject("situationId", situationId);			return new PvpWarSituationDtoCursor(collection.find(o));		}
		public PvpWarSituationDtoCursor findByChallengerId(String challengerId) {						BasicDBObject o = new BasicDBObject("challengerId", challengerId);			return new PvpWarSituationDtoCursor(collection.find(o));		}
		public PvpWarSituationDtoCursor findByDefenderId(String defenderId) {						BasicDBObject o = new BasicDBObject("defenderId", defenderId);			return new PvpWarSituationDtoCursor(collection.find(o));		}
		public PvpWarSituationDtoCursor findByCreateTime(int createTime) {						BasicDBObject o = new BasicDBObject("createTime", createTime);			return new PvpWarSituationDtoCursor(collection.find(o));		}
		public PvpWarSituationDtoCursor findByIsWin(boolean isWin) {						BasicDBObject o = new BasicDBObject("isWin", isWin);			return new PvpWarSituationDtoCursor(collection.find(o));		}
		public PvpWarSituationDtoCursor findByIsSaw(boolean isSaw) {						BasicDBObject o = new BasicDBObject("isSaw", isSaw);			return new PvpWarSituationDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public PvpWarSituationDto createDTO() {			return new PvpWarSituationDto();		}			public static class PvpWarSituationDtoCursor implements Iterator<PvpWarSituationDto>, Iterable<PvpWarSituationDto>{				private DBCursor	cursor;				public PvpWarSituationDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public PvpWarSituationDto next() {				DBObject next = cursor.next();				PvpWarSituationDto dto = new PvpWarSituationDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<PvpWarSituationDto> iterator() {				return this;			}		}	}
		public static class UserChatRecordDao {			private DBCollection	collection;			public UserChatRecordDao(DBCollection collection) {			this.collection = collection;		}			public void save(UserChatRecordDto u) {			collection.save(u.toDBObject());		}			public void delete(UserChatRecordDto u) {			collection.remove(u.toDBObject());		}			public void delete(long id, String receiver) {			collection.remove(key(id, receiver));		}			public UserChatRecordDto get(long id, String receiver) {			DBObject o = collection.findOne(key(id, receiver));			if(o == null) {				return null;			}			UserChatRecordDto x = new UserChatRecordDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(long id, String receiver) {			BasicDBObject o = new BasicDBObject();		o.put("_id", id + ":" + receiver);			return o;		}			public UserChatRecordDtoCursor find() {			return new UserChatRecordDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public UserChatRecordDtoCursor findById(long id) {			collection.ensureIndex("id");			BasicDBObject o = new BasicDBObject("id", id);			return new UserChatRecordDtoCursor(collection.find(o));		}
		public UserChatRecordDtoCursor findBySender(String sender) {			collection.ensureIndex("sender");			BasicDBObject o = new BasicDBObject("sender", sender);			return new UserChatRecordDtoCursor(collection.find(o));		}
		public UserChatRecordDtoCursor findByReceiver(String receiver) {			collection.ensureIndex("receiver");			BasicDBObject o = new BasicDBObject("receiver", receiver);			return new UserChatRecordDtoCursor(collection.find(o));		}
		public UserChatRecordDtoCursor findByTime(long time) {						BasicDBObject o = new BasicDBObject("time", time);			return new UserChatRecordDtoCursor(collection.find(o));		}
		public UserChatRecordDtoCursor findByMessage(String message) {						BasicDBObject o = new BasicDBObject("message", message);			return new UserChatRecordDtoCursor(collection.find(o));		}
		public UserChatRecordDtoCursor findByHasRead(boolean hasRead) {						BasicDBObject o = new BasicDBObject("hasRead", hasRead);			return new UserChatRecordDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public UserChatRecordDto createDTO() {			return new UserChatRecordDto();		}			public static class UserChatRecordDtoCursor implements Iterator<UserChatRecordDto>, Iterable<UserChatRecordDto>{				private DBCursor	cursor;				public UserChatRecordDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public UserChatRecordDto next() {				DBObject next = cursor.next();				UserChatRecordDto dto = new UserChatRecordDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<UserChatRecordDto> iterator() {				return this;			}		}	}
		public static class UserCountersDao implements cn.mxz.util.counter.CounterDao<UserCountersDto>{			private DBCollection	collection;			public UserCountersDao(DBCollection collection) {			this.collection = collection;		}			public void save(UserCountersDto u) {			collection.save(u.toDBObject());		}			public void delete(UserCountersDto u) {			collection.remove(u.toDBObject());		}			public void delete(String uname, String counterId) {			collection.remove(key(uname, counterId));		}			public UserCountersDto get(String uname, String counterId) {			DBObject o = collection.findOne(key(uname, counterId));			if(o == null) {				return null;			}			UserCountersDto x = new UserCountersDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String uname, String counterId) {			BasicDBObject o = new BasicDBObject();		o.put("_id", uname + ":" + counterId);			return o;		}			public UserCountersDtoCursor find() {			return new UserCountersDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public UserCountersDtoCursor findByUname(String uname) {			collection.ensureIndex("uname");			BasicDBObject o = new BasicDBObject("uname", uname);			return new UserCountersDtoCursor(collection.find(o));		}
		public UserCountersDtoCursor findByCounterId(String counterId) {			collection.ensureIndex("counterId");			BasicDBObject o = new BasicDBObject("counterId", counterId);			return new UserCountersDtoCursor(collection.find(o));		}
		public UserCountersDtoCursor findByCount(int count) {						BasicDBObject o = new BasicDBObject("count", count);			return new UserCountersDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public UserCountersDto createDTO() {			return new UserCountersDto();		}			public static class UserCountersDtoCursor implements Iterator<UserCountersDto>, Iterable<UserCountersDto>{				private DBCursor	cursor;				public UserCountersDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public UserCountersDto next() {				DBObject next = cursor.next();				UserCountersDto dto = new UserCountersDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<UserCountersDto> iterator() {				return this;			}		}	}
		public static class UserCountersAllDao implements cn.mxz.util.counter.CounterDao<UserCountersAllDto>{			private DBCollection	collection;			public UserCountersAllDao(DBCollection collection) {			this.collection = collection;		}			public void save(UserCountersAllDto u) {			collection.save(u.toDBObject());		}			public void delete(UserCountersAllDto u) {			collection.remove(u.toDBObject());		}			public void delete(String uname, String counterId) {			collection.remove(key(uname, counterId));		}			public UserCountersAllDto get(String uname, String counterId) {			DBObject o = collection.findOne(key(uname, counterId));			if(o == null) {				return null;			}			UserCountersAllDto x = new UserCountersAllDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String uname, String counterId) {			BasicDBObject o = new BasicDBObject();		o.put("_id", uname + ":" + counterId);			return o;		}			public UserCountersAllDtoCursor find() {			return new UserCountersAllDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public UserCountersAllDtoCursor findByUname(String uname) {			collection.ensureIndex("uname");			BasicDBObject o = new BasicDBObject("uname", uname);			return new UserCountersAllDtoCursor(collection.find(o));		}
		public UserCountersAllDtoCursor findByCounterId(String counterId) {			collection.ensureIndex("counterId");			BasicDBObject o = new BasicDBObject("counterId", counterId);			return new UserCountersAllDtoCursor(collection.find(o));		}
		public UserCountersAllDtoCursor findByCount(int count) {						BasicDBObject o = new BasicDBObject("count", count);			return new UserCountersAllDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public UserCountersAllDto createDTO() {			return new UserCountersAllDto();		}			public static class UserCountersAllDtoCursor implements Iterator<UserCountersAllDto>, Iterable<UserCountersAllDto>{				private DBCursor	cursor;				public UserCountersAllDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public UserCountersAllDto next() {				DBObject next = cursor.next();				UserCountersAllDto dto = new UserCountersAllDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<UserCountersAllDto> iterator() {				return this;			}		}	}
		public static class UserFriendRequestDao {			private DBCollection	collection;			public UserFriendRequestDao(DBCollection collection) {			this.collection = collection;		}			public void save(UserFriendRequestDto u) {			collection.save(u.toDBObject());		}			public void delete(UserFriendRequestDto u) {			collection.remove(u.toDBObject());		}			public void delete(String applicant, String receiver) {			collection.remove(key(applicant, receiver));		}			public UserFriendRequestDto get(String applicant, String receiver) {			DBObject o = collection.findOne(key(applicant, receiver));			if(o == null) {				return null;			}			UserFriendRequestDto x = new UserFriendRequestDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String applicant, String receiver) {			BasicDBObject o = new BasicDBObject();		o.put("_id", applicant + ":" + receiver);			return o;		}			public UserFriendRequestDtoCursor find() {			return new UserFriendRequestDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public UserFriendRequestDtoCursor findByApplicant(String applicant) {			collection.ensureIndex("applicant");			BasicDBObject o = new BasicDBObject("applicant", applicant);			return new UserFriendRequestDtoCursor(collection.find(o));		}
		public UserFriendRequestDtoCursor findByReceiver(String receiver) {			collection.ensureIndex("receiver");			BasicDBObject o = new BasicDBObject("receiver", receiver);			return new UserFriendRequestDtoCursor(collection.find(o));		}
		public UserFriendRequestDtoCursor findByRequestTime(long requestTime) {						BasicDBObject o = new BasicDBObject("requestTime", requestTime);			return new UserFriendRequestDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public UserFriendRequestDto createDTO() {			return new UserFriendRequestDto();		}			public static class UserFriendRequestDtoCursor implements Iterator<UserFriendRequestDto>, Iterable<UserFriendRequestDto>{				private DBCursor	cursor;				public UserFriendRequestDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public UserFriendRequestDto next() {				DBObject next = cursor.next();				UserFriendRequestDto dto = new UserFriendRequestDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<UserFriendRequestDto> iterator() {				return this;			}		}	}
		public static class UserGuideDao {			private DBCollection	collection;			public UserGuideDao(DBCollection collection) {			this.collection = collection;		}			public void save(UserGuideDto u) {			collection.save(u.toDBObject());		}			public void delete(UserGuideDto u) {			collection.remove(u.toDBObject());		}			public void delete(String uname, int guideId) {			collection.remove(key(uname, guideId));		}			public UserGuideDto get(String uname, int guideId) {			DBObject o = collection.findOne(key(uname, guideId));			if(o == null) {				return null;			}			UserGuideDto x = new UserGuideDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String uname, int guideId) {			BasicDBObject o = new BasicDBObject();		o.put("_id", uname + ":" + guideId);			return o;		}			public UserGuideDtoCursor find() {			return new UserGuideDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public UserGuideDtoCursor findByUname(String uname) {			collection.ensureIndex("uname");			BasicDBObject o = new BasicDBObject("uname", uname);			return new UserGuideDtoCursor(collection.find(o));		}
		public UserGuideDtoCursor findByGuideId(int guideId) {			collection.ensureIndex("guideId");			BasicDBObject o = new BasicDBObject("guideId", guideId);			return new UserGuideDtoCursor(collection.find(o));		}
		public UserGuideDtoCursor findByGuideStatus(String guideStatus) {						BasicDBObject o = new BasicDBObject("guideStatus", guideStatus);			return new UserGuideDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public UserGuideDto createDTO() {			return new UserGuideDto();		}			public static class UserGuideDtoCursor implements Iterator<UserGuideDto>, Iterable<UserGuideDto>{				private DBCursor	cursor;				public UserGuideDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public UserGuideDto next() {				DBObject next = cursor.next();				UserGuideDto dto = new UserGuideDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<UserGuideDto> iterator() {				return this;			}		}	}
		public static class WorldChatRecordDao {			private DBCollection	collection;			public WorldChatRecordDao(DBCollection collection) {			this.collection = collection;		}			public void save(WorldChatRecordDto u) {			collection.save(u.toDBObject());		}			public void delete(WorldChatRecordDto u) {			collection.remove(u.toDBObject());		}			public void delete(long id) {			collection.remove(key(id));		}			public WorldChatRecordDto get(long id) {			DBObject o = collection.findOne(key(id));			if(o == null) {				return null;			}			WorldChatRecordDto x = new WorldChatRecordDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(long id) {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);			return o;		}			public WorldChatRecordDtoCursor find() {			return new WorldChatRecordDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public WorldChatRecordDtoCursor findById(long id) {			collection.ensureIndex("id");			BasicDBObject o = new BasicDBObject("id", id);			return new WorldChatRecordDtoCursor(collection.find(o));		}
		public WorldChatRecordDtoCursor findBySender(String sender) {			collection.ensureIndex("sender");			BasicDBObject o = new BasicDBObject("sender", sender);			return new WorldChatRecordDtoCursor(collection.find(o));		}
		public WorldChatRecordDtoCursor findByTime(long time) {						BasicDBObject o = new BasicDBObject("time", time);			return new WorldChatRecordDtoCursor(collection.find(o));		}
		public WorldChatRecordDtoCursor findByMessage(String message) {						BasicDBObject o = new BasicDBObject("message", message);			return new WorldChatRecordDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public WorldChatRecordDto createDTO() {			return new WorldChatRecordDto();		}			public static class WorldChatRecordDtoCursor implements Iterator<WorldChatRecordDto>, Iterable<WorldChatRecordDto>{				private DBCursor	cursor;				public WorldChatRecordDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public WorldChatRecordDto next() {				DBObject next = cursor.next();				WorldChatRecordDto dto = new WorldChatRecordDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<WorldChatRecordDto> iterator() {				return this;			}		}	}
		public static class WorldChatRecordAllDao {			private DBCollection	collection;			public WorldChatRecordAllDao(DBCollection collection) {			this.collection = collection;		}			public void save(WorldChatRecordAllDto u) {			collection.save(u.toDBObject());		}			public void delete(WorldChatRecordAllDto u) {			collection.remove(u.toDBObject());		}			public void delete(long id) {			collection.remove(key(id));		}			public WorldChatRecordAllDto get(long id) {			DBObject o = collection.findOne(key(id));			if(o == null) {				return null;			}			WorldChatRecordAllDto x = new WorldChatRecordAllDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(long id) {			BasicDBObject o = new BasicDBObject();		o.put("_id", id);			return o;		}			public WorldChatRecordAllDtoCursor find() {			return new WorldChatRecordAllDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public WorldChatRecordAllDtoCursor findById(long id) {			collection.ensureIndex("id");			BasicDBObject o = new BasicDBObject("id", id);			return new WorldChatRecordAllDtoCursor(collection.find(o));		}
		public WorldChatRecordAllDtoCursor findBySender(String sender) {			collection.ensureIndex("sender");			BasicDBObject o = new BasicDBObject("sender", sender);			return new WorldChatRecordAllDtoCursor(collection.find(o));		}
		public WorldChatRecordAllDtoCursor findByTime(long time) {						BasicDBObject o = new BasicDBObject("time", time);			return new WorldChatRecordAllDtoCursor(collection.find(o));		}
		public WorldChatRecordAllDtoCursor findByMessage(String message) {						BasicDBObject o = new BasicDBObject("message", message);			return new WorldChatRecordAllDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public WorldChatRecordAllDto createDTO() {			return new WorldChatRecordAllDto();		}			public static class WorldChatRecordAllDtoCursor implements Iterator<WorldChatRecordAllDto>, Iterable<WorldChatRecordAllDto>{				private DBCursor	cursor;				public WorldChatRecordAllDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public WorldChatRecordAllDto next() {				DBObject next = cursor.next();				WorldChatRecordAllDto dto = new WorldChatRecordAllDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<WorldChatRecordAllDto> iterator() {				return this;			}		}	}
		public static class ZbsrDao {			private DBCollection	collection;			public ZbsrDao(DBCollection collection) {			this.collection = collection;		}			public void save(ZbsrDto u) {			collection.save(u.toDBObject());		}			public void delete(ZbsrDto u) {			collection.remove(u.toDBObject());		}			public void delete(String uname) {			collection.remove(key(uname));		}			public ZbsrDto get(String uname) {			DBObject o = collection.findOne(key(uname));			if(o == null) {				return null;			}			ZbsrDto x = new ZbsrDto();			x.fromDBObject(o);			return x;		}			private BasicDBObject key(String uname) {			BasicDBObject o = new BasicDBObject();		o.put("_id", uname);			return o;		}			public ZbsrDtoCursor find() {			return new ZbsrDtoCursor(collection.find());		}			public long getCount() {			return collection.count();		}			public ZbsrDtoCursor findByUname(String uname) {			collection.ensureIndex("uname");			BasicDBObject o = new BasicDBObject("uname", uname);			return new ZbsrDtoCursor(collection.find(o));		}
			public void clear () {			collection.drop();		}			public ZbsrDto createDTO() {			return new ZbsrDto();		}			public static class ZbsrDtoCursor implements Iterator<ZbsrDto>, Iterable<ZbsrDto>{				private DBCursor	cursor;				public ZbsrDtoCursor(DBCursor cursor) {				this.cursor = cursor;			}				public boolean hasNext() {				return cursor.hasNext();			}				public ZbsrDto next() {				DBObject next = cursor.next();				ZbsrDto dto = new ZbsrDto();				dto.fromDBObject(next);				return dto;			}				public int getCount() {				return cursor.count();			}				public void remove() {				throw new UnImplMethodException();			}				public Iterator<ZbsrDto> iterator() {				return this;			}		}	}
		public static class AchieveTaskDto implements db.domain.TaskDTO{		private int taskId;
		private String uname;
		private int finishtimes;
		private boolean isDraw;
		public int getTaskId() {			return this.taskId;		}
		public String getUname() {			return this.uname;		}
		public int getFinishtimes() {			return this.finishtimes;		}
		public boolean getIsDraw() {			return this.isDraw;		}
		public void setTaskId(int taskId) {			this.taskId = taskId;		}
		public void setUname(String uname) {			this.uname = uname;		}
		public void setFinishtimes(int finishtimes) {			this.finishtimes = finishtimes;		}
		public void setIsDraw(boolean isDraw) {			this.isDraw = isDraw;		}
		DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();		checkNull(uname);
		o.put("_id", taskId + ":" + uname);
			o.put("taskId", taskId);
			o.put("uname", uname);
			o.put("finishtimes", finishtimes);
			o.put("isDraw", isDraw);
			return o;		}		void fromDBObject(DBObject o) {		taskId = getInteger(o, "taskId");
		uname = getString(o, "uname");
		finishtimes = getInteger(o, "finishtimes");
		isDraw = getBoolean(o, "isDraw");
		}			BasicDBList getBasicDBList(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return new BasicDBList();			}			return (BasicDBList) obj;		}			boolean getBoolean(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return false;			}			return (Boolean) obj;		}				int getInteger(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Integer) obj;		}				byte[] getBytes(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return null;			}			return (byte[]) obj;		}					float getFloat(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Float) obj;		}				String getString(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return "";			}			return (String) obj;		}			long getLong(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Long) obj;		}				double getDouble(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Double) obj;		}			private void checkNull(Object o) {			if(o == null) {				throw new RuntimeException("属性不能为null!");			}		}			@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class BossRankInfoHistoryDto {		private SimpleChallengerDto killer;
		private List<SimpleChallengerDto> list = Lists.newArrayList();
		private int bossHpMax;
		public SimpleChallengerDto getKiller() {			return this.killer;		}
		public List<SimpleChallengerDto> getList() {			return this.list;		}
		public int getBossHpMax() {			return this.bossHpMax;		}
		public void setKiller(SimpleChallengerDto killer) {			this.killer = killer;		}
		public void setList(List<SimpleChallengerDto> list) {			this.list = list;		}
		public void setBossHpMax(int bossHpMax) {			this.bossHpMax = bossHpMax;		}
		DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();
			if(killer != null) {				o.put("killer", killer.toDBObject());			}
			BasicDBList list = new BasicDBList() ;				for (SimpleChallengerDto listElement : this.list) {				list.add(listElement.toDBObject());			}				o.put("list", list);
			o.put("bossHpMax", bossHpMax);
			return o;		}		void fromDBObject(DBObject o) {			DBObject obj = (DBObject) o.get("killer");			if (obj != null) {				killer = new SimpleChallengerDto();				killer.fromDBObject(obj);			}
				this.list = Lists.newArrayList();			BasicDBList list = getBasicDBList(o, "list");			for (Object xxx : list) {				SimpleChallengerDto tp = new SimpleChallengerDto();				tp.fromDBObject((DBObject) xxx);				this.list.add(tp);			}
		bossHpMax = getInteger(o, "bossHpMax");
		}			BasicDBList getBasicDBList(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return new BasicDBList();			}			return (BasicDBList) obj;		}			boolean getBoolean(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return false;			}			return (Boolean) obj;		}				int getInteger(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Integer) obj;		}				byte[] getBytes(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return null;			}			return (byte[]) obj;		}					float getFloat(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Float) obj;		}				String getString(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return "";			}			return (String) obj;		}			long getLong(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Long) obj;		}				double getDouble(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Double) obj;		}			private void checkNull(Object o) {			if(o == null) {				throw new RuntimeException("属性不能为null!");			}		}			@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class ColdDownDto {		private String uname;
		private int index;
		private long endTime;
		public String getUname() {			return this.uname;		}
		public int getIndex() {			return this.index;		}
		public long getEndTime() {			return this.endTime;		}
		public void setUname(String uname) {			this.uname = uname;		}
		public void setIndex(int index) {			this.index = index;		}
		public void setEndTime(long endTime) {			this.endTime = endTime;		}
		DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();		checkNull(uname);
		o.put("_id", uname + ":" + index);
			o.put("uname", uname);
			o.put("index", index);
			o.put("endTime", endTime);
			return o;		}		void fromDBObject(DBObject o) {		uname = getString(o, "uname");
		index = getInteger(o, "index");
		endTime = getLong(o, "endTime");
		}			BasicDBList getBasicDBList(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return new BasicDBList();			}			return (BasicDBList) obj;		}			boolean getBoolean(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return false;			}			return (Boolean) obj;		}				int getInteger(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Integer) obj;		}				byte[] getBytes(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return null;			}			return (byte[]) obj;		}					float getFloat(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Float) obj;		}				String getString(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return "";			}			return (String) obj;		}			long getLong(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Long) obj;		}				double getDouble(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Double) obj;		}			private void checkNull(Object o) {			if(o == null) {				throw new RuntimeException("属性不能为null!");			}		}			@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class DailyTaskDto implements db.domain.TaskDTO{		private int taskId;
		private String uname;
		private int finishtimes;
		private boolean isDraw;
		public int getTaskId() {			return this.taskId;		}
		public String getUname() {			return this.uname;		}
		public int getFinishtimes() {			return this.finishtimes;		}
		public boolean getIsDraw() {			return this.isDraw;		}
		public void setTaskId(int taskId) {			this.taskId = taskId;		}
		public void setUname(String uname) {			this.uname = uname;		}
		public void setFinishtimes(int finishtimes) {			this.finishtimes = finishtimes;		}
		public void setIsDraw(boolean isDraw) {			this.isDraw = isDraw;		}
		DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();		checkNull(uname);
		o.put("_id", taskId + ":" + uname);
			o.put("taskId", taskId);
			o.put("uname", uname);
			o.put("finishtimes", finishtimes);
			o.put("isDraw", isDraw);
			return o;		}		void fromDBObject(DBObject o) {		taskId = getInteger(o, "taskId");
		uname = getString(o, "uname");
		finishtimes = getInteger(o, "finishtimes");
		isDraw = getBoolean(o, "isDraw");
		}			BasicDBList getBasicDBList(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return new BasicDBList();			}			return (BasicDBList) obj;		}			boolean getBoolean(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return false;			}			return (Boolean) obj;		}				int getInteger(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Integer) obj;		}				byte[] getBytes(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return null;			}			return (byte[]) obj;		}					float getFloat(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Float) obj;		}				String getString(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return "";			}			return (String) obj;		}			long getLong(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Long) obj;		}				double getDouble(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Double) obj;		}			private void checkNull(Object o) {			if(o == null) {				throw new RuntimeException("属性不能为null!");			}		}			@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class EnemyDto {		private String uname;
		private String enemyId;
		public String getUname() {			return this.uname;		}
		public String getEnemyId() {			return this.enemyId;		}
		public void setUname(String uname) {			this.uname = uname;		}
		public void setEnemyId(String enemyId) {			this.enemyId = enemyId;		}
		DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();		checkNull(uname);
		checkNull(enemyId);
		o.put("_id", uname + ":" + enemyId);
			o.put("uname", uname);
			o.put("enemyId", enemyId);
			return o;		}		void fromDBObject(DBObject o) {		uname = getString(o, "uname");
		enemyId = getString(o, "enemyId");
		}			BasicDBList getBasicDBList(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return new BasicDBList();			}			return (BasicDBList) obj;		}			boolean getBoolean(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return false;			}			return (Boolean) obj;		}				int getInteger(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Integer) obj;		}				byte[] getBytes(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return null;			}			return (byte[]) obj;		}					float getFloat(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Float) obj;		}				String getString(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return "";			}			return (String) obj;		}			long getLong(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Long) obj;		}				double getDouble(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Double) obj;		}			private void checkNull(Object o) {			if(o == null) {				throw new RuntimeException("属性不能为null!");			}		}			@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class FeedBackDto {		private String uname;
		private int rechargeGold;
		private List<ReceivedBoxDto> receivedIds = Lists.newArrayList();
		public String getUname() {			return this.uname;		}
		public int getRechargeGold() {			return this.rechargeGold;		}
		public List<ReceivedBoxDto> getReceivedIds() {			return this.receivedIds;		}
		public void setUname(String uname) {			this.uname = uname;		}
		public void setRechargeGold(int rechargeGold) {			this.rechargeGold = rechargeGold;		}
		public void setReceivedIds(List<ReceivedBoxDto> receivedIds) {			this.receivedIds = receivedIds;		}
		DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();		checkNull(uname);
		o.put("_id", uname);
			o.put("uname", uname);
			o.put("rechargeGold", rechargeGold);
			BasicDBList receivedIds = new BasicDBList() ;				for (ReceivedBoxDto receivedIdsElement : this.receivedIds) {				receivedIds.add(receivedIdsElement.toDBObject());			}				o.put("receivedIds", receivedIds);
			return o;		}		void fromDBObject(DBObject o) {		uname = getString(o, "uname");
		rechargeGold = getInteger(o, "rechargeGold");
				this.receivedIds = Lists.newArrayList();			BasicDBList receivedIds = getBasicDBList(o, "receivedIds");			for (Object xxx : receivedIds) {				ReceivedBoxDto tp = new ReceivedBoxDto();				tp.fromDBObject((DBObject) xxx);				this.receivedIds.add(tp);			}
		}			BasicDBList getBasicDBList(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return new BasicDBList();			}			return (BasicDBList) obj;		}			boolean getBoolean(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return false;			}			return (Boolean) obj;		}				int getInteger(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Integer) obj;		}				byte[] getBytes(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return null;			}			return (byte[]) obj;		}					float getFloat(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Float) obj;		}				String getString(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return "";			}			return (String) obj;		}			long getLong(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Long) obj;		}				double getDouble(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Double) obj;		}			private void checkNull(Object o) {			if(o == null) {				throw new RuntimeException("属性不能为null!");			}		}			@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class FeedBack2Dto {		private String uname;
		private int rechargeGold;
		private List<ReceivedBox2Dto> receivedIds = Lists.newArrayList();
		public String getUname() {			return this.uname;		}
		public int getRechargeGold() {			return this.rechargeGold;		}
		public List<ReceivedBox2Dto> getReceivedIds() {			return this.receivedIds;		}
		public void setUname(String uname) {			this.uname = uname;		}
		public void setRechargeGold(int rechargeGold) {			this.rechargeGold = rechargeGold;		}
		public void setReceivedIds(List<ReceivedBox2Dto> receivedIds) {			this.receivedIds = receivedIds;		}
		DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();		checkNull(uname);
		o.put("_id", uname);
			o.put("uname", uname);
			o.put("rechargeGold", rechargeGold);
			BasicDBList receivedIds = new BasicDBList() ;				for (ReceivedBox2Dto receivedIdsElement : this.receivedIds) {				receivedIds.add(receivedIdsElement.toDBObject());			}				o.put("receivedIds", receivedIds);
			return o;		}		void fromDBObject(DBObject o) {		uname = getString(o, "uname");
		rechargeGold = getInteger(o, "rechargeGold");
				this.receivedIds = Lists.newArrayList();			BasicDBList receivedIds = getBasicDBList(o, "receivedIds");			for (Object xxx : receivedIds) {				ReceivedBox2Dto tp = new ReceivedBox2Dto();				tp.fromDBObject((DBObject) xxx);				this.receivedIds.add(tp);			}
		}			BasicDBList getBasicDBList(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return new BasicDBList();			}			return (BasicDBList) obj;		}			boolean getBoolean(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return false;			}			return (Boolean) obj;		}				int getInteger(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Integer) obj;		}				byte[] getBytes(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return null;			}			return (byte[]) obj;		}					float getFloat(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Float) obj;		}				String getString(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return "";			}			return (String) obj;		}			long getLong(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Long) obj;		}				double getDouble(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Double) obj;		}			private void checkNull(Object o) {			if(o == null) {				throw new RuntimeException("属性不能为null!");			}		}			@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class FighterHadDto {		private String uname;
		private int fighterTypeId;
		public String getUname() {			return this.uname;		}
		public int getFighterTypeId() {			return this.fighterTypeId;		}
		public void setUname(String uname) {			this.uname = uname;		}
		public void setFighterTypeId(int fighterTypeId) {			this.fighterTypeId = fighterTypeId;		}
		DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();		checkNull(uname);
		o.put("_id", uname + ":" + fighterTypeId);
			o.put("uname", uname);
			o.put("fighterTypeId", fighterTypeId);
			return o;		}		void fromDBObject(DBObject o) {		uname = getString(o, "uname");
		fighterTypeId = getInteger(o, "fighterTypeId");
		}			BasicDBList getBasicDBList(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return new BasicDBList();			}			return (BasicDBList) obj;		}			boolean getBoolean(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return false;			}			return (Boolean) obj;		}				int getInteger(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Integer) obj;		}				byte[] getBytes(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return null;			}			return (byte[]) obj;		}					float getFloat(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Float) obj;		}				String getString(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return "";			}			return (String) obj;		}			long getLong(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Long) obj;		}				double getDouble(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Double) obj;		}			private void checkNull(Object o) {			if(o == null) {				throw new RuntimeException("属性不能为null!");			}		}			@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class FriendDto {		private String uname;
		private String friendName;
		public String getUname() {			return this.uname;		}
		public String getFriendName() {			return this.friendName;		}
		public void setUname(String uname) {			this.uname = uname;		}
		public void setFriendName(String friendName) {			this.friendName = friendName;		}
		DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();		checkNull(uname);
		checkNull(friendName);
		o.put("_id", uname + ":" + friendName);
			o.put("uname", uname);
			o.put("friendName", friendName);
			return o;		}		void fromDBObject(DBObject o) {		uname = getString(o, "uname");
		friendName = getString(o, "friendName");
		}			BasicDBList getBasicDBList(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return new BasicDBList();			}			return (BasicDBList) obj;		}			boolean getBoolean(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return false;			}			return (Boolean) obj;		}				int getInteger(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Integer) obj;		}				byte[] getBytes(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return null;			}			return (byte[]) obj;		}					float getFloat(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Float) obj;		}				String getString(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return "";			}			return (String) obj;		}			long getLong(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Long) obj;		}				double getDouble(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Double) obj;		}			private void checkNull(Object o) {			if(o == null) {				throw new RuntimeException("属性不能为null!");			}		}			@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class HeishiDto {		private String userName;
		private int qsjs;
		private String buyStr;
		public String getUserName() {			return this.userName;		}
		public int getQsjs() {			return this.qsjs;		}
		public String getBuyStr() {			return this.buyStr;		}
		public void setUserName(String userName) {			this.userName = userName;		}
		public void setQsjs(int qsjs) {			this.qsjs = qsjs;		}
		public void setBuyStr(String buyStr) {			this.buyStr = buyStr;		}
		DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();		checkNull(userName);
		checkNull(buyStr);
		o.put("_id", userName);
			o.put("userName", userName);
			o.put("qsjs", qsjs);
			o.put("buyStr", buyStr);
			return o;		}		void fromDBObject(DBObject o) {		userName = getString(o, "userName");
		qsjs = getInteger(o, "qsjs");
		buyStr = getString(o, "buyStr");
		}			BasicDBList getBasicDBList(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return new BasicDBList();			}			return (BasicDBList) obj;		}			boolean getBoolean(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return false;			}			return (Boolean) obj;		}				int getInteger(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Integer) obj;		}				byte[] getBytes(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return null;			}			return (byte[]) obj;		}					float getFloat(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Float) obj;		}				String getString(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return "";			}			return (String) obj;		}			long getLong(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Long) obj;		}				double getDouble(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Double) obj;		}			private void checkNull(Object o) {			if(o == null) {				throw new RuntimeException("属性不能为null!");			}		}			@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class HeiShiGoodsDto {		private int id;
		private int remainCount;
		private int limit;
		private boolean hasExchange;
		private int countExchangeEvery;
		public int getId() {			return this.id;		}
		public int getRemainCount() {			return this.remainCount;		}
		public int getLimit() {			return this.limit;		}
		public boolean getHasExchange() {			return this.hasExchange;		}
		public int getCountExchangeEvery() {			return this.countExchangeEvery;		}
		public void setId(int id) {			this.id = id;		}
		public void setRemainCount(int remainCount) {			this.remainCount = remainCount;		}
		public void setLimit(int limit) {			this.limit = limit;		}
		public void setHasExchange(boolean hasExchange) {			this.hasExchange = hasExchange;		}
		public void setCountExchangeEvery(int countExchangeEvery) {			this.countExchangeEvery = countExchangeEvery;		}
		DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();
			o.put("id", id);
			o.put("remainCount", remainCount);
			o.put("limit", limit);
			o.put("hasExchange", hasExchange);
			o.put("countExchangeEvery", countExchangeEvery);
			return o;		}		void fromDBObject(DBObject o) {		id = getInteger(o, "id");
		remainCount = getInteger(o, "remainCount");
		limit = getInteger(o, "limit");
		hasExchange = getBoolean(o, "hasExchange");
		countExchangeEvery = getInteger(o, "countExchangeEvery");
		}			BasicDBList getBasicDBList(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return new BasicDBList();			}			return (BasicDBList) obj;		}			boolean getBoolean(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return false;			}			return (Boolean) obj;		}				int getInteger(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Integer) obj;		}				byte[] getBytes(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return null;			}			return (byte[]) obj;		}					float getFloat(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Float) obj;		}				String getString(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return "";			}			return (String) obj;		}			long getLong(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Long) obj;		}				double getDouble(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Double) obj;		}			private void checkNull(Object o) {			if(o == null) {				throw new RuntimeException("属性不能为null!");			}		}			@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class HeiShiStoreDto {		private String uname;
		private List<HeiShiGoodsDto> heiShiGoods = Lists.newArrayList();
		public String getUname() {			return this.uname;		}
		public List<HeiShiGoodsDto> getHeiShiGoods() {			return this.heiShiGoods;		}
		public void setUname(String uname) {			this.uname = uname;		}
		public void setHeiShiGoods(List<HeiShiGoodsDto> heiShiGoods) {			this.heiShiGoods = heiShiGoods;		}
		DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();		checkNull(uname);
		o.put("_id", uname);
			o.put("uname", uname);
			BasicDBList heiShiGoods = new BasicDBList() ;				for (HeiShiGoodsDto heiShiGoodsElement : this.heiShiGoods) {				heiShiGoods.add(heiShiGoodsElement.toDBObject());			}				o.put("heiShiGoods", heiShiGoods);
			return o;		}		void fromDBObject(DBObject o) {		uname = getString(o, "uname");
				this.heiShiGoods = Lists.newArrayList();			BasicDBList heiShiGoods = getBasicDBList(o, "heiShiGoods");			for (Object xxx : heiShiGoods) {				HeiShiGoodsDto tp = new HeiShiGoodsDto();				tp.fromDBObject((DBObject) xxx);				this.heiShiGoods.add(tp);			}
		}			BasicDBList getBasicDBList(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return new BasicDBList();			}			return (BasicDBList) obj;		}			boolean getBoolean(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return false;			}			return (Boolean) obj;		}				int getInteger(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Integer) obj;		}				byte[] getBytes(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return null;			}			return (byte[]) obj;		}					float getFloat(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Float) obj;		}				String getString(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return "";			}			return (String) obj;		}			long getLong(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Long) obj;		}				double getDouble(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Double) obj;		}			private void checkNull(Object o) {			if(o == null) {				throw new RuntimeException("属性不能为null!");			}		}			@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class InviteCodeDto {		private String uname;
		private String code;
		public String getUname() {			return this.uname;		}
		public String getCode() {			return this.code;		}
		public void setUname(String uname) {			this.uname = uname;		}
		public void setCode(String code) {			this.code = code;		}
		DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();		checkNull(uname);
		checkNull(code);
		o.put("_id", uname);
			o.put("uname", uname);
			o.put("code", code);
			return o;		}		void fromDBObject(DBObject o) {		uname = getString(o, "uname");
		code = getString(o, "code");
		}			BasicDBList getBasicDBList(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return new BasicDBList();			}			return (BasicDBList) obj;		}			boolean getBoolean(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return false;			}			return (Boolean) obj;		}				int getInteger(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Integer) obj;		}				byte[] getBytes(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return null;			}			return (byte[]) obj;		}					float getFloat(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Float) obj;		}				String getString(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return "";			}			return (String) obj;		}			long getLong(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Long) obj;		}				double getDouble(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Double) obj;		}			private void checkNull(Object o) {			if(o == null) {				throw new RuntimeException("属性不能为null!");			}		}			@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class InviteUsersDto {		private String a;
		private String b;
		public String getA() {			return this.a;		}
		public String getB() {			return this.b;		}
		public void setA(String a) {			this.a = a;		}
		public void setB(String b) {			this.b = b;		}
		DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();		checkNull(a);
		checkNull(b);
		o.put("_id", a);
			o.put("a", a);
			o.put("b", b);
			return o;		}		void fromDBObject(DBObject o) {		a = getString(o, "a");
		b = getString(o, "b");
		}			BasicDBList getBasicDBList(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return new BasicDBList();			}			return (BasicDBList) obj;		}			boolean getBoolean(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return false;			}			return (Boolean) obj;		}				int getInteger(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Integer) obj;		}				byte[] getBytes(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return null;			}			return (byte[]) obj;		}					float getFloat(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Float) obj;		}				String getString(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return "";			}			return (String) obj;		}			long getLong(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Long) obj;		}				double getDouble(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Double) obj;		}			private void checkNull(Object o) {			if(o == null) {				throw new RuntimeException("属性不能为null!");			}		}			@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class KeyValueDto {		private String k;
		private String v;
		public String getK() {			return this.k;		}
		public String getV() {			return this.v;		}
		public void setK(String k) {			this.k = k;		}
		public void setV(String v) {			this.v = v;		}
		DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();		checkNull(k);
		checkNull(v);
		o.put("_id", k);
			o.put("k", k);
			o.put("v", v);
			return o;		}		void fromDBObject(DBObject o) {		k = getString(o, "k");
		v = getString(o, "v");
		}			BasicDBList getBasicDBList(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return new BasicDBList();			}			return (BasicDBList) obj;		}			boolean getBoolean(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return false;			}			return (Boolean) obj;		}				int getInteger(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Integer) obj;		}				byte[] getBytes(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return null;			}			return (byte[]) obj;		}					float getFloat(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Float) obj;		}				String getString(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return "";			}			return (String) obj;		}			long getLong(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Long) obj;		}				double getDouble(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Double) obj;		}			private void checkNull(Object o) {			if(o == null) {				throw new RuntimeException("属性不能为null!");			}		}			@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class KeyValueDataDto {		private String uname;
		private String data;
		public String getUname() {			return this.uname;		}
		public String getData() {			return this.data;		}
		public void setUname(String uname) {			this.uname = uname;		}
		public void setData(String data) {			this.data = data;		}
		DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();		checkNull(uname);
		checkNull(data);
		o.put("_id", uname);
			o.put("uname", uname);
			o.put("data", data);
			return o;		}		void fromDBObject(DBObject o) {		uname = getString(o, "uname");
		data = getString(o, "data");
		}			BasicDBList getBasicDBList(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return new BasicDBList();			}			return (BasicDBList) obj;		}			boolean getBoolean(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return false;			}			return (Boolean) obj;		}				int getInteger(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Integer) obj;		}				byte[] getBytes(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return null;			}			return (byte[]) obj;		}					float getFloat(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Float) obj;		}				String getString(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return "";			}			return (String) obj;		}			long getLong(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Long) obj;		}				double getDouble(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Double) obj;		}			private void checkNull(Object o) {			if(o == null) {				throw new RuntimeException("属性不能为null!");			}		}			@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class LogSnatchDto {		private int id;
		private String uname;
		private int datatype;
		private int nub;
		private String robber;
		private int time;
		private int warsituationid;
		private boolean iswin;
		private boolean isSaw;
		private boolean isSnatchSuccess;
		public int getId() {			return this.id;		}
		public String getUname() {			return this.uname;		}
		public int getDatatype() {			return this.datatype;		}
		public int getNub() {			return this.nub;		}
		public String getRobber() {			return this.robber;		}
		public int getTime() {			return this.time;		}
		public int getWarsituationid() {			return this.warsituationid;		}
		public boolean getIswin() {			return this.iswin;		}
		public boolean getIsSaw() {			return this.isSaw;		}
		public boolean getIsSnatchSuccess() {			return this.isSnatchSuccess;		}
		public void setId(int id) {			this.id = id;		}
		public void setUname(String uname) {			this.uname = uname;		}
		public void setDatatype(int datatype) {			this.datatype = datatype;		}
		public void setNub(int nub) {			this.nub = nub;		}
		public void setRobber(String robber) {			this.robber = robber;		}
		public void setTime(int time) {			this.time = time;		}
		public void setWarsituationid(int warsituationid) {			this.warsituationid = warsituationid;		}
		public void setIswin(boolean iswin) {			this.iswin = iswin;		}
		public void setIsSaw(boolean isSaw) {			this.isSaw = isSaw;		}
		public void setIsSnatchSuccess(boolean isSnatchSuccess) {			this.isSnatchSuccess = isSnatchSuccess;		}
		DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();		checkNull(uname);
		checkNull(robber);
		o.put("_id", id);
			o.put("id", id);
			o.put("uname", uname);
			o.put("datatype", datatype);
			o.put("nub", nub);
			o.put("robber", robber);
			o.put("time", time);
			o.put("warsituationid", warsituationid);
			o.put("iswin", iswin);
			o.put("isSaw", isSaw);
			o.put("isSnatchSuccess", isSnatchSuccess);
			return o;		}		void fromDBObject(DBObject o) {		id = getInteger(o, "id");
		uname = getString(o, "uname");
		datatype = getInteger(o, "datatype");
		nub = getInteger(o, "nub");
		robber = getString(o, "robber");
		time = getInteger(o, "time");
		warsituationid = getInteger(o, "warsituationid");
		iswin = getBoolean(o, "iswin");
		isSaw = getBoolean(o, "isSaw");
		isSnatchSuccess = getBoolean(o, "isSnatchSuccess");
		}			BasicDBList getBasicDBList(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return new BasicDBList();			}			return (BasicDBList) obj;		}			boolean getBoolean(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return false;			}			return (Boolean) obj;		}				int getInteger(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Integer) obj;		}				byte[] getBytes(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return null;			}			return (byte[]) obj;		}					float getFloat(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Float) obj;		}				String getString(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return "";			}			return (String) obj;		}			long getLong(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Long) obj;		}				double getDouble(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Double) obj;		}			private void checkNull(Object o) {			if(o == null) {				throw new RuntimeException("属性不能为null!");			}		}			@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class MxzTokenDto {		private String lineKongUname;
		private String token;
		private String userId;
		private long generateTime;
		public String getLineKongUname() {			return this.lineKongUname;		}
		public String getToken() {			return this.token;		}
		public String getUserId() {			return this.userId;		}
		public long getGenerateTime() {			return this.generateTime;		}
		public void setLineKongUname(String lineKongUname) {			this.lineKongUname = lineKongUname;		}
		public void setToken(String token) {			this.token = token;		}
		public void setUserId(String userId) {			this.userId = userId;		}
		public void setGenerateTime(long generateTime) {			this.generateTime = generateTime;		}
		DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();		checkNull(lineKongUname);
		checkNull(token);
		checkNull(userId);
		o.put("_id", lineKongUname + ":" + token);
			o.put("lineKongUname", lineKongUname);
			o.put("token", token);
			o.put("userId", userId);
			o.put("generateTime", generateTime);
			return o;		}		void fromDBObject(DBObject o) {		lineKongUname = getString(o, "lineKongUname");
		token = getString(o, "token");
		userId = getString(o, "userId");
		generateTime = getLong(o, "generateTime");
		}			BasicDBList getBasicDBList(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return new BasicDBList();			}			return (BasicDBList) obj;		}			boolean getBoolean(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return false;			}			return (Boolean) obj;		}				int getInteger(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Integer) obj;		}				byte[] getBytes(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return null;			}			return (byte[]) obj;		}					float getFloat(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Float) obj;		}				String getString(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return "";			}			return (String) obj;		}			long getLong(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Long) obj;		}				double getDouble(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Double) obj;		}			private void checkNull(Object o) {			if(o == null) {				throw new RuntimeException("属性不能为null!");			}		}			@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class NvwaDto {		private String uname;
		private int buyCount;
		private int goldAll;
		private boolean isSendBack;
		public String getUname() {			return this.uname;		}
		public int getBuyCount() {			return this.buyCount;		}
		public int getGoldAll() {			return this.goldAll;		}
		public boolean getIsSendBack() {			return this.isSendBack;		}
		public void setUname(String uname) {			this.uname = uname;		}
		public void setBuyCount(int buyCount) {			this.buyCount = buyCount;		}
		public void setGoldAll(int goldAll) {			this.goldAll = goldAll;		}
		public void setIsSendBack(boolean isSendBack) {			this.isSendBack = isSendBack;		}
		DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();		checkNull(uname);
		o.put("_id", uname);
			o.put("uname", uname);
			o.put("buyCount", buyCount);
			o.put("goldAll", goldAll);
			o.put("isSendBack", isSendBack);
			return o;		}		void fromDBObject(DBObject o) {		uname = getString(o, "uname");
		buyCount = getInteger(o, "buyCount");
		goldAll = getInteger(o, "goldAll");
		isSendBack = getBoolean(o, "isSendBack");
		}			BasicDBList getBasicDBList(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return new BasicDBList();			}			return (BasicDBList) obj;		}			boolean getBoolean(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return false;			}			return (Boolean) obj;		}				int getInteger(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Integer) obj;		}				byte[] getBytes(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return null;			}			return (byte[]) obj;		}					float getFloat(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Float) obj;		}				String getString(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return "";			}			return (String) obj;		}			long getLong(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Long) obj;		}				double getDouble(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Double) obj;		}			private void checkNull(Object o) {			if(o == null) {				throw new RuntimeException("属性不能为null!");			}		}			@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class PropAddLogDto {		private long time;
		private String uname;
		private int propId;
		private int count;
		public long getTime() {			return this.time;		}
		public String getUname() {			return this.uname;		}
		public int getPropId() {			return this.propId;		}
		public int getCount() {			return this.count;		}
		public void setTime(long time) {			this.time = time;		}
		public void setUname(String uname) {			this.uname = uname;		}
		public void setPropId(int propId) {			this.propId = propId;		}
		public void setCount(int count) {			this.count = count;		}
		DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();		checkNull(uname);

			o.put("time", time);
			o.put("uname", uname);
			o.put("propId", propId);
			o.put("count", count);
			return o;		}		void fromDBObject(DBObject o) {		time = getLong(o, "time");
		uname = getString(o, "uname");
		propId = getInteger(o, "propId");
		count = getInteger(o, "count");
		}			BasicDBList getBasicDBList(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return new BasicDBList();			}			return (BasicDBList) obj;		}			boolean getBoolean(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return false;			}			return (Boolean) obj;		}				int getInteger(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Integer) obj;		}				byte[] getBytes(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return null;			}			return (byte[]) obj;		}					float getFloat(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Float) obj;		}				String getString(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return "";			}			return (String) obj;		}			long getLong(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Long) obj;		}				double getDouble(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Double) obj;		}			private void checkNull(Object o) {			if(o == null) {				throw new RuntimeException("属性不能为null!");			}		}			@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class PropConsumeLogDto {		private long time;
		private String uname;
		private int propId;
		private int count;
		public long getTime() {			return this.time;		}
		public String getUname() {			return this.uname;		}
		public int getPropId() {			return this.propId;		}
		public int getCount() {			return this.count;		}
		public void setTime(long time) {			this.time = time;		}
		public void setUname(String uname) {			this.uname = uname;		}
		public void setPropId(int propId) {			this.propId = propId;		}
		public void setCount(int count) {			this.count = count;		}
		DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();		checkNull(uname);

			o.put("time", time);
			o.put("uname", uname);
			o.put("propId", propId);
			o.put("count", count);
			return o;		}		void fromDBObject(DBObject o) {		time = getLong(o, "time");
		uname = getString(o, "uname");
		propId = getInteger(o, "propId");
		count = getInteger(o, "count");
		}			BasicDBList getBasicDBList(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return new BasicDBList();			}			return (BasicDBList) obj;		}			boolean getBoolean(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return false;			}			return (Boolean) obj;		}				int getInteger(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Integer) obj;		}				byte[] getBytes(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return null;			}			return (byte[]) obj;		}					float getFloat(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Float) obj;		}				String getString(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return "";			}			return (String) obj;		}			long getLong(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Long) obj;		}				double getDouble(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Double) obj;		}			private void checkNull(Object o) {			if(o == null) {				throw new RuntimeException("属性不能为null!");			}		}			@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class PvpWarSituationDto {		private int situationId;
		private String challengerId;
		private String defenderId;
		private int createTime;
		private byte[] data;
		private boolean isWin;
		private boolean isSaw;
		public int getSituationId() {			return this.situationId;		}
		public String getChallengerId() {			return this.challengerId;		}
		public String getDefenderId() {			return this.defenderId;		}
		public int getCreateTime() {			return this.createTime;		}
		public byte[] getData() {			return this.data;		}
		public boolean getIsWin() {			return this.isWin;		}
		public boolean getIsSaw() {			return this.isSaw;		}
		public void setSituationId(int situationId) {			this.situationId = situationId;		}
		public void setChallengerId(String challengerId) {			this.challengerId = challengerId;		}
		public void setDefenderId(String defenderId) {			this.defenderId = defenderId;		}
		public void setCreateTime(int createTime) {			this.createTime = createTime;		}
		public void setData(byte[] data) {			this.data = data;		}
		public void setIsWin(boolean isWin) {			this.isWin = isWin;		}
		public void setIsSaw(boolean isSaw) {			this.isSaw = isSaw;		}
		DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();		checkNull(challengerId);
		checkNull(defenderId);
		o.put("_id", situationId);
			o.put("situationId", situationId);
			o.put("challengerId", challengerId);
			o.put("defenderId", defenderId);
			o.put("createTime", createTime);
			o.put("data", data);
			o.put("isWin", isWin);
			o.put("isSaw", isSaw);
			return o;		}		void fromDBObject(DBObject o) {		situationId = getInteger(o, "situationId");
		challengerId = getString(o, "challengerId");
		defenderId = getString(o, "defenderId");
		createTime = getInteger(o, "createTime");
		data = getBytes(o, "data");
		isWin = getBoolean(o, "isWin");
		isSaw = getBoolean(o, "isSaw");
		}			BasicDBList getBasicDBList(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return new BasicDBList();			}			return (BasicDBList) obj;		}			boolean getBoolean(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return false;			}			return (Boolean) obj;		}				int getInteger(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Integer) obj;		}				byte[] getBytes(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return null;			}			return (byte[]) obj;		}					float getFloat(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Float) obj;		}				String getString(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return "";			}			return (String) obj;		}			long getLong(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Long) obj;		}				double getDouble(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Double) obj;		}			private void checkNull(Object o) {			if(o == null) {				throw new RuntimeException("属性不能为null!");			}		}			@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class ReceivedBoxDto {		private int boxId;
		public int getBoxId() {			return this.boxId;		}
		public void setBoxId(int boxId) {			this.boxId = boxId;		}
		DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();
			o.put("boxId", boxId);
			return o;		}		void fromDBObject(DBObject o) {		boxId = getInteger(o, "boxId");
		}			BasicDBList getBasicDBList(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return new BasicDBList();			}			return (BasicDBList) obj;		}			boolean getBoolean(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return false;			}			return (Boolean) obj;		}				int getInteger(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Integer) obj;		}				byte[] getBytes(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return null;			}			return (byte[]) obj;		}					float getFloat(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Float) obj;		}				String getString(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return "";			}			return (String) obj;		}			long getLong(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Long) obj;		}				double getDouble(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Double) obj;		}			private void checkNull(Object o) {			if(o == null) {				throw new RuntimeException("属性不能为null!");			}		}			@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class ReceivedBox2Dto {		private int boxId;
		private int receiveTimes;
		public int getBoxId() {			return this.boxId;		}
		public int getReceiveTimes() {			return this.receiveTimes;		}
		public void setBoxId(int boxId) {			this.boxId = boxId;		}
		public void setReceiveTimes(int receiveTimes) {			this.receiveTimes = receiveTimes;		}
		DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();
			o.put("boxId", boxId);
			o.put("receiveTimes", receiveTimes);
			return o;		}		void fromDBObject(DBObject o) {		boxId = getInteger(o, "boxId");
		receiveTimes = getInteger(o, "receiveTimes");
		}			BasicDBList getBasicDBList(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return new BasicDBList();			}			return (BasicDBList) obj;		}			boolean getBoolean(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return false;			}			return (Boolean) obj;		}				int getInteger(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Integer) obj;		}				byte[] getBytes(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return null;			}			return (byte[]) obj;		}					float getFloat(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Float) obj;		}				String getString(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return "";			}			return (String) obj;		}			long getLong(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Long) obj;		}				double getDouble(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Double) obj;		}			private void checkNull(Object o) {			if(o == null) {				throw new RuntimeException("属性不能为null!");			}		}			@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class SimpleChallengerDto {		private String userId;
		private String nick;
		private int reputation;
		private int allDamage;
		private int rank;
		public String getUserId() {			return this.userId;		}
		public String getNick() {			return this.nick;		}
		public int getReputation() {			return this.reputation;		}
		public int getAllDamage() {			return this.allDamage;		}
		public int getRank() {			return this.rank;		}
		public void setUserId(String userId) {			this.userId = userId;		}
		public void setNick(String nick) {			this.nick = nick;		}
		public void setReputation(int reputation) {			this.reputation = reputation;		}
		public void setAllDamage(int allDamage) {			this.allDamage = allDamage;		}
		public void setRank(int rank) {			this.rank = rank;		}
		DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();		checkNull(userId);
		checkNull(nick);

			o.put("userId", userId);
			o.put("nick", nick);
			o.put("reputation", reputation);
			o.put("allDamage", allDamage);
			o.put("rank", rank);
			return o;		}		void fromDBObject(DBObject o) {		userId = getString(o, "userId");
		nick = getString(o, "nick");
		reputation = getInteger(o, "reputation");
		allDamage = getInteger(o, "allDamage");
		rank = getInteger(o, "rank");
		}			BasicDBList getBasicDBList(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return new BasicDBList();			}			return (BasicDBList) obj;		}			boolean getBoolean(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return false;			}			return (Boolean) obj;		}				int getInteger(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Integer) obj;		}				byte[] getBytes(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return null;			}			return (byte[]) obj;		}					float getFloat(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Float) obj;		}				String getString(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return "";			}			return (String) obj;		}			long getLong(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Long) obj;		}				double getDouble(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Double) obj;		}			private void checkNull(Object o) {			if(o == null) {				throw new RuntimeException("属性不能为null!");			}		}			@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class UserChatRecordDto {		private long id;
		private String sender;
		private String receiver;
		private long time;
		private String message;
		private boolean hasRead;
		public long getId() {			return this.id;		}
		public String getSender() {			return this.sender;		}
		public String getReceiver() {			return this.receiver;		}
		public long getTime() {			return this.time;		}
		public String getMessage() {			return this.message;		}
		public boolean getHasRead() {			return this.hasRead;		}
		public void setId(long id) {			this.id = id;		}
		public void setSender(String sender) {			this.sender = sender;		}
		public void setReceiver(String receiver) {			this.receiver = receiver;		}
		public void setTime(long time) {			this.time = time;		}
		public void setMessage(String message) {			this.message = message;		}
		public void setHasRead(boolean hasRead) {			this.hasRead = hasRead;		}
		DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();		checkNull(sender);
		checkNull(receiver);
		checkNull(message);
		o.put("_id", id + ":" + receiver);
			o.put("id", id);
			o.put("sender", sender);
			o.put("receiver", receiver);
			o.put("time", time);
			o.put("message", message);
			o.put("hasRead", hasRead);
			return o;		}		void fromDBObject(DBObject o) {		id = getLong(o, "id");
		sender = getString(o, "sender");
		receiver = getString(o, "receiver");
		time = getLong(o, "time");
		message = getString(o, "message");
		hasRead = getBoolean(o, "hasRead");
		}			BasicDBList getBasicDBList(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return new BasicDBList();			}			return (BasicDBList) obj;		}			boolean getBoolean(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return false;			}			return (Boolean) obj;		}				int getInteger(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Integer) obj;		}				byte[] getBytes(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return null;			}			return (byte[]) obj;		}					float getFloat(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Float) obj;		}				String getString(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return "";			}			return (String) obj;		}			long getLong(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Long) obj;		}				double getDouble(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Double) obj;		}			private void checkNull(Object o) {			if(o == null) {				throw new RuntimeException("属性不能为null!");			}		}			@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class UserCountersDto implements db.domain.DBCounter{		private String uname;
		private String counterId;
		private int count;
		public String getUname() {			return this.uname;		}
		public String getCounterId() {			return this.counterId;		}
		public int getCount() {			return this.count;		}
		public void setUname(String uname) {			this.uname = uname;		}
		public void setCounterId(String counterId) {			this.counterId = counterId;		}
		public void setCount(int count) {			this.count = count;		}
		DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();		checkNull(uname);
		checkNull(counterId);
		o.put("_id", uname + ":" + counterId);
			o.put("uname", uname);
			o.put("counterId", counterId);
			o.put("count", count);
			return o;		}		void fromDBObject(DBObject o) {		uname = getString(o, "uname");
		counterId = getString(o, "counterId");
		count = getInteger(o, "count");
		}			BasicDBList getBasicDBList(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return new BasicDBList();			}			return (BasicDBList) obj;		}			boolean getBoolean(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return false;			}			return (Boolean) obj;		}				int getInteger(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Integer) obj;		}				byte[] getBytes(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return null;			}			return (byte[]) obj;		}					float getFloat(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Float) obj;		}				String getString(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return "";			}			return (String) obj;		}			long getLong(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Long) obj;		}				double getDouble(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Double) obj;		}			private void checkNull(Object o) {			if(o == null) {				throw new RuntimeException("属性不能为null!");			}		}			@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class UserCountersAllDto implements db.domain.DBCounter{		private String uname;
		private String counterId;
		private int count;
		public String getUname() {			return this.uname;		}
		public String getCounterId() {			return this.counterId;		}
		public int getCount() {			return this.count;		}
		public void setUname(String uname) {			this.uname = uname;		}
		public void setCounterId(String counterId) {			this.counterId = counterId;		}
		public void setCount(int count) {			this.count = count;		}
		DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();		checkNull(uname);
		checkNull(counterId);
		o.put("_id", uname + ":" + counterId);
			o.put("uname", uname);
			o.put("counterId", counterId);
			o.put("count", count);
			return o;		}		void fromDBObject(DBObject o) {		uname = getString(o, "uname");
		counterId = getString(o, "counterId");
		count = getInteger(o, "count");
		}			BasicDBList getBasicDBList(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return new BasicDBList();			}			return (BasicDBList) obj;		}			boolean getBoolean(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return false;			}			return (Boolean) obj;		}				int getInteger(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Integer) obj;		}				byte[] getBytes(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return null;			}			return (byte[]) obj;		}					float getFloat(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Float) obj;		}				String getString(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return "";			}			return (String) obj;		}			long getLong(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Long) obj;		}				double getDouble(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Double) obj;		}			private void checkNull(Object o) {			if(o == null) {				throw new RuntimeException("属性不能为null!");			}		}			@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class UserFriendRequestDto {		private String applicant;
		private String receiver;
		private long requestTime;
		public String getApplicant() {			return this.applicant;		}
		public String getReceiver() {			return this.receiver;		}
		public long getRequestTime() {			return this.requestTime;		}
		public void setApplicant(String applicant) {			this.applicant = applicant;		}
		public void setReceiver(String receiver) {			this.receiver = receiver;		}
		public void setRequestTime(long requestTime) {			this.requestTime = requestTime;		}
		DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();		checkNull(applicant);
		checkNull(receiver);
		o.put("_id", applicant + ":" + receiver);
			o.put("applicant", applicant);
			o.put("receiver", receiver);
			o.put("requestTime", requestTime);
			return o;		}		void fromDBObject(DBObject o) {		applicant = getString(o, "applicant");
		receiver = getString(o, "receiver");
		requestTime = getLong(o, "requestTime");
		}			BasicDBList getBasicDBList(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return new BasicDBList();			}			return (BasicDBList) obj;		}			boolean getBoolean(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return false;			}			return (Boolean) obj;		}				int getInteger(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Integer) obj;		}				byte[] getBytes(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return null;			}			return (byte[]) obj;		}					float getFloat(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Float) obj;		}				String getString(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return "";			}			return (String) obj;		}			long getLong(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Long) obj;		}				double getDouble(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Double) obj;		}			private void checkNull(Object o) {			if(o == null) {				throw new RuntimeException("属性不能为null!");			}		}			@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class UserGuideDto {		private String uname;
		private int guideId;
		private String guideStatus;
		public String getUname() {			return this.uname;		}
		public int getGuideId() {			return this.guideId;		}
		public String getGuideStatus() {			return this.guideStatus;		}
		public void setUname(String uname) {			this.uname = uname;		}
		public void setGuideId(int guideId) {			this.guideId = guideId;		}
		public void setGuideStatus(String guideStatus) {			this.guideStatus = guideStatus;		}
		DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();		checkNull(uname);
		checkNull(guideStatus);
		o.put("_id", uname + ":" + guideId);
			o.put("uname", uname);
			o.put("guideId", guideId);
			o.put("guideStatus", guideStatus);
			return o;		}		void fromDBObject(DBObject o) {		uname = getString(o, "uname");
		guideId = getInteger(o, "guideId");
		guideStatus = getString(o, "guideStatus");
		}			BasicDBList getBasicDBList(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return new BasicDBList();			}			return (BasicDBList) obj;		}			boolean getBoolean(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return false;			}			return (Boolean) obj;		}				int getInteger(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Integer) obj;		}				byte[] getBytes(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return null;			}			return (byte[]) obj;		}					float getFloat(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Float) obj;		}				String getString(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return "";			}			return (String) obj;		}			long getLong(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Long) obj;		}				double getDouble(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Double) obj;		}			private void checkNull(Object o) {			if(o == null) {				throw new RuntimeException("属性不能为null!");			}		}			@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class WorldChatRecordDto {		private long id;
		private String sender;
		private long time;
		private String message;
		public long getId() {			return this.id;		}
		public String getSender() {			return this.sender;		}
		public long getTime() {			return this.time;		}
		public String getMessage() {			return this.message;		}
		public void setId(long id) {			this.id = id;		}
		public void setSender(String sender) {			this.sender = sender;		}
		public void setTime(long time) {			this.time = time;		}
		public void setMessage(String message) {			this.message = message;		}
		DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();		checkNull(sender);
		checkNull(message);
		o.put("_id", id);
			o.put("id", id);
			o.put("sender", sender);
			o.put("time", time);
			o.put("message", message);
			return o;		}		void fromDBObject(DBObject o) {		id = getLong(o, "id");
		sender = getString(o, "sender");
		time = getLong(o, "time");
		message = getString(o, "message");
		}			BasicDBList getBasicDBList(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return new BasicDBList();			}			return (BasicDBList) obj;		}			boolean getBoolean(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return false;			}			return (Boolean) obj;		}				int getInteger(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Integer) obj;		}				byte[] getBytes(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return null;			}			return (byte[]) obj;		}					float getFloat(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Float) obj;		}				String getString(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return "";			}			return (String) obj;		}			long getLong(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Long) obj;		}				double getDouble(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Double) obj;		}			private void checkNull(Object o) {			if(o == null) {				throw new RuntimeException("属性不能为null!");			}		}			@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class WorldChatRecordAllDto {		private long id;
		private String sender;
		private long time;
		private String message;
		public long getId() {			return this.id;		}
		public String getSender() {			return this.sender;		}
		public long getTime() {			return this.time;		}
		public String getMessage() {			return this.message;		}
		public void setId(long id) {			this.id = id;		}
		public void setSender(String sender) {			this.sender = sender;		}
		public void setTime(long time) {			this.time = time;		}
		public void setMessage(String message) {			this.message = message;		}
		DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();		checkNull(sender);
		checkNull(message);
		o.put("_id", id);
			o.put("id", id);
			o.put("sender", sender);
			o.put("time", time);
			o.put("message", message);
			return o;		}		void fromDBObject(DBObject o) {		id = getLong(o, "id");
		sender = getString(o, "sender");
		time = getLong(o, "time");
		message = getString(o, "message");
		}			BasicDBList getBasicDBList(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return new BasicDBList();			}			return (BasicDBList) obj;		}			boolean getBoolean(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return false;			}			return (Boolean) obj;		}				int getInteger(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Integer) obj;		}				byte[] getBytes(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return null;			}			return (byte[]) obj;		}					float getFloat(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Float) obj;		}				String getString(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return "";			}			return (String) obj;		}			long getLong(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Long) obj;		}				double getDouble(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Double) obj;		}			private void checkNull(Object o) {			if(o == null) {				throw new RuntimeException("属性不能为null!");			}		}			@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class ZbsrDto {		private String uname;
		private List<ZbsrGoodsDto> equipments = Lists.newArrayList();
		public String getUname() {			return this.uname;		}
		public List<ZbsrGoodsDto> getEquipments() {			return this.equipments;		}
		public void setUname(String uname) {			this.uname = uname;		}
		public void setEquipments(List<ZbsrGoodsDto> equipments) {			this.equipments = equipments;		}
		DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();		checkNull(uname);
		o.put("_id", uname);
			o.put("uname", uname);
			BasicDBList equipments = new BasicDBList() ;				for (ZbsrGoodsDto equipmentsElement : this.equipments) {				equipments.add(equipmentsElement.toDBObject());			}				o.put("equipments", equipments);
			return o;		}		void fromDBObject(DBObject o) {		uname = getString(o, "uname");
				this.equipments = Lists.newArrayList();			BasicDBList equipments = getBasicDBList(o, "equipments");			for (Object xxx : equipments) {				ZbsrGoodsDto tp = new ZbsrGoodsDto();				tp.fromDBObject((DBObject) xxx);				this.equipments.add(tp);			}
		}			BasicDBList getBasicDBList(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return new BasicDBList();			}			return (BasicDBList) obj;		}			boolean getBoolean(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return false;			}			return (Boolean) obj;		}				int getInteger(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Integer) obj;		}				byte[] getBytes(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return null;			}			return (byte[]) obj;		}					float getFloat(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Float) obj;		}				String getString(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return "";			}			return (String) obj;		}			long getLong(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Long) obj;		}				double getDouble(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Double) obj;		}			private void checkNull(Object o) {			if(o == null) {				throw new RuntimeException("属性不能为null!");			}		}			@Override		public String toString() {			return toDBObject().toString();		}	}
		public static class ZbsrGoodsDto {		private int id;
		private int equipmentTempletId;
		private boolean isTeJia;
		private boolean hasReceive;
		public int getId() {			return this.id;		}
		public int getEquipmentTempletId() {			return this.equipmentTempletId;		}
		public boolean getIsTeJia() {			return this.isTeJia;		}
		public boolean getHasReceive() {			return this.hasReceive;		}
		public void setId(int id) {			this.id = id;		}
		public void setEquipmentTempletId(int equipmentTempletId) {			this.equipmentTempletId = equipmentTempletId;		}
		public void setIsTeJia(boolean isTeJia) {			this.isTeJia = isTeJia;		}
		public void setHasReceive(boolean hasReceive) {			this.hasReceive = hasReceive;		}
		DBObject toDBObject() {			BasicDBObject o = new BasicDBObject();
			o.put("id", id);
			o.put("equipmentTempletId", equipmentTempletId);
			o.put("isTeJia", isTeJia);
			o.put("hasReceive", hasReceive);
			return o;		}		void fromDBObject(DBObject o) {		id = getInteger(o, "id");
		equipmentTempletId = getInteger(o, "equipmentTempletId");
		isTeJia = getBoolean(o, "isTeJia");
		hasReceive = getBoolean(o, "hasReceive");
		}			BasicDBList getBasicDBList(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return new BasicDBList();			}			return (BasicDBList) obj;		}			boolean getBoolean(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return false;			}			return (Boolean) obj;		}				int getInteger(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Integer) obj;		}				byte[] getBytes(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return null;			}			return (byte[]) obj;		}					float getFloat(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Float) obj;		}				String getString(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return "";			}			return (String) obj;		}			long getLong(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Long) obj;		}				double getDouble(DBObject o, String k) {			Object obj = o.get(k);			if(obj == null) {				return 0;			}			return (Double) obj;		}			private void checkNull(Object o) {			if(o == null) {				throw new RuntimeException("属性不能为null!");			}		}			@Override		public String toString() {			return toDBObject().toString();		}	}
}