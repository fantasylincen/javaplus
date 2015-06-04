package mongo.gen;import java.util.List;import com.mongodb.BasicDBList;import com.mongodb.BasicDBObject;import com.mongodb.DBObject;import com.google.common.collect.Lists;import cn.javaplus.db.mongo.Key;public class DailyTaskDto {	private int taskId;
	private String uname;
	private int finishtimes;
	private boolean isDraw;
	public int getTaskId() {		return this.taskId;	}
	public String getUname() {		return this.uname;	}
	public int getFinishtimes() {		return this.finishtimes;	}
	public boolean getIsDraw() {		return this.isDraw;	}
	public void setTaskId(int taskId) {		this.taskId = taskId;	}
	public void setUname(String uname) {		this.uname = uname;	}
	public void setFinishtimes(int finishtimes) {		this.finishtimes = finishtimes;	}
	public void setIsDraw(boolean isDraw) {		this.isDraw = isDraw;	}
	DBObject toDBObject() {		BasicDBObject o = new BasicDBObject();		checkNull(uname);
		o.put("_id", taskId + ":" + uname);
		o.put("taskId", taskId);
		o.put("uname", uname);
		o.put("finishtimes", finishtimes);
		o.put("isDraw", isDraw);
		return o;	}	void fromDBObject(DBObject o) {		taskId = getInteger(o, "taskId");
		uname = getString(o, "uname");
		finishtimes = getInteger(o, "finishtimes");
		isDraw = getBoolean(o, "isDraw");
	}		BasicDBList getBasicDBList(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return new BasicDBList();		}		return (BasicDBList) obj;	}	boolean getBoolean(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return false;		}		return (Boolean) obj;	}		int getInteger(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return 0;		}		return (Integer) obj;	}		byte[] getBytes(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return null;		}		return (byte[]) obj;	}		float getFloat(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return 0;		}		return (Float) obj;	}		String getString(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return "";		}		return (String) obj;	}	long getLong(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return 0;		}		return (Long) obj;	}		double getDouble(DBObject o, String k) {		Object obj = o.get(k);		if(obj == null) {			return 0;		}		return (Double) obj;	}	private void checkNull(Object o) {		if(o == null) {			throw new RuntimeException("属性不能为null!");		}	}	@Override	public String toString() {		return toDBObject().toString();	}}