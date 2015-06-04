package mongo.gen;import java.util.List;import com.mongodb.BasicDBList;import com.mongodb.BasicDBObject;import com.mongodb.DBObject;import com.google.common.collect.Lists;import cn.javaplus.db.mongo.Key;public class LoginDataDto {	private String uname;
	private long date;
	public String getUname() {		return this.uname;	}
	public long getDate() {		return this.date;	}
	public void setUname(String uname) {		this.uname = uname;	}
	public void setDate(long date) {		this.date = date;	}
	DBObject toDBObject() {		BasicDBObject o = new BasicDBObject();		checkNull(uname);

		o.put("uname", uname);
		o.put("date", date);
		return o;	}	void fromDBObject(DBObject o) {		uname = getString(o, "uname");
		date = getLong(o, "date");
	}		BasicDBList getBasicDBList(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return new BasicDBList();		}		return (BasicDBList) obj;	}	boolean getBoolean(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return false;		}		return (Boolean) obj;	}		int getInteger(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return 0;		}		return (Integer) obj;	}		byte[] getBytes(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return null;		}		return (byte[]) obj;	}		float getFloat(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return 0;		}		return (Float) obj;	}		String getString(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return "";		}		return (String) obj;	}	long getLong(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return 0;		}		return (Long) obj;	}		double getDouble(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return 0;		}		return (Double) obj;	}	private void checkNull(Object o) {		if(o == null) {			throw new RuntimeException("属性不能为null!");		}	}	@Override	public String toString() {		return toDBObject().toString();	}}