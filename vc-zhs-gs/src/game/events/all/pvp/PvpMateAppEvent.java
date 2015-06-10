package game.events.all.pvp;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

import manager.DWType;
import define.DefaultCfg;
import define.SystemCfg;
import user.UserInfo;
import user.UserManager;
import util.ErrorCode;
import util.RandomUtil;
import util.UtilBase;
import game.award.AwardType;
import game.battle.auto.AutoBattle;
import game.battle.auto.Formation;
import game.battle.auto.ParseBattleSituation;
import game.battle.formation.IFormation;
import game.events.EventBase;
import game.events.EventDescrip;
import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;
import game.fighter.FighterBase;
import game.fighter.Hero;
import game.log.Logs;
import game.log.L;
import game.pvp.MatchingType;
import game.pvp.PvpMateInfo;
import game.pvp.PvpMateManager;
import game.pvp.VideoBase;

@EventDescrip(desc = "匹配战斗")
public class PvpMateAppEvent extends EventBase {

	@Override
	public void run( UserInfo user, ByteBuffer data ) throws IOException {
		
		byte at					= data.get();
		
		if( at == 1 ){
			
			long begin				= System.nanoTime();
			
			MatchingType type 		= MatchingType.fromNumber( data.get() );
			
			ErrorCode code			= ErrorCode.SUCCESS;
			PvpMateInfo mateInfo	= null;
			UserInfo touser			= null;
			boolean isOfRevenge		= false ;
			boolean isRevenge		= data.get() == 1 ;
			int battlelogID 		= 0;// 战报ID
			
			// 			-------------下面进行各种判断
			do{
				
				if( type == null ){
					code			= ErrorCode.UNKNOW_ERROR;
					break;
				}
				
				// 看是否开启复仇之心
				if( isRevenge ){
					battlelogID			= data.getInt();
					int toUserID		= data.getInt();
					mateInfo			= PvpMateManager.getInfo( toUserID, type );
					if( mateInfo == null ){
						code			= ErrorCode.MATE_TOUSER_NOT_TEAM;
						break;
					}
					isOfRevenge			= data.get() == 1 ;
				}
				
				// 先判断匹配次数是否足够
				code 				= user.getDanGradingManager().isCanMate();
				if( code != ErrorCode.SUCCESS ) break;
				
				// 设置阵型
				code 				= user.getTeamManager().setMateTeamData( data, type );
				if( code != ErrorCode.SUCCESS ) break;
				
				// 这里开始匹配 找出相当的 玩家 如果是复仇就不用找了 
				if( !isRevenge ){
					mateInfo 						= PvpMateManager.getInfo( user, type );
					if( mateInfo == null ) code 	= ErrorCode.CUR_NONE_MATE_INFO;
				}
				
			} while( false );
			
			
			ByteBuffer response 				= buildEmptyPackage( 8192 );
			response.putShort( (short) code.ordinal() );
			
			// 如果不成功 直接返回
			if( code != ErrorCode.SUCCESS ) {
				sendPackage( user.getCon(), response );
				return;
			}
			
			// 			 -------先发送匹配玩家 的数据
			touser								= mateInfo.getUser();
			response.putInt( touser.getUID() );
			UtilBase.encodeString( response, touser.getNickName() );
			response.putShort( touser.getLevel() );
			response.put( mateInfo.getDanGrad().toNumber() );
			response.putInt( mateInfo.getFighting() );
			
			// 先获取拷贝数据
			List<Hero> 	a					= user.getTeamManager().getMatcTeamToHero(type);
			if( isOfRevenge ){
				for( Hero h : a ){
					h.setAttack( (int) (h.getAttack() * 1.5f) );
					h.setHp( (int) (h.getHpMax() * 1.5f) );
					h.setHpMax( (int) (h.getHpMax() * 1.5f) );
				}
			}
			List<Hero> 	d					= mateInfo.getList();
			
//			response.put( (byte)a.size() ); /////////////////////
//			for( Hero hero : a ){
//				response.putInt( hero.getUID() );
//				response.putInt( hero.getNid() );
//				response.putShort( hero.getLevel() );
//				hero.getQuality().toByte( response );
//				response.put( hero.getPosition() );
//				response.putInt( hero.getHpMax() );
//				response.putInt( hero.getAttack() );
//				response.put( (byte) (hero.getIsCaptain() ? 1 : 0) );
//				response.put( hero.getSkillAttack().getLevel() );
//				response.putInt( hero.getCaptainSkill().getID() );
//			}
//			
//			mateInfo.putHeroData( response );
			
			
			//           -------开始战斗
			
			// 转换
			List<FighterBase> attackers 	= PvpMateManager.getMateReadyToHero( a );
			List<FighterBase> defenses 		= PvpMateManager.getMateReadyToHero( d );
			
			/** 在战斗前做出 数值调整  */
			for( FighterBase f : attackers ){
				// 这里看羁绊
				f.runFetter( attackers );
			}
			
			// 初始化 双方阵型
			IFormation aFormation 			= new Formation( attackers, true );
			IFormation dFormation 			= new Formation( defenses, false );
			
			// 开始把信息放入 自动回合战斗系统
			AutoBattle battle 				= new AutoBattle( aFormation, dFormation,
					user.getTalentManager().getTalents(),mateInfo.getTalents(),
					user.getDanGradingManager().getInfo().danGrad(), mateInfo.getDanGrad(),
					false );
			
			// 这里重新覆盖一次数据 因为 可能有队长技能加血量的
			List<FighterBase> fighter_a		= battle.getAttackHeroData();
			List<FighterBase> fighter_d		= battle.getDefenseHeroData();
			putHeroData( a, fighter_a, response );
			putHeroData( d, fighter_d, response );
			
			// 开始战斗 让防御方先出手
			battle.run( false );
			
			// 将战斗信息 发给前端
			ByteBuffer content 				= battle.getBattleSituation().getData().asReadOnlyBuffer();
			content.flip();
			response.put( content );
			
			// 攻击方 胜利
			boolean isWin 					= battle.getAttackerIsWin();
			
			// 打印信息
			if( SystemCfg.IS_DEBUG ){
				System.out.println( " 匹配战斗开始  " + user.getNickName() + " VS " + mateInfo.getUser().getNickName() );
				new ParseBattleSituation( null, null,battle.getBattleSituation() ).parse();
				System.out.println("战斗逻辑耗时：" + (System.nanoTime() - begin) / 1000000000f + "秒");
			}
			
			// 抢夺的金币
			int lootCash 					= 0;
			// 如果胜利 表示可以抢夺
			if( isWin ){
				if( isRevenge ){
					lootCash				= touser.getDanGradingManager().lootByCash( battlelogID );
				}else{ 
					// 这里看是否机器人
					if( !mateInfo.getIsRobot() )
						lootCash			= touser.getDanGradingManager().lootByCash(  );
					else
						lootCash			= 0;
				}
			}
			// 上下浮动10%
			float odds 	= RandomUtil.getRandomInt( 90, 110 ) / 100f;
			lootCash 	= (int) (lootCash * odds);
			
			// 今日抢夺金币
			response.putInt( lootCash );
			
			sendPackage( user.getCon(), response );
			
			// 抢夺相关   更新前端 
			if( lootCash > 0 ){
				user.changeAward( AwardType.CASH, lootCash, "匹配抢夺获得", DWType.MISCELLANEOUS );
				UpdateManager.instance.update( user, UpdateType.U_3 );
				
				if( !mateInfo.getIsRobot() ){
					touser.changeAward( AwardType.CASH, -lootCash, "匹配被抢夺消耗", DWType.MISCELLANEOUS );
					UpdateManager.instance.update( touser, UpdateType.U_3 );
					// 帮被抢夺者 保存下数据库
					touser.getDanGradingManager().updata();
				}
			}
			
//			synchronized (user) 
			{
				// 记录日志
				Logs.log( L.L_010,  user.getUID() + "," + user.getNickName() );
				
				// 战斗结束后 结算分数之类的  不是复仇才算匹配
				if( !isRevenge )
					user.getDanGradingManager().handle( isWin, type );
				
				// 如果是机器人后面不执行了
				if( mateInfo.getIsRobot() )
					return;
				
				// 战斗过程数据 ------------------------------------------
				ByteBuffer reportdata 		= ByteBuffer.allocate( content.limit() );
				battle.getBattleSituation().getData().flip();
				reportdata.put( battle.getBattleSituation().getData() );
				
				// 取得胜利者信息  这里保存信息 是怕对方删除英雄
				PvpMateInfo a_winInfo		= new PvpMateInfo( user, a, 0 ); // 攻击方 数据
				PvpMateInfo d_winInfo		= new PvpMateInfo( touser, d, 0 ); // 防守方数据
				reportdata.flip();
				// 战斗过程数据 ------------------------------------------
				
				// 帮被挑战者 记录 失败战报信息  必须要和他最后一次主动操作的颜色才行
				if( touser.getTeamManager().getMateType() == type ){
					touser.getDanGradingManager().addBattlelog( a_winInfo, d_winInfo , reportdata, lootCash, isWin, isRevenge, isOfRevenge );
				}
				
				// 如果是复仇 那么就要扣水晶
				if( isRevenge ){
					
					if( isOfRevenge ){
						int needGold = 20;
						if( SystemCfg.PLATFORM.equals( "DZ" ) ) needGold *= DefaultCfg.DZ_CONSUMPTION_RATIO;
						user.changeAward( AwardType.GOLD, -needGold, "开启复仇之心消耗", DWType.MISCELLANEOUS );
						UpdateManager.instance.update( user, UpdateType.U_4 );
					}
					
					// 删除掉录像 只有胜利才删除 和扣除复仇次数
					if( isWin ){
						user.getDanGradingManager().removeBattlelog( battlelogID );
						
						touser.getDanGradingManager().addBattlelog( a_winInfo, d_winInfo , reportdata, lootCash, isWin, isRevenge, isOfRevenge );
					}
					
				}
				
			}
			
			
		}else if( at == 2 ){
			
			int id		= data.getInt();
			
			VideoBase v	= user.getDanGradingManager().get( id );
			if( v == null ){
				Logs.error( user, "申请单条战报出错 没有该战报  id=" + id );
				return;
			}
			
			ByteBuffer response = buildEmptyPackage( 14098 );
			
			response.putShort( (short) 0 );//////////////
			response.putInt( 0 );//////////////
			String name = UserManager.getInstance().getNickName( v.mTouserID );
			UtilBase.encodeString( response, name );//////////////
			response.putShort( (short)0 );//////////////
			response.put( (byte)0 );//////////////
			response.putInt( 0 ); //////////////
			v.putHeroData( response, v.mDLists, (byte)6 );
			v.putHeroData( response, v.mALists, (byte)0 );
			
			ByteBuffer content 	= v.mData.asReadOnlyBuffer();
//			new ParseBattleSituation( v.mData ).parse();
			response.put( content );
			response.putInt( 0 );
			
			sendPackage( user.getCon(), response );
			
		}
		
		
	}

	private void putHeroData( List<Hero> a, List<FighterBase> fighter, ByteBuffer response ) {
		response.put( (byte)a.size() ); /////////////////////
		for( Hero hero : a ){
			int HP	= getHP( fighter, hero.getPosition() );
			if( HP != hero.getHpMax() && HP != 0 ){
				hero.setHp( HP );
				hero.setHpMax( HP );
			}
			response.putInt( hero.getUID() );
			response.putInt( hero.getNid() );
			response.putShort( hero.getLevel() );
			hero.getQuality().toByte( response );
			response.put( hero.getPosition() );
			response.putInt( hero.getHpMax() );
			response.putInt( hero.getAttack() );
			response.put( (byte) (hero.getIsCaptain() ? 1 : 0) );
			response.put( hero.getSkillAttack().getLevel() );
			response.putInt( hero.getCaptainSkill().getID() );
		}
	}

	private int getHP(List<FighterBase> fighter, byte position) {
		for( FighterBase f : fighter ){
			if( f.getPosition()%6 == position )
				return f.getHpMax();
		}
		return 0;
	}
	
	
	

	
}
