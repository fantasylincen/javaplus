package user;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentHashMap;

import org.xsocket.connection.INonBlockingConnection;

import events.Event;
import util.ErrorCode;

/**
 * 用户管理
 * @author DXF
 *
 */
public class UserManager {

	private static final UserManager instance = new UserManager();
	public static final UserManager getInstance(){
		return instance;
	}
	private UserManager() {	}
	
	private final UserInfoDataProvider 			db 				= UserInfoDataProvider.getInstance();	
	private ConcurrentHashMap<Integer,UserInfo> 	onlineUsers 	= new ConcurrentHashMap<Integer, UserInfo>();
	
	/**
	 * 用户登陆
	 * @param con
	 * @param name 
	 * @param buf
	 * @return
	 * @throws IOException 
	 */
	public ErrorCode login( INonBlockingConnection con, int name  ) throws IOException {
		
		// 根据账号和密码获取 玩家
		UserInfo user = getByName( name );
		
		// 最后设置玩家socket
		return user.setConLogin( con, name );
	}
	
	
	/**
	 * 之所以要从这里运行run方法，主要是为了保证外层不再拥有user信息，<br>
	 * 所有的user信息都是从onlineUsers中获取，这样可以缩小user被发布的范围，增加线程安全性
	 * @param name
	 * @param pack
	 * @param data
	 * @return
	 * @throws IOException 
	 */
	public ErrorCode eventRun( int name, Event pack, byte[] data ) throws IOException {

		UserInfo user = getByName( name );
		if( user == null ){
			return ErrorCode.USER_NOT_FOUND;
		}
		if( user.getPackageManager().safeCheck( pack ) == false ){
			return ErrorCode.PACKAGE_SAFE_CHECK_FAIL;
		}
		ByteBuffer buf = ByteBuffer.wrap( data );
		pack.run( user, buf );
		return ErrorCode.SUCCESS;
	}
	
	
	/**
	 * 通过用户名从数据库获取玩家信息,不管是否在线，只要该玩家确实在数据库中存在，就尽力保存到内存当中来，是否在线无所谓
	 * @param string
	 * @return
	 * 			如果不存在则返回null
	 */
	public UserInfo getByName( int name  ) {
		
		if( name == 0 )
			return null;
		
		UserInfo user = onlineUsers.get( name );
		if( user == null ){
			user = new UserInfo( null, name );
//			ErrorCode code = db.get( user );
//			if( code != ErrorCode.SUCCESS )
//				return null;
			
			onlineUsers.putIfAbsent( name, user );		
		}
		
		return user;
	}
	
//	public UserInfo getByName( String name ){
//		return onlineUsers.get( name );
//	}
	
	public ErrorCode exit( int name ) throws IOException {
		
		UserInfo user = onlineUsers.get( name  );
		
		if( user != null ){
			
			user.setConClose();
			
			return ErrorCode.SUCCESS;
		}
		return ErrorCode.USER_INVALID_LOGIN;
	}
	
	/**
	 * 根据标识获得索引
	 * @param identifying
	 * @return
	 */
	public synchronized int getIndex( String identifying ) {
		
		int index 	= db.get( identifying );
		
		// 如果没有 就创建一个 
		if( index == -1 )
			index	= db.create( identifying );
		if( index == -2 )
			return getIndex( identifying );
		
		return index;
	}
	
	/**
	 * 根据账号获得索引
	 * @param account
	 * @param password
	 * @return
	 */
	public synchronized int getIndex( String account, String password ) {
		
		int index 	= db.get( account , password );
		
		return index;
	}
	
	/**
	 * 获取终端标识
	 * @param index
	 * @return
	 */
	public String getUID( int index ) {
		return db.getUID( index ) ;
	}
	
	/**
	 * 检查账号是否激活
	 * @param index
	 * @return
	 */
	public byte examine( int index ) {
		return index < 0 ? (byte)index : db.getIsAC( index );
	}
	
	/**
	 * 绑定账号
	 * @param identifying 
	 * @param account
	 * @param pass
	 * @param mail_address 
	 * @return
	 */
	public byte bindAccount( int uid, String account, String pass, String mail_address ) {
		
		// 先看有没有这个账号
		String ide	= db.getUID( uid );
		if( ide == null || ide.isEmpty() )
			return 1;
		
		// 先检查账号是否已经使用
		if( db.get( account, pass ) != -1 )
			return 2;
		
		return db.bind( uid, account, pass, mail_address );
	}
	
	/**
	 * 安卓平台注册
	 * @param identifying 
	 * @param account
	 * @param pass
	 * @param mail_address 
	 * @return
	 */
	public int register( String account, String pass, String mail_address ) {
		
		// 先检查账号是否已经使用
		if( db.get( account, pass ) != -1 )
			return 1;
		
		return db.register( account, pass, mail_address );
	}
	
	/**
	 * 根据玩家UID 获取上次登陆区ID
	 * @param uid
	 * @return
	 */
	public short getByDistrictID( int uid ) {
		return db.getToDistrictID( uid );
	}
	public void update( int index, short serverid ) {
		db.updateToDistrictID( index, serverid );
	}
	
	/** 申请密码 */
	public String toapplyPassword( String account, String mail_address ) {
		// 先检查账号是否存在
		return db.get_toapplyPassword( account, mail_address );
	}
	
	/** 是否测试账号 */
	public boolean isPsytopic( int uid ) {
		return db.isPsytopic( uid );
	}
	
	public int getUIDtoAid( String aid ) {
		return db.getUIDtoAid( aid );
	}
	
	public int getIndex_dz( String account ) {
		int index 	= db.getUIDtoAid( account );
		// 如果没有 就创建一个 
		if( index == -1 )
			index	= db.create_dz( account );
		return index;
	}
	
}
