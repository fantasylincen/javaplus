package cn.javaplus.mxzrobot.db;import java.util.List;import com.mongodb.BasicDBList;import com.mongodb.BasicDBObject;import com.mongodb.DBObject;import com.google.common.collect.Lists;import cn.javaplus.db.mongo.Key;public class ChatRecordDto {	private String time;
	private String talker;
	private String content;
	public String getTime() {		return this.time;	}
	public String getTalker() {		return this.talker;	}
	public String getContent() {		return this.content;	}
	public void setTime(String time) {		this.time = time;	}
	public void setTalker(String talker) {		this.talker = talker;	}
	public void setContent(String content) {		this.content = content;	}
	DBObject toDBObject() {		BasicDBObject o = new BasicDBObject();		checkNull(time);
		checkNull(talker);
		checkNull(content);

		o.put("time", time);
		o.put("talker", talker);
		o.put("content", content);
		return o;	}	void fromDBObject(DBObject o) {		time = (String) o.get("time");
		talker = (String) o.get("talker");
		content = (String) o.get("content");
	}	private void checkNull(Object o) {		if(o == null) {			throw new RuntimeException("属性不能为null!");		}	}	@Override	public String toString() {		return toDBObject().toString();	}}