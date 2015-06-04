package http;

import java.io.IOException;
import java.util.Map;

import server.ServerInfo;
import server.ServerManager;
import server.event.InformRechargeEvent;
import util.MainArgs;
import util.md5.MD5;

import com.alibaba.fastjson.JSON;

import events.Event;

public class RechargeHandler extends AbstractAsyncHandler {

	private static final String key = "LINGCHENGDENGXINFENG";
	private static final String[] desc = {
		"did",
		"uid",
		"goodsid",
		"money",
		"pt",
		"tcd",
		"st",
		"sign"
	};

	public RechargeHandler() {
	}

	@Override
	protected void doGet(Map<String, Object> p, Response response ) throws IOException {

		int code = -1;
		ServerInfo server;
		
		try {
			int did 		= new Integer(p.get( desc[++code] ) + "");// * did Int 1 玩家所属服务器ID
			int uid 		= new Integer(p.get( desc[++code] ) + "");// * uid Int 256413254 玩家角色在服务器的唯一ID
			String goodsId 	= p.get( desc[++code] ) + "";// * goodsid String Vcvc12 充值的商品ID
			float money 	= Float.parseFloat( p.get( desc[++code] ) + "" );// * money float 1.00 充值金额
			int pt 			= new Integer(p.get( desc[++code] ) + "");// * pt Long 1370551555 支付完成时间
			String tcd 		= p.get( desc[++code] ) + "";// * tcd String 123465 订单在平台上的订单号
			int st 			= new Integer(p.get( desc[++code] ) + "");// * st Int 1 是否充值成功 1表示成功 其他表示失败
			String sign 	= p.get( desc[++code] ) + "";// * sign String Fas4654s54asd 双方协议签名（下面会有说明）
			
			code			= -1;
			server 			= ServerManager.getInstance().get((byte)did);
			
			if( server == null ) 
				throw new Exception( "did参数无效" );
			if( st != 1 ) 
				throw new Exception( "st不为1" );
			
			String nsign = "did=" + did + "&uid=" + uid + "&goodsid=" + goodsId + "&money=" + money + "&pt=" + pt + "&tcd=" + tcd + "&st=" + st;
			if( !MainArgs.contains("debug") && !sign.equalsIgnoreCase( MD5.md5( nsign + key )) ) 
				throw new Exception( "sign无效" );
			
			InformRechargeEvent gue = (InformRechargeEvent)Event.INFORM_RECHARGE.getEventInstance();
			gue.run( server.getCon(), did,uid,goodsId,money,pt,tcd,st, response );
		} catch (Exception e) {
			String x		= code == -1 ? e.getMessage() : (desc[code] + "为NULL");
			String jsonString = JSON.toJSONString( new RScorr( "error", x ) );
			response.sendString(jsonString);
		}
		
	}

}
