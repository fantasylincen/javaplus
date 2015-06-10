package server;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.xsocket.connection.INonBlockingConnection;

import user.UserManager;
import util.UtilBase;


/**
 * 服务器管理
 * @author DXF
 *
 */
public class ServerManager {
	
	private static final ServerManager instance = new ServerManager();
	public static final ServerManager getInstance(){
		return instance;
	}
	private ServerManager() {	}
	
	// 服务器列表
	private ConcurrentHashMap<Byte,ServerInfo> 	servers	= new ConcurrentHashMap<Byte, ServerInfo>();
	
	/**
	 * 获取 服务器列表
	 * @return
	 */
	public Map<Byte,ServerInfo> getSeverList(){
		return this.servers;
	}
	
	public ServerInfo get( byte id ) {
		return servers.get(id);
	}
	
	/**
	 * 设置在线人数
	 * @param id
	 * @param onlinenum
	 */
	public byte set( byte id, int onlinenum ) {
		
		ServerInfo s = get(id);
		if( s != null ) {
			s.setOnlineNumber( onlinenum );
			if( !s.isOpen() ) s.Open();
			return 1;
		}
		
		return -1;
	}
	
	/**
	 * 有一个服务器登陆 
	 * @param buf 
	 * @param address （IP）
	 * 这里我们将端口 自动生成 
	 */
	public ServerInfo connect( INonBlockingConnection con, ByteBuffer buf ){
		
		byte id				= buf.get();
		String serverName	= UtilBase.decodeString(buf);
		String ip			= UtilBase.decodeString(buf);
		int port			= buf.getInt();
		
		ServerInfo server 	= servers.get( id );
		
		if( server == null ){
			server = new ServerInfo( con, id );
			server.setServerName( serverName );
			server.setIp( ip );
			server.setPort( port );
			servers.putIfAbsent( id, server );
		}else{
			server.closeCon();
			// 只设置 con 就行了 因为端口是一定有的
			server.setCon( con );
			server.setServerName( serverName );
			server.setIp( ip );
			server.setPort( port );
		}
		
//		System.out.println( id + "连接成功!" );
		return server;
	}
	
	/**
	 * 退出
	 * @param con
	 * @param ip 
	 */
	public void Exit( INonBlockingConnection con ) {
		
		byte id 			= (byte) (con.getAttachment() == null ? 0 : (byte)con.getAttachment());
		
		servers.remove( id );
//		ServerInfo server 	= servers.remove( id );
//		if( server != null ){
//			server.closeCon();
//			System.out.println( id + "退出!" );
//		}
	}
	
	/**
	 * 获得开启服务器列表
	 * @return
	 */
	public List<ServerInfo> getStartServer() {
		
		List<ServerInfo> list = new ArrayList<ServerInfo>();
		
		for( ServerInfo s : servers.values() ){
			if( s.isOpen() )
				list.add(s);
		}
		
		return list;
	}
	
	public List<ServerInfo> getStartServer( int uid ) {
		List<ServerInfo> ret = new ArrayList<ServerInfo>();
		
		for( ServerInfo s : servers.values() ){
			if( !s.isOpen() ) continue;
			if( s.getServerID() == 0 && !UserManager.getInstance().isPsytopic( uid ) )
				continue;
			ret.add( s );
		}
		
		return ret;
	}
	
	
	public byte checkCode( short serverid, int index, String key ) {
		
//		KEY k = getKey( index );
//		
//		if( k == null ) return -1;
//		
//		if( !k.key.equals(key) ) return 1;
		
		// 保存登陆区ID 
		UserManager.getInstance().update( index, serverid );
		
		return 0;
	}
	
	
	public void addKey( int index, String key, String identifying ) {
		
//		KEY k		= keys.get( index );
//		
//		if( k == null ){
//			k 		= new KEY( index, identifying, key );
//			k.time	= SystemTimer.currentTimeSecond();
//			keys.putIfAbsent( index, k );
//		}else{
//			k.key			= key;
//			k.identifying	= identifying;
//			k.time			= SystemTimer.currentTimeSecond();
//		}
	}
	
}

class KEY{
	
	int 		index;
	
	String 		identifying;
	
	String 		key;
	
	int 		time;

	KEY( int index, String identifying, String key ){
		this.index 			= index;
		this.identifying 	= identifying;
		this.key 			= key;
	}
}

