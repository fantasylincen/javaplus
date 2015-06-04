package game.events.all.fight;

import game.activity.ActivityManager;
import game.activity.DragonBase;
import game.activity.ParticipationBase;
import game.award.AwardType;
import game.award.ectype.EctypeAward;
import game.battle.auto.AutoBattle;
import game.battle.auto.Formation;
import game.battle.dbinfo.BattleInfo;
import game.battle.formation.IFormation;
import game.events.EventBase;
import game.events.EventDescrip;
import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;
import game.fighter.FighterBase;
import game.log.Logs;
import game.log.L;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

import manager.DWType;

import config.activity.ActivityTempletCfg;
import config.mission.EctypeType;
import config.mission.InstanceTemplet;
import config.mission.InstanceTempletCfg;
import config.mission.TollgateTemplet;
import config.mission.TollgateTempletCfg;
import define.GameData;
import deng.xxoo.utils.XORandom;

import user.UserInfo;
import user.UserManager;
import util.ErrorCode;
import util.SystemTimer;

/**
 * 申请战斗
 * @author DXF
 * 2013-6-18 下午6:17:37
 */
@EventDescrip(desc = "申请战斗信息")
public class SendBattleSituation extends EventBase {

	
	@Override
	public  void run( UserInfo user, ByteBuffer buf ) throws IOException {
//		synchronized( user ){
			
			TollgateTemplet 	missionTemplet	= null;
			FireBoss fireBoss		= new FireBoss(); // 试炼Boss
			// 是否通关
			boolean isGetThrough 	= false;
			
//			long begin				= System.nanoTime();
			
			short ectypeID			= buf.getShort();
			int missionId 			= buf.getInt();
			byte p 					= buf.get();
			
			ErrorCode code			= user.getEctypeStatus();
			
			short dragonId			= 0;
			DragonBase dragon		= ActivityManager.getInstance().getDragon();
			do{
				
				// 大龙单独处理
				dragonId			= ActivityTempletCfg.getMissionToId( ectypeID );
				dragonId			= (short) (dragonId / 100);
				if( dragonId == 2 ){
					if( dragon.check( user ) ){
						code		= ErrorCode.UNKNOW_ERROR;
						break;
					}
				}else{
					
					if( code != ErrorCode.FIGHTER_STATUS ) break;
				}
				
				// 切换阵型
				if( !user.getTeamManager().changePosition( buf ) ){
					code				= ErrorCode.UNKNOW_ERROR;
					break;
				}
				missionTemplet			= TollgateTempletCfg.getTempletById( missionId );
				// ID检查
				if( checkID( user, missionTemplet, ectypeID, missionId, p ) ){
					code				= ErrorCode.UNKNOW_ERROR;
					break;
				}
			
				// 检查是否合法
				code 					= user.getEctypeReconnectManager().isCheckLegal( ectypeID, missionId, p );
			
			} while( false );
			
			// 获取玩家出战英雄
			List<FighterBase> attackers 	= user.getTeamManager().getReadyToHero();
			if( attackers.isEmpty() ) code 	= ErrorCode.USER_NOT_READY_HERO;
			if( missionTemplet == null ) code 	= ErrorCode.UNKNOW_ERROR;
			
			ByteBuffer buffer 		= buildEmptyPackage( 10240 );
			buffer.put( p );
			buffer.putShort( (short)code.ordinal() );
			
			AutoBattle battle	 	= null;
			EctypeAward award	 	= null;
			ByteBuffer content		= null;
			ByteBuffer BD			= ByteBuffer.allocate( 10240 );
			int assistValue			= 0;
			IFormation 				aFormation  = null;
			IFormation 				dFormation	= null ;
			byte trophy 			= 0;
			
			if( code == ErrorCode.SUCCESS ){
				
				award 				= user.getEctypeReconnectManager().getReadyAwards();
				fireBoss.id			= award == null ? 0 : award.getFireBoss();
				
				/** 在战斗前做出 数值调整  */
				for( FighterBase f : attackers ){
					// 如果是大龙  这里要看是否开启了 鼓舞
					if( dragonId == 2 && dragon.isHaveInspire( user ) )
						f.setAttack( f.getAttack() * 2 );
					
					// 这里看是否有提升属性 精英副本
					if( user.ePropertyType != -1 )
						promoteProperty( f, user.ePropertyType );
					
					// 这里看羁绊
					f.runFetter( attackers );
					
					// 测试用
//					f.setAttack( f.getAttack() * 20 );
				}
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
				// 是否使用加速
				if( !user.isNitroBoosts() ){ 
					code = ErrorCode.UNKNOW_ERROR;
					buffer.putShort( 1, (short) code.ordinal() );
					// 这里就简单的 清空就行了
					user.getEctypeReconnectManager().remove();
					// 这里把 协助好友 清除掉
					user.getTeamManager().claerAssist();
					user.getTeamManager().updata();
					user.setEctypeStatus( ErrorCode.UNKNOW_ERROR );
				} else{
					user.recordCurCTime( battle.battleCount() );// 记录当前挑战时间
					
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
							
							// 这里特殊判断 是否圣诞活动副本
							if( dragonId == 4 )
								handleChristmasDungeonAward( user, award );
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
//				}
				
				// 打印信息
//				if( SystemCfg.IS_DEBUG ){
//					aFormation 			= new Formation( attackers, true );
//					dFormation 			= missionTemplet.getFormationCloneByWave( p );
//					System.out.println( "进入战斗： 关卡ID=" + missionId + "  波数=" + p );
//					new ParseBattleSituation( aFormation, dFormation,battle.getBattleSituation() ).parse();
//					System.out.println("战斗逻辑耗时：" + (System.nanoTime() - begin) / 1000000000f + "秒");
//				}
			}
			
			sendPackage( user.getCon(), buffer );
			
			if( code == ErrorCode.SUCCESS ){
				short nId 	= ActivityTempletCfg.getMissionToId( ectypeID );
				nId			= (short) (nId / 100);
				//这里大龙 单独处理
				if( nId == 2 )
					dragonHandle( user, battle );
				else
					handle( aFormation, missionTemplet, ectypeID, missionId, p, 
							isGetThrough, user, battle, award, BD, content, 
							assistValue, fireBoss, trophy );
			}
		}
	}
	
	// 处理圣诞活动副本 奖励
	//	掉落几率：
	//	8次中只能获得一次  例：玩家前7次副本没有获得，那第8次必定获得
	//	例：玩家第2次副本获得，则后续6次每次副本获得几率为0
	// 
	private void handleChristmasDungeonAward( UserInfo user, EctypeAward award ) {
		// 如果已经得到了 那么就不能再得了
		if( user.isGetChristmasDungeonAward || award == null ) return;
		if( user.christmasTimes <= 1 ){// 如果是最后一次 那么直接获得
			
			user.isGetChristmasDungeonAward = true;
			award.getContent().add( GameData.getChristmasDungeonAward() );
//			Logs.debug( user, "最后一次获得 圣诞活动副本奖励" );
		}else if( XORandom.run( 0, 10000 ) <= 2000 ){// 这里来随机 看能否得到装备 0.2 的几率 
			
			user.isGetChristmasDungeonAward = true;
			award.getContent().add( GameData.getChristmasDungeonAward() );
//			Logs.debug( user, "第" + user.christmasTimes + "次获得 圣诞活动副本奖励" );
		}
	}
	
	// 根据类型 提升属性
	private void promoteProperty( FighterBase attackers, byte ePropertyType ) {
		
		float odds = 1f;
		switch( ePropertyType ){
		case 1: odds = 1.5f; break;
		case 2: odds = 2f; break;
		}
		
		attackers.setAttack( (int) (attackers.getAttack() * odds) );
		if( attackers.getHp() != 0 ){
			attackers.setHpMax( (int) (attackers.getHpMax() * odds) );
			attackers.setHp( attackers.getHpMax() );
		}
	}

	/**
	 * 大龙的处理
	 * @param user 
	 * @param battle
	 */
	private void dragonHandle( UserInfo user, AutoBattle battle ) {
		
		Logs.debug( "对大龙造成伤害 =" + battle.getAccumulativeDamage() );
		
		DragonBase dragon = ActivityManager.getInstance().getDragon();
		
		dragon.reliefHp( battle.getAccumulativeDamage() );
		dragon.setUserData( user.getUID(), battle );
		
		// 如果大龙死亡 通知所有玩家
		if( dragon.isDie() ){
			dragon.dieHandle();
		}else{
//			user.setEctypeStatus( ErrorCode.FIGHTER_STATUS );
		}
		
//		ActivityManager.getInstance().update();
	}

	/**
	 * 根据副本ID 获取怪物
	 * @param user 
	 * @param ectypeID
	 * @param missionTemplet
	 * @param p
	 * @param fireBoss 
	 * @param bD 
	 * @return
	 */
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

	// 检查 所有ID 是否有错 
	private boolean checkID(UserInfo user, TollgateTemplet missionTemplet, short ectypeID, int missionId, byte p) {
	
		InstanceTemplet instanceTemplet = InstanceTempletCfg.getTempletById( ectypeID );
		
		if( instanceTemplet == null ){
			Logs.error( user, "申请战斗 - 错误的副本 ID=" + ectypeID );
			return true;
		}
		
		if( !instanceTemplet.isHaveID( missionId ) ){
			Logs.error( user, "申请战斗 - 副本ID=" + ectypeID + "没有关卡ID=" + missionId );
			return true;
		}
		
		if( missionTemplet == null ){
			Logs.error( user, "申请战斗 - 错误的关卡 ID=" + missionId );
			return true;
		}
		
		if( p >= missionTemplet.getThelvNum() || p < 0 ){
			Logs.error( user, "申请战斗 - 错误的波数 P=" + p );
			return true;
		}
		
		return false;
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
				
				UserManager.getInstance().putUpdate( user );
//			}
		}
	}

}

