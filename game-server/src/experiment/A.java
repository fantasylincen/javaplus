package experiment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;

import javax.net.ssl.HttpsURLConnection;

import net.sf.json.JSONObject;

public class A {
	
	String client_id 		= "";
	String client_secret 	= "";
	String refresh_token	= "";
	String access_token 	= "";
	String access_token_create_time = "";
	String access_token_expire_time = "";
	final String base_url 	= "https://accounts.google.com/o/oauth2/token";	
	final String url_tpl	= "https://www.googleapis.com/androidpublisher/v1.1/applications/{0}/inapp/{1}/purchases/{2}";
	
	public void getNewAccessToken( String refresh_token ) throws IOException{
		
		URL url 	= new URL( base_url );
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();  
		connection.setRequestMethod("POST");  
		connection.setDoOutput(true);  
		connection.setAllowUserInteraction(false);  
		
		JSONObject jsonObject 		= new JSONObject();
		jsonObject.put("grant_type", "refresh_token" );
		jsonObject.put("client_id", client_id );
		jsonObject.put("client_secret", client_secret );
		jsonObject.put("refresh_token", refresh_token );
		
		PrintStream ps 				= new PrintStream(connection.getOutputStream());  
		ps.print( jsonObject.toString() );
		ps.close();
		 //Call the service  
		BufferedReader br 	= new BufferedReader(new InputStreamReader(connection.getInputStream()));  
		//Extract response  
		String str;  
		StringBuffer sb 	= new StringBuffer();  
		while ((str = br.readLine()) != null) 
			sb.append(str);  
		br.close();  
		String response 	= sb.toString();  
		JSONObject result 	= JSONObject.fromObject( response );
		
		access_token 		= result.getString("access_token");
		System.out.println( "access_token=" + access_token );
	}
	
	public int checkPurchase( String bill_id, String package_name, String product_id, String purchase_token ) throws IOException{
		
		URL url 	= new URL( MessageFormat.format( url_tpl, package_name, product_id, purchase_token ) );  
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();  
		connection.setRequestMethod("POST");  
		connection.setDoOutput(true);  
		connection.setAllowUserInteraction(false);  
		
		JSONObject jsonObject 		= new JSONObject();
		jsonObject.put("access_token", access_token );
		
		PrintStream ps 				= new PrintStream(connection.getOutputStream());  
		ps.print( jsonObject.toString() );
		ps.close();
		 //Call the service  
		BufferedReader br 	= new BufferedReader(new InputStreamReader(connection.getInputStream()));  
		//Extract response  
		String str;  
		StringBuffer sb 	= new StringBuffer();  
		while ((str = br.readLine()) != null) 
			sb.append(str);  
		br.close();  
		String response 	= sb.toString();  
		JSONObject result 	= JSONObject.fromObject( response );
		
		
		int status 			= result.getInt( "purchaseState" );
		String b			= result.getString( "developerPayload" );
		
		System.out.println( "status=" + status );
		System.out.println( "developerPayload=" + b );
		
		return status;
	}
	
	public static void main( String[] args ) throws IOException, NoSuchAlgorithmException{
		
	}
	
	
}
