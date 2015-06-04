package cn.vgame.b.message;

import cn.vgame.b.Server;
import cn.vgame.b.account.JsonActionAfterRoleEnterGame;

/**
 * 拉取喊话列表
 * 
 * -----------------
 * 
 * 正常情况:
 * {
 * 		messages:[
 * 			{id:10001, message:fuckyou, time:1301111009331, date:2013-11-23 14:12:10},
 * 			{id:10002, message:fuckyou2, time:1301111009331, date:2013-11-23 14:12:10},
 * 			{id:10003, message:fuckyou3, time:1301111009331, date:2013-11-23 14:12:10},
 * 			{id:10004, message:fuckyou4, time:1301111009331, date:2013-11-23 14:12:10},
 * 			{id:10005, message:fuckyou5, time:1301111009331, date:2013-11-23 14:12:10},
 * 			{id:10006, message:fuckyou6, time:1301111009331, date:2013-11-23 14:12:10}
 *  	]	
 * }
 * 
 * 错误:
 *    标准错误
 * 
 */
public class GetMessagesAction extends JsonActionAfterRoleEnterGame {

	private static final long serialVersionUID = 6270471841097023373L;

	@Override
	public Object run() {
		MessageManager manager = Server.getMessageManager();
		Messages messages = manager.getMessages();
		return new RoleMessagePanel(messages, role);
	}

}
