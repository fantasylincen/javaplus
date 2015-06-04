package game.team;

import game.award.AwardType;
import game.events.all.fight.BattleHeroBase;
import game.fighter.FighterBase;
import game.fighter.Hero;
import game.fightoffriend.FightOfFriendBase;
import game.log.Logs;
import game.pvp.MatchingType;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import define.DefaultCfg;

import manager.DWType;

import user.UserInfo;
import user.UserManager;
import util.ErrorCode;

/**
 * 玩家的团队管理
 * @author DXF
 */
public class TeamManager {

	private UserInfo									user;
	private final TeamDataProvider 						db = TeamDataProvider.getInstance();
	
	// 我的团队英雄 列表
	private List<TeamHero> 								teamHeroList;
	
	// 匹配 绿卡团队
	private List<TeamHero> 								matchGreenTeam;
	
	// 匹配 蓝卡团队
	private List<TeamHero> 								matchBlueTeam;
	
	// 匹配 紫卡团队
	private List<TeamHero> 								matchPurpleTeam;
	
	// 排位赛 团队
	private List<TeamHero>								qualifyingTeam;
	
	// 协助好友
	private AssistBase									assistFriends  ;
	
	// 最后一次主动操作的卡片颜色
	private MatchingType 								lastAccordOperate = MatchingType.GREEN_CARD;
	
	@SuppressWarnings("unchecked")
	public TeamManager( UserInfo user ) {
		super();
		this.user 			= user;
		Object[] obj		= db.get( user );
		
		teamHeroList		= (List<TeamHero>) obj[0];
		assistFriends		= (AssistBase) obj[1];
		
		matchGreenTeam		= (List<TeamHero>) obj[2];
		matchBlueTeam		= (List<TeamHero>) obj[3];
		matchPurpleTeam		= (List<TeamHero>) obj[4];
		lastAccordOperate	= (MatchingType) obj[5];
		qualifyingTeam		= (List<TeamHero>) obj[6];
		if( qualifyingTeam.isEmpty() )
			qualifyingTeam.addAll( teamHeroList );
	}

	public AssistBase getAssistFriend() {
		return assistFriends;
	}
	
	public void claerAssist(){
		assistFriends = null;
	}

	public MatchingType getLastAOMate(){
		return lastAccordOperate;
	}
	
	/**
	 * 获得队长信息
	 * @return
	 */
	public Hero getCaptain(){
		if( teamHeroList.isEmpty() ) return null;
		
		TeamHero team = teamHeroList.get(0);
		
		return user.getHeroManager().getHero( team.getUId() );
	}
	
	public List<TeamHero> getTeam() {
		return teamHeroList;
	}
	public List<TeamHero> getMatchGreenTeam() {
		return matchGreenTeam;
	}
	public List<TeamHero> getMatchBlueTeam() {
		return matchBlueTeam;
	}
	public List<TeamHero> getMatchPurpleTeam() {
		return matchPurpleTeam;
	}
	public List<TeamHero> getQualifyingTeam(){
		return qualifyingTeam;
	}
	
	/**
	 * 根据唯一ID 获取 数据
	 * @param uid
	 * @return
	 */
	public TeamHero get( int uid ) {
		for( TeamHero hero : teamHeroList ){
			if( hero.getUId() == uid )
				return hero;
		}
		return null;
	}
	
	/**
	 * 将团队取出
	 * @return
	 */
	public List<Hero> getToHero() {
		
		List<Hero> list = new ArrayList<Hero>();
		
		for( TeamHero t : teamHeroList ){
			
			Hero h = user.getHeroManager().getHero( t.getUId() );
			if( h == null ) continue;
			
			list.add( h );
		}
		
		return list;
	}
	
	/**
	 * 将团队取出
	 * @return
	 */
	public List<FighterBase> getReadyToHero() {
		
		List<FighterBase> fighter = new ArrayList<FighterBase>();
		
		for( int i = 0; i < teamHeroList.size(); i++ )
		{
			TeamHero teamHero 		= teamHeroList.get(i);
			
			FighterBase fighterBase = addReady( teamHero, i == 0 );
			
			fighter.add( fighterBase );
		}
		
		// 最后在把协助好友加上
		if( assistFriends != null && assistFriends.getPosition() != -1 ){
			
			// 这里拷贝一份 
			FighterBase fighterBase = new FighterBase( assistFriends.getCaptainHero() );
			
			// 如果死亡 那么就将hp设为0即可
			if( assistFriends.getIsDie() )
				fighterBase.setHp( 0 );
			
			// 设置位置
			fighterBase.setPosition( assistFriends.getPosition() );
			
			// 设置是否队长
			fighterBase.setIsCaptain( true );
			
			fighter.add( fighterBase );
		}
			
			
		return fighter;
	}
	private FighterBase addReady( TeamHero team, boolean isCaptain ){
		
		// 这里拷贝一份 
		FighterBase fighterBase = new FighterBase( user.getHeroManager().getFighter( team.getUId() ) );
		
		// 如果死亡 那么就将hp设为0即可
		if( team.getIsDie() )
			fighterBase.setHp( 0 );
		
		// 设置位置
		fighterBase.setPosition( team.getPosition() );
		
		// 设置是否队长
		fighterBase.setIsCaptain( isCaptain );
		
		return fighterBase;
	}
	
	/**
	 * 切换团队
	 * @param teamHero
	 */
	public void changeTeam( List<TeamHero> teamHero ) {
		
		if( teamHero == null || teamHero.isEmpty() ) return;
		
		teamHeroList.clear();
		teamHeroList = teamHero;
	}

	/**
	 * 切换团队阵型
	 * @param data
	 * @return
	 */
	public boolean changePosition( ByteBuffer data ) {
		
		byte count								= data.get();
		if( count < 1 || count > 6 ) return false;
		
		List<BattleHeroBase> nBattleHeroLists 	= new ArrayList<BattleHeroBase>();
		byte[] poss								= { 1, 1, 1, 1, 1, 1 };
		for( int i = 0; i < count; i++ )
		{
			int uID				= data.getInt();
			byte pos			= data.get();
			
			if( pos < 0 || pos >= poss.length ){ 
				Logs.error( user, "战斗时出错 英雄位置错误 pos=" + pos );
				assistFriends = null;
				return false;
			}
			
			if( poss[pos] == 1 ){
				poss[pos] = 0;
				nBattleHeroLists.add( new BattleHeroBase( uID, pos ) );
			}else{
				Logs.error( user, "战斗时出错 英雄位置重复！" );
				assistFriends = null;
				return false;
			}
		}
		
		return changePosition( nBattleHeroLists, data );
	}
	
	/**
	 * 切换英雄位置
	 * @param uID
	 * @param pos
	 * @return
	 */
	public boolean changePosition( List<BattleHeroBase> battleHeroLists, ByteBuffer data ) {
		
		if( teamHeroList.isEmpty() ) {
			Logs.error( user, "玩家还没有团队" );
			return false;
		}
		
		for( BattleHeroBase b : battleHeroLists ){
			
//			if( assistFriends != null && assistFriends.getUId() == b.m_nUID ){
//				
//				assistFriends.setPosition( b.m_nPosition );
//				
//				continue;
//			}
			
			TeamHero hero = get( b.m_nUID );
	
			if( hero == null ) {
				Logs.error( user, "切换英雄位置 出错  团队中没有 ID=" + b.m_nUID + " 这个英雄!" );
				return false;
			}
				
			hero.setPosition( b.m_nPosition );
		}
		
		// 接受邀请的好友信息
		/*int uID			= */data.getInt();
		byte pos			= data.get();
		
		if( assistFriends != null && pos != -1 ){
			assistFriends.setPosition( pos );
		}
		
		return true;
	}
	
	/**
	 * 设置 协助好友ID
	 * @param assistID
	 * @return
	 */
	public boolean setAssistID( int assistID ) {
		
		if( assistFriends != null ) assistFriends = null;
		
		UserInfo u 					= UserManager.getInstance().getByName( assistID );
		if( u == null ){
			Logs.debug( user, "当前战斗没有 好友助阵1" );
			return false;
		}
		
		FightOfFriendBase fBase		= user.getFightOfFriendManager().getUIDToUserID( assistID );
		if( fBase == null ){
			Logs.debug( user, "当前战斗没有 好友助阵2" );
			return false;
		}
		
		assistFriends 				= new AssistBase( fBase.m_nCaptainHero.getUID() );
		assistFriends.setUserUID( assistID );
		assistFriends.setCaptainHero( fBase.m_nCaptainHero );
		assistFriends.setExpert( fBase.m_nIsExpert );
		
		// 在这里记录 战斗时的协助好友 保证不能重复被邀请
		user.getFightOfFriendManager().put( assistID );
		
		return true;
	}
	
	/**
	 * 刷新死亡
	 * @param position
	 */
	public void updataDie( byte position ) {
		
		if( assistFriends != null && assistFriends.getPosition() == position )
			assistFriends.setIsDie( true );
			
		for( TeamHero hero : teamHeroList ){
			if( hero.getPosition() == position ){
				hero.setIsDie( true );
				break;
			}
		}
	}
	
	/**
	 * 刷新绝对死亡
	 */
	public void updataAbsoluteDie(){
		for( TeamHero hero : teamHeroList )
			hero.IsAbsoluteDie( hero.getIsDie() );
		if( assistFriends != null )
			assistFriends.IsAbsoluteDie( assistFriends.getIsDie() );
	}
	
	/**
	 * 将所有 复活
	 */
	public void updataLive(){
		for( TeamHero hero : teamHeroList ){
			hero.setIsDie( false );
			hero.IsAbsoluteDie( false );
		}
		if( assistFriends != null ){
			assistFriends.setIsDie( false );
			assistFriends.IsAbsoluteDie( false );
		}
	}
	
	/**
	 * 刷新团队信息
	 */
	public ErrorCode updata() {
		return db.updata( user, encrypt(), encryptAssist(), encryptMate(), encryptQualifying(), lastAccordOperate );
	}




	/**
	 * 初始团队信息
	 * @param teamHero
	 */
	public void initTeam( List<TeamHero> teamHero ) {
		
		if( teamHero == null || teamHero.isEmpty() ) return;
		
		teamHeroList.clear();
		teamHeroList = teamHero;
//		db.add( user, encrypt() );
	}
	
	public void add(){
		db.add( user, encrypt() );
	}
	
	// 将团队信息 加密成字符串
	private String encrypt(){
		
		StringBuilder output = new StringBuilder();
		
		for( int i = 0; i < teamHeroList.size(); i++ )
		{
			TeamHero hero = teamHeroList.get(i);
			
			// 在这里做下 是否 重复
			boolean isRepeat = false;
			for( int j = i + 1; j < teamHeroList.size(); j++ ){
				if( teamHeroList.get(i).getUId() == teamHeroList.get(j).getUId() ){
					isRepeat = true;
					break;
				}
			}
			if( isRepeat ) continue;
			/////
			
			output.append( hero.getUId() );
			output.append(",").append( hero.getPosition() );
			output.append(",").append( hero.getIsDie() ? 1 : 0 );
			output.append(",").append( hero.IsAbsoluteDie() ? 1 : 0 );
			output.append( "|" );
		}
		
		return output.toString();
		
	}
	private String encryptQualifying() {
		StringBuilder output = new StringBuilder();
		
		for( int i = 0; i < qualifyingTeam.size(); i++ )
		{
			TeamHero hero = qualifyingTeam.get(i);
			
			output.append( hero.getUId() );
			output.append(",").append( hero.getPosition() );
			output.append(",").append( hero.getIsDie() ? 1 : 0 );
			output.append(",").append( hero.IsAbsoluteDie() ? 1 : 0 );
			output.append( "|" );
		}
		return output.toString();
	}
	private String encryptAssist() {
		
		if( assistFriends == null )
			return "";
		
		StringBuilder content = new StringBuilder();
		
		content.append( assistFriends.getUserUID() ).append( "," );
		content.append( assistFriends.getPosition() ).append( "," );
		content.append( assistFriends.getIsDie() ? 1 : 0 ).append( "," );
		content.append( assistFriends.IsAbsoluteDie() ? 1 : 0 ).append( "," );
		content.append( assistFriends.getHeorContent() ).append( "," );
		content.append( assistFriends.getExpert() );
		
		return content.toString();
	}
	
	
	/**
	 * 删除指定英雄
	 * @param uid
	 */
	public void remove( int uid ) {
//      默认团队 是不可能被删除的 fox
		
		// 删除匹配团队
		if( remove( MatchingType.GREEN_CARD, uid ) )
			return;
		
		if( remove( MatchingType.BLUE_CARD, uid ) )
			return;
		
		if( remove( MatchingType.PURPLE_CARD, uid ) )
			return;
		
		// 删除排位的
		for( int i = 0; i < qualifyingTeam.size(); i++ ){
			TeamHero x = qualifyingTeam.get(i);
			if( x == null ) continue;
			if( x.getUId() == uid ) {
				qualifyingTeam.remove(i);
				return ;
			}
		}
	}

	/**
	 * 复活指定英雄
	 * @param uid
	 * @return
	 */
	public ErrorCode resirrection( int uid ) {
		
		TeamHero hero = get( uid );
		
		if ( hero == null || (hero != null && !hero.getIsDie()) ){
			
			if( assistFriends != null && assistFriends.getUId() == uid )
				hero = assistFriends;
			else{
				
				Logs.error( user, "玩家复活指定英雄 出错  团队中或协助好友没有 UID=" + uid + "这个英雄！" );
				return ErrorCode.TEAM_NOT_HERO;
				
			}
		}
		
		
		int needMoney = 3000;
		
		if( user.changeAward( AwardType.CASH, -needMoney, "战斗中复活英雄 消耗金币", DWType.MISCELLANEOUS) == -1 )
			return ErrorCode.USER_CASH_NOT_ENOUTH;
		
		hero.setIsDie( false );
		hero.IsAbsoluteDie( false );
		
		return ErrorCode.SUCCESS;
	}

	private String[] encryptMate() {
		
		String[] list 			= new String[3];
		
		StringBuilder content 	= new StringBuilder();
		for( TeamHero x : matchGreenTeam ){
			content.append( x.getUId() );
			content.append(",").append( x.getPosition() );
			content.append(",").append( x.getIsDie() ? 1 : 0 );
			content.append(",").append( x.IsAbsoluteDie() ? 1 : 0 );
			content.append( "|" );
		}
		list[0] 				= content.toString();
		
		content 				= new StringBuilder();
		for( TeamHero x : matchBlueTeam ){
			content.append( x.getUId() );
			content.append(",").append( x.getPosition() );
			content.append(",").append( x.getIsDie() ? 1 : 0 );
			content.append(",").append( x.IsAbsoluteDie() ? 1 : 0 );
			content.append( "|" );
		}
		list[1] 				= content.toString();
		
		content 				= new StringBuilder();
		for( TeamHero x : matchPurpleTeam ){
			content.append( x.getUId() );
			content.append(",").append( x.getPosition() );
			content.append(",").append( x.getIsDie() ? 1 : 0 );
			content.append(",").append( x.IsAbsoluteDie() ? 1 : 0 );
			content.append( "|" );
		}
		list[2] 				= content.toString();
		
		return list;
	}

	/**
	 * 根据匹配卡片 获取对应团队
	 * @param type
	 * @return
	 */
	public List<TeamHero> getTeam( MatchingType type ) {
		if( type == MatchingType.GREEN_CARD ){
			return matchGreenTeam;
		}else if( type == MatchingType.BLUE_CARD ){
			return matchBlueTeam;
		}else{
			return matchPurpleTeam;
		}
	}
	/**
	 * 设置匹配团队
	 * @param type
	 * @param list
	 */
	private void setTeam( MatchingType type, List<TeamHero> list ) {
		if( type == MatchingType.PURPLE_CARD ){
			matchPurpleTeam.clear();
			matchPurpleTeam.addAll( list );
		}else if( type == MatchingType.BLUE_CARD ){
			matchBlueTeam.clear();
			matchBlueTeam.addAll( list );
		}else{
			matchGreenTeam.clear();
			matchGreenTeam.addAll( list );
		}
	}
	// 删除指定英雄
	private boolean remove( MatchingType type, int uid ){
		
		List<TeamHero> list = getTeam( type );
		
		for( int i = 0; i < list.size(); i++ ){
			TeamHero x = list.get(i);
			if( x == null ) continue;
			
			if( x.getUId() == uid ) {
				list.remove(i);
				return true;
			}
		}
		
		return false;
	}
	/**
	 * 根据匹配卡牌 获取团队英雄  复制
	 * @param type
	 * @return
	 */
	public List<Hero> getMatcTeamToHero( MatchingType type ) {
		List<Hero> list 		= new ArrayList<Hero>();
		
		List<TeamHero> teamList = getTeam( type );
		
		for( int i = 0; i < teamList.size(); i++ ){
			
			TeamHero x			= teamList.get(i);
			if( x == null ) continue;
			
			// 这里拷贝一份 
			Hero cloneHero		= user.getHeroManager().cloneHero( x.getUId() );
			if( cloneHero == null ) continue;
			
			// 如果死亡 那么就将hp设为0即可
			if( x.getIsDie() )
				cloneHero.setHp( 0 );
			// 设置位置
			cloneHero.setPosition( x.getPosition() );
			
			// 设置队长
			if( i == 0 ) cloneHero.setIsCaptain( true );
			
			list.add( cloneHero );
		}
		
		return list;
	}

	/**
	 * 设置阵型  
	 * @param list
	 * @param type
	 * @return 
	 */
	public ErrorCode setMateTeamData( ByteBuffer data, MatchingType type ) {
		
		byte[] poss				= { 1, 1, 1, 1, 1, 1 };
		List<TeamHero> listx 	= new ArrayList<TeamHero>();
		List<Integer> xxx 		= new ArrayList<Integer>();
		listx.add( null );
		
		byte count				= data.get();
		if( count == 0 ){
			Logs.error( user, "匹配出错 出战英雄为0个" );
			return ErrorCode.UNKNOW_ERROR;
		}
		
		for( int i = 0; i < count; i++ ){
			int uid				= data.getInt();
			
			if( xxx.indexOf( uid ) != -1 ){
				Logs.error( user, "匹配战开始战斗出错 团队英雄重复  UID=" + uid );
				return ErrorCode.UNKNOW_ERROR;
			}
			
			Hero hero			= user.getHeroManager().getHero( uid );
			if( hero == null )  {
				Logs.error( user, "匹配战开始战斗出错 出战英雄UID=" + uid );
				return ErrorCode.UNKNOW_ERROR;
			}
			
			// 颜色不一样 不行的啊 
			if( hero.getQuality().getColour().toNumber() != type.toNumber() )
			{
				Logs.error( user, "匹配战开始战斗出错 英雄卡片颜色匹配  UID=" + uid );
				return ErrorCode.UNKNOW_ERROR;
			}
			
			byte pos			= data.get();
			byte isCaptain		= data.get();
			// 检查位置 是否重复
			if( poss[pos] == 1 ){
				poss[pos] 		= 0;
				TeamHero x		= new TeamHero( uid, pos, false );
				if( isCaptain == 1 ){
					listx.set( 0, x );
				}else{
					listx.add( x );
				}
				
				xxx.add( uid );
			}else{
				Logs.error( user, "匹配战斗时出错 英雄位置重复！" );
				return ErrorCode.UNKNOW_ERROR;
			}
		}
		
		if( listx.get(0) == null ) listx.remove(0);
			
		setTeam( type, listx );
		
		return ErrorCode.SUCCESS;
	}

	public MatchingType getMateType() {
		return lastAccordOperate;
	}
	private boolean isExistAssist() {
		return assistFriends != null && assistFriends.getPosition() != -1;
	}

	/**
	 * 获得友情点
	 * @return
	 */
	public int getAssistValue() {
		
		if( !isExistAssist() ) return 0;
		
		if( user.getFriendManager().isFriend( assistFriends.getUserUID() ) )
			return DefaultCfg.ECTYPE_A_FRIEND;
		else
			return DefaultCfg.ECTYPE_A_NOT_FRIEND;
	}

	/**
	 * 是否队长
	 * @param hero
	 * @return
	 */
	public boolean isCaptain( int uid ) {
		return teamHeroList.get(0).getUId() == uid;
	}

	/**
	 * 是否队长 包括PVP是否队长
	 * @return
	 */
	public boolean isCaptainAndPvp( int uid ){
	
		if( isCaptain( uid ) ) 
			return true;
		
		if( !matchGreenTeam.isEmpty() && matchGreenTeam.get(0).getUId() == uid ) 
			return true;
		
		if( !matchBlueTeam.isEmpty() && matchBlueTeam.get(0).getUId() == uid ) 
			return true;
		
		if( !matchPurpleTeam.isEmpty() && matchPurpleTeam.get(0).getUId() == uid ) 
			return true;
		
		return false;
	}
	
	/**
	 * 卸下某个队员
	 * @param hero
	 */
	public void discharge( int uid ) {
		for( TeamHero t : teamHeroList ){
			if( uid == t.getUId() ){
				teamHeroList.remove( t );
				return;
			}
		}
		remove( uid );
	}
	
	/////////////////////////////////////////排位赛////////////////////////////////////////////////////////

	public ErrorCode setQualifyingTeam( ByteBuffer data ){
		
		byte[] poss				= { 1, 1, 1, 1, 1, 1 };
		List<TeamHero> listx 	= new ArrayList<TeamHero>();
		List<Integer> xxx 		= new ArrayList<Integer>();
		listx.add( null );
		
		byte count				= data.get();
		if( count == 0 ){
			Logs.error( user, "排位出错 出战英雄为0个" );
			return ErrorCode.UNKNOW_ERROR;
		}
		
		for( int i = 0; i < count; i++ ){
			int uid				= data.getInt();
			
			if( xxx.indexOf( uid ) != -1 ){
				Logs.error( user, "排位战开始战斗出错 团队英雄重复  UID=" + uid );
				return ErrorCode.UNKNOW_ERROR;
			}
			
			Hero hero			= user.getHeroManager().getHero( uid );
			if( hero == null )  {
				Logs.error( user, "排位战开始战斗出错 出战英雄UID=" + uid );
				return ErrorCode.UNKNOW_ERROR;
			}
			
			
			byte pos			= data.get();
			boolean isCaptain	= i == 0;
			// 检查位置 是否重复
			if( poss[pos] == 1 ){
				poss[pos] 		= 0;
				TeamHero x		= new TeamHero( uid, pos, false );
				if( isCaptain ){
					listx.set( 0, x );
				}else{
					listx.add( x );
				}
				
				xxx.add( uid );
			}else{
				Logs.error( user, "排位战斗时出错 英雄位置重复！" );
				return ErrorCode.UNKNOW_ERROR;
			}
		}
		
		if( listx.get(0) == null ) listx.remove(0);
		
		qualifyingTeam.clear();
		qualifyingTeam.addAll(listx);
		
		return ErrorCode.SUCCESS;
	}

	public List<Hero> getQualifyingTeamToHero() {
		
		List<Hero> list = new ArrayList<Hero>();
		
		for( int i = 0; i < qualifyingTeam.size(); i++ ){
			
			TeamHero x			= qualifyingTeam.get(i);
			if( x == null ) continue;
			
			// 这里拷贝一份 
			Hero cloneHero		= user.getHeroManager().cloneHero( x.getUId() );
			if( cloneHero == null ) continue;
			
			// 如果死亡 那么就将hp设为0即可
			if( x.getIsDie() )
				cloneHero.setHp( 0 );
			// 设置位置
			cloneHero.setPosition( x.getPosition() );
			
			// 设置队长
			if( i == 0 ) cloneHero.setIsCaptain( true );
			
			list.add( cloneHero );
		}
		
		if( list.isEmpty() ){
			qualifyingTeam.addAll( teamHeroList );
			return getQualifyingTeamToHero();
		}
		
		return list;
	}

	public int expertNeedMoney() {
		return assistFriends == null ? 0 : assistFriends.getExpert();
	}

}
