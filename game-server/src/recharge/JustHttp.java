package recharge;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;


/**
 *  收入接口调用
 * @author deng
 */
public class JustHttp {
	
	private static String APPID 			= "621C0D46AA1C9B01575D2B7617655D99";
	private static String SERVER_NAME 		= "api.talkinggame.com";
	private static String SERVER_PORT 		= "80";
	private static String QUEST_PATH 		= "/api/charge/";
	private static String DEFAULT_PROTOCOL 	= "http";
	private static String SLASH 			= "/";
	private static String COLON 			= ":";
	private static String QUOTES	 		= "\"";
	
	private HttpURLConnection _HttpURLConnection = null;
	private URL url = null;
	
	public JustHttp(){
		try {
			String serverURL = DEFAULT_PROTOCOL + COLON + SLASH + SLASH + SERVER_NAME + COLON + SERVER_PORT + QUEST_PATH + APPID;
//			System.out.println( serverURL );
			url 			= new URL( serverURL );
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	/**
	 * @param msgID 唯一ID用订单ID
	 * @param OS 支付平台
	 * @param accountID 玩家唯一ID
	 * @param orderID 订单ID 这里用收据 因为前端用的收据
	 * @param currencyAmount 充值现金
	 * @param currencyType 充值货币类型 CNY人民币 USD美元 EUR欧元 
	 * @param virtualCurrencyAmount 充值获得的虚拟币
	 * @param chargeTime 充值时间
	 * @param gameServer 服务器名字
	 * @param level 充值时候玩家的等级
	 * @return
	 */
	public String doPost( String msgID, String OS, String accountID, String orderID, String currencyAmount,
			String currencyType, String virtualCurrencyAmount, String chargeTime, String gameServer, String level ){
		
		StringBuilder jsonData = new StringBuilder();
		jsonData.append( "[{" );
		jsonData.append( QUOTES + "msgID" + QUOTES + COLON ).append( QUOTES + msgID + QUOTES ).append( "," );
		jsonData.append( QUOTES + "status" + QUOTES + COLON ).append( QUOTES + "success" + QUOTES ).append( "," );
		jsonData.append( QUOTES + "OS" + QUOTES + COLON ).append( QUOTES + OS + QUOTES ).append( "," );
		jsonData.append( QUOTES + "accountID" + QUOTES + COLON ).append( QUOTES + accountID + QUOTES ).append( "," );
		jsonData.append( QUOTES + "orderID" + QUOTES + COLON ).append( QUOTES + orderID + QUOTES ).append( "," );
		jsonData.append( QUOTES + "currencyAmount" + QUOTES + COLON ).append( currencyAmount ).append( "," );
		jsonData.append( QUOTES + "currencyType" + QUOTES + COLON ).append( QUOTES + currencyType + QUOTES ).append( "," );
		jsonData.append( QUOTES + "virtualCurrencyAmount" + QUOTES + COLON ).append( virtualCurrencyAmount ).append( "," );
		jsonData.append( QUOTES + "chargeTime" + QUOTES + COLON ).append( chargeTime ).append( "," );
//		jsonData.append( QUOTES + "iapID" + QUOTES + COLON ).append( QUOTES + iapID + QUOTES ).append( "," );
//		jsonData.append( QUOTES + "paymentType" + QUOTES + COLON ).append( QUOTES + paymentType + QUOTES ).append( "," );
		jsonData.append( QUOTES + "gameServer" + QUOTES + COLON ).append( QUOTES + gameServer + QUOTES ).append( "," );
//		jsonData.append( QUOTES + "gameVersion" + QUOTES + COLON ).append( QUOTES + gameVersion + QUOTES ).append( "," );
		jsonData.append( QUOTES + "level" + QUOTES + COLON ).append( level );
//		jsonData.append( QUOTES + "mission" + QUOTES + COLON ).append( QUOTES + mission + QUOTES );
		jsonData.append( "}]" );
//		System.out.println( jsonData.toString() );
		
		String ret = doPost( gzip( jsonData.toString() ) );
		
		return ret;
	}
	
	// 将字符串压缩为gzip流
	private byte[] gzip( String content ) {
		
		ByteArrayOutputStream baos = null;
		GZIPOutputStream out = null;
		byte[] ret = null;
	
		try {
			baos 	= new ByteArrayOutputStream();
			out 	= new GZIPOutputStream(baos);
			out.write( content.getBytes() );
			out.close();
			baos.close();
			ret 	= baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			
			if( baos != null ){
				try {
					baos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if( out != null ){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		return ret;
	}
	
	private String doPost( byte[] message ) {
		String result = "";
		
		try {
			_HttpURLConnection = (HttpURLConnection) url.openConnection();
			_HttpURLConnection.setRequestMethod( "POST" );
			_HttpURLConnection.setDoOutput( true );
			_HttpURLConnection.setRequestProperty( "Content-Type", "application/msgpack" );
			_HttpURLConnection.setRequestProperty( "Content-Length", String.valueOf( message.length ) );
			DataOutputStream ds = new DataOutputStream( _HttpURLConnection.getOutputStream() );
			ds.write( message );
			ds.flush();
			ds.close();
			
			result = gzipStream2String( _HttpURLConnection.getInputStream() );
			_HttpURLConnection.disconnect();
			
		} catch (Exception e) {
			_HttpURLConnection.disconnect();
			e.printStackTrace();
		}
		return result;
	}
	
	private String gzipStream2String( InputStream inputStream ) throws IOException {
		GZIPInputStream gzipInputStream = new GZIPInputStream( inputStream );
		byte[] buf = new byte[1024];
		int num = 1;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		while( (num = gzipInputStream.read(buf,0,buf.length)) != -1 ){
			baos.write( buf, 0, num );
		}
		return new String( baos.toByteArray(), "utf-8" );
	}
	
	
	public static void main( String[] args ){
		
		JustHttp just = new JustHttp();
		String ret = just.doPost( "12345", "ios", "123", "12345asdas", "901", "USD", 
				"1231", System.currentTimeMillis() +"", "校长的爱", "22" );
		
		System.out.println( "result data : " + ret );
	}
	
}
