package cn.vgame.b.mission;

import cn.vgame.b.account.Role;
import cn.vgame.b.gen.dto.MongoGen.Daos;
import cn.vgame.b.gen.dto.MongoGen.MissionDataDto;
import cn.vgame.b.gen.dto.MongoGen.MongoMap;
import cn.vgame.b.gen.dto.MongoGen.RoleDto;

/**
 * 玩家关卡
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
	 * 设置关卡的星星数 获取前端的得到星星个数。以及通关关数
	 * 
	 */

	public void setMissionStar(String  status, int id) {
		RoleDto dto = role.getDto();
		MongoMap<MissionDataDto> data = dto.getMissionData();

		MissionDataDto v = new MissionDataDto();
		v.setStatus(status);
		data.put(id + "", v);
		Daos.getRoleDao().save(dto);

	}

	public int getMaxMissionId() {
		RoleDto dto = role.getDto();
		int maxMissionId = dto.getMaxMissionId();
		return maxMissionId;
	}

	public String getStatus(int id) {
		
		RoleDto dto = role.getDto();
		MongoMap<MissionDataDto> data = dto.getMissionData();
		MissionDataDto v = data.get(id + "");
		if(v == null){
			return "";
		}
		String status = v.getStatus();
		if(status == null){
			return "";
		}else{
		return status;
		}
	}    
	}
	

