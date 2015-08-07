package cn.vgame.b.turntable;

import cn.vgame.b.account.JsonActionAfterRoleEnterGame;

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

public class SubmitMissionDataAction extends JsonActionAfterRoleEnterGame {

//	public class MissionStarItem {
	//		private int id;
	//		private String status;
	//	
	//		public int getId() {
	//			return id;
	//		}
	//	
	//		public void setId(int id) {
	//			this.id = id;
	//		}
	//	
	//		public String getStatus() {
	//			return status;
	//		}
	//	
	//		public void setStatus(String status) {
	//			this.status = status;
	//		}
	//	}
	//	
	//	public void setMissionStar(String  status, String id) {
	//		RoleDto dto = role.getDto();
	//		MongoMap<MissionDataDto> data = dto.getMissionData();
	//
	//		MissionDataDto v = new MissionDataDto();
	//		v.setStar(1);
	//		data.put(id, v);
	//		Daos.getRoleDao().save(dto);
	//
	//	}
	//
	//	public int getMaxMissionId() {
	//		RoleDto dto = role.getDto();
	//		int maxMissionId = dto.getMaxMissionId();
	//		return maxMissionId;
	//	}

	public class SuccessResult {

		public boolean isSuccess() {
			return true;
		}

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3429112450250598945L;

	
	
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

	@Override
	protected Object run() {
		role.getMission().setMissionStar(status, id);
		return new SuccessResult();
	}
}
