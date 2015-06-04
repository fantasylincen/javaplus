package server;

import org.xsocket.connection.INonBlockingConnection;

/**
 * 单个服务器 信息
 * @author DXF
 *
 */
public class ServerInfo {

	// 服务器ID
	private byte 			serverID;
	
	// 用户连接服务器端口
	private int				port		= 8000;
	
	
	
	// 在线人数 用来判断当前服务器压力
	private int				onlineNumber ;
	
	// 是否开启
	private boolean 		isOpen		= false;
	
	INonBlockingConnection 	con;

	private String ip;
	private String serverName;
	
	public ServerInfo( INonBlockingConnection con, byte serverid ){
		
		this.serverID 	= serverid;
		
		setCon( con );
	}
	
	public INonBlockingConnection getCon(){
		return this.con;
	}
	public int getPort(){
		return this.port;
	}
	public byte getServerID(){
		return this.serverID;
	}
	public ServerStatus getStatus(){
		
		if( onlineNumber <= 50 )
			return ServerStatus.SS_0;
		
		if( onlineNumber <= 200 )
			return ServerStatus.SS_1;
		
		if( onlineNumber <= 500 )
			return ServerStatus.SS_2;
		
		return ServerStatus.SS_3;
	}
	public boolean isOpen(){
		return this.isOpen;
	}
	public void Open(){
		isOpen = true;
	}
	
	public void closeCon(){
		this.con 	= null;
		isOpen		= false;
	}

	public void setCon( INonBlockingConnection con ) {
		this.con 	= con;
		isOpen		= true;
		this.con.setAttachment( serverID );
	}

	public void setOnlineNumber( int onlinenum ) {
		onlineNumber = onlinenum;
	}
	
	public int getOnlineNumber(){
		return onlineNumber;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getServerName(){
		return serverName;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getIp(){
		return ip;
	}
	public void setPort(int port ) {
		this.port = port;
	}
}
