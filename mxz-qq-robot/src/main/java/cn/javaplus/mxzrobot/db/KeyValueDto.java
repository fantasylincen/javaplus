package cn.javaplus.mxzrobot.db;import java.util.List;import com.mongodb.BasicDBList;import com.mongodb.BasicDBObject;import com.mongodb.DBObject;import com.google.common.collect.Lists;import cn.javaplus.db.mongo.Key;public class KeyValueDto {	private String key;
	private String value;
	public String getKey() {		return this.key;	}
	public String getValue() {		return this.value;	}
	public void setKey(String key) {		this.key = key;	}
	public void setValue(String value) {		this.value = value;	}
	DBObject toDBObject() {		BasicDBObject o = new BasicDBObject();		checkNull(key);
		checkNull(value);
		o.put("_id", key);
		o.put("key", key);
		o.put("value", value);
		return o;	}	void fromDBObject(DBObject o) {		key = (String) o.get("key");
		value = (String) o.get("value");
	}	private void checkNull(Object o) {		if(o == null) {			throw new RuntimeException("属性不能为null!");		}	}	@Override	public String toString() {		return toDBObject().toString();	}}