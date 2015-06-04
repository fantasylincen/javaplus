package experiment;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;


public class JostDemo {
	
	private static String appId = "621C0D46AA1C9B01575D2B7617655D99";
	
	public static void main( String[] args ){
		String jsonData = "[{\"msgID\":\"1001\",\"accountID\":\"whl\"}]";
		
		System.out.println( "post data : " + jsonData );
		byte[] dataByte = gzip( jsonData );
		
		HttpClient client = new HttpClient( "api.talkinggame.com", "80", "/api/charge/" + appId );
		System.out.println( "result data : " + client.doPost( dataByte) );
	}

	// 将字符串压缩为gzip流
	private static byte[] gzip( String content ) {
		
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

	private static class HttpClient{
		
		HttpURLConnection _HttpURLConnection = null;
		
		URL url = null;
		
		private String DEFAULT_PROTOCOL = "http";
		
		private String SLASH = "/";
		
		private String COLON = ":";
		
//		public String DEFAULT_NET_ERROR = "NetError";
		
//		public String POST 	= "POST";
		
		public HttpClient( String serverName, String port, String questPath ) {
			try {
				
				String serverURL = DEFAULT_PROTOCOL + COLON + SLASH + SLASH + serverName;
				
				if( port != null && port.trim().length() > 0 ){
					serverURL 	+= COLON;
					serverURL	+= port.trim();
				}
				
				if( questPath.charAt(0) != '/' )
					serverURL	+= SLASH;
				
				serverURL		+= questPath;
				
				System.out.println( serverURL );
				url 			= new URL( serverURL );
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public String doPost( byte[] message ) {
			
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
		
	}
	
}

