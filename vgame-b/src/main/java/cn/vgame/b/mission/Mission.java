package cn.vgame.b.mission;

import cn.javaplus.excel.Row;
import cn.javaplus.excel.Sheet;
import cn.vgame.b.Server;
import cn.vgame.b.account.Role;
import cn.vgame.b.gen.dto.MongoGen.Daos;
import cn.vgame.b.gen.dto.MongoGen.MissionDataDto;
import cn.vgame.b.gen.dto.MongoGen.MongoMap;
import cn.vgame.b.gen.dto.MongoGen.RoleDto;
import cn.vgame.b.result.ErrorResult;
import cn.vgame.share.KeyValue;
import cn.vgame.share.Xml;

/**
 * 玩家背包
 */
public class Mission {

	private final Role role;

	public Mission(Role role) {
		this.role = role;
	}

	public Role getRole() {
		return role;
	}


	/**
	 * 设置关卡的星星数
	 *  获取前端的得到星星个数。以及通关关数
	 *
	 */

	public void setMissionStar(int star ,String id) {
		RoleDto dto = role.getDto();
		MongoMap<MissionDataDto> data = dto.getMissionData();
		//最大关卡
//		int maxMissionId = dto.getMaxMissionId();
		MissionDataDto d = data.get(id);

			MissionDataDto v=new MissionDataDto();
			v.setStar(1);
			data.put(id, v);
			Daos.getRoleDao().save(dto);


	}
	
	/**
	 * 获取关卡的星星数
	 */
	

	
	public int getStar(String id) {
		RoleDto dto = role.getDto();
		MongoMap<MissionDataDto> data = dto.getMissionData();
		MissionDataDto d = data.get(id);
			MissionDataDto v=new MissionDataDto();
             int star=v.getStar();
			return star;
	}
	

}
