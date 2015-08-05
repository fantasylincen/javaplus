package cn.vgame.b.role;

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
 * 玩家基本信息
 */
public class RoleMessage {

	private final Role role;

	public RoleMessage(Role role) {
		this.role = role;
	}

	public Role getRole() {
		return role;
	}




	/**
	 * 获取玩家信息的基本信息
	 * 
	 */




	
	
}
