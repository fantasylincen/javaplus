package cn.vgame.a.account;

import cn.javaplus.log.Log;
import cn.vgame.a.Server;
import cn.vgame.a.plantform.Plantform;
import cn.vgame.a.plantform.PlantformFactory;
import cn.vgame.a.plantform.TokenChecker;

/**
 * 玩家进入游戏服务器
 * 
 * -----------------
 * 
 * 
 * A.正常情况: { String id : 客户端传过来的userId, String sessionId, }
 * 
 * B.错误: 标准错误: 10003 token error
 * 
 * 
 * 说明: 标准错误, 客户端需要对所有包 统一处理 { String error 错误文字, int code 这个错误对应到配置表 messages
 * 里面的错误号, String args [ 比如 消息: 10008 message too long len must < %s0 那么 args =[
 * 10] 时 , 该消息表示 message too long len must < 10 String arg1... String arg2...
 * String arg3... ], }
 */
public class EnterServerAction extends JsonAction {

	private static final long serialVersionUID = -6099859675509539457L;

	private String token;
	private String userId;

	private String plantform;
	private String appId;
	private String zoneId;
	private String deviceId;

	@Override
	public Object exec() {

		Plantform p = PlantformFactory.createPlantform(plantform);
		
		TokenChecker checker = p.getChecker();

		checker.check(userId, token, appId);

		session.setAttribute("userId", getUserId());
		session.setAttribute("plantform", p);
		session.setAttribute("zoneId", zoneId);
		session.setAttribute("deviceId", deviceId);
		
		Log.d("enter game " + getUserId());
		Server.getKeyValueSaveOnly().add("ENTER_GAME_TIMES", 1);
		return new EnterServerResult(getUserId(), session.getId());

	}

	/**
	 * 验证串
	 */
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * 玩家ID
	 */
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPlantform() {
		return plantform;
	}

	public void setPlantform(String plantform) {
		this.plantform = plantform;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
}
