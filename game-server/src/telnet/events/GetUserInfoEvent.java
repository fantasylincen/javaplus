package telnet.events;

import game.util.fighting.FightingFormula;

import java.io.PrintWriter;


import server.ServerManager;
import telnet.CommandBase;
import user.UserInfo;
import user.UserManager;
import util.UtilBase;

/**
 * 获取玩家信息
 * @author DXF
 */
public class GetUserInfoEvent extends CommandBase{

	@Override
	public void run( PrintWriter out, byte jurisdiction, String... args ) throws Exception {
		
		if( !ServerManager.isOpen() ){
			out.print( "服务器尚未开启!\r\n" );
			return;
		}
		
		if( args.length != 1 )
			throw new Exception( "参数错误,请重新输入:" );
		
		UserInfo u		= UserManager.getInstance().getByNickName( args[0] );
		if( u == null )
			throw new Exception( "参数错误,请重新输入:" );
		
		u.getStrReplyTimeToSecond( 1 );
		
		out.print( "\r\n" );
		out.print( "玩家<" + u.getNickName() + "> 的基本信息如下:\r\n" );
		out.print( "IP:	    \t" + u.remoteAddress() + "\r\n" );
		out.print( "创建时间:\t" + UtilBase.secondsToDateStr( u.getCreateTime() ) + "\r\n" );
		out.print( "UID:    \t" + u.getUID() + "\r\n" );
		out.print( "等级:   \t" + u.getLevel() + "\r\n" );
		out.print( "经验:   \t" + u.getCurExp() + "\r\n"  );
		out.print( "体力:   \t" + u.getStrength() + "\r\n"  );
		out.print( "最大体力:\t" + u.getStrengthMax() + "\r\n"  );
		out.print( "金币:   \t" + u.getCash() + "\r\n"  );
		out.print( "水晶:   \t" + u.getGold() + "\r\n"  );
		out.print( "战斗力: \t" + FightingFormula.run( u ) + "\r\n"  );
		out.print( "段位:   \t" + u.getDanGradingManager().getInfo().danGrad().toName() + "\r\n"  );
		out.print( "英雄个数:\t" + u.getHeroManager().getLists().size() + "\r\n"  );
		out.print( "英雄上限:\t" + u.getBagCapacity() + "\r\n"  );
		out.print( "在线情况:\t" + (u.isOnline() ? "在线" : "离线(".concat( UtilBase.secondsToDateStr(u.getLastLogoutTime()) ).concat(")") ) + "\r\n"  );
		out.print( "\r\n" );
	}


	@Override
	public void clear(PrintWriter out) {
		
	}

}
