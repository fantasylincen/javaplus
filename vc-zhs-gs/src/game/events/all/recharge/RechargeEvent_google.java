package game.events.all.recharge;

import java.io.IOException;
import java.nio.ByteBuffer;

import net.sf.json.JSONObject;

import recharge.RechargeDataProvider;
import recharge.RechargeManager;
import recharge.google.GoogleVerifyPurchase;

import config.recharge.RechargeTemplet;
import config.recharge.RechargeTempletCfg;

import user.UserInfo;
import util.UtilBase;
import game.events.EventBase;
import game.events.EventDescrip;
import game.log.Logs;

@EventDescrip(desc = "google充值")
public class RechargeEvent_google extends EventBase{

	private final RechargeDataProvider db	= RechargeDataProvider.getInstance();
	
	@Override
	public void run(UserInfo user, ByteBuffer buf) throws IOException {
		
		String signedData	= UtilBase.decodeString(buf);// josn 数据
		String signature	= UtilBase.decodeString(buf);// 签名
		byte versionsType	= buf.get(); // 版本类型
	
		JSONObject json 	= JSONObject.fromObject( signedData );
		String orderId		= json.getString( "orderId" );		// 订单号
		String productId 	= json.getString( "productId" );;	// 商品ID
		RechargeTemplet r 	= RechargeTempletCfg.get( productId );
		
		if( orderId.isEmpty() || r == null ) {
			Logs.error( "google充值失败  信息为空" );
			return;
		}
		
		// 如果是充值月卡  那就看是否还可以充值
//		if( r.isMonthCard() ) if( !user.isCanRecharge( r.getId()%100 ) ){
//			Logs.error( user, "请求充值错误  月卡天数已经上限 不能再冲" );
//			return ;
//		}
		
//		if( r.isRestriction() && !user.chekCanBuyRestriction( r.getId() ) ){
//			Logs.error( user, "请求充值错误  限购已经购买过 不能再次购买！" );
//			return ;
//		}
		
		//// 下面开始进行验证----------------------
		
		// 先检查 是否有该订单
		int result = db.verify_google( user, orderId, r );
		
		if( result == 0 ){
			// 这里验证订单号是否有效
			if( GoogleVerifyPurchase.verifyPurchase(signedData, signature, versionsType) ){
				RechargeManager.getInstance().handler( user, orderId, r );
				db.create( user, orderId , r, productId );
			}else{
				result = -1;
			}
		}
		
		ByteBuffer response = buildEmptyPackage( 32 );
		response.putInt( result );
		sendPackage( user.getCon(), response );
			
	}

}
