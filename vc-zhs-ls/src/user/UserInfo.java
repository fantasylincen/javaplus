package user;

import java.io.IOException;

import log.CLog;

import org.xsocket.connection.INonBlockingConnection;

import util.ErrorCode;

public class UserInfo {

	/** 底层的网络连接  */
	private 	INonBlockingConnection	 				con;
	
	/** 包管理器 */
	private final UserPackageManager					packageManager;
	
	/** 用户名ID */
	private final int									name;
	
	/** 账号 */
	private String										account;
	
	/** 密码 */
	private String										password;
	
	/** 当前玩家的状态 */
	private UserStatus 									status = UserStatus.GUEST;
	
	/**
	 * 构造函数，保持一个尽量精简的构造函数
	 */
	public UserInfo( INonBlockingConnection con, int name  ) {
		this.con 			= con;
		this.packageManager = new UserPackageManager();
		this.name 			= name;
		this.password		= "";
		this.account		= "";
	}
	
	
	public int getName () {
		return name;
	}
	public String getPassword () {
		return password;
	}
	public String getAccount(){
		return account;
	}

	public void setPassword( String pass ){
		this.password = pass;
	}
	public void setAccount( String account ){
		this.account = account;
	}
	
	public synchronized UserStatus getStatus () {
		return status;
	}
	public synchronized void setStatus ( UserStatus status ) {
		this.status = status;
	}
	
	
	public synchronized INonBlockingConnection getCon () {
		return con;
	}
	public UserPackageManager getPackageManager () {
		return packageManager;
	}
	
	/**
	 * 设置登录连接，方案如下：
	 * 如果原本无con连接，直接赋值，并设置con的Attachment
	 * 如果原本有连接，则主动切断原有连接并返回USER_HAS_LOGIN标识，让客户端等待500ms后重试
	 * 
	 * con.setAttachment( name );这句代码可能造成死锁，但是好像也不能移到user锁之外，因为必须保证user的con为此con，而此con的attachment必须为user的name<br>
	 * 保证不会在对con进行大规模加锁的封闭调用，则可以避免
	 * 应该不会行成死锁，因为setAttachment函数仅仅在此处调用2012-10-10
	 * 这属于不变条件
	 * 
	 * @param con
	 * @param name
	 * @return
	 * @throws IOException
	 */
	public synchronized ErrorCode setConLogin( INonBlockingConnection _con, int name ) throws IOException {
		ErrorCode code = ErrorCode.SUCCESS;
		
		if( _con == null )
			return ErrorCode.USER_INVALID_LOGIN;
		
		if( this.con != null ){
//			this.con.close();
			this.con = null;
			CLog.error( name + ":抢占登陆!" );
		}
		
		// 这里玩家登陆成功
		this.con = _con;
		this.con.setAttachment( name );
		setStatus( UserStatus.LOGIN );
		
		return code;
	}
	
	/**
	 *  这里无需再次调用close函数，因为关闭连接无非
	 * 	1、客户端主动发起，这个时候，连接已经关闭
	 * 	2、服务器主动发起，在发起处，已经调用过close了
	 * 	只需把con置为null即可
	 * @throws IOException
	 */
	public synchronized void setConClose() throws IOException{
		con = null;
	}
	
}
