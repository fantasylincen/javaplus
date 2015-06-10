package recharge.lb;

import java.io.IOException;
import java.net.URLEncoder;

import game.events.Event;
import game.events.all.recharge.RechargeEvent_lb;
import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;
import game.log.Logs;
import recharge.RechargeDataProvider;
import user.UserInfo;
import user.UserManager;

public class LbVerifyPurchase {

	// 应用在易接服务获取的同步密钥
	private static final String PRIVATE_KEY = "0ECOQP4Z82X0MLBC8O1OXUQ0H8927OVQ";
	private static final String APP_ID		= "{9FCCA9CC-010A0752}";
	private static final String APP_ID1		= "9FCCA9CC010A0752";
	private static final String CHECK_LOGIN_URL = "http://sync.1sdk.cn/login/check.html";
	
	public static boolean payResultNotify( String content, String app, String cbi, String ct,
			String fee, String pt, String sdk, String ssid, String st,
			String tcd, String uid, String ver, String sign) throws IOException {
		
//		System.out.println( "进入充值 = " + content );
		
		StringBuilder strSign = new StringBuilder();
		strSign.append( "app=" ).append( app ).append( "&" );
		strSign.append( "cbi=" ).append( cbi ).append( "&" ); // 服务器ID-玩家UID-充值ID
		strSign.append( "ct=" ).append( ct ).append( "&" );
		strSign.append( "fee=" ).append( fee ).append( "&" ); // 充值额度
		strSign.append( "pt=" ).append( pt ).append( "&" );
		strSign.append( "sdk=" ).append( sdk ).append( "&" );
		strSign.append( "ssid=" ).append( ssid ).append( "&" );
		strSign.append( "st=" ).append( st ).append( "&" );
		strSign.append( "tcd=" ).append( tcd ).append( "&" ); // 订单号
		strSign.append( "uid=" ).append( uid ).append( "&" );
		strSign.append( "ver=" ).append( ver );
		int rmb			= Integer.parseInt( fee ) / 100;
		short code 		= -1;
		UserInfo user 	= null;
		do{
			if( !MD5.encode(strSign.toString() + PRIVATE_KEY).equalsIgnoreCase(sign) ){ 
				Logs.error( user, "充值验证错误  MD5.encode(strSign + PRIVATE_KEY)=" + MD5.encode(strSign + PRIVATE_KEY) + ",sign=" + sign ); 
				break; 
			}
			if( !st.equals("1") || !app.equalsIgnoreCase( APP_ID1 ) ) { Logs.error( user, "充值不成功 st=" + st + ",app=" + app ); break; }
			
			///----------- 下面开始各种验证
			int UID 	= Integer.parseInt( cbi.split("-")[1] );// 玩家唯一ID
//			short rid 	= Short.parseShort( cbi.split("-")[2] );// 充值ID
			user 		= UserManager.getInstance().getByName( UID );
			if( user == null ) break;
//			RechargeTemplet r = RechargeTempletCfg.get( rid );
//			if( r == null ){ Logs.error( user, "请求充值错误  没有充值ID=" + rid ); break; }
			
			// 如果是充值月卡  那就看是否还可以充值
//			if( r.isMonthCard() ) if( !user.isCanRecharge( r.getId()%100 ) ){ Logs.error( user, "请求充值错误  月卡天数已经上限 不能再冲" ); break; }
			
			// 如果是限购 不能再买  这里如果加了 限制会导致前端购买了 扣了钱 但是不给水晶
			//if( r.isRestriction() ) if( !user.chekCanBuyRestriction( r.getId() ) ) { CLog.error( user, "请求充值错误  已经购买过限购 不在再买" ); break; }
		
			// 然后验证数据库 是否有这个交易ID了
			if( RechargeDataProvider.getInstance().verify( user, tcd ) ){ Logs.error( user, "请求充值错误  数据库已经有这个交易ID=" + tcd );  break; }
				
			// 开始保存数据库
			if( !RechargeDataProvider.getInstance().updata( user, tcd, rmb, content ) ) break;
			
			user.RMB += rmb;
			
			UserManager.getInstance().putUpdate( user );
			
			UpdateManager.instance.update( user, UpdateType.U_36 );
			
			code = 0;
	
			RechargeEvent_lb lb = (RechargeEvent_lb) Event.RECHARGE_EVENT_LB.getEventInstance();
			lb.run( user, code, tcd, rmb );
		}while( false );
		
		if( user != null && code != 0 ) {
			RechargeEvent_lb lb = (RechargeEvent_lb) Event.RECHARGE_EVENT_LB.getEventInstance();
			lb.run( user, code, tcd, rmb );
		}	
		
		return code == 0;
	}

	public static boolean loginVerify(String sdk, String app, String uin, String sess) {
		
		if( !app.equals( APP_ID ) )
			return false;
		
		try {
			StringBuilder getUrl = new StringBuilder();
			getUrl.append(CHECK_LOGIN_URL);
			getUrl.append("?app=");
			getUrl.append(app);
			getUrl.append("&sdk=");
			getUrl.append(sdk);
			getUrl.append("&uin=");
			getUrl.append(URLEncoder.encode(uin, "UTF-8"));
			getUrl.append("&sess=");
			getUrl.append(URLEncoder.encode(sess, "UTF-8"));
			
//			System.out.println( getUrl.toString() );
			
			HTTPHelper.SimpleHTTPResult ret = HTTPHelper.simpleInvoke("GET", getUrl.toString(), null, null);
			
			// 下面的返回值由CP服务器和客户端定义，这里的返回值只做参考用"success"
			if (ret.code != 200)
				return false;
			if (ret.data == null || ret.data.length == 0)
				return false ;
			
			String r = new String (ret.data);
			
//			System.out.println( "code=" + ret.code + ", date=" + r );
			
			return r.equals( "0" );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static void main( String[] args ){
		
//		LbVerifyPurchase.loginVerify( "{D8347740-4A3D8773}a", APP_ID + "a", "16323304a", "a" );
		
		String c		= "app=9fcca9cc010a0752&cbi=1-117315210-101&ct=1418892684161&fee=100&pt=1418892625852&sdk=d83477404a3d8773&ssid=1248247&st=1&tcd=668c3d9d3b1f4e498428d5757271ed33&uid=16323303&ver=1&sign=399bcca1ddfefce43232d5b6e35a939b";
		String list[] 	= c.split( "&" );
		String app		= list[0].split("=")[1];
		String cbi		= list[1].split("=")[1];
		String ct		= list[2].split("=")[1];
		String fee		= list[3].split("=")[1];
		String pt		= list[4].split("=")[1];
		String sdk		= list[5].split("=")[1];
		String ssid		= list[6].split("=")[1];
		String st		= list[7].split("=")[1];
		String tcd		= list[8].split("=")[1];
		String uid		= list[9].split("=")[1];
		String ver		= list[10].split("=")[1];
		String sign		= list[11].split("=")[1];
		
		StringBuilder strSign = new StringBuilder();
		strSign.append( "app=" ).append( app ).append( "&" );
		strSign.append( "cbi=" ).append( cbi ).append( "&" ); // 服务器ID-玩家UID-充值ID
		strSign.append( "ct=" ).append( ct ).append( "&" );
		strSign.append( "fee=" ).append( fee ).append( "&" ); // 充值额度
		strSign.append( "pt=" ).append( pt ).append( "&" );
		strSign.append( "sdk=" ).append( sdk ).append( "&" );
		strSign.append( "ssid=" ).append( ssid ).append( "&" );
		strSign.append( "st=" ).append( st ).append( "&" );
		strSign.append( "tcd=" ).append( tcd ).append( "&" ); // 订单号
		strSign.append( "uid=" ).append( uid ).append( "&" );
		strSign.append( "ver=" ).append( ver );
		
		// a3472fbe866e9e82c1753cdfc6f4db3f
		String key = MD5.encode( strSign.toString() + PRIVATE_KEY );
		System.out.println( key );
		System.out.println( sign );
	}


}
