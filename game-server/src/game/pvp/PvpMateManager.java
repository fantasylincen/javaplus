package game.pvp;

import java.util.ArrayList;
import java.util.List;

import robot.MateRobotManager;

import config.fighter.HeroTempletCfg;
import config.grow.UserGrowTempletCfg;
import config.talent.TalentTempletCfg;

import game.fighter.FighterBase;
import game.fighter.Hero;
import game.team.TeamManager;
import game.util.fighting.FightingFormula;
import user.UserInfo;
import user.UserManager;
import util.RandomUtil;

/**
 * PVP 匹配管理中心
 * @author DXF
 */
public class PvpMateManager {

	
	public PvpMateManager(){}
	
	/**
	 * 直接 获得指定玩家 数据
	 * @param user
	 * @param curFighting 
	 * @param type
	 * @return
	 */
	public static PvpMateInfo get( UserInfo user, MatchingType type ) {
		
		PvpMateInfo info = new PvpMateInfo();
		
		info.setUser( user );
		info.setList(  user.getTeamManager().getMatcTeamToHero( type ) );
		info.setFighting( FightingFormula.run( user, type ) );
		info.setDanGrad( user.getDanGradingManager().getInfo().danGrad() );
		info.setTalents( user.getTalentManager().getTalents() );
		
		return info;
	}
	
	/**
	 * 根据玩家 信息和卡片信息 进行匹配
	 * @param user
	 * @param type
	 * @return
	 */
	public static PvpMateInfo getInfo( UserInfo user, MatchingType type ){
		
		int mateCount				= user.getDanGradingManager().getInfo().mateCount();
		//匹配以四局为一个回合，采用三胜一负至，前三回合可根据前规则来匹配，最后一局采用匹配到比到> 自身实力数据的用户
		boolean round				= (mateCount + 1) % 4 == 0;
		
		// 匹配玩家战斗力
		int curFighting				= FightingFormula.run( user, type );
		int tempFighting			= 0;
		
		// 剩余的
		int residueUser				= -1;
		
		// 可以匹配的列表 到时候用来随机
		List<Integer> lists			= new ArrayList<Integer>();
		
		List<UserInfo> ls			= UserManager.getInstance().getMemoryAllUser();
		for( UserInfo temp : ls ){
			
			if( temp.getUID() == user.getUID() ) continue;
			
			TeamManager manager 	= temp.getTeamManager();
			
			if( manager.getTeam( type ).isEmpty() ) continue;
			
			int finghting			= FightingFormula.run( temp, type );
			
			// 这里 根据战斗力   如果 匹配到 记录数据 直接返回
			if( FightingFormula.toDetermineStrength( curFighting, finghting, round ) ){
				lists.add( temp.getUID() );
			}else{
				
				// 如果不符合 那么就放到预选里面 但是尽量找出 最接近自己的
				int xx 				= Math.abs( curFighting - finghting );
				if( tempFighting > xx || tempFighting == 0 ){
					tempFighting	= xx;
					residueUser		= temp.getUID();
				}
			}
		}
		
		// 这里随机一个出来 
		if( !lists.isEmpty() ){
			int rand				= RandomUtil.getRandomInt( 0, lists.size() - 1 );
			UserInfo xxx			= UserManager.getInstance().getByName( lists.get(rand) );
			return get( xxx, type );
		}
		
		// 这里如果没有 就找最接近的  注意：这里相差比较大
		if( residueUser != -1 ){
			UserInfo xxx			= UserManager.getInstance().getByName( residueUser );
			return get( xxx, type );
		}
		
		// 如果还是没有找到 那么就用机器人
		return MateRobotManager.get( user, type, round );
	}
	
	/**
	 * 根据玩家ID 获取玩家信息
	 * @param toUserID
	 * @param type
	 * @return
	 */
	public static PvpMateInfo getInfo( int toUserID, MatchingType type ) {
		
		UserInfo temp = UserManager.getInstance().getByName(toUserID);
		if( temp == null ) return null;
		
		TeamManager manager 	= temp.getTeamManager();
		
		if( manager.getTeam( type ).isEmpty() ) return null;
		
		return get( temp, type );
	}
	
	public static List<FighterBase> getMateReadyToHero( List<Hero> herolist ) {
		
		List<FighterBase> list = new ArrayList<FighterBase>();
		
		for( Hero hero : herolist ){
			list.add( new Hero( hero ) );
		}
		
		return list;
	}
	
	public static void main( String[] args ){
		
		HeroTempletCfg.init();
		TalentTempletCfg.init();
		UserGrowTempletCfg.init();
		
//		UserManager.getInstance().getByName( 1011725 );
//		UserManager.getInstance().getByName( 1011728 );
//		UserManager.getInstance().getByName( 1011746 );
//		System.out.println( user.getNickName() );
//		System.out.println( UserManager.getInstance().getMaps().size() );
		
		PvpMateInfo info = getInfo( new UserInfo(null, 1011776 ), MatchingType.GREEN_CARD );
		
		if( info == null ){
			System.out.println( "没有找到" );
			return ;
		}
		System.out.println( info.getUser().getNickName() );
	}


	
}
