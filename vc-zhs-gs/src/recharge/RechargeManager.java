package recharge;

import game.award.AwardInfo;
import game.award.AwardType;
import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;
import game.log.Logs;
import game.log.L;
import game.mail.MailBase;
import game.mail.MailType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


import net.sf.json.JSONObject;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Base64;

import config.aa.ActivityAwardTempletCfg;
import config.recharge.RechargeTemplet;
import config.recharge.RechargeTempletCfg;

import telnet.events.SetActivityEevet;
import user.UserInfo;
import user.UserManager;

/**
 * 充值 管理中心
 * @author DXF
 */
public class RechargeManager {
	
	private static RechargeManager instance = new RechargeManager();
	public static  RechargeManager getInstance(){ return instance; }
	private RechargeManager(){ }
	
	private final RechargeDataProvider db	= RechargeDataProvider.getInstance();
	
	/**
	 * 新的充值 接口
	 * @param user
	 * @param orderId
	 * @param quantity
	 * @param r
	 * @param content
	 */
	public void newHandler( UserInfo user, String orderId, int quantity, RechargeTemplet r, String content ) {
		
		// 是否新增用户
		int isNewly 		= user.getRechargeGold() > 0 ? 0 : 1;
		int gold			= r.getGold()*quantity;
		float money			= r.getMoney()*(float)quantity;
		recharge( user, gold );
		user.setRechargeMoney( user.getRechargeMoney() + money );
		user.setRechargeMoney1( user.getRechargeMoney1() + money );
		user.setRechargeMoney2( user.getRechargeMoney2() + money );
		
		// 记录日志 如果是沙箱就不要记录
		if( content.equals( "buy" ) )
			Logs.log( L.L_013,  
					user.getUID() + "," +
					user.getNickName() + "," + gold + "," + isNewly + "," + r.getId() + "," + money + "," + orderId );
			
		// 如果是首冲
		if( isNewly == 1 )
			ActivityAwardTempletCfg.shouChong( money, user );
		
		if( SetActivityEevet.isOpen2 )
			ActivityAwardTempletCfg.xinKai( user );
		if( SetActivityEevet.isOpen5 )
			ActivityAwardTempletCfg.xinKai1( user );
		if( SetActivityEevet.isOpen6 && user.strongestAres == 0 ){
			user.strongestAres = money;
			// 发放悟空	
			AwardInfo award	= new AwardInfo( AwardType.HERO, 10113, 1, 1, 2, 0 );
			MailBase mail 	= new MailBase( award, MailType.SYSTEM_PRESENT );
			user.getMailManager().addMail( mail );
		}
		
		// 玩家每日单笔充值$99.9即可获得  圣诞老人*1
		if( SetActivityEevet.isOpen8 && !user.christmasRechargeToDay && money >= 99 ){
			user.christmasRechargeToDay = true;
			AwardInfo award	= new AwardInfo( AwardType.HERO, 10115, 1, 1, 2, 0 );
			MailBase mail 	= new MailBase( award, MailType.SYSTEM_PRESENT );
			user.getMailManager().addMail( mail );
		}
//		活动内容：每日当天，玩家累计充值以下金额即可获得
//		1：当日累积充值$20  即可获得  XX（贵方确定角色） 绿色*1
//		2：当日累积充值$80  即可获得  XX（贵方确定角色） 绿色*4
//		3：当日累积充值$200 即可获得  XX（贵方确定角色） 紫色*1
		if( SetActivityEevet.isOpen9 )
			ActivityAwardTempletCfg.xinKai2( user );
		
		// 这里看下是否月卡 默认1为月卡
		if( r.isMonthCard() ){
			user.addMonthCardFate( r.getId() % 100 );
			if( r.getId() == 101 )
				UpdateManager.instance.update( user, UpdateType.U_28 );
			if( r.getId() == 102 )
				UpdateManager.instance.update( user, UpdateType.U_31 );
			if( r.getId() == 103 )
				UpdateManager.instance.update( user, UpdateType.U_32 );
			
			// 记录日志
			if( content.equals( "buy" ) )
				Logs.log( L.L_014,  
						user.getUID() + "," +
						user.getNickName() + "," + gold + "," + isNewly + "," + r.getId() + "," + money + "," + orderId );
		}
		
		// 这里看下是否 限购
//		if( r.isRestriction() ){
//			short id =	(short) ((r.getId() % 10) + 200);
//			user.setRestriction( id );
//			UpdateManager.instance.update( user, UpdateType.U_33, id );
//		}
	}
	
	/**
	 * 处理一笔交易
	 * @param orderId
	 */
	public RechargeTemplet handler( int orderId ) {
		
		int data[]			= db.get( orderId );
		RechargeTemplet r 	= RechargeTempletCfg.get( (short) data[1] );
		if( r == null ) return null;
		UserInfo user		= UserManager.getInstance().getByName( data[0] );
		if( user == null ) return null;
		
		// 是否新增用户
		int isNewly 		= user.getRechargeGold() > 0 ? 0 : 1;
		
		recharge( user, r.getGold() );
		float money = user.getRechargeMoney() + r.getMoney();
		user.setRechargeMoney( money );
		
		// 记录日志
		Logs.log( L.L_013,  
				user.getUID() + "," +
				user.getNickName() + "," + r.getGold() + "," + isNewly + "," + r.getId() + "," + r.getMoney() + "," + orderId );
			
		// 如果是首冲
		if( isNewly == 1 ){
			ActivityAwardTempletCfg.shouChong( r.getMoney(), user );
		}
		
		if( SetActivityEevet.isOpen2 ){
			ActivityAwardTempletCfg.xinKai( user );
		}
		
		if( SetActivityEevet.isOpen5 ){
			ActivityAwardTempletCfg.xinKai1( user );
		}
		
		
		// 这里看下是否月卡 默认1为月卡
		if( r.isMonthCard() ){
			user.addMonthCardFate( r.getId() % 100 );
			if( r.getId() == 101 )
				UpdateManager.instance.update( user, UpdateType.U_28 );
			if( r.getId() == 102 )
				UpdateManager.instance.update( user, UpdateType.U_31 );
			if( r.getId() == 103 )
				UpdateManager.instance.update( user, UpdateType.U_32 );
			
			// 记录日志
			Logs.log( L.L_014,  
					user.getUID() + "," +
					user.getNickName() + "," + r.getGold() + "," + isNewly + "," + r.getId() + "," + r.getMoney() + "," + orderId );
		}
		
		// 这里看下是否 限购
		if( r.isRestriction() ){
			short id =	(short) ((r.getId() % 10) + 200);
			user.setRestriction( id );
			UpdateManager.instance.update( user, UpdateType.U_33, id );
		}
		
		return r;
	}
	
	/**
	 * google 充值
	 * @param orderId 
	 * @param r
	 */
	public void handler( UserInfo user, String orderId, RechargeTemplet r ) {
		
		// 是否新增用户
		int isNewly 		= user.getRechargeGold() > 0 ? 0 : 1;
		
		recharge( user, r.getGold() );
		float money = user.getRechargeMoney() + r.getMoney();
		user.setRechargeMoney( money );
		
		// 记录日志
		Logs.log( L.L_013,  
				user.getUID() + "," +
				user.getNickName() + "," + r.getGold() + "," + isNewly + "," + r.getId() + "," + r.getMoney() + "," + orderId );
			
		// 如果是首冲
		if( isNewly == 1 ){
			ActivityAwardTempletCfg.shouChong( r.getMoney(), user );
		}
		
		if( SetActivityEevet.isOpen2 ){
			ActivityAwardTempletCfg.xinKai( user );
		}
		if( SetActivityEevet.isOpen5 ){
			ActivityAwardTempletCfg.xinKai1( user );
		}
		
		
		// 这里看下是否月卡 默认1为月卡
		if( r.isMonthCard() ){
			user.addMonthCardFate( r.getId() % 100 );
			if( r.getId() == 101 )
				UpdateManager.instance.update( user, UpdateType.U_28 );
			if( r.getId() == 102 )
				UpdateManager.instance.update( user, UpdateType.U_31 );
			if( r.getId() == 103 )
				UpdateManager.instance.update( user, UpdateType.U_32 );
			
			// 记录日志
			Logs.log( L.L_014,  
					user.getUID() + "," +
					user.getNickName() + "," + r.getGold() + "," + isNewly + "," + r.getId() + "," + r.getMoney() + "," + orderId );
		}
		
		// 这里看下是否 限购
		if( r.isRestriction() ){
			short id =	(short) ((r.getId() % 10) + 200);
			user.setRestriction( id );
			UpdateManager.instance.update( user, UpdateType.U_33, id );
		}
		
	}
	

	/** 充值 */
	public void recharge( UserInfo user, int value ){
		if( user == null ) return;
		
		user.recharge( value );
	}
	
	/**
	 * 漏单处理
	 * @param user
	 */
	public void LeakageSingleProcessing( UserInfo user ) {
		
		int[] data = db.lospHandle( user );
		
		// 充值水晶
		if( data[0] > 0 )
			recharge( user, data[0] );
		
		// 加上月卡
		int i = 0;
		while( i++ < data[1] )
			user.addMonthCardFate( 0 );
		i = 0;
		while( i++ < data[2] )
			user.addMonthCardFate( 1 );
		i = 0;
		while( i++ < data[3] )
			user.addMonthCardFate( 2 );
		if( data[1] > 0 )
			UpdateManager.instance.update( user, UpdateType.U_28 );
		if( data[2] > 0 )
			UpdateManager.instance.update( user, UpdateType.U_31 );
		if( data[3] > 0 )
			UpdateManager.instance.update( user, UpdateType.U_32 );
	}
	
	public String verifyReceipt( String receipt ) throws IOException {  
  
		return verify( receipt.getBytes(), "https://buy.itunes.apple.com/verifyReceipt" );
	}
	
	private String verify( byte[] receipt, String site ) throws IOException {
		
		URL url 	=  new URL( site );  	// 正式版本

        // 使连接,使用发布模式
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();  
		connection.setRequestMethod("POST");  
		connection.setDoOutput(true);  
		connection.setAllowUserInteraction(false);  
  
		// 使用64位 转一下
		Base64 encoder 				= new Base64();
		String encodedReceipt		= new String( encoder.encode( receipt ) );
		
		// 创建一个查询JSON对象
		JSONObject jsonObject 		= new JSONObject();
		jsonObject.put("receipt-data", encodedReceipt);

		// 查询JSON对象写入输出流的连接
		PrintStream ps 				= new PrintStream(connection.getOutputStream());  
		ps.print( jsonObject.toString() );
		ps.close();

		// 调用服务
		BufferedReader br 	= new BufferedReader(new InputStreamReader(connection.getInputStream()));  
		String str;  
		StringBuffer sb 	= new StringBuffer();  
		while ((str = br.readLine()) != null)
			sb.append(str);
		br.close();
		
		String response 	= sb.toString();  
		JSONObject result 	= JSONObject.fromObject( response );
		int status 			= result.getInt("status");
		
//		System.out.println( status );
//		System.out.println( response );
		
		if (status == 0) {
			int l = site.split("\\.")[0].length();
			return result.getString( "receipt" ) + "&" + site.substring( 8, l );
		} else if( status == 21007 ) {// 这里说明 这个是测试用的 
			return verify( receipt, "https://sandbox.itunes.apple.com/verifyReceipt" );
		}
		
		return null;
	}
	
	
	
	public int verifyReceipt( byte[] receipt, boolean b ) throws IOException, EncoderException {  
		int status 	= -1;  
  
        //This is the URL of the REST webservice in iTunes App Store  
		URL url 	= null;
//		if( isDebug )
//			url		= new URL("https://sandbox.itunes.apple.com/verifyReceipt");  	// 测试版本
//		else
			url 	= new URL("https://buy.itunes.apple.com/verifyReceipt");  	// 正式版本

        //make connection, use post mode  
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();  
		connection.setRequestMethod("POST");  
		connection.setDoOutput(true);  
		connection.setAllowUserInteraction(false);  
  
		//Encode the binary receipt data into Base 64  
		//Here I'm using org.apache.commons.codec.binary.Base64 as an encoder, since commons-codec is already in Grails classpath  
		Base64 encoder 				= new Base64();  
		String encodedReceipt		= new String( encoder.encode(receipt) );
		 
		//Create a JSON query object  
		//Here I'm using Grails' org.codehaus.groovy.grails.web.json.JSONObject  
		JSONObject jsonObject 		= new JSONObject();
		jsonObject.put("receipt-data", encodedReceipt);

		//Write the JSON query object to the connection output stream  
		PrintStream ps 				= new PrintStream(connection.getOutputStream());  
		ps.print( jsonObject.toString() );
		ps.close();

		 //Call the service  
		BufferedReader br 	= new BufferedReader(new InputStreamReader(connection.getInputStream()));  
		//Extract response  
		String str;  
		StringBuffer sb 	= new StringBuffer();  
		while ((str = br.readLine()) != null) {  
			sb.append(str);  
//	            sb.append("/n");
		}  
		br.close();  
		String response 	= sb.toString();  
		JSONObject result 	= JSONObject.fromObject( response );
		//Deserialize response  
		status 				= result.getInt("status");
		if (status == 0) {  
			//provide content  
		} else {
			//signal error, throw an exception, do your stuff honey!  
		}
//		System.out.println( status );
//		System.out.println( response );

		return status ;  
	}
	
	public static void main(String[] args) throws IOException, EncoderException 
	{
		byte[] receipt = new byte[2];
		receipt[0]		= 1;
		receipt[1]		= 2;
//		verifyReceipt( receipt );
	}

	

	
	

}
