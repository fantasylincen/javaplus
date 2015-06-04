package game.events.all.recharge;

import java.io.IOException;
import java.nio.ByteBuffer;

import recharge.RechargeManager;

import config.recharge.RechargeTemplet;
import config.recharge.RechargeTempletCfg;

import user.UserInfo;
import user.UserManager;
import game.events.EventBase;
import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;
import game.log.Logs;

public class RechargeEvent_lb2 extends EventBase{

	@Override
	public void run(UserInfo user, ByteBuffer buf) throws IOException {
		short id 			= buf.getShort();
		short code 			= -1;
		RechargeTemplet r	= null;
		do{
			// 先从配置表验证 这个商品ID 是否正确
			r = RechargeTempletCfg.get( id );
			if( r == null ) { Logs.error( user, "请求充值错误  没有ID=" + id ); break; };
			
			// 如果是充值月卡  那就看是否还可以充值
//			if( r.isMonthCard() ) if( !user.isCanRecharge( r.getId()%100 ) ){ Logs.error( user, "请求充值错误  月卡天数已经上限 不能再冲" ); break ; }
			
			// 如果是限购 不能再买  这里如果加了 限制会导致前端购买了 扣了钱 但是不给水晶
//			if( r.isRestriction() ) if( !user.chekCanBuyRestriction( r.getId() ) ) { CLog.error( user, "请求充值错误  已经购买过限购 不在再买" ); break ; }
			
			// 开始保存数据库
			if( r.getMoney() > user.RMB ){ Logs.error( user, "请求充值错误  余额不足 change=" + (user.RMB - r.getMoney()) );  break;  }
			user.RMB -= r.getMoney();
			
			// 开始发放水晶
			RechargeManager.getInstance().newHandler( user, "", 1, r, "buy" );
			code = 0;
			
			UserManager.getInstance().putUpdate( user );
		}while(false);
		
		ByteBuffer response 	= buildEmptyPackage( 125 );
		response.putShort( code );
		sendPackage( user.getCon(), response );
		if( code == 0 )
			UpdateManager.instance.update( user, UpdateType.U_36 );
	}

}
