package game.award.ectype;

import config.activity.ActivityTempletCfg;
import manager.DWType;
import game.activity.ActivityManager;
import game.award.AwardInfo;
import game.log.Logs;


import user.UserInfo;
import util.ErrorCode;

/**
 * 副本准备奖品中心  这里永远只保存最后一次战斗的数据 为战斗重连做准备
 * 所有数据库  副本战报信息 通过这个调用
 * @author DXF
 * 2013-7-1 下午2:16:21
 */
public class EctypeReconnectManager {
	
	private final UserInfo									user;
	private EctypeAward										readyAwards;
	private final ReconnectDataProvider						db = ReconnectDataProvider.getInstance();;
	
	public byte starLevel							= 0;
	
	public EctypeReconnectManager ( UserInfo user ) {
		this.user 	= user;
		readyAwards = db.get( user, (byte)0 );
	}

	public EctypeAward getReadyAwards() {
		return readyAwards;
	}

	public void setReadyAwards(EctypeAward awards ) {
		this.readyAwards = awards;
	}
	
	
	/**
	 * 准备奖品  这里是副本怪物掉落后  先把奖品记录到数据库  并不给玩家
	 * @param list
	 */
	public void addReadyAward( EctypeAward award ) 
	{
		if( readyAwards != null ){
			readyAwards.setFightId( award.getFightId() );
			readyAwards.setTheLv( award.getTheLv() );
			readyAwards.setIsWin( award.getIsWin() );
			readyAwards.setFireBoss( award.getFireBoss() );
			readyAwards.getContent().addAll( award.getContent() );
			db.upData( user , readyAwards );
		}else{
			readyAwards = award;
			
			if( db.get( user, (byte)1 ) == null )
				db.add( user, readyAwards );
			else
				db.upData( user, readyAwards );
		}
	}
	/**
	 * 添加失败
	 * @param award
	 */
	public void addFailure( EctypeAward award )
	{
		if( readyAwards != null ){
			readyAwards.setFightId( award.getFightId() );
			readyAwards.setTheLv( award.getTheLv() );
			readyAwards.getContent().addAll( award.getContent() );
			db.upData( user , readyAwards );
		}else{
			readyAwards = award;
			
			if( db.get( user, (byte)1 ) == null )
				db.add( user , readyAwards );
			else
				db.upData( user, readyAwards );
		}
	}
	
	/**
	 * 发放 准备 奖励  主要是副本关卡的奖励
	 * @param missionId  
	 */
	public void issueReadyAward( int missionId ) 
	{
		if( readyAwards == null ) return;
		
		if( readyAwards.getId() != missionId ) 
		{
			Logs.error( "奖励失败  readyAwards.getId() != missionId " );
			return;
		}
		
		// 发送准备奖励
		for( AwardInfo award : readyAwards.getContent() ){
			user.changeAward( award, "副本掉落 奖励", DWType.ATLASLOOT );
		}
		
		readyAwards.setContent( null );
		
		// 这里刷新一下 以防万一 
		db.upData( user, readyAwards );
	}
	
	/** 删除记录 */
	public void remove()
	{
		// 这里要将 团队里面所有英雄 复活
		user.getTeamManager().updataLive();

		if( readyAwards == null )
			return;
		db.remove(  user , readyAwards  );
		readyAwards = null;
	}

	/**
	 * 进入副本的时候   检查是否合法
	 * @param ectypeID
	 * @param missionId 
	 * @param p
	 * @return
	 */
		
	public ErrorCode isCheckLegal(short ectypeID, int missionId, byte p) {
		
		// 这里如果是大龙
		short nId 	= ActivityTempletCfg.getMissionToId( ectypeID );
		nId			= (short) (nId / 100);
		if( nId == 2 ){
			readyAwards = null;
			if( ActivityManager.getInstance().getDragon().isDie() ) {
				Logs.error( user , " 大龙已死亡 不能继续挑战!" );
				return ErrorCode.UNKNOW_ERROR;
			}
			return ErrorCode.SUCCESS;
		}
					
		if( readyAwards == null ){
			if( p != 0 ){
				Logs.error( user , "进入副本失败  你还不能挑战这一波 p=" + p );
				return ErrorCode.UNKNOW_ERROR;
			}
		}else{
			
			if( missionId != readyAwards.getId() ){
				Logs.error( user , "当前关卡=" + readyAwards.getId() + "还没打完   不能打关卡ID=" + missionId );
				return ErrorCode.UNKNOW_ERROR;
			}
			
			
			if( !readyAwards.getIsWin() ){
				Logs.error( user , "这个副本你已经失败不能继续挑战  关卡ID=" + missionId );
				return ErrorCode.ECTYPE_NOT_WIN; 
			}
			
			if( p != (readyAwards.getTheLv() + 1) ){
				Logs.error( user , "波数错误  p=" + p );
				return ErrorCode.ECTYPE_NOT_THE; 
			}
		}
		
		return ErrorCode.SUCCESS;
	}
	
	public static void main ( String[] args ) {
		
	}

}
