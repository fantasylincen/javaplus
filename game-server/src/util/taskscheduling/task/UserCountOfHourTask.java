package util.taskscheduling.task;

import java.util.TimerTask;

import game.log.Logs;
import game.log.L;
import user.UserManager;

/**
 * 用户每小时 在线用户  记录日志
 * @author DXF
 */
public class UserCountOfHourTask extends TimerTask{

	@Override
	public void run() {
		
		// 记录日志 当前在线玩家数量
		Logs.log( L.L_011, UserManager.getInstance().getMaps().size() + "" );
		
		// 向登陆服发送当前服务器状态
		
	}
	
	
}
