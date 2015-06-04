package game.activity;

import game.award.AwardInfo;
import game.award.AwardType;
import game.battle.auto.AutoBattle;
import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;
import game.mail.MailBase;
import game.mail.MailType;
import game.util.DragonAwardManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import notice.NoticeManager;

import datalogging.ConsumelogF;
import datalogging.DataLogDataProvider;
import define.DefaultCfg;

import lombok.Getter;
import lombok.Setter;
import lua.Lua;
import lua.LuaProxy;
import manager.DWType;

import user.UserInfo;
import user.UserManager;
import util.ErrorCode;
import util.SystemTimer;
import util.UtilBase;

/**
 * 大龙基础类
 * @author DXF
 */
public class DragonBase {
	
	// 大龙初始血量 
	@Setter
	private int 						_hpBase				= 0;
	
	// 当前血量
	@Setter
	private int 						_hpCur				= 0;

	// 大龙初始攻击
	@Setter
	private int 						_attackBase			= 0;
	
	
	private boolean 					_isOpen				= true;
	
	//参与活动的玩家
	private List<ParticipationBase>	_participations 		= new ArrayList<ParticipationBase>();
	@Getter
	private volatile boolean 			isHandle			= false;
	
	// 前十名玩家
//	private List<ParticipationBase> theTopTens				= new ArrayList<ParticipationBase>();
	
	public DragonBase(){
		
	}
	
	
	public void init(){
		_hpBase		= DefaultCfg.getDragonInitHp();
		_hpCur		= _hpBase;
		_attackBase = DefaultCfg.getDragonInitAttack();
//		_hpBase		= 100000;
//		_hpCur		= _hpBase;
//		_attackBase = 2;
		isHandle	= false;
		_participations.clear();
//		theTopTens.clear();
	}
	
	public boolean isOpen() {
		return _isOpen;
	}
	public void isOpen( boolean isOpen ) {
		this._isOpen = isOpen;
		if( _isOpen ) {
			init();
			// 发送公告
			NoticeManager.getInstance().addTimely( 104 );
		}
	}
	
	public int getHpMax(){
		return _hpBase;
	}
	
	public int getHpCur(){
		return _hpCur;
	}
	public void reliefHp(int accumulativeDamage) {
		_hpCur -= accumulativeDamage;
		if( _hpCur < 0 ) _hpCur = 0;
	}
	
	public int getAttack(){
		return _attackBase;
	}
	
	public List<ParticipationBase> getParticipaton(){
		return _participations;
	}

	public List<ParticipationBase> getTheTopTens(){
		Collections.sort( _participations, comparator );
//		List<ParticipationBase> xx = new ArrayList<ParticipationBase>();
//		if( !_participations.isEmpty() && !isHandle ){
//			for( int i = 0; i < 10; i++ ){
//				if( i >= _participations.size() ) break;
//				xx.add( _participations.get(i) );
//			}
//		}else
//			return theTopTens;
		return _participations;
	}

	// 获得名次
	public int[] getRanking( int uid ){
		int size = _participations.size();
		for( int i = 0; i < size; i++ ){
			ParticipationBase p = _participations.get(i);
			if( p.getUID() == uid )
			{
				int data[] 	= new int[2];
				data[0]		= i;
				data[1]		= p.getValue();
				return data;
			}
		}
		return new int[]{-1,0};
	}
	
	public ParticipationBase get( int uid ){
		for( ParticipationBase p : _participations ){
			if( p.getUID() == uid )
				return p;
		}
		return null;
	}
	
	
	public void add( int uid ){
		
//		if( get(uid) != null ) return;
		
		ParticipationBase p = new ParticipationBase( uid );
		_participations.add( p );
	}
	
	/**
	 * 获得玩家挑战次数
	 * @param uid
	 * @return
	 */
	public byte getCount( int uid ) {
		ParticipationBase p = get( uid );
		if( p != null ) return p.getCount();
		return 0;
	}
	
	/**
	 * 获取对大龙累计造成的伤害
	 * @param uid
	 * @return
	 */
	public int getValue( int uid ){
		ParticipationBase p = get( uid );
		if( p == null ) return 0;
		return p.getValue();
	}

	public boolean isDie() {
		return _hpCur <= 0;
	}

	/**
	 * 大龙死亡处理
	 */
	public void dieHandle() {
		
		if( _participations.isEmpty() ) return;
		if( isHandle ) return;
		
		isHandle			= true;
		List<String> name	= new ArrayList<String>();

		// 这里给所有参加大龙的玩家发放奖励
		// 先排序
		Collections.sort( _participations, comparator );
		
		// 将前十名玩家 记录下
//		theTopTens.clear();
//		for( int i = 0; i < 10 && i < _participations.size(); i++ )
//			theTopTens.add( _participations.get(i) );
		
		int size = _participations.size();
		for( int i = 0; i < size; i++ ){
			ParticipationBase p = _participations.get( i );
			UserInfo u 			= UserManager.getInstance().getByName( p.getUID() );
			if( u == null ) continue;
			if( u.isDrawDragon ) continue;
			
//			synchronized ( u ) {
				
				u.isDrawDragon 		= true;
				int gold 			= 0;
				int cash			= 0;
				int index 			= i+1;
				List<AwardInfo> axx	= DragonAwardManager.get( index );
				for( AwardInfo a :  axx ){
					if( a.getAward() == AwardType.GOLD ) gold = a.getNumber();
					if( a.getAward() == AwardType.CASH ) cash = a.getNumber();
				}
				
//				MessageTemplet message		= null;
//				String content				= "";
				MailBase mail				= null;
				
				// 下面发送公告
				if( index <= 50 ){
					mail					= new MailBase( NoticeManager.noticeName, MailType.SYSTEM_NOTICE, 204 + "|" + UtilBase.secondsToDate(SystemTimer.currentTimeSecond(), "yyyy.MM.dd" )
							 + "," + index + "," + gold + "," + cash + "|" + UtilBase.secondsToDate( SystemTimer.currentTimeSecond(), "yyyy.MM.dd" ) );
				}else{
					mail					= new MailBase( NoticeManager.noticeName, MailType.SYSTEM_NOTICE, 205 + "|" + UtilBase.secondsToDate(SystemTimer.currentTimeSecond(), "yyyy.MM.dd" )
							 + "," + gold + "," + cash + "|" + UtilBase.secondsToDate( SystemTimer.currentTimeSecond(), "yyyy.MM.dd" ) );
				}
				
				u.getMailManager().addMail( mail );
				
				// 以邮件的形式发放
				for( AwardInfo a : axx ){
					mail = new MailBase( a, MailType.SYSTEM_PRESENT );
					u.getMailManager().addMail( mail );
				}
				// 记录日志
				DataLogDataProvider.getInstance().add( u, ConsumelogF.DRAGON_LIST, gold );
			
				// 记录前3名玩家
				if( index <= 3 ) name.add( u.getNickName() );
//			}
		}
		
		// 必须清空
//		_participations.clear();
		
		// 发送滚动公告
		String notice 	= "";
		for( String s : name ){
			if( !notice.isEmpty() ) notice += ",";
			notice 		+= "[" + s + "]";
		}
		if( !notice.isEmpty() )
			NoticeManager.getInstance().addTimely( 206, UtilBase.nToPlainText( notice ) );
	}

    /** 按照位置从低到高进行排序，否则计算被攻击战士的时候可能出错 */
    private static final Comparator<ParticipationBase> comparator = new Comparator<ParticipationBase>(){
        @Override
        public int compare( ParticipationBase p1, ParticipationBase p2 ) {
            return p2.getValue() - p1.getValue();
        }
    };
    
	/**
	 * 对玩家的处理
	 * @param uid
	 * @param battle
	 */
	public void setUserData( int uid, AutoBattle battle ) {
		
		ParticipationBase p = get( uid );
		if( p == null ) return;
		
		p.setValue( battle.getAccumulativeDamage() );
		p.setIsDie( battle.attackIsAllDie() );
		
	}


	public boolean check( UserInfo user ) {
		
		ParticipationBase p = get( user.getUID() );
		
		if( p == null ) return true;
		
		return p.isEnter(); // 是否可以进入
	}

	/**
	 * 复活 
	 * @param user
	 * @param type
	 * @return
	 */
	public ErrorCode resurgence( UserInfo user, byte type ) {
		
		// 先判断大龙是否死亡
		if( isDie() )
			return ErrorCode.CHALLENGE_THE_DRAGON_DIE;
		if( !_isOpen )
			return ErrorCode.CHALLENGE_THE_DRAGON_DIE;
		
		ParticipationBase p = get( user.getUID() );
		
		if( p == null ) 
			return ErrorCode.UNKNOW_ERROR;
		
		switch( type ){
		
//		·金币复活：消耗金币=2W*（已金币复活次数+1），最多复活5次，5次之后按钮不可点击							
//		·水晶复活：消耗水晶=20*（已水晶复活次数+1），达到100/次后，不再继续增加							
		case 0:// 时间到了
		{
			if( p.getResidueTime() > 0 )
				return ErrorCode.CHALLENGE_THE_DRAGON_RESURGENCE;
		}
		break;
		case 1:// 水晶复活
		{
//			int need = 20 * (p.getRresurgenceTimes( 0 ) + 1);
//			int need = 10;
			LuaProxy lua 	= Lua.createLuaState( "gameData.lua" );
			int need		= lua.retInteger( "getDragonResurgenceNeedCrystal" , p.getRresurgenceTimes( 0 ) );
			lua.close();
			if( user.changeAward( AwardType.GOLD, -need, "挑战大龙 复活队友", DWType.MISCELLANEOUS ) != -1 ){
				p.resurgence( );
				p.jiluRresurgenceTimes( 0 );
			}
			UpdateManager.instance.update( user, UpdateType.U_4 );
		}
		break;
		case 2:// 金币复活
		{
//			int need = 20000 * (p.getRresurgenceTimes( 1 ) + 1);
//			int need = 10000;
			LuaProxy lua 	= Lua.createLuaState( "gameData.lua" );
			int need		= lua.retInteger( "getDragonResurgenceNeedCash" , p.getRresurgenceTimes( 1 ) );
			lua.close();
			if( user.changeAward( AwardType.CASH, -need, "挑战大龙 复活队友", DWType.MISCELLANEOUS ) != -1 ){
				p.resurgence( );
				p.jiluRresurgenceTimes( 1 );
			}
			UpdateManager.instance.update( user, UpdateType.U_3 );
		}
		break;
		}
		
		return ErrorCode.SUCCESS;
	}

	/**
	 * 查看玩家是否 已经鼓舞
	 * @param user
	 * @return
	 */
	public boolean isHaveInspire( UserInfo user ) {
		
		ParticipationBase p = get( user.getUID() );
		
		if( p == null ) return false;
			
		return p.isInspire();
	}

	/**
	 * 开启玩家鼓舞
	 * @param user
	 */
	public void startInspire( UserInfo user ) {
		
		ParticipationBase p = get( user.getUID() );
		
		if( p == null ) {
			p = new ParticipationBase( user.getUID() );
			_participations.add( p );
		}
		
		p.stratInspire();
	}


	public void setUserList( String string ) {
		if( string == null || string.isEmpty() ) return;
		
		String content[] 	= string.split( "\\|" );
		for( int i = 0; i < content.length; i++ ){
			
			String s[]		= content[i].split( "," );
			
			ParticipationBase p = new ParticipationBase( Integer.parseInt( s[0] ) );
			p.setValue( Integer.parseInt( s[1] ) );
			p.setTime( Integer.parseInt( s[2] ) );
			p.setIsDie( Integer.parseInt( s[3] ) == 1 );
			_participations.add( p );
		}
	}


	public String getUserList() {
		
		StringBuilder content = new StringBuilder();
		
		for( ParticipationBase p : _participations ){
			
			content.append( p.getUID() ).append( "," );
			content.append( p.getValue() ).append( "," );
			content.append( p.getTime() ).append( "," );
			content.append( p.isDie() ? 1 : 0 ).append( "|" );
		}
		
		return content.toString();
	}


	/**
	 * 关闭鼓舞
	 * @param uid
	 */
	public void closeInspire( int uid ) {
		
		if( _participations.isEmpty() ) return;
		
		ParticipationBase p = get( uid );
		if( p != null )
			p.closeInspire();
	}

	/** 是否进入战斗 */
	public boolean enterCombat( UserInfo user ) {
		ParticipationBase p = get( user.getUID() );
		if( p == null ){
			p = new ParticipationBase( user.getUID() );
			_participations.add( p );
		}
		if( p.isEnterCombat() ) return true;
		p.setEnterCombat( true );
		user.isDrawDragon = false;
		return false;
	}

}
