package mongo.gen;import java.util.List;import com.mongodb.BasicDBList;import com.mongodb.BasicDBObject;import com.mongodb.DBObject;import com.google.common.collect.Lists;import cn.javaplus.db.mongo.Key;public class LogDto {	private int ids;
	private long time;
	private int serverId;
	private String logHead;
	private String logText;
	public int getIds() {		return this.ids;	}
	public long getTime() {		return this.time;	}
	public int getServerId() {		return this.serverId;	}
	public String getLogHead() {		return this.logHead;	}
	public String getLogText() {		return this.logText;	}
	public void setIds(int ids) {		this.ids = ids;	}
	public void setTime(long time) {		this.time = time;	}
	public void setServerId(int serverId) {		this.serverId = serverId;	}
	public void setLogHead(String logHead) {		this.logHead = logHead;	}
	public void setLogText(String logText) {		this.logText = logText;	}
	DBObject toDBObject() {		BasicDBObject o = new BasicDBObject();		checkNull(logHead);
		checkNull(logText);
		o.put("_id", ids);
		o.put("ids", ids);
		o.put("time", time);
		o.put("serverId", serverId);
		o.put("logHead", logHead);
		o.put("logText", logText);
		return o;	}	void fromDBObject(DBObject o) {		ids = getInteger(o, "ids");
		time = getLong(o, "time");
		serverId = getInteger(o, "serverId");
		logHead = getString(o, "logHead");
		logText = getString(o, "logText");
	}		BasicDBList getBasicDBList(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return new BasicDBList();		}		return (BasicDBList) obj;	}	boolean getBoolean(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return false;		}		return (Boolean) obj;	}		int getInteger(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return 0;		}		return (Integer) obj;	}		byte[] getBytes(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return null;		}		return (byte[]) obj;	}		float getFloat(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return 0;		}		return (Float) obj;	}		String getString(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return "";		}		return (String) obj;	}	long getLong(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return 0;		}		return (Long) obj;	}		double getDouble(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return 0;		}		return (Double) obj;	}	private void checkNull(Object o) {		if(o == null) {			throw new RuntimeException("属性不能为null!");		}	}	@Override	public String toString() {		return toDBObject().toString();	}}