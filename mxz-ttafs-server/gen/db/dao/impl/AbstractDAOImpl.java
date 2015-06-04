//package db.dao.impl;
//
//import java.io.IOException;
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.codehaus.jackson.map.ObjectMapper;
//
//import cn.javaplus.common.db.DAOBase;
//import cn.javaplus.common.util.Util;
//import cn.javaplus.common.util.exception.IORuntimeException;
//
//import com.google.common.collect.Lists;
//import com.mongodb.BasicDBObject;
//import com.mongodb.DBCollection;
//import com.mongodb.DBCursor;
//import com.mongodb.DBObject;
//import com.mongodb.MongoException;
//import com.mongodb.WriteResult;
//import com.mongodb.util.JSON;
//
//import db.domain.Domain;
//import db.domain.Key;
//
//public class AbstractDAOImpl<T extends Domain> implements DAOBase<T> {
//
//	protected Class<T>	Tclass;
//
//	public AbstractDAOImpl() {
//		super();
//	}
//
//	public AbstractDAOImpl(Class<T> tclass) {
//		Tclass = tclass;
//	}
//
//	@Override
//	public void add(T o) {
//		DBCollection collection = getCollection();
//		ensureKeyIndex();
//		DBObject dbObject = toJson(o);
//		WriteResult r = collection.insert(dbObject);
//		String error = r.getError();
//		if (error != null) {
//			throw new MongoException(error);
//		}
//	}
//
//	protected DBObject toJson(T o) {
//		DBObject dbObject;
//		try {
//			dbObject = (DBObject) JSON.parse(new ObjectMapper().writeValueAsString(o));
//		} catch (IOException e) {
//			throw new IORuntimeException(e);
//		}
//		return dbObject;
//	}
//
//	protected void ensureKeyIndex() {
//
//		List<Field> fs = getKeyFields();
//
//		BasicDBObject basicDBObject = new BasicDBObject();
//		for (Field field : fs) {
//			basicDBObject.put(field.getName(), 1);
//		}
//
//		getCollection().ensureIndex(basicDBObject, Tclass.getSimpleName() + "Key", true);
//	}
//
//	protected DBCollection getCollection() {
//		DBCollection collection = CollectionFactory.getCollection("test");
//		return collection;
//	}
//
//	@Override
//	public void addAll(List<T> o) {
//		ArrayList<DBObject> ls = Lists.newArrayList();
//		for (T a : o) {
//			ls.add(toJson(a));
//		}
//		DBCollection collection = getCollection();
//		WriteResult r = collection.insert(ls);
//		String error = r.getError();
//		if (error != null) {
//			throw new MongoException(error);
//		}
//	}
//
//	@Override
//	public List<T> getAll() {
//		List<T> ls = Lists.newArrayList();
//		DBCursor find = getCollection().find();
//		ObjectMapper mapper = new ObjectMapper();
//		while (find.hasNext()) {
//			DBObject dbObject = (DBObject) find.next();
//			T value = parse(dbObject);
//			ls.add(value);
//		}
//		return ls;
//	}
//
//	protected T parse(DBObject dbObject) {
//		try {
//			T value = (T) new ObjectMapper().readValue(dbObject.toString(), Tclass);
//			return value;
//		} catch (IOException e) {
//			throw new IORuntimeException(e);
//		}
//	}
//
//	@Override
//	public int getCount() {
//		return (int) getCollection().getCount();
//	}
//
//	@Override
//	public void update(T o) {
//		DBCollection collection = getCollection();
//		ensureKeyIndex();
//
//		WriteResult r = collection.update(buildFinder(o), toJson(o));
//		String error = r.getError();
//		if (error != null) {
//			throw new MongoException(error);
//		}
//	}
//
//	protected List<Field> getKeyFields() {
//		List<Field> ls = Lists.newArrayList();
//		Field[] fields = Tclass.getDeclaredFields();
//		for (Field field : fields) {
//			Key k = field.getAnnotation(Key.class);
//			if (k != null) {
//				ls.add(field);
//			}
//		}
//		return ls;
//	}
//
//	protected DBObject buildFinder(T o) {
//		Field[] fields = Tclass.getDeclaredFields();
//		BasicDBObject obj = new BasicDBObject();
//
//		List<Field> ks = getKeyFields();
//
//		for (Field field : fields) {
//			if (ks.contains(field)) {
//				try {
//					obj.put(field.getName(), field.get(o));
//				} catch (IllegalArgumentException e1) {
//					Util.toRuntimeException(e1);
//				} catch (IllegalAccessException e1) {
//					Util.toRuntimeException(e1);
//				}
//			}
//		}
//
//		return obj;
//	}
//
//	@Override
//	public int nextId() {
//		return 0;
//	}
//
//	@Override
//	public List<T> findBy(String filed, String value) {
//		DBCollection c = getCollection();
//		c.ensureIndex(filed);
//		DBCursor find = c.find(new BasicDBObject(filed, value));
//		List<T> ls = Lists.newArrayList();
//		while (find.hasNext()) {
//			DBObject dbObject = (DBObject) find.next();
//			ls.add(parse(dbObject));
//		}
//		return ls;
//	}
//
//	@Override
//	public T createDTO() {
//		try {
//			return Tclass.newInstance();
//		} catch (InstantiationException e) {
//			throw new RuntimeException(e);
//
//		} catch (IllegalAccessException e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	@Override
//	public void clear() {
//		WriteResult r = getCollection().remove(null);
//		String error = r.getError();
//		if (error != null) {
//			throw new MongoException(error);
//		}
//	}
//
//}