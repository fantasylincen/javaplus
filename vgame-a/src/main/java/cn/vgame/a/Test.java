package cn.vgame.a;

import cn.vgame.a.gen.dto.MongoGen.Daos;
import cn.vgame.a.gen.dto.MongoGen.MongoMap;
import cn.vgame.a.gen.dto.MongoGen.RoleDao;
import cn.vgame.a.gen.dto.MongoGen.RoleDto;

public class Test {
	public static void main(String[] args) {
		RoleDao dao = Daos.getRoleDao();
		RoleDto role = dao.get("iddddd");
		role.setNick("zhoujl");
		
		dao.save(role);
		
		RoleDto dto = dao.createDTO();
		dto.setId("100001");
//		dto.setxxx
//		...
		dao.save(role);
		
		
		
//		MongoMap<String> xxx = dto.getKeyValueDaily();
	}
}
