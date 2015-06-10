package login.server.event;

import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;
import game.log.Logs;

import java.io.IOException;
import java.nio.ByteBuffer;

import config.recharge.RechargeTemplet;
import config.recharge.RechargeTempletCfg;
import define.SystemCfg;
import recharge.RechargeDataProvider;
import recharge.RechargeManager;
import server.ServerManager;
import user.UserInfo;
import user.UserManager;
import util.UtilBase;

public class InformRechargeEvent extends ILEvent{

	@Override
	public void run( UserInfo x, ByteBuffer buf ) throws IOException {
		
		int uid 		= buf.getInt();// 玩家唯一ID
		String goodsId 	= UtilBase.decodeString(buf);// 商品ID
		float money 	= buf.getFloat();//充值现金
		int pt 			= buf.getInt();//充值完成时间
		String tcd 		= UtilBase.decodeString(buf);//订单号
		
		
		///////////////////////// 这里开始充值 /////////////////////////
//		Logs.error( "充值成功 " + UserManager.getInstance().getByName(uid).getNickName() + ", goosId=" + goodsId + ", money=" + money + ", pt=" + pt + ", tcd=" + tcd );
		
		RechargeTemplet r	= RechargeTempletCfg.get( goodsId );
		UserInfo user 		= UserManager.getInstance().getByName( uid );
		
		short code 			= 1;
		do{
			if( user == null ) { Logs.error( "充值错误  user为空  tcd=" + tcd ); code = 1; break; }
			
			// 然后验证数据库 是否有这个交易ID了
			if( RechargeDataProvider.getInstance().verify( user, tcd ) ){  Logs.error( user, "请求充值错误  数据库已经有这个交易ID=" + tcd ); code = 2; break; }
			
			// 看下这里是否 定制的 如果不是要另外处理
			if( SystemCfg.PLATFORM.equals( "DZ" ) ){
				
				// 开始保存数据库
				if( !RechargeDataProvider.getInstance().create( user, tcd, "dz", money, 1, "定制:" + UtilBase.secondsToDateStr( pt ) ) ) break;
				user.RMB += money;
				UpdateManager.instance.update( user, UpdateType.U_36 );
				
			} else {
				
				// 先从配置表验证 这个商品ID 是否正确
				if( r == null ) { Logs.error( user, "请求充值错误  没有ID=" + goodsId ); code = 1; break; };
				
				// 开始保存数据库
				if( !RechargeDataProvider.getInstance().create( user, tcd, goodsId, money, 1, "MiniLegend:" + UtilBase.secondsToDateStr( pt ) ) ) break;
				
				// 开始发放水晶
				RechargeManager.getInstance().newHandler( user, tcd, 1, r, "MiniLegend:" + UtilBase.secondsToDateStr( pt ) );
			}
			
			UserManager.getInstance().putUpdate( user );
			code = 0;
		} while ( false );
		
		////////  返回结果 
		ByteBuffer buffer = buildEmptyPackage( 256 );
		buffer.putInt( uid );
		buffer.putShort( code );
		sendPackage( ServerManager.getLoginCon(), buffer );
	}

}
