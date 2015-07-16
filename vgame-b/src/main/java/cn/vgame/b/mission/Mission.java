package cn.vgame.b.mission;

import java.util.ArrayList;
import java.util.List;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.excel.Row;
import cn.javaplus.excel.Sheet;
import cn.vgame.b.Server;
import cn.vgame.b.account.Role;
import cn.vgame.b.bag.Bag;
import cn.vgame.b.gen.dto.MongoGen.Daos;
import cn.vgame.b.gen.dto.MongoGen.MissionDataDto;
import cn.vgame.b.gen.dto.MongoGen.MongoMap;
import cn.vgame.b.gen.dto.MongoGen.RoleDto;
import cn.vgame.b.result.ErrorResult;
import cn.vgame.b.turntable.GetBagAction.BagItem;
import cn.vgame.share.KeyValue;
import cn.vgame.share.Xml;

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

	private String key(int type) {
		return "Mission:" + type;
	}

	public int getCount(int id) {
		RoleDto dto = role.getDto();
		MongoMap<MissionDataDto> data = dto.getMissionData();
		MissionDataDto d = data.get(id + "");
		return d.getStar();
	}

	/**
	 * 设置关卡的星星数 获取前端的得到星星个数。以及通关关数
	 * 
	 */

	public void setMissionStar(int star, String id) {
		RoleDto dto = role.getDto();
		MongoMap<MissionDataDto> data = dto.getMissionData();

		MissionDataDto v = new MissionDataDto();
		v.setStar(1);
		data.put(id, v);
		Daos.getRoleDao().save(dto);

	}

	public int getMaxMissionId() {
		RoleDto dto = role.getDto();
		int maxMissionId = dto.getMaxMissionId();
		return maxMissionId;
	}

}
