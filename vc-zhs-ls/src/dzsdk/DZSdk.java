package dzsdk;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import user.UserManager;
import util.md5.MD5;

import com.alibaba.fastjson.JSONObject;

public class DZSdk {
	
	private final static String key 	= "ZHS-3A324FCCE568BC27E7C0D6E885CD0573";
	
	private final static String goUrl 	= "http://zhs.cw678.com/Services/GetAccountInfo?";
	
	public static int checkUserLogin( String accName ) throws Exception {
		
		int time = (int)(System.currentTimeMillis()/1000); 
		StringBuilder strSign = new StringBuilder();
		strSign.append( accName );
		strSign.append( time );
		strSign.append( key );
		String sign = MD5.md5( strSign.toString() ).toLowerCase();
		
		//AccName=1111&Time=1432121212&Flag=834598349543895
		StringBuilder getUrl = new StringBuilder();
		getUrl.append("AccName=");
		getUrl.append( accName );
		getUrl.append("&Time=");
		getUrl.append( time );
		getUrl.append("&Flag=");
		getUrl.append( sign );
		
		StringBuffer ret 	= new StringBuffer();
		URL url 			= new URL(goUrl + getUrl);
		URLConnection conn 	= url.openConnection();
		BufferedReader reader = new BufferedReader( new InputStreamReader( conn.getInputStream(), "utf-8" ) );
		String line 		= "";
		while ((line = reader.readLine()) != null)
			ret.append(line);
		
		// 将返回信息取出
		JSONObject res 		= JSONObject.parseObject( ret.toString() );
		if( res.getInteger( "Ret" ) != 1 )
			throw new Exception( String.valueOf( res.getInteger( "Ret" ) ) );
		
		//"AccId":1,"AccName":"wolaiwan","AccPass":"caef8ed509b93561ceca49e6fbf019d3","CreateTime":"2015-02-02T15:58:47","LastLoginTime":"2015-02-02T15:58:47","LastUpdateTime":"2015-02-02T15:58:47","LateGold":0,"ProGold":0,"RegFrom":"","RegIp":"119.128.19.160","Score":0,"SpreadId":"f8ed299a62c"
//		JSONObject x		= JSONObject.parseObject( res.getString( "Message" ) );
//		System.out.println( x.toString() );
		
		return UserManager.getInstance().getIndex_dz( "dz_" + accName );
	}

	public static void main(String[] args) throws Exception {
//		checkUserLogin( "wolaiwan" );
		
	}
	
}
