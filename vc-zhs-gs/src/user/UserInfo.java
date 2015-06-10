package user;

import game.activity.ActivityManager;
import game.award.AwardType;
import game.award.AwardInfo;
import game.award.ectype.EctypeReconnectManager;
import game.award.system.AwardManager;
import game.battle.dbinfo.BattleInfoManager;
import game.ectype.EctypeManager;
import game.ectype.EctypeStartLevel;
import game.ectype.EliteEctypeCountManager;
import game.equipment.EquipmentManager;
import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;
import game.fighter.Hero;
import game.fightoffriend.FightOfFriendManager;
import game.friend.FriendManager;
import game.growup.Colour;
import game.growup.captain.CaptainSkillManager;
import game.hero.HeroManager;
import game.invitingfriends.InvitingFriendsManager;
import game.log.Logs;
import game.mail.MailManager;
import game.pvp.DanGradingManager;
import game.qualifying.QualifyingManager;
import game.talent.TalentManager;
import game.team.TeamManager;
import game.util.StrengthUpgrade;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xsocket.connection.INonBlockingConnection;

import lombok.Getter;
import lombok.Setter;
import manager.DWType;

import config.boot.BootTempletCfg;
import config.grow.UserGrowTemplet;
import config.grow.UserGrowTempletCfg;
import config.recharge.RechargeTempletCfg;
import config.saward.SAwardType;
import config.vip.VipInfoTemplet;
import config.vip.VipInfoTempletCfg;

import define.DefaultCfg;
import define.UserUniqueID;
import deng.xxoo.utils.XOTime;

import util.ErrorCode;
import util.SystemTimer;
import util.UtilBase;

/**
 * 用户基础信息类
 * 
 * 线程安全？
 * 
 * @author dxf
 * 2013-6-30 下午02:24:20
 */
public class UserInfo {
//	private final static Logger logger = LoggerFactory.getLogger( UserInfo.class ); 

	public byte getVipLevel() {
		return vipLevel;
	}

	public void setVipLevel(byte vipLevel) {
		this.vipLevel = vipLevel;
	}

	/** 玩家英雄管理器 */
	private HeroManager									heroManager;
	
	/** 玩家团队管理器 */
	private TeamManager									teamManager;
	
	/** 包管理器 */
	private final UserPackageManager					packageManager;

	/** 副本通过管理器 */
	private EctypeManager								ectypeManager;
	
	/** 英雄副本记录次数  管理器 */
	private EliteEctypeCountManager						eliteEctypeCountManager;
	
	/** 副本准备奖励管理器 */
	private EctypeReconnectManager						ectypeReconnectManager;
	
	/** 副本战斗前 邀请好友列表 管理器 */
	private FightOfFriendManager						fightOfFriendManager;
	
	/** 战报管理 */
	private BattleInfoManager							battleInfoManager;
	
	/** 好友管理 */
	private FriendManager								friendManager;
	
	/** 邮件管理 */
	private MailManager									mailManager;
	
	/** 天赋管理 */
	private TalentManager								talentManager;
	
	/** 系统奖励管理 */
	private AwardManager								awardManager;
	
	/** pvp管理 */
	private DanGradingManager							danGradingManager;
	
	/** 邀请好友管理 */
	private InvitingFriendsManager						invitingFriendsManager;
	
	// 装备管理中心
	private EquipmentManager							equipmentManager;
	
	/** 重置队长记录 */
	private CaptainSkillManager							captainSkillManager;
	
	/** 排位赛管理 */
	private QualifyingManager							qualifyingManager;
	
	/** 所有唯一ID基础值 */
	private UserUniqueID								basisUniqueID;
	
	/** 底层的网络连接  */
	private 	INonBlockingConnection	 				con;
	
	/** 用户唯一ID */
	private final int									uID;
	
	/** 当前玩家的状态 */
	private UserStatus 									status = UserStatus.GUEST;
	
	/** 当前玩家的游戏状态 */
	private GameStatus 									gameStatus = GameStatus.NORMAL;

	/**  现金 */
	private int											cash;
	
	/** 金币，点券 */
	private int											gold;
	
	/** 体力 */
	private short										strength;
	
	/** 体力上限 */
	private short										strengthMax;
	
	// 体力当前回复时间
	private int											strReplyTime;
	
	// 当前已购买次数
	private byte										buyStrCount;
	
	// 购买次数上限
	private byte										buyStrCountMax;
//	public byte 										addbuyStrCount = 0;
	
	/**  昵称 */
	private String										nickName;
	
	/** 等级 */
	private short										level;
	
	/** 当前经验 */
	private int											curExp;
	
	/** 创建时间，单位：秒 */
	private int											createTime;

	/** 本次登陆时间  这个值不记录数据库 */
	private int											loginTime;
	
	/** 上次登陆时间   单位：秒  */
	private int 										lastLoginTime;
	
	/** 上次下线时间，单位：秒 */
	private int											lastLogoutTime;
	
	/** 玩家总的登陆次数 */
	private short										loginCount;
	
	/** 玩家每日登陆次数 */
	private int											dayLoginCount;
	
	private byte										sex						= 1;
	
	private boolean										isAdult					= true;

	// 背包上限 这里的背包 就是英雄背包
	private short 										bagCapacity;
	
	// 装备背包上限 
	@Setter @Getter
	private short 										EquipbagCapacity;
	
	// 友情值
	private int 										friendshipValue;
	
	// 好友个数上限
	private short 										friendCapacity;

	// 新手引导ID
	private short 										newbieGuideID			= 0;
	
	// vip等级
	private byte										vipLevel				= 0;

	// 充值的 水晶
	private int											rechargeGold;
	
	// 充值的 人民币
	private float 										rechargeMoney;
	
	// 充值的 人民币
	private float 										rechargeMoney1;
	// 充值的 人民币
	private float 										rechargeMoney2;
	
	// 购买PVP次数上限
	private byte										pvpMateBuyCount;
		
	// 月卡天数
	private short[]										monthCardFate			= new short[]{0,0,0};
	
	// 限购
	private List<Short> 								restriction 			= new ArrayList<Short>();
	
	// 奖杯数量
	private int											trophyNumer				= 0;
	
	// 副本星级
	private EctypeStartLevel							ectypeStartLevel;
	
	// 记录 英雄主题周
	private byte										isHthemeWeek;
	
	// 记录 抽卡送豪礼
	private byte										isCardTo10;
	
	// 记录副本状态 不记录数据库 
	private ErrorCode									ectypeStatus = ErrorCode.SUCCESS;

	/**
	 * 所在服务器ID 
	 * 这里主要是为了 以后合服 准备
	 */
	private int											serverID;

	public boolean isLingQvRiCahgn	= false;

	public float xinkai		= 0;
	
	public float xinkai1	= 0;
	
	// 是否流失
	public boolean isTurnover = false;
	
	// 是否第一次抽卡
	public boolean isFirsttimeThatcard = true;
	
	// 当天是否领取了 奖励
	public volatile long drawAwardTime = 0;
	
	// 充值金额 - 只限乐巴 和 定制
	public float RMB	= 0;
	
	/**
	 * 构造函数，保持一个尽量精简的构造函数
	 * @param sex 
	 * @param nickName 
	 */
	public UserInfo( INonBlockingConnection con, int name, String nickName ) {
		this.con 			= con;
		this.packageManager = new UserPackageManager();
		this.uID 			= name;
		this.nickName 		= nickName;
	}
	
	public UserInfo( INonBlockingConnection con, int name ) {
		this.con 			= con;
		this.packageManager = new UserPackageManager();
		this.uID 			= name;
	}

	public int getUID() {
		return this.uID;
	}
	
	//TODO ////////////////////////////////////////////   玩家等级       ////////////////////////////////////////////////////////////////////
	public short getLevel () {
		return level;
	}
	public void setLevel ( short level ) {
		this.level = level;
	}
	/**
	 * 是否满级
	 * @return
	 */
	public boolean isFullLevel(){
		return this.level == DefaultCfg.USER_FULL_LEVEL;
	}
	
	//TODO ////////////////////////////////////////////    玩家经验        ////////////////////////////////////////////////////////////////////
	/** 获得当前经验  */
	public int getCurExp(){
		return this.curExp;
	}
	/**
	 * 添加玩家经验
	 * @param exp
	 */
	public int addExp( int exp )
	{
		if( exp <= 0  || isFullLevel() ) return this.curExp;
		
		UserGrowTemplet ut	= UserGrowTempletCfg.getTempletById( (short)this.level );
		if( ut == null ) return 0;
		int maxExp 			= ut.getExp();
		
		this.curExp			+= exp;
		
		short isUpgrade 	= 0; 
		
		// 表示升级了 用循环 是可能会连续升几级
		while( this.curExp >= maxExp ){
			upLevel();
			++isUpgrade;
			this.curExp 	-= maxExp;

			// 这里表示满级了
			if( isFullLevel() ) {
				this.curExp = maxExp;
				break;
			}
			maxExp = UserGrowTempletCfg.getTempletById( (short) this.level ).getExp();
		}
		
		if( this.con != null && this.con.isOpen() ){
			if( isUpgrade > 0 ){
				if( friendCapacity <= FriendManager.MAX_NUM_LEVEL )
					UpdateManager.instance.update( this, UpdateType.U_11 );
			}
			
			UpdateManager.instance.update( this, UpdateType.U_1 );
		}
		
		if( isUpgrade > 0 )
			handleUpgrade( isUpgrade );
		
		return this.curExp;
	}
	
	// 玩家升级 需要处理的事情 在这里做
	public void upLevel()
	{
		//先是等级 加一
		++this.level;
		
		// 如果小于上限才加 好友上限
		if( friendCapacity < FriendManager.MAX_NUM_LEVEL ){
			++friendCapacity;
		}
		
	}
	
	public void handleUpgrade( short isUpgrade ){
		
		// 体力值回复满
		changeStrength( StrengthUpgrade.run( isUpgrade, this.level, this.strengthMax ), "玩家升级 回满体力值" );
		
		// 记录 系统奖励 是否满足
		getAwardManager().record( SAwardType.UG, this.level );
		
		// 是否满足奖励系统条件
		if( isSatisfyCondition( isUpgrade ) ){
			
			UserInfo u = UserManager.getInstance().getByName( getInvitingFriendsManager().getIMyID() );
			if( u != null ){
//				synchronized( u ){
					u.getInvitingFriendsManager().awardToRecord( this, isUpgrade );
//				}
			}
		}
		
		// 更新天赋提示
		getTalentManager().runReminder();
		
		// 赠送引导物品
		BootTempletCfg.run( this, level );
	}
	
	private boolean isSatisfyCondition( short isUpgrade ){
		short toLevel = (short) (level - isUpgrade);
		return (level / 10) - (toLevel / 10) >= 1;
	}
	
	//TODO ////////////////////////////////////////////    英雄经验        ////////////////////////////////////////////////////////////////////
	public void addHeroExp( int exp )
	{
		heroManager.addExp( exp );
	}
	
	
	//TODO ////////////////////////////////////////////    奖杯        ////////////////////////////////////////////////////////////////////
	public int addTrophy( int change ){
		if( getTrophyNumer() + change < 0 ){
			return -1;
		}
		setTrophyNumer(getTrophyNumer() + change);
		
		return getTrophyNumer();
	}

	//TODO ////////////////////////////////////////////    体力        ////////////////////////////////////////////////////////////////////
	
	/** 体力回满 */
	public void fullStrengthBack() {
		if( strength < strengthMax )
			changeStrength( strengthMax - strength, "每日回满" );
	}
	
	public void setBuyStrCount( byte count ){
		this.buyStrCount = count;
	}
	
	public byte getBuyStrCount(){
		return this.buyStrCount;
	}
	
	public void setBuyStrCountMax( byte count ){
		this.buyStrCountMax = count;
	}
	
	public byte getBuyStrCountMax(){
		return this.buyStrCountMax;
	}

	public int getBuyStrNeedGold() {
		if( buyStrCount <= 2 ){
			return 50;//50
		}else if( buyStrCount == 3 ){
			return 80;//80
		}else {
			return 100;//100
		}
	}
	public boolean IsBuyStrCount() {
		byte str = (byte) (ActivityManager.getInstance().isConsumeOrgyIsOpen() ? this.getBuyStrCountMax() + 1 : this.getBuyStrCountMax());
		return this.buyStrCount >= str;
	}
	public void updateBuyStrCount(){
		++this.buyStrCount;
	}
	
	/**  获取当前体力值 */
	public short getStrength() {
		return strength;
	}
	
	public short getStrengthMax(){
		return this.strengthMax;
	}
	public void setStrengthMax( short strengthMax ){
		this.strengthMax = strengthMax;
	}
	/** 
	 * 修改体力
	 * @param change		增加为正数，减少为负数
	 * @param funcName
	* @return 					< 0		体力扣除失败
	 * 							>=0		当前拥有的体力
	 */	
	public int changeStrength( int change, String funcName ){
		if( strength + change < 0 ){
			Logs.debug( this, "拥有体力：" + strength + "欲扣除体力：" + change + "，出现负数，调用函数为" + funcName );
			return -1;
		}
		
		// 在扣之前先算一下 
		getStrReplyTimeToSecond( 1 );
		
		strength += change;
		
		if( strength >= strengthMax ){
//			strength	 = strengthMax;
			strReplyTime = 0; // 如果满了 那么时间就应该为0
			
		}else if( strReplyTime == 0 ){// 这里记录 时间
			strReplyTime = SystemTimer.currentTimeSecond();
		}
		
		return strength;
	}
	
	/**  获取当前体力回复时间点  */
	public void setStrReplyTime( int strengthReplytime ) {
		
		int curTime 		= SystemTimer.currentTimeSecond();
		
		// 用循环 如果有多次
		while( strengthReplytime != 0 && (strengthReplytime + DefaultCfg.STRENGTH_REPLYTIME) < curTime ){
			
			strengthReplytime 	+= DefaultCfg.STRENGTH_REPLYTIME;
			
			strength 			+= DefaultCfg.STRENGTH_REPLY_NUM;
			if( strength >= strengthMax ){
				strength	 	= strengthMax;
				strReplyTime 	= 0; // 如果满了 那么时间就应该为0
				return;
			}
		}
		
		this.strReplyTime 	= strengthReplytime;
	}
	
	/** 获取体力记录时间  这个主要是DB存储需要 */
	public int getStrReplyTime() {
		return this.strReplyTime;
	}
	public void setStrReplyTime1( int strr ){
		this.strReplyTime = strr;
	}
	
	/**  获取当前体力回复时间点 （单位秒） 这个主要是前端需要  */
	public int getStrReplyTimeToSecond( int at ){
		
		if( strength >= strengthMax ){
//			if( at == 0 )
//				strength 	= strengthMax;
			strReplyTime 	= 0; // 如果满了 那么时间就应该为0
			return strReplyTime;
		}
		
		// 算出过去时间 
		int formerly 		= SystemTimer.currentTimeSecond();
		
		// 用循环 如果有多次
		while( strReplyTime != 0 && (strReplyTime + DefaultCfg.STRENGTH_REPLYTIME) < formerly ){
			
			strReplyTime 	+= DefaultCfg.STRENGTH_REPLYTIME;
			
			strength 		+= DefaultCfg.STRENGTH_REPLY_NUM;
			if( strength >= strengthMax ){
				strength	 = strengthMax;
				strReplyTime = 0; // 如果满了 那么时间就应该为0
				return 0;
			}
		}
		
		int teplyTime 		= strReplyTime == 0 ? 0 : (strReplyTime + DefaultCfg.STRENGTH_REPLYTIME) - formerly;
		
		return teplyTime;
	}
	
	
	//TODO ////////////////////////////////////////////    金币        ////////////////////////////////////////////////////////////////////
	
	public int getCash(){
		return cash;
	}
	
	/**
	 * 修改玩家的金币数量
	 * @param change			增加为正数，减少为负数
	 * 
	 * @return 					-1		扣除失败<br>
	 * 							>=0		当前拥有的现金<br>
	 * 
	 * 注意：仅仅用于从数据库中进行初始化操作，其余的修改请调用{@link #changeAward(AwardType, int, String)}
	 */
	public int addCash( int change ){
		
		if( cash + change < 0 ){
			
			return -1;
		}
		cash += change;
		
		//TODO 处理防沉迷系统，其他的vip加成等信息
		
//		Thread thr = Thread.currentThread();
//      StackTraceElement[] ele = thr.getStackTrace();
//      String func = ele[2].getMethodName();
//      System.out.println(func);
 
//		buildLog( AwardType.CASH, change, cash, funcName );
		return cash;
	}
	
	//TODO ////////////////////////////////////////////    水晶        ////////////////////////////////////////////////////////////////////
	
	/**
	 * 获取玩家水晶
	 * @return
	 */
	public int getGold(  ){
		return gold;
	}
	
	/**
	 * 修改玩家的水晶数量
	 * @param change			增加为正数，减少为负数
	 *
	 * @return 					< 0		扣除失败<br>
	 * 							>=0		当前拥有的金币<br>
	 * 
	 * 	注意：仅仅用于从数据库中进行初始化操作，其余的修改请调用{@link #changeAward(AwardType, int, String)}
	 */
	public int addGold( int change ){
		
		if( gold + change < 0 ){
			return -1;
		}
		gold += change;
		
		return gold;
	}
	
	/**
	 * 充值水晶
	 * @param number
	 */
	public void recharge( int number ) {
		if( number <= 0 ) return;
		
		// 先设置充值金额
		setRechargeGold(getRechargeGold() + number);
		
		// 添加实际水晶
		addGold( number );
		
		// 添加奖励记录
		getAwardManager().record( SAwardType.VIP, this.getRechargeGold() );
		getAwardManager().record( SAwardType.TU, this.getRechargeGold() );
		
		// 刷新水晶
		UpdateManager.instance.update( this, UpdateType.U_4 );
		
		// 刷新VIP等级
		updateVipLevel();
	}
	
	private void updateVipLevel() {
		
		VipInfoTemplet v		= VipInfoTempletCfg.get( (byte) (vipLevel + 1) );
		if( v == null ) return;
		
		int maxValue			= v.getCondition();
		
		boolean isUp			= false;
		boolean isBuyStr		= false;
		boolean isStr			= false;
		boolean isDanBuy		= false;
		while( getRechargeGold() >= maxValue ){
			++vipLevel;
			isUp				= true;
			
			if( v.getBuyStrCount() != 0 ){
				isBuyStr			= true;
				this.buyStrCountMax	+= v.getBuyStrCount();
			}
			if( v.getPhysicalLimit() != 0 ){
				isStr				= true;
				this.strengthMax	+= v.getPhysicalLimit();
			}
			if( v.getBuyPvpCount() != 0 ){
				isDanBuy			= true;
				this.setPvpMateBuyCount((byte) (this.getPvpMateBuyCount() + v.getBuyPvpCount()));
			}
			
			v					= VipInfoTempletCfg.get( (byte) (vipLevel + 1) );
			if( v == null ) break;
			maxValue 			= v.getCondition();
		}
		
		if( isUp )
			UpdateManager.instance.update( this, UpdateType.U_26 );
		if( isBuyStr )
			UpdateManager.instance.update( this, UpdateType.U_15 );
		if( isStr ){
			if( strReplyTime == 0 && strength < strengthMax)
				strReplyTime = SystemTimer.currentTimeSecond();
			UpdateManager.instance.update( this, UpdateType.U_2 );
		}
		if( isDanBuy )
			UpdateManager.instance.update( this, UpdateType.U_17 );
		
		UserManager.getInstance().putUpdate( this );
	}

	//TODO ////////////////////////////////////////////    友情点        ////////////////////////////////////////////////////////////////////
	
	/** 获得友情点 */
	public int getFriendshipValue(){
		return friendshipValue;
	}
	
	/**
	 * 修改玩家的友情点
	 * @param change			增加为正数，减少为负数
	 *
	 * @return 					< 0		扣除失败<br>
	 * 							>=0		当前拥有的友情点<br>
	 * 
	 * 	注意：仅仅用于从数据库中进行初始化操作，其余的修改请调用{@link #changeAward(AwardType, int, String)}
	 */
	public int addFriendshipValue( int change ){
		
		if( friendshipValue + change < 0 ){
			return -1;
		}
		friendshipValue += change;
		
		UpdateManager.instance.update( this, UpdateType.U_6 );
		
		return friendshipValue;
	}
	
	//TODO ////////////////////////////////////////////    奖励        ////////////////////////////////////////////////////////////////////
	/**
	 * 玩家涉及到属性的更改操作
	 * @param award
	 * @param funcName
	 * @return
	 * 		>=0	:返回此属性的当前值
	 * 		-1	:扣除失败，余值不足
	 * 		
	 */
	public int changeAward( AwardInfo award, String funcName, DWType dwtype ){
		int result = 0;
		if( award == null ) return result;
		int change 		= award.getNumber();
		int[] arguments	= award.getArguments();
		
		switch( award.getAward() ){
		case CASH:
			if( change != 0 ){
				result 	= this.addCash( change );
				UpdateManager.instance.update( this, UpdateType.U_3 );
			}
			break;
		case GOLD:
			if( change != 0 ){
				result 	= this.addGold( change );
				UpdateManager.instance.update( this, UpdateType.U_4 );
				// 记录数据仓库
//				DWManager.Instance.put( new RecordGoldBase( getUID(), dwtype, change ) );
			}
			break;
		case TROPHY:
			if( change != 0 ){
				result 	= this.addTrophy( change );
				UpdateManager.instance.update( this, UpdateType.U_29 );
			}
			break;
		case STRENGTH:
			result 	= this.changeStrength( change , "" );
			UpdateManager.instance.update( this, UpdateType.U_2 );
			break;
		case HERO:
			change				= award.getPropId();
			List<Hero> list 	= new ArrayList<Hero>();
			
			if( arguments == null || arguments.length != 3 ){
				for( int i = 0; i < award.getNumber(); i++ ){
					result 			= getHeroManager().create( change, Colour.GREEN, (short)1, (byte)0, false );
					list.add( getHeroManager().getHero(result) );
				}
			}else{
				for( int i = 0; i < award.getNumber(); i++ ){
					short levelBase	= (short) arguments[0];
					Colour colour	= Colour.fromNumber( arguments[1] );
					byte qlevel		= (byte) arguments[2];
					result 			= getHeroManager().create( change, colour, levelBase, qlevel , false );
					list.add( getHeroManager().getHero(result) );
				}
			}
			UpdateManager.instance.update( this, UpdateType.U_100, list );
			break;
		case PROP:
			change						= award.getPropId();
			for( int i = 0; i < award.getNumber(); i++ ){
				result 					= getEquipmentManager().create( change );
			}
			break;
		default:
			throw new IllegalArgumentException( award.getAward() + "属性不存在相应函数" );
		}
		if( result == -1 ){
			Logs.debug( nickName + "奖励类型：" + award.getAward() + "欲改变数值：" + change + "，出现负数，调用函数为" + funcName );
		}
		buildLog( award.getAward(), change, result, dwtype );
		if( !this.isOnline() )
			UserManager.getInstance().putUpdate(this);
		return result;
	}
	/**
	 * 玩家涉及到属性的更改操作
	 * @param type
	 * @param change
	 * @param funcName
	 * @return
	 * 		>=0	:返回此属性的当前值
	 * 		-1	:扣除失败，余值不足
	 * 		
	 */
	public int changeAward( AwardType type, int change, String funcName, DWType dwtype ) {
		int result = 0;
		switch( type ){
		case CASH:
			result 	= this.addCash( change );
			break;
		case GOLD:
			result 	= this.addGold( change );
			// 记录数据仓库
//			DWManager.Instance.put( new RecordGoldBase( getUID(), dwtype, change ) );
			break;
		case EXP:
			result  = this.addExp( change );
			break;
		case FD_VALUE:
			result  = this.addFriendshipValue( change );
			break;
		case HERO_EXP:
			this.addHeroExp( change );
			result	= change;
			break;
		case TROPHY:
			this.addTrophy( change );
			result	= change;
			break;
		default:
			throw new IllegalArgumentException( type + "属性不存在相应函数" );
		}
		if( result == -1 ){
			Logs.debug( nickName + "奖励类型：" + type + "欲改变数值：" + change + "，出现负数，调用函数为" + funcName );
		}
		buildLog( type, change, result, dwtype );
		return result;
	}
	
	/**
	 * 是{@link #changeAward(AwardType, int, String)}的语法糖，希望后期根据代码的实际情况能和此函数进行一次精简，去掉一个相对较少使用的版本
	 * @param award
	 * @param funcName
	 * @return
	 */
	public ErrorCode getAward( AwardInfo award, String funcName ){
		int result = 0;
		switch( award.getAward() ){
		case CASH:
			result = this.addCash( award.getNumber() );
		default:

			break;
		}
		if( result == -1 ){
			Logs.debug( nickName + "奖励类型：" + award.getAward() + "欲改变数值：" + award.getNumber() + "，出现负数，调用函数为" + funcName );
		}
		/**
		 * 考虑背包满的情况
		 */
		return ErrorCode.SUCCESS;
	}
	
	
	public String getNickName () {
		return nickName;
	}
	
	public void setNickName ( String nickName ) {
		this.nickName = nickName;
	}
	
	public UserStatus getStatus () {
		return status;
	}
	public void setStatus ( UserStatus status ) {
		this.status = status;
	}
	
	public GameStatus getGameStatus () {
		return gameStatus;
	}
	public void setGameStatus ( GameStatus status ) {
		this.gameStatus = status;
	}
	
	public INonBlockingConnection getCon () {
		return con;
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
	public ErrorCode setConLogin ( INonBlockingConnection con, int name ) throws IOException {
		ErrorCode code = ErrorCode.SUCCESS;
		if( con == null )
			return ErrorCode.UNKNOW_ERROR;
		
//		if( this.con != null ){
//			lastLoginTime	= loginTime;
//			Logs.error( this, "抢占登陆!" );
//			Logs.error( this, "原：" + this.con.getId() + "  现：" + con.getId() );
////				this.con.close();
//			if( !this.con.getId().equals( con.getId() ) ){
//				this.con = null;
//				this.con = con;
//				this.con.setAttachment( name );
//			}
//		}else{
//			// 这里玩家登陆成功
//			this.con = con;
//			this.con.setAttachment( name );
//		}
		
		if( this.con != null ){
			lastLoginTime	= loginTime;
			Logs.error( this, "抢占登陆!" );
			isAccord = true;
			this.con.close();
		}
		
		this.con = con;
		this.con.setAttachment( name );
		
		++loginCount;
		setStatus( UserStatus.LOGIN );
		return code;
	}
	public void cetCon(INonBlockingConnection con ) {
		this.con = null;
		this.con = con;
	}
	
	/**
	 *  这里无需再次调用close函数，因为关闭连接无非
	 * 	1、客户端主动发起，这个时候，连接已经关闭
	 * 	2、服务器主动发起，在发起处，已经调用过close了
	 * 
	 * 	只需把con置为null即可
	 * @throws IOException
	 */
	public void setConClose() throws IOException{
		con = null;
	}
	public UserPackageManager getPackageManager () {
		return packageManager;
	}
	
	
	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

	public int getLastLogoutTime() {
		return lastLogoutTime;
	}

	public void setLastLogoutTime(int lastLogoutTime) {
		this.lastLogoutTime = lastLogoutTime;
	}
	
	public int getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(int lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public int getLoginTime(){
		return loginTime;
	}
	public void setLoginTime( int loginTime ){
		this.loginTime = loginTime;
	}
	
	public short getLoginCount() {
		return loginCount;
	}
	public void setLoginCount(short loginCount) {
		this.loginCount = loginCount;
	}
	
	public int getDayLoginCount() {
		return dayLoginCount;
	}
	public void setDayLoginCount( int dayLoginCount ) {
		this.dayLoginCount = dayLoginCount;
	}

	public byte getSex() {
		return sex;
	}

	public void setSex(byte sex) {
		this.sex = sex;
	}

	public boolean isAdult() {
		return isAdult;
	}

	public void setAdult(boolean isAdult) {
		this.isAdult = isAdult;
	}

	
	
	public int getServerID() {
		return serverID;
	}
	
	public void setServerID(int ServerID) {
		this.serverID = ServerID;
	}
	
	/**
	 * 玩家是否在线
	 * @return
	 */
	public boolean isOnline(){
		return ( con != null && con.isOpen() );
	}

	public short getBagCapacity() {
		return bagCapacity;
	}

	public void setBagCapacity(short bagCapacity) {
		this.bagCapacity = bagCapacity;
	}
	
	public short getFriendCapacity(){
		return this.friendCapacity;
	}
	public void setFriendCapacity( short friendCapacity ){
		this.friendCapacity = friendCapacity;
	}

	public HeroManager getHeroManager() {
		if( heroManager == null ){
			heroManager = new HeroManager( this );
		}
		return heroManager;
	}
	
	public TeamManager getTeamManager() {
		if( teamManager == null ){
			teamManager = new TeamManager( this );
		}
		return teamManager;
	}
	
	public EctypeManager getEctypeManager()
	{
		if( ectypeManager == null ){
			ectypeManager = new EctypeManager( this );
		}
		return ectypeManager;
	}
	
	public EliteEctypeCountManager getEliteEctypeCountManager()
	{
		if( eliteEctypeCountManager == null ){
			eliteEctypeCountManager = new EliteEctypeCountManager( this );
		}
		return eliteEctypeCountManager;
	}
	
	public EctypeReconnectManager getEctypeReconnectManager()
	{
		if( ectypeReconnectManager == null ){
			ectypeReconnectManager = new EctypeReconnectManager( this );
		}
		return ectypeReconnectManager;
	}
	
	public FightOfFriendManager getFightOfFriendManager()
	{
		if( fightOfFriendManager == null ){
			fightOfFriendManager = new FightOfFriendManager( this );
		}
		return fightOfFriendManager;
	}
	
	public BattleInfoManager getBattleInfoManager()
	{
		if( battleInfoManager == null ){
			battleInfoManager = new BattleInfoManager( this );
		}
		return battleInfoManager;
	}
	
	public FriendManager getFriendManager() {
		if( friendManager == null ){
			friendManager = new FriendManager( this );
		}
		return friendManager;
	}
	
	public MailManager getMailManager() {
		if( mailManager == null ){
			mailManager = new MailManager( this );
		}
		return mailManager;
	}
	
	public TalentManager getTalentManager() {
		if( talentManager == null ){
			talentManager = new TalentManager( this );
		}
		return talentManager;
	}
	
	public CaptainSkillManager getCaptainSkillManager() {
		if( captainSkillManager == null ){
			captainSkillManager = new CaptainSkillManager( this );
		}
		return captainSkillManager;
	}
	
	public AwardManager getAwardManager() {
		if( awardManager == null ){
			awardManager = new AwardManager( this );
		}
		return awardManager;
	}
	
	public DanGradingManager getDanGradingManager() {
		if( danGradingManager == null ){
			danGradingManager = new DanGradingManager( this );
		}
		return danGradingManager;
	}
	
	public EquipmentManager getEquipmentManager(){
		if( equipmentManager == null ) equipmentManager = new EquipmentManager(this);
		return equipmentManager;
	}
	
	public InvitingFriendsManager getInvitingFriendsManager() {
		if( invitingFriendsManager == null ){
			invitingFriendsManager = new InvitingFriendsManager( this );
		}
		return invitingFriendsManager;
	}
	
	public QualifyingManager getQualifyingManager(){
		if( qualifyingManager == null ){
			qualifyingManager = new QualifyingManager(this);
		}
		return qualifyingManager;
	}
	
	public EctypeStartLevel getEctypeStartLevel(){
		if( ectypeStartLevel == null ){
			ectypeStartLevel = new EctypeStartLevel( this );
		}
		return ectypeStartLevel;
	}
	
	public UserUniqueID getBasisUniqueID(){
		if( basisUniqueID == null ){
			basisUniqueID = new UserUniqueID( this );
		}
		return basisUniqueID;
	}
	
	/**
	 * 切换游戏状态
	 * @param status
	 */
	public void changeGameStatus( GameStatus status ) {
		gameStatus = status;
		
		gameStatus.run( this );
	}
	
	public ErrorCode getEctypeStatus(){
		return ectypeStatus;
	}
	public void setEctypeStatus( ErrorCode status ){
		ectypeStatus = status;
	}
	
	/** 当天  是否第一次登陆 */
	public boolean isFirstLogin() {
		
		if( lastLogoutTime == 0 )
			return true;
		
		boolean b = SystemTimer.updateEveryDay( lastLogoutTime * 1000L );
		if( b ){
			long t = XOTime.refTimeInMillis( 0, 0, 0 );
			if ( drawAwardTime == t ) return false;
			drawAwardTime = t;
		}
		
		return b;
	}

	/** 是否连续登陆  */
	public boolean updateContrillerLogin(){
		
		if( dayLoginCount == -7 ){
			dayLoginCount = 1;
			return true;
		}
		
		long l1 = XOTime.refTimeInMillis( lastLogoutTime * 1000L, 24, 0, 0 );
		long l2 = XOTime.currentTimeMillis() - l1;
		if( l2 < 0 ) return false;
		if( l2 >= 86400000l ){//1天 1*24*60*60*1000
			dayLoginCount 	= 1;
		}else{
			addDayLoginCount();
		}
		return true;
	}
	public void updateContrillerLoginToDay(){
		if( dayLoginCount == -7 ){
			dayLoginCount = 1;
		}else{
			addDayLoginCount();
		}
	}
	public void addDayLoginCount(){
		if( dayLoginCount > 0 ) return ;
		dayLoginCount = Math.abs( dayLoginCount ) + 1;
	}
	
	public boolean isCanRecharge( int i ) {
		return getMonthCardFate()[i-1] <= DefaultCfg.MONTH_CARD_FATE_MAX;
	}
	
	public void addMonthCardFate( int id ) {
		if( getMonthCardFate()[id-1] < 0 ) 
			getMonthCardFate()[id-1] = DefaultCfg.MONTH_CARD_FATE_ONE;
		else
			getMonthCardFate()[id-1] += DefaultCfg.MONTH_CARD_FATE_ONE ;
		// 如果实在买60 的那么看90的是不是-1
		if( getMonthCardFate()[2] < 0 && id == 2 )
			getMonthCardFate()[2] = 0;
		
		UpdateManager.instance.update( this, UpdateType.U_32 );
	}
	
	public boolean handleMonthCardFate( int id ) {
		if( getMonthCardFate()[id-1] <= 0 ) return false;
		return --getMonthCardFate()[id-1] >= 0;
	}
	
	public boolean isMonthCardFate( int id ) {
		return getMonthCardFate()[id-1] > 0;
	}
	
	/**
	 * 设置限购
	 * @param id
	 */
	public void setRestriction( short id ) {
		short x = (short) (id % 10);
		for( int i = 0; i < restriction.size(); i++ ){
			short o = (short) (restriction.get(i) % 10);
			if( x == o ){
				restriction.set( i, id );
				return;
			}
		}
	}
	public void setRestriction( String temp ) {
		
		restriction.clear();
		
		if( temp == null || temp.isEmpty() ) {
			temp = RechargeTempletCfg.getRestrictionToString();
		}
		
		String[] list = temp.split(",");
		
		for( int i = 0; i < list.length; i++ )
			restriction.add( Short.parseShort( list[i] ) );
	}
	public String getRestrictionToString(){
		if( restriction.isEmpty() )
			setRestriction( null );
		StringBuilder content = new StringBuilder();
		for( int i = 0; i < restriction.size(); i++ )
			content.append( restriction.get(i) ).append( "," );
		return content.toString();
	}
	public List<Short> getRestrictionData(){
		if ( restriction.isEmpty() )
			setRestriction( null );
		return restriction;
	}
	public boolean chekCanBuyRestriction( short id ) {
		for( short i : restriction ){
			if( i == id ) return true;
		}
		return false;
	}
	
	
	/**
	 * 构造关键数据的日志文件
	 * @param at			奖励类型
	 * @param change		改动的数值
	 * @param current		改动后的当前数值
	 * @param dwtype		改动函数
	 */
	private void buildLog( AwardType at, int change, int current, DWType dwtype ){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append( getUID() );			//用户ID
		sb.append( "," );
		sb.append( nickName );			//用户名
		sb.append( "," );
		sb.append( at.toNumber() );		//物品类型
		sb.append( "," );
		sb.append( change );			//变化值
		sb.append( "," );
		sb.append( dwtype.ordinal() );	//调用的说明
		
		Logs.log( at.getLogType(), sb.toString() );
	}
	
	@Override
	public String toString() {
		
		String connStr = con == null ? "null" : con.getLocalAddress().toString();
		return "UserInfo[uID=" + uID + ", conn=" + connStr 
				+ ", status=" + status + ", money=" + cash + ", strength="
				+ strength + ", nickName=" + nickName
				+ ", level=" + level + ", createTime=" + UtilBase.secondsToDateStr( createTime )
				+ ", lastLogoutTime=" + UtilBase.secondsToDateStr( lastLogoutTime ) + ", loginCount="
				+ loginCount + ", sex=" + sex + ", isAdult=" + isAdult + "]";
	}
	
	public static void main ( String[] args ) {
		//	long begin = System.nanoTime();
		for( int i = 0; i < 1; i++ ){
			UserInfo user = new UserInfo(null, i);
			user.changeAward( AwardType.CASH, 100, "test" , DWType.MISCELLANEOUS);
		}
		System.out.println( UserInfo.class );
		//System.out.println("用时" + (System.nanoTime() - begin) / 1000000000f + "秒");
		
	}

	/**
	 * 是否含有月卡
	 * @return
	 */
	public int isHaveMonthCard() {
		for( int i = 0; i < getMonthCardFate().length; i++ )
			if( getMonthCardFate()[i] > 0 ) return 1;
		return 0;
	}

	private long recordCTime	= 0;
	private int count 			= 0;

	public volatile boolean isAccord		= false;

	public volatile byte ePropertyType		= -1;
	
	public float strongestAres 				= 0;
	
	public volatile boolean isDrawDragon	= false;
	
	public volatile int numerationToDay		= -1;
	
	public byte christmasTimes				= DefaultCfg.INIT_CHRISTMASTIMES;
	
	public int chatTime						= 0;

	public boolean isGetChristmasDungeonAward = false;

	public float christmasRecharge = 0;

	public boolean christmasRechargeToDay = false;

	public byte[] allBuyTimes = new byte[7];

	public void setAllBuyTimes( String string ) {
		if( string.isEmpty() ) return;
		String[] content = string.split( "," );
		for( int i = 0; i < allBuyTimes.length; i++ ){
			allBuyTimes[i] = Byte.parseByte( content[i] );
		}
	}

	public String getAllBuyTimes(){
		StringBuilder content = new StringBuilder();
		for( int i = 0; i < allBuyTimes.length; i++ )
			content.append( allBuyTimes[i] ).append( "," );
		return content.toString();
	}
	
	public boolean isNitroBoosts( ) {
		if( count == 0 ) return true;
		long curTime 	= SystemTimer.currentTimeMillis() - recordCTime; // 获取战斗时间
		long praTime	= count * (vipLevel > 0 ? 245 : 500); // 实际时间登陆 战斗次数*600毫秒
		boolean isx		= curTime >= praTime;
//		System.out.println( this.getNickName() + " ：战斗时间 ->" + curTime + "  实际时间 ->" + praTime + " 战斗次数 ->" + count + "  平均1次时间：" + ((float)curTime/(float)count) );
		if( !isx )
			Logs.error( this.getNickName() + " ：战斗时间 ->" + curTime + "  实际时间 ->" + praTime + " 战斗次数 ->" + count + "  平均1次时间：" + ((float)curTime/(float)count));
		return isx ;// 前端申请的战斗时间 减去 实际战斗时间
	}
	public void recordCurCTime( int count ) {
		this.count 			= count;
		this.recordCTime 	= SystemTimer.currentTimeMillis();
	}

	public String remoteAddress() {
		if( con == null ) return "000.000.000.000";
		if( !con.isOpen() ) return "000.000.000.000";
		return con.getLocalAddress().getHostName();
	}

	public void setGold( int count ) {
		this.gold = count;
	}

	public void setCash( int cash ) {
		this.cash = cash;
	}

	public float getRechargeMoney() {
		return rechargeMoney;
	}

	public void setRechargeMoney(float rechargeMoney) {
		this.rechargeMoney = rechargeMoney;
	}

	public float getRechargeMoney1() {
		return rechargeMoney1;
	}

	public void setRechargeMoney1(float rechargeMoney1) {
		this.rechargeMoney1 = rechargeMoney1;
	}

	public float getRechargeMoney2() {
		return rechargeMoney2;
	}

	public void setRechargeMoney2(float rechargeMoney2) {
		this.rechargeMoney2 = rechargeMoney2;
	}

	public short getEquipbagCapacity() {
		return EquipbagCapacity;
	}

	public void setEquipbagCapacity(short equipbagCapacity) {
		EquipbagCapacity = equipbagCapacity;
	}

	public short getNewbieGuideID() {
		return newbieGuideID;
	}

	public void setNewbieGuideID(short newbieGuideID) {
		this.newbieGuideID = newbieGuideID;
	}

	public int getRechargeGold() {
		return rechargeGold;
	}

	public void setRechargeGold(int rechargeGold) {
		this.rechargeGold = rechargeGold;
	}

	public byte getPvpMateBuyCount() {
		return pvpMateBuyCount;
	}

	public void setPvpMateBuyCount(byte pvpMateBuyCount) {
		this.pvpMateBuyCount = pvpMateBuyCount;
	}

	public byte getIsHthemeWeek() {
		return isHthemeWeek;
	}

	public void setIsHthemeWeek(byte isHthemeWeek) {
		this.isHthemeWeek = isHthemeWeek;
	}

	public byte getIsCardTo10() {
		return isCardTo10;
	}

	public void setIsCardTo10(byte isCardTo10) {
		this.isCardTo10 = isCardTo10;
	}

	public short[] getMonthCardFate() {
		return monthCardFate;
	}

	public void setMonthCardFate(short[] monthCardFate) {
		this.monthCardFate = monthCardFate;
	}

	public int getTrophyNumer() {
		return trophyNumer;
	}

	public void setTrophyNumer(int trophyNumer) {
		this.trophyNumer = trophyNumer;
	}

	
	
}

