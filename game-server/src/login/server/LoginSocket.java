package login.server;

import java.io.IOException;

import login.server.net.LoginHandler;

import org.xsocket.connection.INonBlockingConnection;
import org.xsocket.connection.NonBlockingConnection;

import define.SystemCfg;

/**
 * 连接登陆服 客户端
 * @author DXF
 *
 */
public class LoginSocket {
	
	// 采用非阻塞式的连接 
	private INonBlockingConnection nbc;
	
	
	public LoginSocket() throws IOException{
		
		nbc = new NonBlockingConnection( SystemCfg.LOGIN_ADDRESS, SystemCfg.LOGIN_PORT, new LoginHandler());
		
		//设置编码格式  
		nbc.setEncoding("UTF-8");  
        
        //设置是否自动清空缓存  
		nbc.setAutoflush(true);  
		
		nbc.setConnectionTimeoutMillis(NonBlockingConnection.MAX_TIMEOUT_MILLIS);
	}
	
	public void close() throws IOException {
		nbc.close();
		nbc = null;
	}  
	
	public INonBlockingConnection getCon(){
		return nbc;
	}
	
	public boolean isOpen(){
		return nbc.isOpen();
	}
	
    public static void main(String[] args) throws IOException {  
    	new LoginSocket();
    }


}
