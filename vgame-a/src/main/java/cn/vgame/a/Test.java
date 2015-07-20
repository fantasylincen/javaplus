package cn.vgame.a;

import java.util.Set;

import cn.vgame.a.gen.dto.MongoGen.Daos;
import cn.vgame.a.gen.dto.MongoGen.RoleDao.RoleDtoCursor;
import cn.vgame.a.gen.dto.MongoGen.RoleDto;
import cn.vgame.a.turntable.Turntable;
import cn.vgame.a.turntable.swt.ISwitchs;
import cn.vgame.a.turntable.swt.SwitchAll;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class Test {
	public static void main(String[] args) {
		SwitchAll all = Turntable.getInstance().getSwitchs();
		Set<String> d = all.getAll();
		for (String roleId : d) {
			ISwitchs s = all.get(roleId);
			
		}
	}
}
