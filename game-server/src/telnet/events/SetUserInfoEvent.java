package telnet.events;

import game.award.AwardInfo;
import game.award.AwardType;
import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;
import game.mail.MailBase;
import game.mail.MailType;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import config.aa.ActivityAwardTempletCfg;
import config.vip.VipInfoTemplet;
import config.vip.VipInfoTempletCfg;
import datalogging.ConsumelogF;
import datalogging.DataLogDataProvider;

import notice.NoticeManager;


import manager.DWType;

import recharge.RechargeManager;
import server.ServerManager;
import telnet.CommandBase;
import user.UserInfo;
import user.UserManager;
import util.SystemTimer;

/**
 * 设置玩家信息
 * @author DXF
 *
 */
public class SetUserInfoEvent extends CommandBase {
	
	private List<S> sui			= new ArrayList<S>();
	
	private String[] arglist 	= { " 0: 添加金币( 数量 )", 
									" 1: 添加水晶( 数量 )", 
									" 2: 设置等级( 等级 )", 
									" 3: 添加经验( 数量 )", 
									" 4: 修改名字( 名字 )", 
									" 5: 添加英雄( 表格ID 数量 英雄等级 颜色 颜色等级 )", 
									" 6: 添加装备( 表格ID 数量 )", 
									" 7: 添加体力( 数量 )",
									" 8: 充值水晶( 数量 )",
									" 9: 发送邮件( 类型[0,纯文字 1,物品] 物品类型  物品ID 数量 其他参数 )",
									"10: 添加奖杯( 数量 )",
									"11: 加段位分( 数量 )",
									"12: 设置VIP( 等级 )"
									
									};
	private String[] arglist1 	= { "金币=", "水晶=", "等级=", "经验=", "名字=", "英雄=", "装备=", "体力=", "水晶=", "邮件=", "奖杯=", "段位分=", "VIP等级=" };
	
	@Override
	public void run( PrintWriter out, byte jurisdiction, String... args ) throws Exception {
		
		if( !ServerManager.isOpen() ){
			out.print( "服务器尚未开启!\r\n" );
			return;
		}
		
		S s 			= get( out.toString() );
		
		switch( s.status ){
		case 0:
			
			s.name		=  args[0];
			
			UserInfo u	= UserManager.getInstance().getByNickName( s.name );
			if( u == null )
				throw new Exception( "参数错误,请重新输入:" );
			
			out.print( "你可以输入以下属性 空格后跟对应数值:\r\n" );
			for( int i = 0; i < arglist.length - 1; i++ )
				out.print( arglist[i] + "\r\n" );
			out.flush();
			
			s.status 	= 1;
			throw new Exception( arglist[ arglist.length - 1 ] );
		case 1:
			
			if( args.length < 2 )
				throw new Exception( "参数错误,请重新输入:" );
			
			int index			= -1;
			try {
				index 			= Integer.parseInt( args[0] );
			} catch (Exception e) {
				throw new Exception( "参数错误,请重新输入:" );
			}
			
			if( index < 0 || index >= arglist.length )
				throw new Exception( "参数错误,请重新输入:" );
			
			u					= UserManager.getInstance().getByNickName( s.name );
			if( u == null ){
				s.status		= 0;
				throw new Exception( "该玩家不存在,请重新输入玩家名字:" );
			}
			
			String str 			= set( u, index, args );
			
			out.print( "完成对<" + u.getNickName() + ">的设置  当前 " + str + "\r\n" );
			return;
		}
		
	}

	private S get( String key ) {
		for( S s : sui ){
			if( s.key.equals( key ) ) return s;
		}
		
		S s 	= new S();
		s.key 	= key;
		sui.add( s );
		return s;
	}

	@Override
	public void clear(PrintWriter out) {
		remove( out.toString() );
	}

	private void remove( String key ) {
		for( int i = 0; i < sui.size(); i++ ){
			if( sui.get(i).key.equals( key ) ){
				sui.remove( i );
				return;
			}
		}
	}

	private String set( UserInfo u, int index, String ... value ) throws Exception{
		
		int i			= 0;
		int idx			= 1;
		
		switch( index ){
		case 0:
			if( value.length != 2 )
				throw new Exception( "参数错误,请重新输入:" );
			
			try {
				i		= Integer.parseInt( value[idx] );
			} catch (Exception e) {
				throw new Exception( "参数错误,请重新输入:" );
			}
			u.changeAward( AwardType.CASH, i, "GM赠送", DWType.SYSTEM_IS_PRESENTED );
			UpdateManager.instance.update( u, UpdateType.U_3 );
			return arglist1[index] + u.getCash();
		case 1:
			if( value.length != 2 )
				throw new Exception( "参数错误,请重新输入:" );
			
			try {
				i		= Integer.parseInt( value[idx] );
			} catch (Exception e) {
				throw new Exception( "参数错误,请重新输入:" );
			}
			u.changeAward( AwardType.GOLD, i, "GM赠送", DWType.SYSTEM_IS_PRESENTED );
			UpdateManager.instance.update( u, UpdateType.U_4 );
//			RechargeManager.getInstance().recharge( u, i );
			DataLogDataProvider.getInstance().add( u, ConsumelogF.DISTRIBUTED_SYSTEM, i );
			return arglist1[index] + u.getGold();
		case 2:
			if( value.length != 2 )
				throw new Exception( "参数错误,请重新输入:" );
			
			try {
				i		= Integer.parseInt( value[idx] );
			} catch (Exception e) {
				throw new Exception( "参数错误,请重新输入:" );
			}
			
			int curlv 		= u.getLevel();
			short isUpgrade = 0;
			if( curlv >= i )
				throw new Exception( "输入等级太小,请重新输入:" );
			
			while( curlv < i ){
				u.upLevel();
				++isUpgrade;
				curlv = u.getLevel();
			}
			u.handleUpgrade( isUpgrade );
			
			UpdateManager.instance.update( u, UpdateType.U_1 );
			
			return arglist1[index] + u.getLevel();
		case 3:
			if( value.length != 2 )
				throw new Exception( "参数错误,请重新输入:" );
			
			try {
				i		= Integer.parseInt( value[idx] );
			} catch (Exception e) {
				throw new Exception( "参数错误,请重新输入:" );
			}
			
			u.changeAward( AwardType.EXP, i, "GM赠送", DWType.SYSTEM_IS_PRESENTED );
			UpdateManager.instance.update( u, UpdateType.U_1 );
			return arglist1[index] + u.getCurExp();
		case 4:
			{
			if( value.length != 2 )
				throw new Exception( "参数错误,请重新输入:" );
			
			// 修改名字
			if( !UserManager.getInstance().modifyName( u, value[idx] ) )
				throw new Exception( "名字不合法或者重复,请重新输入:" );
			
			return arglist1[index] + u.getNickName();
			}
		case 5:
			if( value.length < 3 )
				throw new Exception( "参数错误,请重新输入:" );
			
			int id 			= Integer.parseInt( value[1] );
			int num			= Integer.parseInt( value[2] );
			int[] argus		= null;
			if( value.length > 3 ){
				argus		= new int[value.length-3];
				for( int ii = 3; ii < value.length; ii++ )
					argus[ii-3]	= Integer.parseInt( value[ii] );
			}
			AwardInfo award	= new AwardInfo(AwardType.HERO, id, num);
			award.setArguments( argus );
			
			u.changeAward(award, "GM修改", DWType.MISCELLANEOUS );
			return "添加英雄成功";
		case 6:
			if( value.length < 3 )
				throw new Exception( "参数错误,请重新输入:" );
			
			int eid			= Integer.parseInt( value[1] );
			int nnum		= Integer.parseInt( value[2] );
//			int[] nargus	= null;
//			if( value.length > 3 ){
//				nargus		= new int[value.length-3];
//				for( int ii = 3; ii < value.length; ii++ )
//					nargus[ii-3]	= Integer.parseInt( value[ii] );
//			}
			AwardInfo naward= new AwardInfo(AwardType.PROP, eid, nnum);
//			naward.setArguments( nargus );
			
			u.changeAward( naward, "GM修改", DWType.MISCELLANEOUS );
			return "装备添加成功";
		case 7:
//			UserManager.getInstance().clearUser( u.getNickName() );
			
			if( value.length != 2 )
				throw new Exception( "参数错误,请重新输入:" );
			
			try {
				i		= Integer.parseInt( value[idx] );
			} catch (Exception e) {
				throw new Exception( "参数错误,请重新输入:" );
			}
			u.changeStrength( i, "GM赠送" );
			UpdateManager.instance.update( u, UpdateType.U_2 );
			return arglist1[index] + u.getStrength();
		case 8:
			if( value.length != 2 )
				throw new Exception( "参数错误,请重新输入:" );
			
			try {
				i		= Integer.parseInt( value[idx] );
			} catch (Exception e) {
				throw new Exception( "参数错误,请重新输入:" );
			}
			
			// 先保存数据库
//			RechargeDataProvider.getInstance().add( u, i );
			// 是否新增用户
			int isNewly 		= u.getRechargeGold() > 0 ? 0 : 1;
			// 然后充值
			RechargeManager.getInstance().recharge( u, i );
			float money = u.getRechargeMoney() + (int)i/50;
			u.setRechargeMoney( money );
			money = u.getRechargeMoney1() + (int)i/50;
			u.setRechargeMoney1( money );
			// 如果是首冲
			if( isNewly == 1 )
				ActivityAwardTempletCfg.shouChong( i/50, u );
			if( SetActivityEevet.isOpen2 )
				ActivityAwardTempletCfg.xinKai( u );
			if( SetActivityEevet.isOpen5 )
				ActivityAwardTempletCfg.xinKai1( u );
			// 最后刷新给前端
			UpdateManager.instance.update( u, UpdateType.U_4 );
			DataLogDataProvider.getInstance().add( u, ConsumelogF.DISTRIBUTED_SYSTEM, i );
			return arglist1[index] + u.getGold();
		case 9:// 9  类型  物品类型  物品ID 数量 其他参数
			if( value.length < 3 )
				throw new Exception( "参数错误,请重新输入:" );
			
			try {
				i		= Integer.parseInt( value[idx] );
			} catch (Exception e) {
				throw new Exception( "参数错误,请重新输入:" );
			}
			
			if( i == 0 ){
				MailBase mail = new MailBase( NoticeManager.noticeName, MailType.SYSTEM_NOTICE, value[++idx]  );
				u.getMailManager().addMail( mail );
			}else{
				
				if( value.length < 5 )
					throw new Exception( "参数错误,请重新输入:" );
				
				try {
					AwardType atype	= AwardType.fromNumber( Integer.parseInt( value[++idx] ) );
					int id_m		= Integer.parseInt( value[++idx] );
					int num_m		= Integer.parseInt( value[++idx] );
					int[] argus_m	= null;
					if( value.length > 5 ){
						argus_m		= new int[value.length-5];
						for( int l = 5; l < value.length; l++ )
							argus_m[l-5]	= Integer.parseInt( value[l] );
					}
					AwardInfo award_m	= new AwardInfo(atype, id_m, num_m);
					award_m.setArguments( argus_m );
					
					MailBase mail = new MailBase( award_m, MailType.SYSTEM_PRESENT );
					u.getMailManager().addMail( mail );
				} catch (Exception e) {
					throw new Exception( "参数错误,请重新输入:" );
				}
			}
			return arglist1[index] + "发送成功!";
		case 10:
			if( value.length != 2 )
				throw new Exception( "参数错误,请重新输入:" );
			
			try {
				i		= Integer.parseInt( value[idx] );
			} catch (Exception e) {
				throw new Exception( "参数错误,请重新输入:" );
			}
			u.changeAward( AwardType.TROPHY, i, "GM赠送", DWType.SYSTEM_IS_PRESENTED );
			UpdateManager.instance.update( u, UpdateType.U_29 );
			return arglist1[index] + u.getTrophyNumer();
		case 11:
			if( value.length != 2 )
				throw new Exception( "参数错误,请重新输入:" );
			
			try {
				i		= Integer.parseInt( value[idx] );
			} catch (Exception e) {
				throw new Exception( "参数错误,请重新输入:" );
			}
			u.getDanGradingManager().getInfo().addGrade( i );
			UpdateManager.instance.update( u, UpdateType.U_9 );
			return arglist1[index] + u.getDanGradingManager().getInfo().grade();
		case 12:
			if( value.length != 2 )
				throw new Exception( "参数错误,请重新输入:" );
			
			try {
				i		= Byte.parseByte( value[idx] );
			} catch (Exception e) {
				throw new Exception( "参数错误,请重新输入:" );
			}
			
			byte clv 		= u.getVipLevel();
			if( clv >= i )
				throw new Exception( "输入等级太小,请重新输入:" );
			
			VipInfoTemplet v		= VipInfoTempletCfg.get( (byte)i );
			if( v == null ) throw new Exception( "参数错误,请重新输入:" );
			
			boolean isUp			= false;
			boolean isBuyStr		= false;
			boolean isStr			= false;
			boolean isDanBuy		= false;
			
			while( i >= ++clv ){
				v					= VipInfoTempletCfg.get( clv );
				if( v == null ) break;
				
				isUp				= true;
				
				if( v.getBuyStrCount() != 0 ){
					isBuyStr			= true;
					u.setBuyStrCountMax( (byte) (u.getBuyStrCountMax() + v.getBuyStrCount()) );
				}
				if( v.getPhysicalLimit() != 0 ){
					isStr				= true;
					u.setStrengthMax( (short) (u.getStrengthMax() + v.getPhysicalLimit()) );
				}
				if( v.getBuyPvpCount() != 0 ){
					isDanBuy			= true;
					u.setPvpMateBuyCount( (byte) (u.getPvpMateBuyCount() + v.getBuyPvpCount()) );
				}
				
				u.setVipLevel( clv );
			}
			
			if( isUp )
				UpdateManager.instance.update( u, UpdateType.U_26 );
			if( isBuyStr )
				UpdateManager.instance.update( u, UpdateType.U_15 );
			if( isStr ){
				if( u.getStrReplyTime() == 0 && u.getStrength() < u.getStrengthMax() )
					u.setStrReplyTime1( SystemTimer.currentTimeSecond() );
				UpdateManager.instance.update( u, UpdateType.U_2 );
			}
			if( isDanBuy )
				UpdateManager.instance.update( u, UpdateType.U_17 );
			
			UserManager.getInstance().putUpdate( u );
			
			return arglist1[index] + u.getVipLevel();
		}
	
		return "";
	}

}

class S{
	
	String 		key;
	
	int 		status	= 0;
	
	String 		name	= "";
	
	public Object temp;
}

