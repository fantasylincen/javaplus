import mongo.gen.MongoGen.CollectionFetcher;
import mongo.gen.MongoGen.Daos;
import cn.javaplus.collections.counter.Counter;
import cn.javaplus.collections.counter.ICounter;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class QueryMission {

	public String run() {
		return "RUN_FOUNCTION";
	}

	public String query() {

		CollectionFetcher f = Daos.getFetcher();
		DBCollection c = f.getCollection("UserCountersAll");
		BasicDBObject obj = new BasicDBObject();
		obj.put("counterId", "MAX_MISSION_ID");
		DBCursor find = c.find(obj);

		StringBuilder sb = new StringBuilder();

		ICounter<Integer> counter = new Counter<Integer>();

		for (DBObject cc : find) {
			int mission = (Integer) cc.get("count");
			counter.add(mission, 1);
		}

		for (int mission = 0; mission < 101; mission++) {
			sb.append("关卡:" + mission + " 人数:" + counter.get(mission));
			sb.append("<br>");
		}

		return sb.toString();
	}
}