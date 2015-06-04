package telnet.events;


import game.activity.ActivityManager;
import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import define.GameData;
import define.GameDataProvider;

import server.ServerManager;
import telnet.CommandBase;
import user.UserInfo;
import user.UserManager;

public class SetActivityEevet  extends CommandBase{

	private List<SS> sui			= new ArrayList<SS>();
	
	public static boolean  	isOpen1 = false; // 日常 送 体力

	public static boolean  	isOpen2 = true; // 新开服 赠送物品
	
	public static boolean  	isOpen3 = true; // 抽卡送豪礼
	
	public static byte  	isOpen4 = 0; // 英雄主题周( 0 为关闭 )
	
	public static boolean  	isOpen5 = false; // 万圣节 强力武装仆从
	
	public static boolean  	isOpen6 = false; // 最强战神-悟空来袭
	
	public static boolean  	isOpen7 = false; // 圣诞嘉年华( 抢礼物啦 ^^ )
	
	public static boolean  	isOpen8 = false; // 圣诞嘉年华 ( 圣诞老人 )
	
	public static boolean  	isOpen9 = false; // 圣诞嘉年华 ( 英雄降临 )
	
	public static boolean  	isOpen10 = false; // 圣诞嘉年华 ( 圣诞狂欢 )
	
	// ActivityManager.getInstance().isConsumeOrgyIsOpen()
	
	public final String[] name	= { 
			" (01)[ 豪华午,晚宴 ]",
			" (02)[ 强力武装仆从 ]",
			" (03)[ 消费狂欢日 ]", // 精英副本和小龙挑战次数翻倍 每日购买体力次数翻倍
			" (04)[ 高阶技能刷新日 ]",
			" (05)[ 新手礼包 ]",
			" (06)[ 抽卡送豪礼 ]",
			" (07)[ 英雄主题周 ]",
			" (08)[ 万圣节-强力武装仆从 ]",
			" (09)[ 最强战神-悟空来袭 ]",
			" (10)[ 圣诞节-抢礼物啦 ]", // 每日可挑战 圣诞嘉年华 活动副本8次
			" (11)[ 圣诞节-圣诞老人 ]", // 每日单笔充值$99.9获得  圣诞老人*1
			" (12)[ 圣诞节-英雄降临 ]", // 每日累计充值获得英雄
			" (13)[ 圣诞节-圣诞狂欢 ]" // 每日赠送2次100体力
	};
	
	public final String[] desc 	= {
			"",
			"",
			"< 精英副本和小龙挑战次数翻倍 每日购买体力次数翻倍 >",
			"",
			"",
			"",
			"",
			"",
			"",
			"< 每日可挑战 圣诞嘉年华 活动副本8次 >",
			"< 每日单笔充值$99.9获得  圣诞老人*1 >",
			"< 每日累计充值获得英雄 >",
			"< 每日赠送2次100体力 >"
	};
	
	@Override
	public void run( PrintWriter out, byte jurisdiction, String... args ) throws Exception {
		if( !ServerManager.isOpen() ){
			out.print( "服务器尚未开启!\r\n" );
			return;
		}
		
		SS s 			= get( out.toString() );
		switch( s.status ){
		case 0:
			int i		= 0;
			out.print( "\r\n" );
			out.print( foundToString( isOpen1 ) + name[i++] + "\r\n" );
			out.print( foundToString( isOpen2 ) + name[i++] + "\r\n" );
			out.print( foundToString( ActivityManager.getInstance().isConsumeOrgyIsOpen() ) + name[i++] + "\r\n" );
			out.print( foundToString( ActivityManager.getInstance().isRestoreIsOpen() ) + name[i++] + "\r\n" );
			out.print( foundToString( GameData.isHaveMikkaLogin_4 ) + name[i++] + "\r\n" );
			out.print( foundToString( isOpen3 ) + name[i++] + "\r\n" );
			out.print( " " + ( isOpen4 > 0 ? isOpen4 : "□" ) + name[i++] + "\r\n" );
			out.print( foundToString( isOpen5 ) + name[i++] + "\r\n" );
			out.print( foundToString( isOpen6 ) + name[i++] + "\r\n" );
			out.print( foundToString( isOpen7 ) + name[i++] + "\r\n" );
			out.print( foundToString( isOpen8 ) + name[i++] + "\r\n" );
			out.print( foundToString( isOpen9 ) + name[i++] + "\r\n" );
			out.print( foundToString( isOpen10 ) + name[i++] + "\r\n" );
			out.print( "\r\n" );
			out.flush();
			
			s.status 	= 1;
			throw new Exception( "以上是各个活动状态,输入对应编号将改变状态" );
		case 1:
			
			if( args.length < 1 )
				throw new Exception( "参数错误,请重新输入:" );
			
			int index			= -1;
			try {
				index 			= Integer.parseInt( args[0] );
			} catch (Exception e) {
				throw new Exception( "参数错误,请重新输入:" );
			}
			
			out.print( "\r\n" );
			if( index == 1 ){
				isOpen1 = !isOpen1;
				out.print( name[0] + "->" + ( isOpen1 ? "开启" : "关闭" ) + "\r\n" );
			}else if( index == 2 ){
				isOpen2 = !isOpen2;
				out.print( name[1] + "->" + ( isOpen2 ? "开启" : "关闭" ) + "\r\n" );
				if( !isOpen2 ){
					for( UserInfo u : UserManager.getInstance().getMaps().values() ){
						u.xinkai = 0;
						u.setRechargeMoney( 0 );
					}
				}
			}else if( index == 3 ){
				ActivityManager.getInstance().setConsumeOrgyIsOpen( !ActivityManager.getInstance().isConsumeOrgyIsOpen() );
				for( UserInfo user : UserManager.getInstance().getMaps().values() )
					UpdateManager.instance.update( user, UpdateType.U_15 );
				out.print( name[2] + "->" + ( ActivityManager.getInstance().isConsumeOrgyIsOpen() ? "开启" : "关闭" ) + "\r\n" );
			}else if( index == 4 ){
				ActivityManager.getInstance().setRestoreIsOpen( !ActivityManager.getInstance().isRestoreIsOpen() );
				out.print( name[3] + "->" + ( ActivityManager.getInstance().isRestoreIsOpen() ? "开启" : "关闭" ) + "\r\n" );
			}else if( index == 5 ){
				GameData.isHaveMikkaLogin_4 = !GameData.isHaveMikkaLogin_4;
				out.print( name[4] + "->" + ( GameData.isHaveMikkaLogin_4 ? "开启" : "关闭" ) + "\r\n" );
			}else if( index == 6 ){
				isOpen3 = !isOpen3;
				out.print( name[5] + "->" + ( isOpen3 ? "开启" : "关闭" ) + "\r\n" );
			}else if( index == 7 ){
				if( isOpen4 > 0 ) isOpen4 = 0;
				else isOpen4 = 1;
				out.print( name[6] + "->" + ( isOpen4 > 0 ? ("开启(第" + isOpen4 + "天)") : "关闭" ) + "\r\n" );
			}else if( index == 8 ){
				isOpen5 = !isOpen5;
				out.print( name[7] + "->" + ( isOpen5 ? "开启" : "关闭" ) + "\r\n" );
				if( !isOpen5 ){
					for( UserInfo u : UserManager.getInstance().getMaps().values() ){
						u.xinkai1 = 0;
						u.setRechargeMoney1( 0 );
					}
				}
			}else if( index == 9 ){
				isOpen6 = !isOpen6;
				out.print( name[8] + "->" + ( isOpen6 ? "开启" : "关闭" ) + "\r\n" );
			}else if( index == 10 ){
				isOpen7 = !isOpen7;
				out.print( name[9] + "->" + ( isOpen7 ? "开启" : "关闭" ) + "\r\n" );
			}else if( index == 11 ){
				isOpen8 = !isOpen8;
				out.print( name[10] + "->" + ( isOpen8 ? "开启" : "关闭" ) + "\r\n" );
			}else if( index == 12 ){
				isOpen9 = !isOpen9;
				out.print( name[11] + "->" + ( isOpen9 ? "开启" : "关闭" ) + "\r\n" );
			}else if( index == 13 ){
				isOpen10 = !isOpen10;
				out.print( name[12] + "->" + ( isOpen10 ? "开启" : "关闭" ) + "\r\n" );
			}
			
			out.print( "\r\n" );
			
			update();
			return;
		}
		
	}

	private String foundToString( boolean isOpen ) {
		return " " + (isOpen ? "■" : "□");
	}

	public static void update() {
		GameDataProvider.getInstance().updateActivit( dataToString() );
	}

	private static String dataToString() {
		StringBuilder content = new StringBuilder();
		content.append( isOpen1 ? 1 : 0 ).append( "," );
		content.append( isOpen2 ? 1 : 0 ).append( "," );
		content.append( ActivityManager.getInstance().isConsumeOrgyIsOpen() ? 1 : 0 ).append( "," );
		content.append( ActivityManager.getInstance().isRestoreIsOpen() ? 1 : 0 ).append( "," );
		content.append( GameData.isHaveMikkaLogin_4 ? 1 : 0 ).append( "," );
		content.append( isOpen3 ? 1 : 0 ).append( "," );
		content.append( isOpen4 ).append( "," );
		content.append( isOpen5 ? 1 : 0 ).append( "," );
		content.append( isOpen6 ? 1 : 0 ).append( "," );
		content.append( isOpen7 ? 1 : 0 ).append( "," );
		content.append( isOpen8 ? 1 : 0 ).append( "," );
		content.append( isOpen9 ? 1 : 0 ).append( "," );
		content.append( isOpen10 ? 1 : 0 );
		return content.toString();
	}

	public static void initActivit(String string) {
		if( string.isEmpty() )
			return;
		try {
			String[] content = string.split( "," );
			isOpen1 	= content[0].equals( "1" );
			isOpen2 	= content[1].equals( "1" );
			ActivityManager.getInstance().setConsumeOrgyIsOpen( content[2].equals( "1" ) );
			ActivityManager.getInstance().setRestoreIsOpen( content[3].equals( "1" ) );
			GameData.isHaveMikkaLogin_4 = content[4].equals( "1" );
			isOpen3 	= content[5].equals( "1" );
			isOpen4 	= Byte.parseByte( content[6] );
			isOpen5 	= content[7].equals( "1" );
			isOpen6		= content[8].equals( "1" );
			isOpen7		= content[9].equals( "1" );
			isOpen8		= content[10].equals( "1" );
			isOpen9		= content[11].equals( "1" );
			isOpen10	= content[12].equals( "1" );
		} catch (Exception e) {
		}
	}
	
	@Override
	public void clear(PrintWriter out) {
		remove( out.toString() );
	}
	
	private SS get( String key ) {
		for( SS s : sui ){
			if( s.key.equals( key ) ) return s;
		}
		
		SS s 	= new SS();
		s.key 	= key;
		sui.add( s );
		return s;
	}
	
	private void remove( String key ) {
		for( int i = 0; i < sui.size(); i++ ){
			if( sui.get(i).key.equals( key ) ){
				sui.remove( i );
				return;
			}
		}
	}

	
}

class SS{
	
	String 		key;
	
	int 		status	= 0;
	
	String 		name	= "";
}