package nd;

import game.events.Event;
import game.events.all.recharge.RechargeEvent_tb;
import game.log.Logs;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;

import config.recharge.RechargeTemplet;
import config.recharge.RechargeTempletCfg;
import recharge.RechargeDataProvider;
import recharge.RechargeManager;
import user.UserInfo;
import user.UserManager;
import define.SystemCfg;

/**
 * 同步SDK
 * @author DXF
 *
 */
public class Sdk_tb {

	// 这里设置你的APPID
	private static String appid 	= "150196";
	// 这里设置你的APPKEY
	private static String appkey 	= "3sCZmcz*WLjvG9Sf3sZPmbJ#Wi7vFSpf";
	
	public static String getConvertOrderSerial( int orderId, int uid ) {
		return MessageFormat.format( "{0}-{1}-{2}", SystemCfg.GAME_DISTRICT+"", uid+"", orderId+"" );
	}
	
	private static Integer getUserID( String trade_no ) {
		return Integer.parseInt( trade_no.split("-")[0] );
	}

	public static int getOrderSerialToStr( String orderid ) {
		return Integer.parseInt( orderid.split("-")[2] );
	}

	public static void payResultNotify(String source, String trade_no,
			String amount, String partner, String paydes, String debug,
			String tborder, String fromSign) throws IOException {
		
		StringBuilder strSign = new StringBuilder();
		strSign.append( "source=" ).append( source ).append( "&" );
		strSign.append( "trade_no=" ).append( trade_no ).append( "&" );
		strSign.append( "amount=" ).append( amount ).append( "&" );
		strSign.append( "partner=" ).append( partner ).append( "&" );
		strSign.append( "paydes=" ).append( paydes ).append( "&" );
		strSign.append( "debug=" ).append( debug ).append( "&" );
		strSign.append( "tborder=" ).append( tborder ).append( "&" );
		strSign.append( "key=" ).append( appkey );
		
		String sign 		= md5( strSign.toString() );
		
		RechargeTemplet r	= RechargeTempletCfg.get( Short.parseShort( paydes ) );
		UserInfo user 		= UserManager.getInstance().getByName( getUserID( trade_no ) );
		short code 			= 1;
		
		do{
			if( user == null ) { Logs.error( "充值错误  user为空  cooOrderSerial=" + trade_no ); break; }
			
			if( !appid.equals(partner) ) { Logs.error( user, "请求充值错误  无效partner=" + partner ); break; }
			
			if ( !"tongbu".equals(source) ) { Logs.error( user, "请求充值错误  无效source=" + source ); break; }
			
			// 检查秘银是否正确
			if( !sign.toLowerCase().equals(fromSign.toLowerCase()) ) { Logs.error( user, "请求充值错误  无效 1sign=" + fromSign + ", 2sign=" + sign ); break; }
			
			// 然后验证数据库 是否有这个交易ID了
			if( RechargeDataProvider.getInstance().verify( user, tborder ) ){  Logs.error( user, "请求充值错误  数据库已经有这个交易ID=" + tborder ); break; }
			
			// 先从配置表验证 这个商品ID 是否正确
			if( r == null ) { Logs.error( user, "请求充值错误  没有ID=" + paydes ); break; };
			
			// 如果是充值月卡  那就看是否还可以充值
//			if( r.isMonthCard() ) if( !user.isCanRecharge( r.getId()%100 ) ){ Logs.error( user, "请求充值错误  月卡天数已经上限 不能再冲" ); break ; }
			
			// 如果是限购 不能再买  这里如果加了 限制会导致前端购买了 扣了钱 但是不给水晶
			//if( r.isRestriction() ) if( !user.chekCanBuyRestriction( r.getId() ) ) { CLog.error( user, "请求充值错误  已经购买过限购 不在再买" ); break ; }
			
//			float money 	= Float.parseFloat( orderMoney );
//			int quantity	= Integer.parseInt( goodsCount );
			// 开始保存数据库
			if( !RechargeDataProvider.getInstance().create( user, tborder, paydes, r.getMoney(), 1, strSign.toString() + "&sign=" + fromSign ) ) break;
			
			// 开始发放水晶
			RechargeManager.getInstance().newHandler( user, tborder, 1, r, debug.equals("1") ? "sandbox" : "buy" );
			
			UserManager.getInstance().putUpdate( user );
			
			code = 0;
		} while ( false );
		
		if( user != null ) {
			RechargeEvent_tb rec_tb = (RechargeEvent_tb) Event.RECHARGE_EVENT_TB.getEventInstance();
			rec_tb.run( user , code, trade_no );
		}
	}

	
	

	/**
	 * 对字符串进行MD5并返回结果
	 * 
	 * @param sourceStr
	 * @return
	 */
	private static String md5(String sourceStr) {
		String signStr = "";
		try {
			byte[] bytes = sourceStr.getBytes("utf-8");
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(bytes);
			byte[] md5Byte = md5.digest();
			if (md5Byte != null) {
				signStr = HexBin_91.encode(md5Byte);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return signStr;
	}
}
