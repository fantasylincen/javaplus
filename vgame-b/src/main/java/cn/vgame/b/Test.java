package cn.vgame.b;

import cn.vgame.b.gen.dto.MongoGen.Daos;
import cn.vgame.b.gen.dto.MongoGen.MissionDataDto;
import cn.vgame.b.gen.dto.MongoGen.MongoMap;
import cn.vgame.b.gen.dto.MongoGen.RoleDao;
import cn.vgame.b.gen.dto.MongoGen.RoleDto;

public class Test {
public static void main(String[] args) {
	RoleDao dao = Daos.getRoleDao();
	RoleDto dto = dao.get("xxxx");
	MongoMap<MissionDataDto> data = dto.getMissionData();
	MissionDataDto d = data.get("1");
	int star = d.getStar();

	dao.save(dto);
}


}
