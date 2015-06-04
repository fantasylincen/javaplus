package game.events.all.recharge;

import java.io.IOException;
import java.nio.ByteBuffer;


import user.UserInfo;
import util.UtilBase;
import game.events.EventBase;
import game.events.EventDescrip;

@EventDescrip(desc = "充值(91)")
public class RechargeEvent_91 extends EventBase {

//	private final RechargeDataProvider db	= RechargeDataProvider.getInstance();
	
	
	@Override
	public void run(UserInfo user, ByteBuffer buf) throws IOException {
		
//		byte at 		= buf.get();
//		
//		ByteBuffer response = buildEmptyPackage( 32 );
//		response.put( at );
//		
//		int orderId		= 0;
//		
//		switch( at ){
//		
//		case 1:// 请求购买
//		{
//			short id 			= buf.getShort();
//			RechargeTemplet r 	= RechargeTempletCfg.get( id );
//			if( r == null ) {
//				Logs.error( user, "请求充值错误  没有表格ID=" + id );
//				return ;
//			}
//			
//			// 如果是充值月卡  那就看是否还可以充值
////			if( r.isMonthCard() ) if( !user.isCanRecharge( r.getId()%100 ) ){
////				CLog.error( user, "请求充值错误  月卡天数已经上限 不能再冲" );
////				return ;
////			}
//			
//			orderId				= db.add( user, r );
//			UtilBase.encodeString( response, Sdk_91.getConvertOrderSerial( orderId, user.getUID() ) );
//			sendPackage( user.getCon(), response );
//		}
//		break;
//		
//		case 2:// 请求验证
//		{
//			String orderid	= UtilBase.decodeString(buf);
////			System.out.println( "开始请求  orderId " + orderid );
//			orderId			= Sdk_91.getOrderSerialToStr(orderid);
//			int result 		= db.verify_91( orderId )[0];
//			
////			System.out.println( "开始请求  result " + result );
//			if( result == 1 ) return;
//			if( result == -1 || result == 2 ){
//				response.putInt( -1 );
//				sendPackage( user.getCon(), response );
//				return;
//			}
//			
//			// 如果平台已经返回 那么就要充值了 
//			if( result == 0 ){
////				System.out.println( "开始请求  开始充值" );
//				RechargeManager.getInstance().handler( orderId );
//				response.putInt( 0 );
//				sendPackage( user.getCon(), response );
//			}
//			
//			// 这里检查 91是否有这个订单
//			try {
//				Sdk_91 sdk = new Sdk_91();
//				if( !sdk.queryPayResult( orderid ) ){
//					response.putInt( -1 );
//					sendPackage( user.getCon(), response );
//					return;
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//			// 最后 刷新数据库
//			db.updata_91_c( orderId, result );
//		}
//		break;	
//		
//		}
	}
	
	public void run( UserInfo user, short code, String transaction_id ) throws IOException{
		
		if( user == null || !user.isOnline() ) return;
		
		ByteBuffer response = buildEmptyPackage( 1024 );
		response.putShort( code );
		UtilBase.encodeString( response, transaction_id );
		sendPackage( user.getCon(), response );
	}

}
