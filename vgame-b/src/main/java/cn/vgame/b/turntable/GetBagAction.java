package cn.vgame.b.turntable;

import java.util.ArrayList;
import java.util.List;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.excel.Row;
import cn.javaplus.excel.Sheet;
import cn.vgame.b.Server;
import cn.vgame.b.account.JsonActionAfterRoleEnterGame;
import cn.vgame.b.bag.Bag;

/**
 * 获得彩金历史记录
 * 
 * -----------------
 * 
 * A.正常情况:
 * 
 * 获得随机推荐20个玩家 传入参数 count 可选项, 如果不发,默认20个 返回 { count:数量, roles: [ { roleId:角色id,
 * nick昵称}, { roleId:角色id, nick昵称}, { roleId:角色id, nick昵称}, { roleId:角色id,
 * nick昵称}, { roleId:角色id, nick昵称}, { roleId:角色id, nick昵称}, { roleId:角色id,
 * nick昵称}, ] }
 * 
 * B.错误: 标准错误
 */

public class GetBagAction extends JsonActionAfterRoleEnterGame {

	public class BagItem {
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
	 * bag: {
	 * 		items:[
	 * 			{id:10001, count:2},
	 * 			{id:10002, count:2},
	 * 			{id:10003, count:2}
	 * 		]
	 * }
	 *
	 */
	
	
	public class BagResult {
		
		public List<BagItem> getItems() {
			
			Bag bag = role.getBag();
			ArrayList<BagItem> ls = Lists.newArrayList();
			Sheet sheet = Server.getXml().get("bag");
			List<Row> all = sheet.getAll();
			for (Row row : all) {
				int id = row.getInt("id");
				BagItem item = new BagItem();
				item.setId(id);
				item.setCount(bag.getCount(id));
				ls.add(item);
			}
			return ls;
			
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3429112450250598945L;

	@Override
	protected Object run() {
		
//		Object userId = session.getAttribute("userId");
		return new BagResult();
	}
}
