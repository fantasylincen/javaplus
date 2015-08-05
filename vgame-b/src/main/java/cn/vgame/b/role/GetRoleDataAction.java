package cn.vgame.b.role;

import cn.javaplus.excel.Row;
import cn.javaplus.excel.Sheet;
import cn.vgame.b.Server;
import cn.vgame.b.account.JsonActionAfterRoleEnterGame;
import cn.vgame.b.result.ErrorResult;
import cn.vgame.share.Xml;

/**
 * xxxx
 * -----------------
 * 

 */
public class GetRoleDataAction extends JsonActionAfterRoleEnterGame {

	public class RoleResult {
//		用户id
		public String getUserId() {
			return (String) session.getAttribute("userId");
		}
		
		
//		角色id
		public String getRoleId() {
			return role.getId();
		}
		
//		角色头像
		public int getHead() {
			return role.getHead();
		}
		
//		角色昵称
		public String getNick() {
			return role.getNick();
		}
		
//		体力
		public int getPhysical() {
			return role.getPhysical();
			
		}
//		体力恢复倒计时(秒)
		public int getPhysicalCd() {
			return 0; //TODO chenke
		}
		
//		金币
		public long getCoin(){
			return role.getCoin();
		}
		
//		钻石
		public long getMasonry() {
			return role.getMasonry();
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
