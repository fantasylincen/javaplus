package game.pvp;

import game.award.AwardInfo;
import game.award.AwardType;
import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;

import java.nio.ByteBuffer;

import manager.DWType;

import config.dangrading.LevelPromoteRuleTemplet;
import config.dangrading.LevelPromoteRuleTempletCfg;
import config.dangrading.MateAwardTemplet;
import config.dangrading.MateAwardTempletCfg;
import config.loot.LootScaleTempletCfg;

import user.UserInfo;
import util.ErrorCode;
import util.SystemTimer;

/**
 * 段位管理中心
 * @author DXF
 */
public class DanGradingManager {

	private UserInfo									user;
	private final DanGradingProvider 					db 						= DanGradingProvider.getInstance();	
	
	private DanGradingBase								_nDanGradingInfo;
	
	// 录像记录
	private VideoRecording								_nVideoRecording		= new VideoRecording();
	
	
	public DanGradingManager( UserInfo user ) {
		super();
		this.user 			= user;
		_nDanGradingInfo	= db.get( user );
		if( _nDanGradingInfo == null ){
			_nDanGradingInfo = new DanGradingBase();
			_nDanGradingInfo.init( user.getVipLevel() );
			db.add( user, _nDanGradingInfo );
		}
		
		_nVideoRecording	= db.getV( user );
		
//		_nVideoRecording.sorting();
	}
	
	public DanGradingBase getInfo(){
		return _nDanGradingInfo;
	}
	public VideoRecording getVideoRecording(){
		return _nVideoRecording;
	}

	/**
	 * 加入 失败战报 这里说明有人挑战了你
	 * @param a_winInfo
	 * @param d_winInfo 
	 * @param data
	 * @param lootCash 抢夺金币
	 * @param isWin 是否胜利
	 * @param isRevenge 
	 * @param isOfRevenge 是否使用复仇之心
	 */
	public void addBattlelog( PvpMateInfo a_winInfo, PvpMateInfo d_winInfo, ByteBuffer data, 
			int lootCash, boolean isWin, boolean isRevenge, boolean isOfRevenge ) {
		
		int t			= SystemTimer.currentTimeSecond();
		int id			= -1;
		VideoBase base 	= null;
		
//		data.flip();
		if( _nVideoRecording.isMax() ){
			base 			= _nVideoRecording.getLast();
			base.mData		= data;
			base.mTouserID	= a_winInfo.getUser().getUID();
			base.setTime( t );
			base.setAList( a_winInfo.getList() );
			base.setDList( d_winInfo.getList() );
			base.mLootCash	= lootCash;
			base.mIsWin		= isWin;
			base.mIsComplex = isOfRevenge;
			base.mIsRevenge = isRevenge;
			db.updateV( user, base );
		}else{
			
			id 				= db.addV( user, a_winInfo, d_winInfo, data, t, lootCash, isWin, isRevenge, isOfRevenge );
			if( id == - 1 ) return;
			base			= new VideoBase( id, a_winInfo.getUser().getUID(), data );
			base.setTime( t );
			base.setAList( a_winInfo.getList() );
			base.setDList( d_winInfo.getList() );
			base.mLootCash	= lootCash;
			base.mIsWin		= isWin;
			base.mIsComplex = isOfRevenge;
			base.mIsRevenge = isRevenge;
		}
		
		_nVideoRecording.put( base ) ;
		
		// 提示前端有新的战报
		UpdateManager.instance.update( user, UpdateType.U_24 );
	}
	
	/**
	 * 删除战报
	 * @param battlelogID
	 */
	public void removeBattlelog( int battlelogID ) {
		
		_nVideoRecording.remove( battlelogID );
		db.removeV( user, battlelogID );
	}
	
	/**
	 * 根据战报ID 获取战报信息
	 * @param id
	 * @return
	 */
	public VideoBase get( int id ) {
		return _nVideoRecording.get(id);
	}
	
	public int lootByCash( int battlelogID ) {
		
		VideoBase v = get( battlelogID );
		if( v == null ) return 0;
		
		return v.mLootCash / 2;
	}
	
	/**
	 * 结算分数和连胜及其他
	 * @param isWin
	 * @param type  
	 */
	public void handle( boolean isWin, MatchingType type ) {
		
		// 设置数据
		_nDanGradingInfo.setData( isWin, type );
		
		UpdateManager.instance.update( user, UpdateType.U_13 );
		UpdateManager.instance.update( user, UpdateType.U_12 );
		UpdateManager.instance.update( user, UpdateType.U_9 );
		
		// 更新提示
		runReminderL();
		
		// 刷新数据库
		updata();
	}
	
	
	/**
	 * 将段位信息 装入ByteBuffer
	 * @param response
	 */
	public void putData( ByteBuffer response ) {
		
		if( _nDanGradingInfo == null ) return;
		
		response.putInt( _nDanGradingInfo.standings() );
		response.putShort( _nDanGradingInfo.maxVictory() );
		response.putShort( _nDanGradingInfo.maxFailure() );
	}
	
	/** 刷新所有数据到数据库  */
	public void updata(){
		db.updata( user, _nDanGradingInfo );
	}
	
	/** 刷新时间  */
	public void updataTime() {
		_nDanGradingInfo.initToDay();
	}

	/**
	 * 提升段位 LevelPromoteRule
	 * @return
	 */
	public ErrorCode startUpgrade() {
		
		DanGrad next		= DanGrad.fromNumber( _nDanGradingInfo.danGrad().toNumber() + 1 );
		
		if( next == null )// 如果为空 那么说明已经满了
			return ErrorCode.DANGRAD_UPGRADE_YET_MAX;
		
		// 先检测是否可以提升
		ErrorCode code 		= detectionIsupgrade( next );
		
		if( code == ErrorCode.SUCCESS ){
			_nDanGradingInfo.danGrad( next );
			UpdateManager.instance.update( user, UpdateType.U_8 );
			user.getHeroManager().updataPropertyToDangrad();
		}
		
		return code;
	}
	private ErrorCode detectionIsupgrade( DanGrad next ) {
		
		LevelPromoteRuleTemplet l 	= LevelPromoteRuleTempletCfg.get( next ) ;
		
		// 判断积分是否足够
		if( _nDanGradingInfo.grade() < l.getGradeConditions() ) 
			return ErrorCode.DANGRAD_UPGRADE_NOT_GRADE;
		
		// 判断金币是否足够
		if( user.changeAward( AwardType.CASH, -l.getGoldConditions(), "提升段位消耗", DWType.MISCELLANEOUS ) == -1 )
			return ErrorCode.USER_CASH_NOT_ENOUTH;
		
		// 增加体力上限
		short curStr				= l.getBoonStrlimit();
		l							= LevelPromoteRuleTempletCfg.get( _nDanGradingInfo.danGrad() ) ;
		curStr						= (short) (l.getBoonStrlimit() - curStr) ;
		curStr						= curStr < 0 ? 0 : curStr;
		user.setBagCapacity( (short) (user.getBagCapacity() + curStr ) );
		UpdateManager.instance.update( user, UpdateType.U_10 );
		
		return ErrorCode.SUCCESS;
	}
	

	/**
	 * 看今日是否还可以匹配 
	 * @return
	 */
	public ErrorCode isCanMate() {
		
		if( _nDanGradingInfo.todayCount() <= 0 )
			return ErrorCode.DANGRAD_MATE_NOT_COUNT;
		
		return ErrorCode.SUCCESS;
	}

	/**
	 * 领取奖励
	 * @param id
	 * @return
	 */
	public ErrorCode GetTheRewards( byte id ) {
		
		MateAwardTemplet m 		= MateAwardTempletCfg.get(id);
		// 先看 是否符合条件
		if( _nDanGradingInfo.todayMateCount() < m.getCondition() )
			return ErrorCode.DANGRAD_MATEAWARD_NOT_COUNT;
		
		// 看是否已经领取了
		if( _nDanGradingInfo.isCanGetTheRewards( id ) )
			return ErrorCode.DANGRAD_MATEAWARD_HAVE_RECEIVE;
		
		// 发放奖励
		for( AwardInfo a : m.getAward() ){
			user.changeAward( a, "匹配领取奖励", DWType.MISCELLANEOUS );
		}
		
		// 记录领取了的奖励
		_nDanGradingInfo.putTheRewards( id );
		
		// 更新提示
		runReminderA();
		
		return ErrorCode.SUCCESS;
	}

	/**
	 * 领取每日福利
	 * @return
	 */
	public ErrorCode getWelfare() {
		
		// 先判断今日是否已经领取
		if( _nDanGradingInfo.isGetWelfare() )
			return ErrorCode.DANGRAD_MATE_HAVE_RECEIVE;
		
		LevelPromoteRuleTemplet l 	= LevelPromoteRuleTempletCfg.get( _nDanGradingInfo.danGrad() ) ;
		
		// 发放奖励
		user.changeAward( AwardType.GOLD, l.getBoonCrystal(), "匹配每日领取福利", DWType.MISCELLANEOUS );
		
		// 设置是否领取
		_nDanGradingInfo.isGetWelfare( true );
		
		// 刷新前端
		UpdateManager.instance.update( user, UpdateType.U_4 );
		
		return ErrorCode.SUCCESS;
	}

	/**
	 * 判断是否还可以被抢夺
	 * @return
	 */
	private boolean isByLoot() {
		return _nDanGradingInfo.isLoot();
	}

	/**
	 * 抢夺金币
	 * @param isRevenge  
	 * @return
	 */
	public int lootByCash(  ) {
		
		if( !isByLoot() ) return 0;
		
		// 开始执行抢夺
		_nDanGradingInfo.executeLoot();
		
		return LootScaleTempletCfg.lootToCash( user.getCash() );
	}

	/**
	 * 是否还可以购买匹配次数
	 * @return
	 */
	public boolean isBuyMateCount() {
		return _nDanGradingInfo.buyCount() < user.getPvpMateBuyCount() ;
	}

	/** 结算购买次数 
	 * @param changeValue */
	public void buyMateCount() {
		// 加加
		_nDanGradingInfo.reliefBuyCount(  );
		
	}

	private byte reminder_a 	= 0;
	/**
	 * 提示前端  奖励
	 * @return
	 */
	public void runReminderA() {
		byte temp 			= 0;
		for( MateAwardTemplet m : MateAwardTempletCfg.getLits() ) {
			if( _nDanGradingInfo.todayMateCount() >= m.getCondition() && !_nDanGradingInfo.isCanGetTheRewards( m.getId() ) ){
				temp		= 1;
				break;
			}
		}
		
		if( temp != reminder_a ){
			reminder_a = temp;
			UpdateManager.instance.update( user, UpdateType.U_22 );
		}
	}
	public byte reminder_a(){
		return reminder_a;
	}
	public void reminder_a(byte b) {
		this.reminder_a = b;
	}
	
	private byte reminder_l		= 0;
	/**
	 * 提示前端 段位
	 * @return
	 */
	public void runReminderL() {
		DanGrad next				= DanGrad.fromNumber( _nDanGradingInfo.danGrad().toNumber() + 1 );
		if( next == null ) 
			return ;
		LevelPromoteRuleTemplet l 	= LevelPromoteRuleTempletCfg.get( next ) ;
		byte temp 					= 0;
		// 判断积分是否足够
		if( _nDanGradingInfo.grade() >= l.getGradeConditions() ) 
			temp					= 1;
		
		if( reminder_l != temp ){
			reminder_l = temp;
			UpdateManager.instance.update( user, UpdateType.U_23 );
		}
	}
	public byte reminder_l(){
		return reminder_l;
	}
	public void reminder_l(byte b) {
		reminder_l = b;
	}


}
