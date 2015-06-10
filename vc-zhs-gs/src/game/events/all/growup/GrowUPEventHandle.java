package game.events.all.growup;

import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;
import game.fighter.Hero;
import game.growup.Colour;
import game.growup.EvolutionInfo;
import game.growup.HeroEvolution;
import game.growup.Material;
import game.growup.UpgradeInfo;
import game.growup.HeroUpgrade;
import game.log.Logs;
import game.log.L;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import deng.xxoo.utils.XOTime;

import notice.NoticeManager;

import user.UserInfo;
import util.ErrorCode;
import util.UtilBase;

/**
 * 英雄成长信息处理
 * @author DXF
 *
 */
public enum GrowUPEventHandle {
	
	// 选择一个升级英雄
	SELECT_HERO( 1 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			
			int uID		= data.getInt();
			
			Hero hero 	= user.getHeroManager().getHero( uID );
			if( hero == null ){
				Logs.error( user, "英雄成长-升级  选择一个英雄时出错  找不到ID=" + uID );
				return true;
			}
			
			response.putInt( hero.getExp() );
			response.putInt( hero.getGptuExp() );
			
			return false;
		}
	},
	// 选择吞噬英雄
	DEVOUR_HERO( 2 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			
			int uID				= data.getInt();
			Hero lordHero 		= user.getHeroManager().getHero( uID );
			if( lordHero == null ){
				Logs.error( user, "英雄成长-升级  选择吞噬英雄时出错  找不到ID=" + uID );
				return true;
			}
			
			byte size			= data.get();
			
			List<Hero> list		= new ArrayList<Hero>();
			for( int i = 0; i < size; i++ ){
				Hero hero		= user.getHeroManager().getHero( data.getInt() );
				if( hero == null ){
					Logs.error( user, "英雄成长-升级  选择吞噬英雄时出错  找不到ID=" + uID );
					return true;
				}
				// 这里看是不是出战英雄 出战英雄不能拿来吞噬
				if( user.getTeamManager().get( hero.getUID() ) != null ){
					Logs.error( user, "英雄成长-升级  选择吞噬英雄时出错  ID=" + uID + "出战中不能吞噬!" );
					return true;
				}
				if( hero.getUID() == uID ){
					Logs.error( user, "英雄成长-升级  选择吞噬英雄时出错  当前升级英雄不能吞噬自己!" );
					return true;
				}
				
				list.add(hero);
			}
			
			HeroUpgrade upgerade 		= new HeroUpgrade( lordHero, list );
			UpgradeInfo info			= upgerade.getDevourResult();
			
			response.putShort( (short)info.code().ordinal() );
			if( info.code() == ErrorCode.SUCCESS ){
				response.putShort( info.getLevel() );
				response.putInt( info.getHp() );
				response.putInt( info.getAttack() );
				response.putInt( info.getExp() );
				response.putInt( info.needGold() );
				response.put( info.skillOdds() );
			}
			
			return false;
		}
	},
	// 点击升级
	UPGRADE( 3 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			
			int uID				= data.getInt();
			Hero lordHero 		= user.getHeroManager().getHero( uID );
			
			ErrorCode code		= ErrorCode.SUCCESS;
			List<Hero> list		= new ArrayList<Hero>();
			
			if( lordHero == null ){
				Logs.error( user, "英雄成长-升级  选择吞噬英雄时出错  找不到ID=" + uID );
				code			= ErrorCode.UNKNOW_ERROR;
			}
			
			// 为公告先记录技能等级
//			byte skilllv		= lordHero.getSkillAttack().getLevel();
			if( code == ErrorCode.SUCCESS ){
				
				byte size			= data.get();
				for( int i = 0; i < size; i++ ){
					int uid			= data.getInt();
					Hero hero		= user.getHeroManager().getHero( uid );
					if( hero == null ){
						Logs.error( user, "英雄成长-升级  选择吞噬英雄时出错  找不到ID=" + uid );
						code			= ErrorCode.UNKNOW_ERROR;
						break;
					}
					// 这里看是不是出战英雄 出战英雄不能拿来吞噬
					if( user.getTeamManager().get( hero.getUID() ) != null ){
						Logs.error( user, "英雄成长-升级  选择吞噬英雄时出错  ID=" + uid + "出战中不能吞噬!" );
						code			= ErrorCode.UNKNOW_ERROR;
						break;
					}
					if( hero.getUID() == uID ){
						Logs.error( user, "英雄成长-升级  选择吞噬英雄时出错  当前升级英雄不能吞噬自己!" );
						code			= ErrorCode.UNKNOW_ERROR;
						break;
					}
					list.add(hero);
				}
				
				if( code ==  ErrorCode.SUCCESS ){
					HeroUpgrade upgerade 	= new HeroUpgrade( lordHero, list );
					UpgradeInfo info		= upgerade.getDevourResult();
					code					= info.code();
					if( code == ErrorCode.SUCCESS )
						code				= user.getHeroManager().upgerade( lordHero, list , info );
				}
				
			}
			
			try {
				
				response.putShort( (short)code.ordinal() );
				if(  code == ErrorCode.SUCCESS  ){
					response.put( lordHero.getSkillAttack().getLevel() );
//					synchronized( user ){
						// 刷新前端
						UpdateManager.instance.update( user, UpdateType.U_3 );
						UpdateManager.instance.update(user, UpdateType.U_101, list );
						list.clear();
						list.add( lordHero );
						UpdateManager.instance.update(user, UpdateType.U_102, list );
//					}
					
					// 记录公告 技能升级
//					if( lordHero.getSkillAttack().getLevel() > skilllv && lordHero.getSkillAttack().isFullLevel() )
//						NoticeManager.getInstance().addTimely( 203, UtilBase.nToPlainText( user.getNickName() ), UtilBase.nToHero( lordHero ), UtilBase.nToSkill( lordHero.getSkillAttack().getSkill().getId(), lordHero.getSkillAttack().getLevel() ) );
				}
				
				return false;
				
			} catch (Exception e) {
				Logs.error( user, e.getMessage() );
				return true;
			}finally{
				if( code == ErrorCode.SUCCESS ){
					// 记录日志
					Logs.log( L.L_007,  user.getUID() + "," + user.getNickName() );
					
					user.getHeroManager().update( lordHero.getUID() );
				}
				
			}
			
		}
	},
	// 选择进化英雄
	SELECT_EVOLUTION_HERO( 4 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			
			int uID		= data.getInt();
			
			Hero hero 	= user.getHeroManager().getHero( uID );
			if( hero == null ){
				Logs.error( user, "英雄成长-进化 选择一个英雄时出错  找不到ID=" + uID );
				return true;
			}
			
			HeroEvolution evolution = new HeroEvolution( hero );
			
			EvolutionInfo info 		= evolution.getReslut( user.getHeroManager().getLists(), user.getTrophyNumer() );
			
			info.quality.toByte( response );
			response.putShort( info.maxLevel );
			response.putInt( info.hp );
			response.putInt( info.attack );
			response.put( hero.getSkillAttack().getLevel() );
			response.put( (byte) info.material.size() );
			for( Material material : info.material ){
				response.putInt( material.nID );
				material.quality.toByte( response );
				response.putInt( material.uID );
			}
		
			return false;
		}
	},
	// 点击进化
	START_EVOLUTION( 5 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			
			XOTime.beginTimer( );
			int uID			= data.getInt();
			
			ErrorCode code 	= ErrorCode.SUCCESS;
			Hero hero 		= user.getHeroManager().getHero( uID );
			if( hero == null ){
				Logs.error( user, "英雄成长-进化 选择一个英雄时出错  找不到ID=" + uID );
				code		= ErrorCode.UNKNOW_ERROR;
			}
			
			// 现在这获取进化前名字 为了发公告
			Hero fName	= new Hero( hero );
			// 记录之前颜色 方便后面看是不是夸颜色进化
			Colour beforeC	= hero.getQuality().getColour();
			
			List<Hero> list	= new ArrayList<Hero>();
			
			if( code == ErrorCode.SUCCESS ){
				
				byte size		= data.get();
				for( int i = 0; i < size; i++ ){
					int id		= data.getInt();
					Hero h		= user.getHeroManager().getHero( id );
					
					if( h == null ){
						Logs.error( user, "英雄成长-进化  找不到材料ID=" + id );
						code		= ErrorCode.UNKNOW_ERROR;
						break;
					}
					
					if( id == uID ){
						Logs.error( user, "英雄成长-进化 不能吞噬自己=" + id );
						code		= ErrorCode.UNKNOW_ERROR;
						break;
					}
					
					if( h.getNid() != hero.getNid() ){
						Logs.error( user, "英雄成长-进化 不是同一种卡片=" + id );
						code		= ErrorCode.UNKNOW_ERROR;
						break;
					}
					
					if( user.getTeamManager().get(id) != null ){
						Logs.error( user, "英雄成长-进化  出战中不能被吞噬ID=" + id );
						code		= ErrorCode.UNKNOW_ERROR;
						break;
					}
					
					list.add( h );
				}
				
				EvolutionInfo info 			= null;
				do{
					
					if( code != ErrorCode.SUCCESS ) break;
					
					HeroEvolution evolution = new HeroEvolution( hero );
					info 					= evolution.getReslut( list, user.getTrophyNumer() );
					
					if( info.code != ErrorCode.SUCCESS ) break;
					
					// 如果携带等级大于自身
					if( info.carryLv > user.getLevel() ){
						
						// 如果是队长 不准他进化
						if( user.getTeamManager().isCaptain( hero.getUID() ) ){
							info.code			= ErrorCode.UNKNOW_ERROR;
							break;
						}
						
						// 如果是队员 将自动 卸下
						user.getTeamManager().discharge( hero.getUID() );
					}
					
					info.code				= user.getHeroManager().startEvolution( hero, list, info );
					
				}while( false );
			
				code						= info != null ? info.code : code;
			}
			
			try{
				
				response.putShort( (short) code.ordinal() );
				
				// 更新前端
				if( code == ErrorCode.SUCCESS ){
					
//					synchronized ( user ){
						
						UpdateManager.instance.update( user, UpdateType.U_3 );
						
						UpdateManager.instance.update( user, UpdateType.U_101, list );
						list.clear();
						list.add( hero );
						UpdateManager.instance.update( user, UpdateType.U_102, list );
//					}
					
					// 记录公告
					NoticeManager.getInstance().addTimely( 201, UtilBase.nToPlainText( user.getNickName() ), UtilBase.nToHero( fName ), UtilBase.nToHero( hero ) );
				}
				
//				XOTime.endTimerToPrint( );
				return false;
				
			} catch (Exception e) {
				Logs.error( user, e.getMessage() );
				return true;
			}finally{
				if( code == ErrorCode.SUCCESS ){
					// 记录日志
					Logs.log( L.L_008,  user.getUID() + "," + user.getNickName() );
					
					// 刷新数据库
					user.getHeroManager().update( hero.getUID() );
					
					// 如果在团队中有 那么就要删除掉
					if( beforeC != hero.getQuality().getColour() )
						user.getTeamManager().remove( hero.getUID() );
				}
			}
			
		}
		
	},
	
	// 选择升级队长技能英雄
	SELECT_CSKILL_HERO( 6 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			
			response.putInt( user.getCaptainSkillManager().getID() );
			
			response.put( user.getCaptainSkillManager().getCount() );
//			response.put( (byte) (2 - user.getCaptainSkillManager().getCount() < 0 ? 0 : 2 - user.getCaptainSkillManager().getCount() ) );
			
			return false;
		}
	},
	// 点击技能重置
	CLICK_RESET( 7 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			
			user.getCaptainSkillManager().reset();
			
			int capSkillID = user.getCaptainSkillManager().getID();
			
			response.putInt( capSkillID );
			
			// 添加公告 如果是宗师级
			if( capSkillID % 10 == 5 )
				NoticeManager.getInstance().addTimely( 202, UtilBase.nToPlainText( user.getNickName() ) );
						
			return false;
		}
	},
	// 点击学习
	CLICK_LEARN( 8 ) {
		@Override
		public boolean run(UserInfo user, ByteBuffer data, ByteBuffer response) throws IOException {
			
			int uID			= data.getInt();
			Hero hero 		= user.getHeroManager().getHero( uID );
			
			ErrorCode code	= ErrorCode.SUCCESS;
			if( hero == null ){
				Logs.error( user, "英雄成长--队长技能升级 选择一个英雄时出错  找不到ID=" + uID );
				code		= ErrorCode.UNKNOW_ERROR;
			}
			
			try {
				
				if( code == ErrorCode.SUCCESS )
					code = user.getCaptainSkillManager().learn( hero );
				
				response.putShort( (short) code.ordinal() );
				
				return false;
			
			} catch (Exception e) {
				Logs.error( user, e.getMessage() );
				return true;
			}finally{
				
				if( code == ErrorCode.SUCCESS )
					user.getHeroManager().update( hero.getUID() );
			}
			
		}
	};

	private final byte 				number;
	
	GrowUPEventHandle( int n ) {
		number = (byte) n;
	}
	public byte toNumber() {
		return number;
	}
	private static final Map<Byte, GrowUPEventHandle> numToEnum = new HashMap<Byte, GrowUPEventHandle>();
	static{
		for( GrowUPEventHandle a : values() ){
			numToEnum.put( a.number, a );
		}
	}
	public static GrowUPEventHandle fromNumber( int n ){
		return numToEnum.get( (byte)n );
	}
	
	public abstract boolean run( UserInfo user, ByteBuffer data, ByteBuffer response ) throws IOException;
}
