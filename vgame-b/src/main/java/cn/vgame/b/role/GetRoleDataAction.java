package cn.vgame.b.role;

import java.util.ArrayList;
import java.util.List;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.excel.Row;
import cn.javaplus.excel.Sheet;
import cn.vgame.b.Server;
import cn.vgame.b.account.JsonActionAfterRoleEnterGame;
import cn.vgame.b.mission.Mission;
import cn.vgame.b.result.ErrorResult;
import cn.vgame.share.Xml;

/**
 * xxxx -----------------
 * 
 */
public class GetRoleDataAction extends JsonActionAfterRoleEnterGame {

	public class MissionStarItem {
		private int id;
		private String status;
	
		public int getId() {
			return id;
		}
	
		public void setId(int id) {
			this.id = id;
		}
	
		public String getStatus() {
			return status;
		}
	
		public void setStatus(String status) {
			this.status = status;
		}
	}

	public class RoleResult {
		// 用户id
		public String getUserId() {
			return (String) session.getAttribute("userId");
		}

		// 角色id
		public String getRoleId() {
			return role.getId();
		}

		// 角色头像
		public int getHead() {
			return role.getHead();
		}

		// 角色昵称
		public String getNick() {
			return role.getNick();
		}

		// 体力
		public int getPhysical() {
			return role.getPhysical();

		}

		// 体力恢复倒计时(秒)
		public int getPhysicalCd() {
			return 0; // TODO chenke
		}

		// 金币
		public long getCoin() {
			return role.getCoin();
		}

		// 钻石
		public long getMasonry() {
			return role.getMasonry();
		}

		public List<MissionStarItem> getItems() {
		
			Mission mission = role.getMission();
			int maxMissionId=mission.getMaxMissionId();
			ArrayList<MissionStarItem> ls = Lists.newArrayList();
			Sheet sheet = Server.getXml().get("mission");
			List<Row> all = sheet.getAll();
			for (Row row : all) {
				int id = row.getInt("id");
			    if(id>=maxMissionId){
			    	break;
			    }
				MissionStarItem item = new MissionStarItem();
				item.setId(id);
				item.setStatus(mission.getStatus(id));
				ls.add(item);
			}
			return ls;
		
		}

		/**
		 * 获取最大关卡数
		 * @return
		 */
		public int getMaxMissionId() {
			Mission mission = role.getMission();
			int maxMissionId=mission.getMaxMissionId();
			if(maxMissionId==0){
				return mission.getMaxMissionId()+1;
			}else{
			return mission.getMaxMissionId();
			}
		}

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1328235115675215327L;

	@Override
	protected Object run() {
		return new RoleResult();
	}

}
