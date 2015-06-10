package game.events.all.update;

import java.io.IOException;
import java.util.List;

import user.UserInfo;
import game.events.Event;
import game.invitingfriends.InvitingFriendsBase;
import game.log.Logs;

/**
 * 更新包管理器  单列
 * @author DXF
 */
public class UpdateManager {
	
	/** 唯一实例 */
	public static UpdateManager instance = new UpdateManager();
	private UpdateManager() {	}
	
	/**
	 * 告诉前端 更新
	 * @param user
	 * @param type (更新类型)
	 */
	public void update( UserInfo user, UpdateType type ){
		
		ActiveUpdateEvent p = (ActiveUpdateEvent) Event.MAIN_ACTIVE_UPDATE.getEventInstance();
		try {
			p.run( user, type , null, null, -1 );
		} catch (IOException e) {
			e.printStackTrace();
			Logs.error( user, "主动更新包出错! " + type );
		}
	}
	
	/**
	 * 更新 英雄数据 或者 好友
	 * @param user
	 * @param type
	 * @param lists
	 */
	public void update( UserInfo user, UpdateType type, List<?> lists ){
		
		ActiveUpdateEvent p = (ActiveUpdateEvent) Event.MAIN_ACTIVE_UPDATE.getEventInstance();
		try {
			p.run( user, type, lists , null, -1 );
		} catch (IOException e) {
			e.printStackTrace();
			Logs.error( user, "主动更新包出错! " + type );
		}
	}
	
	/**
	 * 发送公告
	 * @param user
	 * @param type
	 * @param strings
	 */
	public void update( UserInfo user, UpdateType type, int id, String ...strings ){
		ActiveUpdateEvent p = (ActiveUpdateEvent) Event.MAIN_ACTIVE_UPDATE.getEventInstance();
		try {
			p.run( user, type, null, null, id, strings );
		} catch (IOException e) {
			e.printStackTrace();
			Logs.error( user, "主动更新包出错! " + type );
		}
	}
	
	/**
	 * 更新 邀请好友数据
	 * @param user
	 * @param type
	 * @param lists
	 */
	public void update( UserInfo user, UpdateType type, InvitingFriendsBase invited ){
		
		ActiveUpdateEvent p = (ActiveUpdateEvent) Event.MAIN_ACTIVE_UPDATE.getEventInstance();
		try {
			p.run( user, type, null, invited, -1 );
		} catch (IOException e) {
			e.printStackTrace();
			Logs.error( user, "主动更新包出错! " + type );
		}
	}

	/**
	 * 每次登陆  更新主界面 提示信息
	 * @param user
	 */
	public void updateMain( UserInfo user ) {
		
		// 更新录像
		if( !user.getDanGradingManager().getVideoRecording().getList().isEmpty() )
			UpdateManager.instance.update( user, UpdateType.U_24 );
		
		// 更新添加好友
		if( !user.getFriendManager().getListBeg().isEmpty() )
			UpdateManager.instance.update( user, UpdateType.U_30 );
		
		// 天赋
		user.getTalentManager().reminder( (byte)0 );
		user.getTalentManager().runReminder();
		// 系统奖励
		user.getAwardManager().reminder( (byte)0 );
		user.getAwardManager().runReminder();
		// 匹配奖励
		user.getDanGradingManager().reminder_a( (byte)0 );
		user.getDanGradingManager().runReminderA();
		// 匹配级位提升
		user.getDanGradingManager().reminder_l( (byte)0 );
		user.getDanGradingManager().runReminderL();
		// 邮件
		UpdateManager.instance.update( user, UpdateType.U_7 );
	}
	
}
