package game.events;


import game.events.all.*;
import game.events.all.award.SystemAwardEvent;
import game.events.all.chat.ChatEvent;
import game.events.all.ectype.EctypeGetListEvent;
import game.events.all.equip.EquipmentCDEvent;
import game.events.all.equip.EquipmentSellEvent;
import game.events.all.equip.EquipmentSynthesisEvent;
import game.events.all.fight.BattleResurrectionEvent;
import game.events.all.fight.DragonInspireEvent;
import game.events.all.fight.DragonUserResurgenceEvent;
import game.events.all.fight.ExitEctypeEvents;
import game.events.all.fight.FightOfFriendEvent;
import game.events.all.fight.FightStartEvent;
import game.events.all.fight.SendBattleInfoEvents;
import game.events.all.fight.SendBattleSituation;
import game.events.all.fight.SweepEvent;
import game.events.all.friend.FriendEvent;
import game.events.all.front_animation.FrontAnimationEvent;
import game.events.all.growup.GrowUPEvent;
import game.events.all.hero.UpDataUserHeroProp;
import game.events.all.invitingfriends.InvitingFriendsEvent;
import game.events.all.mail.MailEvent;
import game.events.all.newbieguide.NewbieGuideEevet;
import game.events.all.pvp.PvpBattleAppEvent;
import game.events.all.pvp.PvpMateAppCardInfoEvent;
import game.events.all.pvp.PvpMateAppEvent;
import game.events.all.pvp.PvpMateAppInfoEvent;
import game.events.all.pvp.PvpMateAwardEvent;
import game.events.all.pvp.PvpMateUpgradeEvent;
import game.events.all.qualifying.QualifyingEevet;
import game.events.all.recharge.RechargeEvent;
import game.events.all.recharge.RechargeEvent_91;
import game.events.all.recharge.RechargeEvent_google;
import game.events.all.recharge.RechargeEvent_lb;
import game.events.all.recharge.RechargeEvent_lb2;
import game.events.all.recharge.RechargeEvent_tb;
import game.events.all.shop.ShopEvent;
import game.events.all.talent.TalentEvent;
import game.events.all.update.ActiveUpdateEvent;
import game.events.all.user.AccounBindEvent;
import game.events.all.user.UserCreateEvent;
import game.events.all.user.UserLoginEvent;
import game.events.all.user.UserLoginFrontEvent;
import game.events.all.user.UserLoginFrontVerifyEvent;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

import user.UserInfo;

/**
 * 
 * @注意：为了传输方便，枚举对应的数字也就是包号不得超过Short.MAX_VALUE
 * @author dxf
 * 2013-6-30
 */
public enum Event {
	
	/*********************************系统保留******************************************************************************/
	TEST_GAME							( 1, 		new TestEvent() ),  	// 测试包
	SYSTEM_SEND_ERROR_CODE				( 10, 		new SystemSendErrorCodeEvent() ),	
	
	/*********************************开场动画******************************************************************************/
	FRONT_ANIMATION						( 110, 		new FrontAnimationEvent() ),
	
	/*********************************用户系统******************************************************************************/
	USER_LOGIN_FRONT_VERIFY				( 97, 		new UserLoginFrontVerifyEvent() ), 		// 登陆前获取角色
	USER_LOGIN_FRONT					( 98, 		new UserLoginFrontEvent() ), 		// 登陆前获取角色
	USER_LOGIN							( 101, 		new UserLoginEvent() ), 			// 用户登录
	USER_CREATE							( 105, 		new UserCreateEvent() ),			// 用户注册
	ACCOUN_BINDEVENT					( 2118, 	new AccounBindEvent() ), 			// 账号绑定
	
	/******************************主界面更新数据****************************************************************************/
	MAIN_ACTIVE_UPDATE					( 50, 		new ActiveUpdateEvent() ),			// 主动更新数据 
	/******************************新手引导系统****************************************************************************/
	NEWBIE_GUIDE						( 51, 		new NewbieGuideEevet() ),			// 
	CHATEVENT							( 52, 		new ChatEvent() ),			// 
	
	/*********************************英雄系统******************************************************************************/
	UPDATA_USER_HERO_PROP				( 202, 		new UpDataUserHeroProp() ),			// 玩家英雄
	
	/********************************英雄成长系统****************************************************************************/
	GROWUP_EVENT						( 203,     	new GrowUPEvent() ),				// 英雄成长系统
	
	/*********************************天赋系统******************************************************************************/
	TALENT_SYSTEM						( 204, 		new TalentEvent() ),				// 天赋系统
	
	/*********************************奖励系统******************************************************************************/
	SYSTEM_AWARD						( 205, 		new SystemAwardEvent() ),			// 奖励系统
	
	/*********************************副本系统******************************************************************************/
	ECTYPE_APP_LIST						( 2101, 	new EctypeGetListEvent() ),			// 申请副本列表
	FIGHT_OF_FRIEND						( 2104, 	new FightOfFriendEvent() ),			// 申请好友列表
	
	/*********************************商城系统******************************************************************************/
	SHOP_SYSTEM							( 212, 		new ShopEvent() ),					// 商城系统
	RECHARGE_EVENT						( 501, 		new RechargeEvent() ),				// 充值系统-苹果
	RECHARGE_EVENT_91					( 502, 		new RechargeEvent_91() ),			// 充值系统-91
	RECHARGE_EVENT_TB					( 503, 		new RechargeEvent_tb() ),			// 充值系统-同步
	RECHARGE_EVENT_GOOGLE				( 504, 		new RechargeEvent_google() ),		// 充值系统-google
	RECHARGE_EVENT_LB					( 505, 		new RechargeEvent_lb() ),			// 充值系统-乐巴
	RECHARGE_EVENT_LB2					( 506, 		new RechargeEvent_lb2() ),			// 充值系统-乐巴
	
	/*********************************好友系统******************************************************************************/
	FRIEND_SYSTEM						( 213, 		new FriendEvent() ),				// 好友系e统
	
	/*********************************好友系统******************************************************************************/
	FRIEND_INVITE_SYSTEM				( 214, 		new InvitingFriendsEvent() ),		// 好友邀请系e统
	
	/*********************************邮件系统******************************************************************************/
	MAIL_SYSTEM							( 215, 		new MailEvent() ),					// 邮件系统
	
	/*********************************战斗系统******************************************************************************/
	SEND_BATTLE_SITUATION				( 301, 		new SendBattleSituation() ), 		// 申请战斗
	SEND_BATTLE_INFO					( 302, 		new SendBattleInfoEvents() ), 		// 申请战报
	EXIT_ECTYPE							( 303, 		new ExitEctypeEvents() ), 			// 退出副本
	CLICK_ONTHE_RESURRECTION			( 304, 		new BattleResurrectionEvent() ), 	// 点击复活
	ECTYPE_START						( 305, 		new FightStartEvent() ), 			// 战斗开始
	DRAGON_USER_RESURGENCE				( 306, 		new DragonUserResurgenceEvent() ), 	// 挑战大龙 玩家复活
	DRAGON_INSPIRE						( 2107, 	new DragonInspireEvent() ), 		// 挑战大龙 申请鼓舞
	SWEEP_EVENT							( 2110, 	new SweepEvent() ), 				// 申请扫荡
	/*********************************PVP系统******************************************************************************/
	PVP_MATE_APPINFO					( 2111, 	new PvpMateAppInfoEvent() ), 		// 申请匹配信息
	PVP_MATE_UPGRADE					( 2112, 	new PvpMateUpgradeEvent() ), 		// 级位
	PVP_MATE_AWARD						( 2113, 	new PvpMateAwardEvent() ), 			// 匹配奖励
	PVP_MATE_APPCARDINFO				( 2114, 	new PvpMateAppCardInfoEvent() ), 	// 申请卡片详细信息
	PVP_BATTLE_APP						( 2115, 	new PvpBattleAppEvent() ), 			// 申请录像
	PVP_MATE_BATTLE						( 401, 		new PvpMateAppEvent() ), 			// 匹配战斗
	
	/*********************************排位系统******************************************************************************/
	QUALIFYING							( 2117, 	new QualifyingEevet() ), 		// 排位赛
	
	/*********************************装备系统******************************************************************************/
	EQUIPMENT_CD						( 2063, 	new EquipmentCDEvent() ),			// 装备 穿和卸
	EQUIPMENT_SELL						( 2062, 	new EquipmentSellEvent() ),			// 装备 出售
	EQUIPMENT_SYNTHESIS					( 2064, 	new EquipmentSynthesisEvent() ),	// 装备 合成
	
	/*********************************测试系统******************************************************************************/
	DEAD_LOCK_TEST						( 3000, 	new DeadLockTestEvent() ),
	USER_REQUEST_CONFIG					( 3001, 	new UserRequestConfigEvent() ),//玩家申请配置表文件(测试用)
	
	GM									( 4001, 	new GMEvent() );
	
	
	private final short 			number;
	private final EventBase 		eventInstance;
	
	Event( int value, EventBase eventInstance ) {
		if( value >= Short.MAX_VALUE || value < 0 ){
			throw new IllegalArgumentException( "包号不符合规范：" + value );
		}
		this.number 		=  (short) value;
		this.eventInstance 	= eventInstance;
		this.eventInstance.setEventId( number );
	}
	private static final Map<Short, Event> numToEnum = new HashMap<Short, Event>();
	
	static{
		for( Event a : values() ){
			
			Event p = numToEnum.put( a.number, a );
			if( p != null ){
				throw new RuntimeException( "通信包" + a.number + "重复了" );
			}
		}
	}
	
	public EventBase getEventInstance() {
		return eventInstance;
	}
	public short toNum() {
		return number;
	}
	public static Event fromNum( short n ){
		return numToEnum.get( n );
	}
	
	/**
	 * 运行此枚举所对应的包的run方法
	 * @param user
	 * @param buf
	 * @throws IOException 
	 */
	public void run( UserInfo user, ByteBuffer buf ) throws IOException {
		eventInstance.run( user, buf );
	}
	
	/**
	 * 打印所有的包信息
	 * @param args
	 */
	public static void main(String[] args) {
		Formatter f = new Formatter( System.out );
		f.format( "%-15s %-100s %-10s %n", "包号", "类别", "功能说明" );
		f.format( "%-15s %-100s %-10s %n", "－－", "－－", "－－－－" );
		for( Event p : values() ){
			
			Class<?> c = p.eventInstance.getClass();
			EventDescrip desc = c.getAnnotation(EventDescrip.class);
			String s = null;
			s = (desc == null) ? "" : desc.desc();
			String className = c.getName().substring( c.getName().lastIndexOf(".") + 1 );
			f.format("%-8s %-40s %-10s %n", p.eventInstance.getEventId(), className, s );
		}
		System.out.println( "--------------------------HTML---------------------------------");
		StringBuilder html = new StringBuilder( "<table><tr><td>包号</td><td>类别</td><td>功能说明</td></tr><tr>" );
		for( Event p : values() ){
			
			Class<?> c = p.eventInstance.getClass();
			EventDescrip desc = c.getAnnotation(EventDescrip.class);
			String s = null;
			s = (desc == null) ? "" : desc.desc();
			String className = c.getName().substring( c.getName().lastIndexOf(".") + 1 );
		//	f.format("%-8s %-40s %-10s \n", p.packageInstance.getPackageNo(), className, s );
			html.append( "<td>" );
			html.append( p.eventInstance.getEventId() );
			html.append( "</td><td>" );
			html.append( className );
			html.append( "</td><td>" );
			html.append( s );
			html.append( "</td></tr>" );
			
			
		} 
		html.append(  "</table>" );
		System.out.println( html );
		f.close();
	}
	
}
