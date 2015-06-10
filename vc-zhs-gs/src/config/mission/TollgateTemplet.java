package config.mission;

import game.activity.DragonBase;
import game.award.AwardInfo;
import game.battle.auto.Formation;
import game.battle.formation.IFormation;
import game.events.all.fight.FireBoss;
import game.fighter.FighterBase;
import game.fighter.NpcFighter;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

import org.jdom2.Element;

import util.RandomUtil;

import config.fighter.NpcFighterTemplet;
import config.fighter.NpcFighterTempletCfg;
import config.tefineith.RefineithFireTempletCfg;

/**
 * 关卡 模板
 * @author DXF
 *
 */
public class TollgateTemplet {

	/** 关卡id */
	private final int					id;
	private final String				name;
	private final String				desc;
	
	// 类型  （1，普通  2，精英  3，挑战  4，活动）
	private final EctypeType			type;
		
	/** 怪物及阵型 */
	private final List<IFormation>		formations;
	
	/** 金币奖励 */
	private final int					awardGold;
	
	/** 英雄经验奖励 */
	private final int					awardHeroExp;
	
	/** 玩家经验奖励 */
	private final int					awardPlayerExp;
	
	/** 玩家奖杯奖励 */
	@Getter
	private int[]						awardTrophy = null;
	
	// 需要体力
	private final short					needStrength;
	
	/** 获得关卡ID */
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDesc() {
		return desc;
	}
	public EctypeType getType() {
		return type;
	}
	public int getAwardGold(){
		return awardGold;
	}
	public int getAwardHeroExp(){
		return awardHeroExp;
	}
	public int getAwardPlayerExp(){
		return awardPlayerExp;
	}
	public short getNeedStrength() {
		return needStrength;
	}
	
	/**
	 * 获得扫荡奖励
	 * @param i
	 * @return
	 */
	public List<AwardInfo> getSweepAward( int i ) {
		
		List<FighterBase> list 		= getFormationCloneByWave(i).getAllFighters();
		List<AwardInfo> awardInfo 	= new ArrayList<AwardInfo>();
		for( FighterBase f : list ){
			
			List<AwardInfo> content	= f.getAwardContent();
			if( content != null ){
				
				for( AwardInfo award : content ){
					int rand 		= (int) (award.getRand()[0] * 100);
					int	randValue	= RandomUtil.getRandomInt(0, 9999);
					if( randValue < rand ){
						awardInfo.add( award );
						break;
					}
				}
			}
		}
		
		return awardInfo;
	}
	
	/**
	 * 获得奖杯数
	 * @return
	 */
	public byte getTrophy() {
		if( awardTrophy == null ) return 0;
		return (byte) RandomUtil.getRandomInt(awardTrophy[0], awardTrophy[1]);
	}
	
	/** 获取某一波的战士阵型的深度克隆数据，波数从0开始 */
	public IFormation getFormationCloneByWave( int wave ){		
		return new Formation( formations.get( wave ) );
	}
	
	/** 获取大龙的数据 */
	public IFormation getFormationCloneByWave( DragonBase dragon ) {
		
		if( formations == null || formations.isEmpty() )
			throw new RuntimeException( " 出现大龙信息为空 " );
		
		// 这里默认第一波
		IFormation ifor			= formations.get( 0 );
		List<FighterBase> list	= ifor.getAllFighters();
		// 这里拷贝一份  如果list里面没有数据 那么就会报错
		FighterBase x			= new FighterBase( list.get(0) );
		
		
		List<FighterBase> xxx	= new ArrayList<FighterBase>();
		
//		CLog.debug( "设置前：" + x );
		// 设置它的血量
		x.setHpMax( dragon.getHpMax() );
		x.setHp( dragon.getHpCur() );
		x.setAttack( dragon.getAttack() );
//		CLog.debug( "设置后：" + x );
		
		xxx.add( x );
		return new Formation( xxx );
	}
	
	/**
	 * 获取试炼的数据
	 * @param p 波数
	 * @param fireBoss 
	 * @param id 副本ID
	 * @return
	 */
	public IFormation getFormationCloneByRefine( byte p, short eid, FireBoss fireBoss ) {
		
		InstanceTemplet templet = InstanceTempletCfg.getTempletById( eid );
		IFormation ifor 		= formations.get( p );
		List<FighterBase> list 	= new ArrayList<FighterBase>();
		
		for( int i = 0; i < ifor.getAllFighters().size(); i++ ){
			FighterBase f		= ifor.getAllFighters().get(i);
			boolean isBoss		= (p == getThelvNum() - 1) && i == ifor.getAllFighters().size() - 1 ; // 是否bosss 
			
			NpcFighter n 		= null;
			
			if( isBoss && fireBoss.id != 0 ){
				n				= RefineithFireTempletCfg.get( fireBoss.id, templet.indexOf(id) );
			}else{
				n				= RefineithFireTempletCfg.get( f.getId(), isBoss, templet.indexOf(id) );
			}
			
			n.setIsBottom( false );
			n.setPosition( f.getPosition() );
			list.add( n );
		}
		
		// 如果是第一波 那么就先把boss找出来
		if( p == 0 ){
			fireBoss.id 		= RefineithFireTempletCfg.getId( ifor.getAllFighters().get(0).getId(), true, templet.indexOf(id) );
		}
		
		return new Formation( list );
	}
	
	/**  获得波数 */
	public int getThelvNum()
	{
		return formations.size();
	}
	
	public TollgateTemplet(Element element) {
		id 				= Integer.parseInt( element.getChildText( "id" ) );
		name 			= element.getChildText( "name" );
		desc 			= element.getChildText( "desc" );
		type			= EctypeType.fromNumber( Integer.parseInt( element.getChildText( "type" ) ) );
		awardGold		= Integer.parseInt( element.getChildText( "awardGold" ) );
		awardHeroExp	= Integer.parseInt( element.getChildText( "awardHeroExp" ) );
		awardPlayerExp	= Integer.parseInt( element.getChildText( "awardPlayerExp" ) );
		needStrength	= Short.parseShort( element.getChildText( "needStrength" ) );
		formations 		= parseFormation( element );
		String trophy	= element.getChildText( "awardTrophy" );
		if( !trophy.isEmpty() ){
			String[] l	= trophy.split( "," );
			if( l.length == 2 ){
				try {
					awardTrophy 	= new int[2];
					awardTrophy[0]	= Integer.parseInt( l[0] );
					awardTrophy[1]	= Integer.parseInt( l[1] );
				} catch (Exception e) {
					awardTrophy		= null;
				}
			}
		}
		
	}
	
	private List<IFormation> parseFormation( Element element ){
		
		List<IFormation> formations = new ArrayList<IFormation>();
		boolean isBottom 			= false;//NPC通常作为防守方出现
		for( int i = 0; i < 3; i++ ){
			String nodeName 	= "fight" + i;
			String content 		= element.getChildText( nodeName );
			if( content == null || content.isEmpty() )
				continue;
			
			IFormation formation = null;
			try {
				List<FighterBase> fightersList 	= parseFighterList( content );
				formation 						= new Formation( fightersList, isBottom );
			} catch (Exception e) {
				continue;
			}
			
			formations.add( formation );
		}
		return formations;
	}

	private List<FighterBase> parseFighterList( String content ){
		
		List<FighterBase> list 	= new ArrayList<FighterBase>();
		String[] fighterArr 	= content.split( "\\|" );
		
		byte[] xxx				= { 0, 0, 0, 0, 0, 0 };
		for( String s : fighterArr ){
			
			String[] temp = s.split( "," );
			if( temp.length != 2 )
				throw new RuntimeException( content + " 副本关卡表错误    temp.length != 2  at=" + content );
			
			int fighterId 	= Integer.parseInt( temp[0] );
			byte pos 		= Byte.parseByte( temp[1] );
			
			if( pos < 0 || pos > Formation.TOTAL_COUNT ) 
				throw new RuntimeException( content + " 错误，配置表中的战士位置必须满足: 0 <= pos <= " + Formation.TOTAL_COUNT );
			
			if( xxx[pos] == 0 )
				xxx[pos] 	= 1;
			else
				throw new RuntimeException( content + " 错误，配置表中的战士位置 重复!" );
			
			if( fighterId < 0 ){
				FighterBase fig 				= new FighterBase( fighterId );
				fig.setHp( 1 ); // 这里要设置血量 要不然 会得不到怪
				fig.setHpMax( 1 );
				fig.setPosition( pos );
				list.add( fig );
			}else{
				NpcFighterTemplet npcfighter 	= NpcFighterTempletCfg.getNpcById( fighterId );
				
				if( npcfighter == null )
					throw new RuntimeException( "读取mission配置表错误   没有" + fighterId + "这个NPCID  at：" + id );
				
				NpcFighter f 					= new NpcFighter( npcfighter );
				f.setPosition( pos );
				list.add( f );
			}
		}
		//按位置排序
		return list;
	}

	public String toString() {
		return "MissionTemplet [id=" + id + ", name=" + name + ", desc=" + desc
				+ ", type=" + type 
				+ ", formations=" + formations 
				+ "]";
	}
	

}
