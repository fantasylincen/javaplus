package game.events.all.fight;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import manager.DWType;


import config.activity.ActivityTempletCfg;
import config.mission.EctypeType;
import config.mission.InstanceTemplet;
import config.mission.InstanceTempletCfg;
import config.mission.TollgateTemplet;
import config.mission.TollgateTempletCfg;
import config.skill.accord.SkillTempletCfg;
import user.UserInfo;
import util.ErrorCode;
import util.SystemTimer;
import util.db.DatabaseUtil;
import game.activity.ActivityManager;
import game.activity.DragonBase;
import game.activity.ParticipationBase;
import game.award.AwardType;
import game.award.ectype.EctypeAward;
import game.battle.auto.AutoBattle;
import game.battle.auto.Formation;
import game.battle.auto.ParseBattleSituation;
import game.battle.dbinfo.BattleInfo;
import game.battle.formation.IFormation;
import game.events.EventBase;
import game.events.EventDescrip;
import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;
import game.fighter.FighterBase;
import game.log.Logs;
import game.log.L;

@EventDescrip(desc = "申请战报信息")
public class SendBattleInfoEvents extends EventBase {

	@Override
	public void run(UserInfo user, ByteBuffer buf) throws IOException {
		int fId 		= buf.getInt();
		
		BattleInfo bi 	= user.getBattleInfoManager().getBattleByteData( fId );
		
		if( bi == null ){
			Logs.error( user, "申请战报信息 出错! bi=" + bi );
			return;
		}
		
		EctypeAward readyAwards = user.getEctypeReconnectManager().getReadyAwards();
		if( readyAwards == null )
		{
			Logs.error( user, "申请战报出错 根本没有战报! id=" + fId );
			return;
		}
		if( bi.getTheLv() == 2 ) { Logs.error( user, "申请战报出错  bi.getTheLv() == 2" ); return; }
		// 这里如果是输了那么就要 设置状态  或者到最后一波怪
//		if( !readyAwards.getIsWin() || readyAwards.getTheLv() == 2 ) 
//			user.setEctypeStatus( ErrorCode.UNKNOW_ERROR );
//		
//		ByteBuffer buffer 	= buildEmptyPackage( 10240 );
//		ByteBuffer content 	= bi.getData().asReadOnlyBuffer();
		
		// ---------------测试数据
//		ByteBuffer data 	= bi.getData().asReadOnlyBuffer();
//		byte at				= data.get();
//		if( at == 3 ){
//			byte size 		= data.get();
//			for( int i = 0; i < size; i++ ){
//				data.getInt();
//				data.get();
//			}
//		}
//		
//		byte size 			= data.get();
//		for( int i = 0; i < size; i++ ){
//			int hp			= data.getInt();
//			byte pos		= data.get();
//			System.out.println( "HP=" + hp );
//			System.out.println( "pos=" + pos );
//		}
//		
//		new ParseBattleSituation( data ).parse();
		// ---------------测试数据
		
//		buffer.put( content );
//		sendPackage( user.getCon(), buffer );
		
		startRun( user, bi.getMissionId(), (byte) (bi.getTheLv() + 1), bi.getEctypeId() );
	}
	
	private void startRun( UserInfo user, int missionId, byte p, short ectypeID ) throws IOException{
		
		TollgateTemplet missionTemplet = TollgateTempletCfg.getTempletById( missionId );
		// 获取玩家出战英雄
		List<FighterBase> attackers 	= user.getTeamManager().getReadyToHero();
		if( attackers.isEmpty() ) { Logs.error( "申请战报出错 attackers.isEmpty()" ); return; }
		if( missionTemplet == null ) { Logs.error( "missionTemplet == null" ); return; }
		
		ByteBuffer buffer 		= buildEmptyPackage( 10240 );
		AutoBattle battle	 	= null;
		ByteBuffer content		= null;
		ByteBuffer BD			= ByteBuffer.allocate( 10240 );
		int assistValue			= 0;
		IFormation 				aFormation  = null;
		IFormation 				dFormation	= null ;
		byte trophy 			= 0;
		EctypeAward award 		= user.getEctypeReconnectManager().getReadyAwards();
		FireBoss fireBoss		= new FireBoss(); 
		fireBoss.id				= award == null ? 0 : award.getFireBoss();
		
		// 这里看是否有提升属性
		if ( user.ePropertyType != -1 )
			promoteProperty( attackers, user.ePropertyType );
		
		// 初始化 双方阵型
		aFormation 			= new Formation( attackers, true );
		dFormation			= getDefendFormation( user, ectypeID, missionTemplet, p, fireBoss, BD );
		
		// 计算出是否第一次 进入这个关卡
		boolean isOne		= user.getEctypeManager().isOneEnter( ectypeID, missionId );
		
		// 开始把信息放入 自动回合战斗系统
		battle 				= new AutoBattle( aFormation, dFormation,user.getTalentManager().getTalents(),null,
				user.getDanGradingManager().getInfo().danGrad(), null, isOne );

		// 获取算过 队长技能后的 英雄数据
		List<FighterBase> list_a	= battle.getAttackHeroData();
		BD.put( (byte) list_a.size() );
		for( FighterBase x : list_a ){
			BD.putInt( x.getHpMax() );
			BD.put( x.getPosition() );
		}
		
		// 开始执行战斗 
		battle.run( true );
		boolean isGetThrough 	= false;
		// 攻击方 胜利 -------------
		if( battle.getAttackerIsWin() ){
			isGetThrough 	= p == (missionTemplet.getThelvNum() - 1);
			
			// 结算星级
			if( battle.getCurrentRound() < 4 )
				user.getEctypeReconnectManager().starLevel++;
			
			// 算出友情点  必须在这做 因为 后面会清除协助好友信息
			if( isGetThrough ){
				assistValue 	= user.getTeamManager().getAssistValue();
				// 设置副本状态
				user.setEctypeStatus( ErrorCode.UNKNOW_ERROR );
				// 这里算出奖杯个数
				trophy 			= missionTemplet.getTrophy();
				// 星级 最少都为1
				if( user.getEctypeReconnectManager().starLevel == 0 )
					user.getEctypeReconnectManager().starLevel = 1;
				if( user.getEctypeReconnectManager().starLevel > user.getEctypeStartLevel().get(missionId) )
					user.getEctypeStartLevel().set( missionId, user.getEctypeReconnectManager().starLevel );
			}
		}else{
			user.setEctypeStatus( ErrorCode.UNKNOW_ERROR );
		}
		
		// 这里放入奖杯个数
		battle.getBattleSituation().putAwardTrophy( trophy );
		// 完了后 放入准备奖励
		battle.getBattleSituation().putReadyToAward( award );
		
		// 将战斗信息 发给前端
		ByteBuffer bbs 		= battle.getBattleSituation().getData().asReadOnlyBuffer();
		bbs.flip();
		BD.put( bbs );
		content				= BD.asReadOnlyBuffer();
		content.flip();
		buffer.put( content );
		sendPackage( user.getCon(), buffer );
		
		handle( aFormation, missionTemplet, ectypeID, missionId, p, 
					isGetThrough, user, battle, award, BD, content, 
					assistValue, fireBoss, trophy );
		
	}
	
	// 根据类型 提升属性
	private void promoteProperty( List<FighterBase> attackers, byte ePropertyType ) {
		float odds = (float) (ePropertyType == 1 ? 1.5 : 2);
		for( FighterBase f : attackers ){
			f.setAttack( (int) (f.getAttack() * odds) );
			f.setHpMax( (int) (f.getHpMax() * odds) );
			f.setHp( (int) (f.getHpMax() * odds) );
		}
	}
	
	private IFormation getDefendFormation( UserInfo user, short ectypeID, TollgateTemplet missionTemplet, byte p, FireBoss fireBoss, ByteBuffer bD ) {
		
		InstanceTemplet instanceTemplet = InstanceTempletCfg.getTempletById( ectypeID );
		EctypeType type					= instanceTemplet.getType();
		
		if( type == EctypeType.COMMON || type == EctypeType.ELITE)
		{
			bD.put( (byte)1 );
			return missionTemplet.getFormationCloneByWave( p );
		}else if( type == EctypeType.ACTIVITY ){
			short nId 	= ActivityTempletCfg.getMissionToId( ectypeID );
			nId			= (short) (nId / 100);
			
			switch( nId ){
			case 1:// 小龙
				bD.put( (byte)1 );
				return missionTemplet.getFormationCloneByWave( p );
			case 2:// 大龙
				bD.put( (byte)2 );
				DragonBase dragon	= ActivityManager.getInstance().getDragon();
				bD.putInt( dragon.getHpCur() );
//				Logs.debug( "大龙当前血量 =" + dragon.getHpCur() );
				bD.putInt( dragon.getValue( user.getUID() ) );
//				Logs.debug( "对大龙的累计伤害 =" + dragon.getValue( user.getUID() ) );
				ParticipationBase pb = ActivityManager.getInstance().getDragon().get( user.getUID() );
				if( pb == null ){
					bD.put( (byte) 0 );
					bD.put( (byte) 0 );
				}else{
					bD.put( (byte) pb.getRresurgenceTimes( 1 ) );
					bD.put( (byte) pb.getRresurgenceTimes( 0 ) );
				}
				return missionTemplet.getFormationCloneByWave( dragon );
			case 3:// 试炼
				bD.put( (byte)3 );
				IFormation ifor  	= missionTemplet.getFormationCloneByRefine( p, ectypeID, fireBoss );
				bD.put( (byte)ifor.getAllFighters().size() );
				for( FighterBase f : ifor.getAllFighters() )
				{
					bD.putInt( f.getId() );
					bD.put( f.getPosition() );
				}
				bD.putInt( fireBoss.id );
				return ifor;
			case 4:// 圣诞节
				bD.put( (byte)1 );
				return missionTemplet.getFormationCloneByWave( p );
			}
		}
		
		return null;
	}

	private void handle( IFormation aFormation, TollgateTemplet missionTemplet, short ectypeID, 
			int missionId, byte p, boolean isGetThrough, UserInfo user, AutoBattle battle, 
			EctypeAward award, ByteBuffer BD, ByteBuffer content, int assistValue, FireBoss fireBoss, byte trophy ){
		
		// 处理下 一些数据
//		synchronized ( user ) {
			// 记录日志 只有第一波才开始记录
			if( p == 0 )
				Logs.log( L.L_005,  user.getUID() + "," + user.getNickName() );
			
			// 记录战报信息   不管输赢
			ByteBuffer data 	= ByteBuffer.allocate( content.limit() );
			BD.flip();
			data.put( BD );
			BattleInfo bi 		= new BattleInfo(ectypeID, missionId, p, data, SystemTimer.currentTimeSecond());
			int fightId 		= user.getBattleInfoManager().addBattleInfo( bi );
			
			// 这一波打完 记录数据库  为断线重连 和奖励记录  
			award 				= new EctypeAward( missionId, p, battle.getAwardContent(), fightId, battle.getAttackerIsWin() );
			award.setFireBoss( fireBoss.id ); 
			user.getEctypeReconnectManager().addReadyAward( award );
			
			// 先刷新 绝对死亡
			user.getTeamManager().updataAbsoluteDie();
			
			// 刷新英雄死亡  记录到数据库   不管输赢
			for( FighterBase f : aFormation.getDieFighters() )
				user.getTeamManager().updataDie( f.getPosition() );
			
			// 记录数据库
			user.getTeamManager().updata();
//		}
		
		// 如果通关  就在这下面结算
		if( isGetThrough ){
		
//			synchronized ( user ) {
				
				if( missionTemplet.getType() == EctypeType.COMMON )
					user.getAwardManager().recordC();
				else if( missionTemplet.getType() == EctypeType.ELITE )
					user.getAwardManager().recordE();
				
				// 扣除体力
				user.changeStrength( -missionTemplet.getNeedStrength(), "副本刷完扣除" );
				
				// 结算友情点
				if( assistValue != 0 )
					user.changeAward( AwardType.FD_VALUE, assistValue, "副本通关 获得友情点", DWType.MISCELLANEOUS );
				
				// 结算奖杯数量 
				if( trophy != 0 ) {
					user.changeAward( AwardType.TROPHY, trophy, "副本掉落", DWType.ATLASLOOT );
					UpdateManager.instance.update(user, UpdateType.U_29 );
				}
				
				// 通过后 找出下一个 ID
				user.getEctypeManager().throughEctype( ectypeID, missionId );
				
				// 这里发放 准备 奖励
				user.getEctypeReconnectManager().issueReadyAward( missionId );
				
				// 刷新一下体力
				UpdateManager.instance.update( user, UpdateType.U_2 );
//			}
		}
	}
	
	public static void main( String[] args ){
		
		SkillTempletCfg.init();
//		SystemCfg.IS_DEBUG = true;
//		
//		try {
//			GameServer.readAllCfg();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
		System.out.println( "配置表加载完成." );
		
		BattleInfo bi		= null;
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;			
		ResultSet res			= null;
		
		String sql = "SELECT * from battle_info_base where uname=?";
		
		try {
			pst = con.prepareStatement( sql );
			
			pst.setInt( 1, 332980163 );
			
			res = pst.executeQuery();
				
			if( res.next() ){
				bi = parsingBattle( res );
			}
			
		} catch (SQLException e) {
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		
		ByteBuffer data 	= bi.getData().asReadOnlyBuffer();
		byte at				= data.get();
		if( at == 3 ){
			byte size 		= data.get();
			for( int i = 0; i < size; i++ ){
				data.getInt();
				data.get();
			}
		}
		
		byte size 			= data.get();
		for( int i = 0; i < size; i++ ){
			int hp			= data.getInt();
			byte pos		= data.get();
			System.out.println( "HP=" + hp );
			System.out.println( "pos=" + pos );
		}
		
		new ParseBattleSituation( data ).parse();
		
	}
	// 解析
	private static BattleInfo parsingBattle(ResultSet res) throws SQLException,IOException {
		int uid 			= res.getInt( "u_id" );
		short eid			= res.getShort( "ectype_id" );
		int mid				= res.getInt( "points_id" );
		byte the			= res.getByte( "the_lv" );
		
//			ByteBuffer data 	= ByteBuffer.wrap( InputStreamTOByte( res.getBlob( "data" ).getBinaryStream() ) );
		ByteBuffer data 	= ByteBuffer.wrap( res.getBytes( "data" ) );
		int time			= res.getInt( "timer" );
		
		BattleInfo battle 	= new BattleInfo( eid, mid, the, data, time);
		battle.setUID( uid );
		
		return battle;
	}
}
