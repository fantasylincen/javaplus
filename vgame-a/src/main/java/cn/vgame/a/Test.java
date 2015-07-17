package cn.vgame.a;

import cn.vgame.a.gen.dto.MongoGen.Daos;
import cn.vgame.a.gen.dto.MongoGen.RoleDao.RoleDtoCursor;
import cn.vgame.a.gen.dto.MongoGen.RoleDto;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class Test {
	public static void main(String[] args) {
		DBCollection c = Daos.getCollection("Role");
		DBCursor sort = c.find().sort(new BasicDBObject("coin", -1));
		RoleDtoCursor find = new RoleDtoCursor(sort);
		
		for (RoleDto dto : find) {
			
		}
	}
}
