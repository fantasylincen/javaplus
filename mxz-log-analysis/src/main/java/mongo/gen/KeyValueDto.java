package mongo.gen;import java.util.List;import com.mongodb.BasicDBList;import com.mongodb.BasicDBObject;import com.mongodb.DBObject;import com.google.common.collect.Lists;import cn.javaplus.db.mongo.Key;public class KeyValueDto {	private String k;
	private String v;
	public String getK() {		return this.k;	}
	public String getV() {		return this.v;	}
	public void setK(String k) {		this.k = k;	}
	public void setV(String v) {		this.v = v;	}
	DBObject toDBObject() {		BasicDBObject o = new BasicDBObject();		checkNull(k);
		checkNull(v);
		o.put("_id", k);
		o.put("k", k);
		o.put("v", v);
		return o;	}	void fromDBObject(DBObject o) {		k = getString(o, "k");
		v = getString(o, "v");
	}		BasicDBList getBasicDBList(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return new BasicDBList();		}		return (BasicDBList) obj;	}	boolean getBoolean(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return false;		}		return (Boolean) obj;	}		int getInteger(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return 0;		}		return (Integer) obj;	}		byte[] getBytes(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return null;		}		return (byte[]) obj;	}		float getFloat(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return 0;		}		return (Float) obj;	}		String getString(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return "";		}		return (String) obj;	}	long getLong(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return 0;		}		return (Long) obj;	}		double getDouble(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return 0;		}		return (Double) obj;	}	private void checkNull(Object o) {		if(o == null) {			throw new RuntimeException("属性不能为null!");		}	}	@Override	public String toString() {		return toDBObject().toString();	}}