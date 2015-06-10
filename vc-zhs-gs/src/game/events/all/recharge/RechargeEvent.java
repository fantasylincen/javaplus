package game.events.all.recharge;

import java.io.IOException;
import java.nio.ByteBuffer;

import config.recharge.RechargeTemplet;
import config.recharge.RechargeTempletCfg;
import datalogging.ConsumelogF;
import datalogging.DataLogDataProvider;
import define.SystemCfg;

import net.sf.json.JSONObject;

import recharge.JustHttp;
import recharge.LTVDataProvider;
import recharge.RechargeDataProvider;
import recharge.RechargeManager;

import user.UserInfo;
import user.UserManager;
import util.UtilBase;
import game.events.EventBase;
import game.events.EventDescrip;
import game.log.Logs;

@EventDescrip(desc = "充值")
public class RechargeEvent extends EventBase{

	private final RechargeDataProvider db	= RechargeDataProvider.getInstance();
	
	@Override
	public void run( UserInfo user, ByteBuffer buf ) throws IOException {
		
		String receipt			= UtilBase.decodeString(buf);
		ByteBuffer response 	= buildEmptyPackage( 10480 );
		
//		if( user.isAppleRecharge ) return;
//		user.isAppleRecharge 	= true;
		
//		RechargeTemplet r 		= RechargeTempletCfg.get( receipt );///////////
//		RechargeManager.getInstance().newHandler( user,"12",1, r, "" );//////////
//		response.put( (byte)0 );/////////////////
//		sendPackage( user.getCon(), response );//////////////////
		// 根据收据 获取返回 收据
		String resultReceipt 	= RechargeManager.getInstance().verifyReceipt( receipt );
		if( resultReceipt == null || resultReceipt.isEmpty() ){
			Logs.error( user, "请求充值错误  苹果验证失败 receipt=" + receipt );
			response.put( (byte) -1 );
			UtilBase.encodeString( response, receipt );
			sendPackage( user.getCon(), response );
			return;
		}

		// 取得苹果返回的 数据
		String[] content		= resultReceipt.split("&");
		JSONObject result 		= JSONObject.fromObject( content[0] );
		String transaction_id	= result.getString( "transaction_id" );// 交易ID
		int quantity			= result.getInt( "quantity" );//数量
		String product_id		= result.getString( "product_id" );//商品ID
		String purchase_date	= result.getString( "purchase_date" );//交易时间
		
		///----------- 下面开始各种验证
		byte code 				= -1;
		RechargeTemplet r		= null;
		do{
			// 先从配置表验证 这个商品ID 是否正确
			r = RechargeTempletCfg.get( product_id );
			if( r == null ) { Logs.error( user, "请求充值错误  没有商品ID=" + product_id ); break; };
			
			// 如果是充值月卡  那就看是否还可以充值
//			if( r.isMonthCard() ) if( !user.isCanRecharge( r.getId()%100 ) ){ Logs.error( user, "请求充值错误  月卡天数已经上限 不能再冲" ); break ; }
			
			// 如果是限购 不能再买  这里如果加了 限制会导致前端购买了 扣了钱 但是不给水晶
//			if( r.isRestriction() ) if( !user.chekCanBuyRestriction( r.getId() ) ) { CLog.error( user, "请求充值错误  已经购买过限购 不在再买" ); break ; }
			
			// 然后验证数据库 是否有这个交易ID了
			if( db.verify( user, transaction_id ) ){ Logs.error( user, "请求充值错误  数据库已经有这个交易ID=" + transaction_id );  break; }
			
			// 开始保存数据库
			if( !db.updata( user, transaction_id, quantity, product_id, purchase_date, r, resultReceipt ) ){ break; }
			
			// 开始发放水晶
			RechargeManager.getInstance().newHandler( user,transaction_id,quantity, r, content[0] );
			code = 0;
			
			JustHttp just = new JustHttp();
			just.doPost( transaction_id, "ios", String.valueOf( user.getUID() ), content[0], 
					String.valueOf( r.getMoney() ), "USD", String.valueOf( r.getGold() ), String.valueOf( System.currentTimeMillis() ), 
					SystemCfg.SERVER_NAME, String.valueOf( user.getLevel() ) );
			
			UserManager.getInstance().putUpdate( user );
		}while(false);
		
		response.put( code );
		UtilBase.encodeString( response, receipt );
		sendPackage( user.getCon(), response );
		
		// 这里做下LTV数据
		if( code == 0 ){
			LTVDataProvider.getInstance().addMoney( user, r.getMoney() );
			DataLogDataProvider.getInstance().add( user, ConsumelogF.PREPAID_PHONE, r.getGold() );
		}
		
	}

}
