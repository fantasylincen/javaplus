package login.server.event;

import game.log.Logs;

import java.io.IOException;
import java.nio.ByteBuffer;

import config.recharge.RechargeTemplet;
import config.recharge.RechargeTempletCfg;
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
		Logs.error( "充值成功 " + UserManager.getInstance().getByName(uid).getNickName() + ", goosId=" + goodsId + ", money=" + money + ", pt=" + pt + ", tcd=" + tcd );
		
		RechargeTemplet r	= RechargeTempletCfg.get( goodsId );
		UserInfo user 		= UserManager.getInstance().getByName( uid );
		
		short code 			= 1;
		do{
			if( user == null ) { Logs.error( "充值错误  user为空  tcd=" + tcd ); code = 1; break; }
			
			// 然后验证数据库 是否有这个交易ID了
			if( RechargeDataProvider.getInstance().verify( user, tcd ) ){  Logs.error( user, "请求充值错误  数据库已经有这个交易ID=" + tcd ); code = 2; break; }
			
			// 先从配置表验证 这个商品ID 是否正确
			if( r == null ) { Logs.error( user, "请求充值错误  没有ID=" + goodsId ); code = 1; break; };
			
			// 如果是充值月卡  那就看是否还可以充值
//			if( r.isMonthCard() ) if( !user.isCanRecharge( r.getId()%100 ) ){ Logs.error( user, "请求充值错误  月卡天数已经上限 不能再冲" ); code = 3; break ; }
			
			// 如果是限购 不能再买  这里如果加了 限制会导致前端购买了 扣了钱 但是不给水晶
			//if( r.isRestriction() ) if( !user.chekCanBuyRestriction( r.getId() ) ) { CLog.error( user, "请求充值错误  已经购买过限购 不在再买" ); break ; }
			
			// 开始保存数据库
			if( !RechargeDataProvider.getInstance().create( user, tcd, goodsId, money, 1, "MiniLegend:" + UtilBase.secondsToDateStr( pt ) ) ) break;
			
			// 开始发放水晶
			RechargeManager.getInstance().newHandler( user, tcd, 1, r, "MiniLegend:" + UtilBase.secondsToDateStr( pt ) );
			
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
