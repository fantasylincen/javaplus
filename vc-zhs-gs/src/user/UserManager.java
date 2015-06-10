package user;

import game.events.Event;
import game.growup.Colour;
import game.log.Logs;
import game.log.L;
import game.team.TeamHero;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.PropertyConfigurator;
import org.xsocket.connection.INonBlockingConnection;

import config.ConfigMain;
import config.fighter.HeroTempletCfg;
import define.DefaultCfg;
import define.SystemCfg;
import sololive.ip.IPRecord;
import util.ErrorCode;
import util.SystemTimer;
import util.UtilBase;
import util.db.DatabaseUtil;

/**
 * 用户管理类	单例
 * @author admin
 * 2012-8-20 下午04:09:39
 */
public class UserManager {
	//private final static Logger logger = LoggerFactory.getLogger(UserManager.class);
	
	private static final UserManager instance = new UserManager();
	public static final UserManager getInstance(){
		return instance;
	}
	private UserManager() {	}
	
	private final UserInfoDataProvider db 						= UserInfoDataProvider.getInstance();	
	private ConcurrentHashMap<Integer,UserInfo> onlineUsers 	= new ConcurrentHashMap<Integer, UserInfo>();
	private ConcurrentHashMap<Integer,UserInfo> offlineUsers 	= new ConcurrentHashMap<Integer, UserInfo>();
	
	// 数据库刷新列表
	private List<Integer> dbUpdateLs							= new ArrayList<Integer>();
	
	// 一个临时用来记录IP
	private ConcurrentHashMap<String,IPRecord> tempIPRecord		= new ConcurrentHashMap<String,IPRecord>();
	public ConcurrentHashMap<String,IPRecord> getIPRecord(){ return tempIPRecord; }
	
	
	public void putUpdate( UserInfo user ){
		if( dbUpdateLs.indexOf( user.getUID() ) == -1 )
			dbUpdateLs.add(user.getUID());
	}
	public UserInfo removeDBUpdate(){
		if( dbUpdateLs.isEmpty() ) return null;
		return getMemUser( dbUpdateLs.remove(0) );
	}
	
	public UserInfo getMemUser( int uid ) {
		UserInfo user = onlineUsers.get( uid );
		if( user == null )
			user = offlineUsers.get( uid );
		return user;
	}
	
	public void addIPRecord( String ip, String name ){
		
		if( ip == null || ip.isEmpty() )
			return;
		
		IPRecord i 	= tempIPRecord.get(name);
		
		if( i == null ){
			i 		= new IPRecord();
			i.ip 	= ip;
			i.name 	= name;
			tempIPRecord.putIfAbsent( name, i );
		}else{
			i.ip	= ip;
		}
	}
	
	public ConcurrentHashMap<Integer,UserInfo> getMaps() {
		return onlineUsers;
	}
	public ConcurrentHashMap<Integer,UserInfo> getOffMaps() {
		return offlineUsers;
	}
	
	public List<UserInfo> getOnline(){
		List<UserInfo> l = new ArrayList<UserInfo>();
		for( UserInfo user : onlineUsers.values() ) {
			l.add( user );
		}
		return l;
	}
	
	public List<UserInfo> getMemoryAllUser(){
		List<UserInfo> l = new ArrayList<UserInfo>();
		for( UserInfo user : onlineUsers.values() )
			l.add( user );
		for( UserInfo user : offlineUsers.values() )
			l.add( user );
		return l;
	}
	
	/**
	 * 玩家退出游戏，回写一些玩家的信息到数据库
	 * 
	 * 注意：
	 * 			
	 * 
	 * @param name			外层确保不会为null
	 * @return
	 * @throws IOException 
	 */
	public ErrorCode exit( int uid ) throws IOException{
		
		UserInfo user = onlineUsers.get( uid );
		if( user != null ){
//			synchronized( user )
			{
				
				// 记录下线时间
				user.setLastLogoutTime( SystemTimer.currentTimeSecond() );
				
				if( user.isAccord ){
					user.isAccord = false;
					return ErrorCode.SUCCESS;
				}
				
//				if( user.getCon() == null )
//					return ErrorCode.SUCCESS;
//
//				if( user.getStatus() == UserStatus.GUEST )
//					return ErrorCode.SUCCESS;
				
				onlineUsers.remove( user.getUID() );
				if( offlineUsers.get(user.getUID()) == null )
					offlineUsers.putIfAbsent( user.getUID(), user );
				
				return updateOffline( user );
			}
		}
		return ErrorCode.USER_INVALID_LOGIN;
	}
	
	/**
	 * 保存所有玩家数据
	 * @throws IOException 
	 */
	public void updata() throws IOException {
		for( UserInfo user : onlineUsers.values() ){
			
			// 如果已经下线 那么就不用管他了
			if( !user.isOnline() )
				continue;
			
			updateOffline( user );
		}
	}
	
	public void updataAll( List<UserInfo> list ) throws IOException {
		for( UserInfo user : list ){
//			synchronized( user )
			{
				try {
					user.setConClose();
					int onlineTime	= user.getLoginTime() == 0 ? 0 : (SystemTimer.currentTimeSecond() - user.getLoginTime());
					// 记录下线日志
					Logs.log( L.L_003, 
							user.getUID() + "," +
							user.getNickName() + "," + onlineTime);
					// 记录下线时间
					user.setLastLogoutTime( SystemTimer.currentTimeSecond() );
					// 记录登陆时间
					user.setLastLoginTime( user.getLoginTime() );
					// 团队信息在这保存一下
					user.getTeamManager().updata();
					// 好友数据在这保存一下
					user.getFriendManager().updata();
					// 记录可学习队长技能
					user.getCaptainSkillManager().updata();
					// 记录 系统奖励
					user.getAwardManager().updata();
					// 副本星级
					user.getEctypeStartLevel().update();
					updata( user );
				} catch (Exception e) {
					Logs.error( user, "服务器关闭时 保存玩家信息出错: " + e.getLocalizedMessage() );
				}
				
			}
			
		}
		onlineUsers.clear();
		offlineUsers.clear();
		dbUpdateLs.clear();
	}
	
	/**
	 * 保存单个玩家信息
	 * @param user
	 */
	public void updata( UserInfo user ) {
		db.update( user );
	}
	
	/**
	 * 及时保存连续登陆次数
	 * @param user
	 */
	public void updataToIsCL( UserInfo user ) {
		db.updataToIsCL( user );
	}
	
	private ErrorCode updateOffline( UserInfo user ) throws IOException{
		
		try {
			// 关掉con
			user.setConClose();
			
			int onlineTime	= user.getLoginTime() == 0 ? 0 : (SystemTimer.currentTimeSecond() - user.getLoginTime());
			// 记录下线日志
			Logs.log( L.L_003, 
					user.getUID() + "," +
					user.getNickName() + "," + onlineTime);
		
			// 记录下线时间
//			user.setLastLogoutTime( SystemTimer.currentTimeSecond() );
			// 记录登陆时间
			user.setLastLoginTime( user.getLoginTime() );
			// 团队信息在这保存一下
			user.getTeamManager().updata();
			// 好友数据在这保存一下
			user.getFriendManager().updata();
			// 记录可学习队长技能
			user.getCaptainSkillManager().updata();
			// 记录 系统奖励
			user.getAwardManager().updata();
			// 副本星级
			user.getEctypeStartLevel().update();
			
			// 保存玩家信息
			UserManager.getInstance().putUpdate(user);
		} catch (Exception e) {
			Logs.error( user, "在玩家退出时 保存玩家信息出错: " + e.getLocalizedMessage() );
		}
		
		return ErrorCode.SUCCESS;
	}
	
	/**
	 * 创建一个新玩家，玩家名以及其他基本属性由user指定
	 * @param con 
	 * @param user
	 * @return
	 * @throws IOException 
	 */
	public ErrorCode create( INonBlockingConnection con, UserInfo user, int heroid ) throws IOException{
		
		ErrorCode code = db.create( user ) ;
		
		if( code == ErrorCode.SUCCESS ){
			
			initUser( user );
			
			// 加入 创建玩家的初始值
			addDefaultHero( user, heroid );
			
			onlineUsers.putIfAbsent( user.getUID(), user );
			user.getTeamManager().add();
			
			// 创建成功后 设置socket
			code = user.setConLogin( con, user.getUID() );
			
			UserManager.getInstance().putUpdate( user );
		}
		
//		virtualUsers.remove( user.getUID() );
		
		return code;
	}
	
	public static void main(String[] args) throws IOException {
		// 初始化 日志
		PropertyConfigurator.configureAndWatch("log4jconfig/log4j.properties");
		SystemCfg.init();
		DatabaseUtil.initialize();
		ConfigMain.readAllCfg();
		UserInfo user = new UserInfo(null, 417408373);
		user.setNickName( "Lambda" );
		UserManager.getInstance().create( null,  user, 10098 );
	}
	
	private void initUser( UserInfo user ){
		
		// 初始英雄背包上限
		user.setBagCapacity( Short.parseShort( DefaultCfg.getByCfg( "max" , "max_hero_count" ) ) );
		// 初始体力值
		user.setStrengthMax( DefaultCfg.MAX_STRENGTH  );
		user.changeStrength( DefaultCfg.MAX_STRENGTH, "初始化" );
		user.setBuyStrCountMax( (byte) 1 );
		// 初始好友上限
		user.setFriendCapacity( Short.parseShort( DefaultCfg.getByCfg( "max" , "max_friend_count" ) ) );
		// 初始装备背包上限
		user.setEquipbagCapacity( DefaultCfg.EQUIP_BAG_MAX_INIT );
		// 初始等级
		user.setLevel( Short.parseShort( DefaultCfg.getByCfg( "create" , "user_lv" ) ) );
		// 初始金币
		user.addCash( Integer.parseInt( DefaultCfg.getByCfg( "create" , "init_cash" ) ) );
		// 初始水晶
		user.addGold( Integer.parseInt( DefaultCfg.getByCfg( "create" , "init_gold" ) ) );
		// 初始PVP购买次数上限
		user.setPvpMateBuyCount( DefaultCfg.PVP_MATE_BUY_COUNT );
		
		//初始VIP等级
//		user.setVipLevel( (byte) 2 );
	}
	
	// 添加默认英雄
	private void addDefaultHero( UserInfo user, int heroid )
	{
		// 初始天赋
		user.getTalentManager().addDefault();
		
		// 赠送 一个蓝色小龙
//		user.getHeroManager().create( 30081, Colour.BLUE, (short)1, (byte)0, true );
//		// 赠送一个选择的绿色卡片
//		user.getHeroManager().create( heroid, Colour.GREEN, (short)1, (byte)0, true );
				
		List<TeamHero> teamHero = new ArrayList<TeamHero>( );
		// 先创建队长
		int uid 				= user.getHeroManager().create( heroid, Colour.GREEN, (short)20, (byte)0, true );
		// 放入团队 位置默认0
		TeamHero hero 			= new TeamHero( uid, (byte)0, false );
		teamHero.add( hero );
		
		// 获取小怪 然后再一个一个 加进去
		int[] heroId			= getGiveHero( heroid );
		if( heroId != null ) 
			for( int i = 0; i < heroId.length; i++ ){
				
				int id 			= heroId[i];
				Colour colour	= Colour.GREEN;
				short lv		= 10; 
				
				if( HeroTempletCfg.getById(id) == null ) {
					Logs.error( user, "在初始英雄出错   找不到ID=" + id );
					continue;
				}
				
				// 这里加入到 英雄数据库
				uid 			= user.getHeroManager().create( id, colour, lv, (byte)0, true );
				if( uid == -1 )	continue;
				
				// 这里加入到 团队 位置从1开始
				byte pos 		= (byte)(i < 6 ? i + 1 : -1);
				hero 			= new TeamHero( uid, pos, false );
				
				teamHero.add( hero );
			}
		
		// 初始团队
		user.getTeamManager().initTeam( teamHero );
		
		//
//		for( int i = 1001; i <= 1005; i++ ){
//			user.getEquipmentManager().create( i );
//		}
//		for( int i = 2901; i <= 2905; i++ ){
//			user.getEquipmentManager().create( i );
//		}
//		for( int i = 3901; i <= 3905; i++ ){
//			user.getEquipmentManager().create( i );
//		}
//		for( int i = 4901; i <= 4905; i++ ){
//			user.getEquipmentManager().create( i );
//		}
//		for( int i = 5901; i <= 5905; i++ ){
//			user.getEquipmentManager().create( i );
//		}
		
	}

	private int[] getGiveHero( int id ){
		
		int[] hero 		= null;
		switch( id ){
		// 坦克30024，法师30099，弓箭手30021，奶妈30062
		case 10010: // 盖伦
			hero		= new int[3];
			hero[0]		= 30099;
			hero[1]		= 30021;
			hero[2]		= 30062;
			return hero;
		case 10091: // 流浪
			hero		= new int[3];
			hero[0]		= 30024;
			hero[1]		= 30021;
			hero[2]		= 30062;
			return hero;
		case 10098: // 寒冰
			hero		= new int[3];
			hero[0]		= 30024;
			hero[1]		= 30099;
			hero[2]		= 30062;
			return hero;
		case 10109: // 索拉卡
			hero		= new int[3];
			hero[0]		= 30024;
			hero[1]		= 30099;
			hero[2]		= 30021;
			return hero;
		}
		return hero;
	}
	
	/**
	 * 显示所有的在线用户列表
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder( "online user[\n");
		for( Entry<Integer, UserInfo> e : onlineUsers.entrySet() ){
			sb.append( "[" );
			sb.append( e.getValue() + "|" + e.getKey() );
			sb.append( "]\n" );
		}
		sb.append( "]" );
		return sb.toString();
	}
	/**
	 * 通过用户名从数据库获取玩家信息,不管是否在线，只要该玩家确实在数据库中存在，就尽力保存到内存当中来，是否在线无所谓
	 * @param string
	 * @return
	 * 
	 * 			如果不存在则返回null
	 * 
	 */
	public UserInfo getByName( Integer name ) {
		if( name == null ) return null;
		
		UserInfo user = onlineUsers.get( name );
		if( user == null ){
			user 	= offlineUsers.get( name );
			if( user == null ){
				user	= new UserInfo( null, name );
				if( db.get( user ) != ErrorCode.SUCCESS )
					return null;
				// 在这里初始 所有唯一ID基础值
				user.getBasisUniqueID().initDataUniqueID();
				offlineUsers.putIfAbsent( name, user );
			}
		}
		return user;
	}
	
	/**
	 * 根据账号获得角色名
	 * @param name
	 * @return
	 */
	public String getNickName( Integer name ){
		
		if( name == null || name == 0 ){
			return "未知";
		}
		
		UserInfo user = getByName( name );
		
		return user != null ? user.getNickName() : "未知";
	}
	/**
	 * 通过昵称获取玩家信息
	 * @param string
	 * @return
	 * 
	 */
	public UserInfo getByNickName( String nickName ) {
		int name = db.getNameByNickName( nickName );
		if( name == 0 ){
			return null;
		}
		return getByName( name );
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
	 * 获取数据库 数据
	 */
	public void accessDatabase() {
		tempIPRecord.clear();
		onlineUsers.clear();
		offlineUsers.clear();
		offlineUsers = db.getAll();
	}
	
	/**
	 * 删除一个用户
	 * @param name
	 */
	public void clearUser( String nickName ){
		
		int uid			= db.getNameByNickName( nickName );
		UserInfo user 	= onlineUsers.remove( (Integer)uid );
		
		try {
			user.setConClose();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 删除
		db.remove( user ); 
	}
	

	
	/**
	 * 修改名字
	 * @param u
	 * @param string
	 */
	public boolean modifyName( UserInfo u, String name ) {
		
		if( UtilBase.nameIsIllegal( name ) )
			return false;
		
		if( nickNameIsDuplicate( name ) )
			return false;
		
		u.setNickName( name );
		
		return true;
	}

	/**
	 * 检查名字是否重复  这里直接就在玩家 MAP里面找 因为 已进来就已经全部角色信息全部获取
	 * @param name
	 * @return
	 */
	private boolean nickNameIsDuplicate( String name ){
		
		for( UserInfo u : onlineUsers.values() ){
			if( u.getNickName().equals( name ) )
				return true;
		}
		return false;
	}
	public void putOnline(int index, UserInfo user) {
//		if( onlineUsers.get(index) == null )
			onlineUsers.putIfAbsent( index, user );
	}
	public void removeOffline( int index ) {
//		if( offlineUsers.get(index) != null )
			offlineUsers.remove( (Integer)index );
	}
	public int memoryNum() {
		return onlineUsers.size() + offlineUsers.size();
	}
	
	public List<UserInfo> getUserList( int uid ) {
		return db.getList( uid );
	}
	
}
