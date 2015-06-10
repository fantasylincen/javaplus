package ppsdk;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import user.UserManager;
import util.md5.MD5;

import com.alibaba.fastjson.JSONObject;

public class PPSdk {

	private final static String url = "http://passport_i.25pp.com:8080/account?tunnel-command=2852126760";
	private final static int AppID 	= 5399;
	private final static String key = "550b6c8126a599efa4db46f865db4981";
	
	public static int checkUserLogin( String sid ) throws Exception {
		
		// 先组织json数据
		JSONObject date 	= new JSONObject();
		date.put( "sid", sid );
		JSONObject game 	= new JSONObject();
		game.put( "gameId", AppID );
		JSONObject content	= new JSONObject();
		content.put( "id", System.currentTimeMillis()/1000 );
		content.put( "service", "account.verifySession" );
		content.put( "data", date );
		content.put( "game", game );
		content.put( "encrypt", "md5" );
		content.put( "sign", MD5.md5( "sid=" + sid + key ) );
		
		// 连接url
		URL console 		= new URL( url );
		URLConnection conn 	= console.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.connect();
        
        // 发送 post 数据
        DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		out.write( content.toJSONString().getBytes("utf-8") );
        out.flush();
        out.close();
        
        // 获取返回信息
        StringBuffer result	= new StringBuffer();
        BufferedReader in 	= new BufferedReader(new InputStreamReader( conn.getInputStream() ) );
        String line;
		while ((line = in.readLine()) != null)
			result.append( line );
		in.close();
		
		// 将返回信息取出
		JSONObject res 		= JSONObject.parseObject( result.toString() );
		JSONObject x		= res.getJSONObject( "state" );
		if( x.getInteger( "code" ) != 1 )
			throw new Exception( String.valueOf( x.getInteger( "code" ) ) );
		
		x					= res.getJSONObject( "data" );
		
		String accountId	= x.getString( "accountId" );
		String creator		= x.getString( "creator" );
//		System.out.println( "accountId=" + accountId + ",creator=" + creator + ", nickName=" + x.getString( "nickName" ) );
		
		return UserManager.getInstance().getIndex(  creator + ":" + accountId );
	}

	public static void main(String[] args) throws Exception {
//		checkUserLogin( "sasdasdasdas" );
		
		System.out.println( MD5.md5( "LINGCHENGDENGXINFENG" ) );
	}
}
