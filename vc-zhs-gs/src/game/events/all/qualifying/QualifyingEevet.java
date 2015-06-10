package game.events.all.qualifying;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

import manager.DWType;
import define.DefaultCfg;
import define.GameData;
import define.GameDataProvider;
import define.SystemCfg;
import user.UserInfo;
import user.UserManager;
import util.ErrorCode;
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
import game.pvp.PvpMateManager;
import game.qualifying.BattlefieldReport;
import game.qualifying.Challenges;
import game.qualifying.QualifyingBase;
import game.team.TeamHero;
import game.util.fighting.FightingFormula;

@EventDescrip(desc = "排位系统  ")
public class QualifyingEevet extends EventBase{

	@Override
	public void run( UserInfo user, ByteBuffer buf ) throws IOException {
		
		byte at 	= buf.get();
		ByteBuffer response = buildEmptyPackage( 10240 );
		response.put( at );
		
		switch( at ){
		case 1: // 申请排位赛信息
			QualifyingBase qualifying = user.getQualifyingManager().getQualifying();
			
			response.putInt( GameData.ranking.indexOf( user.getUID() ) );
			response.putInt( qualifying.getStandings() );
			response.put( qualifying.getResidueDegree() );
			response.put( qualifying.getFrequency() );
			
			List<TeamHero> list = user.getTeamManager().getQualifyingTeam();
			response.put( (byte) list.size() );
			for( int i = 0; i < list.size(); i++ ){
				TeamHero hero = list.get(i);
				response.putInt( hero.getUId() );
				response.put( hero.getPosition() );
				response.put( (byte) (i == 0 ? 1 : 0) );
			}
			response.putInt( FightingFormula.run1( user, list ) );
			
			List<Challenges> challenge = qualifying.getChallenge();
			response.put( (byte) challenge.size() );
			for( Challenges c : challenge ){
				UserInfo temp = UserManager.getInstance().getByName( c.getUid() );
				response.putInt( c.getUid() );
				UtilBase.encodeString( response,  temp.getNickName() );
				response.putShort( temp.getLevel() );
				response.putInt( GameData.ranking.indexOf( temp.getUID() ) );
				List<Hero> xx = temp.getTeamManager().getQualifyingTeamToHero();
				response.put( (byte) xx.size() );
				for( Hero t : xx ){
					response.putInt( t.getNid() );
//					response.put( t.getPosition() );
					t.getQuality().toByte( response );
				}
				response.putInt( FightingFormula.run( user, xx ) );
				response.putInt( c.getCdTimeToTimer() );
			}
			sendPackage( user.getCon(), response );
			return;
		case 2: // 改变阵型
		{
			ErrorCode code = user.getTeamManager().setQualifyingTeam(buf);
			response.putShort( (short) code.ordinal() );
			if( code == ErrorCode.SUCCESS )
				response.putInt( FightingFormula.run1( user, user.getTeamManager().getQualifyingTeam() ) );
			sendPackage( user.getCon(), response );
			return;
		}
		case 3: // 点击换一批
			
			user.getQualifyingManager().Inabatch();
			
			List<Challenges> challenge1 = user.getQualifyingManager().getQualifying().getChallenge();
			response.put( (byte) challenge1.size() );
			for( Challenges c : challenge1 ){
				UserInfo temp = UserManager.getInstance().getByName( c.getUid() );
				response.putInt( c.getUid() );
				UtilBase.encodeString( response,  temp.getNickName() );
				response.putShort( temp.getLevel() );
				response.putInt( GameData.ranking.indexOf( temp.getUID() ) );
				List<Hero> xx = temp.getTeamManager().getQualifyingTeamToHero();
				response.put( (byte) xx.size() );
				for( Hero t : xx ){
					response.putInt( t.getNid() );
//					response.put( t.getPosition() );
					t.getQuality().toByte( response );
				}
				response.putInt( FightingFormula.run( user, xx ) );
				response.putInt( c.getCdTime() );
			}
			sendPackage( user.getCon(), response );
			return;
		case 4: // 立即挑战
		{
			ErrorCode code 	= ErrorCode.SUCCESS;
			long begin		= System.nanoTime();
			int uid 		= buf.getInt();
			UserInfo quilt 	= UserManager.getInstance().getByName( uid );
			
			do{
				if( quilt == null ){
					code		= ErrorCode.UNKNOW_ERROR;
					break;
				}
				
				code 			= user.getTeamManager().setQualifyingTeam( buf );
				if( code != ErrorCode.SUCCESS ) break;
				
				if( !user.getQualifyingManager().getQualifying().isChallenges() ) {		
					code		= ErrorCode.UNKNOW_ERROR;
					break;
				}
				
				// 查看是否 是那3个人
				if( !user.getQualifyingManager().getQualifying().challengesIsHave( uid ) ){
					code		= ErrorCode.UNKNOW_ERROR;
					break;
				}
				
			}while(false);
			
			
			boolean isWin		= false;
			ByteBuffer record 	= ByteBuffer.allocate( 10240 );
			record.putShort( (short) code.ordinal() );
			if( code == ErrorCode.SUCCESS ){
				
				record.putInt( quilt.getUID() );
				UtilBase.encodeString( record, quilt.getNickName() );
				record.putShort( quilt.getLevel() );
				record.put( quilt.getDanGradingManager().getInfo().danGrad().toNumber() );
				record.putInt( FightingFormula.run( quilt ) );
				
				List<Hero> our 	= user.getTeamManager().getQualifyingTeamToHero();
				List<Hero> foe 	= quilt.getTeamManager().getQualifyingTeamToHero();
				// 转换
				List<FighterBase> attackers 	= PvpMateManager.getMateReadyToHero( our );
				List<FighterBase> defenses 		= PvpMateManager.getMateReadyToHero( foe );
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
						user.getTalentManager().getTalents(),quilt.getTalentManager().getTalents(),
						user.getDanGradingManager().getInfo().danGrad(), quilt.getDanGradingManager().getInfo().danGrad(),
						false );
				
				// 这里重新覆盖一次数据 因为 可能有队长技能加血量的
				List<FighterBase> fighter_a		= battle.getAttackHeroData();
				List<FighterBase> fighter_d		= battle.getDefenseHeroData();
				putHeroData( our, fighter_a, record );
				putHeroData( foe, fighter_d, record );
				
				// 开始战斗  让防御方先出手
				battle.run( false );
				
				// 将战斗信息 发给前端
				ByteBuffer content 		= battle.getBattleSituation().getData().asReadOnlyBuffer();
				content.flip();
				record.put( content );
				
				// 攻击方 胜利
				isWin					= battle.getAttackerIsWin();
				
				// 打印信息
				if( SystemCfg.IS_DEBUG ){
					System.out.println( " 匹配战斗开始  " + user.getNickName() + " VS " + quilt.getNickName() );
					new ParseBattleSituation( null, null,battle.getBattleSituation() ).parse();
					System.out.println("战斗逻辑耗时：" + (System.nanoTime() - begin) / 1000000000f + "秒");
				}
			
			}
			
			ByteBuffer tempBuffer = record.asReadOnlyBuffer();
			tempBuffer.flip();
			response.put( tempBuffer );
			response.putInt( 0 );
			sendPackage( user.getCon(), response );
			
			// 胜利或者失败后做的处理
			if( code == ErrorCode.SUCCESS ){
//				synchronized ( user ) 
				{
					ByteBuffer data 	= ByteBuffer.allocate( tempBuffer.limit() );
					record.flip();
					data.put( record );
					user.getQualifyingManager().handle( isWin, data, quilt );
					GameDataProvider.getInstance().updateRanking();
					
					user.getQualifyingManager().update();
				}
			}
			return;
		}
		case 5: // 立即重置
		{
			ErrorCode code 	= ErrorCode.SUCCESS;
			int uid 		= buf.getInt();
			UserInfo quilt 	= UserManager.getInstance().getByName( uid );
			
			do{
				if( quilt == null ){
					code		= ErrorCode.UNKNOW_ERROR;
					break;
				}
				
				Challenges c	= user.getQualifyingManager().getQualifying().getChallenge( uid );
				// 查看是否 是那3个人
				if( c == null ){
					code		= ErrorCode.UNKNOW_ERROR;
					break;
				}
				int needGold = 20;
				if( SystemCfg.PLATFORM.equals( "DZ" ) ) needGold *= DefaultCfg.DZ_CONSUMPTION_RATIO;
				if( user.changeAward( AwardType.GOLD, -needGold, "执行排位立即重置扣取", DWType.MISCELLANEOUS ) == -1 ){
					code		= ErrorCode.UNKNOW_ERROR;
					break;
				}
				
				c.setCdTime( 0 );
			}while(false);
			
			response.putShort( (short) code.ordinal() );
			sendPackage( user.getCon(), response );
			UpdateManager.instance.update( user, UpdateType.U_4 );
			
			return;
		}
		case 6: // 立即购买
		{
			//每人每日5次，可购买10次，每日购买消耗水晶数量为：1-2：20，3-5：30，6-8：50，9-10：100（：前面为购买次数，：后面为消耗水晶数量）。
			
			ErrorCode code 	= ErrorCode.SUCCESS;
			do{
				if( !user.getQualifyingManager().getQualifying().isCanBuy() ) { code = ErrorCode.UNKNOW_ERROR; break; }
				
				int needGold = user.getQualifyingManager().getQualifying().buyNeedGold();
				if( SystemCfg.PLATFORM.equals( "DZ" ) ) needGold *= DefaultCfg.DZ_CONSUMPTION_RATIO;
				if( user.changeAward( AwardType.GOLD, -needGold, "执行排位立即重置扣取", DWType.MISCELLANEOUS ) == -1 ) { code = ErrorCode.UNKNOW_ERROR; break; }
				
				user.getQualifyingManager().getQualifying().startBuy();
			}while(false);
			
			response.putShort( (short) code.ordinal() );
			if( code == ErrorCode.SUCCESS ){
				response.put( user.getQualifyingManager().getQualifying().getResidueDegree() );
				response.put( user.getQualifyingManager().getQualifying().getFrequency() );
			}
			sendPackage( user.getCon(), response );
			
			UpdateManager.instance.update( user, UpdateType.U_4 );
			
			return;
		}
		case 7: // 查看排行榜
		{
			int maxSize = 100;//最大个数
			response.put( (byte) (GameData.ranking.size() <= maxSize ? GameData.ranking.size() : maxSize) );
			for( int i = 0; i < GameData.ranking.size() && i < maxSize; i++ ){
				int uid 		= GameData.ranking.get(i);
				UserInfo temp 	= UserManager.getInstance().getByName(uid);
				response.putInt( temp.getUID() );
				UtilBase.encodeString( response, temp.getNickName() );
			}
			sendPackage( user.getCon(), response );
			return;
		}
		case 8: // 查看玩家（这个只针对查看排行榜里面的）
		{
			int uid 		= buf.getInt();
			UserInfo temp 	= UserManager.getInstance().getByName(uid);
			if( temp == null ) return ;
			
			UtilBase.encodeString( response, temp.getNickName() );
			response.putShort( temp.getLevel() );
			response.putInt( GameData.ranking.indexOf( temp.getUID() ) );
			response.putInt( FightingFormula.run( temp ) );
			List<Hero> our 	= temp.getTeamManager().getQualifyingTeamToHero();
			response.put( (byte) our.size() );
			for( Hero h : our ){
				response.putInt( h.getNid() );
				h.getQuality().toByte(response);
			}
			
			sendPackage( user.getCon(), response );
			return;
		}
		case 9: // 查看录像
		{
			List<BattlefieldReport> battles = user.getQualifyingManager().getQualifying().getBattlefields();
			response.put( (byte) battles.size() );
			for( BattlefieldReport b : battles ){
				response.putInt( b.uid );
				response.putInt( b.time );
				response.put( b.type );
				response.putInt( b.quiltUID );
				UtilBase.encodeString( response, UserManager.getInstance().getNickName( b.quiltUID ) );
				response.putInt( b.rank + 1 );
			}
			sendPackage( user.getCon(), response );
			return;
		}
		case 10: // 查看录像
		{
			int id = buf.getInt();
			BattlefieldReport battlefield = user.getQualifyingManager().getQualifying().getBattlefield( id );
			if( battlefield == null ) return;
			response.put( battlefield.data.asReadOnlyBuffer() );
			sendPackage( user.getCon(), response );
			return;
		}
		case 11: // 领取奖励
		{
			ErrorCode code 	= ErrorCode.SUCCESS;
			int gold[]		= null;
			int rank		= GameData.ranking.indexOf( user.getUID() );
			do{
				if( !user.getQualifyingManager().getQualifying().isGetAward() ){ code = ErrorCode.UNKNOW_ERROR; break; }
				if( rank == -1 ) { code = ErrorCode.UNKNOW_ERROR; break; }
				
				gold 		= user.getQualifyingManager().getQualifying().startGetAwardToGold( rank );
				user.changeAward( AwardType.GOLD, gold[1], "排位奖励", DWType.MISCELLANEOUS );
				user.changeAward( AwardType.CASH, gold[0], "排位奖励", DWType.MISCELLANEOUS );
				user.getQualifyingManager().getQualifying().setIsGetAward( false );
			}while(false);
			
			response.putShort( (short) code.ordinal() );
			if( code == ErrorCode.SUCCESS ){
				response.putInt( rank );
				response.putInt( gold[0] );
				response.putInt( gold[1] );
			} 
			sendPackage( user.getCon(), response );
			return;
		}
		}
	}

	private void putHeroData(List<Hero> list, List<FighterBase> fighter, ByteBuffer response) {
		response.put( (byte)list.size() ); /////////////////////
		for( Hero hero : list ){
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
