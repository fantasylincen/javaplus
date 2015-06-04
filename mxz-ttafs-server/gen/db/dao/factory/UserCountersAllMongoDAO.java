//package db.dao.factory;
//
//
//import java.util.List;
//
//import cn.javaplus.common.db.DAO2;
//import cn.mxz.exception.UnImplMethodException;
//
//import com.mongodb.BasicDBObject;
//import com.mongodb.DBCollection;
//import com.mongodb.DBObject;
//
//import db.domain.UserCountersAll;
//import db.domain.UserCountersAllImpl;
//
//public class UserCountersAllMongoDAO implements DAO2<String, String, UserCountersAll> {
//
//	@Override
//	public void add(UserCountersAll o) {
//		DBCollection c = getCollection();
//		c.insert(parse(o));
//	}
//
//	private DBObject parse(UserCountersAll o) {
//		BasicDBObject bo = new BasicDBObject();
//		String key = key(o.getCounterId(), o.getUname());
//		bo.put("_id", key);
//		bo.put("uname", o.getUname());
//		bo.put("count", o.getCount())	;
//		bo.put("counter_id", o.getCounterId());
//
//		return bo;
//	}
//
//	private DBCollection getCollection() {
//		return CollectionFactory.getCollection("user_counters_all");
//	}
//
//	@Override
//	public void addAll(List<UserCountersAll> o) {
//		throw new UnImplMethodException();
//	}
//
//	@Override
//	public List<UserCountersAll> getAll() {
//		throw new UnImplMethodException();
//	}
//
//	@Override
//	public int getCount() {
//		DBCollection c = getCollection();
//		return (int) c.getCount();
//	}
//
//	@Override
//	public void update(UserCountersAll o) {
//		DBCollection c = getCollection();
//		BasicDBObject q = new BasicDBObject("_id", key(o.getCounterId(), o.getUname()));
//		c.update(q, parse(o));
//	}
//
//	@Override
//	public List<UserCountersAll> findBy(String filed, String value) {
//		throw new UnImplMethodException();
//	}
//
//	@Override
//	public UserCountersAll createDTO() {
//		return new UserCountersAllImpl();
//	}
//
//	@Override
//	public void clear() {
//		getCollection().drop();
//	}
//
//	@Override
//	public List<UserCountersAll> findBy(String field, String o, int limit) {
//		throw new UnImplMethodException();
//	}
//
//	@Override
//	public void update(String fieldName, Object value) {
//		throw new UnImplMethodException();
//	}
//
//	@Override
//	public List<UserCountersAll> findBy(String field, String symbol, String o, int limit) {
//		throw new UnImplMethodException();
//	}
//
//	@Override
//	public List<UserCountersAll> findWhere(String where) {
//		throw new UnImplMethodException();
//	}
//
//	@Override
//	public List<UserCountersAll> findByScope(String field, int min, int max, int limit) {
//		throw new UnImplMethodException();
//	}
//
//	@Override
//	public UserCountersAll get(String k1, String k2) {
//		DBCollection c = getCollection();
//		DBObject ref = new BasicDBObject();
//		String key = key(k1, k2);
//		ref.put("_id", key);
//		DBObject f = c.findOne(ref);
//		if(f == null) {
//			return null;
//		}
//		return parse(f);
//	}
//
//	private UserCountersAll parse(DBObject find) {
//		UserCountersAll u = new UserCountersAllImpl();
//		u.setCount((Integer) find.get("count"));
//		u.setCounterId((String) find.get("counter_id"));
//		u.setUname((String) find.get("uname"));
//		return u;
//	}
//
//	private String key(String k1, String k2) {
//		return k1 + ":" + k2;
//	}
//
//	@Override
//	public void delete(String k1, String k2) {
//		DBCollection c = getCollection();
//		DBObject ref = new BasicDBObject();
//		ref.put("_id", key(k1, k2));
//		c.remove(ref);
//	}
//
//}
