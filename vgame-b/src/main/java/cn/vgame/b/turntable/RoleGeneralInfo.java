package cn.vgame.b.turntable;

import cn.vgame.b.gen.dto.MongoGen.RoleDto;

public class RoleGeneralInfo {

	private final RoleDto dto;

	public RoleGeneralInfo(RoleDto dto) {
		this.dto = dto;
	}

	public String getId() {
		return dto.getId();
	}

	public String getNick() {
		return dto.getNick();
	}
	

}
