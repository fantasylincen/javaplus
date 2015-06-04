package nd;

import game.events.Event;
import game.events.all.recharge.RechargeEvent_91;
import game.log.Logs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
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
import net.sf.json.JSONObject;

public class Sdk_91 {

	// 这里设置你的APPID
	private String appid = "112409";
	// 这里设置你的APPKEY
	private String appkey = "76c955e9580ad0902c405bc6402ed15d98b554a4d40946df";
	// 91的服务器地址
	private String goUrl = "http://service.sj.91.com/usercenter/ap.aspx?";

	/**
	 * 查询支付购买结果的API调用
	 * 
	 * @param cooOrderSerial
	 *            商户订单号
	 * @return ERRORCODE的值
	 * @throws Exception
	 *             API调用失败
	 */
	public boolean queryPayResult(String cooOrderSerial) throws Exception {
		String act = "1";
		StringBuilder strSign = new StringBuilder();
		strSign.append(appid);
		strSign.append(act);
		strSign.append(cooOrderSerial);
		strSign.append(appkey);
		String sign = md5(strSign.toString());
		StringBuilder getUrl = new StringBuilder();
		getUrl.append("Appid=");
		getUrl.append(appid);
		getUrl.append("&Act=");
		getUrl.append(act);
		getUrl.append("&CooOrderSerial=");
		getUrl.append(cooOrderSerial);
		getUrl.append("&Sign=");
		getUrl.append(sign);
		
		String jsonStr 	= HttpGetGo(getUrl.toString());
		JSONObject jo 	= JSONObject.fromObject(jsonStr);
		
		int code		= Integer.parseInt(jo.getString("ErrorCode"));
		if( code != 1 ) return false;

		return Integer.parseInt(jo.getString("PayStatus")) == 1;
	}
	public String queryPayResult1(String cooOrderSerial) throws Exception {
		String act = "1";
		StringBuilder strSign = new StringBuilder();
		strSign.append(appid);
		strSign.append(act);
		strSign.append(cooOrderSerial);
		strSign.append(appkey);
		String sign = md5(strSign.toString());
		StringBuilder getUrl = new StringBuilder();
		getUrl.append("Appid=");
		getUrl.append(appid);
		getUrl.append("&Act=");
		getUrl.append(act);
		getUrl.append("&CooOrderSerial=");
		getUrl.append(cooOrderSerial);
		getUrl.append("&Sign=");
		getUrl.append(sign);
		return HttpGetGo(getUrl.toString());
	}

	/**
	 * 检查用户登陆SESSIONID是否有效
	 * 
	 * @param uin
	 *            91账号ID
	 * @param sessionID
	 * @return
	 * @throws Exception
	 */
	public int checkUserLogin(String uin, String sessionID) throws Exception {
		String act = "4";
		StringBuilder strSign = new StringBuilder();
		strSign.append(appid);
		strSign.append(act);
		strSign.append(uin);
		strSign.append(sessionID);
		strSign.append(appkey);
		String sign = md5(strSign.toString());
		StringBuilder getUrl = new StringBuilder();
		getUrl.append("Appid=");
		getUrl.append(appid);
		getUrl.append("&Act=");
		getUrl.append(act);
		getUrl.append("&Uin=");
		getUrl.append(uin);
		getUrl.append("&SessionId=");
		getUrl.append(sessionID);
		getUrl.append("&Sign=");
		getUrl.append(sign);
		return GetResult(HttpGetGo(getUrl.toString()));
	}

	/**
	 * 接收支付购买结果
	 * 
	 * @param appid
	 * @param act
	 * @param productName
	 * @param consumeStreamId
	 * @param cooOrderSerial
	 * @param uin
	 * @param goodsId
	 * @param goodsInfo
	 * @param goodsCount
	 * @param originalMoney
	 * @param orderMoney
	 * @param note
	 * @param payStatus
	 * @param createTime
	 * @param fromSign
	 * @return 支付结果
	 * @throws IOException 
	 */
//	private static String error[] = { "接收失败","接收成功", "AppId无效", "Act无效", "参数无效", "Sign无效", "网络问题", "无订单" };
	public void payResultNotify( String content, String appid, String act, String productName,
			String consumeStreamId, String cooOrderSerial, String uin,
			String goodsId, String goodsInfo, String goodsCount,
			String originalMoney, String orderMoney, String note,
			String payStatus, String createTime, String fromSign)
			throws IOException {

		StringBuilder strSign = new StringBuilder();
		strSign.append(appid);
		strSign.append(act);
		strSign.append(productName);
		strSign.append(consumeStreamId);
		strSign.append(cooOrderSerial);
		strSign.append(uin);
		strSign.append(goodsId);
		strSign.append(goodsInfo);
		strSign.append(goodsCount);
		strSign.append(originalMoney);
		strSign.append(orderMoney);
		strSign.append(note);
		strSign.append(payStatus);
		strSign.append(createTime);
		strSign.append(appkey);
		
//		System.out.println( "接受数据=" + strSign.toString() );
		String sign 		= md5(strSign.toString());

		RechargeTemplet r	= RechargeTempletCfg.get( goodsId );
		UserInfo user 		= UserManager.getInstance().getByName( getUserID( cooOrderSerial ) );
		short code 			= 1;
		
		do{
			if( user == null ) { Logs.error( "充值错误  user为空  cooOrderSerial=" + cooOrderSerial ); break; }
			
			if( !this.appid.equals(appid) ) { Logs.error( user, "请求充值错误  无效appid=" + appid ); break; }
			
			if ( !"1".equals(act) ) { Logs.error( user, "请求充值错误  无效Act=" + act ); break; }
			
			// 检查秘银是否正确
			if( !sign.toLowerCase().equals(fromSign.toLowerCase()) ) { Logs.error( user, "请求充值错误  无效 1sign=" + fromSign + ", 2sign=" + sign ); break; }
			
			// 然后验证数据库 是否有这个交易ID了
			if( RechargeDataProvider.getInstance().verify( user, consumeStreamId ) ){  Logs.error( user, "请求充值错误  数据库已经有这个交易ID=" + consumeStreamId ); break; }
			
			// 先从配置表验证 这个商品ID 是否正确
			if( r == null ) { Logs.error( user, "请求充值错误  没有ID=" + goodsId ); break; };
						
			// 如果是充值月卡  那就看是否还可以充值
//			if( r.isMonthCard() ) if( !user.isCanRecharge( r.getId()%100 ) ){ Logs.error( user, "请求充值错误  月卡天数已经上限 不能再冲" ); break ; }
			
			// 如果是限购 不能再买  这里如果加了 限制会导致前端购买了 扣了钱 但是不给水晶
			//if( r.isRestriction() ) if( !user.chekCanBuyRestriction( r.getId() ) ) { CLog.error( user, "请求充值错误  已经购买过限购 不在再买" ); break ; }
			
			float money 	= Float.parseFloat( orderMoney );
			int quantity	= Integer.parseInt( goodsCount );
			// 开始保存数据库
			if( !RechargeDataProvider.getInstance().create( user, consumeStreamId, goodsId, money, quantity, content ) ) break;
			
			// 开始发放水晶
			RechargeManager.getInstance().newHandler( user, consumeStreamId, quantity, r, "buy" );
			
			UserManager.getInstance().putUpdate( user );
			
			code = 0;
		} while ( false );
		
		if( user != null ) {
			RechargeEvent_91 rec_91 = (RechargeEvent_91) Event.RECHARGE_EVENT_91.getEventInstance();
			rec_91.run( user , code, cooOrderSerial );		
		}
	}

	/**
	 * 获取91服务器返回的结果
	 * 
	 * @param jsonStr
	 * @return
	 * @throws Exception
	 */
	private int GetResult(String jsonStr) throws Exception {
		// Pattern p = Pattern.compile("(?<=\"ErrorCode\":\")\\d{1,3}(?=\")");
		// Matcher m = p.matcher(jsonStr);
		// m.find();
		// return Integer.parseInt(m.group());

		// 这里需要引入JSON-LIB包内的JAR
		JSONObject jo = JSONObject.fromObject(jsonStr);
		return Integer.parseInt(jo.getString("ErrorCode"));
	}

	/**
	 * 对字符串进行MD5并返回结果
	 * 
	 * @param sourceStr
	 * @return
	 */
	private String md5(String sourceStr) {
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

	/**
	 * 发送GET请求并获取结果
	 * 
	 * @param getUrl
	 * @return
	 * @throws Exception
	 */
	private String HttpGetGo(String getUrl) throws Exception {
		StringBuffer readOneLineBuff = new StringBuffer();
		String content = "";
		URL url = new URL(goUrl + getUrl);
		URLConnection conn = url.openConnection();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				conn.getInputStream(), "utf-8"));
		String line = "";
		while ((line = reader.readLine()) != null) {
			readOneLineBuff.append(line);
		}
		content = readOneLineBuff.toString();
		reader.close();
		return content;
	}
	
	/**
	 * 转换订单号
	 * @param uid 
	 */
	public static String getConvertOrderSerial( int order, int uid ){
		return MessageFormat.format( "{0}-{1}-{2}", SystemCfg.GAME_DISTRICT+"", uid+"", order+"" );
	}
	public static int getOrderSerialToStr( String cooOrderSerial ){
		return Integer.parseInt( cooOrderSerial.split("-")[2] );
	}
	public static int getUserID( String cooOrderSerial ){
		return Integer.parseInt( cooOrderSerial.split("-")[0] );
	}
	
	public static void main( String[] args ) throws Exception{
		
		Sdk_91 sdk = new Sdk_91();
		
		String str = sdk.queryPayResult1( "11" );
		
		JSONObject jo = JSONObject.fromObject(str);
		System.out.println( "ErrorCode=" + jo.getString("ErrorCode") );
		System.out.println( "PayStatus=" + jo.getString("PayStatus") );
		
		JSONObject getUrl = new JSONObject();
		getUrl.accumulate( "ErrorCode", "1" );
		getUrl.accumulate( "ErrorDesc", "" );
		
		System.out.println( getUrl.toString() );
		
	}
}
