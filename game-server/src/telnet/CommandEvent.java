package telnet;

import java.util.HashMap;
import java.util.Map;

import telnet.events.CleanEvent;
import telnet.events.CommandsEvent;
import telnet.events.ExportPayTableEvent;
import telnet.events.GetUserInfoEvent;
import telnet.events.SetActivityEevet;
import telnet.events.ViewFeedbackEevet;
//import telnet.events.OnlineSizeEvent;
//import telnet.events.PlayerCurrentRegionEvent;
//import telnet.events.PlayerLevelDistributeEvent;
//import telnet.events.PrugeDatabaseEvent;
//import telnet.events.ReloadprOpertiesEvent;
import telnet.events.ReloadworldEvent;
import telnet.events.SendNoticeEvent;
import telnet.events.ServerInfoEvent;
//import telnet.events.SetDebugerEvent;
import telnet.events.SetUserInfoEvent;
import telnet.events.StartServerEvent;
import telnet.events.StopServerEvent;

/**
 * 命令事件
 * @author DXF
 */
public enum CommandEvent {

	// 启动服务器
	STARTSERVER					( "startserver", new StartServerEvent() ),
	
	// 关闭服务器
	STOPSERVER					( "stopserver", new StopServerEvent() ),
	
	// 重载游戏世界
	RELOADWORLD					( "reloadworld", new ReloadworldEvent() ),
//	
//	// 重载系统配置
//	RELOADPROPERTIES			( "reloadproperties", new ReloadprOpertiesEvent() ),
//	
//	// 设置服务器运行模式
//	SETDEBUGER					( "setdebuger", new SetDebugerEvent() ),
//	
//	// 当前服务器在线人数
//	ONLINESIZE					( "onlinesize", new OnlineSizeEvent() ),
	
	// 当前服务器信息
	SERVERINFO					( "serverinfo", new ServerInfoEvent() ),
	
	// 清空数据库
//	PRUGEDATABASE				( "prugedatabase", new PrugeDatabaseEvent() ),
	
	// 设置玩家信息
	SETUSERINFO					( "setuserinfo", new SetUserInfoEvent() ),
	
	// 获取玩家信息
	GETUSERINFO					( "getuserinfo", new GetUserInfoEvent() ),
	
	// 发送公告
	SENDANNOUNCEMENT			( "sendnotice", new SendNoticeEvent() ),
	
	// 生成充值表格
	EXPORTPAYTABLE				( "exportpaytable", new ExportPayTableEvent() ),
	
	// 设置活动
	SETACTIVITY					( "setactivity", new SetActivityEevet() ),

	// 查看反馈
	VIEWFEEDBACK				( "viewfeedback", new ViewFeedbackEevet() ),
	
	// 生成玩家当前所在地区
//	PLAYERCURRENTREGION			( "playercurrentregion", new PlayerCurrentRegionEvent() ),
	
	// 生成玩家实力
//	PLAYERLEVELDISTRIBUTE		( "playerleveldistribute", new PlayerLevelDistributeEvent() ),
	
	// 清屏
	CLEAN						( "clean", new CleanEvent() ),
	
	// 命令列表
	COMMANDS					( "?", new CommandsEvent() );
	
	
	private final String 			command;
	private final CommandBase 		eventInstance;
	
	CommandEvent( String command, CommandBase eventInstance ) {
		this.command 		= command;
		this.eventInstance 	= eventInstance;
	}
	private static final Map<String, CommandEvent> numToEnum = new HashMap<String, CommandEvent>();
	
	static{
		for( CommandEvent a : values() ){
			CommandEvent p = numToEnum.put( a.command, a );
			if( p != null ){
				throw new RuntimeException( "通信包" + a.command + "重复了" );
			}
		}
	}
	
	public CommandBase getEventInstance() {
		return eventInstance;
	}
	public String toCommand() {
		return command;
	}
	public static CommandEvent fromNum( String s ){
		return numToEnum.get( s );
	}
}
