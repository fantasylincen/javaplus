package cn.vgame.b.turntable;

import java.util.ArrayList;
import java.util.List;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.excel.Row;
import cn.javaplus.excel.Sheet;
import cn.vgame.b.Server;
import cn.vgame.b.account.JsonActionAfterRoleEnterGame;
import cn.vgame.b.bag.Bag;
import cn.vgame.b.mission.Mission;

/**
 * 获取关卡的星星数
 * 
 * -----------------
 * 
 * A.正常情况:
 * 
 * 根据角色返回获取获取每个关卡的星星数量{ count:数量, roles: [ { 关卡:1,
 * 2}, { roleId:关卡2,2}, { roleId:关卡3, 2}, { roleId:角色id,
 * nick昵称} }
 * 
 * 
 */

public class GetMissionStarAction extends JsonActionAfterRoleEnterGame {

	public class MissionStarItem {
		private int id;
		private int count;

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Mession: {
	 * 		items:[
	 * 			{id:1, count:2},
	 * 			{id:2, count:3},
	 * 			{id:3, count:4}
	 * 		]
	 * }
	 *
	 */
	
	
	public class MissionResult {
		
		public List<MissionStarItem> getItems() {
			
			Mission mission = role.getMission();
			ArrayList<MissionStarItem> ls = Lists.newArrayList();
			Sheet sheet = Server.getXml().get("mession");
			List<Row> all = sheet.getAll();
			for (Row row : all) {
				int id = row.getInt("id");
				MissionStarItem item = new MissionStarItem();
				item.setId(id);
				item.setCount(mission.getCount(id));
				ls.add(item);
			}
			return ls;
			
		}
		
		public int getMaxMissionId() {
			Mission mission = role.getMission();
			return mission.getMaxMissionId();
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3429112450250598945L;

	@Override
	protected Object run() {
		return new MissionResult();
	}
}
